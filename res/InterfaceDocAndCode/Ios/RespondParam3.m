
#import "RespondParam3.h"
@implementation RespondParam3
/* 系统用户号 备注:key*/
@synthesize systemNo;
/* 系统类别0:买家1：卖家2：批发商 备注:*/
@synthesize systemTyoe;
/* 第三方代号类型0：微信OPENID1：安卓设备号2：IOS设备号 备注:*/
@synthesize linkType;
/* 第三方代号 如微信公众号对应的OPENID或者系统设备号 备注:*/
@synthesize linkNo;
/* 关联时间格式：yyyymmdd hh24miss 备注:*/
@synthesize linkTime;
@end

