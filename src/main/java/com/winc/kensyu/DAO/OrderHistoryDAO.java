package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.winc.kensyu.DTO.OrderHistoryDTO;
import com.winc.kensyu.DTO.UserDTO;

public class OrderHistoryDAO {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	public List<OrderHistoryDTO> getOrderHistoryDTO(Connection conn) {
        List<OrderHistoryDTO> list = new ArrayList<>();
        String sql = "SELECT order_code, user_id, order_date, order_count FROM ORDER_HISTORY_TABLE where user_id = ?";

        try {
        	 OrderHistoryDTO dto = new OrderHistoryDTO();
        	 UserDTO Udto = new UserDTO();
        	 
        	 stmt = conn.prepareStatement(sql);
        	 
        	 stmt.setString(1,(Udto.getUserId()));
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	dto.setOrderCode(rs.getString("order_code"));
                dto.setUserId(rs.getString("user_id"));
                dto.setOrderDate(rs.getDate("order_date"));
                dto.setOrderCount(rs.getInt("order_count"));
                list.add(dto);
            }
             

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
	
	
	public void setOrderHistoryDTO(Connection conn, OrderHistoryDTO orderDTO) {
		String sql = "INSERT INTO ORDER_HISTORY_TABLE (ORDER_CODE, USER_ID, ORDER_DATE, ORDER_COUNT) VALUES(?, ?, CURRENT_TIMESTAMP,?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderDTO.getOrderCode());
			stmt.setString(2, orderDTO.getUserId());
			stmt.setInt(3, orderDTO.getOrderCount());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException e) {
				System.out.println("objectのclose時に例外が発生");
				e.printStackTrace();
			}
		}
		
	}
}
