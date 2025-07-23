package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winc.kensyu.DAO.DBAccess;
import com.winc.kensyu.DAO.UserDAO;
import com.winc.kensyu.DTO.UserDTO;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet2")
public class loginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		
		try {
			
			conn = DBAccess.getConnection();
			UserDAO dao = new UserDAO();
			String userID = request.getParameter("ID");
			String pass = request.getParameter("pass");
		
			UserDTO dto = dao.getUSERDTO(conn, userID);
		
			if(dto == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("../T-ShirtsOrder");
				dispatcher.forward(request, response);
			}
			
		
		
			if(pass.equals(dto.getUserPass())) {
				String Json = convertToJson(dto);
				request.setAttribute("userJson", Json);
				RequestDispatcher dispatcher = request.getRequestDispatcher("../T-ShirtsOrder");
				dispatcher.forward(request, response);
			}
			
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
	
	private String convertToJson(UserDTO dto) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\"User_Company\":").append(dto.getUserCompany()).append(",");
		json.append("\"User_Name\":").append(dto.getUserName());
		json.append("}");
		
		return json.toString();
		
	}
	
	
	

}