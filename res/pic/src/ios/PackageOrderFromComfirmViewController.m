//注入网络请求,响应,等待提示
#import "PackageCanAddConfirmPart1LinearLayoutScrollViewCell.h"
#import "SqlApp.h"
#import "SqlApp.h"
#import "PackageComfirmpart2LinearLayoutScrollViewCell.h"
#import "PackageComfirmPart3LinearLayoutScrollViewCell.h"
#import "PackageConfirmPart5LinearLayoutScrollViewCell.h"
#import "MyCanAddPackageViewController.h"



#import "PackageOrderFromComfirmViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation PackageOrderFromComfirmViewController

//back
@synthesize backButton;
//订单确认
@synthesize titleTextView;

- (void)viewDidLoad
{
    [super viewDidLoad];

//back
[self.backButton addTarget:self action:@selector(backButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
[self.backButton setBackgroundImage:[UIImage imageNamed:@"backSelect.png"] forState:UIControlStateSelected];
 [self.backButton setBackgroundImage:[UIImage imageNamed:@"back.png"] forState:UIControlStateNormal];
[self.backButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域

//订单确认
[self.titleTextView setText:null];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)backButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
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
int width=self.bg1467272807617ScrollView.frame.size.width;
int x=0;
int y=0;

PackageCanAddConfirmPart1LinearLayoutScrollViewCell *packageCanAddConfirmPart1LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageCanAddConfirmPart1LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageCanAddConfirmPart1LinearLayout setFrame:CGRectMake(x, y+height, width, packageCanAddConfirmPart1LinearLayout.frame.size.height)];
 height+=packageCanAddConfirmPart1LinearLayout.frame.size.height;
 [self.bg1467272807617ScrollView addSubview:packageCanAddConfirmPart1LinearLayout];

 [views addObject:packageCanAddConfirmPart1LinearLayout];

//业务包名
[packageCanAddConfirmPart1LinearLayout.packageNameTextView setText:null];
//6个月
[packageCanAddConfirmPart1LinearLayout.periodTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",D44_70_CSTMPACK_FEE_PERIOD]];
//北京广州
[packageCanAddConfirmPart1LinearLayout.arearTextView setText:D44_70_NAME1];
//先付款
SqlApp *sqlApp=[[SqlApp alloc ]init ];
NSString *D44_70_PACKET_MODECn=[sqlApp selectTransferredMeaningByCode:D44_70_PACKET_MODE];
[packageCanAddConfirmPart1LinearLayout.feeModeTextView setText:[NSString stringWithFormat:@"[%@]",D44_70_PACKET_MODECn]];
//按次数累加
SqlApp *sqlApp=[[SqlApp alloc ]init ];
NSString *D44_70_CSTMPACK_COUNT_MODECn=[sqlApp selectTransferredMeaningByCode:D44_70_CSTMPACK_COUNT_MODE];
[packageCanAddConfirmPart1LinearLayout.countModeTextView setText:[NSString stringWithFormat:@"[%@]",D44_70_CSTMPACK_COUNT_MODECn]];
//245
[packageCanAddConfirmPart1LinearLayout.feeTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",D44_70_MONEY1]];
//套餐生效日期
[packageCanAddConfirmPart1LinearLayout.dateTitleTextView setText:null];

//2013年
packageCanAddConfirmPart1LinearLayout.beginDateButton.tag=;
// objc_setAssociatedObject(packageCanAddConfirmPart1LinearLayout.beginDateButton, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[packageCanAddConfirmPart1LinearLayout.beginDateButton addTarget:self action:@selector(beginDateButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
PackageComfirmpart2LinearLayoutScrollViewCell *packageComfirmpart2LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageComfirmpart2LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageComfirmpart2LinearLayout setFrame:CGRectMake(x, y+height, width, packageComfirmpart2LinearLayout.frame.size.height)];
 height+=packageComfirmpart2LinearLayout.frame.size.height;
 [self.bg1467272807617ScrollView addSubview:packageComfirmpart2LinearLayout];

 [views addObject:packageComfirmpart2LinearLayout];

//总计:
[packageComfirmpart2LinearLayout.totalfeeTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",null]];
PackageComfirmPart3LinearLayoutScrollViewCell *packageComfirmPart3LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageComfirmPart3LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageComfirmPart3LinearLayout setFrame:CGRectMake(x, y+height, width, packageComfirmPart3LinearLayout.frame.size.height)];
 height+=packageComfirmPart3LinearLayout.frame.size.height;
 [self.bg1467272807617ScrollView addSubview:packageComfirmPart3LinearLayout];

 [views addObject:packageComfirmPart3LinearLayout];


//微支付
packageComfirmPart3LinearLayout.weiPayCheckBox.tag=;
 objc_setAssociatedObject(packageComfirmPart3LinearLayout.weiPayCheckBox, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[packageComfirmPart3LinearLayout.weiPayCheckBox setSelected:];

[packageComfirmPart3LinearLayout.weiPayCheckBox addTarget:self action:@selector(weiPayCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];
[packageComfirmPart3LinearLayout.weiPayCheckBox setBackgroundImage:[UIImage imageNamed:@"check.png"] forState:UIControlStateSelected];
 [packageComfirmPart3LinearLayout.weiPayCheckBox setBackgroundImage:[UIImage imageNamed:@"uncheck.png"] forState:UIControlStateNormal];
[packageComfirmPart3LinearLayout.weiPayCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域



[packageComfirmPart3LinearLayout.weiPayCheckBoxCover setText:nil ];

//银联在线
packageComfirmPart3LinearLayout.onlinePayCheckBox.tag=;
 objc_setAssociatedObject(packageComfirmPart3LinearLayout.onlinePayCheckBox, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[packageComfirmPart3LinearLayout.onlinePayCheckBox setSelected:];

[packageComfirmPart3LinearLayout.onlinePayCheckBox addTarget:self action:@selector(onlinePayCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];
[packageComfirmPart3LinearLayout.onlinePayCheckBox setBackgroundImage:[UIImage imageNamed:@"check.png"] forState:UIControlStateSelected];
 [packageComfirmPart3LinearLayout.onlinePayCheckBox setBackgroundImage:[UIImage imageNamed:@"uncheck.png"] forState:UIControlStateNormal];
[packageComfirmPart3LinearLayout.onlinePayCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域



[packageComfirmPart3LinearLayout.onlinePayCheckBoxCover setText:nil ];
PackageConfirmPart5LinearLayoutScrollViewCell *packageConfirmPart5LinearLayout = [[[NSBundle mainBundle] loadNibNamed:@"PackageConfirmPart5LinearLayoutScrollViewCell"  owner:self options:nil] lastObject];
  [packageConfirmPart5LinearLayout setFrame:CGRectMake(x, y+height, width, packageConfirmPart5LinearLayout.frame.size.height)];
 height+=packageConfirmPart5LinearLayout.frame.size.height;
 [self.bg1467272807617ScrollView addSubview:packageConfirmPart5LinearLayout];

 [views addObject:packageConfirmPart5LinearLayout];


//支付
packageConfirmPart5LinearLayout.paynowButton.tag=;
// objc_setAssociatedObject(packageConfirmPart5LinearLayout.paynowButton, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[packageConfirmPart5LinearLayout.paynowButton addTarget:self action:@selector(paynowButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
//scrollView
self.bg1467272807617ScrollView.contentSize=CGSizeMake(width, height);

 UIEdgeInsets contentInsets = UIEdgeInsetsZero;
 self.bg1467272807617ScrollView.contentInset = contentInsets;
 self.bg1467272807617ScrollView.scrollIndicatorInsets = contentInsets;

 [self.bg1467272807617ScrollView setFrame:CGRectMake(0, self.head.frame.size.height, self.bg1467272807617ScrollView.frame.size.width, self.view.frame.size.height-self.head.frame.size.height-self.bottom.frame.size.height)];


//编辑框键盘顶起

//编辑框键盘顶起
}




















  //----日期选择Start----
UIButton *selectDateTimeBtn=nil;
      #pragma mark -日期选择 
      -(void) beginDateButtonClicked:(UIButton *)btn{
selectDateTimeBtn=btn;
          UIDatePicker *datePicker = [[UIDatePicker alloc] init];
          datePicker.tag = 101;
          datePicker.locale = [[NSLocale alloc] initWithLocaleIdentifier:@"zh_CN"];// 设置区域为中国简体中文
          datePicker.datePickerMode = UIDatePickerModeDate; // 设置picker的显示模式：只显示日期
          [datePicker setDate:[NSDate date] animated:YES]; // 设置日期控件值
          [datePicker addTarget:self
                         action:@selector(dateTimeValueChange:)
               forControlEvents:UIControlEventValueChanged];  // 时间改变时触发此事件
          
          
          
  if ([[[UIDevice currentDevice] systemVersion] floatValue] <= 7.0) {
          NSString *title = UIDeviceOrientationIsLandscape([UIDevice currentDevice].orientation) ? @"\n\n\n\n\n\n\n\n\n" : @"\n\n\n\n\n\n\n\n\n\n\n\n";
          
          UIActionSheet* startsheet = [[UIActionSheet alloc] initWithTitle:title
                                                                  delegate:self
                                                         cancelButtonTitle:@"确定"
                                                    destructiveButtonTitle:nil
                                                         otherButtonTitles:nil,
                                       nil];
          [startsheet addSubview:datePicker];
          [startsheet showInView:self.view];
          
}else{
          
          UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"\n\n\n\n\n\n\n\n\n\n\n\n" message:nil 　　preferredStyle:UIAlertControllerStyleActionSheet];
          
          [alert.view addSubview:datePicker];
          
          UIAlertAction *ok = [UIAlertAction actionWithTitle:@"确定" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
              
              NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];
              
              //实例化一个NSDateFormatter对象
              
              [dateFormat setDateFormat:@"yyyy-MM-dd"];//设定时间格式
              
              NSString *timestamp = [dateFormat stringFromDate:datePicker.date];
              
    [selectDateTimeBtn setTitle:timestamp forState:UIControlStateNormal];
      [selectDateTimeBtn setTitle:timestamp forState:UIControlStateSelected];
              
          }];
          
          
          UIAlertAction *cancel = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
              
              　 }];
          
          [alert addAction:ok];
          
          [alert addAction:cancel];
          
          [self presentViewController:alert animated:YES completion:^{ }];
          
          
}
          
          
      }
      
      //点选择按钮时触发此事件
      -(void) actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{
          UIDatePicker *datePicker = (UIDatePicker *)[actionSheet viewWithTag:101];
          NSDateFormatter *formattor = [[NSDateFormatter alloc] init];
          
          
          formattor.dateFormat = @"yyyy年MM月dd日";
          
          NSString *timestamp = [formattor stringFromDate:datePicker.date];
          
          
    [selectDateTimeBtn setTitle:timestamp forState:UIControlStateNormal];
      [selectDateTimeBtn setTitle:timestamp forState:UIControlStateSelected];
      }

      // 时间改变时触发此事件
      -(void)dateTimeValueChange:(UIDatePicker*)datepick
      {
          NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
          [dateFormatter setDateFormat:@"yyyy年MM月dd日"];
          NSString *timestamp =  [dateFormatter stringFromDate: [datepick date]];
          
    [selectDateTimeBtn setTitle:timestamp forState:UIControlStateNormal];
      [selectDateTimeBtn setTitle:timestamp forState:UIControlStateSelected];
      }
      
      
      //----日期选择End----




-(void)weiPayCheckBoxCheck:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");//取绑定数据
int mId2 = btn.tag;//取绑定数据
  btn.selected = !btn.selected ;//用与button做checkBox
 OrderForm *orderform=datas[mId];
 if (orderform.invoiceCheck ) {//选中
  orderform.invoiceCheck=false;
}else
{
   orderform.invoiceCheck=true;
}
[self refreshUi];
//[tableView reloadData];
}



-(void)onlinePayCheckBoxCheck:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");//取绑定数据
int mId2 = btn.tag;//取绑定数据
  btn.selected = !btn.selected ;//用与button做checkBox
 OrderForm *orderform=datas[mId];
 if (orderform.invoiceCheck ) {//选中
  orderform.invoiceCheck=false;
}else
{
   orderform.invoiceCheck=true;
}
[self refreshUi];
//[tableView reloadData];
}



-(void)paynowButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 self.hidesBottomBarWhenPushed=YES;
MyCanAddPackageViewController *myCanAddPackageViewController=[[MyCanAddPackageViewController alloc ] initWithNibName:@"MyCanAddPackageViewController" bundle:nil];
    [self.navigationController pushViewController:myCanAddPackageViewController animated:YES];

}




