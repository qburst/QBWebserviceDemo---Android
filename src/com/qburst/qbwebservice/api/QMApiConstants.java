/*
 * Package Name : com.mcommerce.android.app
 * Author : Qburst
 * Copyright : Qburst @ 2010-2011
 * Description : A set of API specific constants that will be used through by the web service classes and their callback implementations.
 */

package com.qburst.qbwebservice.api;

public interface QMApiConstants {
	public static final String kErrorMsgKey = "Error";
	public static final String kExceptionKey = "EXCEPTION";
	public static final String kNetworkExceptionKey = "NETWORK_EXCEPTION";
	public static final String kTimeOutExceptionKey = "TIMEOUT_EXCEPTION";
	
	//Request Time out param
	public static final int kRequestTimeOutInMills = 20000;

	// Api ID's
	public static final int kLoginWebServiceId = 0;
	public static final int kCategoryWebServiceId = 1;
	public static final int kSubCategoryWebServiceId = 2;
	public static final int kProductsWebServiceId = 3;
	public static final int kProductsDetailsWebServiceId = 4;
	public static final int kSearchWebServiceId = 5;
	public static final int kRegisterWebServiceId = 6;
	public static final int kProductGalleryServiceId=8;
	public static final int kReviewWebServiceId=7;
	
	// API Prefixes
    public static final String kBaseURL = "http://10.1.3.99:8100/mcommercewebservices"; 
//	public static     final String kBaseURL =  "http://djangowebservices.qburst.com/mcommercewebservices";
//	public static final String kBaseURL ="http://djangowebservices.qburst.com/cardecorwebservices";
    // API PostFixes;
	public static final String kSubCategoriesURL = "/subcategories/";
	public static final String kCategoriesURL = "/categories/";
	public static final String kProductsURL = "/products/";
	public static final String kSearchURL = "/search/";
	public static final String kLoginURL = "/login/";
	public static final String kProductDetailURL = "/product_detail/";
	public static final String kRegisterURL = "/register/";
	public static final String kMainCategoriesURL = "/homescreendata/";
	public static final String kGalleryURL="/productgallery/";
	public static final String kReviewURL="/reviews/";

	// HTTP Status Code Constants
	public static final String kHttpStatusKey = "HttpStatus";
	public static final int kHttpStatusOK = 200;
	public static final int kHttpStatusCreated = 201;
	public static final int kHttpStatusForbidden = 401;
	public static final int kHttpStatusBadRequest = 400;

	public static final String kStatusOK = "OK";
	public static final String kStatusError = "FAILED";
	public static final String kStatusSystemError = "SYSTEM_ERROR";
	public static final String kStatusKey = "responseCode";
	public static final String kAuthHeaderKey = "Authorization";
	
	//Base Web Service
	public static final String kApiSuccessMsgKey = "API_SUCCESS";
	public static final String kApiFailMsgKey = "API_FAIL";
	public static final String kResponseBeanKey = "Response Bean";
	public static final String kApiFailMsg = "API_Fail";
	public static final String kTimeoutError = "Server_Error";
	public static final String kNetworkError = "Network_Error";

	
	// JSON Request Constants
	public static final String kJSONEmailKey = "email";
	public static final String kJSONPasswordKey = "password";
	public static final String kJSONcategoryIdKey = "categoryId";
	public static final String kJSONSubCategoryIdKey = "subcategoryId";
	public static final String kJSONFirstName = "firstname";
	public static final String kJSONLastName = "lastname";
	public static final String kJSONProductIdKey = "productId";
	
	//JSON Response Constants
	//Login
	public static final String kStatusRespKey = "status";
	public static final String kMessageRespKey = "message";
	
	//Categories
	public static final String kCategoryRespKey = "Parent Categories";
	public static final String kCategoryBannerIconRespKey = "icon";
	public static final String kCategoryHomeIconRespKey = "main_image";
	public static final String kCategoryScreenAddressKey = "screenAdress";
	public static final String kCategoryIdRespKey = "id";
	public static final String kCategoryNameRespKey = "name";
	public static final String kCategoryScreenAddressIdRespKey = "id";
	public static final String kCategoryScreenAddressTargetRespKey = "target";

	
	//Subcategories
	public static final String kSubcategoriesRespKey = "subcategories";
	public static final String kMainImageRespKey = "main_image";
	public static final String kIconRespKey = "icon";
	public static final String kIdRespKey = "id";
	public static final String kNameRespKey = "name";
	public static final String kDescriptionRespKey = "description";
	
	//Products
	public static final String kProductsRespKey = "products";
	public static final String kProductsIsInBasketRespKey = "isInBasket";
	public static final String kProductsIsFavouriteRespKey = "isFavourite";
	public static final String kProductsPriceRespKey = "price";
	
	//Search
	public static final String kSearchKey = "query";
	public static final String kScreenAddress = "screenAdress";
	
}
