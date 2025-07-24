<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.winc.kensyu.DTO.OrderHistoryDTO" %>
<%@ page import="com.winc.kensyu.DAO.OrderHistoryDAO" %>
<%@ page import="com.winc.kensyu.DTO.DesignDTO" %>
	<tr>
			<th>注文番号</th>
			<th>注文日</th>
			<th>枚数</th>
			<th>入力情報</th>
			<th>デザイン</th>
			</tr>
			<!-- 表のHTMLが入った変数を入れる -->
			
			
			 <% if((List<OrderHistoryDTO>)request.getAttribute("orderList") != null){
			 	List<OrderHistoryDTO> list = (List<OrderHistoryDTO>)request.getAttribute("orderList");
			 	System.out.println(list);
			 	for(OrderHistoryDTO ohdto:list ){%>
			 		<tr>
			 			<td>
			 				<%= ohdto.getOrderCode() %>
			 			</td>
			 			
			 			<td>
			 				<%= ohdto.getOrderDate()%>
			 			</td>
			 				
			 			<td>
			 				<%= ohdto.getOrderCount()%>
			 			</td>
			 			
			 			<td>
			 				上段:<%= ohdto.getText1() %><br>
			 				下段:<%= ohdto.getText2() %><br>
			 				ベースカラー:<%= ohdto.getBaseColor() %><br>
			 				フォントサイズ:上段<%= ohdto.getText1() %>
			 							  :下段<%= ohdto.getText2() %><br>
			 				横位置:<%= ohdto.getSidePosition() %><br>
			 				縦位置:<%= ohdto.getVerticalPosition() %><br>
			 			</td>
			 			
			 			<td>
			 			
			 			</td>
			 			
			 		</tr>
			 		
			 <%
			 	}}
			 %>