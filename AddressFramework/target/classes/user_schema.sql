create schema user_schema;
CREATE TABLE user_reg
(
	user_id 		        int NOT NULL AUTO_INCREMENT,
    device_type 	        varchar(250)  DEFAULT  NULL,
    device_value	        varchar(250)  DEFAULT  NULL,
    role 			        varchar(45) DEFAULT NULL,
    status 			        varchar(250) DEFAULT NULL,
    registration 	        timestamp  DEFAULT NULL,
    security_question_1 	varchar(250) DEFAULT NULL,
    security_answer_1 		varchar(250) DEFAULT NULL,
    security_question_2 	varchar(250) DEFAULT NULL,
    security_answer_2 		varchar(250) DEFAULT NULL,
    PRIMARY KEY (user_id)
);
CREATE TABLE otp_details
(
	id 				int NOT NULL AUTO_INCREMENT,
	otp 			int DEFAULT NULL,
	otp_req_time	timestamp  DEFAULT NULL,
    device_type 	varchar(250)  DEFAULT NULL,
    device_value	varchar(250)  DEFAULT NULL,
	invalid_attempt int DEFAULT NULL,
	user_id 		int NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE user_activity
(
	activity_id 		int NOT NULL AUTO_INCREMENT,
	user_id 			int NOT NULL,
	secret 				int DEFAULT NULL,
	session_start_time 	timestamp DEFAULT NULL,
    session_end_time 	timestamp DEFAULT NULL,
    session_id 			varchar(250) DEFAULT NULL,
	PRIMARY KEY (activity_id)
);

CREATE TABLE audit_log
(
	audit_id 			bigint NOT NULL AUTO_INCREMENT,
	event_name			varchar(250)  DEFAULT NULL,
	event_type			varchar(250)  DEFAULT NULL,
	event_data			varchar(250)  DEFAULT NULL,
	event_data_value	varchar(250)  DEFAULT NULL,
	event_time 			timestamp DEFAULT NULL,
    event_description 	varchar(250) DEFAULT NULL,
	PRIMARY KEY (audit_id)
);

CREATE TABLE address
(
	address_id 			bigint NOT NULL AUTO_INCREMENT,
	user_id 			int NOT NULL,
	first_name			varchar(250)  DEFAULT NULL,
	last_name			varchar(250)  DEFAULT NULL,
	address_type		varchar(250)  DEFAULT NULL,
	self_address 		int DEFAULT NULL,
	address1			varchar(250)  DEFAULT NULL,
	address2			varchar(250)  DEFAULT NULL,
	address3			varchar(250)  DEFAULT NULL,
	state				varchar(250)  DEFAULT NULL,
	state_code			varchar(250)  DEFAULT NULL,
	city				varchar(250)  DEFAULT NULL,
	zipcode				varchar(250)  DEFAULT NULL,

	PRIMARY KEY (address_id)
);

select * from otp_details;
select * from audit_log;
select * from user_reg;
select * from user_activity;
select * from address;