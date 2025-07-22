 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tシャツ注文システム</title>
<link type="text/css" rel="stylesheet" href="stylesheets/style.css">

<script>/*ベースカラー格納用変数*/
let baseColor;

/*ベースカラー変更*/
function ChangeBaseColor(){
	baseColor = document.getElementById("base-color").value;
	//デバッグ用
	console.log(baseColor);
}</script>


</head>
<body style="margin: 0% 20%">
<p>会社名：</p>
<p>ユーザー：</p>

<p id="tab-control">
	<a href="#tab1">デザイン画面</a>
	<a href="#tab2">注文履歴画面</a>
</p>

<div id="tab-body">
	<!-- デザイン画面 -->
	<div class="tab" id="tab1">
		<h1 style="text-align:center;">Tシャツデザイン画面</h1>
		<p>Tシャツのベースカラー</p>
		<select id="base-color" onchange="ChangeBaseColor()">
			<option value="White-T">白</option>
			<option value="Gray-T">グレー</option>
			<option value="Black-T">黒</option>
		</select>
		<div style="text-align: right;">
			<button id="design-reset">デザインのリセット</button>
		</div>

		
		
		
		<!-- 
		----デザイン表示画面 
		-->
		
		<div id="view-design">
		
		</div>
	</div>
	
	
	<!-- 注文履歴画面 -->
	<div class="tab" id="tab2">
		<h1>注文履歴画面</h1>
	</div>
</div>

<script type="module" src="scripts/designChange.js"></script>
</body>
</html> 