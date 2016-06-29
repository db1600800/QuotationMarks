#import <UIKit/UIKit.h>
@interface PackageDetailPackageInfoLinearLayoutScrollViewCell :UITableViewCell
//套餐信息
@property (weak, nonatomic) IBOutlet UILabel *part1TitleTextView;
//套餐名称
@property (weak, nonatomic) IBOutlet UILabel *packageNameTitleTextView;
//2013
@property (weak, nonatomic) IBOutlet UILabel *packageNameTitleTextViewValueTextView;
//周期
@property (weak, nonatomic) IBOutlet UILabel *feePeriodTextView;
//6个月
@property (weak, nonatomic) IBOutlet UILabel *feePeriodValueTextView;
//价格
@property (weak, nonatomic) IBOutlet UILabel *feeTitleTextView;
//333
@property (weak, nonatomic) IBOutlet UILabel *feeValueTextView;
//服务区域
@property (weak, nonatomic) IBOutlet UILabel *areaTitleTextView;
//广州北京
@property (weak, nonatomic) IBOutlet UILabel *areaValueTextView;
@end

