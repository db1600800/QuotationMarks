#import <UIKit/UIKit.h>
@interface MyPackageTableViewCell :UITableViewCell
//未启用
@property (weak, nonatomic) IBOutlet UILabel *packageStatusTextView;
//2013秋冬自驾游
@property (weak, nonatomic) IBOutlet UILabel *packageNameTextView;
//有效日期:
@property (weak, nonatomic) IBOutlet UILabel *dateTitleTextView;
//2013111
@property (weak, nonatomic) IBOutlet UILabel *startDateTextView;
//-
@property (weak, nonatomic) IBOutlet UILabel *lineTextView;
//2014
@property (weak, nonatomic) IBOutlet UILabel *endDateTextView;
//right
@property (weak, nonatomic) IBOutlet UIImageView *rightImageView;
@end

