package com.compoment.ui.ios.creater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class ViewControllerH {



		String m = "\n\n\n";
	
	    String propertyValue="";
		String initValue="";
	    String pageName="";
	    String className="";
	    boolean isChirldViewNotParentView=false;
		public  ViewControllerH(String pageName,List<CompomentBean> oldBeans,boolean isChirldViewNotParentView) {
            this.pageName=pageName;
            this.isChirldViewNotParentView=isChirldViewNotParentView;
            className=firstCharToUpperAndJavaName(pageName);
			analyseRelativeForVertical(oldBeans);


		
			System.out.println(m);
			
		

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
			if(this.isChirldViewNotParentView)
			{
			m+="@protocol "+className+"ChirldViewCallBackDelegate;\n";
			}
			m+="@interface "+className+"ViewController : UIViewController<UITableViewDataSource,UITableViewDelegate>\n";
			
			
			
		
			
			parent(maxBean);
			m+="\n{\n";
			m+=initValue;
			if(this.isChirldViewNotParentView)
			{
			m+="    NSMutableArray *chirldViewData;\n";
			}
			m+="\n}\n";
			m+=propertyValue;
			
			if(this.isChirldViewNotParentView)
			{
			m+="@property (strong,nonatomic) id<"+className+"ChirldViewCallBackDelegate> chirldViewCallBackDelegate;\n";
			m+="-(void) setChirldViewValue:(NSMutableArray*)mdata  delegate:(id<"+className+"ChirldViewCallBackDelegate>)parent;\n";
			}
			
			m+="@end\n";
			
			
			if(this.isChirldViewNotParentView)
			{
	        m+="@protocol "+className+"ChirldViewCallBackDelegate <NSObject>\n";
			
			m+="-(void) chirldViewCallBack:(NSMutableArray*)mdata;\n";

			m+="@end\n\n";
			
			m+="//父亲ViewController实现接口  "+className+"ChirldViewCallBackDelegate>\n";
			m+="//1. "+className+"ChirldViewCallBackDelegate\n";
			m+="//-(void) chirldViewCallBack:(NSString*)mtype  data:(NSMutableArray*)mdata;\n";
			
			m+="//2.在viewDidLoad中\n";
			m+="//chirldViewController=[["+className+"ViewController alloc ] initWithNibName:@\""+className+"ViewController\" bundle:nil];\n";
		    m+="//chirldViewController.view.frame=CGRectMake(,,,);\n";
		    m+="//[ self.view addSubview:chirldViewController.view];\n";
			}
			
			FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", className+"ViewController",
					"h", m);
      
		}

		public void parent(CompomentBean bean) {

			if (bean.chirlds != null && bean.chirlds.size() > 0) {
				
				if (bean.type.equals("ScrollViewLayout")) {

				     
					propertyValue+="@property (weak, nonatomic) IBOutlet UIScrollView *"+bean.enname.replace("Layout", "")+";\n";
					
					
					
	               
				}else
				{
				for (CompomentBean chirld : bean.chirlds) {

					if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

						parent(chirld);
					} else {
						chirld(chirld, bean);
						
					}
				}
				}

			}

		}

		public void chirld(CompomentBean chirld, CompomentBean parent) {

			if (chirld.type.equals("TextView")) {
				propertyValue+="//"+chirld.cnname+"\n";
				propertyValue+="@property (weak, nonatomic) IBOutlet UILabel *"+chirld.enname+";\n";
		
			
			}

			if (chirld.type.equals("Button")) {
				propertyValue+="//"+chirld.cnname+"\n";
				propertyValue+="@property (weak, nonatomic) IBOutlet UIButton *"+chirld.enname+";\n";
				
			
			}

			if (chirld.type.equals("EditText")) {
				propertyValue+="//"+chirld.cnname+"\n";
				propertyValue+="@property (weak, nonatomic) IBOutlet UITextField *"+chirld.enname+";\n";
				
				
	
			}

			if (chirld.type.equals("CheckBox")) {
			
				propertyValue+="//"+chirld.cnname+"\n";
				propertyValue+="@property (weak, nonatomic) IBOutlet UIButton *"+chirld.enname+";\n";
			}

			if (chirld.type.equals("ListView")) {
				propertyValue+="//"+chirld.cnname+"\n";
				propertyValue+="@property (weak, nonatomic) IBOutlet UITableView *tableView;\n";
				propertyValue+="@property (strong, nonatomic) NSMutableDictionary *cacheCells;\n";
			
				initValue+="int page;\n";
				initValue+="int totalRowCount;\n";
				initValue+="int currentRowCount;\n";
				initValue+="bool requestUnComplete;//发完一个请求再发下一个\n";
				initValue+="NSMutableArray *allIndexpaths;\n";
				initValue+="NSMutableArray *rows;\n";
				
				if(m.contains("@interface ViewController : UIViewController\n"))
				{
					m=m.replace("@interface ViewController : UIViewController\n", "@interface ViewController : UIViewController<UITableViewDataSource,UITableViewDelegate>\n");
				}
			}

			if (chirld.type.equals("ImageView")) {
				propertyValue+="//"+chirld.cnname+"\n";
				propertyValue+="@property (weak, nonatomic) IBOutlet UIImageView *"+chirld.enname+";\n";
			
			
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
