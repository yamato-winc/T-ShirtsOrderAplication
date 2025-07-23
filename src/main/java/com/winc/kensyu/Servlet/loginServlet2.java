package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	    HttpSession session = request.getSession();
		
		try {
		
			conn = DBAccess.getConnection();
			UserDAO dao = new UserDAO();
			String userID = request.getParameter("ID");
			String pass = request.getParameter("pass");
			System.out.println(pass);
		
			UserDTO dto = dao.getUSERDTO(conn, userID, pass);
			if(dto == null) {
//				System.out.println("ここ北代");
				response.sendRedirect("T-ShirtsOrder.jsp");
			}else {
//				System.out.println("nullではなかった");
				
				Map<String, String> map = getUserInfo(dto);
				ObjectMapper objectMapper = new ObjectMapper();
				String Json = objectMapper.writeValueAsString(map);
				
				session.setAttribute("userJson", Json);
				
//				System.out.println(Json);
				response.sendRedirect("T-ShirtsOrder.jsp");
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
	
//	private String convertToJson(UserDTO dto) {
//		StringBuilder json = new StringBuilder();
//		json.append("{");
//		json.append("\"User_Company\":").append(dto.getUserCompany()).append(",");
//		json.append("\"User_Name\":").append(dto.getUserName());
//		json.append("}");
//		
//		return json.toString();
//		
//	}
	
	private Map<String, String> getUserInfo(UserDTO dto) {
		Map<String, String> map = new HashMap<>();
		map.put("User_Company", dto.getUserCompany());
		map.put("User_Name", dto.getUserName());
		System.out.print(map);
		return map;
	}


}