/*タブとページとログインユーザーの表示の取得*/
 const tabs = document.getElementById('tab-control').getElementsByTagName('a');
 const pages = document.getElementById("tab-body").getElementsByClassName("tab");
 const displayUser = document.getElementsByClassName("display-user");
 
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
 function changeTab() {
	for(const tab of tabs) {
		tab.style.display = "";
	}
	for(const disp of displayUser) {
		disp.style.display = "";
	}

	/*href属性値からidを抜き出す*/
	const targetId = this.href.substring(this.href.indexOf("#")+1, this.href.length);

	/*指定ページの表示*/
	for(let i = 0; i < pages.length; i++){
		if(pages[i].id != targetId) {
			pages[i].style.display = "none";
		}else{
			pages[i].style.display = "block";
		}
	}
	

	
	//ベースカラーの選択も行う
	ChangeBaseColor();
	
	/*ページが遷移しないようにfalseを返す*/
	return false;
}

for(let i=0; i<tabs.length; i++){
	tabs[i].onclick = changeTab;
}

/*最初は先頭のタブを選択*/
//tabs[1].onclick();

/*ベースカラー変更*/
function ChangeBaseColor(){
	let selectedColor = document.getElementById("base-color").value;
	const viewDesign = document.getElementById("view-design");
	
	viewDesign.innerHTML= "<img src=\"image/" + selectedColor + ".png\" alt=\"Tシャツの画像\" style=\"height:500px; width:500px;\">";
}