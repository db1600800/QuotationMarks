#import "timeButtonSelectorViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"
#import "timeButtonSelectorTableViewCell.h"
//注入table功能
NSString *timeButtonSelectorCellIdentifier = @"timeButtonSelectorTableViewCell";
NSString *timeButtonSelectorCellHeadIdentifier = @"timeButtonSelectorTableViewCellHead";
@implementation timeButtonSelectorViewController
@synthesize cacheCells;
//back
@synthesize backButton;
//list
@synthesize tableView;
//选择
@synthesize selectButton;
@synthesize index;
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
    UINib *cellNib = [UINib nibWithNibName:@"timeButtonSelectorTableViewCell" bundle:nil];
    [tableView registerNib:cellNib forCellReuseIdentifier:timeButtonSelectorCellIdentifier];
    cacheCells = [NSMutableDictionary dictionary];
    //end TableView
    
    
    //back
    [self.backButton addTarget:self action:@selector(backButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
       [self.backButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域
    
    
    //选择
    [self.selectButton addTarget:self action:@selector(selectButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
}
-(void) viewWillAppear:(BOOL)animated{
     [super viewWillAppear:animated];
    //table
    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];
}
-(void)backButtonClicked:(UIButton *)btn{
    id mId = objc_getAssociatedObject(btn, "mId");
    //取绑定数据int mId2 = btn.tag;
    //取绑定数据//方法3.回到上页面
    [self.view setHidden:YES];
    
}
-(void)selectButtonClicked:(UIButton *)btn{
    id mId = objc_getAssociatedObject(btn, "mId");
    //取绑定数据int mId2 = btn.tag;
    //取绑定数据//方法3.回到上页面
          [self.view setHidden:YES];
    [self.chirldViewCallBackDelegate chirldViewCallBack:rows];
}
-(void)viewWillLayoutSubviews
{
//    int startY=self.headView.frame.origin.y+self.headView.frame.size.height;
//    [self.tableView setFrame:CGRectMake(0, startY, self.tableView.frame.size.width, self.view.frame.size.height-startY )];
}
//指定有多少个分区(Section)，默认为1
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 1;//返回标题数组中元素的个数来确定分区的个数   return [sections count];
}
//指定每个分区中有多少行，默认为1
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (rows==nil ||[rows count]<=0) {
        return 0;
    }else
    {
    return  [rows count];
    }
    
}
//绘制Cell
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    
   
        
    {
        timeButtonSelectorTableViewCell *cell = (timeButtonSelectorTableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:timeButtonSelectorCellIdentifier];
        if (!cell)
        {
            cell = [[[NSBundle mainBundle] loadNibNamed:@"timeButtonSelectorTableViewCell" owner:self options:nil] lastObject];
        }
        
      
         id row= rows[indexPath.row];
                
        
        
        
        [cell.whichCheckBox setSelected:row.isSelect];
        //选择a
        cell.whichCheckBox.tag=indexPath.row;
        
        
        [cell.whichCheckBox addTarget:self action:@selector(whichCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];
        [cell.whichCheckBox setBackgroundImage:[UIImage imageNamed:@"check.png"] forState:UIControlStateSelected];
        [cell.whichCheckBox setBackgroundImage:[UIImage imageNamed:@"uncheck.png"] forState:UIControlStateNormal];
        [cell.whichCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域
        
        [cell.whichCheckBoxCover setText:row.name];
        
        return cell;
    }
    
}
//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    NSString *reuseIdentifier = timeButtonSelectorCellIdentifier;
    timeButtonSelectorTableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];
    if (!cell) {
        cell=[self.tableView dequeueReusableCellWithIdentifier:timeButtonSelectorCellIdentifier];
        [self.cacheCells setObject:cell forKey:reuseIdentifier];
    }
    
    
    
         int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用
        return height+1;
    
}
- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 88;
}
//点击后，过段时间cell自动取消选中
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    
    id *row;
    
    
    for(int j=0;j<[rows count];j++)
    {
        if (j==indexPath.row) {
            row= rows[j];
            row.isSelect=true;
        }else
        {row= row[j];
            row.isSelect=false;
            
        }
    }
    
    [tableView reloadData];
     //     [self.view setHidden:YES];//选中就关闭
          
            
    
    //消除cell选择痕迹
    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];
}
- (void)deselect
{
    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];
}
-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<timeButtonSelectorChirldViewCallBackDelegate>)parent{
    self.chirldViewCallBackDelegate=parent;
    rows=mdata;
    
    [tableView reloadData];
    
}
-(void)whichCheckBoxCheck:(UIButton *)btn{
    //id mId = objc_getAssociatedObject(btn, "mId");
    int mId2 = btn.tag;
    //取绑定数据  btn.selected = !btn.selected ;//用与button做checkBox
    
    id *row;
    
    
    for(int j=0;j<[rows count];j++)
    {
        if (j==mId2) {
            row= rows[j];
            row.isSelect=true;
        }else
        {row= rows[j];
            row.isSelect=false;
            
        }
    }
    
    [tableView reloadData];
}
@end//end viewController

