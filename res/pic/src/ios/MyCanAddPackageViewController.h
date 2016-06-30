


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface MyCanAddPackageViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

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
//可加办套餐
@property (weak, nonatomic) IBOutlet UILabel *titleTextView;
//我的套餐
@property (weak, nonatomic) IBOutlet UIButton *myPackageButton;
//价格从高到低
@property (weak, nonatomic) IBOutlet UIButton *priceUpButton;
//down
@property (weak, nonatomic) IBOutlet UIImageView *downImageView;
//3个月
@property (weak, nonatomic) IBOutlet UIButton *timeButton;
//down
@property (weak, nonatomic) IBOutlet UIImageView *downImageView;
//全国
@property (weak, nonatomic) IBOutlet UIButton *cityButton;
//down
@property (weak, nonatomic) IBOutlet UIImageView *downImageView;
//总计:
@property (weak, nonatomic) IBOutlet UILabel *totalTitleTextView;
//123
@property (weak, nonatomic) IBOutlet UILabel *totalValueTextView;
//办理
@property (weak, nonatomic) IBOutlet UIButton *buyButton;
@end

