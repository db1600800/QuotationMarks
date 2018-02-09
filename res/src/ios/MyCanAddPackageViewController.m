


#import "MyCanAddPackageViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation MyCanAddPackageViewController

//可加倍套餐
@synthesize titleTextView;
//我的套餐
@synthesize mypackageButton;

- (void)viewDidLoad
{
    [super viewDidLoad];
//可加倍套餐
[self.titleTextView setText:null];

//我的套餐
[self.mypackageButton addTarget:self action:@selector(mypackageButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)mypackageButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

-(bool)checkInput{

return true;
}

 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {
  }//end ReturnData

@end//end viewController

