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
@WebServlet(urlPatterns = "/Serverlet9", asyncSupported = true)
public class Serverlet9 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet9() {
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
RequestParam9 bean = gson.fromJson(parameter, RequestParam9.class);
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



/**卖家表9*/
CacheRespondParam9 cacheRespondParam9=new CacheRespondParam9(); 
/** 主键 备注:key*/
cacheRespondParam9.sellerId=;//int
/** 账号 备注:*/
cacheRespondParam9.account=;//String
/** 手机号码 备注:*/
cacheRespondParam9.mobile=;//String
/** 密码 备注:*/
cacheRespondParam9.password=;//String
/** 状态 备注:*/
cacheRespondParam9.status=;//int
					
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

