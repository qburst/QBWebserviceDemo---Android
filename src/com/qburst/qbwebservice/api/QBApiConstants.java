package com.qburst.qbwebservice.api;

public interface QBApiConstants {
	public static final String kNetworkExceptionKey = "NETWORK_EXCEPTION";
	public static final String kTimeOutExceptionKey = "TIMEOUT_EXCEPTION";

	// Request Time out param
	public static final int kRequestTimeOutInMills = 20000;

	// Api ID's
	public static final int kLoginWebServiceId = 0;
	public static final int kCategoryWebServiceId = 1;
	public static final int kSubCategoryWebServiceId = 2;
	public static final int kProductsWebServiceId = 3;
	public static final int kProductsDetailsWebServiceId = 4;
	public static final int kSearchWebServiceId = 5;
	public static final int kRegisterWebServiceId = 6;
	public static final int kProductGalleryServiceId = 8;
	public static final int kReviewWebServiceId = 7;

	// API Prefixes
	public static final String kBaseURL = "http://10.1.3.99:8100/mcommercewebservices";

	// API PostFixes;
	public static final String kSubCategoriesURL = "/subcategories/";
	public static final String kCategoriesURL = "/categories/";
	public static final String kProductsURL = "/products/";
	public static final String kSearchURL = "/search/";
	public static final String kLoginURL = "/login/";
	public static final String kProductDetailURL = "/product_detail/";
	public static final String kRegisterURL = "/register/";
	public static final String kMainCategoriesURL = "/homescreendata/";
	public static final String kGalleryURL = "/productgallery/";
	public static final String kReviewURL = "/reviews/";

	// HTTP Status Code Constants
	public static final String kHttpStatusKey = "HttpStatus";
	public static final int kHttpStatusOK = 200;
	public static final int kHttpStatusCreated = 201;
	public static final String kAuthHeaderKey = "Authorization";

	// Base Web Service
	public static final String kApiSuccessMsgKey = "API_SUCCESS";
	public static final String kApiFailMsgKey = "API_FAIL";
	public static final String kResponseBeanKey = "Response Bean";
	public static final String kTimeoutError = "Server_Error";
	public static final String kNetworkError = "Network_Error";

}
