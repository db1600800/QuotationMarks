//注入网络请求,响应,等待提示
#import "MyPackageViewController.h"
#import "priceUpButtonSelectorViewController.h"
#import "SqlApp.h"
#import "SqlRow.h"
#import "timeButtonSelectorViewController.h"
#import "SqlApp.h"
#import "SqlRow.h"
#import "cityButtonSelectorViewController.h"
#import "SqlApp.h"
#import "SqlRow.h"
#import "MyCanAddPackageViewController.h"
#import "MoreTableViewCell.h"
#import "MyCanAddPackageTableViewCell.h"
#import "Row.h"
#import "Chirld.h"
#import "MyPackageViewController.h"
#import "priceUpButtonSelectorViewController.h"
#import "SqlApp.h"
#import "SqlRow.h"
#import "timeButtonSelectorViewController.h"
#import "SqlApp.h"
#import "SqlRow.h"
#import "cityButtonSelectorViewController.h"
#import "SqlApp.h"
#import "SqlRow.h"
#import "MyCanAddPackageViewController.h"



#import "MyCanAddPackageViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

#import "MyCanAddPackageTableViewCell.h"
//注入table功能
 NSString *MyCanAddPackageCellIdentifier = @"MyCanAddPackageTableViewCell";
 NSString *MyCanAddPackageCellHeadIdentifier = @"MyCanAddPackageTableViewCellHead";

@implementation MyCanAddPackageViewController
@synthesize cacheCells;

//list
@synthesize tableView;
//可加办套餐
@synthesize titleTextView;
//我的套餐
@synthesize myPackageButton;
//价格从高到低
@synthesize priceUpButton;
//down
@synthesize downImageView;
//3个月
@synthesize timeButton;
//down
@synthesize downImageView;
//全国
@synthesize cityButton;
//down
@synthesize downImageView;
//总计:
@synthesize totalTitleTextView;
//123
@synthesize totalValueTextView;
//办理
@synthesize buyButton;

