package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winc.kensyu.DAO.DBAccess;
import com.winc.kensyu.DAO.OrderHistoryDAO;
import com.winc.kensyu.DTO.OrderHistoryDTO;

/**
 * Servlet implementation class orderHistoryServlet
 */
@WebServlet("/orderHistoryServlet")
public class orderHistoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
		try {
			Connection con;
			con = DBAccess.getConnection();
			//注文の履歴を取得getCompanyDAOlメソッドで注文履歴リストを取得
			OrderHistoryDAO dao = new OrderHistoryDAO();
			List<OrderHistoryDTO> orderList = dao.getOrderHistoryDTO(con,"Tanaka@example");
			
			request.setAttribute("orderList", orderList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("T-ShirtsOrder.jsp");
			//ページ遷移
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		

	}

}
