package com.compoment.ui.ios.creater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.addfunction.android.FileBean;
import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.ImportString;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;
import com.compoment.util.RegexUtil.ControllerBean;


/***
 * TableViewCell
 * */
public class TableViewCellAddToViewController {


	


	String waitByModifyFileName;
    String className="";
    String viewDidLoad_Implement="";
	String closeKeyboardDeclare="";
	String closeKeyboardImplement="";
	CompomentDeclareImplement compomentDeclareImplement=new CompomentDeclareImplement();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

    String pageName="";
    boolean isHeadCell=false;

        
	public TableViewCellAddToViewController(String pageName,List<CompomentBean> oldBeans,String waitByModifyFileName,boolean isHeadCell) {
		this.isHeadCell=isHeadCell;
		this.waitByModifyFileName = waitByModifyFileName;
		this.pageName=pageName;
        className=firstCharToUpperAndJavaName(pageName);
		//copyFile();
        analyse(oldBeans);
		add();
	}

	public static String firstCharToUpperAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		for (String s : ss) {
			if (!s.equals("item"))
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return temp;
	}
	
	
	public void copyFile() {
		
	}

	

	public void add() {
		
		List addedLines =new ArrayList();
	
		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(waitByModifyFileName);

		

		
		//是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if(line.contains("//注入table功能"))
			{
				return;
			}
		}

		
		boolean findViewByIdFirst=true;
		
