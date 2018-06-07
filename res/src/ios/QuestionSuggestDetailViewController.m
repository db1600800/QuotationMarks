


#import "QuestionSuggestDetailViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation QuestionSuggestDetailViewController

//需求豆腐饭d
@synthesize questionMsgTextView;
//需求管理模块
@synthesize modelTextView;
//企业管理平台
@synthesize applicationTextView;
//|
@synthesize lineTextView;
//故障及问题
@synthesize questionTypeTextView;
//fileimg
@synthesize fileimgImageView;
//guangldong
@synthesize fileurlButton;
//反馈人
@synthesize feedBackTitleTextView;
//张三
@synthesize feedBackNameTextView;
//|
@synthesize line1TextView;
//13343
@synthesize FeedBackPhoneTextView;
//|
@synthesize line3TextView;
//广州
@synthesize FeedBackOrgTextView;
//反馈时间:
@synthesize FeedBackTimeTitleTextView;
//2018
@synthesize FeedBackTimeTextView;
//处理意见
@synthesize dealMsgEditText;
//处理人
@synthesize dealNameEditText;
//电话
@synthesize dealPhoneEditText;
//提交
@synthesize sumbitButton;

- (void)viewDidLoad
{
    [super viewDidLoad];
//需求豆腐饭d
[self.questionMsgTextView setText:null];
//需求管理模块
[self.modelTextView setText:null];
//企业管理平台
[self.applicationTextView setText:null];
//|
[self.lineTextView setText:null];
//故障及问题
[self.questionTypeTextView setText:null];
//fileimg
[fileimgImageView setImage:[UIImage imageNamed:@"1.jpeg"]]
[fileimgImageView setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@"default.jpg"]];

