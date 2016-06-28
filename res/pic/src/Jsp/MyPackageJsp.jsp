


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


//列表 
			var stop=false;
			$(window).scroll(function(){
			    var totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
			    if($(document).height() <= totalheight){
			        if(stop==true){
			            stop=false;
			            query_ajx(1);//下一页
			        }
			    }
			});
			$(document).ready(function() {
				//查询
				query_ajx(0);//首页
			});
			var currentCount = 0; //当前页数量
			var only_one_sub = true; //防重复提交
			function query_ajx(flg){//0首页  1下一页 
				  
			    if(!only_one_sub){
			        return;//防重复提交
			    }  
			    only_one_sub = false;//防重复提交
			   
			    if(flg == 0){//0首页  1下一页 
			    	$("#page_code").val(1);			    	
			    }		    
			   
			    currentCount = 0;
			    var flag = $("#flag").val();
			    var my_msg = show_hide_msg("页面加载中...");
			    $("#emptyOrErrorMsg").html("");
			    $.ajax({  
			        url:'/companyname/projectname/MyPackageAction!queryAddressList.do?',  
			        data: { "page_code": $("#page_code").val()} , //请求参数 
			        type:"POST",
			        cache:false, 
			        dataType:'json', //预期服务器返回的数据类型  
			        error: function() {
			        	only_one_sub = true;  ////防重复提交
			        	alert_msg("查询出错了!!!"); 
			   			$("#listSpace").html(""); 
			   			$("#emptyOrErrorMsg").html("");
			   			my_msg.close();
			   			currentCount = 0;
			        },  
			        success:function(data){
			          my_msg.close();
			        	if (data != null){
			        		var msg = data.errorMsg;//data为map  errorMsg为key
			     		    if(msg == "success"){
			     		    	
			         		    var dataList = data.dataList;
			         		    currentCount = dataList.length;
				        		if(flg == 0){//0首页  1下一页 
				        			 $("#listSpace").html("");				    	
				     		    }
				            	var html = "";
								for(var i=0;i<listData.length;i++){



 html+='<span id="packageStatusTextView" name ="packageStatusTextView" style="  font-size: 16px; color: #3E91C3;  ">未启用</span>'
 html+='<span id="packageNameTextView" name ="packageNameTextView" style="  font-size: 16px; color: #1D1D1D;  ">2013秋冬</span>'
 html+='<span id="dateTextView" name ="dateTextView" style="  font-size: 16px; color: #A8A8A8;  ">有效期:</span>'
 html+='<span id="startDateTextView" name ="startDateTextView" style="  font-size: 16px; color: #575757;  ">2013</span>'
 html+='<span id="hengTextView" name ="hengTextView" style="  font-size: 16px; color: #575757;  ">-</span>'
 html+='<span id="endDateTextView" name ="endDateTextView" style="  font-size: 16px; color: #575757;  ">2014</span>'
;
								}
								$("#page_code").val(Number($("#page_code").val()) + 1);		
				
								if(dataList.length >= 10){
					        		stop = true;//到底部时加载判断
					        	} 
					        	if(dataList.length == 0){
			          				$("#emptyOrErrorMsg").html('<br><br><br><br><div style="text-align:center;">暂无地址信息</div>');
				            	}
					        	$(html).appendTo($("#listSpace"));
				        		
						   		only_one_sub = true;  ////防重复提交
				        	}else{
				        		only_one_sub = true;  ////防重复提交
				       			$("#listSpace").html("");  
				       			alert_msg(msg);
				       			$("#emptyOrErrorMsg").html("");
				        	}
			        	}
			        }  
			    });
			}



//js取request值  var contentWidth = <s:property value="#request.cut_img_content_info.contentWidth"/>;
//html取request值  <input type="hidden"  name="busiNo" id="busiNo" value="<s:property value="#request.busiNo"/>" />


</script>
</head>
<body>
<div style=" width:100%; height:544px;  background-color:#F2F2F2; ">
<div id="emptyOrErrorMsg"></div><div style=" height:41px; line-height: 41px; text-align: center; left:0; background-color:#FB6F2C" >
<img id="backButton" name ="backButton" src= "/images/back.png" onclick="跳回到上个;" style=" width: 12px; ">
 <span id="titleTextView" name ="titleTextView" style="  font-size: 16px; color: #FFFFFF;  ">我的套餐</span>
<a href="#" id="addPackageButton" name ="addPackageButton"  onclick="跳到"  style="text-align: center; height:3px; line-height: 3px; border-radius: 8px; color:#FFFFFF; background-color:#FB6F2C;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >添加套餐</a> 
  </div>
<div style=" height:43px; line-height: 43px; text-align: center; left:0; background-color:#FFFFFF" >
<a href="#" id="allButton" name ="allButton"  onclick="发请求"  style="text-align: center; height:11px; line-height: 11px; border-radius: 8px; color:#BC8660; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >全部</a> 
<a href="#" id="useButton" name ="useButton"  onclick="发请求"  style="text-align: center; height:10px; line-height: 10px; border-radius: 8px; color:#1D1D1D; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >有效</a> 
<a href="#" id="unuseButton" name ="unuseButton"  onclick="发请求"  style="text-align: center; height:7px; line-height: 7px; border-radius: 8px; color:#1D1D1D; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >过期</a> 
  </div>
<div id="listSpace"></div>
<input type="hidden"  name="page_code" id="page_code" value="1" />
<input type="hidden"  name="page_num" id="page_num" value="10" />
  </div>
</body>
</html>


