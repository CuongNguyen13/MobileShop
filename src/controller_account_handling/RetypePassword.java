package controller_account_handling;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model_BO_service.BO_Account;
import model_DAO.DAO_Account;
import model_utility.Const;
import model_utility.Encrypt;

@WebServlet(urlPatterns = "/retype")
public class RetypePassword extends HttpServlet {

	

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TRANG NÀY CHỈ ĐƯỢC CHUYỂN QUA TỪ TRANG CHECK OTP
		// thứ tự : nhập mail ==> nhập password ==> gõ lại password
		// KHÔNG THỂ TRUY CẬP BẤT KỲ NƠI NÀO KHÁC
		// xử lý khi trang RESET PASS chuyển qua đây
	String	token = (String) request.getAttribute(Const.TOKEN_OTP_RETYPE_PASS);
		if (token != null) {
			request.removeAttribute(Const.TOKEN_OTP_RETYPE_PASS);
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/retype-pass.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/index");
		}
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// kiểm tra có phải trang OTP chuyển qua không
		String token = (String) request.getAttribute(Const.TOKEN_OTP_RETYPE_PASS);
		if (token != null) {
			request.removeAttribute(Const.TOKEN_OTP_RETYPE_PASS);
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/retype-pass.jsp");
			dispatcher.forward(request, response);
			return;

		} else {
			// xử lý đổi mật khẩu mật khẩu
			String password = request.getParameter("password");
			
			HttpSession session = request.getSession();
			String userEmail = (String) session.getAttribute(Const.EMAIL_FORGOT_PASS);

			
			if (BO_Account.getBoAccount().isExist(userEmail)) {
				BO_Account.getBoAccount().changePassword(userEmail, password);
			}

			request.setAttribute("message", "Đổi mật khẩu thành công, vui lòng đăng nhập lại");

			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
