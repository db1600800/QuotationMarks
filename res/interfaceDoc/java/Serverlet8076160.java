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
@WebServlet(urlPatterns = "/Serverlet8076160", asyncSupported = true)
public class Serverlet8076160 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet8076160() {
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
RequestParam8076160 bean = gson.fromJson(parameter, RequestParam8076160.class);
/** 套餐加办流水 备注:*/
bean.D44_70_PACKETSEQ;//int
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



/**套餐绑定关系查询8076160*/
CacheRespondParam8076160 cacheRespondParam8076160=new CacheRespondParam8076160(); 
/** 循环域结束 备注:*/
cacheRespondParam8076160.=;//String
/** 循环域结束 备注:*/
cacheRespondParam8076160.=;//String
/**  备注:*/
cacheRespondParam8076160.=;//String


/** 循环域开始 备注:循环一*/
cacheRespondParam8076160.D44_70_RECORDNUM=;//int
/** 绑定对象类型 (数组)备注:2*/
cacheRespondParam8076160.D44_70_BIND_TYPE=new String[]{};//String[]
/** 最大绑定值 (数组)备注:*/
cacheRespondParam8076160.D44_70_MAX_BINDNUM=new int[]{};//int[]
/** 绑定实际值 (数组)备注:*/
cacheRespondParam8076160.D44_70_BIND_REALNUM=new int[]{};//int[]


/** 循环域开始 备注:循环二*/
cacheRespondParam8076160.D44_70_RECORDNUM1=;//int
/** 绑定对象类型 (数组)备注:2*/
cacheRespondParam8076160.D44_70_BIND_TYPE_BAK=new String[]{};//String[]
/** 绑定标志一 (数组)备注:40*/
cacheRespondParam8076160.D44_70_BIND_OBJECT1=new String[]{};//String[]
/** 绑定标志二 (数组)备注:40*/
cacheRespondParam8076160.D44_70_BIND_OBJECT2=new String[]{};//String[]
					
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
