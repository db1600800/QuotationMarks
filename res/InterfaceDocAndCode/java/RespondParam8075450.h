


#import <Foundation/Foundation.h>
/*套餐明细查询8075450*/
@interface RespondParam8075450:NSObject
/* 套餐号 备注:3*/
@property ( nonatomic) NSString *D44_70_PACKETID;
/* 套餐名称 备注:40*/
@property ( nonatomic) NSString *D44_70_PACKETNAME;
/* 套餐描述 备注:100*/
@property ( nonatomic) NSString *D44_70_DESC;
/* 套餐状态 备注:10 有效  1无效*/
@property ( nonatomic) NSString *D44_70_PACKET_STATUS;
/* 套餐修改模式 备注:2*/
@property ( nonatomic) NSString *D44_70_PACKET_MODIFY_MODE;
/* 客户对象标志 备注:1*/
@property ( nonatomic) NSString *D44_70_CSTMPACK_OBJECT;
/* 套餐累加模式 备注:1*/
@property ( nonatomic) NSString *D44_70_CSTMPACK_COUNT_MODE;
/* 套餐最大累加金额 备注:*/
@property ( nonatomic) int D44_70_TOTALMONEY;
/* 套餐地域模式 备注:1*/
@property ( nonatomic) NSString *D44_70_CSTMPACK_AREA_MODE;
/* 套餐计费模式 备注:1*/
@property ( nonatomic) NSString *D44_70_PACKET_MODE;
/* 套餐计费周期 备注:*/
@property ( nonatomic) int D44_70_CSTMPACK_FEE_PERIOD;
/* 周期收费标准 备注:*/
@property ( nonatomic) int D44_70_MONEY1;
/* 商品代号 备注:20*/
@property ( nonatomic) NSString *D44_70_MERCH_ID;
/* 套餐有效起始日 备注:8 2011-2-15新增*/
@property ( nonatomic) NSString *D44_70_BEGINDATE;
/* 套餐有效结束日 备注:8 2011-2-15新增*/
@property ( nonatomic) NSString *D44_70_ENDDATE;
/* 循环域结束 备注:*/
@property ( nonatomic) NSString *;
/* 循环域结束 备注:*/
@property ( nonatomic) NSString *;
/* 循环域结束 备注:*/
@property ( nonatomic) NSString *;
/*  备注:*/
@property ( nonatomic) NSString *;


/* 循环域开始 备注:循环域1*/
@property ( nonatomic) int D44_70_RECORDNUM;
/* 覆盖市局 备注:8*/
@property ( nonatomic) NSString *D44_70_CITYBRCH;
/* 市局名称 备注:50*/
@property ( nonatomic) NSString *D44_70_NAME1;


/* 循环域开始 备注:循环域2*/
@property ( nonatomic) int D44_70_RECORDNUM1;
/* 业务代号 备注:4*/
@property ( nonatomic) NSString *D44_70_BUSI_ID;
/* 异地交易标志 备注:1*/
@property ( nonatomic) NSString *D44_70_YIDI_FLAG;
/* 最大次数 备注:*/
@property ( nonatomic) int D44_70_NUM;
/* 是否累计金额 备注:1*/
@property ( nonatomic) NSString *D44_70_FLAG_COUNT_MONEY;


/* 循环域开始 备注:循环域3*/
@property ( nonatomic) int D44_70_RECORDNUM2;
/* 绑定对象类型 备注:2*/
@property ( nonatomic) NSString *D44_70_BIND_TYPE;
/* 最大绑定值 备注:*/
@property ( nonatomic) int D44_70_MAX_BINDNUM;
@end


