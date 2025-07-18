package com.winc.kensyu.DAO;
/* 前回使ったDAO。サンプルとして使ってね❤


package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.winc.kensyu.DTO.CompanyDTO;

public class SampleDAO {
	    // 全件取得
	    public List<CompanyDTO> getCompanyDTOl() {
	        List<CompanyDTO> list = new ArrayList<>();
	        String sql = "SELECT phone_number, company_name, zip_code, address_name, password, active FROM company";

	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                CompanyDTO dto = new CompanyDTO();
	                dto.setPhoneNumber(rs.getString("phone_number"));
	                dto.setCompanyName(rs.getString("company_name"));
	                dto.setZipCode(rs.getString("zip_code"));
	                dto.setAddressName(rs.getString("address_name"));
	                dto.setPassword(rs.getString("password"));
	                dto.setActive(rs.getString("active").charAt(0)); // 'Y' or 'N'
	                list.add(dto);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

	    // 電話番号で1件取得
	    public CompanyDTO findByPhone(String phoneNumber) {
	        String sql = "SELECT phone_number, company_name, zip_code, address_name, password, active FROM COMPANY_TABLE WHERE phone_number = ?";
	        CompanyDTO dto = null;

	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, phoneNumber);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                dto = new CompanyDTO();
	                dto.setPhoneNumber(rs.getString("phone_number"));
	                dto.setCompanyName(rs.getString("company_name"));
	                dto.setZipCode(rs.getString("zip_code"));
	                dto.setAddressName(rs.getString("address_name"));
	                dto.setPassword(rs.getString("password"));
	                dto.setActive(rs.getString("active").charAt(0));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return dto;
	    }
	    
	    public int insertCompany(CompanyDTO dto) {
	        String sql = "INSERT INTO COMPANY_TABLE " +
	                     "(PHONE_NUMBER, COMPANY_NAME, ZIP_CODE, ADDRESS_NAME, PASSWORD, ACTIVE) " +
	                     "VALUES (?, ?, ?, ?, ?, ?)";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, dto.getPhoneNumber());
	            stmt.setString(2, dto.getCompanyName());
	            stmt.setString(3, dto.getZipCode());
	            stmt.setString(4, dto.getAddressName());
	            stmt.setString(5, dto.getPassword());
	            stmt.setString(6, String.valueOf(dto.getActive()));  // char -> String

	            return stmt.executeUpdate();  // 1なら成功、0なら失敗

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return -1;
	        }
	    }
	    
	    public boolean existsPhoneNumber(String phoneNumber) {
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(
	                 "SELECT 1 FROM COMPANY_TABLE WHERE PHONE_NUMBER = ?")) {

	            ps.setString(1, phoneNumber);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public CompanyDTO selectCompanyByPhoneNumber(String phoneNumber) {
	        CompanyDTO dto = null;
	        String sql = "SELECT * FROM COMPANY_TABLE WHERE PHONE_NUMBER = ?";

	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, phoneNumber);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                dto = new CompanyDTO();
	                dto.setPhoneNumber(rs.getString("PHONE_NUMBER"));
	                dto.setCompanyName(rs.getString("COMPANY_NAME"));
	                dto.setZipCode(rs.getString("ZIP_CODE"));
	                dto.setAddressName(rs.getString("ADDRESS_NAME"));
	                dto.setPassword(rs.getString("PASSWORD"));
	                dto.setActive(rs.getString("ACTIVE").charAt(0));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return dto;
	    }
	    
	    public void updateCompany(CompanyDTO dto) {
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(
	                "UPDATE COMPANY_TABLE SET COMPANY_NAME=?, ZIP_CODE=?, ADDRESS_NAME=?, PASSWORD=?, ACTIVE=? WHERE PHONE_NUMBER=?")) {
	            ps.setString(1, dto.getCompanyName());
	            ps.setString(2, dto.getZipCode());
	            ps.setString(3, dto.getAddressName());
	            ps.setString(4, dto.getPassword());
	            ps.setString(5, String.valueOf(dto.getActive()));
	            ps.setString(6, dto.getPhoneNumber());
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
*/