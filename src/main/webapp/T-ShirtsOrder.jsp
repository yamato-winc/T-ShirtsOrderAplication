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
<body onload="load()"> <!-- onloadで初期画面変更して -->
<div class="container">

<p id="display-company"></p>
<p id="display-user"></p>

<p id="tab-control">
	<a onclick="changeTab('tab1')">デザイン画面</a>
	<a onclick="changeToOrderHistory()">注文履歴画面</a>
</p>

<div id="tab-body">

	<!------ ログイン画面 ------>
	<div class="tab" id="login-tab">
		<h1>ログイン</h1>
		<input type="text" name="ID" id="input-id" placeholder="IDを入力してください。"><br>
		<input type="text" name="pass" id="input-password" placeholder="パスワードを入力してください。"><br>
		<input type="button" id="submit-login" value="ログイン" onclick="getUser()">
	</div>


	<!-- デザイン画面 -->
	<div class="tab" id="tab1">
		<h1 style="text-align:center;">Tシャツデザイン画面</h1>
		<p>Tシャツのベースカラー</p>
		<select id="base-color" name="base-color" onchange="ChangeBaseColor()">
			<option value="White-T">白</option>
			<option value="Gray-T">グレー</option>
			<option value="Black-T">黒</option>
		</select>
		<div style="text-align: right;">
			<button id="design-reset">デザインのリセット</button>
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
			<h2>注文枚数 : <input type="text" name="order-count">枚 <input type="button" value="注文確定" onclick="buy()"></h2>
		</div>
	</div>
	
	
	<!------注文履歴画面------>
	<div class="tab" id="tab2">
		<h1 style="text-align:left;">注文履歴画面</h1>
		<table id="history-table">
		
		</table>
	</div>
</div>

<script src="scripts/designChange.js"></script>
</div>
</body>
</html> 

