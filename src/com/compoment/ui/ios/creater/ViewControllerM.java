package com.compoment.ui.ios.creater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class ViewControllerM {

	String i = "\n\n\n";
	String tablem = "\n";
	String scrollm = "\n";
	String setvaluem = "\n";
	String viewDidLoad_Declare = "";
	String viewDidLoad_Implement = "";
	String pageName = "";
	String className = "";
	String closeKeyboardDeclare="";
	String closeKeyboardImplement="";
	ScrollViewCells scrollViewCells;
	CompomentDeclareImplement compomentDeclareImplement=new CompomentDeclareImplement();
	
	public ViewControllerM(String pageName, List<CompomentBean> oldBeans) {
		this.pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);
		analyseRelativeForVertical(oldBeans);
		System.out.println(i);

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

	public void analyseRelativeForVertical(List<CompomentBean> oldBeans) {

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

		i += "#import \"" + className + "ViewController.h\"\n";
		i += "#import \"UIImageView+WebCache.h\"\n";
		i += "#import <Foundation/Foundation.h>\n";
		i += "#import <PublicFramework/JSONKit.h>\n";
		i += "#import <objc/runtime.h>\n";
		i += "@implementation " + className + "ViewController\n";

		parent(maxBean);

		i += "- (void)viewDidLoad\n";
		i += "{\n";
		i += "    [super viewDidLoad];\n";

		i += viewDidLoad_Declare;

	    i+=closeKeyboardDeclare;

		i += "}\n\n";

		
		i += "-(void) viewWillAppear:(BOOL)animated{\n";

		i += "}\n\n";
		
		
		

	

		i += viewDidLoad_Implement;
		i+=closeKeyboardImplement;
		
		i += tablem;

		// i+="-(void) setUiValue{\n";
		// i+=setvaluem;
		// i+="}\n\n";

		if (scrollViewCells != null) {
			i += scrollViewCells.scrollDeclare + "\n\n";

			i += scrollViewCells.scrollImplement + "\n\n";
		}

		i += "\n@end//end viewController\n";

		FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", className + "ViewController", "m", i);
	}

	public void parent(CompomentBean bean) {

		if (bean.chirlds != null && bean.chirlds.size() > 0) {

			if (bean.type.equals("ScrollViewLayout")) {

				scrollViewCells = new ScrollViewCells();

				scrollViewCells.parent(bean);
               
			}else
			{

			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parent(chirld);

				} else {
					String selfString = "self.";
					compomentDeclareImplement.chirld(chirld, bean, selfString);
					viewDidLoad_Declare+=compomentDeclareImplement.viewDidLoad_Declare;
					viewDidLoad_Implement+=compomentDeclareImplement.viewDidLoad_Implement;
					closeKeyboardDeclare+=compomentDeclareImplement.closeKeyboardDeclare;
					tablem+=compomentDeclareImplement.tablem;
					i+=compomentDeclareImplement.i;
					setvaluem+=compomentDeclareImplement.setvaluem;
				
					
					
				}
			}
			}

		}

	}

