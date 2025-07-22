package com.winc.kensyu.DTO;

import java.sql.Date;

public class UserDTO {

		private String orderCode;
		private String userId;
		private Date orderDate;
		private int orderCount; 
		
		public String getOrderCode() {
			return orderCode;
		}
		public void setOrderCode(String orderCode) {
			this.orderCode = orderCode;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public Date getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}
		public int getOrderCount() {
			return orderCount;
		}
		public void setOrderCount(int orderCount) {
			this.orderCount = orderCount;
		}
				

}
