
#import "RespondParam19.h"
@implementation RespondParam19
/* 主键 备注:key*/
@synthesize messageId;
/* 内容 备注:*/
@synthesize messageDetail;
/* 01：业务通知02：系统变更通知03：业务进展通知04：其它通知 备注:*/
@synthesize messageType;
/* 0：未读 1：已读 备注:*/
@synthesize readMessageFlag;
/* 系统用户 备注:*/
@synthesize systemNo;
/* 消息渠道01：微信02：安卓03：IOS 备注:*/
@synthesize messageChannel;
/* 消息链接类型 备注:*/
@synthesize messageLinkType;
/* 消息链接业务参数 备注:*/
@synthesize messageLinkPara;
@end