//guangldong
[self.fileurlButton addTarget:self action:@selector(fileurlButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
//反馈人
[self.feedBackTitleTextView setText:null];
//张三
[self.feedBackNameTextView setText:null];
//|
[self.line1TextView setText:null];
//13343
[self.FeedBackPhoneTextView setText:null];
//|
[self.line3TextView setText:null];
//广州
[self.FeedBackOrgTextView setText:null];
//反馈时间:
[self.FeedBackTimeTitleTextView setText:null];
//2018
[self.FeedBackTimeTextView setText:null];

//处理意见
 
self.dealMsgEditText.returnKeyType=UIReturnKeyDone;

[self.dealMsgEditText addTarget:self action:@selector(dealMsgEditTextDidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];

[self.dealMsgEditText addTarget:self action:@selector(dealMsgEditTextDidEnd:) forControlEvents:UIControlEventEditingDidEnd];

[  self.dealMsgEditText  addTarget:self action:@selector(textFieldDidChange_dealMsgEditText:) forControlEvents:UIControlEventEditingChanged];
    UITapGestureRecognizer *dealMsgEditTextTapGuest=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(editTextTapHandle:)];
dealMsgEditTextTapGuest.delegate = self;//头文件<UIGestureRecognizerDelegate>
    [ self.dealMsgEditText  addGestureRecognizer:dealMsgEditTextTapGuest];


//处理人
 
self.dealNameEditText.returnKeyType=UIReturnKeyDone;

[self.dealNameEditText addTarget:self action:@selector(dealNameEditTextDidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];

[self.dealNameEditText addTarget:self action:@selector(dealNameEditTextDidEnd:) forControlEvents:UIControlEventEditingDidEnd];

[  self.dealNameEditText  addTarget:self action:@selector(textFieldDidChange_dealNameEditText:) forControlEvents:UIControlEventEditingChanged];
    UITapGestureRecognizer *dealNameEditTextTapGuest=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(editTextTapHandle:)];
dealNameEditTextTapGuest.delegate = self;//头文件<UIGestureRecognizerDelegate>
    [ self.dealNameEditText  addGestureRecognizer:dealNameEditTextTapGuest];


//电话
 
self.dealPhoneEditText.returnKeyType=UIReturnKeyDone;

[self.dealPhoneEditText addTarget:self action:@selector(dealPhoneEditTextDidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];

[self.dealPhoneEditText addTarget:self action:@selector(dealPhoneEditTextDidEnd:) forControlEvents:UIControlEventEditingDidEnd];

[  self.dealPhoneEditText  addTarget:self action:@selector(textFieldDidChange_dealPhoneEditText:) forControlEvents:UIControlEventEditingChanged];
    UITapGestureRecognizer *dealPhoneEditTextTapGuest=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(editTextTapHandle:)];
dealPhoneEditTextTapGuest.delegate = self;//头文件<UIGestureRecognizerDelegate>
    [ self.dealPhoneEditText  addGestureRecognizer:dealPhoneEditTextTapGuest];


//提交
[self.sumbitButton addTarget:self action:@selector(sumbitButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
 
//键盘顶起
   UITapGestureRecognizer* closeKeyboardtap =[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(closeKeyboardBlankPlaceTapHandle:)];
    [self.view addGestureRecognizer:closeKeyboardtap];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardDidShowNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardDidHideNotification object:nil];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)fileurlButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

-(void)dealMsgEditTextDidEndOnExit:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

-(void)dealMsgEditTextDidEnd:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

- (void)textFieldDidChange_dealMsgEditText:(UITextField *)textField
{
  if (textField.text.length > 7) {
   textField.text = [textField.text substringToIndex:7];
 }
}
-(void)dealNameEditTextDidEndOnExit:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

-(void)dealNameEditTextDidEnd:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

- (void)textFieldDidChange_dealNameEditText:(UITextField *)textField
{
  if (textField.text.length > 7) {
   textField.text = [textField.text substringToIndex:7];
 }
}
-(void)dealPhoneEditTextDidEndOnExit:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

-(void)dealPhoneEditTextDidEnd:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

- (void)textFieldDidChange_dealPhoneEditText:(UITextField *)textField
{
  if (textField.text.length > 7) {
   textField.text = [textField.text substringToIndex:7];
 }
}
-(void)sumbitButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

-(bool)checkInput{
    beannull.null=self..dealMsgEditText.text;

    beannull.null=self..dealNameEditText.text;

    beannull.null=self..dealPhoneEditText.text;


return true;
}
//编辑框键盘顶起start
   float touchy1;
   int   movelength1;
   int  keyboardHeight1;
   bool keyboardOpen;

//注销键盘监听
-(void)viewDidDisappear:(BOOL)animated
{
    
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidShowNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidHideNotification object:nil];
    
}

//<UIGestureRecognizerDelegate>
- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer
{
    return YES;
    
    
}

//点空白区域键盘消失
-(void)closeKeyboardBlankPlaceTapHandle:(UITapGestureRecognizer *)sender
{
  
    if (keyboardOpen) {
        [[[UIApplication sharedApplication] keyWindow] endEditing:YES];
    }
    
}

//touchY 触摸位置
-(void)editTextTapHandle:(UITapGestureRecognizer *)sender
{
    
     if (keyboardOpen) {
         return;
     }
    
    CGPoint point = [sender locationInView:self.view];
    touchy1=point.y+15;
    
    if(keyboardHeight1>0)
    {
        if(touchy1>keyboardHeight1)
        {
            movelength1=touchy1-keyboardHeight1;
            [self MoveView:(-movelength1)];
        }else
        {
            movelength1=0;
            [self MoveView:(-movelength1)];
        }
    }
    
}

//键盘打开监听回调
- (void)keyboardWillShow:(NSNotification *)notification {
    
    
    keyboardOpen=true;
    /*
     Reduce the size of the text view so that it's not obscured by the keyboard.
     Animate the resize so that it's in sync with the appearance of the keyboard.
     */
    
    NSDictionary *userInfo = [notification userInfo];
    
    // Get the origin of the keyboard when it's displayed.
    NSValue* aValue = [userInfo objectForKey:UIKeyboardFrameEndUserInfoKey];
    
    // Get the top of the keyboard as the y coordinate of its origin in self's view's coordinate system. The bottom of the text view's frame should align with the top of the keyboard's final position.
    CGRect keyboardRect = [aValue CGRectValue];
    keyboardRect = [self.view convertRect:keyboardRect fromView:nil];
    
    float keyboardTop = keyboardRect.origin.y;
    
    
    
    if(keyboardHeight1==0)
    {
        // Get the duration of the animation.
        NSValue *animationDurationValue = [userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey];
        NSTimeInterval animationDuration;
        [animationDurationValue getValue:&animationDuration];
        //
        //    // Animate the resize of the text view's frame in sync with the keyboard's appearance.
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:animationDuration];
        
        keyboardHeight1=keyboardTop;
        
        if(touchy1>keyboardHeight1)
        {
            movelength1=touchy1-keyboardHeight1;
            [self MoveView:(-movelength1)];
        }else
        {
            movelength1=0;
            [self MoveView:(-movelength1)];
        }
    
    
        
           [UIView commitAnimations];
    }
    
    
 
    
    
}

//键盘关闭监听回调
- (void)keyboardWillHide:(NSNotification *)notification {
    
      keyboardOpen=false;
    NSDictionary* userInfo = [notification userInfo];
    
    
    
    /*
     Restore the size of the text view (fill self's view).
     Animate the resize so that it's in sync with the disappearance of the keyboard.
     */
    NSValue *animationDurationValue = [userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey];
    NSTimeInterval animationDuration;
    [animationDurationValue getValue:&animationDuration];
    
    [UIView beginAnimations:nil context:NULL];
    [UIView setAnimationDuration:animationDuration];
    
    if(movelength1!=0){
    [self MoveView:(movelength1)];
    movelength1=0;
}
    
    [UIView commitAnimations];
}

-(void)MoveView:(int)h{
    
    
    if (h<0) {
        [UIView beginAnimations:nil context:nil];
        [UIView setAnimationDuration:0.3];
        [UIView setAnimationBeginsFromCurrentState: YES];
        [self.contain setFrame: CGRectMake(self.contain.frame.origin.x, self.contain.frame.origin.y + h, self.contain.frame.size.width, self.contain.frame.size.height)];
        [UIView commitAnimations];
    }else
    {
        [UIView beginAnimations:nil context:nil];
        [UIView setAnimationDuration:0.3];
        [UIView setAnimationBeginsFromCurrentState: YES];
        [self.contain setFrame: CGRectMake(self.contain.frame.origin.x, self.contain.frame.origin.y+h , self.contain.frame.size.width, self.contain.frame.size.height)];
        [UIView commitAnimations];
    }
    
    
    
}
//编辑框键盘顶起end


 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {
  }//end ReturnData

@end//end viewController

