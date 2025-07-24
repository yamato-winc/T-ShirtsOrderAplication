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
				<!-- 表のHTMLが入った変数を入れる -->
			 <% if((List<OrderHistoryDTO>)request.getAttribute("orderList") != null){
			 	List<OrderHistoryDTO> list = (List<OrderHistoryDTO>)request.getAttribute("orderList");
			 	System.out.println("受け取ったリスト" + list);
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
    <div class="view-design" style="position:relative; width:180px; height:180px; overflow:hidden;">
      <img src="image/<%= ohdto.getBaseColor() %>.png" alt="Tシャツの画像" style="width:180px; height:180px;">
      
      <div style="
        position: absolute;
        top: <%= 90 - (ohdto.getVerticalPosition() * 2) %>px;
        left: <%= 90 + (ohdto.getSidePosition() * 2) %>px;
        transform: translate(-50%, -50%);
        width: 140px;
        max-width: 160px;
        overflow: hidden;
        display: flex;
        flex-direction: column;
        align-items: center;
        <% 
          int gap = 10;
          if (ohdto.getText1() != null && !ohdto.getText1().isEmpty() &&
              ohdto.getText2() != null && !ohdto.getText2().isEmpty()) {
              int avg = (ohdto.getText1Size() + ohdto.getText2Size()) / 2;
              gap = Math.max(5, (int)(avg * 0.3));
          }
        %>
        gap: <%= gap %>px;
      ">
        <% if (ohdto.getText1() != null && !ohdto.getText1().trim().isEmpty()) { %>
          <p style="font-size:<%= ohdto.getText1Size() %>pt; color:rgb(<%= ohdto.getText1FontColorId() %>); margin: 0; text-align:center;">
            <%= ohdto.getText1() %>
          </p>
        <% } %>
        
        <% if (ohdto.getText2() != null && !ohdto.getText2().trim().isEmpty()) { %>
          <p style="font-size:<%= ohdto.getText2Size() %>pt; color:rgb(<%= ohdto.getText2FontColorId() %>); margin: 0; text-align:center;">
            <%= ohdto.getText2() %>
          </p>
        <% } %>
      </div>
    </div>
  </div>
</td>

			 			
			 		</tr>
			 		
			 <%
			 	}}
			 %> 