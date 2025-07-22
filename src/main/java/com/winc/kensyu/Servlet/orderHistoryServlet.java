package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.winc.kensyu.DAO.OrderHistoryDAO;
import com.winc.kensyu.DTO.OrderHistoryDTO;

/**
 * Servlet implementation class orderHistoryServlet
 */
@WebServlet("/orderHistoryServlet")
public class orderHistoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		//ユーザーがログインしていないならlogin.jpに画面遷移
		if (userId == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		//注文の履歴を取得getCompanyDAOlメソッドで注文履歴リストを取得
		OrderHistoryDAO dao = new OrderHistoryDAO();
		List<OrderHistoryDTO> orderList = dao.getOrderHistoryDTO();
		
		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("orderHistory.jsp");
		//ページ遷移
		dispatcher.forward(request, response);
	}

}
