


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface QuestionSuggestDetailViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//需求豆腐饭d
@property (weak, nonatomic) IBOutlet UILabel *questionMsgTextView;
//需求管理模块
@property (weak, nonatomic) IBOutlet UILabel *modelTextView;
//企业管理平台
@property (weak, nonatomic) IBOutlet UILabel *applicationTextView;
//|
@property (weak, nonatomic) IBOutlet UILabel *lineTextView;
//故障及问题
@property (weak, nonatomic) IBOutlet UILabel *questionTypeTextView;
//fileimg
@property (weak, nonatomic) IBOutlet UIImageView *fileimgImageView;
//guangldong
@property (weak, nonatomic) IBOutlet UIButton *fileurlButton;
//反馈人
@property (weak, nonatomic) IBOutlet UILabel *feedBackTitleTextView;
//张三
@property (weak, nonatomic) IBOutlet UILabel *feedBackNameTextView;
//|
@property (weak, nonatomic) IBOutlet UILabel *line1TextView;
//13343
@property (weak, nonatomic) IBOutlet UILabel *FeedBackPhoneTextView;
//|
@property (weak, nonatomic) IBOutlet UILabel *line3TextView;
//广州
@property (weak, nonatomic) IBOutlet UILabel *FeedBackOrgTextView;
//反馈时间:
@property (weak, nonatomic) IBOutlet UILabel *FeedBackTimeTitleTextView;
//2018
@property (weak, nonatomic) IBOutlet UILabel *FeedBackTimeTextView;
//处理意见
@property (weak, nonatomic) IBOutlet UITextField *dealMsgEditText;
//处理人
@property (weak, nonatomic) IBOutlet UITextField *dealNameEditText;
//电话
@property (weak, nonatomic) IBOutlet UITextField *dealPhoneEditText;
//提交
@property (weak, nonatomic) IBOutlet UIButton *sumbitButton;
@end

