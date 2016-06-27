#import "MyPackageViewController .h"
#import "MsgReturn .h"
#import "MsgReturn .h"
#import "MsgReturn .h"
#import "MoreTableViewCell.h"
#import "MyPackageTableViewCell.h"
#import "ServiceInvoker .h"
#import "RespondParam8075600 .h"
#import "Row .h"
#import "Chirld .h"
//注入网络请求,响应,等待提示
#import "MyPackageViewController .h"
#import "MsgReturn .h"
#import "MsgReturn .h"
#import "MsgReturn .h"



#import "MyPackageViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

#import "MyPackageTableViewCell.h"
//注入table功能
 NSString *MyPackageCellIdentifier = @"MyPackageTableViewCell";
 NSString *MyPackageCellHeadIdentifier = @"MyPackageTableViewCellHead";

@implementation MyPackageViewController
@synthesize cacheCells;

//list
@synthesize tableView;
//back
@synthesize backButton;
//我的套餐
@synthesize titleTextView;
//添加套餐
@synthesize addPackageButton;
//全部
@synthesize allButton;
//有效
@synthesize useButton;
//过期
@synthesize unuseButton;

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
    UINib *cellNib = [UINib nibWithNibName:@"MyPackageTableViewCell" bundle:nil];
    [tableView registerNib:cellNib forCellReuseIdentifier:MyPackageCellIdentifier];
     cacheCells = [NSMutableDictionary dictionary];
//end TableView 


//back
[self.backButton addTarget:self action:@selector(backButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
[self.backButton setBackgroundImage:[UIImage imageNamed:@"backSelect.png"] forState:UIControlStateSelected];
 [self.backButton setBackgroundImage:[UIImage imageNamed:@"back.png"] forState:UIControlStateNormal];
[self.backButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域

//我的套餐
[self.titleTextView setText:null];

//添加套餐
[self.addPackageButton addTarget:self action:@selector(addPackageButtonClicked:) forControlEvents:UIControlEventTouchUpInside];

//全部
[self.allButton addTarget:self action:@selector(allButtonClicked:) forControlEvents:UIControlEventTouchUpInside];

//有效
[self.useButton addTarget:self action:@selector(useButtonClicked:) forControlEvents:UIControlEventTouchUpInside];

//过期
[self.unuseButton addTarget:self action:@selector(unuseButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
}

-(void) viewWillAppear:(BOOL)animated{
//table
[self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];
}

-(void)backButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据//方法3.回到上页面
  [self.navigationController popViewControllerAnimated:YES];

}

-(void)addPackageButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 self.hidesBottomBarWhenPushed=YES;
MyPackageViewController *myPackageViewController=[[MyPackageViewController alloc ] initWithNibName:@"MyPackageViewController" bundle:nil];
    [self.navigationController pushViewController:myPackageViewController animated:YES];

}

-(void)allButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 MsgReturn *msgReturn=[[MsgReturn alloc]init];
 msgReturn.errorCode=@"-1";//-1显示自定义内容
 msgReturn.errorType=@"02";
 msgReturn.errorDesc=@"请输入搜索内容";
 [PromptError changeShowErrorMsg:msgReturn title:@""  viewController:self block:^(BOOL OKCancel){}];

[self requestnull];

}

-(void)useButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 MsgReturn *msgReturn=[[MsgReturn alloc]init];
 msgReturn.errorCode=@"-1";//-1显示自定义内容
 msgReturn.errorType=@"02";
 msgReturn.errorDesc=@"请输入搜索内容";
 [PromptError changeShowErrorMsg:msgReturn title:@""  viewController:self block:^(BOOL OKCancel){}];

[self requestnull];

}

-(void)unuseButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据 MsgReturn *msgReturn=[[MsgReturn alloc]init];
 msgReturn.errorCode=@"-1";//-1显示自定义内容
 msgReturn.errorType=@"02";
 msgReturn.errorDesc=@"请输入搜索内容";
 [PromptError changeShowErrorMsg:msgReturn title:@""  viewController:self block:^(BOOL OKCancel){}];

[self requestnull];

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
 MyPackageTableViewCell *cell = (MyPackageTableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:MyPackageCellIdentifier];
    if (!cell)
    {
       cell = [[[NSBundle mainBundle] loadNibNamed:@"MyPackageTableViewCell" owner:self options:nil] lastObject];
    }
