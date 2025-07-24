package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.winc.kensyu.DTO.FontColorDTO;

public class FontColorDAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
		
	public List<FontColorDTO> getFontColorDTO() {
		List<FontColorDTO> list = new ArrayList<>();
		String sql = "SELECT fontcolor_id, fontcolor_r, fontcolor_g, fontcolor_b, fontcolor_use_start, fontcolor_use_end FROM FONTCOLOR_TABLE where now() between fontcolor_use_start and fontcolor_use_end";
		try {
			Connection conn = DBAccess.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				FontColorDTO dto = new FontColorDTO();
				dto.setFontColorId(rs.getInt("fontcolor_id"));
				dto.setFontColorR(rs.getInt("fontcolor_r"));
				dto.setFontColorG(rs.getInt("fontcolor_g"));
				dto.setFontColorB(rs.getInt("fontcolor_b"));
				dto.setFontColorUseStart(rs.getDate("fontcolor_use_start"));
				dto.setFontColorUserEnd(rs.getDate("fontcolor_use_end"));
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
				
				if(conn != null) {
					conn.close();
				}
				
			}catch(SQLException e) {
				System.out.println("objectのclose時に例外が発生");
				e.printStackTrace();
			}
		}
		
		return list;
	}
}

