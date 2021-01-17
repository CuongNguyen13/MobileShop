package controller_system;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model_BO_service.BO_Product;
import model_DAO.DAO_Product_main;
import model_beans.Cart;
import model_beans.Product_form;
import model_beans.Product_main;
import model_utility.Config;

@WebServlet(urlPatterns = "/cart")
public class Controller_Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private static DAO_Product_main dao = DAO_Product_main.getDao_Product_main();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		request.setAttribute("message", request.getAttribute("message"));
		request.setAttribute("LIST_INSTANCE_PRODUCT", getListInstanceProductInCart(cart));
		request.setAttribute("TOTAL_MONEY", getTotalMoney(cart));
		dispatcher = this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/system/cart.jsp");
		dispatcher.forward(request, response);
		return;
//		// display Cart
//		int sum = 0;
//		int quantity = cart.getQuantityOfProductInCart();
//		Map<Product_form, Integer> lst = cart.getName_Quantity();
//		Map<Product_form, Integer> map = null;
//		map = cart.getList();
//		sum = cart.getReceiptSum();
//
//		// TODO Auto-generated catch block
//		request.setAttribute("lst", lst);
//		request.setAttribute("sum", sum);
//		request.setAttribute("quantity", quantity);
//		request.setAttribute("map", map);
//		request.setAttribute("SHOW_LIST_CART", map.size());

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productID = request.getParameter("id").trim();
		String choose = request.getParameter("choose");
		String page = request.getParameter("page");
		String datHang = request.getParameter("datHang");
		String colorID = request.getParameter("colorID").trim();

		System.out.println( "/page : " + page + " /chon : " + choose + "/ id  : " + productID
				+ " /màu :" + colorID);

		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("CART");

		switch (choose) {
		case "add":
			String message = null;
			try {
				switch (cart.add(productID, colorID)) {
				case 1:
					message = dao.getProductNameFormID(productID)+" chỉ được mua tối đa " + Config.MAX_QUANTITY_OF_PRODUCT+" sản phẩm " ;
					break;
				case 2:
					message = "tối đa " + Config.MAX_PRODUCT + " mẫu điện thoại trong giỏ hàng";
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (datHang != null) {
				updateCart(cart, session);
				response.sendRedirect(request.getContextPath() + "/cart");
				return;
			}

			if (page != null) {
				updateCart(cart, session);
				request.setAttribute("message", message);
				doGet(request, response);
				return;
			}

			updateCart(cart, session);
			request.setAttribute("message", message);
			// cập nhật lượt xem
				ProductDetail.updateProductView(productID, request);

				Product_main pm = DAO_Product_main.getDao_Product_main().getProduct_main(productID);
				request.setAttribute("product", pm);
			dispatcher = this.getServletContext().getRequestDispatcher("/VIEW/jsp/jsp-page/system/detail-product.jsp");
			dispatcher.forward(request, response);

			break;
		case "decrease":
			try {
				cart.removeProductItem(productID, colorID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateCart(cart, session);
			response.sendRedirect(request.getContextPath() + "/cart");
			break;
		case "remove":
			try {
				cart.removeAllProductSameColor(productID, colorID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateCart(cart, session);
			response.sendRedirect(request.getContextPath() + "/cart");
			break;

		case "remove-all":
			cart.removeAll();
			updateCart(cart, session);
			response.sendRedirect(request.getContextPath() + "/cart");
			break;
		default:
			break;
		}

	}

	private void updateCart(Cart cart, HttpSession session) {
		session.setAttribute("CART", cart);
		session.setAttribute("QUANTITY_INSTANCE_PRODUCT", quantityCart(cart));

	}

	private int quantityCart(Cart cart) {
		int quantity = 0;
		for (String ID : cart.getListProductID()) {
			for (String colorID : cart.getListProduct().get(ID).keySet()) {
				quantity += cart.getListProduct().get(ID).get(colorID);
			}
		}
		return quantity;

	}

	public static int getTotalMoney(Cart cart) {

		int total = 0;
		for (String ID : cart.getListProductID()) {
			for (String colorID : cart.getListProduct().get(ID).keySet()) {
				total += dao.getProduct_main(ID).getPrices().getPrice() * cart.getListProduct().get(ID).get(colorID);
			}

		}
		return total;
	}

	public static ArrayList<Product_form> getListInstanceProductInCart(Cart cart) {
		ArrayList<Product_form> listProduct = new ArrayList<Product_form>();
		for (String ID : cart.getListProductID()) {
			for (String colorID : cart.getListProduct().get(ID).keySet()) {
				Product_main product = dao.getProduct_main(ID);
				String color = dao.getColorName(colorID);
				Product_form product_form = new Product_form();

				product_form.setURL(dao.convertBetweenURLandID(ID));
				product_form.setId(product.getID());
				product_form.setName(product.getName());
				product_form.setNameBranch(product.getBranch().getName());
				product_form.setPrice(product.getPrices().getPrice());
				product_form.setPriceSales(product.getPrices().getPriceSales());
				product_form.setImg(dao.getURLthumbnail(ID, colorID));
				product_form.setColorID(colorID);
				product_form.setColor(color);
				product_form.setQuantityInCart(cart.getListProduct().get(ID).get(colorID));

				listProduct.add(product_form);
			}
		}
		return listProduct;
	}

	// kiểm tra đã đăng nhập hay chưa
//	HttpSession session = request.getSession();
//
//	if (session.getAttribute(Const.CUSTOMER_LOGINED) == null) {
//		for (Cookie c : request.getCookies()) {
//			String name = c.getName();
//			String value = c.getValue();
//			if (name.equals(productID) && checkInt(value)) {
//				int quantity = Integer.parseInt(value) + 1;
//				Cookie newCookie = new Cookie(productID, quantity + "");
//				newCookie.setMaxAge(maxAgeCookie);
//				response.addCookie(newCookie);
//			} else {
//				Cookie newCookie = new Cookie(productID, "1");
//				newCookie.setMaxAge(maxAgeCookie);
//				response.addCookie(newCookie);
//			}
//		}
//	} else {
//		String email = ((Customer) session.getAttribute(Const.CUSTOMER_LOGINED)).getEmail();
//		BO_Cart bo = new BO_Cart(email);
//
//		switch (choose) {
//		case "add":
//			bo.addProduct(productID);
//			break;
//		case "increase":
//			bo.changeQuantity(productID, bo.currentQuantity(productID) + 1);
//			break;
//		case "decrease":
//			bo.changeQuantity(productID, bo.currentQuantity(productID) - 1);
//			break;
//		case "deleteProduct":
//			bo.deleteProduct(productID);
//			break;
//		case "deleteCart":
//			bo.deleteCart();
//			break;
//
//		default:
//			break;
//		}
//
//	}

//	public static HashMap<String, Integer> loadCartFromCookie(HttpServletRequest request) {
//		HashMap<String, Integer> list = new HashMap<String, Integer>();
//		for (Cookie c : request.getCookies()) {
//			String name = c.getName();
//			String value = c.getValue();
//			if (name.startsWith(prefixProductID) && checkInt(value)) {
//				list.put(name, Integer.parseInt(value));
//			}
//		}
//		return list;
//
//	}

//	private static boolean checkInt(String input) {
//		try {
//			Integer.parseInt(input);
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}

}
