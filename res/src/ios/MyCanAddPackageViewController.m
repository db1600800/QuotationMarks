


#import "MyCanAddPackageViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation MyCanAddPackageViewController

//a
@synthesize aButton;

- (void)viewDidLoad
{
    [super viewDidLoad];

//a
[self.aButton addTarget:self action:@selector(aButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)aButtonClicked:(UIButton *)btn{
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

