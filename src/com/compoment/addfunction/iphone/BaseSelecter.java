package com.compoment.addfunction.iphone;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class BaseSelecter {
	String viewControllerName ;
	
	
	public BaseSelecter(String viewControllerName )
	{
		 this.viewControllerName=viewControllerName+"Selector" ;
		
		 viewControllerH();
		 viewControllerM();
		 viewControllerXib();
		 cellH();
		 cellM();
		 cellXib();
		 
		
		 
	}
	
	public String use()
	{
		String m="";
		m+="//父亲ViewController实现接口  "+viewControllerName+"ChirldViewCallBackDelegate>\n";
		m+="//1. "+viewControllerName+"ChirldViewCallBackDelegate\n";

		m+="//-(void) chirldViewCallBack:(NSMutableArray*)mdata;\n";


		m+="//2.在viewDidLoad中\n";


		m+="//chirldViewController=[["+viewControllerName+"ViewController alloc ] initWithNibName:@\""+viewControllerName+"ViewController\" bundle:nil];\n";
		m+="//chirldViewController.view.frame=CGRectMake(,,,);\n";
		m+="//[chirldViewController setChirldViewValue:nil delegate:self];\n";
		m+="//[ self.view addSubview:chirldViewController.view];\n";
		m+="// [chirldViewController.view setHidden:YES];\n";
		
		return m;
		
	}
	
	public String commonSelector()
	{
		String m="";
		m+=use();
		m+=" //SqlApp *sqlapp = [[SqlApp alloc]init];\n";
		m+="  //NSMutableArray *provinces = [sqlapp queryCityMSG:@\"000000\" withLevel:@\"2\"];//省\n";
		m+="  //NSMutableArray *selectors =[[NSMutableArray alloc]init]; \n";
		m+="  //SqlRow *sqlRow =[[SqlRow alloc]init]; \n";
		return m;
	}
	
	
	public String proCityCountySqlApp()
	{
		
		

		
		
		String m1="";
		m1+=use();
		
		
		
		m1+=" SqlApp *sqlapp = [[SqlApp alloc]init];\n";
		m1+="  NSMutableArray *provinces = [sqlapp queryCityMSG:@\"000000\" withLevel:@\"2\"];//省\n";
	    m1+="  NSMutableArray *citys = [sqlapp queryCityMSG:proCode withLevel:@\"3\"];//市\n";
	    m1+="  NSMutableArray *countys = [sqlapp queryCityMSG:cityCode withLevel:@\"4\"];//县\n";
	    
	    
	    String m2="";
	    m2+="#import <Foundation/Foundation.h>\n";
	    m2+="#import \"sqlite3.h\"\n";
	    m2+="@interface SqlQueryCity : NSObject\n";
	    m2+="{\n";
	    m2+="    \n";
	    m2+="    sqlite3 *db;\n";
	    m2+="}\n";

	    
	    m2+="-(BOOL) openDB;\n";
	    m2+="-(NSMutableArray*) queryCityMSG:(NSString*) code withLevel:(NSString*)level;\n";
	    m2+="-(NSMutableArray*) queryCountyNameWithCountyCode:(NSString*) arearCode;\n";
	    m2+="-(NSMutableArray*) queryCityNameWithCountyCode:(NSString*) arearCode;\n";
	    m2+="-(NSMutableArray*) queryProNameWithCountyCode:(NSString*) arearCode;\n";
	    m2+="-(NSString*) queryCityNameWithRegionid:(NSString*) code;\n";
	    m2+="@end\n";
	    
		FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios",   "SqlQueryCity", "h", m2);
		    
		String m="";
		m+="//SqlApp.m\n";
		m+="@implementation SqlQueryCity\n";
		
		m+="-(BOOL) openDB{\n";
		m+="    \n";
		m+="    \n";
		m+="    NSString *path = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];   \n";
		m+="    path = [path stringByAppendingPathComponent:@\"securedDirectory/postservice_IOS.db\"];\n";
		m+="    \n";
		m+="//    NSString *path =[[NSBundle mainBundle] pathForResource:@\"POST_JY\" ofType:@\"db\"];\n";
		m+="    \n";
		m+="    //获取数据库路径\n";
		m+="    //    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);\n";
		m+="    //    NSString *documents = [paths objectAtIndex:0];\n";
		m+="    //    NSString *database_path = [documents stringByAppendingPathComponent:DBNAME];\n";
		m+="    \n";
		m+="    //如果数据库存在，则用sqlite3_open直接打开（不要担心，如果数据库不存在sqlite3_open会自动创建）\n";
		m+="    //打开数据库，这里的[path UTF8String]是将NSString转换为C字符串，因为SQLite3是采用可移植的C(而不是\n";
		m+="    //Objective-C)编写的，它不知道什么是NSString.\n";
		m+="    if (sqlite3_open([path UTF8String], &db) == SQLITE_OK) {\n";
		m+="        return YES;\n";
		m+="    }else{\n";
		m+="        NSLog(@\"数据库打开失败\");\n";
		m+="        sqlite3_close(db);\n";
		m+="        return NO;\n";
		m+="    }\n";
		m+="}\n";

		
		
		
		m+="-(NSMutableArray*) queryCityMSG:(NSString*) code withLevel:(NSString*)level{\n";
		m+="   //level 2省  3市  4县     code:上一级的编码(如查市时传入省的code)\n";
		m+="    [self openDB];\n";
		m+="    \n";
		m+="     NSString *sqlQuery=@\"\";\n";
		m+="    if ([level isEqualToString:@\"2\"]) {//省\n";
		m+="         sqlQuery = [NSString stringWithFormat:\n";
		m+="                              @\"SELECT * FROM PM_CITYCODE  where not CITYCODE = '000000000000000' and CITYCODE like '%%000000000' \"];\n";
		m+="        \n";
		m+="    }else  if ([level isEqualToString:@\"3\"]) {//市\n";
		m+="        \n";
		m+="       NSString *part1= [code substringWithRange:NSMakeRange(0, 6)];\n";
		m+="        NSString *part2=[code substringWithRange:NSMakeRange(9, 6)];\n";
		m+="        \n";
		m+="         sqlQuery = [NSString stringWithFormat:\n";
		m+="                              @\"SELECT * FROM PM_CITYCODE a where not a.CITYCODE = '%@' and a.CITYCODE like '%@'  \",code,[NSString stringWithFormat:@\"%@___%@\",part1,part2]];\n";
		m+="        \n";
		m+="    }else  if ([level isEqualToString:@\"4\"]) {//县\n";
		m+="        \n";
		m+="        NSString *part1= [code substringWithRange:NSMakeRange(0, 9)];\n";
		m+="  \n";
		m+="        \n";
		m+="         sqlQuery = [NSString stringWithFormat:\n";
		m+="                              @\"SELECT * FROM PM_CITYCODE a where not a.CITYCODE = '%@' and a.CITYCODE like '%@%%' \",code,part1];\n";
		m+="        \n";
		m+="    }\n";
		m+="    \n";
		m+=" \n";
		m+="    \n";
		m+="    sqlite3_stmt * statement;\n";
		m+="    NSMutableArray *rows=[[NSMutableArray alloc]init];\n";
		m+="    \n";
		m+="    \n";
		m+="    if (sqlite3_prepare_v2(db, [sqlQuery UTF8String], -1, &statement, nil) == SQLITE_OK) {\n";
		m+="        \n";
		m+="        //查询结果集中一条一条的遍历所有的记录，这里的数字对应的是列值,注意这里的列值\n";
		m+="        \n";
		m+="        while (sqlite3_step(statement) == SQLITE_ROW) {\n";
		m+="            \n";
		m+="           SqlRow *sqlRow=[[SqlRow alloc]init]; \n\n";
		
		m+="            char *superioridCHAR = (char*)sqlite3_column_text(statement, 0);\n";
		m+="            NSString *strsuperiorid = [[NSString alloc]initWithUTF8String:superioridCHAR];\n";
		m+="            sqlRow.KEY=strsuperiorid;\n";
		m+="            \n";
		m+="            char *regionidchar = (char*)sqlite3_column_text(statement, 1);\n";
		m+="            NSString *regionidstring = [[NSString alloc]initWithUTF8String:regionidchar];\n";
		m+="            sqlRow.ID=regionidstring;\n";

		m+="            \n";
		m+="            char *regionnamechar = (char*)sqlite3_column_text(statement, 2);\n";
		m+="            NSString *regionnamestring = [[NSString alloc]initWithUTF8String:regionnamechar];\n";
		m+="            sqlRow.NAME =regionnamestring;\n";
		m+="            \n";
		m+="           [rows addObject:sqlRow]; \n";
		m+="        }\n";
		m+="    }else{\n";
		m+="        NSLog(@\"select error:%@\",sqlQuery);\n";
		m+="        \n";
		m+="    }\n";
		m+="    sqlite3_close(db);\n";

		m+="    return rows;\n";
		m+="}\n";
		
		
		
		
		
		m+="-(NSMutableArray*) queryProNameWithCountyCode:(NSString*) arearCode{\n";
		m+="    \n";
		m+="    \n";
		m+="    NSString *pro=[arearCode substringWithRange:NSMakeRange(0, 6)];\n";
		m+="    \n";
		m+="    NSMutableArray *propro= [self  queryCityNameWithRegionid:[NSString stringWithFormat:@\"%@000000000\",pro]];\n";
		m+="    return propro;\n";
		m+="    \n";
		m+="}\n";

		m+="-(NSMutableArray*) queryCityNameWithCountyCode:(NSString*) arearCode{\n";
		m+="    \n";
		m+="    \n";
		m+="    NSString *city=[arearCode substringWithRange:NSMakeRange(0, 9)];\n";
		m+="    \n";
		m+="    NSMutableArray *citycity= [self queryCityNameWithRegionid:[NSString stringWithFormat:@\"%@000000\",city]];\n";
		m+="    return citycity;\n";
		m+="}\n";


		m+="-(NSMutableArray*) queryCountyNameWithCountyCode:(NSString*) arearCode{\n";
		m+="    \n";
		m+="    \n";
		m+="    \n";
		m+="    NSMutableArray *countcount= [self queryCityNameWithRegionid:arearCode];\n";
		m+="    \n";
		m+="    return countcount;\n";
		m+="    \n";
		m+="}\n";

		m+="-(NSMutableArray*) queryCityNameWithRegionid:(NSString*) code{\n";
		m+="    \n";
		m+="    [self openDB];\n";
		m+="    NSString *sqlQuery = [NSString stringWithFormat:\n";
		m+="                @\"SELECT * FROM PM_CITYCODE  where  CITYCODE = '%@' \",code];\n";
		m+="    \n";
		m+="    NSLog(@\"sqlQuery:%@\",sqlQuery);\n";
		m+="    sqlite3_stmt * statement;\n";
		m+="    \n";
		m+="    NSString * cityName=nil;\n";
		m+="    NSString * cityCode=nil;\n";
		m+="    NSMutableArray *temp=[[NSMutableArray alloc] init];\n";
		m+="    \n";
		m+="    if (sqlite3_prepare_v2(db, [sqlQuery UTF8String], -1, &statement, nil) == SQLITE_OK) {\n";
		m+="        \n";
		m+="        //查询结果集中一条一条的遍历所有的记录，这里的数字对应的是列值,注意这里的列值\n";
		m+="        \n";
		m+="        while (sqlite3_step(statement) == SQLITE_ROW) {\n";
		m+="            \n";
		m+="            char *regionnamechar = (char*)sqlite3_column_text(statement, 0);\n";
		m+="            NSString *regionnamestring = [[NSString alloc]initWithUTF8String:regionnamechar];\n";
		m+="            cityCode = regionnamestring;\n";
		m+="            \n";
		m+="            char *regionnamechar2 = (char*)sqlite3_column_text(statement, 1);\n";
		m+="            NSString *regionnamestring2 = [[NSString alloc]initWithUTF8String:regionnamechar2];\n";
		m+="            cityName = regionnamestring2;\n";
		m+="            \n";
		m+="            [temp addObject:cityCode];\n";
		m+="            [temp addObject:cityName];\n";

		m+="        }\n";
		m+="    }else{\n";
		m+="        NSLog(@\"select error:%@\",sqlQuery);\n";
		m+="        \n";
		m+="    }\n";
		m+="    sqlite3_close(db);\n";

		m+="    return temp;\n";
		m+="}\n";

        m+="@end\n";
        
        FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios",   "SqlQueryCity", "m", m);
		
		System.out.println(m);
		
		return m1;
		
	}
	
	public void viewControllerH()
	{
		String m="";
		
m+="//ios界面 object-c \n";
m+="#import <UIKit/UIKit.h>\n";
m+="#import \"BaseViewController.h\"\n";
m+="@protocol "+viewControllerName+"ChirldViewCallBackDelegate;\n";
m+="@interface "+viewControllerName+"ViewController : BaseViewController<UITableViewDataSource,UITableViewDelegate>\n";

m+="{\n";
m+="int page;\n";
m+="int totalRowCount;\n";
m+="int currentRowCount;\n";
m+="bool requestUnComplete;//发完一个请求再发下一个\n";
m+="NSMutableArray *allIndexpaths;\n";
m+="NSMutableArray *rows;\n";
m+="NSMutableArray *chirldViewData;\n";
m+="    \n";
m+="int index;//传进来\n";

m+="}\n";
m+="@property (nonatomic) int index;\n";
m+="//back\n";
m+="@property (weak, nonatomic) IBOutlet UIButton *backButton;\n";
m+="//list\n";
m+="@property (weak, nonatomic) IBOutlet UITableView *tableView;\n";
m+="@property (strong, nonatomic) NSMutableDictionary *cacheCells;\n";
m+="//选择\n";
m+="@property (weak, nonatomic) IBOutlet UIButton *selectButton;\n";
m+="@property (strong,nonatomic) id<"+viewControllerName+"ChirldViewCallBackDelegate> chirldViewCallBackDelegate;\n";
m+="-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<"+viewControllerName+"ChirldViewCallBackDelegate>)parent;\n";
m+="@end\n";
m+="@protocol "+viewControllerName+"ChirldViewCallBackDelegate <NSObject>\n";
m+="-(void) chirldViewCallBack:(NSMutableArray*)mdata;\n";
m+="@end\n";

m+="//父亲ViewController实现接口  "+viewControllerName+"ChirldViewCallBackDelegate>\n";
m+="//1. "+viewControllerName+"ChirldViewCallBackDelegate\n";

m+="//-(void) chirldViewCallBack:(NSMutableArray*)mdata;\n";


m+="//2.在viewDidLoad中\n";


m+="//chirldViewController=[["+viewControllerName+"ViewController alloc ] initWithNibName:@\""+viewControllerName+"ViewController\" bundle:nil];\n";
m+="//chirldViewController.view.frame=CGRectMake(,,,);\n";
m+="//[chirldViewController setChirldViewValue:nil delegate:self];\n";
m+="//[ self.view addSubview:chirldViewController.view];\n";
m+="// [chirldViewController.view setHidden:YES];\n";


FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", viewControllerName + "ViewController", "h", m);
	}
	
	
	public void viewControllerM()
	{
		String m="";



m+="#import \""+viewControllerName+"ViewController.h\"\n";
m+="#import \"UIImageView+WebCache.h\"\n";
m+="#import <Foundation/Foundation.h>\n";
m+="#import <JSONKit.h>\n";
m+="#import <objc/runtime.h>\n";
m+="#import \"UIButton+EnlargeTouchArea.h\"\n";

m+="#import \""+viewControllerName+"TableViewCell.h\"\n";


m+="//注入table功能\n";
m+="NSString *"+viewControllerName+"CellIdentifier = @\""+viewControllerName+"TableViewCell\";\n";
m+="NSString *"+viewControllerName+"CellHeadIdentifier = @\""+viewControllerName+"TableViewCellHead\";\n";

m+="@implementation "+viewControllerName+"ViewController\n";
m+="@synthesize cacheCells;\n";

m+="//back\n";
m+="@synthesize backButton;\n";
m+="//list\n";
m+="@synthesize tableView;\n";
m+="//选择\n";
m+="@synthesize selectButton;\n";

m+="@synthesize index;\n";

m+="- (void)viewDidLoad\n";
m+="{\n";
m+="    [super viewDidLoad];\n";
m+="    \n";
m+="    //start  TableView\n";
m+="    totalRowCount=0;\n";
m+="    currentRowCount=0;\n";
m+="    page=1;\n";
m+="    \n";
m+="    allIndexpaths=[[NSMutableArray alloc] init];\n";
m+="    rows=[[NSMutableArray alloc] init];\n";
m+="    [self.tableView setDelegate:self];//tableview委托\n";
m+="    [self.tableView setDataSource:self];//tableview数据委托\n";
m+="    self.tableView.tableFooterView=[[UIView alloc]init];//tableview去除多余的分隔线\n";
m+="    //使用自定义的Cell,需要向UITableView进行注册\n";
m+="    UINib *cellNib = [UINib nibWithNibName:@\""+viewControllerName+"TableViewCell\" bundle:nil];\n";
m+="    [tableView registerNib:cellNib forCellReuseIdentifier:"+viewControllerName+"CellIdentifier];\n";
m+="    cacheCells = [NSMutableDictionary dictionary];\n";
m+="    //end TableView\n";
m+="    \n";
m+="    \n";
m+="    //back\n";
m+="    [self.backButton addTarget:self action:@selector(backButtonClicked:) forControlEvents:UIControlEventTouchUpInside];\n";
m+="       [self.backButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域\n";
m+="    \n";
m+="    \n";
m+="    //选择\n";
m+="    [self.selectButton addTarget:self action:@selector(selectButtonClicked:) forControlEvents:UIControlEventTouchUpInside];\n";
m+="}\n";

m+="-(void) viewWillAppear:(BOOL)animated{\n";
m+="     [super viewWillAppear:animated];\n";
m+="    //table\n";
m+="    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
m+="}\n";

m+="-(void)backButtonClicked:(UIButton *)btn{\n";
m+="    id mId = objc_getAssociatedObject(btn, \"mId\");\n";
m+="    //取绑定数据int mId2 = btn.tag;\n";
m+="    //取绑定数据//方法3.回到上页面\n";
m+="    [self.view setHidden:YES];\n";
m+="    \n";
m+="}\n";

m+="-(void)selectButtonClicked:(UIButton *)btn{\n";
m+="    id mId = objc_getAssociatedObject(btn, \"mId\");\n";
m+="    //取绑定数据int mId2 = btn.tag;\n";
m+="    //取绑定数据//方法3.回到上页面\n";
m+="          [self.view setHidden:YES];\n";
m+="    [self.chirldViewCallBackDelegate chirldViewCallBack:rows];\n";
m+="}\n";


m+="-(void)viewWillLayoutSubviews\n";
m+="{\n";
m+="//    int startY=self.headView.frame.origin.y+self.headView.frame.size.height;\n";
m+="//    [self.tableView setFrame:CGRectMake(0, startY, self.tableView.frame.size.width, self.view.frame.size.height-startY )];\n";
m+="}\n";

m+="//指定有多少个分区(Section)，默认为1\n";
m+="- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {\n";
m+="    \n";
m+="    return 1;//返回标题数组中元素的个数来确定分区的个数   return [sections count];\n";
m+="}\n";

m+="//指定每个分区中有多少行，默认为1\n";
m+="- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{\n";
m+="    if (rows==nil ||[rows count]<=0) {\n";
m+="        return 0;\n";
m+="    }else\n";
m+="    {\n";
m+="    return  [rows count];\n";
m+="    }\n";
m+="    \n";
m+="}\n";

m+="//绘制Cell\n";
m+="-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
m+="    \n";
m+="    \n";
m+="   \n";
m+="        \n";
m+="    {\n";
m+="        "+viewControllerName+"TableViewCell *cell = ("+viewControllerName+"TableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:"+viewControllerName+"CellIdentifier];\n";
m+="        if (!cell)\n";
m+="        {\n";
m+="            cell = [[[NSBundle mainBundle] loadNibNamed:@\""+viewControllerName+"TableViewCell\" owner:self options:nil] lastObject];\n";
m+="        }\n";
m+="        \n";
m+="      \n";
m+="         id row= rows[indexPath.row];\n";
m+="                \n";
m+="        \n";
m+="        \n";
m+="        \n";
m+="        [cell.whichCheckBox setSelected:row.isSelect];\n";
m+="        //选择a\n";
m+="        cell.whichCheckBox.tag=indexPath.row;\n";
m+="        \n";
m+="        \n";
m+="        [cell.whichCheckBox addTarget:self action:@selector(whichCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];\n";
m+="        [cell.whichCheckBox setBackgroundImage:[UIImage imageNamed:@\"check.png\"] forState:UIControlStateSelected];\n";
m+="        [cell.whichCheckBox setBackgroundImage:[UIImage imageNamed:@\"uncheck.png\"] forState:UIControlStateNormal];\n";
m+="        [cell.whichCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域\n";
m+="        \n";
m+="        [cell.whichCheckBoxCover setText:row.name];\n";
m+="        \n";
m+="        return cell;\n";
m+="    }\n";
m+="    \n";
m+="}\n";

m+="//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度\n";
m+="- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{\n";
m+="    \n";
m+="    NSString *reuseIdentifier = "+viewControllerName+"CellIdentifier;\n";
m+="    "+viewControllerName+"TableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];\n";
m+="    if (!cell) {\n";
m+="        cell=[self.tableView dequeueReusableCellWithIdentifier:"+viewControllerName+"CellIdentifier];\n";
m+="        [self.cacheCells setObject:cell forKey:reuseIdentifier];\n";
m+="    }\n";
m+="    \n";
m+="    \n";
m+="    \n";
m+="         int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用\n";
m+="        return height+1;\n";
m+="    \n";
m+="}\n";

m+="- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
m+="    return 88;\n";
m+="}\n";

m+="//点击后，过段时间cell自动取消选中\n";
m+="- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{\n";
m+="    \n";
m+="    \n";
m+="    id *row;\n";
m+="    \n";
m+="    \n";
m+="    for(int j=0;j<[rows count];j++)\n";
m+="    {\n";
m+="        if (j==indexPath.row) {\n";
m+="            row= rows[j];\n";
m+="            row.isSelect=true;\n";
m+="        }else\n";
m+="        {row= row[j];\n";
m+="            row.isSelect=false;\n";
m+="            \n";
m+="        }\n";
m+="    }\n";
m+="    \n";
m+="    [tableView reloadData];\n";

m+="     //     [self.view setHidden:YES];//选中就关闭\n";
m+="          \n";
m+="            \n";
m+="    \n";
m+="    //消除cell选择痕迹\n";
m+="    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];\n";
m+="}\n";


m+="- (void)deselect\n";
m+="{\n";
m+="    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
m+="}\n";


m+="-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<"+viewControllerName+"ChirldViewCallBackDelegate>)parent{\n";
m+="    self.chirldViewCallBackDelegate=parent;\n";
m+="    rows=mdata;\n";
m+="    \n";
m+="    [tableView reloadData];\n";
m+="    \n";

m+="}\n";

m+="-(void)whichCheckBoxCheck:(UIButton *)btn{\n";
m+="    //id mId = objc_getAssociatedObject(btn, \"mId\");\n";
m+="    int mId2 = btn.tag;\n";
m+="    //取绑定数据  btn.selected = !btn.selected ;//用与button做checkBox\n";

m+="    \n";
m+="    id *row;\n";

m+="    \n";
m+="    \n";
m+="    for(int j=0;j<[rows count];j++)\n";
m+="    {\n";
m+="        if (j==mId2) {\n";
m+="            row= rows[j];\n";
m+="            row.isSelect=true;\n";
m+="        }else\n";
m+="        {row= rows[j];\n";
m+="            row.isSelect=false;\n";
m+="            \n";
m+="        }\n";
m+="    }\n";
m+="    \n";
m+="    [tableView reloadData];\n";
m+="}\n";

m+="@end//end viewController\n";

FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", viewControllerName + "ViewController", "m", m);
	}
	
	
	public void viewControllerXib()
	{
		String m="";
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		m+="<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.XIB\" version=\"3.0\" toolsVersion=\"7706\" systemVersion=\"14F27\" targetRuntime=\"iOS.CocoaTouch\" propertyAccessControl=\"none\">\n";
		m+="    <dependencies>\n";
		m+="        <deployment identifier=\"iOS\"/>\n";
		m+="        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"7703\"/>\n";
		m+="    </dependencies>\n";
		m+="    <objects>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\" customClass=\""+viewControllerName+"ViewController\">\n";
		m+="            <connections>\n";
		m+="                <outlet property=\"backButton\" destination=\"9Ax-BV-2N7\" id=\"gDx-ai-mbW\"/>\n";
		m+="                <outlet property=\"selectButton\" destination=\"CRv-Jh-LYa\" id=\"Hx0-DH-QbH\"/>\n";
		m+="                <outlet property=\"tableView\" destination=\"Em9-9J-mHC\" id=\"lV9-zQ-21m\"/>\n";
		m+="                <outlet property=\"view\" destination=\"pc2-tW-UPM\" id=\"KMn-ax-aeC\"/>\n";
		m+="            </connections>\n";
		m+="        </placeholder>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFirstResponder\" id=\"-2\" customClass=\"UIResponder\"/>\n";
		m+="        <view contentMode=\"scaleToFill\" id=\"pc2-tW-UPM\">\n";
		m+="            <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"320\" height=\"568\"/>\n";
		m+="            <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		m+="            <subviews>\n";
		m+="                <view contentMode=\"scaleToFill\" id=\"RJH-Dp-yHB\">\n";
		m+="                    <rect key=\"frame\" x=\"22\" y=\"108\" width=\"273\" height=\"305\"/>\n";
		m+="                    <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		m+="                    <subviews>\n";
		m+="                        <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" id=\"CRv-Jh-LYa\">\n";
		m+="                            <rect key=\"frame\" x=\"48\" y=\"247\" width=\"176\" height=\"36\"/>\n";
		m+="                            <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		m+="                            <color key=\"backgroundColor\" red=\"0.94117649999999997\" green=\"0.58823530000000002\" blue=\"0.20784314000000001\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
		m+="                            <color key=\"tintColor\" red=\"1\" green=\"1\" blue=\"1\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
		m+="                            <state key=\"normal\" title=\"选择\">\n";
		m+="                                <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
		m+="                            </state>\n";
		m+="                        </button>\n";
		m+="                        <tableView clipsSubviews=\"YES\" contentMode=\"scaleToFill\" alwaysBounceVertical=\"YES\" style=\"plain\" separatorStyle=\"default\" rowHeight=\"44\" sectionHeaderHeight=\"22\" sectionFooterHeight=\"22\" id=\"Em9-9J-mHC\">\n";
		m+="                            <rect key=\"frame\" x=\"20\" y=\"22\" width=\"233\" height=\"194\"/>\n";
		m+="                            <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		m+="                        </tableView>\n";
		m+="                    </subviews>\n";
		m+="                    <color key=\"backgroundColor\" red=\"1\" green=\"1\" blue=\"1\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
		m+="                </view>\n";
		m+="                <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" id=\"9Ax-BV-2N7\">\n";
		m+="                    <rect key=\"frame\" x=\"273\" y=\"90\" width=\"30\" height=\"30\"/>\n";
		m+="                    <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
		m+="                    <color key=\"backgroundColor\" white=\"0.0\" alpha=\"0.0\" colorSpace=\"calibratedWhite\"/>\n";
		m+="                    <color key=\"tintColor\" white=\"0.0\" alpha=\"0.0\" colorSpace=\"calibratedWhite\"/>\n";
		m+="                    <state key=\"normal\" title=\"back\" backgroundImage=\"ico_input_delete.png\">\n";
		m+="                        <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
		m+="                    </state>\n";
		m+="                </button>\n";
		m+="            </subviews>\n";
		m+="            <color key=\"backgroundColor\" red=\"0.31764706999999998\" green=\"0.21176470999999999\" blue=\"0.12941177000000001\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
		m+="            <point key=\"canvasLocation\" x=\"231\" y=\"228\"/>\n";
		m+="        </view>\n";
		m+="    </objects>\n";
		m+="    <resources>\n";
		m+="        <image name=\"ico_input_delete.png\" width=\"56\" height=\"56\"/>\n";
		m+="    </resources>\n";
		m+="    <simulatedMetricsContainer key=\"defaultSimulatedMetrics\">\n";
		m+="        <simulatedStatusBarMetrics key=\"statusBar\"/>\n";
		m+="        <simulatedOrientationMetrics key=\"orientation\"/>\n";
		m+="        <simulatedScreenMetrics key=\"destination\" type=\"retina4\"/>\n";
		m+="    </simulatedMetricsContainer>\n";
		m+="</document>\n";

		FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", viewControllerName + "ViewController", "xib", m);
	}
	
    public void cellH()
    {
    	String m="";
    	m+="#import <UIKit/UIKit.h>\n";
    	m+="#import \"BaseViewController.h\"\n";
    	m+="#import \"BaseViewController.h\"\n";
    	m+="@interface "+viewControllerName+"TableViewCell :UITableViewCell\n";
    	m+="//选择a\n";
    	m+="@property (weak, nonatomic) IBOutlet UIButton *whichCheckBox;\n";
    	m+="//选择aCover\n";
    	m+="@property (weak, nonatomic) IBOutlet UILabel *whichCheckBoxCover;\n";
    	m+="@end\n";
    	
    	FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", viewControllerName + "TableViewCell", "h", m);
    	
    }
    
    
    public void cellM()
    {
    	String m="";
    	m+="#import <Foundation/Foundation.h>\n";
    	m+="#import \""+viewControllerName+"TableViewCell.h\"\n";
    	m+="@implementation "+viewControllerName+"TableViewCell\n";
    	m+="//选择a\n";
    	m+="@synthesize whichCheckBox;\n";
    	m+="@end\n";

    	FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", viewControllerName + "TableViewCell", "m", m);
    }
	
    public void cellXib()
    {
    	String m="";
    	m+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
    	m+="<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.XIB\" version=\"3.0\" toolsVersion=\"7706\" systemVersion=\"14F27\" targetRuntime=\"iOS.CocoaTouch\" propertyAccessControl=\"none\">\n";
    	m+="    <dependencies>\n";
    	m+="        <deployment identifier=\"iOS\"/>\n";
    	m+="        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"7703\"/>\n";
    	m+="    </dependencies>\n";
    	m+="    <objects>\n";
    	m+="        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\"/>\n";
    	m+="        <placeholder placeholderIdentifier=\"IBFirstResponder\" id=\"-2\" customClass=\"UIResponder\"/>\n";
    	m+="        <tableViewCell contentMode=\"scaleToFill\" selectionStyle=\"blue\" indentationWidth=\"0.0\" rowHeight=\"53\" id=\"Utn-kc-OGD\" customClass=\""+viewControllerName+"TableViewCell\">\n";
    	m+="            <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"320\" height=\"53\"/>\n";
    	m+="            <autoresizingMask key=\"autoresizingMask\"/>\n";
    	m+="            <tableViewCellContentView key=\"contentView\" opaque=\"NO\" clipsSubviews=\"YES\" multipleTouchEnabled=\"YES\" contentMode=\"center\" tableViewCell=\"Utn-kc-OGD\" id=\"bLa-zS-AAv\">\n";
    	m+="                <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"320\" height=\"52.5\"/>\n";
    	m+="                <autoresizingMask key=\"autoresizingMask\"/>\n";
    	m+="                <subviews>\n";
    	m+="                    <view contentMode=\"scaleToFill\" id=\"ewU-Ym-QyF\">\n";
    	m+="                        <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"320\" height=\"53\"/>\n";
    	m+="                        <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
    	m+="                        <subviews>\n";
    	m+="                            <button opaque=\"NO\" contentMode=\"scaleToFill\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" id=\"Hgq-nx-PoB\">\n";
    	m+="                                <rect key=\"frame\" x=\"22\" y=\"16\" width=\"20\" height=\"20\"/>\n";
    	m+="                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
    	m+="                                <color key=\"backgroundColor\" red=\"1\" green=\"1\" blue=\"1\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
    	m+="                                <color key=\"tintColor\" white=\"0.0\" alpha=\"0.0\" colorSpace=\"calibratedWhite\"/>\n";
    	m+="                                <state key=\"normal\" title=\"\" backgroundImage=\"check.png\">\n";
    	m+="                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
    	m+="                                </state>\n";
    	m+="                            </button>\n";
    	m+="                            <label opaque=\"NO\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" text=\"选择a\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" id=\"q48-Ed-mWU\">\n";
    	m+="                                <rect key=\"frame\" x=\"46\" y=\"18\" width=\"207\" height=\"15\"/>\n";
    	m+="                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
    	m+="                                <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"14\"/>\n";
    	m+="                                <color key=\"textColor\" red=\"0.0\" green=\"0.0\" blue=\"0.0\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
    	m+="                                <nil key=\"highlightedColor\"/>\n";
    	m+="                            </label>\n";
    	m+="                        </subviews>\n";
    	m+="                        <color key=\"backgroundColor\" red=\"1\" green=\"1\" blue=\"1\" alpha=\"1\" colorSpace=\"calibratedRGB\"/>\n";
    	m+="                    </view>\n";
    	m+="                </subviews>\n";
    	m+="            </tableViewCellContentView>\n";
    	m+="            <inset key=\"separatorInset\" minX=\"0.0\" minY=\"0.0\" maxX=\"0.0\" maxY=\"0.0\"/>\n";
    	m+="            <connections>\n";
    	m+="                <outlet property=\"whichCheckBox\" destination=\"Hgq-nx-PoB\" id=\"faL-2e-sZu\"/>\n";
    	m+="                <outlet property=\"whichCheckBoxCover\" destination=\"q48-Ed-mWU\" id=\"QoA-dT-Z8T\"/>\n";
    	m+="            </connections>\n";
    	m+="            <point key=\"canvasLocation\" x=\"231\" y=\"228.5\"/>\n";
    	m+="        </tableViewCell>\n";
    	m+="    </objects>\n";
    	m+="    <resources>\n";
    	m+="        <image name=\"check.png\" width=\"82\" height=\"82\"/>\n";
    	m+="    </resources>\n";
    	m+="    <simulatedMetricsContainer key=\"defaultSimulatedMetrics\">\n";
    	m+="        <simulatedStatusBarMetrics key=\"statusBar\"/>\n";
    	m+="        <simulatedOrientationMetrics key=\"orientation\"/>\n";
    	m+="        <simulatedScreenMetrics key=\"destination\" type=\"retina4\"/>\n";
    	m+="    </simulatedMetricsContainer>\n";
    	m+="</document>\n";
    	FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", viewControllerName + "TableViewCell", "xib", m);
    }
	

}
