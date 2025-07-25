package com.winc.kensyu.Servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
    
    private static final Logger logger = Logger.getLogger(orderHistoryServlet.class.getName());

    // ログ設定（C:\logs\change-servlet.log に出力）
    static {
        try {
            File logDir = new File("C:\\logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            FileHandler fh = new FileHandler("C:\\logs\\buy-Servlet.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-gene
		logger.info("doPostメソッドが開始されました。");
		request.setCharacterEncoding("UTF-8");

		OrderHistoryDTO orderDTO = new OrderHistoryDTO();
		DesignDTO designDTO = new DesignDTO();
		String sql = "VALUES NEXT VALUE FOR ORDER_CODE_SEQUENCE";
		String nextOrderCode = null;
		
		try {
			logger.log(Level.INFO, "データベースにアクセスしました。");
			conn = DBAccess.getConnection();
			OrderHistoryDAO orderDAO = new OrderHistoryDAO();
			DesignDAO designDAO = new DesignDAO();
			
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					nextOrderCode = rs.getString(1);
					nextOrderCode = String.format("%07d", Integer.parseInt(nextOrderCode));
					System.out.println(nextOrderCode);
				}
				
			}catch (SQLException e) {
				logger.log(Level.SEVERE, "注文履歴のデータ取得時にエラーが発生しました。", e);
				logger.log(Level.FINE, "エラーが発生しました。");
				e.printStackTrace();
			}finally {
				if(ps != null) {
					ps.close();
				}
			}
			System.out.println("２回目" + nextOrderCode);
			
			String userID = request.getParameter("user_id");
			String baseColor = request.getParameter("base_color");
			
			String text1 = request.getParameter("text1");
			Integer text1Color = Integer.parseInt(request.getParameter("text1_font_color"));
			Integer text1Size = Integer.parseInt(request.getParameter("text1_size"));
			
			String text2 = request.getParameter("text2");
			Integer text2Color = Integer.parseInt(request.getParameter("text2_font_color"));
			Integer text2Size = Integer.parseInt(request.getParameter("text2_size"));
			
			Integer vertical = Integer.parseInt(request.getParameter("vertical_position"));
			Integer side = Integer.parseInt(request.getParameter("side_position"));
			
			Integer orderCount = Integer.parseInt(request.getParameter("order_count"));
			
			//orderHistoryDTOに登録
			orderDTO.setOrderCode(nextOrderCode);
			orderDTO.setUserId(userID);
			orderDTO.setOrderCount(orderCount);
			
			
			//DesignDTOに登録
			designDTO.setOrderCode(nextOrderCode);
			
			designDTO.setBaseColor(baseColor);
			
			designDTO.setText1(text1);
			designDTO.setText1Size(text1Size);
			designDTO.setText1FontColorId(text1Color);
			
			designDTO.setText2(text2);
			designDTO.setText2Size(text2Size);
			designDTO.setText2FontColorId(text2Color);
			
			designDTO.setVerticalPosition(vertical);
			designDTO.setSidePosition(side);
			
			if(designDAO.setDesignDTO(conn, designDTO) && orderDAO.setOrderHistoryDTO(conn, orderDTO)) {
				response.setStatus(200);
			}
			else {
				response.setStatus(500);
			}
			
			
		}catch(Exception e) {
			logger.log(Level.SEVERE, "購入時にエラーが発生しました。", e);
			e.printStackTrace();
			
		}finally {
			try {
				
				if(conn != null) {
					conn.close();
				}
				
			}catch(SQLException e) {
				logger.log(Level.SEVERE, "購入時にエラーが発生しました。", e);
				System.out.println("objectのclose時に例外が発生");
				e.printStackTrace();
				
			}
		}
		
	}

}
