package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.winc.kensyu.DTO.DesignDTO;
import com.winc.kensyu.DTO.OrderHistoryDTO;

public class DesignDAO {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	public void getDesignDTO(Connection conn) {


        String sql = "SELECT order_code, base_color, text1, text1_size, text1_fontcolor_id, text2, text2_size, text2_fontcolor_id, vertical_position,side_position FROM DESIGN_TABLE where order_code = ?";

        try{
        	    stmt = conn.prepareStatement(sql);
        		OrderHistoryDTO Odto = new OrderHistoryDTO();
        		stmt.setString(1, Odto.getOrderCode());
        		rs = stmt.executeQuery();

        	DesignDTO dto = new DesignDTO();
            	dto.setOrderCode(rs.getString("order_code"));
                dto.setBaseColor(rs.getString("base_color"));
                dto.setText1(rs.getString("text1"));
                dto.setText1Size(rs.getInt("text1_size"));
                dto.setText1FontColorId(rs.getString("text1_fontcolor_id"));
                dto.setText2(rs.getString("text2"));
                dto.setText2Size(rs.getInt("text2_size"));
                dto.setText2FontColorId(rs.getString("text2_fontcolor_id"));
                dto.setVerticalPosition(rs.getInt("vertical_position"));
                dto.setSidePosition(rs.getInt("side_position"));
        
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

	}
	
	public boolean setDesignDTO(Connection conn, DesignDTO designDTO) {
		String sql = "INSERT INTO DESIGN_TABLE(ORDER_CODE, BASE_COLOR, TEXT1, TEXT1_SIZE, TEXT1_FONTCOLOR, TEXT2, TEXT2_SIZE, TEXT2_FONTCOLOR, VERTICAL_POSITION, SIDE_POSITION) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, designDTO.getOrderCode());
			stmt.setString(2, designDTO.getBaseColor());
			stmt.setString(3, designDTO.getText1());
			stmt.setInt(4, designDTO.getText1Size());
			stmt.setString(5, designDTO.getText1FontColorId());
			stmt.setString(6, designDTO.getText2());
			stmt.setInt(7, designDTO.getText2Size());
			stmt.setString(8, designDTO.getText2FontColorId());
			stmt.setInt(9, designDTO.getVerticalPosition());
			stmt.setInt(10, designDTO.getSidePosition());
			
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
			}
			catch(SQLException e) {
				System.out.println("objectのclose時に例外が発生");
				e.printStackTrace();
			}
		}
	}
}