- (void)viewDidLoad
{
    [super viewDidLoad];

//start  TableView 
totalRowCount=0;
currentRowCount=0;
page=1;

allIndexpaths=[[NSMutableArray alloc] init];
 rows=[[NSMutableArray alloc] init];
    [self.tableView setDelegate:self];//tableview委托
    [self.tableView setDataSource:self];//tableview数据委托
    self.tableView.tableFooterView=[[UIView alloc]init];//tableview去除多余的分隔线
    //使用自定义的Cell,需要向UITableView进行注册
    UINib *cellNib = [UINib nibWithNibName:@"MyCanAddPackageTableViewCell" bundle:nil];
    [tableView registerNib:cellNib forCellReuseIdentifier:MyCanAddPackageCellIdentifier];
     cacheCells = [NSMutableDictionary dictionary];
//end TableView 

//可加办套餐
[self.titleTextView setText:null];

//我的套餐
[self.myPackageButton addTarget:self action:@selector(myPackageButtonClicked:) forControlEvents:UIControlEventTouchUpInside];

//价格从高到低
[self.priceUpButton addTarget:self action:@selector(priceUpButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
//down
[downImageView setImage:[UIImage imageNamed:@"1.jpeg"]]
[downImageView setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@"default.jpg"]];

//3个月
[self.timeButton addTarget:self action:@selector(timeButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
//down
[downImageView setImage:[UIImage imageNamed:@"1.jpeg"]]
[downImageView setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@"default.jpg"]];

//全国
[self.cityButton addTarget:self action:@selector(cityButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
//down
[downImageView setImage:[UIImage imageNamed:@"1.jpeg"]]
[downImageView setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@"default.jpg"]];
//总计:
[self.totalTitleTextView setText:null];
//123
[self.totalValueTextView setText:null];

//办理
[self.buyButton addTarget:self action:@selector(buyButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
}

-(void) viewWillAppear:(BOOL)animated{
//table
[self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];
}

-(void)myPackageButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 self.hidesBottomBarWhenPushed=YES;
MyPackageViewController *myPackageViewController=[[MyPackageViewController alloc ] initWithNibName:@"MyPackageViewController" bundle:nil];
    [self.navigationController pushViewController:myPackageViewController animated:YES];

}

-(void)priceUpButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据//父亲ViewController实现接口  priceUpButtonSelectorChirldViewCallBackDelegate>
//1. priceUpButtonSelectorChirldViewCallBackDelegate
//-(void) chirldViewCallBack:(NSMutableArray*)mdata;
//2.在viewDidLoad中
//chirldViewController=[[priceUpButtonSelectorViewController alloc ] initWithNibName:@"priceUpButtonSelectorViewController" bundle:nil];
//chirldViewController.view.frame=CGRectMake(,,,);
//[chirldViewController setChirldViewValue:nil delegate:self];
//[ self.view addSubview:chirldViewController.view];
// [chirldViewController.view setHidden:YES];
 //SqlApp *sqlapp = [[SqlApp alloc]init];
  //NSMutableArray *provinces = [sqlapp queryCityMSG:@"000000" withLevel:@"2"];//省
  //NSMutableArray *selectors =[[NSMutableArray alloc]init]; 
  //SqlRow *sqlRow =[[SqlRow alloc]init]; 

}

-(void)timeButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据//父亲ViewController实现接口  timeButtonSelectorChirldViewCallBackDelegate>
//1. timeButtonSelectorChirldViewCallBackDelegate
//-(void) chirldViewCallBack:(NSMutableArray*)mdata;
//2.在viewDidLoad中
//chirldViewController=[[timeButtonSelectorViewController alloc ] initWithNibName:@"timeButtonSelectorViewController" bundle:nil];
//chirldViewController.view.frame=CGRectMake(,,,);
//[chirldViewController setChirldViewValue:nil delegate:self];
//[ self.view addSubview:chirldViewController.view];
// [chirldViewController.view setHidden:YES];
 //SqlApp *sqlapp = [[SqlApp alloc]init];
  //NSMutableArray *provinces = [sqlapp queryCityMSG:@"000000" withLevel:@"2"];//省
  //NSMutableArray *selectors =[[NSMutableArray alloc]init]; 
  //SqlRow *sqlRow =[[SqlRow alloc]init]; 

}

-(void)cityButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据//父亲ViewController实现接口  cityButtonSelectorChirldViewCallBackDelegate>
//1. cityButtonSelectorChirldViewCallBackDelegate
//-(void) chirldViewCallBack:(NSMutableArray*)mdata;
//2.在viewDidLoad中
//chirldViewController=[[cityButtonSelectorViewController alloc ] initWithNibName:@"cityButtonSelectorViewController" bundle:nil];
//chirldViewController.view.frame=CGRectMake(,,,);
//[chirldViewController setChirldViewValue:nil delegate:self];
//[ self.view addSubview:chirldViewController.view];
// [chirldViewController.view setHidden:YES];
 //SqlApp *sqlapp = [[SqlApp alloc]init];
  //NSMutableArray *provinces = [sqlapp queryCityMSG:@"000000" withLevel:@"2"];//省
  //NSMutableArray *selectors =[[NSMutableArray alloc]init]; 
  //SqlRow *sqlRow =[[SqlRow alloc]init]; 

}

-(void)buyButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 self.hidesBottomBarWhenPushed=YES;
MyCanAddPackageViewController *myCanAddPackageViewController=[[MyCanAddPackageViewController alloc ] initWithNibName:@"MyCanAddPackageViewController" bundle:nil];
    [self.navigationController pushViewController:myCanAddPackageViewController animated:YES];

}

-(bool)checkInput{

return true;
}

-(void)viewWillLayoutSubviews
{
int startY=self.headView.frame.origin.y+self.headView.frame.size.height;
 [self.tableView setFrame:CGRectMake(0, startY, self.tableView.frame.size.width, self.view.frame.size.height-startY )];
}

//指定有多少个分区(Section)，默认为1
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 1;//返回标题数组中元素的个数来确定分区的个数   return [sections count];
}

//指定每个分区中有多少行，默认为1
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
     return  [rows count]+1;
    
}

