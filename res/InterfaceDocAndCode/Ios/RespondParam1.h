


#import <Foundation/Foundation.h>
/*买家基本信息表1*/
@interface RespondParam1:NSObject
/* 会员号 备注:Key*/
@property ( nonatomic) int userId;
/* 手机号码 备注:*/
@property ( nonatomic) NSString *moileNo;
/* 会员呢称 备注:*/
@property ( nonatomic) NSString *nickName;
/* 性别0：女1：男 备注:*/
@property ( nonatomic) select sex;
/* 会员头像图片URL地址 备注:*/
@property ( nonatomic) file headUrl;
/* 会员等级预留01：铜牌会员02：银牌会员03：金牌会员 备注:*/
@property ( nonatomic) select cstmLevel;
/* 注册时所在经度格式：小数点后2位 备注:*/
@property ( nonatomic) float lonValue;
/* 注册时所在纬度格式：小数点后2位 备注:*/
@property ( nonatomic) float latValue;
/* 注册时间格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *createTime;
@end


