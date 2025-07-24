var userID = null;

function moveCheck(){
	const sec  = 1800;
	const events = ['click', 'keydown', 'mousemove', 'scroll']
	let timeoutId;
	
	function setTimer(){
		timeoutId = setTimeout(logout, sec * 1000);
	}
	
	function resetTimer(){
		clearTimeout(timeoutId);
		setTimer();
	}
	
	function setEvents(func){
		let len = events.length;
		while(len--){
			document.addEventListener(events[len], func, false);
		}
	}
	
	function logout(){
		userID = null;
		window.location.href = '../T-ShirtsOrder.jsp';
	}
	
	setTimer();
	setEvents(resetTimer);
	
}

function setUserID(str){
	userID = str;
}

function Filter(){
	if(userID === null){
	for(const tab of aTabs) {
		tab.style.display = "none";
	}
	for(const disp of displayUser) {
		disp.style.display = "none";
	}
	
	changeTab("login-tab");
	}else{
		changeTab("tab1");
	}
}

