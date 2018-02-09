


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
<script>
//js取request值  var contentWidth = <s:property value="#request.cut_img_content_info.contentWidth"/>;
//html取request值  <input type="hidden"  name="busiNo" id="busiNo" value="<s:property value="#request.busiNo"/>" />


</script>
</head>
<body>
<div id="emptyOrErrorMsg"></div>

<div id="bg1516530998675DivLayout_Horizon" style="display:flex;flex-direction:row;justify-content: space-between;align-items:center;" >
<span id="titleTextView" name ="titleTextView" >可加倍套餐</span>
<button class="ui-btn"  style="background-color:#FB6F2C;color:#FFFFFF;"  onclick="" >我的套餐</button>
  </div>


<div id="bg1516532032179DivLayout_Vertical" style="display:flex;flex-direction:column;" >

<div id="bg1516532137298DivLayout_Horizon" style="display:flex;flex-direction:row;justify-content: space-between;align-items:center;" >
<div class="ui-row-flex ui-whitespace"  style="-webkit-box-pack:center; ">
<div>价格重高到低</div>
<div class="ui-select" style="margin-left: 6px;margin-right:6px;">
<select>
<option>2014</option>
<option selected>2015</option>
<option>2016</option>
</select>
</div>
</div>
<div class="ui-row-flex ui-whitespace"  style="-webkit-box-pack:center; ">
<div>３个月</div>
<div class="ui-select" style="margin-left: 6px;margin-right:6px;">
<select>
<option>2014</option>
<option selected>2015</option>
<option>2016</option>
</select>
</div>
</div>
<div class="ui-row-flex ui-whitespace"  style="-webkit-box-pack:center; ">
<div>全国</div>
<div class="ui-select" style="margin-left: 6px;margin-right:6px;">
<select>
<option>2014</option>
<option selected>2015</option>
<option>2016</option>
</select>
</div>
</div>
  </div>

  </div>

</body>
</html>

