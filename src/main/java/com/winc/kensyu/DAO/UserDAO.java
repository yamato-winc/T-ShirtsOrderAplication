package com.winc.kensyu.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.winc.kensyu.DTO.UserDTO;

	

	public class UserDAO {
		    // 全件取得
		    public void getUSERDTO() {
		        
		        String sql = "SELECT user_id, user_pass, user_company, user_name, user_phonenumber, submit_user_date, update_user_date FROM USER_TABLE";

		        try (Connection conn = DBAccess.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql);
		             ResultSet rs = stmt.executeQuery()) {

		            
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
		        }

		    }
	}

		    

