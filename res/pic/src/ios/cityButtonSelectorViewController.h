//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "BaseViewController.h"
@protocol cityButtonSelectorChirldViewCallBackDelegate;
@interface cityButtonSelectorViewController : BaseViewController<UITableViewDataSource,UITableViewDelegate>
{
int page;
int totalRowCount;
int currentRowCount;
bool requestUnComplete;//发完一个请求再发下一个
NSMutableArray *allIndexpaths;
NSMutableArray *rows;
NSMutableArray *chirldViewData;
    
int index;//传进来
}
@property (nonatomic) int index;
//back
@property (weak, nonatomic) IBOutlet UIButton *backButton;
//list
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) NSMutableDictionary *cacheCells;
//选择
@property (weak, nonatomic) IBOutlet UIButton *selectButton;
@property (strong,nonatomic) id<cityButtonSelectorChirldViewCallBackDelegate> chirldViewCallBackDelegate;
-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<cityButtonSelectorChirldViewCallBackDelegate>)parent;
@end
@protocol cityButtonSelectorChirldViewCallBackDelegate <NSObject>
-(void) chirldViewCallBack:(NSMutableArray*)mdata;
@end
//父亲ViewController实现接口  cityButtonSelectorChirldViewCallBackDelegate>
//1. cityButtonSelectorChirldViewCallBackDelegate
//-(void) chirldViewCallBack:(NSMutableArray*)mdata;
//2.在viewDidLoad中
//chirldViewController=[[cityButtonSelectorViewController alloc ] initWithNibName:@"cityButtonSelectorViewController" bundle:nil];
//chirldViewController.view.frame=CGRectMake(,,,);
//[chirldViewController setChirldViewValue:nil delegate:self];
//[ self.view addSubview:chirldViewController.view];
// [chirldViewController.view setHidden:YES];

