


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


</script>
</head>
<body>
<div style=" width:100%; height:547px;  background-color:#F2F2F2; ">
<div id="emptyOrErrorMsg"></div><div style=" height:43px; line-height: 43px; text-align: center; left:0; background-color:#FB6F2C" >
<img id="backButton" name ="backButton" src= "/images/back.png" onclick="跳回到上个;" style=" width: 12px; ">
 <span id="titleTextView" name ="titleTextView" style="  font-size: 16px; color: #C58768;  ">可加办套餐详情</span>
  </div>
<div style=" height:489px; line-height: 489px; text-align: center; left:0; background-color:#F2F2F2" >
<div style=" height:177px; line-height: 177px; text-align: center; left:0; background-color:#FFFFFF" >
<div style=" height:26px; line-height: 26px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="part1TitleTextView" name ="part1TitleTextView" style="  font-size: 16px; color: #2A2A2A;  ">套餐信息</span>
  </div>
<div style=" height:26px; line-height: 26px; text-align: center; left:0; background-color:背景颜色" >
 <span id="packageNameTitleTextView" name ="packageNameTitleTextView" style="  font-size: 16px; color: #AEAEAE;  ">套餐名称</span>
 <span id="packageNameTitleTextViewValueTextView" name ="packageNameTitleTextViewValueTextView" style="  font-size: 16px; color: #212121;  ">2013</span>
  </div>
<div style=" height:27px; line-height: 27px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="feePeriodTextView" name ="feePeriodTextView" style="  font-size: 16px; color: #AEAEAE;  ">周期</span>
 <span id="feePeriodValueTextView" name ="feePeriodValueTextView" style="  font-size: 16px; color: #212121;  ">6个月</span>
  </div>
<div style=" height:24px; line-height: 24px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="feeTitleTextView" name ="feeTitleTextView" style="  font-size: 16px; color: #AEAEAE;  ">价格</span>
 <span id="feeValueTextView" name ="feeValueTextView" style="  font-size: 16px; color: #212121;  ">333</span>
  </div>
<div style=" height:25px; line-height: 25px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="areaTitleTextView" name ="areaTitleTextView" style="  font-size: 16px; color: #AEAEAE;  ">服务区域</span>
 <span id="areaValueTextView" name ="areaValueTextView" style="  font-size: 16px; color: #212121;  ">广州北京</span>
  </div>
  </div>
<div style=" height:73px; line-height: 73px; text-align: center; left:0; background-color:#FFFFFF" >
<div style=" height:29px; line-height: 29px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="feeModeTitleTextView" name ="feeModeTitleTextView" style="  font-size: 16px; color: #AEAEAE;  ">收费模式</span>
 <span id="feeModeValueTextView" name ="feeModeValueTextView" style="  font-size: 16px; color: #212121;  ">先付款</span>
  </div>
<div style=" height:28px; line-height: 28px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="countModeTitleTextView" name ="countModeTitleTextView" style="  font-size: 16px; color: #AEAEAE;  ">累加模式</span>
 <span id="countModeTitleTextViewValueTextView" name ="countModeTitleTextViewValueTextView" style="  font-size: 16px; color: #212121;  ">按次</span>
  </div>
  </div>
<div style=" height:67px; line-height: 67px; text-align: center; left:0; background-color:#FFFFFF" >
<div style=" height:24px; line-height: 24px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="part3TitleTextView" name ="part3TitleTextView" style="  font-size: 16px; color: #212121;  ">绑定信息</span>
  </div>
<div style=" height:26px; line-height: 26px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="maxBindNumTitleTextView" name ="maxBindNumTitleTextView" style="  font-size: 16px; color: #AEAEAE;  ">最大绑定数量</span>
 <span id="maxBindNumValueTextView" name ="maxBindNumValueTextView" style="  font-size: 16px; color: #212121;  ">10</span>
 <span id="bindedNumTitleTextView" name ="bindedNumTitleTextView" style="  font-size: 16px; color: #AEAEAE;  ">已绑</span>
 <span id="bindedNumValueTextView" name ="bindedNumValueTextView" style="  font-size: 16px; color: #212121;  ">2</span>
<a href="#" id="newBindButton" name ="newBindButton"  onclick="跳到"  style="text-align: center; height:2px; line-height: 2px; border-radius: 8px; color:#FB6F2C; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >新绑定</a> 
  </div>
  </div>
<div style=" height:25px; line-height: 25px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="cartypeTextView" name ="cartypeTextView" style="  font-size: 16px; color: #AEAEAE;  ">小型汽车</span>
 <span id="carNumTextView" name ="carNumTextView" style="  font-size: 16px; color: #212121;  ">粤c132</span>
 <span id="carMsgTextView" name ="carMsgTextView" style="  font-size: 16px; color: #AEAEAE;  ">小树枝</span>
<a href="#" id="deleteButton" name ="deleteButton"  onclick="发请求"  style="text-align: center; height:11px; line-height: 11px; border-radius: 8px; color:#212121; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >删除</a> 
  </div>
<div style=" height:33px; line-height: 33px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="emptyBindTextView" name ="emptyBindTextView" style="  font-size: 16px; color: #212121;  ">暂未绑定车辆</span>
  </div>
<div style=" height:55px; line-height: 55px; text-align: center; left:0; background-color:#FFFFFF" >
<div style=" height:17px; line-height: 17px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="singleTitleTextView" name ="singleTitleTextView" style="  font-size: 16px; color: #212121;  ">简介</span>
  </div>
<div style=" height:24px; line-height: 24px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="singleMsgValueTextView" name ="singleMsgValueTextView" style="  font-size: 16px; color: #212121;  ">safdsf</span>
  </div>
  </div>
  </div>
  </div>
</body>
</html>

