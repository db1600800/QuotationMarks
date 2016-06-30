//注入网络请求,响应,等待提示
#import "PackageDetailPackageInfoLinearLayoutScrollViewCell.h"
#import "PackageDetailPart2LinearLayoutScrollViewCell.h"
#import "SqlApp.h"
#import "SqlApp.h"
#import "SqlApp.h"
#import "PackageDetailPart3LinearLayoutScrollViewCell.h"
#import "PackageDetailPart4LinearLayoutScrollViewCell.h"
#import "SqlApp.h"
#import "PackagePartEmptyLinearLayoutScrollViewCell.h"
#import "PackagePart8LinearLayoutScrollViewCell.h"
#import "MyPackageViewController.h"
#import "MsgReturn.h"



#import "PackageDetailViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation PackageDetailViewController

//back
@synthesize backButton;
//可加办套餐详情
@synthesize titleTextView;

- (void)viewDidLoad
{
    [super viewDidLoad];

//back
[self.backButton addTarget:self action:@selector(backButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
[self.backButton setBackgroundImage:[UIImage imageNamed:@"backSelect.png"] forState:UIControlStateSelected];
 [self.backButton setBackgroundImage:[UIImage imageNamed:@"back.png"] forState:UIControlStateNormal];
[self.backButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域

//可加办套餐详情
[self.titleTextView setText:null];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)backButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据//方法3.回到上页面
  [self.navigationController popViewControllerAnimated:YES];

}

-(bool)checkInput{

return true;
}

-(void) scrollUI{
   for (UIView *view in views) {
        [view removeFromSuperview];
}
 [views removeAllObjects];
int height=0;
int width=self.bg1467190764370ScrollView.frame.size.width;
int x=0;
int y=0;

PackageDetailPackageInfoLinearLayoutScrollViewCell *packageDetailPackageInfoLinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageDetailPackageInfoLinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageDetailPackageInfoLinearLayout setFrame:CGRectMake(x, y+height, width, packageDetailPackageInfoLinearLayout.frame.size.height)];
 height+=packageDetailPackageInfoLinearLayout.frame.size.height;
 [self.bg1467190764370ScrollView addSubview:packageDetailPackageInfoLinearLayout];

 [views addObject:packageDetailPackageInfoLinearLayout];

//套餐信息
[packageDetailPackageInfoLinearLayout.part1TitleTextView setText:null];
//套餐名称
[packageDetailPackageInfoLinearLayout.packageNameTitleTextView setText:null];
//2013
[packageDetailPackageInfoLinearLayout.packageNameTitleTextViewValueTextView setText:null];
//周期
[packageDetailPackageInfoLinearLayout.feePeriodTextView setText:D44_70_CSTMPACK_FEE_PERIOD];
//6个月
[packageDetailPackageInfoLinearLayout.feePeriodValueTextView setText:D44_70_CSTMPACK_FEE_PERIOD];
//价格
[packageDetailPackageInfoLinearLayout.feeTitleTextView setText:null];
//333
[packageDetailPackageInfoLinearLayout.feeValueTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",D44_70_TOTALMONEY]];
//服务区域
[packageDetailPackageInfoLinearLayout.areaTitleTextView setText:null];
//广州北京
[packageDetailPackageInfoLinearLayout.areaValueTextView setText:D44_70_NAME1];
PackageDetailPart2LinearLayoutScrollViewCell *packageDetailPart2LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageDetailPart2LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageDetailPart2LinearLayout setFrame:CGRectMake(x, y+height, width, packageDetailPart2LinearLayout.frame.size.height)];
 height+=packageDetailPart2LinearLayout.frame.size.height;
 [self.bg1467190764370ScrollView addSubview:packageDetailPart2LinearLayout];

 [views addObject:packageDetailPart2LinearLayout];

//收费模式
SqlApp *sqlApp=[[SqlApp alloc ]init ];
NSString *nullCn=[sqlApp selectTransferredMeaningByCode:null];
[packageDetailPart2LinearLayout.feeModeTitleTextView setText:[NSString stringWithFormat:@"[%@]",nullCn]];
//先付款
SqlApp *sqlApp=[[SqlApp alloc ]init ];
NSString *D44_70_PACKET_MODECn=[sqlApp selectTransferredMeaningByCode:D44_70_PACKET_MODE];
[packageDetailPart2LinearLayout.feeModeValueTextView setText:[NSString stringWithFormat:@"[%@]",D44_70_PACKET_MODECn]];
//累加模式
[packageDetailPart2LinearLayout.countModeTitleTextView setText:null];
//按次
SqlApp *sqlApp=[[SqlApp alloc ]init ];
NSString *D44_70_PACKET_MODECn=[sqlApp selectTransferredMeaningByCode:D44_70_PACKET_MODE];
[packageDetailPart2LinearLayout.countModeTitleTextViewValueTextView setText:[NSString stringWithFormat:@"[%@]",D44_70_PACKET_MODECn]];
PackageDetailPart3LinearLayoutScrollViewCell *packageDetailPart3LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageDetailPart3LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageDetailPart3LinearLayout setFrame:CGRectMake(x, y+height, width, packageDetailPart3LinearLayout.frame.size.height)];
 height+=packageDetailPart3LinearLayout.frame.size.height;
 [self.bg1467190764370ScrollView addSubview:packageDetailPart3LinearLayout];

 [views addObject:packageDetailPart3LinearLayout];

//绑定信息
[packageDetailPart3LinearLayout.part3TitleTextView setText:null];
//最大绑定数量
[packageDetailPart3LinearLayout.maxBindNumTitleTextView setText:null];
//10
[packageDetailPart3LinearLayout.maxBindNumValueTextView setText:D44_70_MAX_BINDNUM];
//已绑
[packageDetailPart3LinearLayout.bindedNumTitleTextView setText:null];
//2
[packageDetailPart3LinearLayout.bindedNumValueTextView setText:D44_70_BIND_REALNUM];

//新绑定
packageDetailPart3LinearLayout.newBindButton.tag=;
// objc_setAssociatedObject(packageDetailPart3LinearLayout.newBindButton, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[packageDetailPart3LinearLayout.newBindButton addTarget:self action:@selector(newBindButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
PackageDetailPart4LinearLayoutScrollViewCell *packageDetailPart4LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageDetailPart4LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageDetailPart4LinearLayout setFrame:CGRectMake(x, y+height, width, packageDetailPart4LinearLayout.frame.size.height)];
 height+=packageDetailPart4LinearLayout.frame.size.height;
 [self.bg1467190764370ScrollView addSubview:packageDetailPart4LinearLayout];

 [views addObject:packageDetailPart4LinearLayout];

