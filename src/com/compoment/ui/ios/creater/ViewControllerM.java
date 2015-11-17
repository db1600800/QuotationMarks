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
	   boolean isChirldViewNotParentView=false;
	CompomentDeclareImplement compomentDeclareImplement=new CompomentDeclareImplement();
	
	public ViewControllerM(String pageName, List<CompomentBean> oldBeans,   boolean isChirldViewNotParentView) {
		this.pageName = pageName;
		this.isChirldViewNotParentView=isChirldViewNotParentView;
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

		
		if(isChirldViewNotParentView)
		{
		i+="-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<"+className+"ChirldViewCallBackDelegate>)parent{\n";
	
		 i+="   self.chirldViewCallBackDelegate=parent;\n";
		 i+="   chirldViewData=mdata;\n";
		
		i+="}\n";
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
					
					if(closeKeyboardDeclare==null ||closeKeyboardDeclare.equals(""))
					{
						
					closeKeyboardDeclare+=compomentDeclareImplement.closeKeyboardDeclare;
					closeKeyboardImplement+=compomentDeclareImplement.closeKeyboardImplement;
					}
					
					tablem+=compomentDeclareImplement.tablem;
					i+=compomentDeclareImplement.i;
					setvaluem+=compomentDeclareImplement.setvaluem;
				
					
					
				}
			}
			}

		}

	}



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
