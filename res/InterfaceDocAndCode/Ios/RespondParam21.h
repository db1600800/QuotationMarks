


#import <Foundation/Foundation.h>
/*结算表21*/
@interface RespondParam21:NSObject
/* 主键 备注:key*/
@property ( nonatomic) int settId;
/* 订单号 备注:*/
@property ( nonatomic) NSString *orderNo;
/* 结算流水 备注:*/
@property ( nonatomic) NSString *settSn;
/* 结算状态 备注:*/
@property ( nonatomic) int status;
/* 总费用 备注:*/
@property ( nonatomic) float sumPrice;
/* 商家ID 备注:*/
@property ( nonatomic) int sellerId;
@end