//小型汽车
SqlApp *sqlApp=[[SqlApp alloc ]init ];
NSString *nullCn=[sqlApp selectTransferredMeaningByCode:null];
[packageDetailPart4LinearLayout.cartypeTextView setText:[NSString stringWithFormat:@"[%@]",nullCn]];
//粤c132
[packageDetailPart4LinearLayout.carNumTextView setText:null];
//小树枝
[packageDetailPart4LinearLayout.carMsgTextView setText:null];

//删除
packageDetailPart4LinearLayout.deleteButton.tag=;
// objc_setAssociatedObject(packageDetailPart4LinearLayout.deleteButton, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[packageDetailPart4LinearLayout.deleteButton addTarget:self action:@selector(deleteButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
PackagePartEmptyLinearLayoutScrollViewCell *packagePartEmptyLinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackagePartEmptyLinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packagePartEmptyLinearLayout setFrame:CGRectMake(x, y+height, width, packagePartEmptyLinearLayout.frame.size.height)];
 height+=packagePartEmptyLinearLayout.frame.size.height;
 [self.bg1467190764370ScrollView addSubview:packagePartEmptyLinearLayout];

 [views addObject:packagePartEmptyLinearLayout];

//暂未绑定车辆
[packagePartEmptyLinearLayout.emptyBindTextView setText:null];
PackagePart8LinearLayoutScrollViewCell *packagePart8LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackagePart8LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packagePart8LinearLayout setFrame:CGRectMake(x, y+height, width, packagePart8LinearLayout.frame.size.height)];
 height+=packagePart8LinearLayout.frame.size.height;
 [self.bg1467190764370ScrollView addSubview:packagePart8LinearLayout];

 [views addObject:packagePart8LinearLayout];

