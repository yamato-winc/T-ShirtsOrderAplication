package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.winc.kensyu.DTO.OrderHistoryDTO;

public class OrderHistoryDAO {
	
	public List<OrderHistoryDTO> getCompanyDTOl() {
        List<OrderHistoryDTO> list = new ArrayList<>();
        String sql = "SELECT order_code, user_id, order_date, order_count FROM ORDER_HISTORY_TABLE";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	OrderHistoryDTO dto = new OrderHistoryDTO();
            	dto.setOrderCode(rs.getNString("order_code"));
                dto.setUserId(rs.getNString("user_id"));
                dto.setOrderDate(rs.getDate("order_date"));
                dto.setOrderCount(rs.getInt("order_count"));
                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