		//
		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}
			
			 if(line.contains("numberOfSectionsInTableView") &&isHeadCell==true)	
			 {
				 
				 String m="\n";
				
					m += "- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{\n";
					m+=" "+className+"TableViewCellHead *cell = ("+className+"TableViewCellHead*)[self.tableView dequeueReusableHeaderFooterViewWithIdentifier:"+className+"CellHeadIdentifier];\n";
					m+="    if (!cell)\n";
					m+="    {\n";
					m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCellHead\" owner:self options:nil] lastObject];\n";
					m+="    }\n";
					m+=controllers;
					m+="return cell;\n";
					m += "}\n\n";
					
					
					
		
					m += "-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{\n";
					
					m+=" "+className+"TableViewCellHead *cell = ("+className+"TableViewCellHead*)[self.tableView dequeueReusableHeaderFooterViewWithIdentifier:"+className+"CellHeadIdentifier];\n";
					m+="    if (!cell)\n";
					m+="    {\n";
					m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCellHead\" owner:self options:nil] lastObject];\n";
					m+="    }\n";
				    m+="   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   \n";
				    m+="return height+1;\n";;
				    
					m += "}\n\n";
					
			    	content += m;
			    	
			  
			    	
			 }
			
			 
			 if(line.contains("@implementation")&&isHeadCell==false)
			 {
				 String m="";
				     m+="#import \""+className+"TableViewCell.h\"\n";
				     m+="//注入table功能\n";
					 m+=" NSString *"+className+"CellIdentifier = @\""+className+"TableViewCell\";\n";	
					 m+=" NSString *"+className+"CellHeadIdentifier = @\""+className+"TableViewCellHead\";\n\n";	
					 
				
			    	content += m;
			 }
			 if(line.contains("@end//end viewController")&&isHeadCell==false)	
			 {
				 content+=closeKeyboardImplement;
				 content +=viewDidLoad_Implement;
				 
			 }
		
			 
			 //前
			content += line + "\n";
			 //后
			
			 if(line.contains("@implementation")&&isHeadCell==false)
			 {
					String m="@synthesize cacheCells;\n";	
			    	content += m;
			 }
			
			
		 if(line.contains("[super viewDidLoad]")&&isHeadCell==false)
					
				{
					String m="\n//start  TableView \n";
					
					m+="totalRowCount=0;\n";
					m+="currentRowCount=0;\n";
					m+="page=1;\n\n";
					m+="allIndexpaths=[[NSMutableArray alloc] init];\n";
					m+=" rows=[[NSMutableArray alloc] init];\n";
					
					m+="    [self.tableView setDelegate:self];//tableview委托\n";
					m+="    [self.tableView setDataSource:self];//tableview数据委托\n";
					m+="    self.tableView.tableFooterView=[[UIView alloc]init];//tableview去除多余的分隔线\n";
				
					m+="    //使用自定义的Cell,需要向UITableView进行注册\n";
					m+="    UINib *cellNib = [UINib nibWithNibName:@\""+className+"TableViewCell\" bundle:nil];\n";
					m+="    [tableView registerNib:cellNib forCellReuseIdentifier:"+className+"CellIdentifier];\n";
			    	
					m+="     cacheCells = [NSMutableDictionary dictionary];\n";
					m+="//end TableView \n\n";
					
					m+=closeKeyboardDeclare;
					content += m;
				}
	 if(line.contains("viewWillAppear:(BOOL)animated")&&isHeadCell==false)		
				{
					String m="//table\n";
					m+="[self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
			    	content += m;
				}
	 
	 

	 
	 
	  if(line.contains("cellForRowAtIndexPath")&&isHeadCell==false)		
				{
					String m="\n";
					
					m+="\n//分页Start(可注调)\n";
					m+=" if([indexPath row] == ([rows count])  && [rows count]>0) {\n";
				       m+=" if( currentRowCount<totalRowCount)\n";
					   m+=" {\n";
					      m+="    [self request9999:YES];\n";    
					      m+="  MoreTableViewCell *cell = [[[NSBundle mainBundle] loadNibNamed:@\"MoreTableViewCell\" owner:self options:nil] lastObject];\n";
					      m+="  return cell;\n";
				       m+=" }else\n";
					      m+=" {\n";
				          m+="    return [[UITableViewCell alloc] init ];\n";
					   m+=" }\n";
					   
				  m+="  }\n";
					    
				  m+=" else  if([indexPath row] == ([rows count]) && [rows count]==0)\n";
				  m+="{\n";
							 m+="return [[UITableViewCell alloc] init ];\n";
				  m+=" }\n";
				  m+=" else //分页End\n\n";
				  m+=" {\n";
					
					m+=" "+className+"TableViewCell *cell = ("+className+"TableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:"+className+"CellIdentifier];\n";
					m+="    if (!cell)\n";
					m+="    {\n";
					m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCell\" owner:self options:nil] lastObject];\n";
					m+="    }\n";
					m+=controllers;
					m+="return cell;\n";
				  m+=" }\n";
			    	content += m;
				}
	  
			 if(line.contains("heightForRowAtIndexPath")&&isHeadCell==false)	
			 {
					String m="\n";
					m+="NSString *reuseIdentifier = "+className+"CellIdentifier;\n";
				    m+=""+className+"TableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];\n";
				    m+="if (!cell) {\n";
				    m+="  cell=[self.tableView dequeueReusableCellWithIdentifier:"+className+"CellIdentifier];\n";
				    m+="  [self.cacheCells setObject:cell forKey:reuseIdentifier];\n";
				    m+="}\n\n";

				    m+=controllers;
				    
				    
				    
					m+="\n//分页Start(可注调)\n";
				    m+="if([indexPath row] == ([rows count])  && [rows count]>0) {\n";
				        
				      m+="if( currentRowCount<totalRowCount)\n";
				      m+="  {//LoadMoreView\n";
				    	m+="     return 0;\n";
				      m+=" }else\n";
				      m+=" {\n";
				    		m+="    return 0;\n";
				      m+=" }\n";     
				   m+="}else  if([indexPath row] == ([rows count])  && [rows count]==0) {\n";
				    			m+="   return 0;\n";
				   m+="}\n";
				   m+=" else  //分页End\n\n";
				   m+=" {\n";
				 
				    m+="   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   \n";
				    m+="return height+1;\n";
				    m+=" }\n";
			    	content += m;
			 }
		 
			 if(line.contains("@end//end viewController")  &&isHeadCell==false)	
			 {
				 
String m="";

m+="-(void) request9999:(BOOL)ismore{\n";

    m+="\n//分页Start\n";
	m+="if(ismore)\n";
    m+="{\n";
    	m+="if (requestUnComplete==false) {\n";
        	m+="requestUnComplete=true;\n";
        	m+="}else\n";
        	m+="{\n";
        	m+="return;\n";
            m+="}\n";
    m+="}else //分页End \n\n";
    	 m+="{\n";
         m+="totalRowCount=0;\n";
         m+="currentRowCount=0;\n";
         m+="page=1;\n";
         
         m+=" [rows removeAllObjects];\n";
         
         m+="if(allIndexpaths!=nil && [allIndexpaths count]>0)\n";
         m+="{\n";
        		 m+=" [self.tableView deleteRowsAtIndexPaths:allIndexpaths withRowAnimation:UITableViewRowAnimationFade];\n";
         m+="}\n";
       
         m+=" [ allIndexpaths  removeAllObjects];\n\n";
         
    m+="}\n\n";
    
    m+="....(请求内容)\n";   
 m+="}\n\n";


m+="\n//九宫图列表数据(九宫图列表用到)Start\n";
m+="@interface Section : NSObject\n";
m+="@property (strong,nonatomic) NSString *title;\n";
m+="@property (strong,nonatomic) NSString *sectionId;\n";
m+="@property (strong,nonatomic) NSMutableArray *sectionRows;\n";
m+="@end\n\n";




m+="@interface Row : NSObject\n";
m+="@property (strong,nonatomic) NSMutableArray *rowChirlds;\n";
m+="@end\n\n";


m+="@interface Chirld : NSObject\n";
m+="@property (strong,nonatomic) NSString *pic;\n";
m+="@property (strong,nonatomic) NSString *picName;\n";
m+="@property (strong,nonatomic) NSString *picPrice;\n";
m+="@property (strong,nonatomic) NSString *productId;\n";
m+="@end\n\n";

m+="//九宫图列表数据(九宫图列表用到)End\n\n";

m+="-(void) respond9999{\n";

m+="....(返回数据)\n";

m+="requestUnComplete=false;//避免重复请求 一个发完下一个再发\n";

m+="//九宫图列表数据Start\n";
m+="Row *sectionRow;\n";
m+="NSMutableArray *thisPageRows=[[NSMutableArray alloc] init];\n";
m+="			    for (int i=0; i<[mdata count]; i++) {\n";
m+="			        RespondParam0027 *commonItem2=mdata[i];\n";
m+="			        \n";
m+="			        \n";
m+="			        if (i==0 || i%3==0) {//每行3个\n";
m+="			            sectionRow=[[Row alloc ] init];\n";
m+="			            sectionRow.rowChirlds=[[NSMutableArray alloc]init];\n";
m+="			            [thisPageRows addObject:sectionRow];\n";
m+="			            [rows addObject:sectionRow];\n";
m+="			        }\n";
m+="			        \n";
m+="			        Chirld *rowChirld=[[Chirld alloc] init ];\n";
m+="			        rowChirld.productId=commonItem2.merchID;\n";
m+="			        rowChirld.pic=commonItem2.merchPicID;\n";
m+="			        rowChirld.picName=commonItem2.merchName;\n";
m+="			        rowChirld.picPrice=[NSString stringWithFormat:@\"%.2f\",commonItem2.merchPrice] ;\n";
m+="			        \n";
m+="			        //chirld add\n";
m+="			        [sectionRow.rowChirlds addObject:rowChirld];\n";
m+="			        \n";
m+="			        \n";
m+="			    }\n\n";

m+="//在函数中-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath  添加使用九宫图结果\n";
m+="Row *row=thisPageRows[indexPath.row];\n";
m+="if ([row.rowChirlds count]>0) {//第一列 }else{[cell.pic1Button.titleLabel setText:@\"\"]; }\n"; 
m+="if ([row.rowChirlds count]>1) {//第二列 } \n\n";
m+="//九宫图列表数据End\n\n";


m+="//分页Start\n";
m+="totalRowCount=commonItem.totalNum;\n";
m+="currentRowCount+=commonItem.recordNum;\n";

m+="if (commonItem.recordNum>0) {\n";
m+="    if (currentRowCount< totalRowCount) {\n";
m+="        page++;\n";
      
m+="    }\n";
m+="}else if(commonItem.recordNum==0)\n";
m+="{\n";
m+="// 暂无数据\n";
m+="}\n";
m+="//分页End\n\n";


m+="NSMutableArray *insertIndexPaths = [[NSMutableArray alloc]init];\n";
m+="for (int ind = 0; ind < [thisPageRows count]; ind++) {\n";
m+="    NSIndexPath    *newPath =  [NSIndexPath indexPathForRow:[rows indexOfObject:[thisPageRows objectAtIndex:ind]] inSection:0];\n";
m+="    [allIndexpaths addObject:newPath];\n";
m+="    [insertIndexPaths addObject:newPath];\n";
m+="}\n";
m+="[self.tableView insertRowsAtIndexPaths:insertIndexPaths withRowAnimation:UITableViewRowAnimationFade];\n";

m+="}\n\n";


content += m;
			 }

		}

		content=ImportString.autoAddImportInMFileHead(content);
	 String filename=FileUtil.makeFile(waitByModifyFileName, content);
	}


	String controllers="";
	public void analyse(List<CompomentBean> oldBeans) {
		
		//n+="    NSMutableDictionary *sectionADic=[sectionAZDicArray objectAtIndex:indexPath.section];  \n";
		//n+="    \n";
		//n+="    NSMutableArray *sectionChirldsArray=[sectionADic objectForKey:@\"SectionChirldsArray\"];\n";
		//n+="    NSMutableDictionary *chirldDic=[sectionChirldsArray objectAtIndex:indexPath.row];\n";
		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		// 找出容器
		for (CompomentBean bean : oldBeans) {
			if (bean.type.contains("Layout")) {
				if (bean.w >= maxW) {
					maxW = bean.w;
					maxBean = bean;
				}

				if (bean.h >= maxH) {
					maxH = bean.h;
					maxBean = bean;
				}

				layouts.add(bean);
			}
		}
		
		
		parent(maxBean);
	
		
  
	}

	public void parent(CompomentBean bean) {

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parent(chirld);
				} else {
					 	String selfString = "cell.";
					compomentDeclareImplement.chirld(chirld, bean, selfString,"TableViewCell");
					controllers+=compomentDeclareImplement.viewDidLoad_Declare;
					viewDidLoad_Implement+=compomentDeclareImplement.viewDidLoad_Implement;
					closeKeyboardDeclare+=compomentDeclareImplement.closeKeyboardDeclare;
					
				}
			}

		}

	}

