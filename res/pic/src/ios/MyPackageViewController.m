//注入网络请求,响应,等待提示



#import "MyPackageViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation MyPackageViewController

//a
@synthesize aTextView;

- (void)viewDidLoad
{
    [super viewDidLoad];
//a
[self.aTextView setText:null];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(bool)checkInput{

return true;
}




/*套餐绑定加办8076170*/
NSString  *n8076170=@"8076170";
/*套餐绑定加办8076170*/
-(void) request8076170{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 会员号 备注:12 2010-11-15新增*/
[businessparam setValue:@"" forKey:@"D44_70_CUSTMNUM"];
/* 套餐加办流水 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PACKETSEQ"];
/* 套餐代号 备注:3*/
[businessparam setValue:@"" forKey:@"D44_70_PACKETID"];
/* 循环域结束 备注:*/
[businessparam setValue:@"" forKey:@""];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/* 循环域开始 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_RECORDNUM1"];
/* 绑定对象类型 备注:2*/
NSMutableArray *D44_70_BIND_TYPEList=[[NSMutableArray alloc]init];
// [D44_70_BIND_TYPEList addObject:@""];
[businessparam setValue:D44_70_BIND_TYPEList forKey:@"D44_70_BIND_TYPE"];
/* 绑定标志一 备注:40*/
NSMutableArray *D44_70_BIND_OBJECT1List=[[NSMutableArray alloc]init];
// [D44_70_BIND_OBJECT1List addObject:@""];
[businessparam setValue:D44_70_BIND_OBJECT1List forKey:@"D44_70_BIND_OBJECT1"];
/* 绑定标志二 备注:40*/
NSMutableArray *D44_70_BIND_OBJECT2List=[[NSMutableArray alloc]init];
// [D44_70_BIND_OBJECT2List addObject:@""];
[businessparam setValue:D44_70_BIND_OBJECT2List forKey:@"D44_70_BIND_OBJECT2"];
}

 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8076170  delegate:self   viewController:self];
}

 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {



NSMutableArray *listData8076170=[[NSMutableArray alloc]init];
/*套餐绑定加办8076170*/
if ([msgReturn.formName isEqualToString:n8076170]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8076170 *commonItem=[[RespondParam8076170 alloc]init];
/* 套餐加办流水 备注:*/
commonItem.D44_70_PACKETSEQ=[[returnDataBody objectForKey:@"D44_70_PACKETSEQ"]intValue];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
}

  }//end ReturnData

@end//end viewController


