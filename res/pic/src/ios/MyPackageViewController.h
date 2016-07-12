


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface MyPackageViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//a
@property (weak, nonatomic) IBOutlet UILabel *aTextView;
@end

