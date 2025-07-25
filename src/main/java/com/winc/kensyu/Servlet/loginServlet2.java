package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	    response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
		
		try {
			conn = DBAccess.getConnection();
			UserDAO dao = new UserDAO();
			String userID = request.getParameter("ID");
			String pass = request.getParameter("pass");
			
			if(!userID.contains("@")) {
				response.setStatus(400);
			}
			else {
				UserDTO dto = dao.getUSERDTO(conn, userID);
				
				if(dto == null) {
					response.setStatus(404);
				}
			
				else if(!dto.getUserPass().equals(pass)) {
					response.setStatus(403);
				}
				else {
					ObjectMapper objectMapper = new ObjectMapper();
					String Json = objectMapper.writeValueAsString(dto);
				
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print(Json);
					out.flush();
				
				}
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
	
}