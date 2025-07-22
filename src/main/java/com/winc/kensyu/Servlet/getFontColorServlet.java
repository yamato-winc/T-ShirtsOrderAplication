package com.winc.kensyu.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public Map<Integer, String> getFontColorServlet() {
        // TODO Auto-generated constructor stub
        
        FontColorDAO dao = new FontColorDAO();
        List<FontColorDTO> listdata = dao.getFontColorDTO();
        Map<Integer,String> map = new HashMap<>();
        
        for(int i = 0; i<listdata.size(); i++) {
        	int fontColorId = listdata.get(i).getFontColorId();
        	int r = listdata.get(i).getFontColorR(); 
        	int g = listdata.get(i).getFontColorG();
        	int b = listdata.get(i).getFontColorB();
        	String RGB =  r + "," + g + "," + b;
        	
        	map.put(fontColorId, RGB);
        	
        }
        
        return map;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
