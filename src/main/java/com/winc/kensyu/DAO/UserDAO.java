package com.winc.kensyu.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.winc.kensyu.DTO.UserDTO;

	

	public class UserDAO {
		
		    // 全件取得
		    public void getUSERDTOALL() {
		    	Connection conn = null;
		    	PreparedStatement stmt = null;
		    	ResultSet rs = null;
		    	String sql = "SELECT user_id, user_pass, user_company, user_name, user_phonenumber, submit_user_date, update_user_date FROM USER_TABLE";
		    	
		    	try {
		    		conn = DBAccess.getConnection();
		    		stmt = conn.prepareStatement(sql);
		    		rs = stmt.executeQuery();
		    		
		    		UserDTO dto = new UserDTO();
		    		dto.setUserId(rs.getString("user_id"));
		    		dto.setUserPass(rs.getString("user_pass"));
		    		dto.setUserCompany(rs.getString("user_company"));
		    		dto.setUserName(rs.getString("user_name"));
		    		dto.setUserPhonenumber(rs.getString("user_phonenumber"));
		    		dto.setSubmitUserDate(rs.getDate("submit_user_date"));
		    		dto.setUpdateUserDate(rs.getDate("update_user_date"));
		    		
		    	} catch (SQLException e) {
		    		e.printStackTrace();
		    	}finally {
		    		try {
		    			if(rs != null) {
		    				rs.close();
		    			}
		    			
		    			if(stmt != null) {
		    				rs.close();
		    			}
		    			
		    			if(conn != null) {
		    				conn.close();
		    			}
		    		}catch(SQLException e) {
		    			System.out.println("objectのclose時に例外が発生");
		    			e.printStackTrace();
		    		}
		    	}
		    }
		    
		    // 検索取得
		    public UserDTO getUSERDTO(Connection conn, String userID) {
		    	PreparedStatement stmt = null;
		    	ResultSet rs = null;
		    	
		    	String sql = "SELECT user_id, user_pass, user_company, user_name, user_phonenumber, submit_user_date, update_user_date FROM USER_TABLE WHERE user_id = ?";
		    	
		    	try{
		    		stmt = conn.prepareStatement(sql);
		    		stmt.setString(1, userID);
		    		rs = stmt.executeQuery();
		    		
		    		if(rs.next() == false) {
		    			return null;
		    		}
		    		
		    		UserDTO dto = new UserDTO();
		    		dto.setUserId(rs.getString("user_id"));
		    		dto.setUserPass(rs.getString("user_pass"));
		    		dto.setUserCompany(rs.getString("user_company"));
		    		dto.setUserName(rs.getString("user_name"));
		    		dto.setUserPhonenumber(rs.getString("user_phonenumber"));
		    		dto.setSubmitUserDate(rs.getDate("submit_user_date"));
		    		dto.setUpdateUserDate(rs.getDate("update_user_date"));
		    		
		    		return dto;
		    		
		    	} catch (SQLException e) {
		    		e.printStackTrace();
		    		return null;
		    	}finally {
		    		try {
		    			if(rs != null) {
		    				rs.close();
		    			}
		    			
		    			if(stmt != null) {
		    				rs.close();
		    			}
		    			
		    		}catch(SQLException e) {
		    			System.out.println("objectのclose時に例外が発生");
		    			e.printStackTrace();
		    		}
		    	}
		    }
	}