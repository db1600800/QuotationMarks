package com.compoment.ui.ios.creater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.compoment.cut.CompomentBean;

public class ViewController {



		String m = "\n\n\n";
		String i="\n\n\n";
	    String pageName="";
	    
		public  ViewController(List<CompomentBean> oldBeans) {

			analyseRelativeForVertical(oldBeans);


		
			System.out.println(m);
			
			System.out.println(i);

		}

		

		public void analyseRelativeForVertical(List<CompomentBean> oldBeans) {
		
			m += "//ios界面 object-c \n";
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
			
			
			m+="#import <UIKit/UIKit.h>\n";
			m+="@interface ViewController : UIViewController\n";

			i+="@implementation ViewController\n";
			
		
			
			parent(maxBean);
			
			m+="@end\n";
            i+="\n@end\n";
		}

		public void parent(CompomentBean bean) {

			if (bean.chirlds != null && bean.chirlds.size() > 0) {
				for (CompomentBean chirld : bean.chirlds) {

					if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

						parent(chirld);
					} else {
						chirld(chirld, bean);
						
					}
				}

			}

		}

		public void chirld(CompomentBean chirld, CompomentBean parent) {

			if (chirld.type.equals("TextView")) {
				m+="//"+chirld.cnname+"\n";
				m+="@property (weak, nonatomic) IBOutlet UILabel *"+chirld.enname+";\n";
		
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("Button")) {
				m+="//"+chirld.cnname+"\n";
				m+="@property (weak, nonatomic) IBOutlet UIButton *"+chirld.enname+";\n";
				
			
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("EditText")) {
				m+="//"+chirld.cnname+"\n";
				m+="@property (weak, nonatomic) IBOutlet UITextField *"+chirld.enname+";\n";
				
				
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("CheckBox")) {
//			

			}

			if (chirld.type.equals("ListView")) {

			}

			if (chirld.type.equals("ImageView")) {
				m+="//"+chirld.cnname+"\n";
				m+="@property (weak, nonatomic) IBOutlet UIImageView *"+chirld.enname+";\n";
			
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("ExpandableListView")) {

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
