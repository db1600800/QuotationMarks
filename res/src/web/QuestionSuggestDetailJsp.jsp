


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8'>
	<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta Http-Equiv="Cache-Control" Content="no-cache"/>
	<meta Http-Equiv="Pragma" Content="no-cache"/>
	<meta Http-Equiv="Expires" Content="-1"/>
  <link rel="stylesheet" href="<%=basePath%>css/frozen.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>lib/zepto.min.js"></script>
	<script src="<%=basePath%>js/frozen.js"></script>
<link href="<%=basePath%>js/calendar_control/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/calendar_control/mobiscroll.js" type="text/javascript"></script>
	
	<script
	src="<%=basePath%>js/calendar_control/mobiscroll_002.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>js/calendar_control/mobiscroll_004.js"
	type="text/javascript"></script>
<link
	href="<%=basePath%>js/calendar_control/mobiscroll_002.css"
	rel="stylesheet" type="text/css">
<script
	src="<%=basePath%>js/calendar_control/mobiscroll_003.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>js/calendar_control/mobiscroll_005.js"
	type="text/javascript"></script>
<link
	href="<%=basePath%>js/calendar_control/mobiscroll_003.css"
	rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/calendar_control/date.js"></script>
<link
	href="<%=basePath%>js/calendar_control/mobiscroll.css"
	rel="stylesheet" type="text/css">
<script
	src="<%=basePath%>js/calendar_control/mobiscroll.js"
	type="text/javascript"></script>
<script>
//js取request值  var contentWidth = <s:property value="#request.cut_img_content_info.contentWidth"/>;
//html取request值  <input type="hidden"  name="busiNo" id="busiNo" value="<s:property value="#request.busiNo"/>" />


</script>
</head>
<body id="body" class="h-body" style="display:flex;flex-direction: column;">
<div id="emptyOrErrorMsg"></div>

<div id="bg1528336111334DivLayout_Vertical" class="" style="background-color:#FFFFFF;display:flex;flex-direction:column;" >

<div id="bg1528336127595DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >
<span id="modelTextView" name ="modelTextView" style="font-size:20px;color:#4C4C4C;">需求管理模块</span>
  </div>


<div id="bg1528336390886DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >
<span id="applicationTextView" name ="applicationTextView" style="font-size:16px;color:#ABABAB;">企业管理平台</span>
<span id="lineTextView" name ="lineTextView" style="font-size:16px;color:#ABABAB;">|</span>
<span id="questionTypeTextView" name ="questionTypeTextView" style="font-size:16px;color:#ABABAB;">故障及问题</span>
  </div>

<span id="questionMsgTextView" name ="questionMsgTextView" style="font-size:12px;color:#838383;">需求豆腐饭d</span>
  </div>


<div id="bg1528338002810DivLayout_Vertical" class="" style="background-color:#FFFFFF;display:flex;flex-direction:column;" >

<div id="bg1528338038902DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >
<button  style="border-radius: 0.1rem;padding:6px;background-color:;color:#7878D3;"  onclick="" >guangldong</button>
  </div>


<div id="bg1528338151296DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >
<span id="feedBackTitleTextView" name ="feedBackTitleTextView" style="font-size:16px;color:#4C4C4C;">反馈人</span>
<span id="feedBackNameTextView" name ="feedBackNameTextView" style="font-size:16px;color:#4C4C4C;">张三</span>
<span id="line1TextView" name ="line1TextView" style="font-size:16px;color:#4C4C4C;">|</span>
<span id="FeedBackPhoneTextView" name ="FeedBackPhoneTextView" style="font-size:16px;color:#4C4C4C;">13343</span>
<span id="line3TextView" name ="line3TextView" style="font-size:16px;color:#4C4C4C;">|</span>
<span id="FeedBackOrgTextView" name ="FeedBackOrgTextView" style="font-size:16px;color:#4C4C4C;">广州</span>
  </div>


