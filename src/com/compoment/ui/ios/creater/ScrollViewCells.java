package com.compoment.ui.ios.creater;

import java.util.ArrayList;
import java.util.List;

import com.compoment.cut.CompomentBean;
import com.compoment.cut.iphone.IphoneTableViewCellXib;

public class ScrollViewCells {

	String scrollDeclare="";
	String scrollImplement="";
	CompomentDeclareImplement compomentDeclareImplement=new CompomentDeclareImplement();
	public void parent(CompomentBean bean) {// bean.type==ScrollViewLayout

		if (bean.chirlds != null && bean.chirlds.size() > 0) {

			if (bean.type.equals("ScrollViewLayout")) {
				// start
				scrollDeclare += "-(void) scrollUI{\n";
				
				scrollDeclare += "   for (UIView *view in views) {\n";
						scrollDeclare += "        [view removeFromSuperview];\n";
						scrollDeclare += "}\n";
								scrollDeclare += " [views removeAllObjects];\n";
				
				scrollDeclare += "int height=0;\n";
				scrollDeclare += "int width=self."+bean.enname.replace("Layout", "")+".frame.size.width;\n";
				scrollDeclare += "int x=0;\n";
				scrollDeclare += "int y=0;\n\n";

			}

			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					if (bean.type.equals("ScrollViewLayout")) {

						if (chirld.isRunTimeAddScrollView) {
						
							List maxbean=new ArrayList();
							maxbean.add(chirld);

							TableViewCellH tableViewCellH = new TableViewCellH(chirld.enname, maxbean,
									"ScrollViewCell");
							TableViewCellM tableViewCellM = new TableViewCellM(chirld.enname, maxbean,
									"ScrollViewCell");

							IphoneTableViewCellXib iphoneLayout = new IphoneTableViewCellXib(chirld.enname,maxbean,"ScrollViewCell");
							
							scrollDeclare += firstCharToUpperAndJavaName(chirld.enname) + "ScrollViewCell *"
									+ chirld.enname + " = [[[NSBundle mainBundle] loadNibNamed:@\""
									+ firstCharToUpperAndJavaName(chirld.enname)
									+ "ScrollViewCell\"  owner:self options:nil] lastObject];\n";
							scrollDeclare += "  [" + chirld.enname + " setFrame:CGRectMake(x, y+height, width, "
									+ chirld.enname + ".frame.size.height)];\n";

							scrollDeclare += " height+=" + chirld.enname + ".frame.size.height;\n";

							scrollDeclare += " [self." + bean.enname.replace("Layout", "") + " addSubview:" + chirld.enname + "];\n\n";
							scrollDeclare += " [views addObject:" + chirld.enname + "];\n\n";
						} else

						{

							scrollDeclare += "  [" + chirld.enname + " setFrame:CGRectMake(x, y+height, width, "
									+ chirld.enname + ".frame.size.height)];\n";

							scrollDeclare += " height+=" + chirld.enname + ".frame.size.height;\n\n";
						}
					}
					parent(chirld);

				} else {
					String selfString = "self.";
					if (bean.isRunTimeAddScrollView) {
						selfString = bean.enname+".";
					}
					compomentDeclareImplement.chirld(chirld, bean,selfString);
				
					compomentDeclareImplement.chirld(chirld, bean, selfString);
					scrollDeclare+=compomentDeclareImplement.viewDidLoad_Declare;
					scrollImplement+=compomentDeclareImplement.viewDidLoad_Implement;
					

				}
			}

			if (bean.type.equals("ScrollViewLayout")) {
				// end
				scrollDeclare += "//scrollView\n";
				scrollDeclare += "self."+bean.enname.replace("Layout", "")+".contentSize=CGSizeMake(width, height);\n\n";

				scrollDeclare += " UIEdgeInsets contentInsets = UIEdgeInsetsZero;\n";
				scrollDeclare += " self."+bean.enname.replace("Layout", "")+".contentInset = contentInsets;\n";
				scrollDeclare += " self."+bean.enname.replace("Layout", "")+".scrollIndicatorInsets = contentInsets;\n\n";

				scrollDeclare += " [self."+bean.enname.replace("Layout", "")+" setFrame:CGRectMake(0, self.head.frame.size.height, self."+bean.enname.replace("Layout", "")+".frame.size.width, self.view.frame.size.height-self.head.frame.size.height-self.bottom.frame.size.height)];\n";
				
				scrollDeclare += "}\n";

			}

		}

	}

