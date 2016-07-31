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
@WebServlet(urlPatterns = "/Serverlet8075290", asyncSupported = true)
public class Serverlet8075290 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet8075290() {
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
RequestParam8075290 bean = gson.fromJson(parameter, RequestParam8075290.class);
/** 会员号 备注:*/
bean.B07_CSTM_NO;//String
/** 循环域结束 备注:*/
bean.;//String
/** 支付方式 备注:*/
bean.D44_70_PAY_MODE;//String
/**  备注:*/
bean.;//String
/** 循环域开始 备注:*/
bean.B04_MAX_RECORD;//int
/** 加办标志 备注:*/
bean.D44_70_FLAG_JB;//String[]
/** 原套餐加办流水号 备注:*/
bean.D44_70_CARID;//int[]
/** 套餐号 备注:*/
bean.B22_QH;//String[]
/** 加办流水号 备注:*/
bean.D44_70_PACKETSEQ;//int[]
/** 指定起始日期 备注:*/
bean.D44_70_BEGINDATE;//String[]
/** 指定截止日期 备注:*/
bean.D44_70_ENDDATE;//String[]
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



/**会员订单预览8075290*/
CacheRespondParam8075290 cacheRespondParam8075290=new CacheRespondParam8075290(); 
/** 业务开办局 备注:*/
cacheRespondParam8075290.D44_70_YWKBJ=;//String
/**  备注:*/
cacheRespondParam8075290.=;//String
/** 商户号 备注:*/
cacheRespondParam8075290.B82_STORE_NO=;//String
/** 记账商品号 备注:*/
cacheRespondParam8075290.B87_MERCH_ID=;//String
/** 记账商品名称 备注:*/
cacheRespondParam8075290.I_MERCH_NAME=;//String
/** 客户编号 备注:*/
cacheRespondParam8075290.I_CSTM_NO=;//String
/** 客户姓名 备注:*/
cacheRespondParam8075290.I_CSTM_NAME =;//String
/** 客户联系电话 备注:*/
cacheRespondParam8075290.B87_TELE_NO=;//String
/** 客户联系地址 备注:*/
cacheRespondParam8075290.I_CSTM_ADDR=;//String
/** 客户联系邮编 备注:*/
cacheRespondParam8075290.B30_CSTM_ZIP=;//String
/** 收件人姓名 备注:*/
cacheRespondParam8075290.I_RCVR_NAME=;//String
/** 收件人电话 备注:*/
cacheRespondParam8075290.I_RCVR_TEL=;//String
/** 收件人地址 备注:*/
cacheRespondParam8075290.I_RCVR_ADDR=;//String
/** 收件人邮编 备注:*/
cacheRespondParam8075290.I_RCVR_POST=;//String
/** 收件人要求到达日期 备注:*/
cacheRespondParam8075290.I_RCVR_DATE=;//String
/** 收件人要求到达时间 备注:*/
cacheRespondParam8075290.I_RCVR_TIME=;//String
/** 支付金额1 备注:*/
cacheRespondParam8075290.I_AMT1=;//float
/** 支付金额2 备注:*/
cacheRespondParam8075290.I_AMT2=;//float
/** 支付金额3 备注:*/
cacheRespondParam8075290.I_AMT3=;//float
/** 支付金额4 备注:*/
cacheRespondParam8075290.I_AMT4=;//float
/** 支付金额5 备注:*/
cacheRespondParam8075290.I_AMT5=;//float
/** 业务种类 备注:*/
cacheRespondParam8075290.I_BUSI_NO =;//String
/**  备注:*/
cacheRespondParam8075290.=;//String
/** 总业务金额 备注:*/
cacheRespondParam8075290.B00_CASH_AMT=;//float
/** 业务手续费 备注:*/
cacheRespondParam8075290.B04_FEE2=;//float
/** 配送费 备注:*/
cacheRespondParam8075290.B60_MAIL_FEE=;//float
/** 优惠手续费 备注:*/
cacheRespondParam8075290.B60_BATE_FEE =;//float
/** 预多滞纳金 备注:*/
cacheRespondParam8075290.B60_DEST_FEE =;//float
/** 合计费用 备注:*/
cacheRespondParam8075290.B60_TOTAL_FEE=;//float
/** 附加费 备注:*/
cacheRespondParam8075290.B60_OIL_FEE=;//float
/** 收货人所在城市 备注:*/
cacheRespondParam8075290.B04_TRANBRANCH=;//String
/** 收货人所在城市 备注:*/
cacheRespondParam8075290.B04_TRANBRANCH=;//String
/** 客户反馈方式 备注:*/
cacheRespondParam8075290.B05_DEAL_TYPE=;//String
/** 订单回音途径 备注:*/
cacheRespondParam8075290.B13_HJJE_CAP=;//String
/** 客户资料描述 备注:*/
cacheRespondParam8075290.B60_MESS_SUB=;//String
/** 备注 备注:*/
cacheRespondParam8075290.B87_REMARK=;//String
/**  备注:*/
cacheRespondParam8075290.=;//String
/** 应收金额 备注:*/
cacheRespondParam8075290.B04_BALANCE2=;//float
/**  备注:*/
cacheRespondParam8075290.=;//String
/** 循环域结束 备注:*/
cacheRespondParam8075290.=;//String
/** 循环域结束 备注:*/
cacheRespondParam8075290.=;//String
/** 异地交易标志 备注:*/
cacheRespondParam8075290.D44_70_YIDI_FLAG=;//String
/** 优惠手续费 备注:*/
cacheRespondParam8075290.B60_SOFT_UP=;//float
/** 优惠配送费 备注:*/
cacheRespondParam8075290.B60_SOFT_DOWN=;//float
/**  备注:*/
cacheRespondParam8075290.=;//String


/** 循环域开始 备注:*/
cacheRespondParam8075290.B04_MAX_RECORD=;//int
/** 商品代码（记订单明细用） (数组)备注:*/
cacheRespondParam8075290.I_MERCH_ID=new String[]{};//String[]
/** 商品名称（记订单明细用） (数组)备注:*/
cacheRespondParam8075290.B82_MERCH_NAME=new String[]{};//String[]
/** 商品数量（记订单明细用） (数组)备注:*/
cacheRespondParam8075290.B04_REC_NUM=new int[]{};//int[]
/** 商品价格 (数组)备注:*/
cacheRespondParam8075290.B82_MERCH_PRICE=new float[]{};//float[]
/** 商品优惠后价格 (数组)备注:*/
cacheRespondParam8075290.B82_PREFER_PRICE=new float[]{};//float[]
/** 客户代号 (数组)备注:*/
cacheRespondParam8075290.D44_70_CUSTMNUM=new String[]{};//String[]
/** 套餐号 (数组)备注:*/
cacheRespondParam8075290.D44_70_PACKETID=new String[]{};//String[]
/** 商品备注1 (数组)备注:*/
cacheRespondParam8075290.B50_ORDER_REMARK=new String[]{};//String[]
/** 商品备注2 (数组)备注:*/
cacheRespondParam8075290.B60_REMARK2=new String[]{};//String[]
/** 业务相关资料描述 (数组)备注:*/
cacheRespondParam8075290.B50_SALE_MSG=new String[]{};//String[]
/** 关联优惠流水 (数组)备注:*/
cacheRespondParam8075290.D44_70_SEQNO=new int[]{};//int[]
/** 优惠金额 (数组)备注:*/
cacheRespondParam8075290.D44_70_MONEY1=new float[]{};//float[]


/** 循环域开始 备注:*/
cacheRespondParam8075290.B13_ENTR_FP_NUM=;//int
/** 业务基本费用 (数组)备注:*/
cacheRespondParam8075290.B89_PAY_SUM=new float[]{};//float[]
/** 基本滞纳金 (数组)备注:*/
cacheRespondParam8075290.B60_LOTT_AMT3=new float[]{};//float[]
/** 预多收滞纳金 (数组)备注:*/
cacheRespondParam8075290.B60_LOTT_AMT5=new float[]{};//float[]
/** 优惠配送费 (数组)备注:*/
cacheRespondParam8075290.B60_LOTT_AMT4=new float[]{};//float[]
/** 实收配送费 (数组)备注:*/
cacheRespondParam8075290.B05_BALANCE=new float[]{};//float[]
					
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

