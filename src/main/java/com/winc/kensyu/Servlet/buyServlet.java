package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winc.kensyu.DAO.DBAccess;
import com.winc.kensyu.DAO.DesignDAO;
import com.winc.kensyu.DAO.OrderHistoryDAO;
import com.winc.kensyu.DTO.DesignDTO;
import com.winc.kensyu.DTO.OrderHistoryDTO;

/**
 * Servlet implementation class buyServlet
 */
@WebServlet("/buyServlet")
public class buyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement ps = null;
    ResultSet rs = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-gene
		OrderHistoryDTO orderDTO = new OrderHistoryDTO();
		DesignDTO designDTO = new DesignDTO();
		String sql = "VALUES NEXT VALUE FOR order_code_seq";
		String nextOrderCode = null;
		
		try {
			conn = DBAccess.getConnection();
			OrderHistoryDAO orderDAO = new OrderHistoryDAO();
			DesignDAO designDAO = new DesignDAO();
			
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					nextOrderCode = rs.getString(1);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(ps != null) {
					ps.close();
				}
			}
			
			
			String userID = request.getParameter("userID");
			
			String baseColor = request.getParameter("baseColor");
			
			String upperText = request.getParameter("upperText");
			String upperColor = request.getParameter("color-option[data-color]");
			Integer upperSize = Integer.parseInt(request.getParameter("upperSize"));
			
			String lowerText = request.getParameter("lowerText");
			String lowerColor = request.getParameter("");
			Integer lowerSize = Integer.parseInt(request.getParameter("lowerSize"));
			
			Integer textHeight = Integer.parseInt(request.getParameter("textHeight"));
			Integer textWidth = Integer.parseInt(request.getParameter("textWidth"));
			
			Integer orderCount = Integer.parseInt(request.getParameter("orderCount"));
			
			//orderHistoryDTOに登録
			orderDTO.setOrderCode(nextOrderCode);
			orderDTO.setUserId(userID);
			orderDTO.setOrderCount(orderCount);
			
			
			//DesignDTOに登録
			designDTO.setOrderCode(nextOrderCode);
			
			designDTO.setBaseColor(baseColor);
			
			designDTO.setText1(upperText);
			designDTO.setText1Size(upperSize);
			designDTO.setText1FontColorId(upperColor);
			
			designDTO.setText2(lowerText);
			designDTO.setText2Size(lowerSize);
			designDTO.setText2FontColorId(lowerColor);
			
			designDTO.setVerticalPosition(textHeight);
			designDTO.setSidePosition(textWidth);
			
			
			designDAO.setDesignDTO(conn, designDTO);
			orderDAO.setOrderHistoryDTO(conn, orderDTO);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/T-Shirt.jsp");
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			try {
				
				if(conn != null) {
					conn.close();
				}
				
			}catch(SQLException e) {
				
				System.out.println("objectのclose時に例外が発生");
				e.printStackTrace();
				
			}
		}
		
	}

}
