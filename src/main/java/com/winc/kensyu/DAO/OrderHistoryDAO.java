package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.winc.kensyu.DTO.OrderHistoryDTO;

public class OrderHistoryDAO {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	public List<OrderHistoryDTO> getOrderHistoryDTO(Connection conn,String userId) {
        List<OrderHistoryDTO> list = new ArrayList<>();
        String sql = "SELECT orderhis.order_code, user_id, order_date, order_count, base_color,text1,text1_size,text1_fontcolor_id, text2, text2_size, text2fontcolor_id, vertical_position, side_position FROM ORDER_HISTORY_TABLE orderhis inner join DESIGN_TABLE design on orderhis.order_code = design.order_code where user_id = ? order by orderhis.order_code desc";
        System.out.println("DAO" + userId);
        try {
        	 OrderHistoryDTO dto = new OrderHistoryDTO();
        	 
        	 stmt = conn.prepareStatement(sql);
        	 
        	 stmt.setString(1,(userId));
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	dto.setOrderCode(rs.getString("order_code"));
                dto.setUserId(rs.getString("user_id"));
                dto.setOrderDate(rs.getDate("order_date"));
                dto.setOrderCount(rs.getInt("order_count"));
                dto.setBaseColor(rs.getString("base_color"));
                dto.setText1(rs.getString("text1"));
                dto.setText1Size(rs.getInt("text1_size"));
                dto.setText1FontColorId(rs.getString("text1_fontcolor_id"));
                dto.setText2(rs.getString("text2"));
                dto.setText2Size(rs.getInt("text2_size"));
                dto.setText2FontColorId(rs.getString("text2fontcolor_id"));
                dto.setVerticalPosition(rs.getInt("vertical_position"));
                dto.setSidePosition(rs.getInt("side_position"));
                
                System.out.println("DAOが返すリスト" +list);
                list.add(dto);
            }
             

        } catch (SQLException e) {
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

        return list;
    }
	
	
	public boolean setOrderHistoryDTO(Connection conn, OrderHistoryDTO orderDTO) {
		String sql = "INSERT INTO ORDER_HISTORY_TABLE (ORDER_CODE, USER_ID, ORDER_DATE, ORDER_COUNT) VALUES(?, ?, CURRENT_TIMESTAMP,?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderDTO.getOrderCode());
			stmt.setString(2, orderDTO.getUserId());
			stmt.setInt(3, orderDTO.getOrderCount());
			
			stmt.executeUpdate();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
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
