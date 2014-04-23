package com.compoment.db.ios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.creater.first.QuotationMarks;
import com.compoment.db.ios.CoredataDbDao.PropertyBean;
import com.compoment.gbkToUtf8.creater.GbkToUtf_FileOrDir;

public class CoredataDbSynchronize {
	List propertys = new ArrayList();
	String className = null;

	public static void main(String[] args) throws IOException {

		CoredataDbSynchronize mark = new CoredataDbSynchronize();
		mark.init(mark);
		 mark.hfile();
		//mark.mfile();

	}

	public void init(CoredataDbSynchronize mark) {

		try {
			String classDir = mark.getClass().getResource("/").getPath();
			FileReader fr = new FileReader(classDir
					+ "com/compoment/db/ios/MarkBefor.txt");
			BufferedReader br = new BufferedReader(fr);

			String myreadline;

			while (br.ready()) {
				myreadline = br.readLine();

				if (myreadline.contains("@interface")) {
					int start = myreadline.indexOf("@");
					start = start + 10;
					int end = myreadline.indexOf(":");
					className = myreadline.substring(start, end).trim();
				} else if (myreadline.contains("@property")) {
					int start1 = myreadline.indexOf(")");
					int start2 = myreadline.indexOf("*");
					int end = myreadline.indexOf(";");

					String type = myreadline.substring(start1 + 1, start2)
							.trim();
					String name = myreadline.substring(start2 + 1, end).trim();
					PropertyBean bean = mark.new PropertyBean();
					bean.type = type;
					bean.name = name;
					propertys.add(bean);
				}

			}

			br.close();
			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String firstCharToUpper(String string) {
		// buy_typelist

		string = string.substring(0, 1).toUpperCase() + string.substring(1);

		return string;
	}

	public class PropertyBean {

		public String type;// 类型
		public String name;// 属性名
	}

	public void hfile() {

		String m = "";

		m+="#import <Foundation/Foundation.h>\n";
		m+="#import \"CRM_EventCategory.h\"\n";
		m+="#import \"CRM_Customer.h\"\n";

		m+="@interface "+className+"Synchronize : NSObject \n";
	
		m+="- (void)sync"+className+"With";
		int k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada"))
			{
				continue;
			}
			if (k == 0) {
				m += firstCharToUpper(property.name) + ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			k++;
		}
	
		m+=";\n\n";
		
		
		

		m+="- (void)sync"+className+"DicArray:(NSArray *)"+className.toLowerCase()+"DicArray;\n";

		m+="- (NSArray *)unSync"+className+"Array;\n";

		m+="- (NSArray *)unSync"+className+"DicArray;\n";

		m+="@end\n";


		System.out.println(m);
	}

	public void mfile() {
		String m = "";
			
		m+="#import \""+className+"Synchronize.h\"\n";
		m+="#import \""+className+"Mamager.h\"\n";
		m+="#import \"CRM.h\"\n";
		m+="#import \""+className+"CategoryManager.h\"\n";

		m+="@implementation "+className+"Synchronize\n";

		m+="// 服务端数据同步到本地"+className+"\n";
		m+="- (void)sync"+className+"With";
		int k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada"))
			{
				continue;
			}
			if (k == 0) {
				m += firstCharToUpper(property.name) + ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			k++;
		}
		m+=" {\n";
		m+="    \n";
		m+="    "+className+"Mamager *"+className.toLowerCase()+"Mamager = ["+className+"Mamager sharedInstance];\n";
		m+="    NSArray *"+className.toLowerCase()+"s = ["+className.toLowerCase()+"Mamager findWithTerminalID:terminalID];\n";
		m+="    \n";
		m+="    if ("+className.toLowerCase()+"s != nil && ["+className.toLowerCase()+"s count] > 0) {\n";
		m+="        //本地有记录\n";
		m+="        "+className+" *"+className.toLowerCase()+" = ["+className.toLowerCase()+"s objectAtIndex:0];\n";
		m+="        if ([status isEqualToNumber:@(CRM_ENTITY_NORMAL)]) {\n";
		m+="            if (["+className.toLowerCase()+".status isEqualToNumber:@(CRM_ENTITY_DELETED)]) {\n";
		m+="                return;\n";
		m+="            }\n";
		m+="        }\n";
		m+="        \n";
		m+="        ["+className.toLowerCase()+"Mamager updateWith"+className+":"+className.toLowerCase()+"\n";
		
	
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada"))
			{
				continue;
			}
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			
		}
		m+="                   ];\n";
		m+="        \n";
		m+="    } else {\n";
		m+="        \n";
		m+="        [eventMamager createWith\n";
		
	     k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada"))
			{
				continue;
			}
			if (k == 0) {
				m += firstCharToUpper(property.name) + ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			k++;
		}
		
		m+=" ];\n";
		m+="    }\n";
		m+="    return;\n";
		m+="}\n\n";
		
		

		m+="- (void)sync"+className+"DicArray:(NSArray *)"+className.toLowerCase()+"DicArray {\n";
		m+="    \n";
		m+="    for (NSDictionary *item in "+className.toLowerCase()+"DicArray) {\n";
		m+="        NSString *serverID = [item objectForKey:@\"serverID\"];\n";
		m+="        NSString *terminalID = [item objectForKey:@\"terminalID\"];\n";
		m+="        NSNumber *status = [item objectForKey:@\"status\"];\n";
		m+="        \n";
		m+="        NSString *content = [item objectForKey:@\"content\"];\n";
		m+="        \n";
		m+="        CRM_EventCategoryManager *eventCategoryManager =\n";
		m+="        [CRM_EventCategoryManager sharedInstance];\n";
		m+="        CRM_EventCategory *eventCategory =\n";
		m+="        [eventCategoryManager findWithTerminalID:terminalID];\n";
		m+="        \n";
		m+="        [slef syncCRM_Event:serverID\n";
		m+="                 terminalID:terminalID\n";
		m+="                     status:status\n";
		m+="                    content:content\n";
		m+="                   category:eventCategory\n";
		m+="                   customer:nil];\n";
		m+="    }\n";
		m+="}\n";

		m+="//查询待同步（CRM_Event新增或修改的记录）\n";
		m+="- (NSArray *)unSyncCRM_EventArray {\n";
		m+="    CRM_EventMamager *eventMamager = [CRM_EventMamager sharedInstance];\n";
		m+="    \n";
		m+="    NSPredicate *filter =\n";
		m+="    [NSPredicate predicateWithFormat:@\"status != %@\", @(CRM_ENTITY_NORMAL)];\n";
		m+="    NSArray *result = [[eventMamager findAll] filteredArrayUsingPredicate:filter];\n";
		m+="    \n";
		m+="    return result;\n";
		m+="}\n";

		m+="- (NSArray *)unSyncCRM_EventDicArray {\n";
		m+="    \n";
		m+="    // BeanArray 转成 DicArray\n";
		m+="    NSArray *crm_eventArray = [self unSyncCRM_EventArray];\n";
		m+="    \n";
		m+="    //参数\n";
		m+="    // CRM_Event\n";
		m+="    NSMutableArray *crm_eventDicArray = [[NSMutableArray alloc] init];\n";
		m+="    for (CRM_Event *event in crm_eventArray) {\n";
		m+="        \n";
		m+="        NSMutableDictionary *dic = [NSMutableDictionary dictionary];\n";
		m+="        \n";
		m+="        // [dicGroup setObject:group.ServerId forKey:@\"serverId\"];\n";
		m+="        //[dicGroup setObject:group.GroupName forKey:@\"groupName\"];\n";
		m+="        // [dicGroup setObject:[NSString stringWithFormat:@\"%d\", group.SortCode]\n";
		m+="        //          forKey:@\"sortCode\"];\n";
		m+="        // long\n";
		m+="        //        [dicGroup\n";
		m+="        //         setObject:[NSNumber numberWithLongLong:[group.UpdateTime\n";
		m+="        // longLongValue]]\n";
		m+="        //         forKey:@\"updateTime\"];\n";
		m+="        \n";
		m+="        [crm_eventDicArray addObject:dic];\n";
		m+="    }\n";
		m+="}\n";


		m+="@end\n";


		System.out.println(m);

	}
}
