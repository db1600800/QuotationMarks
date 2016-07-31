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
@WebServlet(urlPatterns = "/Serverlet4453020", asyncSupported = true)
public class Serverlet4453020 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet4453020() {
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
RequestParam4453020 bean = gson.fromJson(parameter, RequestParam4453020.class);
/** 起始日期 备注:*/
bean.B87_BEGIN_DATE;//String
/** 截止日期 备注:*/
bean.B87_END_DATE;//String
/** 关联订单号 备注:*/
bean.I_ORDER_NO ;//String
/** 循环域结束 备注:*/
bean.;//String
/** 退款方式 备注:0 帐户退款1 现金退款2 退额度 2012-5-31新增加*/
bean.B82_FOOT_KIND;//String
/** 业务类型 备注:2012-5-31新增加*/
bean.D44_70_BUSI_ID;//String
/** 原订单受理渠道 备注:2012-5-31新增加*/
bean.D44_70_CHANNEL;//String
/** 退款模式 备注:2012-5-31新增加*/
bean.D44_70_REFOUND_MODE;//String
/** 指派处理机构 备注:2012-5-31新增加*/
bean.D44_70_DEALBRCHNO;//String
/**  备注:*/
bean.;//String
/** 起始记录号 备注:必填*/
bean.B88_IMP_NUM;//int
/** 查询最大记录数 备注:必填*/
bean.B88_UNSEND_NUM;//int
/**  备注:*/
bean.;//String
/** 循环域开始 备注:2012-5-31新增加*/
bean.B04_REC_NUM;//int
/** 退款单状态编号 备注:2012-5-31新增加*/
bean.B80_BOOK_STAT;//String[]
}

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



/**退款单批量查询 4453020*/
CacheRespondParam4453020 cacheRespondParam4453020=new CacheRespondParam4453020(); 
/** 查询结果总记录数 备注:满足条件的总记录数*/
cacheRespondParam4453020.B30_ZF_NUM=;//int
					
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

