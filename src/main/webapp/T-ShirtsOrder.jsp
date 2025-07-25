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
<body onload="load()">

<header>
<div id="header-text">
<h1>オリジナルTシャツ注文サイト</h1>
<p id="display-company"></p>
<p id="display-user"></p>
</div>
</header>
<div class="container">
<main>
<div id="tab-control">
	<a onclick="changeTab('tab1')">デザイン画面</a>
	<a onclick="changeToOrderHistory()">注文履歴画面</a>
</div>
<div id="tab-body">

	<!------ ログイン画面 ------>
	<div class="tab" id="login-tab">
		<div id="input-idPass">
			<h1>ログイン</h1>
			<input type="text" name="ID" id="input-id" placeholder="IDを入力してください。"><br>
			<input type="text" name="pass" id="input-password" placeholder="パスワードを入力してください。"><br>
			<input type="button" id="submit-login" value="ログイン" onclick="getUser()">
		</div>
	</div>


	<!-- デザイン画面 -->
	<div class="tab" id="tab1">
		<h1 style="text-align:center;">Tシャツデザイン画面</h1>
		<p id="label-base-color">Tシャツのベースカラー</p>
		<select id="base-color" name="base-color" onchange="ChangeBaseColor()">
			<option value="W">白</option>
			<option value="G">グレー</option>
			<option value="B">黒</option>
		</select>
		<button id="design-reset" onclick="DesignReserButton()">デザインのリセット</button>

		
		<!-- 入力欄 -->
		<div class="clearfix">
		<div id="input-field">
			<p>Tシャツに入れる文字(上段)</p>
			<input type="text" name="text1" id="upper-text-input" onchange="ChangeDesign()">
			<select class="color-palette" name="text1_font_color" onchange="ChangeDesign()"></select>
				
			<p>Tシャツに入れる文字(下段)</p>
			<input type="text" name="text2" id="lower-text-input" onchange="ChangeDesign()">
			<select class="color-palette" name="text2_font_color" onchange="ChangeDesign()"></select>
			
			<p>上段文字のフォントサイズ<input type="number" name="text1_size" class="font-size" value="24" onchange="ChangeDesign()">pt</p>
			<p>下段文字のフォントサイズ<input type="number" name="text2_size" class="font-size" value="24" onchange="ChangeDesign()">pt</p>
			<p>文字の縦位置<input type="number" name="vertical_position" class="position" value="0" onchange="ChangeDesign()"></p>
			<p>文字の横位置<input type="number" name="side_position" class="position" value="0" onchange="ChangeDesign()"></p>
		</div>
			
		<!-- Tシャツ表示 -->
		<div class="view-design">
			<div id="view-T-shirts"></div>
			<section id="view-text">
				<div id="view-upper-text"></div>
				<div id="view-lower-text"></div>
			</section>		
		</div>
		</div>
		
		<!-- 注文枚数、注文確定ボタン -->
		<div id="order-submit">
			<h2>注文枚数 : <input type="text" name="order-count" value="1">枚 <input type="button" value="注文確定" onclick="buy()"></h2>
		</div>
	</div>
	
	
	<!------注文履歴画面------>
	<div class="tab" id="tab2">
		<h1 style="text-align:left;">注文履歴画面</h1>
		<table id="history-table">
		
		</table>
	</div>
	
</div>
</main>
<script src="scripts/designChange.js"></script>
</div>
</body>
</html> 

