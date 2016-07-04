


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface PackageOrderFromComfirmViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//back
@property (weak, nonatomic) IBOutlet UIButton *backButton;
//订单确认
@property (weak, nonatomic) IBOutlet UILabel *titleTextView;
@property (weak, nonatomic) IBOutlet UIScrollView *bg1467272807617ScrollView;
@end

