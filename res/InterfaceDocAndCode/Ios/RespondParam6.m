
#import "RespondParam6.h"
@implementation RespondParam6
/* 退款单号 顺序号 备注:key*/
@synthesize refundId;
/* 会员号 备注:*/
@synthesize userId;
/* 订单号 备注:*/
@synthesize orderNo;
/* 退款原因 备注:*/
@synthesize refundReason;
/* 是否与卖家已协商0：是1：否 备注:*/
@synthesize isConsultSeller;
/* 退款方式0：系统退款1：人工退款 备注:*/
@synthesize refundStyle;
/* 订单原金额 备注:*/
@synthesize orderMoney;
/* 退款申请金额 备注:*/
@synthesize applyMoney;
/* 申请时 间 格式：yyyymmdd hh24miss 备注:*/
@synthesize applyTime;
/* 最后退款时间 格式：yyyymmdd hh24miss 备注:*/
@synthesize refundTime;
@end

