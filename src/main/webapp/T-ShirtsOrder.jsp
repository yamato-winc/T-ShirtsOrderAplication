 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tシャツ注文システム</title>
<link type="text/css" rel="stylesheet" href="stylesheets/style.css">
</head>
<body style="margin: 0% 20%" onload="changeTab()"> <!-- onloadで初期画面変更して -->
<p class="display-user">会社名：</p>
<p class="display-user">ユーザー：</p>

<p id="tab-control">
	<a href="#tab1">デザイン画面</a>
	<a href="#tab2">注文履歴画面</a>
</p>

<div id="tab-body">

	<!------ ログイン画面 ------>
	<div class="tab" id="login-tab">
		<h1>ログイン</h1>
		<form method="get" action="/loginServlet">
		<input type="text" id="input-id" placeholder="IDを入力してください。"><br>
		<input type="text" id="input-password" placeholder="パスワードを入力してください。"><br>
		<input type="submit" id="submit-login" value="ログイン" onclick="changeTab()" >
		</form>
	</div>



	<!-- デザイン画面 -->
	<div class="tab" id="tab1">
		<h1 style="text-align:center;">Tシャツデザイン画面</h1>
		<p>Tシャツのベースカラー</p>
		<form action="" method="post">
		<select id="base-color" onchange="ChangeBaseColor()">
			<option value="White-T">白</option>
			<option value="Gray-T">グレー</option>
			<option value="Black-T">黒</option>
		</select>
		<div style="text-align: right;">
			<button id="design-reset">デザインのリセット</button>
		</div>
		
		<!-- 入力欄 -->
		<div id="input-field">
			<p>Tシャツに入れる文字(上段)</p>
			<input type="text" id="upper-text-input">
			<!-- カラーパレット -->
			<div id="color-box"></div>
			<div id="color-options">
			<!-- サンプルカラー -->
			  <div class="color-swatch" style="background-color: #000000;" data-color="#000000"></div>
			  <div class="color-swatch hidden" style="background-color: #ff0000;" data-color="#ff0000"></div>
			  <div class="color-swatch hidden" style="background-color: #008000;" data-color="#008000"></div>
			  <div class="color-swatch hidden" style="background-color: #0000ff;" data-color="#0000ff"></div>
			  <div class="color-swatch hidden" style="background-color: #ffff00;" data-color="#ffff00"></div>
			  <div class="color-swatch hidden" style="background-color: #800080;" data-color="#800080"></div>
			  <div class="color-swatch hidden" style="background-color: #ffc0cb;" data-color="#ffc0cb"></div>
			  <div class="color-swatch hidden" style="background-color: #808080;" data-color="#808080"></div>
			  <div class="color-swatch hidden" style="background-color: #a52a2a;" data-color="#a52a2a"></div>
			</div>
			<script>
				const colorBox = document.getElementByID("color-box");
				const swatches = document.querySelectorAll(".color-swatch");
				const colorOptions = document.getElementById("color-options");
				
				let paletteExpanded = false;
				
				  // スウォッチクリック時
				  swatches.forEach((swatch, index) => {
				    swatch.addEventListener('click', (e) => {
				      e.stopPropagation();
				
				      const color = swatch.dataset.color;
				      colorBox.style.backgroundColor = color;


				      // パレット展開
				      if (!paletteExpanded) {
				        swatches.forEach((s, i) => {
				          if (i !== 0) s.classList.remove('hidden');
				        });
				        paletteExpanded = true;
				      }
				    });
				  });
				// パレット外クリックで閉じる
				  document.addEventListener('click', () => {
				    if (paletteExpanded) {
				      swatches.forEach((s, i) => {
				        if (i !== 0) s.classList.add('hidden');
				      });
				      paletteExpanded = false;
				    }
				  });
				  // パレット内部クリックは除外
				  colorOptions.addEventListener('click', (e) => {
				    e.stopPropagation();
				  });
			</script>
				
			<p>Tシャツに入れつ文字(下段)</p>
			<input type="text" id="lower-text-input">
			
			<p>上段文字のフォントサイズ<input type="number">pt</p>
			<p>下段文字のサイズ<input type="number">pt</p>
			<p>文字の縦位置<input type="number">%</p>
			<p>文字の横位置<input type="number">%</p>
		</div>
			
		<!-- Tシャツ表示 -->
		<div id="view-design">
		</div>
		<!-- 注文枚数、注文確定ボタン -->
		<div id="order-submit">
			<h2>注文枚数 : <input type="text">枚 <input type=submit value="注文確定"></h2>
		</div>
		</form>
	</div>
	
	
	<!------注文履歴画面------>
	<div class="tab" id="tab2">
		<h1 style="text-align:left;">注文履歴画面</h1>
		<table id="history-table">
			<tr>
			<th>注文番号</th>
			<th>注文日</th>
			<th>枚数</th>
			<th>デザイン</th>
			</tr>
			<!-- 表のHTMLが入った変数を入れる -->
		</table>
	</div>
</div>

<script src="scripts/designChange.js"></script>
</body>
</html> 