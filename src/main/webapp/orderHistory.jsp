<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.winc.kensyu.DTO.OrderHistoryDTO" %>
<%@ page import="com.winc.kensyu.DAO.OrderHistoryDAO" %>
<%@ page import="com.winc.kensyu.DTO.DesignDTO" %>
	<tr>
			<th title="お客様の注文の識別番号です。">注文番号</th>
			<th title="注文を確定した日付です。">注文日</th>
			<th title="注文したTシャツの合計枚数です。">枚数</th>
			<th title="入力された文字やレイアウトの設定です。">入力情報</th>
			<th title="作成されたTシャツのデザイン画像です。">デザイン</th>
			</tr title="">
			<!-- 表のHTMLが入った変数を入れる -->
			
			
			 <% if((List<OrderHistoryDTO>)request.getAttribute("orderList") != null){
			 	List<OrderHistoryDTO> list = (List<OrderHistoryDTO>)request.getAttribute("orderList");
			 	System.out.println(list);
			 	for(OrderHistoryDTO ohdto:list ){%>
			 		<tr>
			 			<td><!-- 注文番号 -->
			 				<%= ohdto.getOrderCode() %>
			 			</td>
			 			
			 			<td><!-- 注文日 -->
			 				<%= ohdto.getOrderDate()%>
			 			</td>
			 				
			 			<td><!-- 枚数 -->
			 				<%= ohdto.getOrderCount()%>
			 			</td>
			 			
			 			<td><!-- 入力情報 -->
			 				上段:<%= ohdto.getText1() %><br>
			 				下段:<%= ohdto.getText2() %><br>
			 				ベースカラー:<%= ohdto.getBaseColor() %><br>
			 				フォントサイズ<br>
			 				:上段<%= ohdto.getText1Size() %><br>
			 				:下段<%= ohdto.getText2Size() %><br>
			 				横位置:<%= ohdto.getSidePosition() %><br>
			 				縦位置:<%= ohdto.getVerticalPosition() %><br>
			 			</td>
			 			
			 			<td><!-- デザイン -->
			 				<div class="design-container">
    
    						<div class="design-preview">
      							<span></span>
    						</div>

    						<!-- コピー ボタンエリア -->
					    <div class="copy-button-area">
      						 <input type="button" value="デザインをコピー"
							   onclick='
							     let data = "";
							     data += "baseColor=<%= ohdto.getBaseColor() %>;";
							     data += "Text1=<%= ohdto.getText1() %>;";
							     data += "Text1Size=<%= ohdto.getText1Size() %>;";
							     data += "Text1FontColorId=<%= ohdto.getText1FontColorId() %>;";
							     data += "Text2=<%= ohdto.getText2() %>;";
							     data += "Text2Size=<%= ohdto.getText2Size() %>;";
							     data += "Text2FontColorId=<%= ohdto.getText2FontColorId() %>;";
							     data += "verticalPosition=<%= ohdto.getVerticalPosition() %>;";
							     data += "sidePosition=<%= ohdto.getSidePosition() %>;";
							     sessionStorage.setItem("copiedDesign", data);
							     changeTab(\"tab1\");'
							     />
						    </div>
						  </div>
			 			</td>
			 			
			 		</tr>
			 		
			 <%
			 	}}
			 %> 