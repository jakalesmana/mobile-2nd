package com.dyned.generalenglish1.util;

public class URLAddress {

	public static final String DEVELOPMENT_BASE_URL = "http://mdc.pistarlabs.net/0.1.0/index.php";
//	public static final String DYNED_DOMAIN = "https://mobile.dyned.com/index.php";
	public static final String CMS_URL = "http://cms.pistarlabs.net/index.php";
	
	public static final String API_KEY = "c31b32364ce19ca8fcd150a417ecce58";
	public static final String URL_LOGIN = DEVELOPMENT_BASE_URL + "/api/oauth/login";
	public static final String URL_CONVERSATION_UPDATE = DEVELOPMENT_BASE_URL + "/api/2/conversation/update";
	public static final String URL_CONVERSATION_HISTORY = DEVELOPMENT_BASE_URL + "/api/2/conversation/history";
	
	public static final String REGISTER_URL = DEVELOPMENT_BASE_URL + "/api/oauth/register";
	public static final String UPDATE_PROFILE_URL = DEVELOPMENT_BASE_URL + "/api/account/update_profile";
	public static final String UPDATE_PROFILE_AVATAR = DEVELOPMENT_BASE_URL + "/api/account/update_picture";
	public static final String COUNTRY_URL = DEVELOPMENT_BASE_URL + "/api/helper/country_list";
	public static final String LANGUAGE_URL = DEVELOPMENT_BASE_URL + "/api/helper/language_list";
	public static final String PROFILE_URL = DEVELOPMENT_BASE_URL + "/api/account/profile";
	public static final String SUPPORT_URL = DEVELOPMENT_BASE_URL + "/contact?mode=webview";
	public static final String ACCOUNT_URL = DEVELOPMENT_BASE_URL + "/account";
	public static final String STUDY_TIPS_URL = CMS_URL + "/api/study_tips";
	public static final String APPLICATION_URL = CMS_URL + "/api/applications/os";
	public static final String NOTIFICATION_URL = DEVELOPMENT_BASE_URL + "/api/notification";
	public static final String URL_LOGOUT = DEVELOPMENT_BASE_URL + "/api/oauth/logout";
	public static final String URL_FORGOT_PASSWORD = DEVELOPMENT_BASE_URL + "/api/oauth/forgot_password";	
	public static final String SET_DEVICE_TOKEN = DEVELOPMENT_BASE_URL + "/api/2/device/android_add_token";
	public static final String URL_CHANGE_PASSWORD = DEVELOPMENT_BASE_URL + "/api/account/update_password";	
}