//绘制Cell
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {


//分页Start(可注调)
 if([indexPath row] == ([rows count])  && [rows count]>0) {
 if( currentRowCount<totalRowCount)
 {
    [self request9999:YES];
  MoreTableViewCell *cell = [[[NSBundle mainBundle] loadNibNamed:@"MoreTableViewCell" owner:self options:nil] lastObject];
  return cell;
 }else
 {
    return [[UITableViewCell alloc] init ];
 }
  }
 else  if([indexPath row] == ([rows count]) && [rows count]==0)
{
return [[UITableViewCell alloc] init ];
 }
 else //分页End

 {
 MyCanAddPackageTableViewCell *cell = (MyCanAddPackageTableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:MyCanAddPackageCellIdentifier];
    if (!cell)
    {
       cell = [[[NSBundle mainBundle] loadNibNamed:@"MyCanAddPackageTableViewCell" owner:self options:nil] lastObject];
    }

//业务包名称
cell.packageNameCheckBox.tag=;
 objc_setAssociatedObject(cell.packageNameCheckBox, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[cell.packageNameCheckBox setSelected:];

[cell.packageNameCheckBox addTarget:self action:@selector(packageNameCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];
[cell.packageNameCheckBox setBackgroundImage:[UIImage imageNamed:@"check.png"] forState:UIControlStateSelected];
 [cell.packageNameCheckBox setBackgroundImage:[UIImage imageNamed:@"uncheck.png"] forState:UIControlStateNormal];
[cell.packageNameCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域



[cell.packageNameCheckBoxCover setText:nil ];
//60
[cell.packageMoneyTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",D44_70_MONEY1]];
//周期:
[cell.packagePeriodTitleTextView setText:null];
//6个月
[cell.packagePeriodValueTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",D44_70_CSTMPACK_FEE_PERIOD]];
//right
[rightImageView setImage:[UIImage imageNamed:@"1.jpeg"]]
[rightImageView setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@"default.jpg"]];
return cell;
 }
    
}

//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

NSString *reuseIdentifier = MyCanAddPackageCellIdentifier;
MyCanAddPackageTableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];
if (!cell) {
  cell=[self.tableView dequeueReusableCellWithIdentifier:MyCanAddPackageCellIdentifier];
  [self.cacheCells setObject:cell forKey:reuseIdentifier];
}


//业务包名称
cell.packageNameCheckBox.tag=;
 objc_setAssociatedObject(cell.packageNameCheckBox, "mId", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定
[cell.packageNameCheckBox setSelected:];

[cell.packageNameCheckBox addTarget:self action:@selector(packageNameCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];
[cell.packageNameCheckBox setBackgroundImage:[UIImage imageNamed:@"check.png"] forState:UIControlStateSelected];
 [cell.packageNameCheckBox setBackgroundImage:[UIImage imageNamed:@"uncheck.png"] forState:UIControlStateNormal];
[cell.packageNameCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域



[cell.packageNameCheckBoxCover setText:nil ];
//60
[cell.packageMoneyTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",D44_70_MONEY1]];
//周期:
[cell.packagePeriodTitleTextView setText:null];
//6个月
[cell.packagePeriodValueTextView setText:[NSString stringWithFormat:@"￥[%.2f]元",D44_70_CSTMPACK_FEE_PERIOD]];
//right
[rightImageView setImage:[UIImage imageNamed:@"1.jpeg"]]
[rightImageView setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@"default.jpg"]];

//分页Start(可注调)
if([indexPath row] == ([rows count])  && [rows count]>0) {
if( currentRowCount<totalRowCount)
  {//LoadMoreView
     return 0;
 }else
 {
    return 0;
 }
}else  if([indexPath row] == ([rows count])  && [rows count]==0) {
   return 0;
}
 else  //分页End

 {
   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   
return height+1;
 }
}

- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 88;
}

//点击后，过段时间cell自动取消选中
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
if (indexPath.row == [rows count]  && [rows count]>0) {
[self request9999:YES];
   return;
}else{
}
    //消除cell选择痕迹
    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];
}
- (void)deselect
{
    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];
}



/*可办理套餐查询8010004*/
NSString  *n8010004=@"8010004";
/*可办理套餐查询8010004*/
-(void) request8010004{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 套餐名称 备注:40*/
[businessparam setValue:@"" forKey:@"D44_70_PACKETNAME"];
/* 排序方式 备注:201：价格从高倒地02：价格从低到高*/
[businessparam setValue:@"" forKey:@"D44_70_FIELD_SORTID"];
/* 服务周期 备注:201：1个月02：3个月03：6个月04：12个月*/
[businessparam setValue:@"" forKey:@"D44_70_CODE_TYPE"];
/* 服务区域城市代号 备注:15*/
[businessparam setValue:@"" forKey:@"D44_70_CITYCOE"];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
/* 页码 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PAGENUM"];
/* 页码大小 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PAGECODE"];
/*  备注:*/
[businessparam setValue:@"" forKey:@""];
 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8010004  delegate:self   viewController:self];
}

 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {



NSMutableArray *listData8010004=[[NSMutableArray alloc]init];
/*可办理套餐查询8010004*/
if ([msgReturn.formName isEqualToString:n8010004]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8010004 *commonItem=[[RespondParam8010004 alloc]init];
/* 总记录数 备注:*/
commonItem.D44_70_MAXRECORD=[[returnDataBody objectForKey:@"D44_70_MAXRECORD"]intValue];
/* 循环域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/*  备注:*/
commonItem.=[returnDataBody objectForKey:@""];
/* 循环域结束 备注:*/
commonItem.D44_70_RECORDNUM1=[returnDataBody objectForKey:@"D44_70_RECORDNUM1"];
/* 循环域开始 备注:*/
int D44_70_RECORDNUM= [[returnDataBody objectForKey:@"D44_70_RECORDNUM"]intValue];
for(int i=0;i<D44_70_RECORDNUM;i++)
{
RespondParam8010004 *item1=[[RespondParam8010004 alloc]init];
/* 业务包号 备注:3*/
item1.D44_70_PACKETID=[[returnDataBody objectForKey:@"D44_70_PACKETID"] objectAtIndex:i];
/* 业务包名称 备注:40*/
item1.D44_70_PACKETNAME=[[returnDataBody objectForKey:@"D44_70_PACKETNAME"] objectAtIndex:i];
/* 业务包描述 备注:100*/
item1.D44_70_DESC=[[returnDataBody objectForKey:@"D44_70_DESC"] objectAtIndex:i];
/* 客户对象标志 备注:1*/
item1.D44_70_CSTMPACK_OBJECT=[[returnDataBody objectForKey:@"D44_70_CSTMPACK_OBJECT"] objectAtIndex:i];
/* 业务包累加模式 备注:1*/
item1.D44_70_CSTMPACK_COUNT_MODE=[[returnDataBody objectForKey:@"D44_70_CSTMPACK_COUNT_MODE"] objectAtIndex:i];
/* 业务包地域模式 备注:1*/
item1.D44_70_CSTMPACK_AREA_MODE=[[returnDataBody objectForKey:@"D44_70_CSTMPACK_AREA_MODE"] objectAtIndex:i];
/* 业务包计费模式 备注:1*/
item1.D44_70_PACKET_MODE=[[returnDataBody objectForKey:@"D44_70_PACKET_MODE"] objectAtIndex:i];
/* 业务包计费周期 备注:*/
item1.D44_70_CSTMPACK_FEE_PERIOD=[[[returnDataBody objectForKey:@"D44_70_CSTMPACK_FEE_PERIOD"] objectAtIndex:i]intValue];
/* 周期收费标准 备注:*/
item1.D44_70_MONEY1=[[[returnDataBody objectForKey:@"D44_70_MONEY1"] objectAtIndex:i]intValue];
/* 有效标志 备注:1*/
item1.D44_70_VALID=[[returnDataBody objectForKey:@"D44_70_VALID"] objectAtIndex:i];
/* 商品代号 备注:20*/
item1.D44_70_MERCH_ID=[[returnDataBody objectForKey:@"D44_70_MERCH_ID"] objectAtIndex:i];
[listData8010004 addObject:item1];
}

/* 循环域开始 备注:20160619新增*/
int D44_70_RECORDNUM1= [[returnDataBody objectForKey:@"D44_70_RECORDNUM1"]intValue];
for(int i=0;i<D44_70_RECORDNUM1;i++)
{
RespondParam8010004 *item2=[[RespondParam8010004 alloc]init];
/* 业务包号 备注:3*/
item2.D44_70_PACKETIDNEW=[[returnDataBody objectForKey:@"D44_70_PACKETIDNEW"] objectAtIndex:i];
/* 覆盖市局 备注:8*/
item2.D44_70_CITYBRCH=[[returnDataBody objectForKey:@"D44_70_CITYBRCH"] objectAtIndex:i];
/* 市局名称 备注:50*/
item2.D44_70_NAME1=[[returnDataBody objectForKey:@"D44_70_NAME1"] objectAtIndex:i];
[listData8010004 addObject:item2];
}

}

  }//end ReturnData

