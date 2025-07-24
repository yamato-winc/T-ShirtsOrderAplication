/*タブとページとログインユーザーの表示の取得*/
// const tabs = document.getElementById('tab-control').getElementsByTagName('a');
 const viewText = document.getElementById("viewText");
 const pages = document.getElementById("tab-body").getElementsByClassName("tab");
 const displayUser = document.getElementsByClassName("display-user");
 const orderCount = document.getElementsByName("user-id")[0];
 
 //ページ表時
 function viewLoginPage(){
	for(const page of pages) {
		page.style.display = "none";
	}
	for(const tab of tabs) {
		tab.style.display = "none";
	}
	for(const disp of displayUser) {
		disp.style.display = "none";
	}
	pages[0].style.display = "block";
}

 /*タブの切り替え処理*/
 /*function changeTab() {
	for(const tab of tabs) {
		tab.style.display = "";
	}
	for(const disp of displayUser) {
		disp.style.display = "";
	}

	/*href属性値からidを抜き出す*/
	/*if(this.href != undefined){
	const targetId = this.href.substring(this.href.indexOf("#")+1, this.href.length);

	/*指定ページの表示*/
/*	for(let i = 0; i < pages.length; i++){
		if(pages[i].id != targetId) {
			pages[i].style.display = "none";
		}else{
			pages[i].style.display = "block";
		}
	}
	
	/*ページが遷移しないようにfalseを返す*/
/*	return false;
}}
*/

for(let i=0; i<tabs.length; i++){
	tabs[i].onclick = changeTab;
}

/*最初は先頭のタブを選択*/
tabs[0].onclick();

/*ベースカラー変更*/
function ChangeBaseColor(){
	let selectedColor = document.getElementById("base-color").value;
	const viewDesign = document.getElementById("view-design");
	
	viewDesign.innerHTML= "<img src=\"image/" + selectedColor + ".png\" alt=\"Tシャツの画像\" style=\"height:500px; width:500px;\">";
}

//ページにデザインを保存するための変数
///////////////////////////////////////
let text1;
let text1Color;
let text1Size;

let text2;
let text2Color;
let text2Size;

let verticalPosition;
let sidePosition;
////////////////////////////////////////

//デザイン変更時の変数
function ChangeDesign(){
	text1 = document.getElementById("upper-text-input");
	text1Color = document.getElementByName("text1_font_color")[0].value;
	text1Size = document.getElementsByName("text1_size")[0].value;
	
	text2 = document.getElementById("lower-text-input");
	text2Color = document.getElementsByName("text2_font_color")[0].value;
	text2Size = document.getElementsByName("text2_size")[0].value;
	
	verticalPosition = document.getElementsByName("vertical_position");
	sidePosition = document.getElementsByName("side_position");
	
	const upperText = document.createElement("p");
	upperText.text = text1;
	upperText.style.color = text1Color;
	upperText.style.fontSize = text1Size;
	viewText.appendChild(upperText);
	
	const lowerText = document.createElement("p");
	lowerText.text = text2;
	lowerText.style.color = text2Color;
	lowerText.style.fontSize = text2Size;
	viewText.appendChild(lowerText);
	
}

//ユーザー情報の取得
function getUser(){
		console.log("getUser()始まった");
		fetch("loginServlet2")
		.then(response => response.json())
		.then(data => {
			const company = document.getElementById("display-company");
			const user = document.getElementById("display-user");
			company.innerText = "会社名：" + data["User_Company"];
			user.innerText = "ユーザー：" + data["User_Name"];
		})
}

//色の取得

//let colorJson = [];
//const colors = [];
//function getColor(){
	//console.log("getColor()始まった");
	//fetch("getFontColorServlet")
	//	.then(response => response.json())
		//.then(data => {
		//	colorJson = data;
	//		colors.push(data[]);
		//})
//}


//テストカラー
/*
const colorPalette = document.getElementsByClassName("color-palette");
const selectTag = [colorPalette[0],colorPalette[1]];
for(const colorCode of colors){
	for(const select of selectTag){
	const color = document.createElement("option");
	color.text = "■";
	color.value = colorCode;
	color.style.color = colorCode;
	select.appendChild(color);
};
};
*/

function changeToOrderHistory(){
	try{
	fetch("./orderHistoryServlet")
		.then(response => {
			if(!response.ok) throw new Error("通信エラー");
			return response.text();
		})
		.then(html => {
			document.getElementById("history-table").innerHTML = html;		
			changeTab("tab2");
		})
	}catch(error){
		console.error("エラー",error);
	}
}

function changeTab(tabId) {
  const tabs = document.querySelectorAll(".tab");
  tabs.forEach(tab => {
    tab.classList.remove("active");
  });
  const activeTab = document.getElementById(tabId);
  if (activeTab) {
    activeTab.classList.add("active");
  }
}


//画面ロード時の初期設定
function load(){
	//色の取得
//	getColor();
	
	changeTab("login-tab");
	
	//ベースカラーの選択も行う
	ChangeBaseColor();
	
	/*setUserID(request.getAttribute("userId"));*/
//	setUserID("Tanaka@example");
	
	//ログインユーザーの表示
//	getUser();
	console.log("実行されたよ");
//	orderCount.setAttribute("value", userID);
}