<div id="bg1528338623565DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >
<span id="FeedBackTimeTitleTextView" name ="FeedBackTimeTitleTextView" style="font-size:16px;color:#4C4C4C;">反馈时间:</span>
<span id="FeedBackTimeTextView" name ="FeedBackTimeTextView" style="font-size:16px;color:#4C4C4C;">2018</span>
  </div>

  </div>


<div id="bg1528338715610DivLayout_Vertical" class="" style="background-color:#FFFFFF;display:flex;flex-direction:column;border: 1px solid #ABABAB;" >

<div id="bg1528339089137DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content: space-between;align-items:center;" >
<input style=" border: 0.02rem solid #ddd;border-radius: 0.1rem; line-height: 9px; height: 9px;  font-size: 16px;"  type="text"  id="dealNameEditText" name="dealNameEditText" placeholder="处理人"><input style=" border: 0.02rem solid #ddd;border-radius: 0.1rem; line-height: 10px; height: 10px;  font-size: 16px;"  type="text"  id="dealPhoneEditText" name="dealPhoneEditText" placeholder="电话"><div  style="display:flex;">
<div>处理进度</div>
<div class="ui-select" style="border: 0.02rem solid #ddd;border-radius: 0.1rem; margin-left: 2px;margin-right:2px;">
<select id="dealProgressSelecter">
<option>2014</option>
<option selected>2015</option>
<option>2016</option>
</select>
</div>
</div>
  </div>

<input style=" border: 0.02rem solid #ddd;border-radius: 0.1rem; line-height: 12px; height: 12px;  font-size: 16px;"  type="text"  id="dealMsgEditText" name="dealMsgEditText" placeholder="处理意见">  </div>


<div id="bg1528339425914DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;" >
<button  style="border-radius: 0.1rem;padding:6px;background-color:#169BD5;color:#FFFFFF;flex:1;"  onclick="" >提交</button>
  </div>

</body>
<script type="text/javascript">
$(document).ready(function(){
     init();
});
</script> 

<script type="text/javascript">
//body宽高等于窗体
 var screenHeight=document.documentElement.clientHeight;
 var screenWidth=document.documentElement.clientWidth; 
var body = $(".h-body");
body.width(screenWidth + "px");
body.height(screenHeight + "px");
 </script> 

<script type="text/javascript">
//select选择
$("#dealProgressSelecter").change(function () {  
var s = $(this).children('option:selected').val();  
window.where.set("dealProgressSelecter",s);
 }); 
</script>

