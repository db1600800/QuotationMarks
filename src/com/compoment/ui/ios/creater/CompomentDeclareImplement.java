package com.compoment.ui.ios.creater;

import com.compoment.cut.CompomentBean;

public class CompomentDeclareImplement {
	
	
	public String i="";
	public String viewDidLoad_Declare="";
	public String viewDidLoad_Implement="";
	public String setvaluem="";
	public String closeKeyboardDeclare="";
	public String closeKeyboardImplement="";
	public String tablem="";
	

	

	public void chirld(CompomentBean chirld, CompomentBean parent,String selfString  ) {

		i="";
		viewDidLoad_Declare="";
		viewDidLoad_Implement="";
		 setvaluem="";
		 closeKeyboardDeclare="";
		 closeKeyboardImplement="";
		 tablem="";


		if (chirld.type.equals("TextView")) {

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			viewDidLoad_Declare += "//" + chirld.cnname + "\n";
			viewDidLoad_Declare += "[" +selfString +chirld.enname + " setText:"+chirld.interfaceColumnEnName+"];\n";
			
			if(chirld.isRunTimeHeightTextview)
			{
			viewDidLoad_Declare += "//start换行高度\n";
			viewDidLoad_Declare += "   ["+selfString+chirld.enname+" setNumberOfLines:0];\n";
			viewDidLoad_Declare += ""+selfString+chirld.enname+".lineBreakMode = NSLineBreakByWordWrapping;\n";
			viewDidLoad_Declare += " CGSize   size"+chirld.enname+" = [ "+selfString+chirld.enname+"  sizeThatFits:CGSizeMake("+selfString+chirld.enname+".frame.size.width, MAXFLOAT)];\n";
			viewDidLoad_Declare += " ["+selfString+chirld.enname+" setFrame:CGRectMake("+selfString+chirld.enname+".frame.origin.x \n";
			viewDidLoad_Declare += "         , "+selfString+chirld.enname+".frame.origin.y, "+selfString+chirld.enname+".frame.size.width, size"+chirld.enname+".height)];\n";
			viewDidLoad_Declare += "//end换行高度\n";
			}
			
		}

		if (chirld.type.equals("Button")) {

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			viewDidLoad_Declare += selfString + chirld.enname + ".tag=;\n";
			viewDidLoad_Declare += " objc_setAssociatedObject(" + selfString + chirld.enname
					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "Clicked:) forControlEvents:UIControlEventTouchUpInside];\n";

			viewDidLoad_Implement += "-(void)" + chirld.enname + "Clicked:(UIButton *)btn{\n";
			viewDidLoad_Implement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
			viewDidLoad_Implement += "int mId2 = btn.tag;\n//取绑定数据";

			// ("跳到") ("单选") ("发请求") ("弹出")
			if ("跳到".equals( chirld.actionString)) {
				viewDidLoad_Implement += " self.hidesBottomBarWhenPushed=YES;\n";
				viewDidLoad_Implement += firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
						+ "ViewController *" + chirld.jumpToWhichPage + "ViewController=[["
						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
						+ "ViewController alloc ] initWithNibName:@\""
						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "ViewController\" bundle:nil];\n";
				viewDidLoad_Implement += "    [self.navigationController pushViewController:"
						+ chirld.jumpToWhichPage + "ViewController animated:YES];\n";
			} else if ("跳回到上几个".equals( chirld.actionString)) {
				viewDidLoad_Implement += "//方法1.回到上几个页面\n";
				viewDidLoad_Implement += " for (UIViewController *controller in self.navigationController.viewControllers) {\n";
				viewDidLoad_Implement += "       if (![controller isKindOfClass:[FirstPageViewController class]]) {\n";
				viewDidLoad_Implement += "            [controller removeFromParentViewController];\n";
				viewDidLoad_Implement += "  }\n";
				viewDidLoad_Implement += "//方法2.回到上几个页面\n";
				viewDidLoad_Implement += "//  [self.navigationController popToViewController:controller animated:YES];\n\n";
				viewDidLoad_Implement += " }\n";
				viewDidLoad_Implement += "	//    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];\n";
				viewDidLoad_Implement += "	 //   [appDelegate.tabbar setSelectedIndex:0];\n\n";

				viewDidLoad_Implement += "//方法3.回到上几个页面\n";
				viewDidLoad_Implement += "  [self.navigationController popToRootViewControllerAnimated:YES];\n";

			} else if ("跳回到上个".equals( chirld.actionString)) {

				viewDidLoad_Implement += "//方法3.回到上页面\n";
				viewDidLoad_Implement += "  [self.navigationController popViewControllerAnimated:YES];\n";

			} else if ( "单选".equals( chirld.actionString)) {

			} else if ( "发请求".equals( chirld.actionString)) {
				viewDidLoad_Implement += " MsgReturn *msgReturn=[[MsgReturn alloc]init];\n";
				viewDidLoad_Implement += " msgReturn.errorCode=@\"-1\";//-1显示自定义内容\n";
				viewDidLoad_Implement += " msgReturn.errorType=@\"02\";\n";
				viewDidLoad_Implement += " msgReturn.errorDesc=@\"请输入搜索内容\";\n";
				viewDidLoad_Implement += " [PromptError changeShowErrorMsg:msgReturn title:@\"\"  viewController:self block:^(BOOL OKCancel){}];\n\n";
				viewDidLoad_Implement += "[self request" + chirld.interfaceId + "];\n";

			} else if ("弹出".equals( chirld.actionString)) {

				viewDidLoad_Implement += "if(menu==nil){";
				viewDidLoad_Implement += "menu=[[" + firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
						+ "MenuViewController alloc ] initWithNibName:@\""
						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "MenuViewController\" bundle:nil];\n";

				viewDidLoad_Implement += "[menu.view setFrame:CGRectMake(menu.frame.origin.x, menu.frame.origin.y-menu.frame.size.height , .frame.size.width, .frame.size.height)];\n";

				viewDidLoad_Implement += "[self.view addSubview:menu.view];\n";

				viewDidLoad_Implement += "[menu setUiValue:datas  delegate:self];\n";
				viewDidLoad_Implement += "menu.view.hidden=YES;\n";
				viewDidLoad_Implement += "}\n\n";

				viewDidLoad_Implement += "if (menu.view.hidden) {\n";
				viewDidLoad_Implement += "    menu.view.hidden=NO;\n";
				viewDidLoad_Implement += "}else\n";
				viewDidLoad_Implement += "{\n";
				viewDidLoad_Implement += "    menu.view.hidden=YES;\n";
				viewDidLoad_Implement += "}\n";

			}

			viewDidLoad_Implement += "}\n\n";

		}

		if (chirld.type.equals("EditText")) {

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			setvaluem += "//" + chirld.cnname + "\n";
			setvaluem += "[" + chirld.enname + " setValue:]\n";

			viewDidLoad_Declare += "" + selfString + chirld.enname + ".tag=;\n";
			viewDidLoad_Declare += " objc_setAssociatedObject(" + selfString + chirld.enname
					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";

			viewDidLoad_Declare += " " + selfString + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "DidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";

			viewDidLoad_Implement += "-(void)" + chirld.enname + "DidEndOnExit:(UITextField *)textField{\n";
			viewDidLoad_Implement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
			viewDidLoad_Implement += " int  orderFormIndex= textField.tag;\n";
			viewDidLoad_Implement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
			viewDidLoad_Implement += "     orderform.invoiceMsg=textField.text;\n";
			viewDidLoad_Implement += "}\n\n";

			viewDidLoad_Declare += " " + selfString + chirld.enname + ".tag=i;\n";
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "DidEnd:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
			viewDidLoad_Declare += " " + selfString + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n";

			viewDidLoad_Implement += "-(void)" + chirld.enname + "DidEnd:(UITextField *)textField{\n";
			viewDidLoad_Implement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
			viewDidLoad_Implement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
			viewDidLoad_Implement += " int  orderFormIndex= textField.tag;\n";
			viewDidLoad_Implement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
			viewDidLoad_Implement += "     orderform.invoiceMsg=textField.text;\n";
			viewDidLoad_Implement += "}\n\n";
			
			
			
			closeKeyboardDeclare+= "\n UITapGestureRecognizer* closeKeyboardtap =[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(closeKeyboard)];\n";
			closeKeyboardDeclare += "[self.scrollView addGestureRecognizer:closeKeyboardtap];\n";
			
			closeKeyboardImplement += "-(void)closeKeyboard\n";
			closeKeyboardImplement += "{\n";
			closeKeyboardImplement += " [[[UIApplication sharedApplication] keyWindow] endEditing:YES];\n";
			closeKeyboardImplement += "}\n\n";

		}

		if (chirld.type.equals("CheckBox")) {

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			viewDidLoad_Declare += "" + selfString + chirld.enname + ".tag=;\n";
			viewDidLoad_Declare += " objc_setAssociatedObject(" + selfString + chirld.enname
					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";

			viewDidLoad_Declare += "\n[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "Check:) forControlEvents:UIControlEventTouchUpInside];\n";
			viewDidLoad_Declare += "[" + selfString + chirld.enname
					+ " setBackgroundImage:[UIImage imageNamed:@\"check.png\"] forState:UIControlStateSelected];\n";
			viewDidLoad_Declare += " [" + selfString + chirld.enname
					+ " setBackgroundImage:[UIImage imageNamed:@\"uncheck.png\"] forState:UIControlStateNormal];\n";

