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
@WebServlet(urlPatterns = "/Serverlet8071100", asyncSupported = true)
public class Serverlet8071100 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet8071100() {
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
RequestParam8071100 bean = gson.fromJson(parameter, RequestParam8071100.class);
/** 业务开办局 备注:新增*/
bean.D44_70_YWKBJ;//String
/** 记账商品号 备注:*/
bean.B87_MERCH_ID;//String
/** 记账商品名称 备注:*/
bean.I_MERCH_NAME;//String
/** 记账商品数量 备注:*/
bean.I_MERCH_NUM;//int
/** 客户编号 备注:*/
bean.I_CSTM_NO;//String
/** 客户姓名 备注:C*/
bean.I_CSTM_NAME ;//String
/** 客户联系电话 备注:C*/
bean.B87_TELE_NO;//String
/** 客户联系地址 备注:C*/
bean.I_CSTM_ADDR;//String
/** 客户联系邮编 备注:C*/
bean.B30_CSTM_ZIP;//String
/** 收件人姓名 备注:C*/
bean.I_RCVR_NAME;//String
/** 收件人电话 备注:C*/
bean.I_RCVR_TEL;//String
/** 收件人地址 备注:C*/
bean.I_RCVR_ADDR;//String
/** 收件人邮编 备注:C*/
bean.I_RCVR_POST;//String
/** 收件人要求到达日期 备注:C*/
bean.I_RCVR_DATE;//String
/** 收件人要求到达时间 备注:C*/
bean.I_RCVR_TIME;//String
/** 支付金额1 备注:C*/
bean.I_AMT1;//float
/** 支付金额2 备注:C*/
bean.I_AMT2;//float
/** 支付金额3 备注:C*/
bean.I_AMT3;//float
/** 支付金额4 备注:C*/
bean.I_AMT4;//float
/** 支付金额5 备注:C*/
bean.I_AMT5;//float
/** 业务种类 备注:M*/
bean.I_BUSI_NO ;//String
/** 支付方式 备注:M*/
bean.B60_PAY_TYPE;//String
/** 业务基本费 备注:*商品基本费*/
bean.B00_CASH_AMT;//float
/** 业务手续费 备注:*手续费*/
bean.B04_FEE2;//float
/** 配送费 备注:*配送费*/
bean.B60_MAIL_FEE;//float
/** 优惠手续费 备注:暂无用*/
bean.B60_BATE_FEE ;//float
/** 预多滞纳金 备注:暂无用*/
bean.B60_DEST_FEE ;//float
/** 合计费用 备注:*订单总金额*/
bean.B60_TOTAL_FEE;//float
/** 下单人所在城市 备注:M*/
bean.B04_OPENBRANCH ;//String
/** 收货人所在城市 备注:M*/
bean.B04_TRANBRANCH;//String
/** 客户反馈方式 备注:M*/
bean.B05_DEAL_TYPE;//String
/** 订单回音途径 备注:M*/
bean.B13_HJJE_CAP;//String
/** 会员资料描述 备注:*/
bean.B60_MESS_SUB;//String
/** 备注 备注:交通违章业务存放：是否邮寄发票*/
bean.B87_REMARK;//String
/** 商户号 备注:*/
bean.B82_STORE_NO;//String
/** 订单状态 备注:*/
bean.I_OB_MAILFLAG;//String
/** 渠道代码 备注:M*/
bean.I_CHANNEL_NO;//String
/** 配送方式 备注:M*/
bean.D44_70_SHIPMODE;//String
/** 异地交易标志 备注:*/
bean.D44_70_YIDI_FLAG;//String
/** 循环域结束 备注:*/
bean.;//String
/**  备注:*/
bean.;//String
/** 订单计费日期 备注:*/
bean.D44_70_COMPUTEDATE;//String
/**  备注:*/
bean.;//String
/** 计费备用字段3 备注:2010-9-30新增*/
bean.D44_70_ACCOUNTRESERVE3;//String
/** 计费备用字段4 备注:2010-9-30新增*/
bean.D44_70_ACCOUNTRESERVE4;//String
/** 营销员代号 备注:2016-5-3新增*/
bean.D44_70_SALERID;//String
/** 循环域开始 备注:*/
bean.B04_MAX_RECORD;//int
/** 商品代码（记订单明细用） 备注:M*/
bean.I_MERCH_ID;//String[]
/** 商品名称（记订单明细用） 备注:M*/
bean.B82_MERCH_NAME;//String[]
/** 客户代号 备注:*/
bean.D44_70_CUSTMNUM;//String[]
/** 套餐号 备注:*/
bean.D44_70_PACKETID;//String[]
/** 商品备注2 备注:*/
bean.B60_REMARK2;//String[]
/** 商品价格 备注:M*/
bean.B82_MERCH_PRICE;//float[]
/** 商品优惠后价格 备注:M*/
bean.B82_PREFER_PRICE;//float[]
/** 商品数量（记订单明细用） 备注:M*/
bean.B04_REC_NUM;//int[]
/** 商品备注1 备注:*/
bean.B50_ORDER_REMARK;//String[]
/** 业务相关资料描述 备注:*/
bean.B50_SALE_MSG;//String[]
/** 关联优惠流水 备注:2010-7-8新增*/
bean.D44_70_SEQNO;//int[]
/** 优惠金额 备注:2010-7-8新增*/
bean.D44_70_MONEY1;//float[]
/** 套餐加办流水号 备注:2010-9-30新增*/
bean.D44_70_PACKETHANDLESEQ;//int[]
/** 优惠服务费 备注:2010-9-30新增*/
bean.D44_70_DISCOUNTSHIPFEE;//float[]
/** 计费备用字段1 备注:2010-9-30新增*/
bean.D44_70_ACCOUNTRESERVE1;//String[]
/** 计费备用字段2 备注:2010-9-30新增*/
bean.D44_70_ACCOUNTRESERVE2;//String[]
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



/**订单生成8071100*/
CacheRespondParam8071100 cacheRespondParam8071100=new CacheRespondParam8071100(); 
/** 订单号 备注:*/
cacheRespondParam8071100.I_ORDER_NO =;//String
					
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

