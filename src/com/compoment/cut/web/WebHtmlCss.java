package com.compoment.cut.web;

public class WebHtmlCss {
	
	public void create()
	{
		String m="";
		m+="<%@ page language=\"java\" import=\"java.util.*\" contentType=\"text/html;charset=UTF-8\" isELIgnored=\"false\" %>\n";
		m+="<%@ taglib prefix=\"s\" uri=\"/struts-tags\" %>   \n";
		m+="<%@page import=\"java.util.*\"%>\n";


		m+="<!DOCTYPE HTML>\n";
		m+="<html>\n";
		m+="<head>\n";
		m+="<meta charset='utf-8'>\n";
		m+="<meta name=\"viewport\" content=\"width=device-width,initial-scale=1, maximum-scale=1, \">\n";
		m+="<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n";
		m+="<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n";
		m+="<title>消息列表</title>\n";
		m+="<li style=\" list-style: none \"nk href=\"/chinapost/jiyouwx/css/css.css\" rel=\"stylesheet\" type=\"text/css\">\n";

		m+="<script type=\"text/javascript\" src=\"/chinapost/jiyouwx/js/jquery-1.10.1.min.js\">\n";
		m+="</script>\n";
		m+="<script type=\"text/javascript\" src=\"/chinapost/jiyouwx/js/swiper-2.1.min.js\">\n";
		m+="</script>\n";
		m+="<script>\n";
		m+="  function queryDetail(param){\n";
		m+="	  var my_msg = show_hide_msg(\"页面跳转中，请稍候...\");\n";
		m+="	  window.location.href=\"/chinapost/jiyouwx/news/newsDetail.jsp?id=\"+param;\n";
		m+="	  my_msg = null;\n";
		m+="  }\n";
		m+=" \n";
		m+="  var stop=true;\n";
		m+="  $(window).scroll(function(){\n";
		m+="      var totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());\n";
		m+="      if($(document).height() <= totalheight){\n";
		m+="          if(stop==true){\n";
		m+="              stop=false;\n";
		m+="              query_ajx(1);\n";
		m+="          }\n";
		m+="      }\n";
		m+="  });\n";

		m+="  $(document).ready(function() {\n";
		m+="	//查询订单列表\n";
		m+="	query_ajx(0);\n";
		m+="  });\n";

		m+="  var only_one_sub = true; //防重复提交\n";
		m+="  function query_ajx(flg){ \n";
		m+="	  \n";
		m+="      if(!only_one_sub){\n";
		m+="          return;//防重复提交\n";
		m+="      }  \n";
		m+="      only_one_sub = false;//防重复提交\n";
		m+="     \n";
		m+="      if(flg == 0){//按查询时\n";
		m+="      	$(\"#page_code\").val(1);			    	\n";
		m+="      }		    \n";
		m+="      var my_msg = show_hide_msg(\"页面加载中...\");\n";
		m+="      $.ajax({  \n";
		m+="          url:'/jiyou/wx/MemberAction!queryNews.do?',  \n";
		m+="          data: { \"page_code\": $(\"#page_code\").val(), \"page_num\": $(\"#page_num\").val()} ,  \n";
		m+="          type:\"POST\",\n";
		m+="          cache:false, \n";
		m+="          dataType:'json', //预期服务器返回的数据类型  \n";
		m+="          error: function() {\n";
		m+="          	only_one_sub = true;  ////防重复提交\n";
		m+="     			alert_msg(\"查询出错了!!!\"); \n";
		m+="     			$(\"#form1_pageset\").html(\"\"); \n";
		m+="     			$(\"#noMsg\").html(\"\");\n";
		m+="     			 my_msg.close();\n";
		m+="          },  \n";
		m+="          success:function(data){\n";
		m+="        	  my_msg.close();\n";
		m+="            $(\"#noMsg\").html(\"\");\n";
		m+="          	if (data != null){\n";
		m+="          		var msg = data.result;\n";
		m+="       		    if(msg == \"success\"){\n";
		m+="       		    	\n";
		m+="           		    var rlt = data.rltdata.redo;\n";
		m+="  	        		if(flg == 0){//按查询时\n";
		m+="  	        			 $(\"#form1_pageset\").html(\"\");				    	\n";
		m+="  	     		    }\n";
		m+="  	            	var opt = \"\";\n";
		m+="  					for(var i=0;i<rlt.length;i++){\n";
		m+="  						opt += '<div onclick=\"queryDetail(\''+rlt[i].infoID +'\')\" style=\" background-color:#fff; line-height:25px;\" >\n";

		m+="<dl style=\" background:url(../images/next.png) no-repeat right center #fff; background-size:10px; line-height:20px; position:relative;\" >';\n";

		m+="  						opt += '<dt>\n";
		m+="<span style=\" float:right\" >'+rlt[i].time+'</span>'+rlt[i].infoTitle+'</dt>';\n";

		m+="  						opt += '<dd style=\" padding-right:20px;\" >'+rlt[i].infoContentShort+'</dd>\n";

		m+="</dl>\n";
		m+="</div>';\n";
		m+="  					}\n";
		m+="  								\n";
		m+="  					var page_num = $(\"#page_num\").val();\n";
		m+="  					if(rlt.length >= Number(page_num)){\n";
		m+="	  					$(\"#page_code\").val(Number($(\"#page_code\").val()) + 1);		\n";
		m+="  		        		stop = true;//到底部时加载判断\n";
		m+="  		        	} else if(rlt.length > 0){\n";
		m+="  	  		        	stop = false;\n";
		m+="  		        	} else {\n";
		m+="  	  		        	stop = false;\n";
		m+="            			$(\"#noMsg\").html('<br>\n";
		m+="<br>\n";
		m+="<br>\n";
		m+="<br>\n";
		m+="<div style=\"text-align:center;\">暂无消息</div>');\n";
		m+="	            	}\n";
		m+="  		        	$(opt).appendTo($(\"#form1_pageset\"));\n";
		m+="  	        		\n";
		m+="  			   		only_one_sub = true;  ////防重复提交\n";
		m+="  	        	}else{\n";
		m+="  	        		only_one_sub = true;  ////防重复提交\n";
		m+="  	        		alert_msg(msg); \n";
		m+="  	       			$(\"#form1_pageset\").html(\"\");  \n";
		m+="  	       			$(\"#noMsg\").html(\"\");\n";
		m+="  	        	}\n";
		m+="          	}\n";
		m+="          }  \n";
		m+="      });\n";
		m+="  }\n";
		m+="</script>\n";
		m+="</head>\n";
		m+="<body style=\" font-size:12px; font-family:\"黑体\";; font-size: 14px; ; background-color: #f5f5f5; color: #333; line-height:20px; \">\n";



		m+="<header style=\" height: 42px; line-height: 42px; background-color: #fd5f28; font-size: 18px; color: #fff; text-align: center; z-index: 2; position: relative; width: 100%; position:fixed; top:0; left:0  \">\n";

		m+="<img style=\" width: 12px; position: absolute; left: 10px; top:10px; ; border:0; border: 0 \" src=\"/chinapost/jiyouwx/images/back.png\" onclick=\"window.location.href='/jiyou/wx/MemberAction!init.do';\" >消息列表</header>\n";



		m+="<div style=\" height:42px; width:100%\" >&nbsp;</div>\n";

		m+="	<form action=\"\" id=\"merch_news_action_form\" method=\"POST\">\n";
		m+="  <div style=\" width:100%; height:10px;\" >\n";

		m+="</div>\n";
		m+="  <ul style=\" margin-top:10px;\"  id=\"form1_pageset\">\n";

		m+="</ul>\n";
		m+="  <div id=\"noMsg\">\n";
		m+="</div>\n";
		m+="  \n";
		m+=" 	<input style=\"line-height: normal;; font-size:14px; font-size: 14px; \" type=\"hidden\"  name=\"page_code\" id=\"page_code\" value=\"1\" />\n";



		m+=" 	<input style=\"line-height: normal;; font-size:14px; font-size: 14px; \" type=\"hidden\"  name=\"page_num\" id=\"page_num\" value=\"10\" />\n";



		m+="	</form>\n";
		m+="  <jsp:include page=\"/chinapost/jiyouwx/global_error.jsp\">\n";
		m+="</jsp:include>\n";
		m+="</body>\n";
		m+="</html>\n";

	}

}
