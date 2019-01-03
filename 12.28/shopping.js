function plus(index){
	/*
	 获取当前index对应行的单价及数量
	 * */
	let price = document.getElementsByName("price")[index].value;  //获取单价
	let amount = document.getElementsByName("amount")[index];
	amount.value = ++amount.value;//累加数量
	console.log("单价：" + price + "，数量：" + amount.value)
	
	document.getElementById("price" + index).innerText = price * amount.value;
	
	total();
}

function minus(index){
	let price = document.getElementsByName("price")[index].value;  //获取单价
	let amount = document.getElementsByName("amount")[index];
	if(amount.value <= 1){
		return;  //阻止程序继续往下执行
	}
	
	amount.value = --amount.value;
	document.getElementById("price" + index).innerText = price * amount.value;
	
	total();
}


function total(){
	let arr = document.getElementsByName("pay");
	//console.log(arr);
	
	let sum = 0;
	for (var item in arr) {
		//console.log(arr[item]);
		if(arr[item].innerText){ //判断元素是否有innerText属性
			//console.log(arr[item].innerText)
			sum += parseFloat(arr[item].innerText);
		}
	}
	//console.log(sum);
	document.getElementById("totalPrice").innerHTML = "&nbsp;" + sum;
}

window.onload=total;