//简介
[packagePart8LinearLayout.singleTitleTextView setText:null];
//safdsf
[packagePart8LinearLayout.singleMsgValueTextView setText:D44_70_DESC];
//start换行高度
   [packagePart8LinearLayout.singleMsgValueTextView setNumberOfLines:0];
packagePart8LinearLayout.singleMsgValueTextView.lineBreakMode = NSLineBreakByWordWrapping;
 CGSize   sizesingleMsgValueTextView = [ packagePart8LinearLayout.singleMsgValueTextView  sizeThatFits:CGSizeMake(packagePart8LinearLayout.singleMsgValueTextView.frame.size.width, MAXFLOAT)];
 [packagePart8LinearLayout.singleMsgValueTextView setFrame:CGRectMake(packagePart8LinearLayout.singleMsgValueTextView.frame.origin.x 
         , packagePart8LinearLayout.singleMsgValueTextView.frame.origin.y, packagePart8LinearLayout.singleMsgValueTextView.frame.size.width, sizesingleMsgValueTextView.height)];
//end换行高度

//scrollView
self.bg1467190764370ScrollView.contentSize=CGSizeMake(width, height);

 UIEdgeInsets contentInsets = UIEdgeInsetsZero;
 self.bg1467190764370ScrollView.contentInset = contentInsets;
 self.bg1467190764370ScrollView.scrollIndicatorInsets = contentInsets;

 [self.bg1467190764370ScrollView setFrame:CGRectMake(0, self.head.frame.size.height, self.bg1467190764370ScrollView.frame.size.width, self.view.frame.size.height-self.head.frame.size.height-self.bottom.frame.size.height)];


//编辑框键盘顶起

//编辑框键盘顶起
}








































-(void)newBindButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 self.hidesBottomBarWhenPushed=YES;
MyPackageViewController *myPackageViewController=[[MyPackageViewController alloc ] initWithNibName:@"MyPackageViewController" bundle:nil];
    [self.navigationController pushViewController:myPackageViewController animated:YES];

}









-(void)deleteButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 MsgReturn *msgReturn=[[MsgReturn alloc]init];
 msgReturn.errorCode=@"-1";//-1显示自定义内容
 msgReturn.errorType=@"02";
 msgReturn.errorDesc=@"请输入搜索内容";
 [PromptError changeShowErrorMsg:msgReturn title:@""  viewController:self block:^(BOOL OKCancel){}];

[self requestnull];

}










-(bool) checkInput{

return true;
}



/*套餐明细查询8075450*/
NSString  *n8075450=@"8075450";
/*套餐明细查询8075450*/
-(void) request8075450{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 套餐号 备注:3*/
[businessparam setValue:@"" forKey:@"D44_70_PACKETID"];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8075450  delegate:self   viewController:self];
}




/*套餐绑定关系查询8076160*/
NSString  *n8076160=@"8076160";
/*套餐绑定关系查询8076160*/
-(void) request8076160{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 套餐加办流水 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PACKETSEQ"];
 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8076160  delegate:self   viewController:self];
}




