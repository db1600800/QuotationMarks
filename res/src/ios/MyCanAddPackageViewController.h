


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface MyCanAddPackageViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//可加办套餐
@property (weak, nonatomic) IBOutlet UILabel *titleTextView;
//我的套餐
@property (weak, nonatomic) IBOutlet UIButton *mypackageButton;
//Y60元
@property (weak, nonatomic) IBOutlet UILabel *moneyTextView;
//check
@property (weak, nonatomic) IBOutlet UIButton *checkCheckBox;
//checkCover
@property (weak, nonatomic) IBOutlet UILabel *checkCheckBoxCover;
//2013秋天
@property (weak, nonatomic) IBOutlet UILabel *titlevalueTextView;
//周期
@property (weak, nonatomic) IBOutlet UILabel *monthtitleTextView;
//6个月
@property (weak, nonatomic) IBOutlet UILabel *monthvalueTextView;
//总计
@property (weak, nonatomic) IBOutlet UILabel *totaltitleTextView;
//123
@property (weak, nonatomic) IBOutlet UILabel *toalvalueTextView;
//办理
@property (weak, nonatomic) IBOutlet UIButton *doitButton;
//首页
@property (weak, nonatomic) IBOutlet UILabel *shouyeTextView;
//套餐
@property (weak, nonatomic) IBOutlet UILabel *packageTextView;
//我的
@property (weak, nonatomic) IBOutlet UILabel *myTextView;
@end

