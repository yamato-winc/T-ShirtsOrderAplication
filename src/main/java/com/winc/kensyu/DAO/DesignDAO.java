package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.winc.kensyu.DTO.DesignDTO;

public class DesignDAO {
	
	public void getDesignDTO() {

        String sql = "SELECT order_code, base_color, text1, text1_size, text1_fontcolor_id, text2, text2_size, text2_fontcolor_id, vertical_position,side_position FROM DESIGN_TABLE";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
        }

    
	}
}