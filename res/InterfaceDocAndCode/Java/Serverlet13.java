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
@WebServlet(urlPatterns = "/Serverlet13", asyncSupported = true)
public class Serverlet13 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet13() {
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
RequestParam13 bean = gson.fromJson(parameter, RequestParam13.class);
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



/**平台商品信息表13*/
CacheRespondParam13 cacheRespondParam13=new CacheRespondParam13(); 
/** 商品代号 备注:key*/
cacheRespondParam13.productID=;//int
/** 商品名称 备注:*/
cacheRespondParam13.productName=;//String
/** 商品所属分类代号 备注:*/
cacheRespondParam13.productCategoryId=;//int
/** 商品类型0：单个商品1：套餐 备注:*/
cacheRespondParam13.productType=;//select
/** 商品状态0:上架1：下架 备注:*/
cacheRespondParam13.productStatus=;//select
/** 交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶 备注:*/
cacheRespondParam13.tradingUnit=;//select
/** 颜色 备注:*/
cacheRespondParam13.productColor=;//String
/** 是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？） 备注:*/
cacheRespondParam13.isFresh=;//select
/** 原产地名称 备注:*/
cacheRespondParam13.sourceArea=;//String
/** 是否支持退货0：否 1：是 备注:*/
cacheRespondParam13.isCanRefund=;//select
/** 是否需要当场处理0：否1：是（是否支持当场处理？） 备注:*/
cacheRespondParam13.isNeedSpot=;//select
/** 详情URL地址 备注:*/
cacheRespondParam13.productDetail=;//String
/** 配菜支持天数 备注:*/
cacheRespondParam13.supportDay=;//int
/** 商品发布渠道 多个渠道以“,”好隔开01：微信02：安卓03：IOS（应该没有渠道） 备注:*/
cacheRespondParam13.channelNo=;//String
/** 备注1 备注:*/
cacheRespondParam13.remakr1=;//String
/** 备注2 备注:*/
cacheRespondParam13.remakr2=;//String
					
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

