package com.compoment.ui.ios.creater;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TableViewControl_Cell_XibCreater {
	public String m = "";

	String tableViewCellName = "EditCell2";
	String tableViewControlName = "Edit";
	String keySectionName = "";
	String keyTableArrayName = "";
	boolean isMutilCell = false;

	String jsonString = "";
	List<ImgBean> controls;

	public static void main(String[] args) throws IOException {

		TableViewControl_Cell_XibCreater mark = new TableViewControl_Cell_XibCreater();

	}

	public TableViewControl_Cell_XibCreater() {
		Gson gson = new Gson();
		init();
		controls = gson.fromJson(jsonString, new TypeToken<List<ImgBean>>() {
		}.getType());
		tableControllerH();
		tableControllerM();
		tableViewCellH(controls, tableViewCellName);
		tableViewCellM(controls, tableViewCellName);
		tableViewCellXib(controls, tableViewCellName);

	}

	public void tableControllerH() {
		String m = "";

		m += "#import <UIKit/UIKit.h>\n";

		m += "@protocol TableReloadDelegate\n";

		m += "- (void)tableDidReload:(CGSize)tableSize;\n";

		m += "@end\n";

		m += "@interface "
				+ tableViewControlName
				+ "ViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>\n\n";

		m += "@property(nonatomic, assign) CGFloat fWidth;\n\n";

		m += "@property(nonatomic, assign) CGFloat fHeight;\n\n";

		m += "@property(nonatomic, weak) id<TableReloadDelegate> tableReloadDelegate;\n\n";

		m += "@property(nonatomic, strong) NSMutableArray *tableDicArray;\n\n";

		m += "@property(nonatomic, assign) UIPopoverController *popVC;\n\n";

		m += "@property(nonatomic, retain) UIView *rootView;\n\n";

		m += "@property(nonatomic, strong) UIView *tableHolderView;\n\n";

		m += "@property(nonatomic, strong) UITableView *tableView;\n";
		
		m += "@property(nonatomic, assign) MBProgressHUD *HUD;\n";

		m += "- (id)initWithNibName:(NSString *)nibNameOrNil  tableReloadDelegate:(id<TableReloadDelegate>)delegate;\n\n";
		m += "@end\n";

		System.out.println(m);
	}

	// table
	public String tableControllerM() {

		String m = "###########################\n";
		m += "#import \"" + tableViewControlName + "ViewController.h\" \n";
		m += "#define keySectionName @\"" + keySectionName + "\"\n";
		m += "#define keyTableArrayName @\"" + keyTableArrayName + "\"\n";
		m += "#define kBXDCRM_IOS7 ([[[UIDevice currentDevice] systemVersion] floatValue] >= 7.0 ? 20. : 0.)\n";

		m += "@implementation " + tableViewControlName + "ViewController\n";

		m += "@synthesize fWidth;\n\n";

		m += "@synthesize fHeight;\n\n";

		m += "@synthesize tableReloadDelegate;\n\n";

		m += "@synthesize tableDicArray;\n\n";

		m += "@synthesize popVC;\n\n";
		
		m += "@synthesize tableView;\n\n";
	
		m += "- (id)initWithNibName:(NSString *)nibNameOrNil  tableReloadDelegate:(id <TableReloadDelegate>)delegate {\n";
		m += "    self = [super initWithNibName:nibNameOrNil bundle:nil];\n";
		m += "    if (self) {\n";
		m += "        self.tableDicArray = [NSMutableArray arrayWithCapacity:0];\n";
		m += "        self.tableReloadDelegate = delegate;\n";
		m += "        [self initDicData];\n";
		m += "    }\n";
		m += "    return self;\n";
		m += "}\n";

		m += "//数据\n";
		m += "- (void)initDicData {\n";
		m+="    [self showLoading];\n";
		m+="    NSMutableArray *mutableArray = [[NSMutableArray alloc] init];\n";
		m+="    \n";
		for(int i=0;i<2;i++)
		{
		m+="    "+tableViewCellName+"Bean *bean = [["+tableViewCellName+"Bean alloc] init];\n";
		m+="    bean.name = @\"1\";\n";
		m+="    [mutableArray addObject:bean];\n";
		}
		m+="    \n";
		m+="    NSMutableDictionary *mutableDictionary =\n";
		m+="    [NSMutableDictionary dictionaryWithCapacity:5];\n";
		m+="    [mutableDictionary setObject:mutableArray forKey:keyTableArrayName];\n";
		m+="    [tableDicArray addObject:mutableDictionary];\n";
		m+="    [self hideLoading];\n";
		m += "}\n";

		m += "//页面\n";
		m += "- (void)viewDidLoad {\n";
		m += "    [super viewDidLoad];\n";

		m+="//背景色 宽 高";
		m += "    self.view.backgroundColor = [UIColor colorWithRed:228.f / 255.f green:228.f / 255.f blue:225.f / 255.f alpha:1.f];\n";
		m += "    CGRect frame = self.view.frame;\n";
		m += "    frame.size.width = fWidth;\n";
		m += "    frame.size.height = fHeight;\n";
		m += "    self.view.frame = frame;\n";

		
		m += " \n";
		m += "    // table\n";
		m += "    [tableView setSeparatorStyle:UITableViewCellSeparatorStyleNone];\n";
		m += "    tableView.backgroundColor = [UIColor whiteColor];\n";
		m += "    tableView.delegate = self;\n";
		m += "    tableView.dataSource = self;\n";
		
		
		m += "    UIView *tableFooterView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(tableView.bounds), 10.f)];\n";
		m += "    [tView setTableFooterView:tableFooterView];\n";
		m += "    \n";
		m += "    [tableHolderView addSubview:tableView];\n";
		
		m += "    [self reloadTableView:YES];\n";
		m += "  \n";
		m += "  \n";
		m += "}\n";

		m += "#pragma mark table view data source\n";
		m += "- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {\n";
		m += "    NSDictionary *dict = [tableDicArray objectAtIndex:(NSUInteger) section];\n";
		m += "    NSMutableArray *beans = [dict objectForKey:keyTableArrayName];\n";
		m += "    return beans.count;\n";
		m += "}\n";

		m += "- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {\n";
		m += "    return tableDicArray.count;\n";
		m += "}\n";

		m += "- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {\n";
		m += "    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(tableHolderView.bounds), 50.f)];\n";
		m += "    view.backgroundColor = [UIColor whiteColor];\n";

		m += "    UILabel *sectionLabel = [[UILabel alloc] initWithFrame:CGRectMake(20, 10, 120, 40)];\n";
		m += "    sectionLabel.textColor = [UIColor blackColor];\n";
		m += "    sectionLabel.font = [UIFont systemFontOfSize:18.];\n";
		m += "    sectionLabel.textAlignment = NSTextAlignmentLeft;\n";
		m += "    sectionLabel.backgroundColor = [UIColor clearColor];\n";
		m += "    sectionLabel.text = [[tableDicArray objectAtIndex:(NSUInteger) section] objectForKey:keySectionName];\n";
		m += "    [view addSubview:sectionLabel];\n";
		m += "    return view;\n";
		m += "}\n";

		m += "- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {\n";

		m += "        NSDictionary *dict = [tableDicArray objectAtIndex:(NSUInteger) section];\n";
		m += "        NSMutableArray *array = [dict objectForKey:keyTableArrayName];\n";
		m += "        if (array == nil || array.count == 0) {\n";
		m += "            return 0.f;\n";
		m += "        } else {\n";
		m += "            return 50.f;\n";
		m += "        }\n";
		m += "    \n";
		m += "}\n";

		m += "- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
		m += "    NSDictionary *dict = [tableDicArray objectAtIndex:(NSUInteger) indexPath.section];\n";
		m += "    NSMutableArray *beans = [dict objectForKey:keyTableArrayName];\n";
		m += "    "
				+ tableViewCellName
				+ "Bean *bean = [beans objectAtIndex:(NSUInteger) indexPath.row];\n";
		m += "    NSString *CellIdentifier = [NSString stringWithFormat:@\"Cell_section_%d_row_%d_t_%f\", indexPath.section, indexPath.row, [NSDate date].timeIntervalSince1970];\n";
		m += "    UITableViewCell *cell = nil;\n";
		if (isMutilCell) {
			m += "//多个类型的cell时 做判断";
			m += "        cell = [self getCell:tableView cellId:CellIdentifier cellValue:bean];\n";
		} else {
			m += "        cell = [self getCell:tableView cellId:CellIdentifier cellValue:bean];\n";
		}

		m += "    return cell;\n";
		m += "}\n";

		
		m += "- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
		m += "    NSDictionary *dict = [tableDicArray objectAtIndex:(NSUInteger) indexPath.section];\n";
		m += "    NSMutableArray *beans = [dict objectForKey:keyTableArrayName];\n";

		m += "    "
				+ tableViewCellName
				+ "Bean *bean = [beans objectAtIndex:(NSUInteger) indexPath.row];\n";

		m += "}\n";


		m += "#pragma mark anniversary cell init\n";
		m += "- (UITableViewCell *)getCell:(UITableView *)tableView cellId:(NSString *)CellIdentifier cellValue:("
				+ tableViewCellName + "Bean *)bean {\n";
		m += "    "
				+ tableViewCellName
				+ " *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];\n";
		m += "    if (cell == nil) {\n";
		m += "     //   cell = [["
				+ tableViewCellName
				+ "alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier withWidth:CGRectGetWidth(tableView.bounds) - 20];\n";

		m += "NSArray *nib =[[NSBundle mainBundle] loadNibNamed:@\""
				+ tableViewCellName + "\" owner:self options:nil];\n";
		m += "        for (id oneObject in nib) {\n";
		m += "            if ([oneObject isKindOfClass:[" + tableViewCellName
				+ " class]]) {\n";
		m += "                cell = oneObject;\n";
		m += "            }\n";
		m += "        }\n";

		m += "    }\n";

		m += "    if (todoModel != nil && todoModel.anniversaryDict != nil) {\n";
		for (ImgBean bean : controls) {
			if (bean.controlType.equals("UITextView")) {
				m += " // cell."
						+ bean.enName
						+ ".tag = ((NSNumber *) [bean objectForKey:@\"ID\"]).intValue;\n";
				m += "[cell." + bean.enName
						+ " setTitle:[bean objectForKey:@\"" + bean.enName
						+ "Value\"] forState:UIControlStateNormal];\n";
			} else if (bean.controlType.equals("UIButton")) {
				m += " [cell."
						+ bean.enName
						+ " addTarget:self action:@selector(popupBeginTimePicker:) forControlEvents:UIControlEventTouchUpInside];\n";
			} else if (bean.controlType.equals("UILabel")) {

				m += "   cell." + bean.enName
						+ ".text = [bean objectForKey:@\"" + bean.enName
						+ "Value\"] ;\n";
			}
		}


		m += "    cell.selectionStyle = UITableViewCellSelectionStyleNone;\n";
		m += "    return cell;\n";
		m += "}\n";
		
		
		m+="//加载数据loading\n";
		m+="- (void)showLoading {\n";
		m+="    \n";
		m+="    UIView *mainView = nil;\n";
		m+="    \n";
		m+="    if ([[[UIApplication sharedApplication] windows] count] > 1) {\n";
		m+="        mainView = [[[UIApplication sharedApplication] windows] objectAtIndex:1];\n";
		m+="    }\n";
		m+="    else {\n";
		m+="        mainView = [[[UIApplication sharedApplication] windows] objectAtIndex:0];\n";
		m+="    }\n";
		m+="    \n";
		m+="    HUD = [[MBProgressHUD alloc] init];\n";
		m+="    [mainView addSubview:HUD];\n";
		m+="    [HUD show:YES];\n";
		m+="}\n";

		m+="- (void)hideLoading\n";
		m+="{\n";
		m+="    if (HUD != nil) {\n";
		m+="        [HUD removeFromSuperview];\n";
		m+="        [HUD release];\n";
		m+="        HUD = nil;\n";
		m+="    }\n";
		m+="}\n";

		
		
		m += "#pragma mark date  popup picker\n";
		m += "- (void)popupDateTimePicker:(id)popupDateTimePickerBtn {\n";
		m += "    UIButton *btn = (UIButton *) popupDateTimePickerBtn;\n";
		m += "    UIViewController *contentViewController = [[UIViewController alloc] init];\n";
		m += "    UIDatePicker *datePicker = [[UIDatePicker alloc] init];\n";
		m += "    NSDate *date = [[NSDate date] dateByAddingTimeInterval:600];\n";

		m += "    datePicker.datePickerMode = UIDatePickerModeDateAndTime;\n";
		m += "    datePicker.locale = [NSLocale currentLocale];\n";
		m += "    datePicker.date = date;\n";
		m += "    datePicker.minimumDate = [NSDate date];\n";
		
		m += "    [datePicker addTarget:self action:@selector(setRemindTime:) forControlEvents:UIControlEventValueChanged];\n";
		m += "    [contentViewController.view addSubview:datePicker];\n";
		m += "    contentViewController.title = @\"提醒时间\";\n";

		m += "    UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:contentViewController];\n";
		m += "    navigationController.contentSizeForViewInPopover = CGSizeMake(340, 270); //内容大小\n";

		m += "    popVC = [[UIPopoverController alloc] initWithContentViewController:navigationController];\n";
		m += "    popVC.popoverContentSize = CGSizeMake(340, 270);\n";

		m += "    CGRect popoverRect = CGRectMake(50, 0, 10, 10);\n";
		m += "    [popVC presentPopoverFromRect:popoverRect\n";
		m += "                           inView:btn\n";
		m += "         permittedArrowDirections:UIPopoverArrowDirectionDown\n";
		m += "                         animated:YES];\n";
		m += "}\n";
		
		
		m += "@end\n";
		System.out.println(m);
		return m;
	}
	
	
	
	
	// table cell h
		public String tableViewCellH(List<ImgBean> controls,
				String tableViewCellName) {
			String m = "########################\n";
			m += "#import <UIKit/UIKit.h>\n";

			m += "#define CellHeight 50.f\n";

			m += "@interface " + tableViewCellName + " : UITableViewCell\n";

			for (ImgBean bean : controls) {
				if(!bean.controlType.equals("UITableViewCell"))
				m += "@property(nonatomic, strong) IBOutlet " + bean.controlType
						+ " *" + firstCharLower(bean.enName) + ";\n";
			}
			m+="- (void)setValuesWithDic:(NSDictionary*)dict;\n";
			m += "@end\n";

			System.out.println(m);
			
			return m;

		}

		
			
		// table cell m
		public String tableViewCellM(List<ImgBean> controls,
				String tableViewCellName) {
			String m = "############################\n\n";

			m += "#import \"" + tableViewCellName + ".h\"\n";

			m += "@implementation " + tableViewCellName + "\n";
			for (ImgBean bean : controls) {
				if(!bean.controlType.equals("UITableViewCell"))
				m += "@synthesize " + firstCharLower(bean.enName) + ";\n";
			}

			m += "- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier  {\n";
			m += "    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];\n";
			m += "    if (self) {\n";
			m += "  }\n";
			m+="  return self;\n";
			m += "}\n";
			
			
			
			m += "- (void)setValueWithDic:(NSDictionary*)dict {\n";
			
			for (ImgBean bean : controls)

			{
				if(!bean.controlType.equals("UITableViewCell"))
				{
				ImgBean parent = bean.controlBelong;
				
					if (bean.controlType.equals("UITextView")) {
						m += "//" + bean.cnName + "\n";
						m+=" "+this.firstCharLower(bean.enName)+".text = [NSString stringWithFormat:@\"%@\",[dict objectForKey:@\""+bean.enName+"Value\"]];\n";
		
					} else if (bean.controlType.equals("UIImageView")) {
						
					} else if (bean.controlType.equals("UIButton")) {
						
					} else if (bean.controlType.equals("UILabel")) {
						m += "//" + bean.cnName + "\n";
						m+=" "+this.firstCharLower(bean.enName)+".text = [NSString stringWithFormat:@\"%@\",[dict objectForKey:@\""+bean.enName+"Value\"]];\n";
						

					} else if (bean.controlType.equals("UIView")) {
					}
				}
			}
			m += "}\n";
			
			
			
			
			m += "- (void)setSelected:(BOOL)selected animated:(BOOL)animated {\n";
			m += "    [super setSelected:selected animated:animated];\n";
			m += "    // Configure the view for the selected state\n";
			m += "}\n";

			m += "@end\n";

			System.out.println(m);

			return m;

		}
		

	

	// table cell xib
	public void tableViewCellXib(List<ImgBean> controls,
			String tableViewCellName) {
		int tableX = 0;
		int tableY = 0;
		int tableH = 0;
		int tableW = 0;

		String m = "#######################\n";
		m += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		m += "<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.iPad.XIB\" version=\"3.0\" toolsVersion=\"5053\" systemVersion=\"13C1021\" targetRuntime=\"iOS.CocoaTouch.iPad\" propertyAccessControl=\"none\" useAutolayout=\"YES\">\n";
		m += "    <dependencies>\n";
		m += "        <deployment defaultVersion=\"1536\" identifier=\"iOS\"/>\n";
		m += "        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"3733\"/>\n";
		m += "    </dependencies>\n";
		m += "    <objects>\n";

		m += "        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\" customClass=\"\">\n";

		m += "        </placeholder>\n";

		m += "        <placeholder placeholderIdentifier=\"IBFirstResponder\" id=\"-2\" customClass=\"UIResponder\"/>\n";

		for (ImgBean bean : controls) {
			if (bean.controlType.equals("UITableViewCell")) {
				tableX = bean.x;
				tableY = bean.y;
				tableW = bean.w;
				tableH = bean.h;

				m += "        <tableViewCell contentMode=\"scaleToFill\" selectionStyle=\"default\" indentationWidth=\"10\" rowHeight=\""
						+ bean.h
						+ "\" id=\""
						+ bean.id
						+ "\" customClass=\""
						+ tableViewCellName + "\">\n";
				m += "            <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\""
						+ bean.w + "\" height=\"" + bean.h + "\"/>\n";
				m += "            <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
				m += "            <tableViewCellContentView key=\"contentView\" opaque=\"NO\" clipsSubviews=\"YES\" multipleTouchEnabled=\"YES\" contentMode=\"center\" tableViewCell=\""
						+ bean.id + "\" id=\"" + this.getId() + "\">\n";
				m += "                <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\""
						+ bean.w + "\" height=\"" + (bean.h - 1) + "\"/>\n";
				m += "                <autoresizingMask key=\"autoresizingMask\"/>\n";
				m += "                <subviews>\n";
			}
		}

		for (ImgBean bean : controls) {
			int viewX = bean.x;
			int viewY = bean.y;
			int viewW = bean.w;
			int viewH = bean.h;

			if (bean.controlType.equals("UIView")) {

				m += "                    <view contentMode=\"scaleToFill\" fixedFrame=\"YES\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
						+ bean.id + "\">\n";
				m += "                        <rect key=\"frame\" x=\""
						+ (viewX - tableX) + "\" y=\"" + (viewY - tableY)
						+ "\" width=\"" + viewW + "\" height=\"" + viewH
						+ "\"/>\n";
				m += "                        <autoresizingMask key=\"autoresizingMask\" widthSizable=\"YES\" heightSizable=\"YES\"/>\n";
				m += "                        <subviews>\n";

				for (ImgBean bean2 : controls) {

					if (bean2.controlBelong != null
							&& bean2.controlBelong.enName.equals(bean.enName)) {

						m += xibSubView(bean2, viewX, viewY, viewW, viewH);
					}

				}
				m += "                        </subviews>\n";
				m += "                        <color key=\"backgroundColor\" white=\"1\" alpha=\"1\" colorSpace=\"custom\" customColorSpace=\"calibratedWhite\"/>\n";
				m += "                    </view>\n";

			} else {
				for (ImgBean bean2 : controls) {
					if (bean2.controlBelong != null
							&& bean2.controlBelong.enName.equals(bean.enName))
						m += xibSubView(bean2, viewX, viewY, viewW, viewH);

				}
			}
		}

		m += "                </subviews>\n";
		m += "            </tableViewCellContentView>\n";

		m += "            <connections>\n";

		for (ImgBean bean : controls) {
			if (!bean.controlType.equals("UITableViewCell"))
				m += "                <outlet property=\""
						+ this.firstCharLower(bean.enName)
						+ "\" destination=\"" + bean.id + "\" id=\""
						+ this.getId() + "\"/>\n";
		}

		m += "            </connections>\n";
		m += "        </tableViewCell>\n";
		m += "    </objects>\n";
		m += "</document>\n";
		System.out.println(m);

	}

	public String xibSubView(ImgBean bean2, int viewX, int viewY, int viewW,
			int viewH) {
		String m = "\n";

		if (bean2.controlType.equals("UILabel")) {
			m += "    <label opaque=\"NO\" clipsSubviews=\"YES\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" text=\""
					+ bean2.enName
					+ " "
					+ bean2.cnName
					+ "\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ bean2.id + "\">\n";
			m += "                                <rect key=\"frame\" x=\""
					+ (bean2.x - viewX) + "\" y=\"" + (bean2.y - viewY)
					+ "\" width=\"" + bean2.w + "\" height=\"" + bean2.h
					+ "\"/>\n";
			m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
			m += "                                <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"17\"/>\n";
			m += "                                <nil key=\"highlightedColor\"/>\n";
			m += "                            </label>\n";
		} else if (bean2.controlType.equals("UITextView")) {
			m += "                            <textField opaque=\"NO\" clipsSubviews=\"YES\" contentMode=\"scaleToFill\" fixedFrame=\"YES\" contentHorizontalAlignment=\"left\" contentVerticalAlignment=\"center\" borderStyle=\"roundedRect\" minimumFontSize=\"17\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ bean2.id + "\">\n";
			m += "                                <rect key=\"frame\" x=\""
					+ (bean2.x - viewX) + "\" y=\"" + (bean2.y - viewY)
					+ "\" width=\"" + bean2.w + "\" height=\"" + bean2.h
					+ "\"/>\n";
			m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
			m += "                                <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"14\"/>\n";
			m += "                                <textInputTraits key=\"textInputTraits\"/>\n";
			m += "                            </textField>\n";

		} else if (bean2.controlType.equals("UIImageView")) {
			m += "      <imageView userInteractionEnabled=\"NO\" contentMode=\"scaleToFill\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ bean2.id + "\">\n";
			m += "                                <rect key=\"frame\" x=\""
					+ (bean2.x - viewX) + "\" y=\"" + (bean2.y - viewY)
					+ "\" width=\"" + bean2.w + "\" height=\"" + bean2.h
					+ "\"/>\n";
			m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
			m += "                            </imageView>\n";

		} else if (bean2.controlType.equals("UIButton")) {

			m += "              <button opaque=\"NO\" contentMode=\"scaleToFill\" fixedFrame=\"YES\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
					+ bean2.id + "\">\n";
			m += "                                <rect key=\"frame\" x=\""
					+ (bean2.x - viewX) + "\" y=\"" + (bean2.y - viewY)
					+ "\" width=\"" + bean2.w + "\" height=\"" + bean2.h
					+ "\"/>\n";
			m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
			m += "                                <state key=\"normal\" title=\""
					+ bean2.cnName + "\">\n";
			m += "                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
			m += "                                </state>\n";
			m += "                            </button>\n";
		}

		return m;
	}



	// 首字母小写
	public static String firstCharLower(String s) {
		// buy_typelist

		s = s.substring(0, 1).toLowerCase() + s.substring(1);

		return s;
	}

	// xib id生成器
	public static String getCharAndNumr(int length) {
		String val = "";

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}

		return val;
	}

	// xib id生成器
	public static String getId() {
		return getCharAndNumr(3) + "-" + getCharAndNumr(2) + "-"
				+ getCharAndNumr(3);

	}

	public void init() {
		FileReader fr = null;
		String classDir = this.getClass().getResource("/").getPath();
		try {
			fr = new FileReader(classDir
					+ "com/compoment/ui/ios/creater/MarkBefor.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(fr);
			String myreadline;

			while (br.ready()) {
				ImgBean imgBean = new ImgBean();
				myreadline = br.readLine();

				jsonString += myreadline;

			}

			br.close();
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}