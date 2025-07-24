/*タブとページとログインユーザーの表示の取得*/
 const aTabs = document.getElementById('tab-control').getElementsByTagName('a');
 const viewText = document.getElementById("viewText");
 const pages = document.getElementById("tab-body").getElementsByClassName("tab");
 const displayUser = document.getElementsByClassName("display-user");

//ページにデザインを保存するための変数
///////////////////////////////////////
var successUserId;
var orderCount;

var baseColor;

var text1;
var text1Color;
var text1Size;

var text2;
var text2Color;
var text2Size;

var verticalPosition;
var sidePosition;
////////////////////////////////////////

/*ベースカラー変更*/
function ChangeBaseColor(){
	baseColor = document.getElementById("base-color").value;
	const viewDesign = document.querySelectorAll(".view-design #view-T-shirts")[0];
	viewDesign.innerHTML= "<img src=\"image/" + baseColor + ".png\" alt=\"Tシャツの画像\" style=\"height:500px; width:500px;\">";
}


function buy(){
	if(confirm("注文を確定してよろしいですか。")){
	orderCount = document.getElementsByName("order-count")[0];
	console.log(orderCount);
	
	const params = new URLSearchParams();
	params.append("user_id",successUserId);
	params.append("order_count",orderCount);
	params.append("base_color",baseColor);
	params.append("text1",text1);
	params.append("text1_size",text1Size);
	params.append("text1_font_color",text1Color);
	params.append("text2",text2);
	params.append("text2_size",text2Size);
	params.append("text2_font_color",text2Color);
	params.append("vertival_position",verticalPosition);
	params.append("side_position",sidePosition);
	
	fetch("./buyServlet",{
	method:"POST",
	headers:{
		"Content-Type":"application/x-www-form-urlencoded"
	},
	body:params.toString()
	}).then(response => {
		if(!response.ok){
			changeToOrderHistory();
		}else{
			alert("注文の確定に失敗しました。");
		}
		
	});
	};	
}

//デザイン変更時の変数
function ChangeDesign(){
	text1 = document.getElementById("upper-text-input").value;
//	text1Color = document.getElementsByName("text1_font_color")[0].value;
	text1Color = "rgb(255,0,0)";
	text1Size = document.getElementsByName("text1_size")[0].value;
	
	text2 = document.getElementById("lower-text-input").value;
	//text2Color = document.getElementsByName("text2_font_color")[0].value;
	text2Color = "rgb(0,255,0)";
	text2Size = document.getElementsByName("text2_size")[0].value;
	
	verticalPosition = document.getElementsByName("vertical_position")[0].value;
	sidePosition = document.getElementsByName("side_position")[0].value;
	
	const upperText = document.querySelector("#view-upper-text"); 
	console.log(upperText);
	upperText.innerHTML = `<p style="font-size:${text1Size}pt; color:${text1Color};">${text1}</p>`;

	const lowerText = document.querySelector("#view-lower-text");
	console.log(lowerText);
	lowerText.innerHTML =  `<p style="font-size:${text2Size}pt; color:${text2Color};">${text2}</p>`;
	
	const viewText = document.querySelector("#view-text");
	let verticalTop = 480 - (verticalPosition * 1.35);
	let verticalHeight = 300 - (-verticalPosition * 2);
	
	viewText.setAttribute("style", 
  "position: absolute; " +
  "top: " + verticalTop + "px; left: 270px; " +
  "transform: translate(-50%, -50%); " +
  "width: 400px; height: " + verticalHeight + "px; " +  // ← 固定サイズ追加
  "overflow: hidden;" +              // ← 内容がはみ出す場合に切る
  "white-space: nowrap;"
);


}

function designReset(){
	if(confirm("現在作成中のデザインをリセットします。よろしいですか？")){
	
	document.getElementById("upper-text-input").value = "";
//	text1Color = document.getElementsByName("text1_font_color")[0].value;
	text1Color = "rgb(255,0,0)";
	document.getElementsByName("text1_size")[0].value = 24;
	
	document.getElementById("lower-text-input").value = "";
	//text2Color = document.getElementsByName("text2_font_color")[0].value;
	text2Color = "rgb(0,255,0)";
	document.getElementsByName("text2_size")[0].value = 24;
	
	document.getElementsByName("vertical_position")[0].value = 0;
	document.getElementsByName("side_position")[0].value = 0;
	ChangeDesign();
	}
}

//ユーザー情報の取得
function getUser(){
		console.log("getUser()始まった");
		const id = document.getElementById("input-id").value;
		console.log(id);
		const pass = document.getElementById("input-password").value;
		console.log(pass);
		fetch("./loginServlet2?ID=" + id + "&pass=" + pass)
		.then(response => response.json())
		.then(json => {
			const company = document.getElementById("display-company");
			const user = document.getElementById("display-user");
			company.innerText = "会社名：" + json.userCompany;
			user.innerText = "ユーザー：" + json.userName;
			changeTab("tab1");
			setUserID(id);
			Filter()
				//ベースカラーの選択も行う
			ChangeBaseColor();
	
		})
}

//色の取得
let colorMap = new Map();
const colors = [];
function getColor(){
	console.log("getColor()始まった");
	fetch("./getFontColorServlet")
		.then(response => response.json())
		.then(json => {
			for(const color of json){
				const RGB = color.FontColor_R + "," + color.FontColor_G + "," + color.FontColor_B;
				colorMap.set(color.FontColor_Id,RGB);
			}
			//カラーパレットへセット
			const colorPalette = document.getElementsByClassName("color-palette");
			const selectTag = [colorPalette[0],colorPalette[1]];
			for(const rgb of colorMap.values()){
				for(const select of selectTag){
				const color = document.createElement("option");
				color.text = "■";
				color.value = rgb;
				color.style.color = "rgb(" + rgb + ")";
				select.appendChild(color);
			};
		};
	})
}


function changeToOrderHistory(){
	try{
	fetch("./orderHistoryServlet?ID=" + userID)
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
  
  if(tabId === "tab1" || tabId === "tab2"){
		//ユーザーとタブを表示
	for(const tab of aTabs) {
		tab.style.display = "";
	}
	for(const disp of displayUser) {
		disp.style.display = "";
	}
}
  
}



//画面ロード時の初期設定
function load(){
	
	getColor();
	
	//ユーザーとタブの表示を消す
	for(const tab of aTabs) {
		tab.style.display = "none";
	}
	for(const disp of displayUser) {
		disp.style.display = "none";
	}
	
	changeTab("login-tab");
	

	//ログインユーザーの表示
	console.log("実行されたよ");
//	orderCount.setAttribute("value", userID);
}

