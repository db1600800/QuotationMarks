


#import <Foundation/Foundation.h>
/*可办理套餐查询8010004*/
@interface RespondParam8010004:NSObject
/* 总记录数 备注:*/
@property ( nonatomic) int D44_70_MAXRECORD;
/* 循环域结束 备注:*/
@property ( nonatomic) NSString *;
/*  备注:*/
@property ( nonatomic) NSString *;
/* 循环域结束 备注:*/
@property ( nonatomic) NSString *D44_70_RECORDNUM1;


/* 循环域开始 备注:*/
@property ( nonatomic) int D44_70_RECORDNUM;
/* 业务包号 备注:3*/
@property ( nonatomic) NSString *D44_70_PACKETID;
/* 业务包名称 备注:40*/
@property ( nonatomic) NSString *D44_70_PACKETNAME;
/* 业务包描述 备注:100*/
@property ( nonatomic) NSString *D44_70_DESC;
/* 客户对象标志 备注:1*/
@property ( nonatomic) NSString *D44_70_CSTMPACK_OBJECT;
/* 业务包累加模式 备注:1*/
@property ( nonatomic) NSString *D44_70_CSTMPACK_COUNT_MODE;
/* 业务包地域模式 备注:1*/
@property ( nonatomic) NSString *D44_70_CSTMPACK_AREA_MODE;
/* 业务包计费模式 备注:1*/
@property ( nonatomic) NSString *D44_70_PACKET_MODE;
/* 业务包计费周期 备注:*/
@property ( nonatomic) int D44_70_CSTMPACK_FEE_PERIOD;
/* 周期收费标准 备注:*/
@property ( nonatomic) int D44_70_MONEY1;
/* 有效标志 备注:1*/
@property ( nonatomic) NSString *D44_70_VALID;
/* 商品代号 备注:20*/
@property ( nonatomic) NSString *D44_70_MERCH_ID;


/* 循环域开始 备注:20160619新增*/
@property ( nonatomic) int D44_70_RECORDNUM1;
/* 业务包号 备注:3*/
@property ( nonatomic) NSString *D44_70_PACKETIDNEW;
/* 覆盖市局 备注:8*/
@property ( nonatomic) NSString *D44_70_CITYBRCH;
/* 市局名称 备注:50*/
@property ( nonatomic) NSString *D44_70_NAME1;
@end