//	public void chirld(CompomentBean chirld, CompomentBean parent) {
//
//		if (chirld.type.equals("TextView")) {
//			controllers+="//"+chirld.cnname+"\n";
//			controllers+="cell."+chirld.enname+".text= ((..*)[listData objectAtIndex:indexPath.row])."+chirld.enname+";\n";
//	
//		
//		}
//
//		if (chirld.type.equals("Button")) {
//			controllers+="//"+chirld.cnname+"\n";
//		
//	
//			  controllers+=" objc_setAssociatedObject(cell."+chirld.enname+", \"section\", indexPath.section, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//			  controllers+=" objc_setAssociatedObject(cell."+chirld.enname+", \"row\", indexPath.row, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//				
//			  
//			  controllers += "[cell."  + chirld.enname + " addTarget:self action:@selector("
//						+ chirld.enname + "Clicked:) forControlEvents:UIControlEventTouchUpInside];\n";
//			  
//		
//			  
//			  viewDidLoad_viewClickImplement += "-(void)" + chirld.enname + "Clicked:(UIButton *)btn{\n";
//				viewDidLoad_viewClickImplement += "id section = objc_getAssociatedObject(btn, \"section\");\n//取绑定数据";
//				viewDidLoad_viewClickImplement += "id section = objc_getAssociatedObject(btn, \"row\");\n//取绑定数据";
//			
//
//				// ("跳到") ("单选") ("发请求") ("弹出")
//				if (chirld.actionString.equals("跳到")) {
//					viewDidLoad_viewClickImplement += " self.hidesBottomBarWhenPushed=YES;\n";
//					viewDidLoad_viewClickImplement += firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//							+ "ViewController *" + chirld.jumpToWhichPage + "ViewController=[["
//							+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//							+ "ViewController alloc ] initWithNibName:@\""
//							+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "ViewController\" bundle:nil];\n";
//					viewDidLoad_viewClickImplement += "    [self.navigationController pushViewController:"
//							+ chirld.jumpToWhichPage + "ViewController animated:YES];\n";
//				} else if (chirld.actionString.equals("跳回到上几个")) {
//					viewDidLoad_viewClickImplement += "//方法1.回到上几个页面\n";
//					viewDidLoad_viewClickImplement += " for (UIViewController *controller in self.navigationController.viewControllers) {\n";
//					viewDidLoad_viewClickImplement += "       if (![controller isKindOfClass:[FirstPageViewController class]]) {\n";
//					viewDidLoad_viewClickImplement += "            [controller removeFromParentViewController];\n";
//					viewDidLoad_viewClickImplement += "  }\n";
//					viewDidLoad_viewClickImplement += "//方法2.回到上几个页面\n";
//					viewDidLoad_viewClickImplement += "//  [self.navigationController popToViewController:controller animated:YES];\n\n";
//					viewDidLoad_viewClickImplement += " }\n";
//					viewDidLoad_viewClickImplement += "	//    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];\n";
//					viewDidLoad_viewClickImplement += "	 //   [appDelegate.tabbar setSelectedIndex:0];\n\n";
//
//					viewDidLoad_viewClickImplement += "//方法3.回到上几个页面\n";
//					viewDidLoad_viewClickImplement += "  [self.navigationController popToRootViewControllerAnimated:YES];\n";
//
//				} else if (chirld.actionString.equals("跳回到上个")) {
//
//					viewDidLoad_viewClickImplement += "//方法3.回到上页面\n";
//					viewDidLoad_viewClickImplement += "  [self.navigationController popViewControllerAnimated:YES];\n";
//
//				} else if (chirld.actionString.equals("单选")) {
//
//				} else if (chirld.actionString.equals("发请求")) {
//					viewDidLoad_viewClickImplement += " MsgReturn *msgReturn=[[MsgReturn alloc]init];\n";
//					viewDidLoad_viewClickImplement += " msgReturn.errorCode=@\"-1\";//-1显示自定义内容\n";
//					viewDidLoad_viewClickImplement += " msgReturn.errorType=@\"02\";\n";
//					viewDidLoad_viewClickImplement += " msgReturn.errorDesc=@\"请输入搜索内容\";\n";
//					viewDidLoad_viewClickImplement += " [PromptError changeShowErrorMsg:msgReturn title:@\"\"  viewController:self block:^(BOOL OKCancel){}];\n\n";
//					viewDidLoad_viewClickImplement += "[self request" + chirld.interfaceId + "];\n";
//
//				} else if (chirld.actionString.equals("弹出")) {
//
//					viewDidLoad_viewClickImplement += "if(menu==nil){";
//					viewDidLoad_viewClickImplement += "menu=[[" + firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//							+ "MenuViewController alloc ] initWithNibName:@\""
//							+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "MenuViewController\" bundle:nil];\n";
//
//					viewDidLoad_viewClickImplement += "[menu.view setFrame:CGRectMake(menu.frame.origin.x, menu.frame.origin.y-menu.frame.size.height , .frame.size.width, .frame.size.height)];\n";
//
//					viewDidLoad_viewClickImplement += "[self.view addSubview:menu.view];\n";
//
//					viewDidLoad_viewClickImplement += "[menu setUiValue:datas  delegate:self];\n";
//					viewDidLoad_viewClickImplement += "menu.view.hidden=YES;\n";
//					viewDidLoad_viewClickImplement += "}\n\n";
//
//					viewDidLoad_viewClickImplement += "if (menu.view.hidden) {\n";
//					viewDidLoad_viewClickImplement += "    menu.view.hidden=NO;\n";
//					viewDidLoad_viewClickImplement += "}else\n";
//					viewDidLoad_viewClickImplement += "{\n";
//					viewDidLoad_viewClickImplement += "    menu.view.hidden=YES;\n";
//					viewDidLoad_viewClickImplement += "}\n";
//
//				}
//
//				viewDidLoad_viewClickImplement += "}\n\n";
//			
//		
//		}
//
//		if (chirld.type.equals("EditText")) {
//			controllers+="//"+chirld.cnname+"\n";
//			
//	
//				
//				
//				controllers += "cell."  + chirld.enname + ".tag=;\n";
//				controllers += " objc_setAssociatedObject(cell."  + chirld.enname
//						+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//
//				controllers += " cell."+ chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";
//				controllers += "[cell." + chirld.enname + " addTarget:self action:@selector("
//						+ chirld.enname + "DidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
//
//				viewDidLoad_viewClickImplement += "-(void)" + chirld.enname + "DidEndOnExit:(UITextField *)textField{\n";
//				viewDidLoad_viewClickImplement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
//				viewDidLoad_viewClickImplement += " int  orderFormIndex= textField.tag;\n";
//				viewDidLoad_viewClickImplement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
//				viewDidLoad_viewClickImplement += "     orderform.invoiceMsg=textField.text;\n";
//				viewDidLoad_viewClickImplement += "}\n\n";
//
//				controllers += " cell."  + chirld.enname + ".tag=i;\n";
//				controllers += "[cell."  + chirld.enname + " addTarget:self action:@selector("
//						+ chirld.enname + "DidEnd:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
//				controllers += " cell." + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";
//
//				viewDidLoad_viewClickImplement += "-(void)" + chirld.enname + "DidEnd:(UITextField *)textField{\n";
//				viewDidLoad_viewClickImplement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
//				viewDidLoad_viewClickImplement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
//				viewDidLoad_viewClickImplement += " int  orderFormIndex= textField.tag;\n";
//				viewDidLoad_viewClickImplement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
//				viewDidLoad_viewClickImplement += "     orderform.invoiceMsg=textField.text;\n";
//				viewDidLoad_viewClickImplement += "}\n\n";
//				
//				
//				
//				
//				closeKeyboardDeclare+= "\n UITapGestureRecognizer* closeKeyboardtap =[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(closeKeyboard)];\n";
//				closeKeyboardDeclare += "[self.scrollView addGestureRecognizer:closeKeyboardtap];\n";
//				
//				closeKeyboardImplement += "-(void)closeKeyboard\n";
//				closeKeyboardImplement += "{\n";
//				closeKeyboardImplement += " [[[UIApplication sharedApplication] keyWindow] endEditing:YES];\n";
//				closeKeyboardImplement += "}\n\n";
//
//		}
//
//		if (chirld.type.equals("CheckBox")) {
//		
//			controllers+="//"+chirld.cnname+"\n";
//			controllers+="[cell."+chirld.enname+" setImage:[UIImage imageNamed:@\""+chirld.picName+"\"] forState:UIControlStateNormal];\n";
//		    controllers+="[cell."+chirld.enname+" setImage:[UIImage imageNamed:@\"press"+chirld.picName+"\"] forState:UIControlStateSelected];\n";
//	
//			  controllers+=" objc_setAssociatedObject(cell."+chirld.enname+", \"section\", indexPath.section, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//			  controllers+=" objc_setAssociatedObject(cell."+chirld.enname+", \"row\", indexPath.row, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//				
//			controllers+="[cell."+chirld.enname+" addTarget:self action:@selector("+chirld.enname+"clicked:) forControlEvents:UIControlEventTouchUpInside];\n";
//			controllers+="-(void)"+chirld.enname+"clicked:(UIButton *)btn{\n";
//			controllers+=" //objc_setAssociatedObject(btn, \"productId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//			controllers+="id productId = objc_getAssociatedObject(btn, \"productId\");\n//取数据";
//			 controllers+="  //btn.selected = !btn.selected;\n//用于butoon做checkBox控件";
//			controllers+="}\n";
//			
//			controllers += "cell." + chirld.enname + ".tag=;\n";
//			controllers += " objc_setAssociatedObject(" + selfString + chirld.enname
//					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//
//			viewDidLoad_viewClickDeclare += "\n[" + selfString + chirld.enname + " addTarget:self action:@selector("
//					+ chirld.enname + "Check:) forControlEvents:UIControlEventTouchUpInside];\n";
//			viewDidLoad_viewClickDeclare += "[" + selfString + chirld.enname
//					+ " setBackgroundImage:[UIImage imageNamed:@\"check.png\"] forState:UIControlStateSelected];\n";
//			viewDidLoad_viewClickDeclare += " [" + selfString + chirld.enname
//					+ " setBackgroundImage:[UIImage imageNamed:@\"uncheck.png\"] forState:UIControlStateNormal];\n";
//
//			viewDidLoad_viewClickImplement += "-(void)" + chirld.enname + "Check:(UIButton *)btn{\n";
//			viewDidLoad_viewClickImplement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
//			viewDidLoad_viewClickImplement += "int mId2 = btn.tag;\n//取绑定数据";
//			viewDidLoad_viewClickImplement += "  btn.selected = !btn.selected ;//用与button做checkBox\n";
//			viewDidLoad_viewClickImplement += " OrderForm *orderform=datas[mId];\n";
//			viewDidLoad_viewClickImplement += " if (orderform.invoiceCheck ) {//选中\n";
//			viewDidLoad_viewClickImplement += "  orderform.invoiceCheck=false;\n";
//			viewDidLoad_viewClickImplement += "}else\n";
//			viewDidLoad_viewClickImplement += "{\n";
//			viewDidLoad_viewClickImplement += "   orderform.invoiceCheck=true;\n";
//			viewDidLoad_viewClickImplement += "}\n";
//			viewDidLoad_viewClickImplement += "[self refreshUi];\n";
//			viewDidLoad_viewClickImplement += "}\n\n";
//			
//		}
//
//		if (chirld.type.equals("ListView")) {
//			
//		}
//
//		if (chirld.type.equals("ImageView")) {
//			controllers+="//"+chirld.cnname+"\n";
//			controllers+="[cell."+chirld.enname+" setImageWithURL:[NSURL URLWithString:((..*)[listData objectAtIndex:indexPath.row])."+chirld.enname+" placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";
//		
//		}
//
//		if (chirld.type.equals("ExpandableListView")) {
//
//		}
//	}

	
  
}
