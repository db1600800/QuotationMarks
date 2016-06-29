


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface PackageDetailViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//back
@property (weak, nonatomic) IBOutlet UIButton *backButton;
//可加办套餐详情
@property (weak, nonatomic) IBOutlet UILabel *titleTextView;
@property (weak, nonatomic) IBOutlet UIScrollView *bg1467190764370ScrollView;
@end

