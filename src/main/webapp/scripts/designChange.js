/*タブとページの取得*/
 const tabs = document.getElementById('tab-control').getElementsByTagName('a');
 const pages = document.getElementById("tab-body").getElementsByClassName("tab");
 
 /*タブの切り替え処理*/
 function changeTab() {
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
	
	/*ページが遷移しないようにfalseを返す*/
	return false;
}

for(let i=0; i<tabs.length; i++){
	tabs[i].onclick = changeTab;
}

/*最初は先頭のタブを選択*/
tabs[0].onclick();

/*ベースカラー格納用変数*/
let baseColor;

/*ベースカラー変更*/
function ChangeBaseColor(){
	baseColor = document.getElementById("base-color").value;
	//デバッグ用
	console.log(baseColor);
}