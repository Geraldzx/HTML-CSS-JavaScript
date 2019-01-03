function postt(){
	document.getElementById('post1').style.display='block';
}
var tou=new Array("tou01.jpg","tou02.jpg","tou03.jpg","tou04.jpg");
function conceal(){
	document.getElementById('post1').style.display='none';
	var newLi = document.createElement('li');
	var Num=Math.floor(Math.random()*4);  //随机获取头像
    var touDiv=document.createElement("div");   //创建头像所在的div节点
    var touImg=document.createElement("img");  //创建头像节点
    touImg.setAttribute("src","img/"+tou[Num]);  //增加头像路径
    touDiv.appendChild(touImg);
	var titleH1=document.createElement("h1"); //创建标题所在的标签h1
    var title=document.getElementById("title").value;  //获取标题
     titleH1.innerHTML=title;   //将标题内容放在h1标签中

    var newP=document.createElement("p"); //创建一个新的p节点元素
    var secName=document.createElement("span");
    var secSelect=document.getElementById("x").value;  //获取版块
    secName.innerHTML="版块："+secSelect;    //把版块内容放到span中
    var myDate=new Date();
    var currentDate=myDate.getFullYear()+"-"+parseInt(myDate.getMonth()+1)+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes();
    var timeSpan=document.createElement("span");
    timeSpan.innerHTML="发表时间："+currentDate;
    newP.appendChild(secName);   //在p节点中插入版块
    newP.appendChild(timeSpan);  //在p节点中插入发布时间；

    newLi.appendChild(touDiv);  //插入头像
    newLi.appendChild(titleH1);  //插入标题
    newLi.appendChild(newP);  //插入版块、时间内容
    var postList=document.getElementById("postList");
    postList.insertBefore(newLi,postList.firstChild);   //把当前内容插入到列表最前面

    document.getElementById("title").value="";    //标题设置为空
    document.getElementById("content").value="";  //内容设置为空

    document.getElementById("post1").style.display="none";
}
