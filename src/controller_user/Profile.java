package controller_user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model_BO_service.BO_Account;
import model_beans.Customer;
import model_utility.Const;
import model_utility.EncryptPassword;

@WebServlet(urlPatterns = "/member/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/profile.jsp");

		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		Customer acc = (Customer) session.getAttribute(Const.CUSTOMER_LOGINED);
		acc.setAddress(address);
		acc.setName(name);
		acc.setPhoneNumber(phoneNumber);
		if (password != "") {
			acc.setPassword(EncryptPassword.md5(password));
		}

		(new BO_Account()).update(acc);

		request.setAttribute("message", "Đã cập nhập thành công");
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/account/profile.jsp");
		dispatcher.forward(request, response);

	}

}