//未启用
[cell.packageStatusTextView setText:null];
//2013秋冬自驾游
[cell.packageNameTextView setText:null];
//有效日期:
[cell.dateTitleTextView setText:null];
//2013111
[cell.startDateTextView setText:null];
//-
[cell.lineTextView setText:null];
//2014
[cell.endDateTextView setText:null];
//right
[rightImageView setImage:[UIImage imageNamed:@"1.jpeg"]]
[rightImageView setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@"default.jpg"]];
return cell;
 }
    
}

//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

NSString *reuseIdentifier = MyPackageCellIdentifier;
MyPackageTableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];
if (!cell) {
  cell=[self.tableView dequeueReusableCellWithIdentifier:MyPackageCellIdentifier];
  [self.cacheCells setObject:cell forKey:reuseIdentifier];
}

//未启用
[cell.packageStatusTextView setText:null];
//2013秋冬自驾游
[cell.packageNameTextView setText:null];
//有效日期:
[cell.dateTitleTextView setText:null];
//2013111
[cell.startDateTextView setText:null];
//-
[cell.lineTextView setText:null];
//2014
[cell.endDateTextView setText:null];
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



/*会员已购买套餐查询8075600*/
NSString  *n8075600=@"8075600";
/*会员已购买套餐查询8075600*/
-(void) request8075600{
NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];
/* 会员号 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_CUSTMNUM"];
/* 查询范围 备注:0：有效套餐
1:过期套餐
2：未启用套餐
3：所有套餐*/
[businessparam setValue:@"" forKey:@"D44_70_VALID"];
/* 页码 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PAGENUM"];
/* 页码大小 备注:*/
[businessparam setValue:@"" forKey:@"D44_70_PAGECODE"];
 ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];
 [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n8075600  delegate:self   viewController:self];
}

 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {



NSMutableArray *listData8075600=[[NSMutableArray alloc]init];
/*会员已购买套餐查询8075600*/
if ([msgReturn.formName isEqualToString:n8075600]){
NSString *returnCode=[root objectForKey:@"returnCode"];
NSDictionary *returnData=[root objectForKey:@"returnData"];
NSDictionary *head=[returnData objectForKey:@"head"];
NSString *ret_errcode=[head objectForKey:@"ret_errcode"];
NSString *ret_msg=[head objectForKey:@"ret_msg"];
NSDictionary *body=[returnData objectForKey:@"body"];
RespondParam8075600 *commonItem=[[RespondParam8075600 alloc]init];
/* 总记录数 备注:*/
commonItem.D44_70_MAXRECORD=[[returnDataBody objectForKey:@"D44_70_MAXRECORD"]intValue];
/* 重复域开始 备注:*/
commonItem.D44_70_RECORDNUM=[[returnDataBody objectForKey:@"D44_70_RECORDNUM"]intValue];
/* 套餐加办流水 备注:*/
commonItem.D44_70_SEQNO=[[returnDataBody objectForKey:@"D44_70_SEQNO"]intValue];
/* 套餐号 备注:*/
commonItem.D44_70_PACKETID=[returnDataBody objectForKey:@"D44_70_PACKETID"];
/* 套餐名 备注:*/
commonItem.D44_70_PACKETNAME=[returnDataBody objectForKey:@"D44_70_PACKETNAME"];
/* 起始有效期 备注:*/
commonItem.D44_70_BEGINDATE=[returnDataBody objectForKey:@"D44_70_BEGINDATE"];
/* 截止有效期 备注:*/
commonItem.D44_70_ENDDATE=[returnDataBody objectForKey:@"D44_70_ENDDATE"];
/* 套餐加办日期 备注:*/
commonItem.D44_70_TRAN_DATE=[returnDataBody objectForKey:@"D44_70_TRAN_DATE"];
/* 套餐绑定内容 备注:*/
commonItem.D44_70_BIG_DESC=[returnDataBody objectForKey:@"D44_70_BIG_DESC"];
/* 套餐状态 备注:*/
commonItem.D44_70_NEW_STATUS=[returnDataBody objectForKey:@"D44_70_NEW_STATUS"];
/* 重复域结束 备注:*/
commonItem.=[returnDataBody objectForKey:@""];
}

  }//end ReturnData

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