-(bool) checkInput{

return true;
}



/*支付方式查询 8051920*/
NSString  *n8051920=@"8051920";
/*支付方式查询 8051920*/
-(void) request8051920{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 业务代码 备注:M*/
[businessparam setValue:@"" forKey:@"I_BUSI_NO "];
/* 渠道标示 备注:C*/
[businessparam setValue:@"" forKey:@"I_CHANNEL_NO"];
 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8051920  delegate:self   viewController:self];
}




/*会员订单预览8075290*/
NSString  *n8075290=@"8075290";
/*会员订单预览8075290*/
-(void) request8075290{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 会员号 备注:*/
[businessparam setValue:@"" forKey:@"B07_CSTM_NO"];
/* 循环域结束 备注:*/
[businessparam setValue:@"" forKey:@""];
/* 支付方式 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PAY_MODE"];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/* 循环域开始 备注:*/
[businessparam setValue:@"" forKey:@"B04_MAX_RECORD"];
/* 加办标志 备注:*/
NSMutableArray *D44_70_FLAG_JBList=[[NSMutableArray alloc]init];
// [D44_70_FLAG_JBList addObject:@""];
[businessparam setValue:D44_70_FLAG_JBList forKey:@"D44_70_FLAG_JB"];
/* 原套餐加办流水号 备注:*/
NSMutableArray *D44_70_CARIDList=[[NSMutableArray alloc]init];
// [D44_70_CARIDList addObject:@""];
[businessparam setValue:D44_70_CARIDList forKey:@"D44_70_CARID"];
/* 套餐号 备注:*/
NSMutableArray *B22_QHList=[[NSMutableArray alloc]init];
// [B22_QHList addObject:@""];
[businessparam setValue:B22_QHList forKey:@"B22_QH"];
/* 加办流水号 备注:*/
NSMutableArray *D44_70_PACKETSEQList=[[NSMutableArray alloc]init];
// [D44_70_PACKETSEQList addObject:@""];
[businessparam setValue:D44_70_PACKETSEQList forKey:@"D44_70_PACKETSEQ"];
/* 指定起始日期 备注:*/
NSMutableArray *D44_70_BEGINDATEList=[[NSMutableArray alloc]init];
// [D44_70_BEGINDATEList addObject:@""];
[businessparam setValue:D44_70_BEGINDATEList forKey:@"D44_70_BEGINDATE"];
/* 指定截止日期 备注:*/
NSMutableArray *D44_70_ENDDATEList=[[NSMutableArray alloc]init];
// [D44_70_ENDDATEList addObject:@""];
[businessparam setValue:D44_70_ENDDATEList forKey:@"D44_70_ENDDATE"];
}

 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8075290  delegate:self   viewController:self];
}




