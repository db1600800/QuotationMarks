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
@WebServlet(urlPatterns = "/Serverlet8075600", asyncSupported = true)
public class Serverlet8075600 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet8075600() {
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
RequestParam8075600 bean = gson.fromJson(parameter, RequestParam8075600.class);
/** 会员号 备注:*/
bean.D44_70_CUSTMNUM;//String
/** 查询范围 备注:0：有效套餐1:过期套餐2：未启用套餐3：所有套餐*/
bean.D44_70_VALID;//String
/** 页码 备注:*/
bean.D44_70_PAGENUM;//int
/** 页码大小 备注:*/
bean.D44_70_PAGECODE;//int
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



/**会员已购买套餐查询8075600*/
CacheRespondParam8075600 cacheRespondParam8075600=new CacheRespondParam8075600(); 
/** 总记录数 备注:*/
cacheRespondParam8075600.D44_70_MAXRECORD=;//int
/** 重复域开始 备注:*/
cacheRespondParam8075600.D44_70_RECORDNUM=;//int
/** 套餐加办流水 备注:*/
cacheRespondParam8075600.D44_70_SEQNO=;//int
/** 套餐号 备注:*/
cacheRespondParam8075600.D44_70_PACKETID=;//String
/** 套餐名 备注:*/
cacheRespondParam8075600.D44_70_PACKETNAME=;//String
/** 起始有效期 备注:*/
cacheRespondParam8075600.D44_70_BEGINDATE=;//String
/** 截止有效期 备注:*/
cacheRespondParam8075600.D44_70_ENDDATE=;//String
/** 套餐加办日期 备注:*/
cacheRespondParam8075600.D44_70_TRAN_DATE=;//String
/** 套餐绑定内容 备注:*/
cacheRespondParam8075600.D44_70_BIG_DESC=;//String
/** 套餐状态 备注:*/
cacheRespondParam8075600.D44_70_NEW_STATUS=;//String
/** 重复域结束 备注:*/
cacheRespondParam8075600.=;//String
					
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

