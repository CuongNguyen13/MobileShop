package controller_admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model_DAO.DAO_AddProduct;

@WebServlet(urlPatterns = "/admin/product-add")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO_AddProduct dao = new DAO_AddProduct();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/admin/admin-product-add.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idProduct = dao.createIdProduct();
		String name = request.getParameter("nameProduct");
		String price = request.getParameter("price");
		String priceSale = request.getParameter("priceSale");
		String brand = request.getParameter("brand");
		String quatity = request.getParameter("quatity");
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		String topic = request.getParameter("topic");
		String countColor = request.getParameter("countColor");
		dao.addProduct(idProduct, name, type, dao.idBrand(brand), state, topic, quatity, "CH01");
		dao.addPrice(idProduct, price, priceSale);

		
		for (int i = 1; i <= Integer.parseInt(countColor); i++) {
			//lấy mã màu từ jsp
			String idColor = dao.createIdColor();
			String code = request.getParameter("codeColor" + i);
			String nameColor = request.getParameter("color" + i);
			if (!nameColor.equals("")) {
				dao.addColor(idColor, code, nameColor);
				//lấy hình ảnh từ jsp
				String imgMain = request.getParameter("anhnen" + i);
				String imgEx1 = request.getParameter("anhMT1" + i);
				String imgEx2 = request.getParameter("anhMT2" + i);
				String imgEx3 = request.getParameter("anhMT3" + i);
				String imgEx4 = request.getParameter("anhMT4" + i);
				dao.addImg(idProduct, idColor, imgMain, "NEN");
				dao.addImg(idProduct, idColor, imgEx1, "PHU");
				dao.addImg(idProduct, idColor, imgEx2, "PHU");
				dao.addImg(idProduct, idColor, imgEx3, "PHU");
				dao.addImg(idProduct, idColor, imgEx4, "PHU");
			}
		}


		doGet(request, response);
	}
}
