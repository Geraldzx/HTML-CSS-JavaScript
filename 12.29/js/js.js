function plus(indext){   //加
	var amount = document.getElementsByName('amount')[indext];
	//console.log(amount);
	var price = document.getElementsByName('price')[indext];
	amount.value= ++amount.value;
	document.getElementsByName('pay')[indext].innerHTML=amount.value*price.value;
	money();
}
function minus(indext){   //加
	var amount = document.getElementsByName('amount')[indext];
	//console.log(amount);
	if(amount.value<=1)
	return;
	var price = document.getElementsByName('price')[indext];
	amount.value= --amount.value;
	document.getElementsByName('pay')[indext].innerHTML=amount.value*price.value;
	money();
}
function money(){
	var mn = document.getElementsByName('pay');
	var money=0;
	for (let i = 0 ; i < mn.length ; i ++) {
		if(mn[i].innerText)
		money+=parseFloat(mn[i].innerText);
	}
	//console.log(money);
	document.getElementById('money').innerText=money;
}
window.onload=money();
