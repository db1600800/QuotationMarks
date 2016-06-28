


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "BaseViewController.h"
@protocol SelectInsureProductChirldViewCallBackDelegate;
@interface SelectInsureProductViewController : BaseViewController<UITableViewDataSource,UITableViewDelegate>

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
@property (strong,nonatomic) id<SelectInsureProductChirldViewCallBackDelegate> chirldViewCallBackDelegate;
-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<SelectInsureProductChirldViewCallBackDelegate>)parent;
@end
@protocol SelectInsureProductChirldViewCallBackDelegate <NSObject>
-(void) chirldViewCallBack:(NSMutableArray*)mdata;
@end

//父亲ViewController实现接口  SelectInsureProductChirldViewCallBackDelegate>
//1. SelectInsureProductChirldViewCallBackDelegate

//-(void) chirldViewCallBack:(NSMutableArray*)mdata;


//2.在viewDidLoad中


//chirldViewController=[[SelectInsureProductViewController alloc ] initWithNibName:@"SelectInsureProductViewController" bundle:nil];
//chirldViewController.view.frame=CGRectMake(,,,);
//[chirldViewController setChirldViewValue:nil delegate:self];
//[ self.view addSubview:chirldViewController.view];
// [chirldViewController.view setHidden:YES];

