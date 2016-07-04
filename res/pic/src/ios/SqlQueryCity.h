#import <Foundation/Foundation.h>
#import "sqlite3.h"
@interface SqlQueryCity : NSObject
{
    
    sqlite3 *db;
}
-(BOOL) openDB;
-(NSMutableArray*) queryCityMSG:(NSString*) code withLevel:(NSString*)level;
-(NSMutableArray*) queryCountyNameWithCountyCode:(NSString*) arearCode;
-(NSMutableArray*) queryCityNameWithCountyCode:(NSString*) arearCode;
-(NSMutableArray*) queryProNameWithCountyCode:(NSString*) arearCode;
-(NSString*) queryCityNameWithRegionid:(NSString*) code;
@end
@interface SqlRow : NSObject
@property (nonatomic) NSString *superId;
@property (nonatomic) NSString *rowId;
@property (nonatomic) NSString *rowMsg;
@end

