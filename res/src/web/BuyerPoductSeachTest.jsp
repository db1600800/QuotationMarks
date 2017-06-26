<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜品搜索查询</title>
<script
	type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script>
		
		
		$(document).on('ready', function() {
			var searchInp="?productName=&productType=";
			$("#searchIn").val(searchInp);
			var addInp="?";
			$("#addIn").val(addInp);
			var updateInp="?";
			$("#updateIn").val(updateInp);
			var deleteInp="?";
			$("#deleteIn").val(deleteInp);
		});
		
		
		function doSearch(){
		var searchInp=	$("#searchIn").val();
var requesturl='http://localhost:8080/m/query.do'+searchInp;
			
				$.ajax({
					type:'POST',
					url:'buyerpoductseach/query.do'+searchInp,
					data:{
					},
					success:function(result){
							var returndata = result.returnData;
var jsonstr=JSON.stringify(result); 
$("#pageRequest").html(requesturl);
$("#pageContent").html(jsonstr);
					},
					error : function() {
						alert("对不起，系统错误，请稍候重试！")
					}
				});
			
		}
		
		
		function doAdd(){
		var addInp=	$("#addIn").val();
var requesturl='http://localhost:8080/m/insert.do'+addInp;
			
				$.ajax({
					type:'POST',
					url:'buyerpoductseach/insert.do'+addInp,
					data:{
					},
					success:function(result){
							var returndata = result.returnData;
var jsonstr=JSON.stringify(result); 
$("#pageRequest").html(requesturl);
$("#pageContent").html(jsonstr);
					},
					error : function() {
						alert("对不起，系统错误，请稍候重试！")
					}
				});
			
		}
		
		
		function doUpdate(){
			var updateInp= $("#updateIn").val();
var requesturl='http://localhost:8080/m/update.do'+updateInp;
			
				$.ajax({
					type:'POST',
					url:'buyerpoductseach/update.do'+updateInp,
					data:{
					},
					success:function(result){
							var returndata = result.returnData;
var jsonstr=JSON.stringify(result); 
$("#pageRequest").html(requesturl);
$("#pageContent").html(jsonstr);
					},
					error : function() {
						alert("对不起，系统错误，请稍候重试！")
					}
				});
			
		}
		function doDelete(code){
		var 	deleteInp=$("#deleteIn").val();
var requesturl='http://localhost:8080/m/delete.do'+deleteInp;
			
				$.ajax({
					type:'POST',
					url:'buyerpoductseach/delete.do'+deleteInp,
					data:{
					},
					success:function(result){
							var returndata = result.returnData;
var jsonstr=JSON.stringify(result); 
$("#pageRequest").html(requesturl);
$("#pageContent").html(jsonstr);
					},
					error : function() {
						alert("对不起，系统错误，请稍候重试！")
					}
				});
			
		}
		
		
		
	</script>
</head>
<body>
	<div> 菜品搜索查询接口</div>
	<div style="padding-left:20px;margin-bottom:10px;" >
	<input type="hidden" id="thd_sys_id" name="thd_sys_id" value="" />
	查询urlPara：<input type="text" id="searchIn" style="margin-left:10px;width:800px;height:20px; " value=""/>
	<input type="button" value="查询" id="search" name = "search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;"  onclick="doSearch()">
	</div>
	
	<div style="padding-left:20px;margin-bottom:10px;" >
	<input type="hidden" id="thd_sys_id" name="thd_sys_id" value="" />
	新增urlPara：<input type="text" id="addIn" style="margin-left:10px;width:800px;height:20px; " value=""/>
	<input type="button" value="新增" id="search" name = "search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;"  onclick="doAdd()">
	</div>
	
	<div style="padding-left:20px;margin-bottom:10px;" >
	<input type="hidden" id="thd_sys_id" name="thd_sys_id" value="" />
	修改urlPara：<input type="text" id="updateIn" style="margin-left:10px;width:800px;height:20px; " value=""/>
	<input type="button" value="修改" id="search" name = "search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;"  onclick="doUpdate()">
	</div>
	<div style="padding-left:20px;margin-bottom:10px;" >
	<input type="hidden" id="thd_sys_id" name="thd_sys_id" value="" />
	删除urlPara：<input type="text" id="deleteIn" style="margin-left:10px;width:800px;height:20px; " value=""/>
	<input type="button" value="删除" id="search" name = "search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;"  onclick="doDelete()">
	</div>
<div> 请求Url</div>
<div id="pageRequest"></div>
	<div> 接口输出</div>
	<div id="pageContent"></div>
  </div>
	
</body>
</html>

