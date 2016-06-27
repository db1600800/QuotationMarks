


/**退款单批量查询 4453020*/
public class RequestParam4453020{
/** 起始日期 备注:*/
public String B87_BEGIN_DATE;
/** 截止日期 备注:*/
public String B87_END_DATE;
/** 关联订单号 备注:*/
public String I_ORDER_NO ;
/** 循环域结束 备注:*/
public String ;
/** 退款方式 备注:0 帐户退款1 现金退款2 退额度 2012-5-31新增加*/
public String B82_FOOT_KIND;
/** 业务类型 备注:2012-5-31新增加*/
public String D44_70_BUSI_ID;
/** 原订单受理渠道 备注:2012-5-31新增加*/
public String D44_70_CHANNEL;
/** 退款模式 备注:2012-5-31新增加*/
public String D44_70_REFOUND_MODE;
/** 指派处理机构 备注:2012-5-31新增加*/
public String D44_70_DEALBRCHNO;
/**  备注:*/
public String ;
/** 起始记录号 备注:必填*/
public int B88_IMP_NUM;
/** 查询最大记录数 备注:必填*/
public int B88_UNSEND_NUM;
/**  备注:*/
public String ;


/** 循环域开始 备注:2012-5-31新增加*/
public int B04_REC_NUM;
/** 退款单状态编号 备注:2012-5-31新增加*/
public String B80_BOOK_STAT[];
}


