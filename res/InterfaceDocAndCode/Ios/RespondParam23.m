
#import "RespondParam23.h"
@implementation RespondParam23
/* 日志流水号 顺序号，从1开始 备注:key*/
@synthesize orderLogId;
/* 处理时间 备注:*/
@synthesize dealTime;
/* 处理类型0：下单 1：支付 2：配货 3：配货取消 4：取货 5：退款申请6：退款完成 备注:*/
@synthesize dealType;
/* 处理信息内容 备注:*/
@synthesize dealContent;
/* 处理人0：买家 1：卖家 备注:*/
@synthesize dealPerson;
/* 操作经度 格式：小数点后2位 备注:*/
@synthesize lonValue;
/* 操作纬度 备注:*/
@synthesize latValue;
@end

