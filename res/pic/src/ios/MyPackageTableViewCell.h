#import <UIKit/UIKit.h>
@interface MyPackageTableViewCell :UITableViewCell
//未启用
@property (weak, nonatomic) IBOutlet UILabel *packageStatusTextView;
//2013秋冬
@property (weak, nonatomic) IBOutlet UILabel *packageNameTextView;
//有效期:
@property (weak, nonatomic) IBOutlet UILabel *dateTextView;
//2013
@property (weak, nonatomic) IBOutlet UILabel *startDateTextView;
//-
@property (weak, nonatomic) IBOutlet UILabel *hengTextView;
//2014
@property (weak, nonatomic) IBOutlet UILabel *endDateTextView;
//right
@property (weak, nonatomic) IBOutlet UIImageView *rightImageView;
@end