/*订单生成8071100*/
NSString  *n8071100=@"8071100";
/*订单生成8071100*/
-(void) request8071100{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 业务开办局 备注:新增*/
[businessparam setValue:@"" forKey:@"D44_70_YWKBJ"];
/* 记账商品号 备注:*/
[businessparam setValue:@"" forKey:@"B87_MERCH_ID"];
/* 记账商品名称 备注:*/
[businessparam setValue:@"" forKey:@"I_MERCH_NAME"];
/* 记账商品数量 备注:*/
[businessparam setValue:@"" forKey:@"I_MERCH_NUM"];
/* 客户编号 备注:*/
[businessparam setValue:@"" forKey:@"I_CSTM_NO"];
/* 客户姓名 备注:C*/
[businessparam setValue:@"" forKey:@"I_CSTM_NAME "];
/* 客户联系电话 备注:C*/
[businessparam setValue:@"" forKey:@"B87_TELE_NO"];
/* 客户联系地址 备注:C*/
[businessparam setValue:@"" forKey:@"I_CSTM_ADDR"];
/* 客户联系邮编 备注:C*/
[businessparam setValue:@"" forKey:@"B30_CSTM_ZIP"];
/* 收件人姓名 备注:C*/
[businessparam setValue:@"" forKey:@"I_RCVR_NAME"];
/* 收件人电话 备注:C*/
[businessparam setValue:@"" forKey:@"I_RCVR_TEL"];
/* 收件人地址 备注:C*/
[businessparam setValue:@"" forKey:@"I_RCVR_ADDR"];
/* 收件人邮编 备注:C*/
[businessparam setValue:@"" forKey:@"I_RCVR_POST"];
/* 收件人要求到达日期 备注:C*/
[businessparam setValue:@"" forKey:@"I_RCVR_DATE"];
/* 收件人要求到达时间 备注:C*/
[businessparam setValue:@"" forKey:@"I_RCVR_TIME"];
/* 支付金额1 备注:C*/
[businessparam setValue:@"" forKey:@"I_AMT1"];
/* 支付金额2 备注:C*/
[businessparam setValue:@"" forKey:@"I_AMT2"];
/* 支付金额3 备注:C*/
[businessparam setValue:@"" forKey:@"I_AMT3"];
/* 支付金额4 备注:C*/
[businessparam setValue:@"" forKey:@"I_AMT4"];
/* 支付金额5 备注:C*/
[businessparam setValue:@"" forKey:@"I_AMT5"];
/* 业务种类 备注:M*/
[businessparam setValue:@"" forKey:@"I_BUSI_NO "];
/* 支付方式 备注:M*/
[businessparam setValue:@"" forKey:@"B60_PAY_TYPE"];
/* 业务基本费 备注:*商品基本费*/
[businessparam setValue:@"" forKey:@"B00_CASH_AMT"];
/* 业务手续费 备注:*手续费*/
[businessparam setValue:@"" forKey:@"B04_FEE2"];
/* 配送费 备注:*配送费*/
[businessparam setValue:@"" forKey:@"B60_MAIL_FEE"];
/* 优惠手续费 备注:暂无用*/
[businessparam setValue:@"" forKey:@"B60_BATE_FEE "];
/* 预多滞纳金 备注:暂无用*/
[businessparam setValue:@"" forKey:@"B60_DEST_FEE "];
/* 合计费用 备注:*订单总金额*/
[businessparam setValue:@"" forKey:@"B60_TOTAL_FEE"];
/* 下单人所在城市 备注:M*/
[businessparam setValue:@"" forKey:@"B04_OPENBRANCH "];
/* 收货人所在城市 备注:M*/
[businessparam setValue:@"" forKey:@"B04_TRANBRANCH"];
/* 客户反馈方式 备注:M*/
[businessparam setValue:@"" forKey:@"B05_DEAL_TYPE"];
/* 订单回音途径 备注:M*/
[businessparam setValue:@"" forKey:@"B13_HJJE_CAP"];
/* 会员资料描述 备注:*/
[businessparam setValue:@"" forKey:@"B60_MESS_SUB"];
/* 备注 备注:交通违章业务存放：是否邮寄发票*/
[businessparam setValue:@"" forKey:@"B87_REMARK"];
/* 商户号 备注:*/
[businessparam setValue:@"" forKey:@"B82_STORE_NO"];
/* 订单状态 备注:*/
[businessparam setValue:@"" forKey:@"I_OB_MAILFLAG"];
/* 渠道代码 备注:M*/
[businessparam setValue:@"" forKey:@"I_CHANNEL_NO"];
/* 配送方式 备注:M*/
[businessparam setValue:@"" forKey:@"D44_70_SHIPMODE"];
/* 异地交易标志 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_YIDI_FLAG"];
/* 循环域结束 备注:*/
[businessparam setValue:@"" forKey:@""];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/* 订单计费日期 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_COMPUTEDATE"];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/* 计费备用字段3 备注:2010-9-30新增*/
[businessparam setValue:@"" forKey:@"D44_70_ACCOUNTRESERVE3"];
/* 计费备用字段4 备注:2010-9-30新增*/
[businessparam setValue:@"" forKey:@"D44_70_ACCOUNTRESERVE4"];
/* 营销员代号 备注:2016-5-3新增*/
[businessparam setValue:@"" forKey:@"D44_70_SALERID"];
/* 循环域开始 备注:*/
[businessparam setValue:@"" forKey:@"B04_MAX_RECORD"];
/* 商品代码（记订单明细用） 备注:M*/
NSMutableArray *I_MERCH_IDList=[[NSMutableArray alloc]init];
// [I_MERCH_IDList addObject:@""];
[businessparam setValue:I_MERCH_IDList forKey:@"I_MERCH_ID"];
/* 商品名称（记订单明细用） 备注:M*/
NSMutableArray *B82_MERCH_NAMEList=[[NSMutableArray alloc]init];
// [B82_MERCH_NAMEList addObject:@""];
[businessparam setValue:B82_MERCH_NAMEList forKey:@"B82_MERCH_NAME"];
/* 客户代号 备注:*/
NSMutableArray *D44_70_CUSTMNUMList=[[NSMutableArray alloc]init];
// [D44_70_CUSTMNUMList addObject:@""];
[businessparam setValue:D44_70_CUSTMNUMList forKey:@"D44_70_CUSTMNUM"];
/* 套餐号 备注:*/
NSMutableArray *D44_70_PACKETIDList=[[NSMutableArray alloc]init];
// [D44_70_PACKETIDList addObject:@""];
[businessparam setValue:D44_70_PACKETIDList forKey:@"D44_70_PACKETID"];
/* 商品备注2 备注:*/
NSMutableArray *B60_REMARK2List=[[NSMutableArray alloc]init];
// [B60_REMARK2List addObject:@""];
[businessparam setValue:B60_REMARK2List forKey:@"B60_REMARK2"];
/* 商品价格 备注:M*/
NSMutableArray *B82_MERCH_PRICEList=[[NSMutableArray alloc]init];
// [B82_MERCH_PRICEList addObject:@""];
[businessparam setValue:B82_MERCH_PRICEList forKey:@"B82_MERCH_PRICE"];
/* 商品优惠后价格 备注:M*/
NSMutableArray *B82_PREFER_PRICEList=[[NSMutableArray alloc]init];
// [B82_PREFER_PRICEList addObject:@""];
[businessparam setValue:B82_PREFER_PRICEList forKey:@"B82_PREFER_PRICE"];
/* 商品数量（记订单明细用） 备注:M*/
NSMutableArray *B04_REC_NUMList=[[NSMutableArray alloc]init];
// [B04_REC_NUMList addObject:@""];
[businessparam setValue:B04_REC_NUMList forKey:@"B04_REC_NUM"];
/* 商品备注1 备注:*/
NSMutableArray *B50_ORDER_REMARKList=[[NSMutableArray alloc]init];
// [B50_ORDER_REMARKList addObject:@""];
[businessparam setValue:B50_ORDER_REMARKList forKey:@"B50_ORDER_REMARK"];
/* 业务相关资料描述 备注:*/
NSMutableArray *B50_SALE_MSGList=[[NSMutableArray alloc]init];
// [B50_SALE_MSGList addObject:@""];
[businessparam setValue:B50_SALE_MSGList forKey:@"B50_SALE_MSG"];
/* 关联优惠流水 备注:2010-7-8新增*/
NSMutableArray *D44_70_SEQNOList=[[NSMutableArray alloc]init];
// [D44_70_SEQNOList addObject:@""];
[businessparam setValue:D44_70_SEQNOList forKey:@"D44_70_SEQNO"];
/* 优惠金额 备注:2010-7-8新增*/
NSMutableArray *D44_70_MONEY1List=[[NSMutableArray alloc]init];
// [D44_70_MONEY1List addObject:@""];
[businessparam setValue:D44_70_MONEY1List forKey:@"D44_70_MONEY1"];
/* 套餐加办流水号 备注:2010-9-30新增*/
NSMutableArray *D44_70_PACKETHANDLESEQList=[[NSMutableArray alloc]init];
// [D44_70_PACKETHANDLESEQList addObject:@""];
[businessparam setValue:D44_70_PACKETHANDLESEQList forKey:@"D44_70_PACKETHANDLESEQ"];
/* 优惠服务费 备注:2010-9-30新增*/
NSMutableArray *D44_70_DISCOUNTSHIPFEEList=[[NSMutableArray alloc]init];
// [D44_70_DISCOUNTSHIPFEEList addObject:@""];
[businessparam setValue:D44_70_DISCOUNTSHIPFEEList forKey:@"D44_70_DISCOUNTSHIPFEE"];
/* 计费备用字段1 备注:2010-9-30新增*/
NSMutableArray *D44_70_ACCOUNTRESERVE1List=[[NSMutableArray alloc]init];
// [D44_70_ACCOUNTRESERVE1List addObject:@""];
[businessparam setValue:D44_70_ACCOUNTRESERVE1List forKey:@"D44_70_ACCOUNTRESERVE1"];
/* 计费备用字段2 备注:2010-9-30新增*/
NSMutableArray *D44_70_ACCOUNTRESERVE2List=[[NSMutableArray alloc]init];
// [D44_70_ACCOUNTRESERVE2List addObject:@""];
[businessparam setValue:D44_70_ACCOUNTRESERVE2List forKey:@"D44_70_ACCOUNTRESERVE2"];
}

 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8071100  delegate:self   viewController:self];
}

 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {



NSMutableArray *listData8051920=[[NSMutableArray alloc]init];
/*支付方式查询 8051920*/
if ([msgReturn.formName isEqualToString:n8051920]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8051920 *commonItem=[[RespondParam8051920 alloc]init];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域开始 备注:*/
int recodeNum= [[returnDataBody objectForKey:@"recodeNum"]intValue];
for(int i=0;i<recodeNum;i++)
{
RespondParam8051920 *item1=[[RespondParam8051920 alloc]init];
/* 支付方式 备注:M*/
item1.B60_PAY_TYPE=[[returnDataBody objectForKey:@"B60_PAY_TYPE"] objectAtIndex:i];
/* 支付方式名称 备注:M*/
item1.B05_ORIE_MODE=[[returnDataBody objectForKey:@"B05_ORIE_MODE"] objectAtIndex:i];
[listData8051920 addObject:item1];
}

}




NSMutableArray *listData8075290=[[NSMutableArray alloc]init];
/*会员订单预览8075290*/
if ([msgReturn.formName isEqualToString:n8075290]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8075290 *commonItem=[[RespondParam8075290 alloc]init];
/* 业务开办局 备注:*/
commonItem.D44_70_YWKBJ=[returnDataBody objectForKey:@"D44_70_YWKBJ"];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 商户号 备注:*/
commonItem.B82_STORE_NO=[returnDataBody objectForKey:@"B82_STORE_NO"];
/* 记账商品号 备注:*/
commonItem.B87_MERCH_ID=[returnDataBody objectForKey:@"B87_MERCH_ID"];
/* 记账商品名称 备注:*/
commonItem.I_MERCH_NAME=[returnDataBody objectForKey:@"I_MERCH_NAME"];
/* 客户编号 备注:*/
commonItem.I_CSTM_NO=[returnDataBody objectForKey:@"I_CSTM_NO"];
/* 客户姓名 备注:*/
commonItem.I_CSTM_NAME =[returnDataBody objectForKey:@"I_CSTM_NAME "];
/* 客户联系电话 备注:*/
commonItem.B87_TELE_NO=[returnDataBody objectForKey:@"B87_TELE_NO"];
/* 客户联系地址 备注:*/
commonItem.I_CSTM_ADDR=[returnDataBody objectForKey:@"I_CSTM_ADDR"];
/* 客户联系邮编 备注:*/
commonItem.B30_CSTM_ZIP=[returnDataBody objectForKey:@"B30_CSTM_ZIP"];
/* 收件人姓名 备注:*/
commonItem.I_RCVR_NAME=[returnDataBody objectForKey:@"I_RCVR_NAME"];
/* 收件人电话 备注:*/
commonItem.I_RCVR_TEL=[returnDataBody objectForKey:@"I_RCVR_TEL"];
/* 收件人地址 备注:*/
commonItem.I_RCVR_ADDR=[returnDataBody objectForKey:@"I_RCVR_ADDR"];
/* 收件人邮编 备注:*/
commonItem.I_RCVR_POST=[returnDataBody objectForKey:@"I_RCVR_POST"];
/* 收件人要求到达日期 备注:*/
commonItem.I_RCVR_DATE=[returnDataBody objectForKey:@"I_RCVR_DATE"];
/* 收件人要求到达时间 备注:*/
commonItem.I_RCVR_TIME=[returnDataBody objectForKey:@"I_RCVR_TIME"];
/* 支付金额1 备注:*/
commonItem.I_AMT1=[[returnDataBody objectForKey:@"I_AMT1"]floatValue];
/* 支付金额2 备注:*/
commonItem.I_AMT2=[[returnDataBody objectForKey:@"I_AMT2"]floatValue];
/* 支付金额3 备注:*/
commonItem.I_AMT3=[[returnDataBody objectForKey:@"I_AMT3"]floatValue];
/* 支付金额4 备注:*/
commonItem.I_AMT4=[[returnDataBody objectForKey:@"I_AMT4"]floatValue];
/* 支付金额5 备注:*/
commonItem.I_AMT5=[[returnDataBody objectForKey:@"I_AMT5"]floatValue];
/* 业务种类 备注:*/
commonItem.I_BUSI_NO =[returnDataBody objectForKey:@"I_BUSI_NO "];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 总业务金额 备注:*/
commonItem.B00_CASH_AMT=[[returnDataBody objectForKey:@"B00_CASH_AMT"]floatValue];
/* 业务手续费 备注:*/
commonItem.B04_FEE2=[[returnDataBody objectForKey:@"B04_FEE2"]floatValue];
/* 配送费 备注:*/
commonItem.B60_MAIL_FEE=[[returnDataBody objectForKey:@"B60_MAIL_FEE"]floatValue];
/* 优惠手续费 备注:*/
commonItem.B60_BATE_FEE =[[returnDataBody objectForKey:@"B60_BATE_FEE "]floatValue];
/* 预多滞纳金 备注:*/
commonItem.B60_DEST_FEE =[[returnDataBody objectForKey:@"B60_DEST_FEE "]floatValue];
/* 合计费用 备注:*/
commonItem.B60_TOTAL_FEE=[[returnDataBody objectForKey:@"B60_TOTAL_FEE"]floatValue];
/* 附加费 备注:*/
commonItem.B60_OIL_FEE=[[returnDataBody objectForKey:@"B60_OIL_FEE"]floatValue];
/* 收货人所在城市 备注:*/
commonItem.B04_TRANBRANCH=[returnDataBody objectForKey:@"B04_TRANBRANCH"];
/* 收货人所在城市 备注:*/
commonItem.B04_TRANBRANCH=[returnDataBody objectForKey:@"B04_TRANBRANCH"];
/* 客户反馈方式 备注:*/
commonItem.B05_DEAL_TYPE=[returnDataBody objectForKey:@"B05_DEAL_TYPE"];
/* 订单回音途径 备注:*/
commonItem.B13_HJJE_CAP=[returnDataBody objectForKey:@"B13_HJJE_CAP"];
/* 客户资料描述 备注:*/
commonItem.B60_MESS_SUB=[returnDataBody objectForKey:@"B60_MESS_SUB"];
/* 备注 备注:*/
commonItem.B87_REMARK=[returnDataBody objectForKey:@"B87_REMARK"];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 应收金额 备注:*/
commonItem.B04_BALANCE2=[[returnDataBody objectForKey:@"B04_BALANCE2"]floatValue];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 异地交易标志 备注:*/
commonItem.D44_70_YIDI_FLAG=[returnDataBody objectForKey:@"D44_70_YIDI_FLAG"];
/* 优惠手续费 备注:*/
commonItem.B60_SOFT_UP=[[returnDataBody objectForKey:@"B60_SOFT_UP"]floatValue];
/* 优惠配送费 备注:*/
commonItem.B60_SOFT_DOWN=[[returnDataBody objectForKey:@"B60_SOFT_DOWN"]floatValue];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域开始 备注:*/
int B04_MAX_RECORD= [[returnDataBody objectForKey:@"B04_MAX_RECORD"]intValue];
for(int i=0;i<B04_MAX_RECORD;i++)
{
RespondParam8075290 *item1=[[RespondParam8075290 alloc]init];
/* 商品代码（记订单明细用） 备注:*/
item1.I_MERCH_ID=[[returnDataBody objectForKey:@"I_MERCH_ID"] objectAtIndex:i];
/* 商品名称（记订单明细用） 备注:*/
item1.B82_MERCH_NAME=[[returnDataBody objectForKey:@"B82_MERCH_NAME"] objectAtIndex:i];
/* 商品数量（记订单明细用） 备注:*/
item1.B04_REC_NUM=[[[returnDataBody objectForKey:@"B04_REC_NUM"] objectAtIndex:i]intValue];
/* 商品价格 备注:*/
item1.B82_MERCH_PRICE=[[[returnDataBody objectForKey:@"B82_MERCH_PRICE"] objectAtIndex:i]floatValue];
/* 商品优惠后价格 备注:*/
item1.B82_PREFER_PRICE=[[[returnDataBody objectForKey:@"B82_PREFER_PRICE"] objectAtIndex:i]floatValue];
/* 客户代号 备注:*/
item1.D44_70_CUSTMNUM=[[returnDataBody objectForKey:@"D44_70_CUSTMNUM"] objectAtIndex:i];
/* 套餐号 备注:*/
item1.D44_70_PACKETID=[[returnDataBody objectForKey:@"D44_70_PACKETID"] objectAtIndex:i];
/* 商品备注1 备注:*/
item1.B50_ORDER_REMARK=[[returnDataBody objectForKey:@"B50_ORDER_REMARK"] objectAtIndex:i];
/* 商品备注2 备注:*/
item1.B60_REMARK2=[[returnDataBody objectForKey:@"B60_REMARK2"] objectAtIndex:i];
/* 业务相关资料描述 备注:*/
item1.B50_SALE_MSG=[[returnDataBody objectForKey:@"B50_SALE_MSG"] objectAtIndex:i];
/* 关联优惠流水 备注:*/
item1.D44_70_SEQNO=[[[returnDataBody objectForKey:@"D44_70_SEQNO"] objectAtIndex:i]intValue];
/* 优惠金额 备注:*/
item1.D44_70_MONEY1=[[[returnDataBody objectForKey:@"D44_70_MONEY1"] objectAtIndex:i]floatValue];
[listData8075290 addObject:item1];
}

/* 循环域开始 备注:*/
int B13_ENTR_FP_NUM= [[returnDataBody objectForKey:@"B13_ENTR_FP_NUM"]intValue];
for(int i=0;i<B13_ENTR_FP_NUM;i++)
{
RespondParam8075290 *item2=[[RespondParam8075290 alloc]init];
/* 业务基本费用 备注:*/
item2.B89_PAY_SUM=[[[returnDataBody objectForKey:@"B89_PAY_SUM"] objectAtIndex:i]floatValue];
/* 基本滞纳金 备注:*/
item2.B60_LOTT_AMT3=[[[returnDataBody objectForKey:@"B60_LOTT_AMT3"] objectAtIndex:i]floatValue];
/* 预多收滞纳金 备注:*/
item2.B60_LOTT_AMT5=[[[returnDataBody objectForKey:@"B60_LOTT_AMT5"] objectAtIndex:i]floatValue];
/* 优惠配送费 备注:*/
item2.B60_LOTT_AMT4=[[[returnDataBody objectForKey:@"B60_LOTT_AMT4"] objectAtIndex:i]floatValue];
/* 实收配送费 备注:*/
item2.B05_BALANCE=[[[returnDataBody objectForKey:@"B05_BALANCE"] objectAtIndex:i]floatValue];
[listData8075290 addObject:item2];
}

}




NSMutableArray *listData8071100=[[NSMutableArray alloc]init];
/*订单生成8071100*/
if ([msgReturn.formName isEqualToString:n8071100]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8071100 *commonItem=[[RespondParam8071100 alloc]init];
/* 订单号 备注:*/
commonItem.I_ORDER_NO =[returnDataBody objectForKey:@"I_ORDER_NO "];
}

  }//end ReturnData

@end//end viewController