			viewDidLoad_Implement += "-(void)" + chirld.enname + "Check:(UIButton *)btn{\n";
			viewDidLoad_Implement += "id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
			viewDidLoad_Implement += "int mId2 = btn.tag;\n//取绑定数据";
			viewDidLoad_Implement += "  btn.selected = !btn.selected ;//用与button做checkBox\n";
			viewDidLoad_Implement += " OrderForm *orderform=datas[mId];\n";
			viewDidLoad_Implement += " if (orderform.invoiceCheck ) {//选中\n";
			viewDidLoad_Implement += "  orderform.invoiceCheck=false;\n";
			viewDidLoad_Implement += "}else\n";
			viewDidLoad_Implement += "{\n";
			viewDidLoad_Implement += "   orderform.invoiceCheck=true;\n";
			viewDidLoad_Implement += "}\n";
			viewDidLoad_Implement += "[self refreshUi];\n";
			viewDidLoad_Implement += "}\n\n";

		}

		if (chirld.type.equals("ListView")) {
			i += "//" + chirld.cnname + "\n";
			i += "@synthesize tableView;\n";

			
			
			tablem += "-(void)viewWillLayoutSubviews\n";
			tablem += "{\n";
			tablem += "int startY=self.headView.frame.origin.y+self.headView.frame.size.height;\n";
			tablem += " [self.tableView setFrame:CGRectMake(0, startY, self.tableView.frame.size.width, self.view.frame.size.height-startY )];\n";
			tablem += "}\n\n";
			
			
			

			tablem += "//指定有多少个分区(Section)，默认为1\n";
			tablem += "- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {\n";
			tablem += "    \n";
			tablem += "    return 1;//返回标题数组中元素的个数来确定分区的个数   return [sections count];\n";
			tablem += "}\n\n";

			tablem += "//指定每个分区中有多少行，默认为1\n";
			tablem += "- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{\n";
			tablem += "    \n";

			tablem += "     return  [rows count]+1;\n";
			tablem += "    \n";
			tablem += "}\n\n";

			tablem += "//绘制Cell\n";
			tablem += "-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
			tablem += "    \n";

			tablem += "}\n\n";

			tablem += "//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度\n";
			tablem += "- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{\n";

			tablem += "}\n\n";

	

			tablem += "- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
			tablem += "    return 88;\n";
			tablem += "}\n\n";

			tablem += "//点击后，过段时间cell自动取消选中\n";
			tablem += "- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{\n";
			tablem += "if (indexPath.row == [rows count]  && [rows count]>0) {\n";
		    tablem += "[self request9999:YES];\n";
			tablem += "   return;\n";
			tablem += "}else{\n}\n";
					
			tablem += "    //消除cell选择痕迹\n";
			tablem += "    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];\n";
			tablem += "}\n";
			tablem += "- (void)deselect\n";
			tablem += "{\n";
			tablem += "    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
			tablem += "}\n";
		}

		if (chirld.type.equals("ImageView")) {

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			setvaluem += "//" + chirld.cnname + "\n";
			setvaluem += "[" + chirld.enname + " setImage:[UIImage imageNamed:@\"1.jpeg\"]]\n";
			setvaluem += "[" + chirld.enname
					+ " setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";

		}
		
		if (chirld.type.equals("ScrollView")) {

		}

		

		if (chirld.type.equals("ExpandableListView")) {

		}

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

}
