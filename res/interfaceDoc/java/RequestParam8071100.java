


/**订单生成8071100*/
public class RequestParam8071100{
/** 业务开办局 备注:新增*/
public String D44_70_YWKBJ;
/** 记账商品号 备注:*/
public String B87_MERCH_ID;
/** 记账商品名称 备注:*/
public String I_MERCH_NAME;
/** 记账商品数量 备注:*/
public int I_MERCH_NUM;
/** 客户编号 备注:*/
public String I_CSTM_NO;
/** 客户姓名 备注:C*/
public String I_CSTM_NAME ;
/** 客户联系电话 备注:C*/
public String B87_TELE_NO;
/** 客户联系地址 备注:C*/
public String I_CSTM_ADDR;
/** 客户联系邮编 备注:C*/
public String B30_CSTM_ZIP;
/** 收件人姓名 备注:C*/
public String I_RCVR_NAME;
/** 收件人电话 备注:C*/
public String I_RCVR_TEL;
/** 收件人地址 备注:C*/
public String I_RCVR_ADDR;
/** 收件人邮编 备注:C*/
public String I_RCVR_POST;
/** 收件人要求到达日期 备注:C*/
public String I_RCVR_DATE;
/** 收件人要求到达时间 备注:C*/
public String I_RCVR_TIME;
/** 支付金额1 备注:C*/
public float I_AMT1;
/** 支付金额2 备注:C*/
public float I_AMT2;
/** 支付金额3 备注:C*/
public float I_AMT3;
/** 支付金额4 备注:C*/
public float I_AMT4;
/** 支付金额5 备注:C*/
public float I_AMT5;
/** 业务种类 备注:M*/
public String I_BUSI_NO ;
/** 支付方式 备注:M*/
public String B60_PAY_TYPE;
/** 业务基本费 备注:*商品基本费*/
public float B00_CASH_AMT;
/** 业务手续费 备注:*手续费*/
public float B04_FEE2;
/** 配送费 备注:*配送费*/
public float B60_MAIL_FEE;
/** 优惠手续费 备注:暂无用*/
public float B60_BATE_FEE ;
/** 预多滞纳金 备注:暂无用*/
public float B60_DEST_FEE ;
/** 合计费用 备注:*订单总金额*/
public float B60_TOTAL_FEE;
/** 下单人所在城市 备注:M*/
public String B04_OPENBRANCH ;
/** 收货人所在城市 备注:M*/
public String B04_TRANBRANCH;
/** 客户反馈方式 备注:M*/
public String B05_DEAL_TYPE;
/** 订单回音途径 备注:M*/
public String B13_HJJE_CAP;
/** 会员资料描述 备注:*/
public String B60_MESS_SUB;
/** 备注 备注:交通违章业务存放：是否邮寄发票*/
public String B87_REMARK;
/** 商户号 备注:*/
public String B82_STORE_NO;
/** 订单状态 备注:*/
public String I_OB_MAILFLAG;
/** 渠道代码 备注:M*/
public String I_CHANNEL_NO;
/** 配送方式 备注:M*/
public String D44_70_SHIPMODE;
/** 异地交易标志 备注:*/
public String D44_70_YIDI_FLAG;
/** 循环域结束 备注:*/
public String ;
/**  备注:*/
public String ;
/** 订单计费日期 备注:*/
public String D44_70_COMPUTEDATE;
/**  备注:*/
public String ;
/** 计费备用字段3 备注:2010-9-30新增*/
public String D44_70_ACCOUNTRESERVE3;
/** 计费备用字段4 备注:2010-9-30新增*/
public String D44_70_ACCOUNTRESERVE4;
/** 营销员代号 备注:2016-5-3新增*/
public String D44_70_SALERID;


/** 循环域开始 备注:*/
public int B04_MAX_RECORD;
/** 商品代码（记订单明细用） 备注:M*/
public String I_MERCH_ID[];
/** 商品名称（记订单明细用） 备注:M*/
public String B82_MERCH_NAME[];
/** 客户代号 备注:*/
public String D44_70_CUSTMNUM[];
/** 套餐号 备注:*/
public String D44_70_PACKETID[];
/** 商品备注2 备注:*/
public String B60_REMARK2[];
/** 商品价格 备注:M*/
public float B82_MERCH_PRICE[];
/** 商品优惠后价格 备注:M*/
public float B82_PREFER_PRICE[];
/** 商品数量（记订单明细用） 备注:M*/
public int B04_REC_NUM[];
/** 商品备注1 备注:*/
public String B50_ORDER_REMARK[];
/** 业务相关资料描述 备注:*/
public String B50_SALE_MSG[];
/** 关联优惠流水 备注:2010-7-8新增*/
public int D44_70_SEQNO[];
/** 优惠金额 备注:2010-7-8新增*/
public float D44_70_MONEY1[];
/** 套餐加办流水号 备注:2010-9-30新增*/
public int D44_70_PACKETHANDLESEQ[];
/** 优惠服务费 备注:2010-9-30新增*/
public float D44_70_DISCOUNTSHIPFEE[];
/** 计费备用字段1 备注:2010-9-30新增*/
public String D44_70_ACCOUNTRESERVE1[];
/** 计费备用字段2 备注:2010-9-30新增*/
public String D44_70_ACCOUNTRESERVE2[];
}


