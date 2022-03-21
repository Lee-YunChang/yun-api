package com.yunapi.util;

public class MessageUtils {
	public final static String SUCCESS = "success";
	public final static String FAIL = "fail";
	
	public final static String INVALID_ADMIN_API_KEY = "유효하지 않은 관리자 api-key 입니다";
	public final static String INVALID_ADMIN_USERNAME = "존재하지 않는 관리자 id(username) 입니다.";
	public final static String INVALID_USER_ID = "존재하지 않는 bankq_user 입니다.";
	public final static String INVALID_USER = "일치하는 user가 없습니다. (s-Id 또는 s-token 오류)";
	public final static String INVALID_MYDATA_USER_ID = "존재하지 않는 mydata_user 입니다.";
	public final static String INVALID_BT_ID = "존재하지 않는 소비분류 id(btId) 입니다.";
	public final static String INVALID_ACCOUNT_TYPE = "유효하지 않는 ACCOUNT_TYPE입니다.";
	public final static String INVALID_CONSENT_DATA = "유효하지 않은 consent_date 입니다.";
	public final static String INVALID_CONSUMPTION_HISTORY_ID = "조회되는 소비 내역 id가 없습니다.";
	public final static String DUPLICATE_MYDATA_USER = "이미 가입된 mydata_user 입니다.";
	public final static String UNREG_FUNDCARE_USER ="펀드케어 서비스에 가입하지 않은 사용자입니다. 가입 후 이용해주세요.";
	
	public final static String EMPTY_USER_OAUTH = "user_oauth 값이 없습니다.";
	public final static String EMPTY_ORG_CODE = "org_code 값이 없습니다.";
	public final static String EMPTY_GRANT_TYPE = "grant_type 값이 없습니다.";
	public final static String EMPTY_CONSENT_VERSION = "consent_version 값이 없습니다.";
	public final static String EMPTY_UUID = "uuid 값이 없습니다.";
	public final static String EMPTY_REPORT = "조회되는 리포트가 없습니다.";
	public final static String EMPTY_FUNDCARE_REPORT = "연관된 펀드케어 리포트가 없습니다.";
	public final static String EMPTY_CONSUMPTION_REPORT = "연관된 소비케어 리포트가 없습니다.";

	public final static String DUPLICATE_USER_ACCOUNT = "이미 가입한 기록이 있습니다.";					// Q001
	public final static String INCORRECT_PASSWORD = "비밀번호가 일치하지 않습니다.";						// Q002
	public final static String UNREG_USER ="가입하지 않은 사용자입니다. 가입 후 이용해주세요.";				// Q003
	public final static String MAIL_DUPLICATE = "중복되는 메일이 있습니다.";							// Q004
	public final static String INCORRECT_CI = "본인 정보가 일치하지 않습니다. 입력한 내용을 다시 확인하세요";	// Q005
	public final static String INCORRECT_USERID_OR_PASSWORD = "아이디 또는 비밀번호가 일치하지 않습니다.";
	public final static String INCORRECT_EMAIL_CODE ="incorrect_email";
	public final static String UNMATCHED_UUID ="다른 기기에 로그인 된 사용자입니다.";
	public final static String INCORRECT_SID = "user_id와 s-Id가 일치하지 않습니다.";
	public final static String INCORRECT_TOKENKEY = "token_key가 일치하지 않습니다.";
	public final static String PASSWORD_ERROR = "패스워드 형식이 맞지 않습니다.";
	
	public final static long DEFAULT_BT_ID = 32L;
	public final static String DEFAULT_BT_TYPE2 = "미분류";
	
	public final static String FUNDCARE_QTY_REPORT_TITLE_FIRST = "%d %d일 평가정보 알림"; // "%s 평가정보 알림";
	public final static String DB_ERROR_CONSENT_VERSION ="전송요구서 버전 정보가 잘못되었습니다.";
	public final static String DB_ERROR_CONSENT_DATA_3 ="전송요구서3 정보가 잘못되었습니다.";
	public final static String DB_ERROR_CONSENT_DATA_4 ="전송요구서4 정보가 잘못되었습니다.";
	public final static String DB_ERROR_CONSENT_DATA_5 ="전송요구서5 정보가 잘못되었습니다.";

}
