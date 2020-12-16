package controller_account_handling;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model_BO_service.BO_Account;
import model_beans.Account;
import model_utility.Const;
import model_utility.OTP;
import model_utility.SendMail;

@WebServlet(urlPatterns = "/otp")
public class CheckOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String token = (String) request.getAttribute(Const.TOKEN_REGISTER_OTP);
		// bắt đầu điều hướng
		request.removeAttribute(Const.TOKEN_RESETPASS_OTP);
		if (token != null) {
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/check-otp.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/index");
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account newUser = (Account) session.getAttribute("newUser_register");
		String email = newUser.getEmail();
		
		OTP otp = (OTP) session.getAttribute(Const.KEY_SYSTEM_OTP);

		// // TRANG NÀY CHỈ ĐƯỢC CHUYỂN QUA TỪ TRANG REGISTER
		// lấy token
		String token = (String) request.getAttribute(Const.TOKEN_REGISTER_OTP);
		
		String tokenClient = (String) request.getParameter("TOKENKEY");


		if (token != null |  tokenClient !=null) {
			token = (String) request.getAttribute(Const.TOKEN_REGISTER_OTP);
			request.removeAttribute(Const.TOKEN_REGISTER_OTP);
						SendMail.send(email, otp.getSysOTP());
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/check-otp.jsp");
			dispatcher.forward(request, response);
			return;

			// xử lý dữ liệu khách hàng gửi OTP lên
		} else {
			
			String userOTP = request.getParameter("OTP");		
			if (!otp.checkLiveOTP(LocalDateTime.now())) {
				request.setAttribute("message", "Mã OTP đã hết hiệu lực");
				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/check-otp.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			if (!otp.checkOTP(userOTP)) {
				request.setAttribute("message", "Sai mã OTP");
				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/check-otp.jsp");
				dispatcher.forward(request, response);
				return;

			}


			// ĐÃ XÁC THỰC MAIL THÀNH CÔNG THÊM VÀO DATABASE
			(new BO_Account()).add(newUser);
//
//			// Thêm user này vào session
			session.setAttribute(Const.CUSTOMER_LOGINED, newUser);
			session.removeAttribute(Const.KEY_SYSTEM_OTP);


			request.setAttribute("messageSuccess", "Đăng kí tài khoản thành công");
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/index");
			dispatcher.forward(request, response);
			return;
		}
	}
}
