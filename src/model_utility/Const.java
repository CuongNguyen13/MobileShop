package model_utility;

public class Const {
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String CUSTOMER_ROLE = "CUSTOMER";
	public static final String ACCOUNT_ENABLE = "ENABLE";
	public static final String ACCONT_DISABLE = "DISABLE";

	// TOKEN DÙNG ĐỂ XÁC THỰC SERVLET NÀY GỬI DỮ LIỆU QUA SERVLET KIA
	// TRÁNH TRƯỜNG HỢP VÀO BẰNG URL
	public static final String TOKEN_REGISTER_OTP = "TOKEN_KEY";
	public static final String TOKEN_RESETPASS_OTP = "TOKEN_KEY";
	public static final String TOKEN_OTP_RETYPE_PASS = "TOKEN_KEY";
	public static final String TOKEN_CART_TO_PAY = "KEY_CartToPayment";
	public static final String EMAIL_FORGOT_PASS = "CUSTOMER_EMAIL";
	// link cần truy cập nhưng bị khóa, lưu lại để đăng nhập đúng vẫn vào trang đó
	public static final String CURRENT_LINK = "CurrentURL";

	// giá trị xác nhận người dùng đã đăng nhập
	public static final String CUSTOMER_LOGINED = "CUSTOMER_LOGINED_KEY";
	public static final String ADMIN_LOGINED = "ADMIN_LOGINED";
	
	

}