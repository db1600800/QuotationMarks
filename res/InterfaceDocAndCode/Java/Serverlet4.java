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
@WebServlet(urlPatterns = "/Serverlet4", asyncSupported = true)
public class Serverlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet4() {
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
RequestParam4 bean = gson.fromJson(parameter, RequestParam4.class);
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



/**买家订单表4*/
CacheRespondParam4 cacheRespondParam4=new CacheRespondParam4(); 
/** 订单号格式：日期+10位流水号 备注:key*/
cacheRespondParam4.orderNo=;//String
/** 会员号 备注:*/
cacheRespondParam4.userId=;//int
/** 会员姓名 备注:*/
cacheRespondParam4.userName=;//String
/** 会员联系手机 备注:*/
cacheRespondParam4.userPhone=;//String
/** 订单状态01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝 备注:*/
cacheRespondParam4.orderStatus=;//select
/** 订单总金额 备注:*/
cacheRespondParam4.totalMoney=;//float
/** 订单已支付金额 备注:*/
cacheRespondParam4.payMoney=;//float
/** 订单支付状态0：未支付1：支付中2：已支付 备注:*/
cacheRespondParam4.payStatus=;//select
/** 店铺代号 备注:*/
cacheRespondParam4.shopId=;//int
/** 所在市场代号 备注:*/
cacheRespondParam4.marketId=;//int
/** 配送方式预留 01：自提02：寄递 备注:*/
cacheRespondParam4.shipType=;//select
/** 省份代号预留：目前阶段接口送空值 备注:*/
cacheRespondParam4.provCode=;//String
/** 市局代号预留：目前阶段接口送空值 备注:*/
cacheRespondParam4.cityCode=;//String
/** 区县代号预留：目前阶段接口送空值 备注:*/
cacheRespondParam4.countyCode=;//String
/** 详细地址代号 备注:*/
cacheRespondParam4.detailAddr=;//String
/** 发票类型预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票 备注:*/
cacheRespondParam4.invoiceType=;//select
/** 发票抬头 备注:*/
cacheRespondParam4.invoiceTitle=;//String
/** 给卖家留言 备注:*/
cacheRespondParam4.orderRemark=;//String
/** 提货时间 备注:*/
cacheRespondParam4.getProductTime=;//String
/** 订单下单时间格式：yyyymmdd hh24miss 备注:*/
cacheRespondParam4.bookTime=;//String
/** 订单支付时间格式：yyyymmdd hh24miss 备注:*/
cacheRespondParam4.payTime=;//String
/** 订单受理渠道01：微信02：安卓03：IOS 备注:*/
cacheRespondParam4.channelNo=;//select
/** 备注1 备注:*/
cacheRespondParam4.remark1=;//String
/** 备注2 备注:*/
cacheRespondParam4.remark2=;//String
					
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

