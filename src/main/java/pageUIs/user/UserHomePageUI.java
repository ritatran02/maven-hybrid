package pageUIs.user;

public class UserHomePageUI {
    // public: gọi hàm/ biến ra sd bt
    // private/ default: khác package k dùng được
    // protected: các class bên PO k kế thừa PUI nên k áp dụng
    // static: cho phép gọi trực tiếp từ class
    // final: ngăn việc update lại các giá trị trong quá trình chạy
    // String: Vì cai By locator của Selenium đều nhận vào String
    // REGISTER_LINK: static final để quy ước 1 biến là HẰNG SỐ dùng trong JAVA
    // Convention cho hằng số: phải viết hoa - nhiều hơn 1 từ thì phải dùng dấu _ để phân tách

    public static final String REGISTER_LINK = "class=ico-register";

    public static final String MY_ACCOUNT_LINK = "class=ico-account";
}