//	public void chirld(CompomentBean chirld, CompomentBean parent) {
//
//		String selfString = "self.";
//		if (parent.isRunTimeAddScrollView) {
//			selfString = parent.enname+".";
//		}
//
//		if (chirld.type.equals("TextView")) {
//
//			scrollDeclare += "\n//" + chirld.cnname + "\n";
//			scrollDeclare += "[" + selfString+chirld.enname + " setText:"+chirld.interfaceColumnEnName+"];\n\n";
//			
//			scrollDeclare += "//start换行高度\n";
//			scrollDeclare += "   ["+selfString+chirld.enname+" setNumberOfLines:0];\n";
//			scrollDeclare += ""+selfString+chirld.enname+".lineBreakMode = NSLineBreakByWordWrapping;\n";
//			scrollDeclare += " CGSize   size"+chirld.enname+" = [ "+selfString+chirld.enname+"  sizeThatFits:CGSizeMake("+selfString+chirld.enname+".frame.size.width, MAXFLOAT)];\n";
//			scrollDeclare += " ["+selfString+chirld.enname+" setFrame:CGRectMake("+selfString+chirld.enname+".frame.origin.x \n";
//		    scrollDeclare += "         , "+selfString+chirld.enname+".frame.origin.y, "+selfString+chirld.enname+".frame.size.width, size"+chirld.enname+".height)];\n";
//		    scrollDeclare += "//end换行高度\n";
//			
//		}
//
//		if (chirld.type.equals("Button")) {
//
//			scrollDeclare += "\n//" + chirld.cnname + "\n";
//
//			scrollDeclare += selfString + chirld.enname + ".tag=;\n";
//			scrollDeclare += " objc_setAssociatedObject(" + selfString + chirld.enname
//					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//			scrollDeclare += "[" + selfString + chirld.enname + " addTarget:self action:@selector(" + chirld.enname
//					+ "Clicked:) forControlEvents:UIControlEventTouchUpInside];\n";
//
//			scrollImplement += "-(void)" + chirld.enname + "Clicked:(UIButton *)btn{\n";
//			scrollImplement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
//			scrollImplement += "int mId2 = btn.tag;\n//取绑定数据";
//
//			// ("跳到") ("单选") ("发请求") ("弹出")
//			if (chirld.actionString.equals("跳到")) {
//				scrollImplement += " self.hidesBottomBarWhenPushed=YES;\n";
//				scrollImplement += firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "ViewController *"
//						+ chirld.jumpToWhichPage + "ViewController=[["
//						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//						+ "ViewController alloc ] initWithNibName:@\""
//						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "ViewController\" bundle:nil];\n";
//				scrollImplement += "    [self.navigationController pushViewController:" + chirld.jumpToWhichPage
//						+ "ViewController animated:YES];\n";
//			} else if (chirld.actionString.equals("跳回到上几个")) {
//				scrollImplement += "//方法1.回到上几个页面\n";
//				scrollImplement += " for (UIViewController *controller in self.navigationController.viewControllers) {\n";
//				scrollImplement += "       if (![controller isKindOfClass:[FirstPageViewController class]]) {\n";
//				scrollImplement += "            [controller removeFromParentViewController];\n";
//				scrollImplement += "  }\n";
//				scrollImplement += "//方法2.回到上几个页面\n";
//				scrollImplement += "//  [self.navigationController popToViewController:controller animated:YES];\n\n";
//				scrollImplement += " }\n";
//				scrollImplement += "	//    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];\n";
//				scrollImplement += "	 //   [appDelegate.tabbar setSelectedIndex:0];\n\n";
//
//				scrollImplement += "//方法3.回到上几个页面\n";
//				scrollImplement += "  [self.navigationController popToRootViewControllerAnimated:YES];\n";
//
//			} else if (chirld.actionString.equals("跳回到上个")) {
//
//				scrollImplement += "//方法3.回到上页面\n";
//				scrollImplement += "  [self.navigationController popViewControllerAnimated:YES];\n";
//
//			} else if (chirld.actionString.equals("单选")) {
//
//			} else if (chirld.actionString.equals("发请求")) {
//				scrollImplement += " MsgReturn *msgReturn=[[MsgReturn alloc]init];\n";
//				scrollImplement += " msgReturn.errorCode=@\"-1\";//-1显示自定义内容\n";
//				scrollImplement += " msgReturn.errorType=@\"02\";\n";
//				scrollImplement += " msgReturn.errorDesc=@\"请输入搜索内容\";\n";
//				scrollImplement += " [PromptError changeShowErrorMsg:msgReturn title:@\"\"  viewController:self block:^(BOOL OKCancel){}];\n\n";
//				scrollImplement += "[self request" + chirld.interfaceId + "];\n";
//
//			} else if (chirld.actionString.equals("弹出")) {
//
//				scrollImplement += "if(menu==nil){";
//				scrollImplement += "menu=[[" + firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
//						+ "MenuViewController alloc ] initWithNibName:@\""
//						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "MenuViewController\" bundle:nil];\n";
//
//				scrollImplement += "[menu.view setFrame:CGRectMake(menu.frame.origin.x, menu.frame.origin.y-menu.frame.size.height , .frame.size.width, .frame.size.height)];\n";
//
//				scrollImplement += "[self.view addSubview:menu.view];\n";
//
//				scrollImplement += "[menu setUiValue:datas  delegate:self];\n";
//				scrollImplement += "menu.view.hidden=YES;\n";
//				scrollImplement += "}\n\n";
//
//				scrollImplement += "if (menu.view.hidden) {\n";
//				scrollImplement += "    menu.view.hidden=NO;\n";
//				scrollImplement += "}else\n";
//				scrollImplement += "{\n";
//				scrollImplement += "    menu.view.hidden=YES;\n";
//				scrollImplement += "}\n";
//
//			}
//
//			scrollImplement += "}\n\n";
//
//		}
//
//		if (chirld.type.equals("EditText")) {
//
//			scrollDeclare += "\n//" + chirld.cnname + "\n";
//
//			scrollDeclare += "" + selfString + chirld.enname + ".tag=;\n";
//			scrollDeclare += " objc_setAssociatedObject(" + selfString + chirld.enname
//					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//
//			scrollDeclare += " " + selfString + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";
//			scrollDeclare += "[" + selfString + chirld.enname + " addTarget:self action:@selector(" + chirld.enname
//					+ "DidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
//
//			scrollImplement += "-(void)" + chirld.enname + "DidEndOnExit:(UITextField *)textField{\n";
//			scrollImplement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
//			scrollImplement += " int  orderFormIndex= textField.tag;\n";
//			scrollImplement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
//			scrollImplement += "     orderform.invoiceMsg=textField.text;\n";
//			scrollImplement += "}\n\n";
//
//			scrollDeclare += " " + selfString + chirld.enname + ".tag=i;\n";
//			scrollDeclare += "[" + selfString + chirld.enname + " addTarget:self action:@selector(" + chirld.enname
//					+ "DidEnd:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
//			scrollDeclare += " " + selfString + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";
//
//			scrollImplement += "-(void)" + chirld.enname + "DidEnd:(UITextField *)textField{\n";
//			scrollImplement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
//			scrollImplement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
//			scrollImplement += " int  orderFormIndex= textField.tag;\n";
//			scrollImplement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
//			scrollImplement += "     orderform.invoiceMsg=textField.text;\n";
//			scrollImplement += "}\n\n";
//
//		}
//
//		if (chirld.type.equals("CheckBox")) {
//
//			scrollDeclare += "\n//" + chirld.cnname + "\n";
//
//			scrollDeclare += "" + selfString + chirld.enname + ".tag=;\n";
//			scrollDeclare += " objc_setAssociatedObject(" + selfString + chirld.enname
//					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
//
//			scrollDeclare += "\n[" + selfString + chirld.enname + " addTarget:self action:@selector(" + chirld.enname
//					+ "Check:) forControlEvents:UIControlEventTouchUpInside];\n";
//			scrollDeclare += "[" + selfString + chirld.enname
//					+ " setBackgroundImage:[UIImage imageNamed:@\"check.png\"] forState:UIControlStateSelected];\n";
//			scrollDeclare += " [" + selfString + chirld.enname
//					+ " setBackgroundImage:[UIImage imageNamed:@\"uncheck.png\"] forState:UIControlStateNormal];\n";
//
//			scrollImplement += "-(void)" + chirld.enname + "Check:(UIButton *)btn{\n";
//			scrollImplement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
//			scrollImplement += "int mId2 = btn.tag;\n//取绑定数据";
//			scrollImplement += "  btn.selected = !btn.selected ;//用与button做checkBox\n";
//			scrollImplement += " OrderForm *orderform=datas[mId];\n";
//			scrollImplement += " if (orderform.invoiceCheck ) {//选中\n";
//			scrollImplement += "  orderform.invoiceCheck=false;\n";
//			scrollImplement += "}else\n";
//			scrollImplement += "{\n";
//			scrollImplement += "   orderform.invoiceCheck=true;\n";
//			scrollImplement += "}\n";
//			scrollImplement += "[self refreshUi];\n";
//			scrollImplement += "}\n\n";
//
//		}
//
//		if (chirld.type.equals("ListView")) {
//
//		}
//
//		if (chirld.type.equals("ImageView")) {
//
//			scrollDeclare += "\n//" + chirld.cnname + "\n";
//			scrollDeclare += "[" + chirld.enname + " setImage:[UIImage imageNamed:@\"1.jpeg\"]]\n";
//			scrollDeclare += "[" + chirld.enname
//					+ " setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";
//
//		}
//
//		if (chirld.type.equals("ExpandableListView")) {
//
//		}
//
//	}

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
}
