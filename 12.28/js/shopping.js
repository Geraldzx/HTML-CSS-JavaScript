function plus(indext){
	var num = document.getElementsByName("amount")[indext];
	let price = document.getElementsByName("price")[indext].value;
	//console.log(price);
	//console.log(num);
	num.value= ++num.value;
	
	document.getElementsByName("pay")[indext].innerHTML=price*num.value;
	money();
}

function minus(indext){
	var num = document.getElementsByName("amount")[indext];
	if(num.value<=1)
	    return;
	let price = document.getElementsByName("price")[indext].value;
	num.value= --num.value;
	document.getElementsByName("pay")[indext].innerHTML=price*num.value;
	money();
}
function money(){
	var no = document.getElementsByName("pay");
	var num=0;
	for(let i = 0 ; i < no.length ; i++){
		if(no[i].innerText){
			num+=parseFloat(no[i].innerText);
		}
	}
	document.getElementById("totalPrice").innerHTML=num;
}
window.onload=money();