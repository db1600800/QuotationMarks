//SqlApp.m
@implementation SqlQueryCity
-(BOOL) openDB{
    
    
    NSString *path = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];   
    path = [path stringByAppendingPathComponent:@"securedDirectory/postservice_IOS.db"];
    
//    NSString *path =[[NSBundle mainBundle] pathForResource:@"POST_JY" ofType:@"db"];
    
    //获取数据库路径
    //    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    //    NSString *documents = [paths objectAtIndex:0];
    //    NSString *database_path = [documents stringByAppendingPathComponent:DBNAME];
    
    //如果数据库存在，则用sqlite3_open直接打开（不要担心，如果数据库不存在sqlite3_open会自动创建）
    //打开数据库，这里的[path UTF8String]是将NSString转换为C字符串，因为SQLite3是采用可移植的C(而不是
    //Objective-C)编写的，它不知道什么是NSString.
    if (sqlite3_open([path UTF8String], &db) == SQLITE_OK) {
        return YES;
    }else{
        NSLog(@"数据库打开失败");
        sqlite3_close(db);
        return NO;
    }
}
-(NSMutableArray*) queryCityMSG:(NSString*) code withLevel:(NSString*)level{
   //level 2省  3市  4县     code:上一级的编码(如查市时传入省的code)
    [self openDB];
    
     NSString *sqlQuery=@"";
    if ([level isEqualToString:@"2"]) {//省
         sqlQuery = [NSString stringWithFormat:
                              @"SELECT * FROM PM_CITYCODE  where not CITYCODE = '000000000000000' and CITYCODE like '%%000000000' "];
        
    }else  if ([level isEqualToString:@"3"]) {//市
        
       NSString *part1= [code substringWithRange:NSMakeRange(0, 6)];
        NSString *part2=[code substringWithRange:NSMakeRange(9, 6)];
        
         sqlQuery = [NSString stringWithFormat:
                              @"SELECT * FROM PM_CITYCODE a where not a.CITYCODE = '%@' and a.CITYCODE like '%@'  ",code,[NSString stringWithFormat:@"%@___%@",part1,part2]];
        
    }else  if ([level isEqualToString:@"4"]) {//县
        
        NSString *part1= [code substringWithRange:NSMakeRange(0, 9)];
  
        
         sqlQuery = [NSString stringWithFormat:
                              @"SELECT * FROM PM_CITYCODE a where not a.CITYCODE = '%@' and a.CITYCODE like '%@%%' ",code,part1];
        
    }
    
 
    
    sqlite3_stmt * statement;
    NSMutableArray *rows=[[NSMutableArray alloc]init];
    
    
    if (sqlite3_prepare_v2(db, [sqlQuery UTF8String], -1, &statement, nil) == SQLITE_OK) {
        
        //查询结果集中一条一条的遍历所有的记录，这里的数字对应的是列值,注意这里的列值
        
        while (sqlite3_step(statement) == SQLITE_ROW) {
            
           SqlRow *sqlRow=[[SqlRow alloc]init]; 

            char *superioridCHAR = (char*)sqlite3_column_text(statement, 0);
            NSString *strsuperiorid = [[NSString alloc]initWithUTF8String:superioridCHAR];
            sqlRow.superId=strsuperiorid;
            
            char *regionidchar = (char*)sqlite3_column_text(statement, 1);
            NSString *regionidstring = [[NSString alloc]initWithUTF8String:regionidchar];
            sqlRow.rowId=regionidstring;
            
            char *regionnamechar = (char*)sqlite3_column_text(statement, 2);
            NSString *regionnamestring = [[NSString alloc]initWithUTF8String:regionnamechar];
            sqlRow.rowMsg =regionnamestring;
            
           [rows addObject:sqlRow]; 
        }
    }else{
        NSLog(@"select error:%@",sqlQuery);
        
    }
    sqlite3_close(db);
    return rows;
}
-(NSMutableArray*) queryProNameWithCountyCode:(NSString*) arearCode{
    
    
    NSString *pro=[arearCode substringWithRange:NSMakeRange(0, 6)];
    
    NSMutableArray *propro= [self  queryCityNameWithRegionid:[NSString stringWithFormat:@"%@000000000",pro]];
    return propro;
    
}
-(NSMutableArray*) queryCityNameWithCountyCode:(NSString*) arearCode{
    
    
    NSString *city=[arearCode substringWithRange:NSMakeRange(0, 9)];
    
    NSMutableArray *citycity= [self queryCityNameWithRegionid:[NSString stringWithFormat:@"%@000000",city]];
    return citycity;
}
-(NSMutableArray*) queryCountyNameWithCountyCode:(NSString*) arearCode{
    
    
    
    NSMutableArray *countcount= [self queryCityNameWithRegionid:arearCode];
    
    return countcount;
    
}
-(NSMutableArray*) queryCityNameWithRegionid:(NSString*) code{
    
    [self openDB];
    NSString *sqlQuery = [NSString stringWithFormat:
                @"SELECT * FROM PM_CITYCODE  where  CITYCODE = '%@' ",code];
    
    NSLog(@"sqlQuery:%@",sqlQuery);
    sqlite3_stmt * statement;
    
    NSString * cityName=nil;
    NSString * cityCode=nil;
    NSMutableArray *temp=[[NSMutableArray alloc] init];
    
    if (sqlite3_prepare_v2(db, [sqlQuery UTF8String], -1, &statement, nil) == SQLITE_OK) {
        
        //查询结果集中一条一条的遍历所有的记录，这里的数字对应的是列值,注意这里的列值
        
        while (sqlite3_step(statement) == SQLITE_ROW) {
            
            char *regionnamechar = (char*)sqlite3_column_text(statement, 0);
            NSString *regionnamestring = [[NSString alloc]initWithUTF8String:regionnamechar];
            cityCode = regionnamestring;
            
            char *regionnamechar2 = (char*)sqlite3_column_text(statement, 1);
            NSString *regionnamestring2 = [[NSString alloc]initWithUTF8String:regionnamechar2];
            cityName = regionnamestring2;
            
            [temp addObject:cityCode];
            [temp addObject:cityName];
        }
    }else{
        NSLog(@"select error:%@",sqlQuery);
        
    }
    sqlite3_close(db);
    return temp;
}
@end

@implementation SqlRow 
@synthesize superId;
@synthesize rowId;
@synthesize rowMsg;
@end

