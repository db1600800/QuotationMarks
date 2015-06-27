package com.compoment.ui.ios.creater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class ViewControllerM {



	
		String i="\n\n\n";
		String tablem="\n";
		String setvaluem="\n";
	    String pageName="";
	    String className="";
		public  ViewControllerM(String pageName,List<CompomentBean> oldBeans) {
            this.pageName=pageName;
            className=firstCharToUpperAndJavaName(pageName);
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
			
	
            i+="#import \""+className+"ViewController.h\"\n";
            i+="#import \"UIImageView+WebCache.h\"\n";
            i+="#import <Foundation/Foundation.h>\n";
            i+="#import <PublicFramework/JSONKit.h>\n";
			i+="@implementation "+className+"ViewController\n";
			
		
			
			parent(maxBean);
			
		
			i+="- (void)viewDidLoad\n";
			i+="{\n";
			i+="    [super viewDidLoad];\n";

			i+="  UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(handTap)];\n";
			i+="    [self.modifyPwdTextView addGestureRecognizer:tap];\n";

			
			i+="}\n\n";

            i+="-(void)handTap{\n  "
            		+ "  [self presentViewController:updatePwdViewController animated:NO completion:^{}];\n"
            		+ "[self dismissViewControllerAnimated:NO completion:^(){}]; \n};\n";

			i+="-(void) viewWillAppear:(BOOL)animated{\n";
		
			
			i+="}\n\n";
			
			i+=tablem;
			
			i+="-(void) setUiValue{\n";
			i+=setvaluem;
			i+="}\n\n";
            i+="\n@end\n";
            
            FileUtil.makeFile(KeyValue.readCache("picPath"), "src/ios", className+"ViewController",
					"m", i);
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
				
				setvaluem+="//"+chirld.cnname+"\n";
				setvaluem+="["+chirld.enname+" setValue:]\n";
			}

			if (chirld.type.equals("Button")) {

				
			
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
			}

			if (chirld.type.equals("EditText")) {
		
				
				
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
				
				setvaluem+="//"+chirld.cnname+"\n";
				setvaluem+="["+chirld.enname+" setValue:]\n";
			}

			if (chirld.type.equals("CheckBox")) {
			

			}

			if (chirld.type.equals("ListView")) {
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize tableView;\n";
				

				tablem+="- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{\n";
				tablem+="    return @\"\";\n";
				tablem+="  \n";
				tablem+="}\n\n";

				tablem+="//自定义SectionHeader\n";
				tablem+="- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{\n";
			
				tablem+="    return nil;\n";
				tablem+="}\n\n";

				tablem+="//自定义SectionHeader高度\n";
				tablem+="-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{\n";
				tablem+="    return 22.0;\n";
				tablem+="}\n\n";


				tablem+="//指定有多少个分区(Section)，默认为1\n";
				tablem+="- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {\n";
				tablem+="    \n";
				tablem+="    return 1;//返回标题数组中元素的个数来确定分区的个数\n";
				tablem+="}\n\n";


				tablem+="//指定每个分区中有多少行，默认为1\n";
				tablem+="- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{\n";
				tablem+="    \n";
		
				tablem+="     return  [listData count];;\n";
				tablem+="    \n";
				tablem+="}\n\n";



				tablem+="//绘制Cell\n";
				tablem+="-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
				tablem+="    \n";

				tablem+="}\n\n";



				tablem+="//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度\n";
				tablem+="- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{\n";
			
			

				tablem+="}\n\n";


				tablem+="- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
				tablem+="    return 88;\n";
				tablem+="}\n\n";


				tablem+="//点击后，过段时间cell自动取消选中\n";
				tablem+="- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{\n";
				tablem+="    //消除cell选择痕迹\n";
				tablem+="    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];\n";
				tablem+="}\n";
				tablem+="- (void)deselect\n";
				tablem+="{\n";
				tablem+="    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
				tablem+="}\n";
			}

			if (chirld.type.equals("ImageView")) {
		
				i+="//"+chirld.cnname+"\n";
				i+="@synthesize "+chirld.enname+";\n";
				
				setvaluem+="//"+chirld.cnname+"\n";
				setvaluem+="["+chirld.enname+" setImage:[UIImage imageNamed:@\"1.jpeg\"]]\n";
				setvaluem+="["+chirld.enname+" setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";
				
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
