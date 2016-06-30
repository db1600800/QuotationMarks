


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
//checkbox 
var packageNameCheckBoxBool = false;
function setpackageNameCheckBoxBool(){
	if(packageNameCheckBoxBool){
	packageNameCheckBoxBool = false;
	$("#packageNameCheckBox").attr("style"," background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;");
}else{
	packageNameCheckBoxBool = true;
	$("#packageNameCheckBox").attr("style","  background: url(images/checked.png) no-repeat 2px center;  padding-left: 35px; padding-top:0px; padding-bottom:0px;");
}
}



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
			        url:'/companyname/projectname/MyCanAddPackageAction!queryAddressList.do?',  
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



html+='<div style=" height:33px; line-height: 33px; text-align: center; left:0; background-color:#FFFFFF" >'
html+='<span id="packageNameCheckBox" style="  background: url(images/check.png) no-repeat 2px center;  padding-left: 40px;  padding-top:0px; padding-bottom:0px;"  onclick="setDefaultAddr()">业务包名称</span>'
 html+='<span id="packageMoneyTextView" name ="packageMoneyTextView" style="  font-size: 16px; color: #B84752;  ">60</span>'
  html+='</div>'
html+='<div style=" height:29px; line-height: 29px; text-align: center; left:0; background-color:#FFFFFF" >'
 html+='<span id="packagePeriodTitleTextView" name ="packagePeriodTitleTextView" style="  font-size: 16px; color: #A4A4A4;  ">周期:</span>'
 html+='<span id="packagePeriodValueTextView" name ="packagePeriodValueTextView" style="  font-size: 16px; color: #2A2A2A;  ">6个月</span>'
  html+='</div>'
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
<div style=" width:100%; height:521px;  background-color:#F2F2F2; ">
<div id="emptyOrErrorMsg"></div><div style=" height:44px; line-height: 44px; text-align: center; left:0; background-color:#FB6F2C" >
 <span id="titleTextView" name ="titleTextView" style="  font-size: 16px; color: #FFFFFF;  ">可加办套餐</span>
<a href="#" id="myPackageButton" name ="myPackageButton"  onclick="跳到"  style="text-align: center; height:7px; line-height: 7px; border-radius: 8px; color:#FFFFFF; background-color:#FB6F2C;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >我的套餐</a> 
  </div>
<div style=" height:37px; line-height: 37px; text-align: center; left:0; background-color:#FFFFFF" >
<a href="#" id="priceUpButton" name ="priceUpButton"  onclick="通用选择器"  style="text-align: center; height:9px; line-height: 9px; border-radius: 8px; color:#212121; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >价格从高到低</a> 
 <imageView userInteractionEnabled="NO" contentMode="scaleToFill" horizontalHuggingPriority="251" verticalHuggingPriority="251" fixedFrame="YES" image="down.png" translatesAutoresizingMaskIntoConstraints="NO" id="wWz-fk-ocP">
 <rect key="frame" x="103" y="10" width="10" height="12"/>
 </imageView>
<a href="#" id="timeButton" name ="timeButton"  onclick="通用选择器"  style="text-align: center; height:7px; line-height: 7px; border-radius: 8px; color:#212121; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >3个月</a> 
 <imageView userInteractionEnabled="NO" contentMode="scaleToFill" horizontalHuggingPriority="251" verticalHuggingPriority="251" fixedFrame="YES" image="down.png" translatesAutoresizingMaskIntoConstraints="NO" id="lsP-yA-xDn">
 <rect key="frame" x="199" y="15" width="3" height="2"/>
 </imageView>
<a href="#" id="cityButton" name ="cityButton"  onclick="通用选择器"  style="text-align: center; height:8px; line-height: 8px; border-radius: 8px; color:#353434; background-color:#FFFFFF;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >全国</a> 
 <imageView userInteractionEnabled="NO" contentMode="scaleToFill" horizontalHuggingPriority="251" verticalHuggingPriority="251" fixedFrame="YES" image="down.png" translatesAutoresizingMaskIntoConstraints="NO" id="oij-33-vfr">
 <rect key="frame" x="282" y="15" width="2" height="2"/>
 </imageView>
  </div>
<div id="listSpace"></div>
<input type="hidden"  name="page_code" id="page_code" value="1" />
<input type="hidden"  name="page_num" id="page_num" value="10" />
<div style=" height:46px; line-height: 46px; text-align: center; left:0; background-color:#FFFFFF" >
 <span id="totalTitleTextView" name ="totalTitleTextView" style="  font-size: 16px; color: #353434;  ">总计:</span>
 <span id="totalValueTextView" name ="totalValueTextView" style="  font-size: 16px; color: #B84752;  ">123</span>
<a href="#" id="buyButton" name ="buyButton"  onclick="跳到"  style="text-align: center; height:15px; line-height: 15px; border-radius: 8px; color:#FFFFFF; background-color:#FB6F2C;  font-size: 16px ; margin:1px;  padding: 1px; text-decoration: none;" >办理</a> 
  </div>
  </div>
</body>
</html>


