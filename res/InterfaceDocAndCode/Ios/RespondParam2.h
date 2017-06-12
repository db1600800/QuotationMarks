


#import <Foundation/Foundation.h>
/*买家店铺收藏信息表2*/
@interface RespondParam2:NSObject
/* 用户id 备注:Key*/
@property ( nonatomic) int userId;
/* 店铺代号 备注:Key*/
@property ( nonatomic) int shopId;
/* 店铺所在市场代号 备注:*/
@property ( nonatomic) int marketId;
/* 店铺经营范围表id 备注:*/
@property ( nonatomic) int shopBusineeRangeId;
/* 是否默认店铺0：否1：是 备注:*/
@property ( nonatomic) select isDefaultShop;
/* 收藏时间 备注:*/
@property ( nonatomic) NSString *colletcTime;
@end


