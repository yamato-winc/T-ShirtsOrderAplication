/*タブとページとログインユーザーの表示の取得*/
 const aTabs = document.getElementById('tab-control').getElementsByTagName('a');
 const viewText = document.getElementById("viewText");
 const pages = document.getElementById("tab-body").getElementsByClassName("tab");
 const displayUser = document.getElementsByClassName("display-user");

//ページにデザインを保存するための変数
///////////////////////////////////////
var orderCount;

var baseColor;
let colorMap = new Map();

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

function getKeyByValue(rgb) {
	const value = rgb.replace(/^rgb\(|\)$/g, '');
	console.log("比較元" + value);
  for (let [key, val] of colorMap.entries()) {
    console.log("比較対象" + val);
    if (val === value) {
	console.log("結果" + key);
      return key;
    }
  }
  return null; // 見つからなかった場合
}

function buy(){
	console.log(verticalPosition + sidePosition);
	if(confirm("注文を確定してよろしいですか。")){
	orderCount = document.getElementsByName("order-count")[0].value;
	
	const params = new URLSearchParams();
	params.append("user_id",userID);
	params.append("order_count",orderCount);
	params.append("base_color",baseColor);
	params.append("text1",text1);
	params.append("text1_size",text1Size);
	params.append("text1_font_color",getKeyByValue(text1Color));
	params.append("text2",text2);
	params.append("text2_size",text2Size);
	params.append("text2_font_color",getKeyByValue(text2Color));
	params.append("vertical_position",verticalPosition);
	params.append("side_position",sidePosition);
	
	fetch("./buyServlet",{
	method:"POST",
	headers:{
		"Content-Type":"application/x-www-form-urlencoded"
	},
	body:params.toString()
	}).then(response => {
		if(response.ok){
			changeToOrderHistory();
		}else{
			alert("注文の確定に失敗しました。");
		}
		
	});
	};	
}

