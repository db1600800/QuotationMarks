
#import "RespondParam4.h"
@implementation RespondParam4
/* 订单号格式：日期+10位流水号 备注:key*/
@synthesize orderNo;
/* 会员号 备注:*/
@synthesize userId;
/* 会员姓名 备注:*/
@synthesize userName;
/* 会员联系手机 备注:*/
@synthesize userPhone;
/* 订单状态01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝 备注:*/
@synthesize orderStatus;
/* 订单总金额 备注:*/
@synthesize totalMoney;
/* 订单已支付金额 备注:*/
@synthesize payMoney;
/* 订单支付状态0：未支付1：支付中2：已支付 备注:*/
@synthesize payStatus;
/* 店铺代号 备注:*/
@synthesize shopId;
/* 所在市场代号 备注:*/
@synthesize marketId;
/* 配送方式预留 01：自提02：寄递 备注:*/
@synthesize shipType;
/* 省份代号预留：目前阶段接口送空值 备注:*/
@synthesize provCode;
/* 市局代号预留：目前阶段接口送空值 备注:*/
@synthesize cityCode;
/* 区县代号预留：目前阶段接口送空值 备注:*/
@synthesize countyCode;
/* 详细地址代号 备注:*/
@synthesize detailAddr;
/* 发票类型预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票 备注:*/
@synthesize invoiceType;
/* 发票抬头 备注:*/
@synthesize invoiceTitle;
/* 给卖家留言 备注:*/
@synthesize orderRemark;
/* 提货时间 备注:*/
@synthesize getProductTime;
/* 订单下单时间格式：yyyymmdd hh24miss 备注:*/
@synthesize bookTime;
/* 订单支付时间格式：yyyymmdd hh24miss 备注:*/
@synthesize payTime;
/* 订单受理渠道01：微信02：安卓03：IOS 备注:*/
@synthesize channelNo;
/* 备注1 备注:*/
@synthesize remark1;
/* 备注2 备注:*/
@synthesize remark2;
@end

