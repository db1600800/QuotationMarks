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
 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {
  }//end ReturnData

@end//end viewController