-(void)packageNameCheckBoxCheck:(UIButton *)btn{
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

@end//end viewController
-(void) request9999:(BOOL)ismore{

//分页Start
if(ismore)
{
if (requestUnComplete==false) {
requestUnComplete=true;
}else
{
return;
}
}else //分页End 

{
totalRowCount=0;
currentRowCount=0;
page=1;
 [rows removeAllObjects];
if(allIndexpaths!=nil && [allIndexpaths count]>0)
{
 [self.tableView deleteRowsAtIndexPaths:allIndexpaths withRowAnimation:UITableViewRowAnimationFade];
}
 [ allIndexpaths  removeAllObjects];

}

....(请求内容)
}


//九宫图列表数据(九宫图列表用到)Start
@interface Section : NSObject
@property (strong,nonatomic) NSString *title;
@property (strong,nonatomic) NSString *sectionId;
@property (strong,nonatomic) NSMutableArray *sectionRows;
@end

@interface Row : NSObject
@property (strong,nonatomic) NSMutableArray *rowChirlds;
@end

@interface Chirld : NSObject
@property (strong,nonatomic) NSString *pic;
@property (strong,nonatomic) NSString *picName;
@property (strong,nonatomic) NSString *picPrice;
@property (strong,nonatomic) NSString *productId;
@end

//九宫图列表数据(九宫图列表用到)End

-(void) respond9999{
....(返回数据)
requestUnComplete=false;//避免重复请求 一个发完下一个再发
//九宫图列表数据Start
Row *sectionRow;
NSMutableArray *thisPageRows=[[NSMutableArray alloc] init];
			    for (int i=0; i<[mdata count]; i++) {
			        RespondParam0027 *commonItem2=mdata[i];
			        
			        
			        if (i==0 || i%3==0) {//每行3个
			            sectionRow=[[Row alloc ] init];
			            sectionRow.rowChirlds=[[NSMutableArray alloc]init];
			            [thisPageRows addObject:sectionRow];
			            [rows addObject:sectionRow];
			        }
			        
			        Chirld *rowChirld=[[Chirld alloc] init ];
			        rowChirld.productId=commonItem2.merchID;
			        rowChirld.pic=commonItem2.merchPicID;
			        rowChirld.picName=commonItem2.merchName;
			        rowChirld.picPrice=[NSString stringWithFormat:@"%.2f",commonItem2.merchPrice] ;
			        
			        //chirld add
			        [sectionRow.rowChirlds addObject:rowChirld];
			        
			        
			    }

//在函数中-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath  添加使用九宫图结果
Row *row=thisPageRows[indexPath.row];
if ([row.rowChirlds count]>0) {//第一列 
}
else{
 [cell.picButton setTitle:@"" forState:UIControlStateNormal ];
[cell.picButton setTitle:@"" forState:UIControlStateSelected ];
 }
if ([row.rowChirlds count]>1) {//第二列 } 

//九宫图列表数据End

//分页Start
totalRowCount=commonItem.totalNum;
currentRowCount+=commonItem.recordNum;
if (commonItem.recordNum>0) {
    if (currentRowCount< totalRowCount) {
        page++;
    }
}else if(commonItem.recordNum==0)
{
// 暂无数据
}
//分页End

NSMutableArray *insertIndexPaths = [[NSMutableArray alloc]init];
for (int ind = 0; ind < [thisPageRows count]; ind++) {
    NSIndexPath    *newPath =  [NSIndexPath indexPathForRow:[rows indexOfObject:[thisPageRows objectAtIndex:ind]] inSection:0];
    [allIndexpaths addObject:newPath];
    [insertIndexPaths addObject:newPath];
}
[self.tableView insertRowsAtIndexPaths:insertIndexPaths withRowAnimation:UITableViewRowAnimationFade];
}