//デザイン変更
function ChangeDesign(){
    text1 = document.getElementById("upper-text-input").value;
    const text1ColorSelect = document.getElementsByName("text1_font_color")[0];
    text1Color = text1ColorSelect.value ? "rgb(" + text1ColorSelect.value + ")" : "rgb(0,0,0)";
    text1Size = document.getElementsByName("text1_size")[0].value;
    
    // 上段セレクトボックスの背景色を選択中の色に変更
    if (text1ColorSelect.value) {
        text1ColorSelect.style.backgroundColor = "rgb(" + text1ColorSelect.value + ")";
        // コントラストのため文字色も調整
        const rgbValues1 = text1ColorSelect.value.split(',').map(v => parseInt(v.trim()));
        const brightness1 = (rgbValues1[0] * 299 + rgbValues1[1] * 587 + rgbValues1[2] * 114) / 1000;
        text1ColorSelect.style.color = brightness1 > 128 ? "black" : "white";
    }
    
    text2 = document.getElementById("lower-text-input").value;
    const text2ColorSelect = document.getElementsByName("text2_font_color")[0];
    text2Color = text2ColorSelect.value ? "rgb(" + text2ColorSelect.value + ")" : "rgb(0,0,0)";
    text2Size = document.getElementsByName("text2_size")[0].value;
    
    // 下段セレクトボックスの背景色を選択中の色に変更
    if (text2ColorSelect.value) {
        text2ColorSelect.style.backgroundColor = "rgb(" + text2ColorSelect.value + ")";
        // コントラストのため文字色も調整
        const rgbValues2 = text2ColorSelect.value.split(',').map(v => parseInt(v.trim()));
        const brightness2 = (rgbValues2[0] * 299 + rgbValues2[1] * 587 + rgbValues2[2] * 114) / 1000;
        text2ColorSelect.style.color = brightness2 > 128 ? "black" : "white";
    }
    
    verticalPosition = document.getElementsByName("vertical_position")[0].value;
    sidePosition = document.getElementsByName("side_position")[0].value;
    
    const upperText = document.querySelector("#view-upper-text"); 
    const lowerText = document.querySelector("#view-lower-text");
    const viewText = document.querySelector("#view-text");

    // 上下のテキストを設定（空の場合は何も表示しない）
    if (text1.trim() !== "") {
        upperText.innerHTML = `<p style="font-size:${text1Size}pt; color:${text1Color};">${text1}</p>`;
    } else {
        upperText.innerHTML = "";
    }
    
    if (text2.trim() !== "") {
        lowerText.innerHTML = `<p style="font-size:${text2Size}pt; color:${text2Color};">${text2}</p>`;
    } else {
        lowerText.innerHTML = "";
    }
    
    // 文字の存在状況を確認
    const hasUpperText = text1.trim() !== "";
    const hasLowerText = text2.trim() !== "";
    
    // 文字間隔をサイズに応じて調整
    let textGap = 10;  // デフォルト間隔
    if (hasUpperText && hasLowerText) {
        // 両方の文字がある場合、文字サイズの平均に基づいて間隔を調整
        const avgSize = (Number(text1Size) + Number(text2Size)) / 2;
        textGap = Math.max(5, avgSize * 0.3);  // 最小5px、文字サイズの30%
    }
    
    // 縦方向の位置計算（制限なし）
    const basePositionY = 250;  // Tシャツの中央
    const moveAmountY = basePositionY - (Number(verticalPosition) * 2);  // 2px/unit
    
    // 横方向の位置計算（制限なし）
    const basePositionX = 270;  // デフォルトの左位置
    const moveAmountX = basePositionX + (Number(sidePosition) * 2);  // 2px/unit（プラスで右へ）
    
    console.log("上段:", hasUpperText, "下段:", hasLowerText, "縦位置:", verticalPosition, "横位置:", sidePosition, "文字間隔:", textGap, "Y:", moveAmountY, "X:", moveAmountX);
    
    viewText.setAttribute("style", 
        "position: absolute; " +
        "top: " + moveAmountY + "px; " +
        "left: " + moveAmountX + "px; " +
        "transform: translate(-50%, -50%); " +
        "width: 400px; " +
        "height: auto; " +
        "max-width: 500px; " +   // Tシャツの幅に制限
        "max-height: 500px; " +  // Tシャツの高さに制限
        "overflow: hidden; " +   // 枠外を見切る（縦横両方）
        "display: flex; " +
        "flex-direction: column; " +
        "align-items: center; " +
        "gap: " + textGap + "px; "  // 文字サイズに応じた間隔
    );
    
    // Tシャツエリアでもクリッピングを設定（より確実に）
    const viewDesign = document.querySelector(".view-design");
    if (viewDesign) {
        viewDesign.style.overflow = "hidden";
        viewDesign.style.position = "relative";  // 子要素のクリッピングのため
        viewDesign.style.width = "500px";       // 幅を明示的に指定
        viewDesign.style.height = "500px";      // 高さを明示的に指定
    }

    // 個別のテキストブロックのスタイル
    upperText.style.textAlign = "center";
    lowerText.style.textAlign = "center";
}

function designReset(){
	if(confirm("現在作成中のデザインをリセットします。よろしいですか？")){
	
	document.getElementById("upper-text-input").value = "";
	// セレクトボックスをリセット（最初のオプションを選択）
	const text1ColorSelect = document.getElementsByName("text1_font_color")[0];
	text1ColorSelect.selectedIndex = 0;
	text1ColorSelect.style.backgroundColor = "";  // 背景色をリセット
	text1ColorSelect.style.color = "";            // 文字色をリセット
	document.getElementsByName("text1_size")[0].value = 24;
	
	document.getElementById("lower-text-input").value = "";
	// セレクトボックスをリセット（最初のオプションを選択）
	const text2ColorSelect = document.getElementsByName("text2_font_color")[0];
	text2ColorSelect.selectedIndex = 0;
	text2ColorSelect.style.backgroundColor = "";  // 背景色をリセット
	text2ColorSelect.style.color = "";            // 文字色をリセット
	document.getElementsByName("text2_size")[0].value = 24;
	
	document.getElementsByName("vertical_position")[0].value = 0;
	document.getElementsByName("side_position")[0].value = 0;
	ChangeDesign();
	}
}


//ユーザー情報の取得
function getUser(){
		const id = document.getElementById("input-id").value;
		const pass = document.getElementById("input-password").value
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
function getColor(){
	fetch("./getFontColorServlet")
		.then(response => response.json())
		.then(json => {
			for(const color of json){
				const RGB = color.FontColor_R + "," + color.FontColor_G + "," + color.FontColor_B;
				colorMap.set(color.FontColor_Id,RGB);
			}
			console.log(colorMap);
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
	console.log(userID);
	fetch("./orderHistoryServlet?ID=" + userID)
		.then(response => {
			if(!response.ok) throw new Error("通信エラー");
			return response.text();
		})
		.then(html => {
			console.log(html);
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

