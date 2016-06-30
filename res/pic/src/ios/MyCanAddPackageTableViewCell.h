#import <UIKit/UIKit.h>
@interface MyCanAddPackageTableViewCell :UITableViewCell
//业务包名称
@property (weak, nonatomic) IBOutlet UIButton *packageNameCheckBox;
//业务包名称Cover
@property (weak, nonatomic) IBOutlet UILabel *packageNameCheckBoxCover;
//60
@property (weak, nonatomic) IBOutlet UILabel *packageMoneyTextView;
//周期:
@property (weak, nonatomic) IBOutlet UILabel *packagePeriodTitleTextView;
//6个月
@property (weak, nonatomic) IBOutlet UILabel *packagePeriodValueTextView;
//right
@property (weak, nonatomic) IBOutlet UIImageView *rightImageView;
@end