//	public void chirld(CompomentBean chirld, CompomentBean parent) {
//
//		String selfString = "self.";
//
//
//		if (chirld.type.equals("TextView")) {
//
//			i += "//" + chirld.cnname + "\n";
//			i += "@synthesize " + chirld.enname + ";\n";
//
//			viewDidLoad_viewClickDeclare += "//" + chirld.cnname + "\n";
//			viewDidLoad_viewClickDeclare += "[" + chirld.enname + " setValue:]\n";
//		}
//
//		if (chirld.type.equals("Button")) {
//
//			i += "//" + chirld.cnname + "\n";
//			i += "@synthesize " + chirld.enname + ";\n";
//
//			viewDidLoad_viewClickDeclare += selfString + chirld.enname + ".tag=;\n";
//			viewDidLoad_viewClickDeclare += " objc_setAssociatedObject(" + selfString + chirld.enname
//					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//			viewDidLoad_viewClickDeclare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
//					+ chirld.enname + "Clicked:) forControlEvents:UIControlEventTouchUpInside];\n";
//
//			viewDidLoad_viewClickImplement += "-(void)" + chirld.enname + "Clicked:(UIButton *)btn{\n";
//			viewDidLoad_viewClickImplement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
//			viewDidLoad_viewClickImplement += "int mId2 = btn.tag;\n//取绑定数据";
//
//			// ("跳到") ("单选") ("发请求") ("弹出")
//			if (chirld.actionString.equals("跳到")) {
//				viewDidLoad_viewClickImplement += " self.hidesBottomBarWhenPushed=YES;\n";
//				viewDidLoad_viewClickImplement += firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//						+ "ViewController *" + chirld.jumpToWhichPage + "ViewController=[["
//						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//						+ "ViewController alloc ] initWithNibName:@\""
//						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "ViewController\" bundle:nil];\n";
//				viewDidLoad_viewClickImplement += "    [self.navigationController pushViewController:"
//						+ chirld.jumpToWhichPage + "ViewController animated:YES];\n";
//			} else if (chirld.actionString.equals("跳回到上几个")) {
//				viewDidLoad_viewClickImplement += "//方法1.回到上几个页面\n";
//				viewDidLoad_viewClickImplement += " for (UIViewController *controller in self.navigationController.viewControllers) {\n";
//				viewDidLoad_viewClickImplement += "       if (![controller isKindOfClass:[FirstPageViewController class]]) {\n";
//				viewDidLoad_viewClickImplement += "            [controller removeFromParentViewController];\n";
//				viewDidLoad_viewClickImplement += "  }\n";
//				viewDidLoad_viewClickImplement += "//方法2.回到上几个页面\n";
//				viewDidLoad_viewClickImplement += "//  [self.navigationController popToViewController:controller animated:YES];\n\n";
//				viewDidLoad_viewClickImplement += " }\n";
//				viewDidLoad_viewClickImplement += "	//    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];\n";
//				viewDidLoad_viewClickImplement += "	 //   [appDelegate.tabbar setSelectedIndex:0];\n\n";
//
//				viewDidLoad_viewClickImplement += "//方法3.回到上几个页面\n";
//				viewDidLoad_viewClickImplement += "  [self.navigationController popToRootViewControllerAnimated:YES];\n";
//
//			} else if (chirld.actionString.equals("跳回到上个")) {
//
//				viewDidLoad_viewClickImplement += "//方法3.回到上页面\n";
//				viewDidLoad_viewClickImplement += "  [self.navigationController popViewControllerAnimated:YES];\n";
//
//			} else if (chirld.actionString.equals("单选")) {
//
//			} else if (chirld.actionString.equals("发请求")) {
//				viewDidLoad_viewClickImplement += " MsgReturn *msgReturn=[[MsgReturn alloc]init];\n";
//				viewDidLoad_viewClickImplement += " msgReturn.errorCode=@\"-1\";//-1显示自定义内容\n";
//				viewDidLoad_viewClickImplement += " msgReturn.errorType=@\"02\";\n";
//				viewDidLoad_viewClickImplement += " msgReturn.errorDesc=@\"请输入搜索内容\";\n";
//				viewDidLoad_viewClickImplement += " [PromptError changeShowErrorMsg:msgReturn title:@\"\"  viewController:self block:^(BOOL OKCancel){}];\n\n";
//				viewDidLoad_viewClickImplement += "[self request" + chirld.interfaceId + "];\n";
//
//			} else if (chirld.actionString.equals("弹出")) {
//
//				viewDidLoad_viewClickImplement += "if(menu==nil){";
//				viewDidLoad_viewClickImplement += "menu=[[" + firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//						+ "MenuViewController alloc ] initWithNibName:@\""
//						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "MenuViewController\" bundle:nil];\n";
//
//				viewDidLoad_viewClickImplement += "[menu.view setFrame:CGRectMake(menu.frame.origin.x, menu.frame.origin.y-menu.frame.size.height , .frame.size.width, .frame.size.height)];\n";
//
//				viewDidLoad_viewClickImplement += "[self.view addSubview:menu.view];\n";
//
//				viewDidLoad_viewClickImplement += "[menu setUiValue:datas  delegate:self];\n";
//				viewDidLoad_viewClickImplement += "menu.view.hidden=YES;\n";
//				viewDidLoad_viewClickImplement += "}\n\n";
//
//				viewDidLoad_viewClickImplement += "if (menu.view.hidden) {\n";
//				viewDidLoad_viewClickImplement += "    menu.view.hidden=NO;\n";
//				viewDidLoad_viewClickImplement += "}else\n";
//				viewDidLoad_viewClickImplement += "{\n";
//				viewDidLoad_viewClickImplement += "    menu.view.hidden=YES;\n";
//				viewDidLoad_viewClickImplement += "}\n";
//
//			}
//
//			viewDidLoad_viewClickImplement += "}\n\n";
//
//		}
//
//		if (chirld.type.equals("EditText")) {
//
//			i += "//" + chirld.cnname + "\n";
//			i += "@synthesize " + chirld.enname + ";\n";
//
//			setvaluem += "//" + chirld.cnname + "\n";
//			setvaluem += "[" + chirld.enname + " setValue:]\n";
//
//			viewDidLoad_viewClickDeclare += "" + selfString + chirld.enname + ".tag=;\n";
//			viewDidLoad_viewClickDeclare += " objc_setAssociatedObject(" + selfString + chirld.enname
//					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//
//			viewDidLoad_viewClickDeclare += " " + selfString + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";
//			viewDidLoad_viewClickDeclare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
//					+ chirld.enname + "DidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
//
//			viewDidLoad_viewClickImplement += "-(void)" + chirld.enname + "DidEndOnExit:(UITextField *)textField{\n";
//			viewDidLoad_viewClickImplement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
//			viewDidLoad_viewClickImplement += " int  orderFormIndex= textField.tag;\n";
//			viewDidLoad_viewClickImplement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
//			viewDidLoad_viewClickImplement += "     orderform.invoiceMsg=textField.text;\n";
//			viewDidLoad_viewClickImplement += "}\n\n";
//
//			viewDidLoad_viewClickDeclare += " " + selfString + chirld.enname + ".tag=i;\n";
//			viewDidLoad_viewClickDeclare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
//					+ chirld.enname + "DidEnd:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
//			viewDidLoad_viewClickDeclare += " " + selfString + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";
//
//			viewDidLoad_viewClickImplement += "-(void)" + chirld.enname + "DidEnd:(UITextField *)textField{\n";
//			viewDidLoad_viewClickImplement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
//			viewDidLoad_viewClickImplement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
//			viewDidLoad_viewClickImplement += " int  orderFormIndex= textField.tag;\n";
//			viewDidLoad_viewClickImplement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
//			viewDidLoad_viewClickImplement += "     orderform.invoiceMsg=textField.text;\n";
//			viewDidLoad_viewClickImplement += "}\n\n";
//			
//			
//			
//			closeKeyboardDeclare+= "\n UITapGestureRecognizer* closeKeyboardtap =[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(closeKeyboard)];\n";
//			closeKeyboardDeclare += "[self.scrollView addGestureRecognizer:closeKeyboardtap];\n";
//			
//			closeKeyboardImplement += "-(void)closeKeyboard\n";
//			closeKeyboardImplement += "{\n";
//			closeKeyboardImplement += " [[[UIApplication sharedApplication] keyWindow] endEditing:YES];\n";
//			closeKeyboardImplement += "}\n\n";
//
//		}
//
//		if (chirld.type.equals("CheckBox")) {
//
//			i += "//" + chirld.cnname + "\n";
//			i += "@synthesize " + chirld.enname + ";\n";
//
//			viewDidLoad_viewClickDeclare += "" + selfString + chirld.enname + ".tag=;\n";
//			viewDidLoad_viewClickDeclare += " objc_setAssociatedObject(" + selfString + chirld.enname
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
//			i += "//" + chirld.cnname + "\n";
//			i += "@synthesize tableView;\n";
//
//			
//			
//			tablem += "-(void)viewWillLayoutSubviews\n";
//			tablem += "{\n";
//			tablem += "int startY=self.headView.frame.origin.y+self.headView.frame.size.height;\n";
//			tablem += " [self.tableView setFrame:CGRectMake(0, startY, self."+chirld.enname+".frame.size.width, self.view.frame.size.height-startY )];\n";
//			tablem += "}\n\n";
//			
//			
//			tablem += "- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{\n";
//			tablem += "    return @\"\";\n";
//			tablem += "  \n";
//			tablem += "}\n\n";
//
//			tablem += "//自定义SectionHeader\n";
//			tablem += "- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{\n";
//
//			tablem += "    return nil;\n";
//			tablem += "}\n\n";
//
//			tablem += "//自定义SectionHeader高度\n";
//			tablem += "-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{\n";
//			tablem += "    return 22.0;\n";
//			tablem += "}\n\n";
//
//			tablem += "//指定有多少个分区(Section)，默认为1\n";
//			tablem += "- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {\n";
//			tablem += "    \n";
//			tablem += "    return 1;//返回标题数组中元素的个数来确定分区的个数   return [sections count];\n";
//			tablem += "}\n\n";
//
//			tablem += "//指定每个分区中有多少行，默认为1\n";
//			tablem += "- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{\n";
//			tablem += "    \n";
//
//			tablem += "     return  [rows count]+1;\n";
//			tablem += "    \n";
//			tablem += "}\n\n";
//
//			tablem += "//绘制Cell\n";
//			tablem += "-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
//			tablem += "    \n";
//
//			tablem += "}\n\n";
//
//			tablem += "//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度\n";
//			tablem += "- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{\n";
//
//			tablem += "}\n\n";
//
//	
//
//			tablem += "- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
//			tablem += "    return 88;\n";
//			tablem += "}\n\n";
//
//			tablem += "//点击后，过段时间cell自动取消选中\n";
//			tablem += "- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{\n";
//			tablem += "if (indexPath.row == [rows count]  && [rows count]>0) {\n";
//		    tablem += "[self request9999:YES];\n";
//			tablem += "   return;\n";
//			tablem += "}else{\n}\n";
//					
//			tablem += "    //消除cell选择痕迹\n";
//			tablem += "    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];\n";
//			tablem += "}\n";
//			tablem += "- (void)deselect\n";
//			tablem += "{\n";
//			tablem += "    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
//			tablem += "}\n";
//		}
//
//		if (chirld.type.equals("ImageView")) {
//
//			i += "//" + chirld.cnname + "\n";
//			i += "@synthesize " + chirld.enname + ";\n";
//
//			setvaluem += "//" + chirld.cnname + "\n";
//			setvaluem += "[" + chirld.enname + " setImage:[UIImage imageNamed:@\"1.jpeg\"]]\n";
//			setvaluem += "[" + chirld.enname
//					+ " setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";
//
//		}
//		
//		if (chirld.type.equals("ScrollView")) {
//
//		}
//
//		
//
//		if (chirld.type.equals("ExpandableListView")) {
//
//		}
//
//	}

	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.time != s2.time) {
				return (int) (s2.time - s1.time);
			}
			return 0;
		}
	};

}