<script type="text/javascript">
//进入页面向后台取数据,初始化页面
 function init() {
$.ajax({
url:${pageContext.request.contextPath}/__,
type:'post',
dataType:'json',
async:true,
data:{where:window.where},
timeout:1000,
error:function(){
requestIng=false;
alert("ajax error");
}, 
success:function(rsObj)
{
requestIng=false;
var resultSize=rsObj.resultSize;
window.resultTotal=rsObj.resultTotal;
var resultData=rsObj.resultData;
if(resultSize>0){
for(var i=0;i<resultData.length;i++){ 
var itemHtml='';




itemHtml+='<div id="bg1528336111334DivLayout_Vertical" class="" style="background-color:#FFFFFF;display:flex;flex-direction:column;" >'

itemHtml+='<div id="bg1528336127595DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >'
itemHtml+='<span id="modelTextView" name ="modelTextView" style="font-size:20;color:#4C4C4C;">需求管理模块</span>'
  itemHtml+='</div>'


itemHtml+='<div id="bg1528336390886DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >'
itemHtml+='<span id="applicationTextView" name ="applicationTextView" style="font-size:16;color:#ABABAB;">企业管理平台</span>'
itemHtml+='<span id="lineTextView" name ="lineTextView" style="font-size:16;color:#ABABAB;">|</span>'
itemHtml+='<span id="questionTypeTextView" name ="questionTypeTextView" style="font-size:16;color:#ABABAB;">故障及问题</span>'
  itemHtml+='</div>'

itemHtml+='<span id="questionMsgTextView" name ="questionMsgTextView" style="font-size:12;color:#838383;">需求豆腐饭d</span>'
  itemHtml+='</div>'


itemHtml+='<div id="bg1528338002810DivLayout_Vertical" class="" style="background-color:#FFFFFF;display:flex;flex-direction:column;" >'

itemHtml+='<div id="bg1528338038902DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >'
itemHtml+='<button  style="padding:6px;background-color:;color:#7878D3;"  onclick="" >guangldong</button>'
  itemHtml+='</div>'


itemHtml+='<div id="bg1528338151296DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >'
itemHtml+='<span id="feedBackTitleTextView" name ="feedBackTitleTextView" style="font-size:16;color:#4C4C4C;">反馈人</span>'
itemHtml+='<span id="feedBackNameTextView" name ="feedBackNameTextView" style="font-size:16;color:#4C4C4C;">张三</span>'
itemHtml+='<span id="line1TextView" name ="line1TextView" style="font-size:16;color:#4C4C4C;">|</span>'
itemHtml+='<span id="FeedBackPhoneTextView" name ="FeedBackPhoneTextView" style="font-size:16;color:#4C4C4C;">13343</span>'
itemHtml+='<span id="line3TextView" name ="line3TextView" style="font-size:16;color:#4C4C4C;">|</span>'
itemHtml+='<span id="FeedBackOrgTextView" name ="FeedBackOrgTextView" style="font-size:16;color:#4C4C4C;">广州</span>'
  itemHtml+='</div>'


itemHtml+='<div id="bg1528338623565DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >'
itemHtml+='<span id="FeedBackTimeTitleTextView" name ="FeedBackTimeTitleTextView" style="font-size:16;color:#4C4C4C;">反馈时间:</span>'
itemHtml+='<span id="FeedBackTimeTextView" name ="FeedBackTimeTextView" style="font-size:16;color:#4C4C4C;">2018</span>'
  itemHtml+='</div>'

  itemHtml+='</div>'


itemHtml+='<div id="bg1528338715610DivLayout_Vertical" class="" style="background-color:#FFFFFF;display:flex;flex-direction:column;border: 1px solid #ABABAB;" >'

itemHtml+='<div id="bg1528339089137DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content: space-between;align-items:center;" >'
itemHtml+='<input style=" border: 0; line-height: 9px; height: 9px;  font-size: 16px;"  type="text"  id="dealNameEditText" name="dealNameEditText" placeholder="处理人">'itemHtml+='<input style=" border: 0; line-height: 10px; height: 10px;  font-size: 16px;"  type="text"  id="dealPhoneEditText" name="dealPhoneEditText" placeholder="电话">'itemHtml+='<div  style="display:flex;">'
itemHtml+='<div>处理进度</div>'
itemHtml+='<div class="ui-select" style="margin-left: 2px;margin-right:2px;">'
itemHtml+='<select id="dealProgressSelecter">'
itemHtml+='<option>2014</option>'
itemHtml+='<option selected>2015</option>'
itemHtml+='<option>2016</option>'
itemHtml+='</select>'
itemHtml+='</div>'
itemHtml+='</div>'
  itemHtml+='</div>'

itemHtml+='<input style=" border: 0; line-height: 12px; height: 12px;  font-size: 16px;"  type="text"  id="dealMsgEditText" name="dealMsgEditText" placeholder="处理意见">'  itemHtml+='</div>'


itemHtml+='<div id="bg1528339425914DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;" >'
itemHtml+='<button  style="padding:6px;background-color:#169BD5;color:#FFFFFF;flex:1;"  onclick="" >提交</button>'
  itemHtml+='</div>'

 $(".body").append(itemHtml); 
 }
 }
});
}
</script>
</html>

