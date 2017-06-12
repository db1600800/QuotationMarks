


/**买家退款表6*/
public class CacheRespondParam6{
/** 退款单号 顺序号 备注:key*/
public int refundId;
/** 会员号 备注:*/
public String userId;
/** 订单号 备注:*/
public String orderNo;
/** 退款原因 备注:*/
public String refundReason;
/** 是否与卖家已协商0：是1：否 备注:*/
public select isConsultSeller;
/** 退款方式0：系统退款1：人工退款 备注:*/
public select refundStyle;
/** 订单原金额 备注:*/
public float orderMoney;
/** 退款申请金额 备注:*/
public float applyMoney;
/** 申请时 间 格式：yyyymmdd hh24miss 备注:*/
public String applyTime;
/** 最后退款时间 格式：yyyymmdd hh24miss 备注:*/
public String refundTime;
}