/*套餐绑定撤销8076180*/
NSString  *n8076180=@"8076180";
/*套餐绑定撤销8076180*/
-(void) request8076180{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 套餐加办流水 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PACKETSEQ"];
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
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8076180  delegate:self   viewController:self];
}

 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {



NSMutableArray *listData8075450=[[NSMutableArray alloc]init];
/*套餐明细查询8075450*/
if ([msgReturn.formName isEqualToString:n8075450]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8075450 *commonItem=[[RespondParam8075450 alloc]init];
/* 套餐号 备注:3*/
commonItem.D44_70_PACKETID=[returnDataBody objectForKey:@"D44_70_PACKETID"];
/* 套餐名称 备注:40*/
commonItem.D44_70_PACKETNAME=[returnDataBody objectForKey:@"D44_70_PACKETNAME"];
/* 套餐描述 备注:100*/
commonItem.D44_70_DESC=[returnDataBody objectForKey:@"D44_70_DESC"];
/* 套餐状态 备注:10 有效  1无效*/
commonItem.D44_70_PACKET_STATUS=[returnDataBody objectForKey:@"D44_70_PACKET_STATUS"];
/* 套餐修改模式 备注:2*/
commonItem.D44_70_PACKET_MODIFY_MODE=[returnDataBody objectForKey:@"D44_70_PACKET_MODIFY_MODE"];
/* 客户对象标志 备注:1*/
commonItem.D44_70_CSTMPACK_OBJECT=[returnDataBody objectForKey:@"D44_70_CSTMPACK_OBJECT"];
/* 套餐累加模式 备注:1*/
commonItem.D44_70_CSTMPACK_COUNT_MODE=[returnDataBody objectForKey:@"D44_70_CSTMPACK_COUNT_MODE"];
/* 套餐最大累加金额 备注:*/
commonItem.D44_70_TOTALMONEY=[[returnDataBody objectForKey:@"D44_70_TOTALMONEY"]intValue];
/* 套餐地域模式 备注:1*/
commonItem.D44_70_CSTMPACK_AREA_MODE=[returnDataBody objectForKey:@"D44_70_CSTMPACK_AREA_MODE"];
/* 套餐计费模式 备注:1*/
commonItem.D44_70_PACKET_MODE=[returnDataBody objectForKey:@"D44_70_PACKET_MODE"];
/* 套餐计费周期 备注:*/
commonItem.D44_70_CSTMPACK_FEE_PERIOD=[[returnDataBody objectForKey:@"D44_70_CSTMPACK_FEE_PERIOD"]intValue];
/* 周期收费标准 备注:*/
commonItem.D44_70_MONEY1=[[returnDataBody objectForKey:@"D44_70_MONEY1"]intValue];
/* 商品代号 备注:20*/
commonItem.D44_70_MERCH_ID=[returnDataBody objectForKey:@"D44_70_MERCH_ID"];
/* 套餐有效起始日 备注:8 2011-2-15新增*/
commonItem.D44_70_BEGINDATE=[returnDataBody objectForKey:@"D44_70_BEGINDATE"];
/* 套餐有效结束日 备注:8 2011-2-15新增*/
commonItem.D44_70_ENDDATE=[returnDataBody objectForKey:@"D44_70_ENDDATE"];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域开始 备注:循环域1*/
int D44_70_RECORDNUM= [[returnDataBody objectForKey:@"D44_70_RECORDNUM"]intValue];
for(int i=0;i<D44_70_RECORDNUM;i++)
{
RespondParam8075450 *item1=[[RespondParam8075450 alloc]init];
/* 覆盖市局 备注:8*/
item1.D44_70_CITYBRCH=[[returnDataBody objectForKey:@"D44_70_CITYBRCH"] objectAtIndex:i];
/* 市局名称 备注:50*/
item1.D44_70_NAME1=[[returnDataBody objectForKey:@"D44_70_NAME1"] objectAtIndex:i];
[listData8075450 addObject:item1];
}

/* 循环域开始 备注:循环域2*/
int D44_70_RECORDNUM1= [[returnDataBody objectForKey:@"D44_70_RECORDNUM1"]intValue];
for(int i=0;i<D44_70_RECORDNUM1;i++)
{
RespondParam8075450 *item2=[[RespondParam8075450 alloc]init];
/* 业务代号 备注:4*/
item2.D44_70_BUSI_ID=[[returnDataBody objectForKey:@"D44_70_BUSI_ID"] objectAtIndex:i];
/* 异地交易标志 备注:1*/
item2.D44_70_YIDI_FLAG=[[returnDataBody objectForKey:@"D44_70_YIDI_FLAG"] objectAtIndex:i];
/* 最大次数 备注:*/
item2.D44_70_NUM=[[[returnDataBody objectForKey:@"D44_70_NUM"] objectAtIndex:i]intValue];
/* 是否累计金额 备注:1*/
item2.D44_70_FLAG_COUNT_MONEY=[[returnDataBody objectForKey:@"D44_70_FLAG_COUNT_MONEY"] objectAtIndex:i];
[listData8075450 addObject:item2];
}

/* 循环域开始 备注:循环域3*/
int D44_70_RECORDNUM2= [[returnDataBody objectForKey:@"D44_70_RECORDNUM2"]intValue];
for(int i=0;i<D44_70_RECORDNUM2;i++)
{
RespondParam8075450 *item3=[[RespondParam8075450 alloc]init];
/* 绑定对象类型 备注:2*/
item3.D44_70_BIND_TYPE=[[returnDataBody objectForKey:@"D44_70_BIND_TYPE"] objectAtIndex:i];
/* 最大绑定值 备注:*/
item3.D44_70_MAX_BINDNUM=[[[returnDataBody objectForKey:@"D44_70_MAX_BINDNUM"] objectAtIndex:i]intValue];
[listData8075450 addObject:item3];
}

}




NSMutableArray *listData8076160=[[NSMutableArray alloc]init];
/*套餐绑定关系查询8076160*/
if ([msgReturn.formName isEqualToString:n8076160]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8076160 *commonItem=[[RespondParam8076160 alloc]init];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域开始 备注:循环一*/
int D44_70_RECORDNUM= [[returnDataBody objectForKey:@"D44_70_RECORDNUM"]intValue];
for(int i=0;i<D44_70_RECORDNUM;i++)
{
RespondParam8076160 *item1=[[RespondParam8076160 alloc]init];
/* 绑定对象类型 备注:2*/
item1.D44_70_BIND_TYPE=[[returnDataBody objectForKey:@"D44_70_BIND_TYPE"] objectAtIndex:i];
/* 最大绑定值 备注:*/
item1.D44_70_MAX_BINDNUM=[[[returnDataBody objectForKey:@"D44_70_MAX_BINDNUM"] objectAtIndex:i]intValue];
/* 绑定实际值 备注:*/
item1.D44_70_BIND_REALNUM=[[[returnDataBody objectForKey:@"D44_70_BIND_REALNUM"] objectAtIndex:i]intValue];
[listData8076160 addObject:item1];
}

/* 循环域开始 备注:循环二*/
int D44_70_RECORDNUM1= [[returnDataBody objectForKey:@"D44_70_RECORDNUM1"]intValue];
for(int i=0;i<D44_70_RECORDNUM1;i++)
{
RespondParam8076160 *item2=[[RespondParam8076160 alloc]init];
/* 绑定对象类型 备注:2*/
item2.D44_70_BIND_TYPE_BAK=[[returnDataBody objectForKey:@"D44_70_BIND_TYPE_BAK"] objectAtIndex:i];
/* 绑定标志一 备注:40*/
item2.D44_70_BIND_OBJECT1=[[returnDataBody objectForKey:@"D44_70_BIND_OBJECT1"] objectAtIndex:i];
/* 绑定标志二 备注:40*/
item2.D44_70_BIND_OBJECT2=[[returnDataBody objectForKey:@"D44_70_BIND_OBJECT2"] objectAtIndex:i];
[listData8076160 addObject:item2];
}

}




NSMutableArray *listData8076180=[[NSMutableArray alloc]init];
/*套餐绑定撤销8076180*/
if ([msgReturn.formName isEqualToString:n8076180]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8076180 *commonItem=[[RespondParam8076180 alloc]init];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
}

  }//end ReturnData

@end//end viewController


