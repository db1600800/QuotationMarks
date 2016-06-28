package com.compoment.addfunction.iphone;

public class BaseSelecter {
	
	
	public void viewControllerH()
	{
		String m="";
		
		m+="#import <UIKit/UIKit.h>\n";
		m+="#import \"StampTranCall.h\"\n";
		
		m+="@protocol SelectDialogChirldViewCallBackDelegate;\n";
		m+="@interface SelectDialogViewController : BaseViewController<UITableViewDataSource,UITableViewDelegate,StampTranCallDelegate>\n";

		m+="{\n";
		m+="    int page;\n";
		m+="    int totalRowCount;\n";
		m+="    int currentRowCount;\n";
		m+="    bool requestUnComplete;//发完一个请求再发下一个\n";
		m+="    NSMutableArray *allIndexpaths;\n";
		m+="    NSMutableArray *rows;\n";
		m+="    NSMutableArray *chirldViewData;\n";
		m+="    \n";
		
		m+="    \n";
		m+="}\n";
	
		
		m+="//back\n";
		m+="@property (weak, nonatomic) IBOutlet UIButton *backButton;\n";
		m+="//list\n";
		m+="@property (weak, nonatomic) IBOutlet UITableView *tableView;\n";
		m+="@property (strong, nonatomic) NSMutableDictionary *cacheCells;\n";
		m+="//选择\n";
		m+="@property (weak, nonatomic) IBOutlet UIButton *selecInsuranceButton;\n";
		m+="@property (strong,nonatomic) id<SelectDialogChirldViewCallBackDelegate> chirldViewCallBackDelegate;\n";
		m+="-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<SelectDialogChirldViewCallBackDelegate>)parent;\n";
		m+="@end\n";
		m+="@protocol SelectDialogChirldViewCallBackDelegate <NSObject>\n";
		m+="-(void) chirldViewCallBack_selectCompany:(NSMutableArray*)mdata;\n";
		m+="@end\n";
		
	}
	
	
	public void viewControllerM()
	{
		String m="";
		m+="//注入网络请求,响应,等待提示\n";



		m+="#import \"SelectInsuranceDialogViewController.h\"\n";
		m+="#import \"UIImageView+WebCache.h\"\n";
		m+="#import <Foundation/Foundation.h>\n";
		m+="#import <JSONKit.h>\n";
		m+="#import <objc/runtime.h>\n";
		m+="#import \"UIButton+EnlargeTouchArea.h\"\n";
		m+="#import \"SelectInsuranceDialogTableViewCell.h\"\n";
		m+="#import \"RespondParam8056110.h\"\n";

		m+="//注入table功能\n";
		m+=" NSString *SelectInsuranceDialogCellIdentifier = @\"SelectInsuranceDialogTableViewCell\";\n";
		m+=" NSString *SelectInsuranceDialogCellHeadIdentifier = @\"SelectInsuranceDialogTableViewCellHead\";\n";

		m+="@implementation SelectInsuranceDialogViewController\n";
		m+="@synthesize citycode;\n";
		m+="@synthesize cacheCells;\n";
		m+="//back\n";
		m+="@synthesize backButton;\n";
		m+="//list\n";
		m+="@synthesize tableView;\n";
		m+="//选择保险公司\n";
		m+="@synthesize selecInsuranceButton;\n";
		m+="- (void)viewDidLoad\n";
		m+="{\n";
		m+="    [super viewDidLoad];\n";

		m+="//start  TableView \n";
		m+="totalRowCount=0;\n";
		m+="currentRowCount=0;\n";
		m+="page=1;\n";

		m+="allIndexpaths=[[NSMutableArray alloc] init];\n";
		m+=" rows=[[NSMutableArray alloc] init];\n";
		m+="    [self.tableView setDelegate:self];//tableview委托\n";
		m+="    [self.tableView setDataSource:self];//tableview数据委托\n";
		m+="    self.tableView.tableFooterView=[[UIView alloc]init];//tableview去除多余的分隔线\n";
		m+="    //使用自定义的Cell,需要向UITableView进行注册\n";
		m+="    UINib *cellNib = [UINib nibWithNibName:@\"SelectInsuranceDialogTableViewCell\" bundle:nil];\n";
		m+="    [tableView registerNib:cellNib forCellReuseIdentifier:SelectInsuranceDialogCellIdentifier];\n";
		m+="     cacheCells = [NSMutableDictionary dictionary];\n";
		m+="//end TableView \n";


		m+="//back\n";
		m+="[self.backButton addTarget:self action:@selector(backButtonClicked:) forControlEvents:UIControlEventTouchUpInside];\n";
		m+="[self.backButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域\n";


		m+="//选择保险公司\n";

		m+="[self.selecInsuranceButton addTarget:self action:@selector(selecInsuranceButtonClicked:) forControlEvents:UIControlEventTouchUpInside];\n";
		m+="    \n";
		m+="   \n";
		m+="}\n";

		m+="-(void) viewWillAppear:(BOOL)animated{\n";
		m+="     [super viewWillAppear:animated];\n";
		m+="//table\n";
		m+="[self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
		m+="    \n";
		m+="    // [self  request8056110];\n";
		m+="}\n";

		m+="-(void)backButtonClicked:(UIButton *)btn{\n";
		m+="id mId = objc_getAssociatedObject(btn, \"mId\");\n";
		m+="//取绑定数据int mId2 = btn.tag;\n";
		m+="//取绑定数据//方法3.回到上页面\n";
		m+=" // [self.navigationController popViewControllerAnimated:YES];\n";
		m+="[self.view setHidden:YES];\n";
		m+="}\n";

		m+="-(void)selecInsuranceButtonClicked:(UIButton *)btn{\n";
		m+="id mId = objc_getAssociatedObject(btn, \"mId\");\n";
		m+="//取绑定数据int mId2 = btn.tag;\n";
		m+="//取绑定数据//方法3.回到上页面\n";
		m+="  //[self.navigationController popViewControllerAnimated:YES];\n";
		m+="    [self.view setHidden:YES];\n";
		m+="    \n";
		m+="    [self.chirldViewCallBackDelegate chirldViewCallBack_selectCompany:rows];\n";
		m+="}\n";


		m+="-(void)viewWillLayoutSubviews\n";
		m+="{\n";
		m+="//int startY=self.headView.frame.origin.y+self.headView.frame.size.height;\n";
		m+="// [self.tableView setFrame:CGRectMake(0, startY, self.tableView.frame.size.width, self.view.frame.size.height-startY )];\n";
		m+="}\n";

		m+="//指定有多少个分区(Section)，默认为1\n";
		m+="- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {\n";
		m+="    \n";
		m+="    return 1;//返回标题数组中元素的个数来确定分区的个数   return [sections count];\n";
		m+="}\n";

		m+="//指定每个分区中有多少行，默认为1\n";
		m+="- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{\n";
		m+="    \n";
		m+="     return  [rows count];\n";
		m+="    \n";
		m+="}\n";

		m+="//绘制Cell\n";
		m+="-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {\n";


		m+=" {\n";
		m+=" SelectInsuranceDialogTableViewCell *cell = (SelectInsuranceDialogTableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:SelectInsuranceDialogCellIdentifier];\n";
		m+="    if (!cell)\n";
		m+="    {\n";
		m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\"SelectInsuranceDialogTableViewCell\" owner:self options:nil] lastObject];\n";
		m+="    }\n";
		m+="     \n";
		m+="     \n";
		m+="     RespondParam8056110 *row=rows[indexPath.row];\n";

		m+="//中国人寿\n";
		m+="cell.insurancenameCheckBox.tag=indexPath.row;\n";
		m+="     [cell.insurancenameCheckBox setSelected:row.isSelect];\n";
		m+="[cell.insurancenameCheckBox addTarget:self action:@selector(insurancenameCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];\n";
		m+="[cell.insurancenameCheckBox setBackgroundImage:[UIImage imageNamed:@\"check.png\"] forState:UIControlStateSelected];\n";
		m+=" [cell.insurancenameCheckBox setBackgroundImage:[UIImage imageNamed:@\"uncheck.png\"] forState:UIControlStateNormal];\n";
		m+="[cell.insurancenameCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域\n";
		m+="     \n";
		m+="     \n";
		m+="     //名称\n";
		m+="    [ cell.insurancenameCheckBoxCover setText:row.D44_70_BX_COMPANY];\n";

		m+="//人寿\n";

		m+="[cell.insurancePicImageView setImageWithURL:[NSURL URLWithString:row.D44_70_PICID ] placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";
		m+="return cell;\n";
		m+=" }\n";
		m+="    \n";
		m+="}\n";

		m+="//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度\n";
		m+="- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{\n";

		m+="NSString *reuseIdentifier = SelectInsuranceDialogCellIdentifier;\n";
		m+="SelectInsuranceDialogTableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];\n";
		m+="if (!cell) {\n";
		m+="  cell=[self.tableView dequeueReusableCellWithIdentifier:SelectInsuranceDialogCellIdentifier];\n";
		m+="  [self.cacheCells setObject:cell forKey:reuseIdentifier];\n";
		m+="}\n";



		m+=" \n";
		m+="   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   \n";
		m+="return height+1;\n";
		m+=" \n";
		m+="}\n";

		m+="- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
		m+="    return 88;\n";
		m+="}\n";

		m+="//点击后，过段时间cell自动取消选中\n";
		m+="- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{\n";

		m+="    RespondParam8056110 *row=rows[indexPath.row];\n";
		m+="    row.isSelect=!row.isSelect;\n";
		m+="    [tableView reloadData];\n";
		m+="    //消除cell选择痕迹\n";
		m+="    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];\n";
		m+="}\n";
		m+="- (void)deselect\n";
		m+="{\n";
		m+="    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
		m+="}\n";
		m+="-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<SelectInsuranceDialogChirldViewCallBackDelegate>)parent{\n";
		m+="   self.chirldViewCallBackDelegate=parent;\n";
		m+="   rows=mdata;\n";
		m+="    [tableView reloadData];\n";
		m+="}\n";

		m+="-(void)insurancenameCheckBoxCheck:(UIButton *)btn{\n";
		m+="//id mId = objc_getAssociatedObject(btn, \"mId\");\n";
		m+="int mId2 = btn.tag;\n";
		m+="//取绑定数据  btn.selected = !btn.selected ;//用与button做checkBox\n";
		m+="    \n";
		m+="     RespondParam8056110 *row=rows[mId2];\n";
		m+="    row.isSelect=!row.isSelect;\n";

		m+="[tableView reloadData];\n";
		m+="}\n";




	
		m+="     \n";
		m+="     }\n";
		m+=" \n";
		m+=" \n";
		m+="@end//end viewController\n";






	}
	

}
