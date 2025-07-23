 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
 <%@ page import="com.winc.kensyu.DTO.OrderHistoryDTO" %>
  <%@ page import="com.winc.kensyu.DAO.OrderHistoryDAO" %>
 <%@ page import="com.winc.kensyu.DTO.DesignDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tシャツ注文システム</title>
<link type="text/css" rel="stylesheet" href="stylesheets/style.css">
<script src="scripts/loginFilter.js"></script>
</head>
<body onload="changeTab()"> <!-- onloadで初期画面変更して -->
<div class="container">

<p class="display-user">会社名：</p>
<p class="display-user">ユーザー：</p>

<div id="tab-control">
	<form action="./orderHistoryServlet" method="get">
	<input type="submit">
	<a href="#tab1">デザイン画面</a>
	</form>
	<a href="#tab2">注文履歴画面</a>
</div>

<div id="tab-body">

	<!------ ログイン画面 ------>
	<div class="tab" id="login-tab">
		<h1>ログイン</h1>
		<form method="get" action="./loginServlet2">
		<input type="text" name="ID" id="input-id" placeholder="IDを入力してください。"><br>
		<input type="text" name="pass" id="input-password" placeholder="パスワードを入力してください。"><br>
		<input type="submit" id="submit-login" value="ログイン">
		</form>
	</div>


	<!-- デザイン画面 -->
	<div class="tab" id="tab1">
		<h1 style="text-align:center;">Tシャツデザイン画面</h1>
		<p>Tシャツのベースカラー</p>
		<form action="/buyServlet" method="post">
		<select id="base-color" onchange="ChangeBaseColor()">
			<option value="White-T">白</option>
			<option value="Gray-T">グレー</option>
			<option value="Black-T">黒</option>
		</select>
		<div style="text-align: right;">
			<input type="button" id="design-reset">デザインのリセット
		</div>
		
		<!-- 入力欄 -->
		<div class="clearfix">
		<div id="input-field">
			<p>Tシャツに入れる文字(上段)</p>
			<input type="text" name="text1" id="upper-text-input" onchange="ChangeDesign()">
			<select class="color-palette" name="text1_font_color" onchange="ChangeDesign()"></select>
				
			<p>Tシャツに入れつ文字(下段)</p>
			<input type="text" name="text2" id="lower-text-input" onchange="ChangeDesign()">
			<select class="color-palette" name="text2_font_color" onchange="ChangeDesign()"></select>
			
			<p>上段文字のフォントサイズ<input type="number" name="text1_size" class="font-size" value="24" onchange="ChangeDesign()">pt</p>
			<p>下段文字のフォントサイズ<input type="number" name="text2_size" class="font-size" value="24" onchange="ChangeDesign()">pt</p>
			<p>文字の縦位置<input type="number" name="vertical_position" class="position" value="0" onchange="ChangeDesign()">%</p>
			<p>文字の横位置<input type="number" name="side_position" class="position" value="0" onchange="ChangeDesign()">%</p>
		</div>
			
		<!-- Tシャツ表示 -->
		<div id="view-design">
			<section id="view-text"></section>
		
		</div>
		</div>
		
		<!-- 注文枚数、注文確定ボタン -->
		<div id="order-submit">
			<h2>注文枚数 : <input type="text">枚 <input type=submit value="注文確定"></h2>
		</div>
		</form>
	</div>
	
	
	<!------注文履歴画面------>
<!------注文履歴画面------>
	<div class="tab" id="tab2">
		<h1 style="text-align:left;">注文履歴画面</h1>
		<table id="history-table">
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
		</table>
	</div>
</div>

<script src="scripts/designChange.js"></script>
</div>
</body>
</html> 

