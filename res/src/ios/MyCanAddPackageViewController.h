


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface MyCanAddPackageViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//可加倍套餐
@property (weak, nonatomic) IBOutlet UILabel *titleTextView;
//我的套餐
@property (weak, nonatomic) IBOutlet UIButton *mypackageButton;
@end

