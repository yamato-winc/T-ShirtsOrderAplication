<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.winc.kensyu.DTO.OrderHistoryDTO" %>
<%@ page import="com.winc.kensyu.DAO.OrderHistoryDAO" %>
<%@ page import="com.winc.kensyu.DTO.DesignDTO" %>
<%@ page import="com.winc.kensyu.DTO.FontColorDTO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%
// フォントカラーマップを作成
Map<String, String> fontColorMap = new HashMap<>();
List<FontColorDTO> fontColorList = (List<FontColorDTO>)request.getAttribute("fontColorList");
if(fontColorList != null) {
    for(FontColorDTO fontColor : fontColorList) {
        String rgb = fontColor.getFontColorR() + "," + fontColor.getFontColorG() + "," + fontColor.getFontColorB();
        fontColorMap.put(String.valueOf(fontColor.getFontColorId()), rgb);
    }
}
%>

<table id="history-table">
	<tr>
		<th title="お客様の注文の識別番号です。">注文番号</th>
		<th title="注文を確定した日付です。">注文日</th>
		<th title="注文したTシャツの合計枚数です。">枚数</th>
		<th title="入力された文字やレイアウトの設定です。">入力情報</th>
		<th title="作成されたTシャツのデザイン画像です。">デザイン</th>
	</tr>
	<!-- 表のHTMLが入った変数を入れる -->
	
	<% if((List<OrderHistoryDTO>)request.getAttribute("orderList") != null){
		List<OrderHistoryDTO> list = (List<OrderHistoryDTO>)request.getAttribute("orderList");
		System.out.println("受け取ったリスト" + list.size() + "件");
		for(OrderHistoryDTO ohdto : list) {
			// フォントカラーを取得（デフォルトは黒）
			String text1Color = fontColorMap.getOrDefault(ohdto.getText1FontColorId(), "0,0,0");
			String text2Color = fontColorMap.getOrDefault(ohdto.getText2FontColorId(), "0,0,0");
	%>
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
				:上段<%= ohdto.getText1Size() %>pt<br>
				:下段<%= ohdto.getText2Size() %>pt<br>
				横位置:<%= ohdto.getSidePosition() %><br>
				縦位置:<%= ohdto.getVerticalPosition() %><br>
			</td>
			
			<td><!-- デザイン -->
				<div class="design-container">
					<div class="view-design" style="position:relative; width:180px; height:180px; overflow:hidden; margin: auto;">
						<img src="image/<%= ohdto.getBaseColor() %>.png" alt="Tシャツの画像" style="width:180px; height:180px;">
						
						<div style="
							position: absolute;
							top: <%= 90 - (ohdto.getVerticalPosition() * 1.5) %>px;
							left: <%= 90 + (ohdto.getSidePosition() * 1.5) %>px;
							transform: translate(-50%, -50%);
							width: 140px;
							max-width: 160px;
							overflow: hidden;
							display: flex;
							flex-direction: column;
							align-items: center;
							<% 
								int gap = 8;
								if (ohdto.getText1() != null && !ohdto.getText1().trim().isEmpty() &&
									ohdto.getText2() != null && !ohdto.getText2().trim().isEmpty()) {
									int avg = (ohdto.getText1Size() + ohdto.getText2Size()) / 2;
									gap = Math.max(3, (int)(avg * 0.25));
								}
							%>
							gap: <%= gap %>px;
						">
							<% if (ohdto.getText1() != null && !ohdto.getText1().trim().isEmpty()) { %>
								<p style="
									font-size:<%= (ohdto.getText1Size() * 0.25) %>pt; 
									color:rgb(<%= text1Color %>); 
									margin: 0; 
									text-align:center;
									text-shadow: 1px 1px 2px rgba(0,0,0,0.3);
									font-weight: bold;
									white-space: nowrap;
									overflow: hidden;
									text-overflow: ellipsis;
									max-width: 100%;
								">
									<%= ohdto.getText1() %>
								</p>
							<% } %>
							
							<% if (ohdto.getText2() != null && !ohdto.getText2().trim().isEmpty()) { %>
								<p style="
									font-size:<%= (ohdto.getText2Size() * 0.25) %>pt; 
									color:rgb(<%= text2Color %>); 
									margin: 0; 
									text-align:center;
									text-shadow: 1px 1px 2px rgba(0,0,0,0.3);
									font-weight: bold;
									white-space: nowrap;
									overflow: hidden;
									text-overflow: ellipsis;
									max-width: 100%;
								">
									<%= ohdto.getText2() %>
								</p>
							<% } %>
						</div>
					</div>
				</div>
			</td>
		</tr>
	<%
		}
	} else { %>
		<tr>
			<td colspan="5" style="text-align: center;">注文履歴がありません</td>
		</tr>
	<% } %>
</table> 