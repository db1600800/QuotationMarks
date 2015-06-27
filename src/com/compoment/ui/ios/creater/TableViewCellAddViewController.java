package com.compoment.ui.ios.creater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.addfunction.android.FileBean;
import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;
import com.compoment.util.RegexUtil.ControllerBean;


/***
 * TableViewCell
 * */
public class TableViewCellAddViewController {


	


	String waitByModifyFileName;
    String className="";

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

    String pageName="";
  

        
	public TableViewCellAddViewController(String pageName,List<CompomentBean> oldBeans,String waitByModifyFileName) {
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
			
			
			 if(line.contains("@implementation"))
			 {
				 String m="";
				     m+="#import \""+className+"TableViewCell.h\"\n";
				     m+="//注入table功能\n";
					 m+=" NSString *CellIdentifier = @\""+className+"TableViewCell\";\n";	
			    	content += m;
			 }
		
			content += line + "\n";
			
			 if(line.contains("@implementation"))
			 {
					String m="@synthesize cacheCells;\n";	
			    	content += m;
			 }
			
			
		 if(line.contains("[super viewDidLoad]"))
					
				{
					String m="//table\n";
					m+="    [tableView setDelegate:self];//指定委托\n";
					m+="    [tableView setDataSource:self];//指定数据委托\n";
					m+="     cacheCells = [NSMutableDictionary dictionary];\n";
					m+="    \n";
					m+="    //使用自定义的Cell,需要向UITableView进行注册\n";
					m+="    UINib *cellNib = [UINib nibWithNibName:@\""+className+"TableViewCell\" bundle:nil];\n";
					m+="    [tableView registerNib:cellNib forCellReuseIdentifier:CellIdentifier];\n";
			    	content += m;
				}
			 else if(line.contains("viewWillAppear:(BOOL)animated"))		
				{
					String m="//table\n";
					m+="[self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
			    	content += m;
				}
			 else if(line.contains("cellForRowAtIndexPath"))		
				{
					String m="\n";
					m+=" "+className+"TableViewCell *cell = ("+className+"TableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];\n";
					m+="    if (!cell)\n";
					m+="    {\n";
					m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCell\" owner:self options:nil] lastObject];\n";
					m+="    }\n";
					m+=n;
					m+="return cell;\n";
			    	content += m;
				}
			 else if(line.contains("heightForRowAtIndexPath"))	
			 {
					String m="\n";
					m+="NSString *reuseIdentifier = CellIdentifier;\n";
				    m+=""+className+"TableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];\n";
				    m+="if (!cell) {\n";
				    m+="  cell=[self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];\n";
				    m+="  [self.cacheCells setObject:cell forKey:reuseIdentifier];\n";
				    m+="}\n\n";

				    m+=n;
				    
				    m+="\n// CGSize size = [cell.contentView systemLayoutSizeFittingSize:UILayoutFittingCompressedSize];//autolayout有效 配合上边使用\n";
				    m+="   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   \n";
				    m+="return height+1;\n";
			    	content += m;
			 }
		 
			 

		}

	 String filename=FileUtil.makeFile(waitByModifyFileName, content);
	}


	String n="";
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
					chirld(chirld, bean);
					
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {

		if (chirld.type.equals("TextView")) {
			n+="//"+chirld.cnname+"\n";
			n+="cell."+chirld.enname+".text= ((..*)[listData objectAtIndex:indexPath.row])."+chirld.enname+";\n";
	
		
		}

		if (chirld.type.equals("Button")) {
			n+="//"+chirld.cnname+"\n";
			n+="[cell."+chirld.enname+" setImage:[UIImage imageNamed:@\""+chirld.picName+"\"] forState:UIControlStateNormal];\n";
		    n+="[cell."+chirld.enname+" setImage:[UIImage imageNamed:@\"press"+chirld.picName+"\"] forState:UIControlStateSelected];\n";
			n+="cell."+chirld.enname+".tag =  (indexPath.section)*1000+indexPath.row;\n";
			n+="[cell."+chirld.enname+" addTarget:self action:@selector("+chirld.enname+"clicked:) forControlEvents:UIControlEventTouchUpInside];\n";
			n+="-(void)"+chirld.enname+"clicked:(UIButton *)btn{\n";
			n+="    int tab=btn.tag;\n";
			n+="    int row= btn.tag%1000;\n";
			 n+="   int section=btn.tag/1000;\n";
			 n+="  //btn.selected = !btn.selected;\n//用于butoon做checkBox控件";
			n+="}\n";
			
		
		}

		if (chirld.type.equals("EditText")) {
			n+="//"+chirld.cnname+"\n";
			
			n+="[cell."+chirld.enname+" addTarget:self action:@selector("+chirld.enname+"EditingChanged:) forControlEvents:UIControlEventEditingChanged];\n";
				n+="-(void)"+chirld.enname+"EditingChanged:(UITextField *)textField{\n";
				n+="UITextRange * selectedRange = [textField.markedTextRange];\n";
				n+="if(selectedRange == nil || selectedRange.empty){\n";
				     // 这里取到textfielf.text 进行检索
				n+="}\n"; 
				n+="}\n";
				
				n+="[cell."+chirld.enname+" addTarget:self action:@selector("+chirld.enname+"DidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];\n";
				n+="-(void)"+chirld.enname+"DidEndOnExit:(UITextField *)textField{\n";
				n+=" [...other控件 becomeFirstResponder];//把焦点给别人 键盘消失\n";
				n+="}\n";

		}

		if (chirld.type.equals("CheckBox")) {
		

		}

		if (chirld.type.equals("ListView")) {
			
		}

		if (chirld.type.equals("ImageView")) {
			n+="//"+chirld.cnname+"\n";
			n+="[cell."+chirld.enname+" setImageWithURL:[NSURL URLWithString:((..*)[listData objectAtIndex:indexPath.row])."+chirld.enname+" placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";
		
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}

	
  
}
