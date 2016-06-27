


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface MyPackageViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{
int page;
int totalRowCount;
int currentRowCount;
bool requestUnComplete;//发完一个请求再发下一个
NSMutableArray *allIndexpaths;
NSMutableArray *rows;

}
//list
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) NSMutableDictionary *cacheCells;
//back
@property (weak, nonatomic) IBOutlet UIButton *backButton;
//我的套餐
@property (weak, nonatomic) IBOutlet UILabel *titleTextView;
//添加套餐
@property (weak, nonatomic) IBOutlet UIButton *addPackageButton;
//全部
@property (weak, nonatomic) IBOutlet UIButton *allButton;
//有效
@property (weak, nonatomic) IBOutlet UIButton *useButton;
//过期
@property (weak, nonatomic) IBOutlet UIButton *unuseButton;
@end

