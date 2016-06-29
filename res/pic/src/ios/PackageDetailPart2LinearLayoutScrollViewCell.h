#import <UIKit/UIKit.h>
@interface PackageDetailPart2LinearLayoutScrollViewCell :UITableViewCell
//收费模式
@property (weak, nonatomic) IBOutlet UILabel *feeModeTitleTextView;
//先付款
@property (weak, nonatomic) IBOutlet UILabel *feeModeValueTextView;
//累加模式
@property (weak, nonatomic) IBOutlet UILabel *countModeTitleTextView;
//按次
@property (weak, nonatomic) IBOutlet UILabel *countModeTitleTextViewValueTextView;
@end

