package addressframework.session.security;

import org.springframework.stereotype.Component;


@Component
public class CreateJWT {

    public String getEncryptedValue(String value, int secret) {
        String encrypt = "";
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            ch += secret;
            encrypt = encrypt + ch;
        }
        return encrypt;
    }

    public String getDecryptedValue(String value, int secret) {
        String decrypted = "";
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            ch -= secret;
            decrypted = decrypted + ch;
        }
        return decrypted;
    }

}