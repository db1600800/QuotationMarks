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
@WebServlet(urlPatterns = "/Serverlet8075450", asyncSupported = true)
public class Serverlet8075450 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet8075450() {
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
RequestParam8075450 bean = gson.fromJson(parameter, RequestParam8075450.class);
/** 套餐号 备注:3*/
bean.D44_70_PACKETID;//String
/**  备注:*/
bean.;//String
/**  备注:*/
bean.;//String
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



/**套餐明细查询8075450*/
CacheRespondParam8075450 cacheRespondParam8075450=new CacheRespondParam8075450(); 
/** 套餐号 备注:3*/
cacheRespondParam8075450.D44_70_PACKETID=;//String
/** 套餐名称 备注:40*/
cacheRespondParam8075450.D44_70_PACKETNAME=;//String
/** 套餐描述 备注:100*/
cacheRespondParam8075450.D44_70_DESC=;//String
/** 套餐状态 备注:10 有效  1无效*/
cacheRespondParam8075450.D44_70_PACKET_STATUS=;//String
/** 套餐修改模式 备注:2*/
cacheRespondParam8075450.D44_70_PACKET_MODIFY_MODE=;//String
/** 客户对象标志 备注:1*/
cacheRespondParam8075450.D44_70_CSTMPACK_OBJECT=;//String
/** 套餐累加模式 备注:1*/
cacheRespondParam8075450.D44_70_CSTMPACK_COUNT_MODE=;//String
/** 套餐最大累加金额 备注:*/
cacheRespondParam8075450.D44_70_TOTALMONEY=;//int
/** 套餐地域模式 备注:1*/
cacheRespondParam8075450.D44_70_CSTMPACK_AREA_MODE=;//String
/** 套餐计费模式 备注:1*/
cacheRespondParam8075450.D44_70_PACKET_MODE=;//String
/** 套餐计费周期 备注:*/
cacheRespondParam8075450.D44_70_CSTMPACK_FEE_PERIOD=;//int
/** 周期收费标准 备注:*/
cacheRespondParam8075450.D44_70_MONEY1=;//int
/** 商品代号 备注:20*/
cacheRespondParam8075450.D44_70_MERCH_ID=;//String
/** 套餐有效起始日 备注:8 2011-2-15新增*/
cacheRespondParam8075450.D44_70_BEGINDATE=;//String
/** 套餐有效结束日 备注:8 2011-2-15新增*/
cacheRespondParam8075450.D44_70_ENDDATE=;//String
/** 循环域结束 备注:*/
cacheRespondParam8075450.=;//String
/** 循环域结束 备注:*/
cacheRespondParam8075450.=;//String
/** 循环域结束 备注:*/
cacheRespondParam8075450.=;//String
/**  备注:*/
cacheRespondParam8075450.=;//String


/** 循环域开始 备注:循环域1*/
cacheRespondParam8075450.D44_70_RECORDNUM=;//int
/** 覆盖市局 (数组)备注:8*/
cacheRespondParam8075450.D44_70_CITYBRCH=new String[]{};//String[]
/** 市局名称 (数组)备注:50*/
cacheRespondParam8075450.D44_70_NAME1=new String[]{};//String[]


/** 循环域开始 备注:循环域2*/
cacheRespondParam8075450.D44_70_RECORDNUM1=;//int
/** 业务代号 (数组)备注:4*/
cacheRespondParam8075450.D44_70_BUSI_ID=new String[]{};//String[]
/** 异地交易标志 (数组)备注:1*/
cacheRespondParam8075450.D44_70_YIDI_FLAG=new String[]{};//String[]
/** 最大次数 (数组)备注:*/
cacheRespondParam8075450.D44_70_NUM=new int[]{};//int[]
/** 是否累计金额 (数组)备注:1*/
cacheRespondParam8075450.D44_70_FLAG_COUNT_MONEY=new String[]{};//String[]


/** 循环域开始 备注:循环域3*/
cacheRespondParam8075450.D44_70_RECORDNUM2=;//int
/** 绑定对象类型 (数组)备注:2*/
cacheRespondParam8075450.D44_70_BIND_TYPE=new String[]{};//String[]
/** 最大绑定值 (数组)备注:*/
cacheRespondParam8075450.D44_70_MAX_BINDNUM=new int[]{};//int[]
					
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

