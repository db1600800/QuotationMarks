import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
/**
 * Servlet implementation class 
 */
@WebServlet(urlPatterns = "/Serverlet8010004", asyncSupported = true)
public class Serverlet8010004 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet8010004() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. start async
		final AsyncContext ctx = request.startAsync();
		parameter = ctx.getRequest().getParameter("parameter");



Gson gson = new Gson();
RequestParam8010004 bean = gson.fromJson(parameter, RequestParam8010004.class);
/** 套餐名称 备注:40*/
bean.D44_70_PACKETNAME;//String
/** 排序方式 备注:201：价格从高倒地02：价格从低到高*/
bean.D44_70_FIELD_SORTID;//String
/** 服务周期 备注:201：1个月02：3个月03：6个月04：12个月*/
bean.D44_70_CODE_TYPE;//String
/** 服务区域城市代号 备注:15*/
bean.D44_70_CITYCOE;//String
/**  备注:*/
bean.;//String
/** 页码 备注:*/
bean.D44_70_PAGENUM;//int
/** 页码大小 备注:*/
bean.D44_70_PAGECODE;//int
/**  备注:*/
bean.;//String
		// 2. set the timeout
		ctx.setTimeout(500);
		// 3. add listener
		ctx.addListener(new AsyncListener() {
			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				System.out.println("onTimeout...");
			}
			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				System.out.println("onStartAsync...");
			}
			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				System.out.println("onError...");
			}
			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				System.out.println("onComplete...");
			}
		});
		// 4. run a thread
		ctx.start(new Runnable() {
			@Override
			public void run() {
				try {
					ctx.getResponse().setContentType("application/json");
//000000 表示成功
Respond respond=new Respond();
respond.returnCode=;
ReturnData returnData=respond.new ReturnData();
respond.returnData=returnData;
Head head=returnData.new Head();
returnData.head=head;
head.ret_errcode=;
head.ret_msg=;
Body body=returnData.new Body();
returnData.body=body;



/**可办理套餐查询8010004*/
CacheRespondParam8010004 cacheRespondParam8010004=new CacheRespondParam8010004(); 
/** 总记录数 备注:*/
cacheRespondParam8010004.D44_70_MAXRECORD=;//int
/** 循环域结束 备注:*/
cacheRespondParam8010004.=;//String
/**  备注:*/
cacheRespondParam8010004.=;//String
/** 循环域结束 备注:*/
cacheRespondParam8010004.D44_70_RECORDNUM1=;//String


/** 循环域开始 备注:*/
cacheRespondParam8010004.D44_70_RECORDNUM=;//int
/** 业务包号 (数组)备注:3*/
cacheRespondParam8010004.D44_70_PACKETID=new String[]{};//String[]
/** 业务包名称 (数组)备注:40*/
cacheRespondParam8010004.D44_70_PACKETNAME=new String[]{};//String[]
/** 业务包描述 (数组)备注:100*/
cacheRespondParam8010004.D44_70_DESC=new String[]{};//String[]
/** 客户对象标志 (数组)备注:1*/
cacheRespondParam8010004.D44_70_CSTMPACK_OBJECT=new String[]{};//String[]
/** 业务包累加模式 (数组)备注:1*/
cacheRespondParam8010004.D44_70_CSTMPACK_COUNT_MODE=new String[]{};//String[]
/** 业务包地域模式 (数组)备注:1*/
cacheRespondParam8010004.D44_70_CSTMPACK_AREA_MODE=new String[]{};//String[]
/** 业务包计费模式 (数组)备注:1*/
cacheRespondParam8010004.D44_70_PACKET_MODE=new String[]{};//String[]
/** 业务包计费周期 (数组)备注:*/
cacheRespondParam8010004.D44_70_CSTMPACK_FEE_PERIOD=new int[]{};//int[]
/** 周期收费标准 (数组)备注:*/
cacheRespondParam8010004.D44_70_MONEY1=new int[]{};//int[]
/** 有效标志 (数组)备注:1*/
cacheRespondParam8010004.D44_70_VALID=new String[]{};//String[]
/** 商品代号 (数组)备注:20*/
cacheRespondParam8010004.D44_70_MERCH_ID=new String[]{};//String[]


/** 循环域开始 备注:20160619新增*/
cacheRespondParam8010004.D44_70_RECORDNUM1=;//int
/** 业务包号 (数组)备注:3*/
cacheRespondParam8010004.D44_70_PACKETIDNEW=new String[]{};//String[]
/** 覆盖市局 (数组)备注:8*/
cacheRespondParam8010004.D44_70_CITYBRCH=new String[]{};//String[]
/** 市局名称 (数组)备注:50*/
cacheRespondParam8010004.D44_70_NAME1=new String[]{};//String[]
					
					PrintWriter writer = ctx.getResponse().getWriter();
					writer.write(new Gson().toJson(respond,Respond.class));
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ctx.complete();
			}
		});
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

 class Respond{
public String returnCode;
public ReturnData returnData;

 class ReturnData{
public Head head;

 class Head{
public String ret_errcode;
public String ret_msg;
}
public Body body;

 class Body{
}
}
}
}

