package addressframework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import addressframework.exception.dto.InvalidRequestException;
import addressframework.model.UserActivity;
import addressframework.repository.UserActivityRepository;
import addressframework.session.security.CreateJWT;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Service Implementation Class to generate and validate otp
 * 
 * @author Anup Gupta
 *
 */
@Service
public class SessionServiceImpl {

	@Autowired
	private UserActivityRepository userActivityRepo;

	@Autowired
	private CreateJWT createJWT;

	@Value("${session.expire.time}")
	private int sessionExpireTime;
	
	@Value("${secret.value}")
    private int secretValue;
	
	public int validateSession(HttpServletRequest reqst, HttpServletResponse resp) {
		int userId = 0;
		Map<String, String> headerMap = new HashMap<String, String>();
		boolean jwtToken = false;
		Enumeration headerNames = reqst.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = reqst.getHeader(key);
			headerMap.put(key, value);
		}
		
		// Fetch CSRF Token from Header
		String strCSRFToken = headerMap.get("csrftoken");

		Cookie[] cookies = reqst.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cookieName = cookie.getName();
				String cookieValue = cookie.getValue();

				if (cookieName.equalsIgnoreCase("_usr_jwt_token")) {
					jwtToken = true;
					UserActivity userActivity = getSessionDetails(cookieValue);
					if (cookieValue.equalsIgnoreCase(userActivity.getSessionId())) {
						if (userActivity.getSessionEndTime()
								.compareTo(new Timestamp(System.currentTimeMillis())) != 0) {
							updateSessionDetails(userActivity);
							userId=userActivity.getUserId();
						} else {
							clearSessionCookies(resp);
							throw new InvalidRequestException(HttpStatus.UNAUTHORIZED, "userSession",
									"You are not authorized to access " + "resource", "SESSION-001");
						}
					} else {
						clearSessionCookies(resp);
						throw new InvalidRequestException(HttpStatus.UNAUTHORIZED, "userSession",
								"You are not authorized to access " + "resource", "SESSION-001");
					}

				}
				if (cookieName.equalsIgnoreCase("_usr_csrf_token")) {
					String csrfToken = createJWT.getDecryptedValue(cookieValue,secretValue);
					if (!csrfToken.equalsIgnoreCase(strCSRFToken)) {
						clearSessionCookies(resp);
						throw new InvalidRequestException(HttpStatus.UNAUTHORIZED, "userSession",
								"You are not authorized to access " + "resource", "SESSION-001");

					}

				}
			}
		}
		if (!jwtToken) {
			clearSessionCookies(resp);
			throw new InvalidRequestException(HttpStatus.UNAUTHORIZED, "userSession",
					"You are not authorized to access " + "resource", "SESSION-001");
		}
	return  userId;
	}

	public UserActivity getSessionDetails(String sessionId) {
		UserActivity userActivity = userActivityRepo.findBySessionId(sessionId);

		return userActivity;
	}

	public void updateSessionDetails(UserActivity userActivity) {

		userActivity.setSessionStartTime(new Timestamp(System.currentTimeMillis()));
		userActivity.setSessionEndTime(new Timestamp(System.currentTimeMillis() + sessionExpireTime * 60 * 1000));
		userActivity.setSessionId(userActivity.getSessionId());
		userActivityRepo.save(userActivity);
	}

	public HttpServletResponse clearSessionCookies(HttpServletResponse resp) {
		// Reset the JWT cookie
		Cookie jwtSessionCookie = new Cookie("_usr_jwt_token", "");
		jwtSessionCookie.setPath("/");
		jwtSessionCookie.setMaxAge(0);
		resp.addCookie(jwtSessionCookie);

		// Reset the Session ID cookie
		Cookie sessionCookie = new Cookie("_usr_csrf_token", "");
		sessionCookie.setMaxAge(0);
		sessionCookie.setPath("/");
		resp.addCookie(sessionCookie);

		Cookie metaCookie = new Cookie("SESSION-META", "");
		sessionCookie.setMaxAge(0);
		metaCookie.setPath("/");
		// metaCookie.setSecure(true);
		resp.addCookie(metaCookie);

		return resp;

	}

	public void clearSessionDetails(int userId) {
		UserActivity userActivity=getUserActivity(userId);
		userActivityRepo.delete(userActivity);
	}

	public UserActivity getUserActivity(int userId){
		UserActivity userActivity=userActivityRepo.findByUserId(userId);
		return userActivity;
	}
}