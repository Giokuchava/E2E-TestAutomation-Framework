package api;

/**
 * Contains all API endpoint paths
 */
public class APIEndpoints {

    // Product Endpoints
    public static final String GET_ALL_PRODUCTS = "/productsList";
    public static final String POST_ALL_PRODUCTS = "/productsList";
    public static final String SEARCH_PRODUCT = "/searchProduct";

    // Brand Endpoints
    public static final String GET_ALL_BRANDS = "/brandsList";
    public static final String PUT_ALL_BRANDS = "/brandsList";

    // User Account Endpoints
    public static final String CREATE_ACCOUNT = "/createAccount";
    public static final String VERIFY_LOGIN = "/verifyLogin";
    public static final String DELETE_ACCOUNT = "/deleteAccount";
    public static final String UPDATE_ACCOUNT = "/updateAccount";
    public static final String GET_USER_DETAIL_BY_EMAIL = "/getUserDetailByEmail";

    private APIEndpoints() {
        // Private constructor to prevent instantiation
    }
}