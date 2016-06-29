#import <UIKit/UIKit.h>
@interface PackageDetailPart3LinearLayoutScrollViewCell :UITableViewCell
//绑定信息
@property (weak, nonatomic) IBOutlet UILabel *part3TitleTextView;
//最大绑定数量
@property (weak, nonatomic) IBOutlet UILabel *maxBindNumTitleTextView;
//10
@property (weak, nonatomic) IBOutlet UILabel *maxBindNumValueTextView;
//已绑
@property (weak, nonatomic) IBOutlet UILabel *bindedNumTitleTextView;
//2
@property (weak, nonatomic) IBOutlet UILabel *bindedNumValueTextView;
//新绑定
@property (weak, nonatomic) IBOutlet UIButton *newBindButton;
@end

