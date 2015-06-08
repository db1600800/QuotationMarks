package com.compoment.ui.ios.creater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.compoment.cut.CompomentBean;

public class ViewControllerM {



	
		String i="\n\n\n";
	    String pageName="";
	    
		public  ViewControllerM(String pageName,List<CompomentBean> oldBeans) {
            this.pageName=pageName;
			analyseRelativeForVertical(oldBeans);


	
			
			System.out.println(i);

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
			
	

			i+="@implementation ViewController\n";
			
		
			
			parent(maxBean);
			
		
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
		
		
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("Button")) {

				
			
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("EditText")) {
		
				
				
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("CheckBox")) {
//			

			}

			if (chirld.type.equals("ListView")) {
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("ImageView")) {
		
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
