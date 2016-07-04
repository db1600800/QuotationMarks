


<html>
<head>
	<meta charset='utf-8'>
	<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<META Http-Equiv="Cache-Control" Content="no-cache"/>
	<META Http-Equiv="Pragma" Content="no-cache"/>
	<META Http-Equiv="Expires" Content="-1"/>
	<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
<script type="text/javascript"  id="myJs">

//js取request值  var contentWidth = <s:property value="#request.cut_img_content_info.contentWidth"/>;
//html取request值  <input type="hidden"  name="busiNo" id="busiNo" value="<s:property value="#request.busiNo"/>" />

//checkbox 
var setAddr = false;
function setDefaultAddr(){
	if(setAddr){
	setAddr = false;
	$("#weiPayCheckBox").attr("style"," background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;");
}else{
	setAddr = true;
	$("#weiPayCheckBox").attr("style","  background: url(images/checked.png) no-repeat 2px center;  padding-left: 35px; padding-top:0px; padding-bottom:0px;");
}
}

//checkbox 
var setAddr = false;
function setDefaultAddr(){
	if(setAddr){
	setAddr = false;
	$("#onlinePayCheckBox").attr("style"," background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;");
}else{
	setAddr = true;
	$("#onlinePayCheckBox").attr("style","  background: url(images/checked.png) no-repeat 2px center;  padding-left: 35px; padding-top:0px; padding-bottom:0px;");
}
}


</script>
</head>
<body>
<div style=" width:100%; height:546px;  background-color:#F1F4F2; ">
<div id="emptyOrErrorMsg"></div><div style=" height:44px; line-height: 44px; text-align: center; left:0; background-color:#F2620B" >
<img id="backButton" name ="backButton" src= "/images/back.png" onclick="null;" style=" width: 12px; ">
 <span id="titleTextView" name ="titleTextView" style="  font-size: 16px; color: #FFFFFF;  ">订单确认</span>
  </div>
<div style=" height:487px; line-height: 487px; text-align: center; left:0; background-color:#F1F4F2" >
<div style=" height:81px; line-height: 81px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="packageNameTextView" name ="packageNameTextView" style="  font-size: 16px; color: #222222;  ">业务包名</span>
 <span id="periodTextView" name ="periodTextView" style="  font-size: 16px; color: #858585;  ">6个月</span>
 <span id="arearTextView" name ="arearTextView" style="  font-size: 16px; color: #A5A5A5;  ">北京广州</span>
 <span id="feeModeTextView" name ="feeModeTextView" style="  font-size: 16px; color: #8D8D8D;  ">先付款</span>
 <span id="countModeTextView" name ="countModeTextView" style="  font-size: 16px; color: #A5A5A5;  ">按次数累加</span>
 <span id="feeTextView" name ="feeTextView" style="  font-size: 16px; color: #BB7E88;  ">245</span>
<div style=" height:25px; line-height: 25px; text-align: center; left:0; background-color:#FFF4EB" >
 <span id="dateTitleTextView" name ="dateTitleTextView" style="  font-size: 16px; color: #222222;  ">套餐生效日期</span>
<a href="#" id="beginDateButton" name ="beginDateButton"  onclick="日期选择器"  style="text-align: center; height:3px; line-height: 3px; border-radius: 8px; color:#222222; background-color:#FFF4EB;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >2013年</a> 
  </div>
  </div>
<div style=" height:40px; line-height: 40px; text-align: center; left:0; background-color:背景颜色" >
<div style=" height:33px; line-height: 33px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="totalfeeTextView" name ="totalfeeTextView" style="  font-size: 16px; color: #222222;  ">总计:</span>
  </div>
  </div>
<div style=" height:67px; line-height: 67px; text-align: center; left:0; background-color:#FFFFFF" >
<div style=" height:22px; line-height: 22px; text-align: center; left:0; background-color:#FFFFFF" >
<span id="weiPayCheckBox" style="  background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;"  onclick="setDefaultAddr()">微支付</span>  </div>
<div style=" height:27px; line-height: 27px; text-align: center; left:0; background-color:#FFFFFF" >
<span id="onlinePayCheckBox" style="  background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;"  onclick="setDefaultAddr()">银联在线</span>  </div>
  </div>
<div style=" height:40px; line-height: 40px; text-align: center; left:0; background-color:#FFFFFF" >
<a href="#" id="paynowButton" name ="paynowButton"  onclick="跳到"  style="text-align: center; height:13px; line-height: 13px; border-radius: 8px; color:#FFFFFF; background-color:#F2620B;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >支付</a> 
  </div>
  </div>
  </div>
</body>
</html>

