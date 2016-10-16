


#import "MyCanAddPackageViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation MyCanAddPackageViewController

//a
@synthesize aTextView;

- (void)viewDidLoad
{
    [super viewDidLoad];
//a
[self.aTextView setText:null];
}

-(void) viewWillAppear:(BOOL)animated{
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

