#import <UIKit/UIKit.h>
@interface PackageCanAddConfirmPart1LinearLayoutScrollViewCell :UITableViewCell
//业务包名
@property (weak, nonatomic) IBOutlet UILabel *packageNameTextView;
//6个月
@property (weak, nonatomic) IBOutlet UILabel *periodTextView;
//北京广州
@property (weak, nonatomic) IBOutlet UILabel *arearTextView;
//先付款
@property (weak, nonatomic) IBOutlet UILabel *feeModeTextView;
//按次数累加
@property (weak, nonatomic) IBOutlet UILabel *countModeTextView;
//245
@property (weak, nonatomic) IBOutlet UILabel *feeTextView;
//套餐生效日期
@property (weak, nonatomic) IBOutlet UILabel *dateTitleTextView;
//2013年
@property (weak, nonatomic) IBOutlet UIButton *beginDateButton;
@end

