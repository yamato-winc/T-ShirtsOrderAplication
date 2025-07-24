package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winc.kensyu.DAO.FontColorDAO;
import com.winc.kensyu.DTO.FontColorDTO;
/**
 * Servlet implementation class getFontColorServlet
 */
@WebServlet("/getFontColorServlet")
public class getFontColorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public List<FontColorDTO> getfontColorServlet() {
        // TODO Auto-generated constructor stub
        
        FontColorDAO dao = new FontColorDAO();
        List<FontColorDTO> listdata = dao.getFontColorDTO();
        return listdata;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<FontColorDTO> fontColorlist = getfontColorServlet();
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(fontColorlist);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
	}

}
