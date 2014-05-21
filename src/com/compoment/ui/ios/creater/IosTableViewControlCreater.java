package com.compoment.ui.ios.creater;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;






import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class IosTableViewControlCreater {
	public String m = "";

	String tableViewCellName = "EditCell";
    String tableViewControlName="Edit";
    String keySectionName="";
    String keyTableArrayName="";
    boolean isMutilCell=false;
    
    String jsonString="";
    List<ImgBean> controls;

	public static void main(String[] args) throws IOException {

		IosTableViewControlCreater mark = new IosTableViewControlCreater();
		
	

	}
	public IosTableViewControlCreater() {
		 Gson gson = new Gson();  
		init();
		controls= gson.fromJson(jsonString,  
	                new TypeToken<List<ImgBean>>() {  
	                }.getType());  
		
		tableViewCellH(controls, tableViewCellName);
		tableViewCellM(controls, tableViewCellName);
		tableViewCellXib(controls, tableViewCellName);

	}
	

	
	

	public void tableControllerH()
	{
		String m="";
		
		m += "#import <UIKit/UIKit.h>\n";

		m += "@protocol TableReloadDelegate\n";

		m += "- (void)tableDidReload:(CGSize)tableSize;\n";

		m += "@end\n";

		m += "@interface "+tableViewControlName+"ViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>\n";

		m += "@property(nonatomic, assign) CGFloat fWidth;\n";
		
		m += "@property(nonatomic, assign) CGFloat fHeight;\n";
		
		m += "@property(nonatomic, weak) id<TableReloadDelegate> tableReloadDelegate;\n";
		
		m += "@property(nonatomic, strong) NSMutableArray *tableDicArray;\n";
		
		m += "@property(nonatomic, assign) UIPopoverController *popVC;\n";
		
		m += "@property(nonatomic, assign) UIView *rootView;\n";
		
		m += "@property(nonatomic, strong) UIView *tableHolderView;\n";
		
		m += "@property(nonatomic, strong) UITableView *tableView;\n";
		
		m += "- (id)initWithNibName:(NSString *)nibNameOrNil  tableReloadDelegate:(id<TodoTableReloadDelegate>)delegate;\n";
		m += "@end\n";
	}
	
	// table
	public String tableControllerM() {

		String m = "";
		m+="#define keySectionName @\""+keySectionName+"\"";
		m+="#define keyTableArrayName @\""+keyTableArrayName+"\"";
		
		m+="@implementation "+tableViewControlName+"ViewController";
		
		
		m += "- (id)initWithNibName:(NSString *)nibNameOrNil  tableReloadDelegate:(id <TableReloadDelegate>)delegate {\n";
		m += "    self = [super initWithNibName:nibNameOrNil bundle:nil];\n";
		m += "    if (self) {\n";
		m += "        self.tableDicArray = [NSMutableArray arrayWithCapacity:0];\n";
		m += "        self.tableReloadDelegate = delegate;\n";
		m += "        [self initData];\n";
		m += "    }\n";
		m += "    return self;\n";
		m += "}\n";

		m += "//数据\n";
		m += "- (void)initDicData {\n";
		m += "   //NSDictionary *dict = [NSDictionary dictionaryWithObjectsAndKeys:sectionName, kSectionName, wrapperTodos, kTodoArray, nil];\n";
		m += "   //[tablDicArray addObject:dictDone];\n";
		m += "}\n";

		m += "//页面\n";
		m += "- (void)viewDidLoad {\n";
		m += "    [super viewDidLoad];\n";

		m += "    self.view.backgroundColor = [UIColor colorWithRed:228.f / 255.f green:228.f / 255.f blue:225.f / 255.f alpha:1.f];\n";
		m += "    CGRect frame = self.view.frame;\n";
		m += "    frame.size.width = _fWidth;\n";
		m += "    frame.size.height = _fHeight;\n";
		m += "    self.view.frame = frame;\n";

		m += " \n";
		m += "    //可滚动页面 scrollView = [[TPKeyboardAvoidingScrollView alloc] initWithFrame:self.view.frame];\n";
		m += "    //不可滚动页面\n";
		m += "    rootView = [[UIView alloc] initWithFrame:self.view.frame];\n";
		m += "    [self.view addSubview:rootView];\n";
		m += "    \n";
		m += "    float tableViewPadding = 20.f;\n";
		m += "    CGFloat extraHeight = 0;\n";
		m += "    tableHolderView = [[UIView alloc] initWithFrame:\n";
		m += "            CGRectMake(tableViewPadding,\n";
		m += "                    CGRectGetHeight(self.view.bounds) + tableViewPadding,\n";
		m += "                    CGRectGetWidth(self.view.bounds) - tableViewPadding * 2.,\n";
		m += "                    CGRectGetHeight(self.view.bounds)  - tableViewPadding * 2 - extraHeight)];\n";
		m += "    tableHolderView.backgroundColor = [UIColor whiteColor];\n";

		m += "    [rootView addSubview:tableHolderView];\n";
		m += " \n";
		m += " \n";
		m += "    // table\n";
		m += "   // float tableViewHeaderHeight = CGRectGetHeight(_tableHeaderView.bounds);\n";
		m += "    UITableView *tView = [[UITableView alloc] initWithFrame:CGRectMake(\n";
		m += "            0,\n";
		m += "            tableViewHeaderHeight,\n";
		m += "            CGRectGetWidth(_tableHolderView.bounds),\n";
		m += "            CGRectGetHeight(_tableHolderView.bounds) - tableViewHeaderHeight)\n";
		m += "                                                      style:UITableViewStylePlain];\n";
		m += "    tableView = tView;\n";
		m += "    [tView setSeparatorStyle:UITableViewCellSeparatorStyleNone];\n";
		m += "    tView.backgroundColor = [UIColor whiteColor];\n";
		m += "    tView.delegate = self;\n";
		m += "    tView.dataSource = self;\n";
		m += "    UIView *tableFooterView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(tableView.bounds), 10.f)];\n";
		m += "    [tView setTableFooterView:tableFooterView];\n";
		m += "    \n";
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
		m += "    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(_tableHolderView.bounds), 50.f)];\n";
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
		m += "    NSMutableArray *beans = [dict objectForKey:kTableArrayName];\n";

		m += "    "+tableViewCellName+"Bean *bean = [beans objectAtIndex:(NSUInteger) indexPath.row];\n";
		m += "    NSString *CellIdentifier = [NSString stringWithFormat:@\"Cell_section_%d_row_%d_t_%f\", indexPath.section, indexPath.row, [NSDate date].timeIntervalSince1970];\n";
		m += "    UITableViewCell *cell = nil;\n";
		if(isMutilCell)
		{
			m+="//多个类型的cell时 做判断";
			m += "        cell = [self getCell:tableView cellId:CellIdentifier cellValue:bean];\n";
		}else
		{
			m += "        cell = [self getCell:tableView cellId:CellIdentifier cellValue:bean];\n";
		}
		
		m += "    return cell;\n";
		m += "}\n";
		
		

		m += "- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
		m += "    NSDictionary *dict = [tableDicArray objectAtIndex:(NSUInteger) indexPath.section];\n";
		m += "    NSMutableArray *beans = [dict objectForKey:kTableArrayName];\n";

		m += "    "+tableViewCellName+"Bean *bean = [beans objectAtIndex:(NSUInteger) indexPath.row];\n";

		m+="//计算每行文字的高度 然后加起来";
		m += " //       NSString *peopleIds = bean.eventCustomersRelated;\n";
		m += " //       NSArray *array = [peopleIds componentsSeparatedByString:@\",\"];\n";
		m += " //       int extraLines = 0;\n";
		m += " //       if (array.count > 1) {\n";
		m += " //           extraLines = array.count - 1;\n";
		m += " //       }\n";

		m += " //       CGSize todoDescLabelSize = [self calContentTextHeight:bean.eventDesc width:(CGRectGetWidth(tableView.bounds) - 20 - 60 - 100) font:[UIFont systemFontOfSize:16.]];\n";
		m += " //       CGSize todoMemoLabelSize = [self calContentTextHeight:bean.eventMemo width:(CGRectGetWidth(tableView.bounds) - 20 - 60 - 100) font:[UIFont systemFontOfSize:16.]];\n";
		m += " //       return 50.f * 5 + fmaxf(50.f, todoDescLabelSize.height + 20) + fmaxf(100.f, todoMemoLabelSize.height + 35) + 50 * extraLines;\n";
	
		m += "}\n";
		
		
		m+="//计算文字大小";
		m+="- (CGSize)calContentTextHeight:(NSString *)text\n";
		m+="                         width:(CGFloat)w\n";
		m+="                          font:(UIFont *)font {\n";
		m+="    if (text == nil) {\n";
		m+="        return CGSizeMake(w, 0);\n";
		m+="    }\n";
		m+="    CGSize textSize;\n";
		m+="    if (kBXDCRM_IOS7) {\n";
		m+="        NSAttributedString *attrStr = [[NSAttributedString alloc]\n";
		m+="                                       initWithString:text\n";
		m+="                                       attributes:@{ NSFontAttributeName : font }];\n";
		m+="        NSRange textRange = NSMakeRange(0, attrStr.length);\n";
		m+="        NSDictionary *dic = [attrStr attributesAtIndex:0 effectiveRange:&textRange];\n";
		m+="        //计算文本的大小\n";
		m+="        textSize =\n";
		m+="        [text boundingRectWithSize:CGSizeMake(w, MAXFLOAT)\n";
		m+="                           options:NSStringDrawingUsesLineFragmentOrigin |\n";
		m+="         NSStringDrawingUsesFontLeading\n";
		m+="                        attributes:dic\n";
		m+="                           context:nil].size;\n";
		m+="        \n";
		m+="    } else {\n";
		m+="        textSize = [text sizeWithFont:font\n";
		m+="                    constrainedToSize:CGSizeMake(w, MAXFLOAT)\n";
		m+="                        lineBreakMode:NSLineBreakByWordWrapping];\n";
		m+="    }\n";
		m+="    return textSize;\n";
		m+="}\n";


		m += "#pragma mark anniversary cell init\n";
		m += "- (UITableViewCell *)getCell:(UITableView *)tableView cellId:(NSString *)CellIdentifier cellValue:("+tableViewCellName+"Bean *)bean {\n";
		m += "    "+tableViewCellName+" *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];\n";
		m += "    if (cell == nil) {\n";
		m += "        cell = [["+tableViewCellName+" alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier withWidth:CGRectGetWidth(tableView.bounds) - 20];\n";
		m += "    }\n";

		m += "    if (todoModel != nil && todoModel.anniversaryDict != nil) {\n";
		for (ImgBean bean : controls) {
		m += "        NSString *"+bean.enName+"Value = [bean objectForKey:@\""+bean.enName+"Value\"];\n";
		m += "       // cell."+bean.enName+".tag = ((NSNumber *) [bean objectForKey:@\"ID\"]).intValue;\n";
		m += "        [cell."+bean.enName+" setTitle:[bean objectForKey:@\""+bean.enName+"Value\"] forState:UIControlStateNormal];\n";
		}
		
		
//		m += "        cell.todoContentLabel.text = descStr;\n";
//		m += "        // adjust the height of cell and the spline pos\n";
//		m += "        CGSize todoDescLabelSize = [self calContentTextHeight:descStr width:(CGRectGetWidth(tableView.bounds) - 20 - 60 - 100) font:[UIFont systemFontOfSize:16.f]];\n";
//		m += "        CGRect todoLabelFrame = cell.todoContentLabel.frame;\n";
//		m += "        todoLabelFrame.size.height = fmaxf(50.f, todoDescLabelSize.height + 20);\n";
//		m += "        cell.todoContentLabel.frame = todoLabelFrame;\n";
//
//		m += "        CGRect spLineFrame = cell.spLineImage.frame;\n";
//		m += "        spLineFrame.origin.y = todoLabelFrame.size.height - 1;\n";
//		m += "        cell.spLineImage.frame = spLineFrame;\n";
//		m += "    }\n";

		m += "    cell.selectionStyle = UITableViewCellSelectionStyleNone;\n";
		m += "    return cell;\n";
		m += "}\n";

		
		m += "#pragma mark remind time picker action\n";
		m += "- (void)popupRemindTimePicker:(id)popupRemindTimePickerBtn {\n";
		m += "    BXDModelButton *btn = (BXDModelButton *) popupRemindTimePickerBtn;\n";
		m += "    UIViewController *contentViewController = [[UIViewController alloc] init];\n";
		m += "    BXDModelDatePicker *datePicker = [[BXDModelDatePicker alloc] init];\n";

		m += "    NSDate *date = [[NSDate date] dateByAddingTimeInterval:600];\n";
		m += "    BXDCrmTodoEventWrapperModel *model = btn.model;\n";
		m += "    if (model.todoEventDetail.eventRemindDate != nil) {\n";
		m += "        date = model.todoEventDetail.eventRemindDate;\n";
		m += "    } else {\n";
		m += "        if (model.todoEventDetail.eventBeginDate == nil) {\n";
		m += "            model.todoEventDetail.eventRemindDate = date;\n";
		m += "        } else {\n";
		m += "            model.todoEventDetail.eventRemindDate = [model.todoEventDetail.eventBeginDate dateByAddingTimeInterval:600];\n";
		m += "        }\n";
		m += "        [self reloadTableView:YES];\n";
		m += "    }\n";

		m += "    datePicker.datePickerMode = UIDatePickerModeDateAndTime;\n";
		m += "    datePicker.locale = [NSLocale currentLocale];\n";
		m += "    datePicker.date = date;\n";
		m += "    datePicker.minimumDate = [NSDate date];\n";
		m += "    datePicker.dataModel = btn.model;\n";
		m += "    datePicker.uiModel = btn;\n";

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

		return m;
	}

	// table cell h
	public String tableViewCellH(List<ImgBean> controls,
			String tableViewCellName) {
		m += "#import <UIKit/UIKit.h>\n";

		m += "#define CellHeight 50.f\n";

		m += "@interface " + tableViewCellName + " : UITableViewCell\n";

		for (ImgBean bean : controls) {
			m += "@property(nonatomic, strong) IBOutlet " + bean.controlType
					+ " *" + firstCharLower(bean.enName) + ";\n";
		}
		m += "@property(nonatomic, assign) float width;\n";
		m += "@property(nonatomic, assign) float height;\n";
		m += "- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier withWidth:(float)width;\n";
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

		m += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		m += "<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.iPad.XIB\" version=\"3.0\" toolsVersion=\"5053\" systemVersion=\"13C1021\" targetRuntime=\"iOS.CocoaTouch.iPad\" propertyAccessControl=\"none\" useAutolayout=\"YES\">\n";
		m += "    <dependencies>\n";
		m += "        <deployment defaultVersion=\"1536\" identifier=\"iOS\"/>\n";
		m += "        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"3733\"/>\n";
		m += "    </dependencies>\n";
		m += "    <objects>\n";

		m += "        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\" customClass=\""
				+ tableViewCellName + "\">\n";
		m += "            <connections>\n";

		for (ImgBean bean : controls) {

			m += "                <outlet property=\""
					+ this.firstCharLower(bean.enName) + "\" destination=\""
					+ bean.id + "\" id=\"" + this.getId() + "\"/>\n";
		}

		m += "            </connections>\n";
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
						+ bean.enName + "\">\n";
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
			if (bean.controlType.equals("UIView")) {

				int viewX = bean.x;
				int viewY = bean.y;
				int viewW = bean.w;
				int viewH = bean.h;

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
						if (bean2.controlType.equals("UILabel")) {
							m += "    <label opaque=\"NO\" clipsSubviews=\"YES\" userInteractionEnabled=\"NO\" contentMode=\"left\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" text=\""
									+ bean2.enName
									+ " "
									+ bean2.cnName
									+ "\" lineBreakMode=\"tailTruncation\" baselineAdjustment=\"alignBaselines\" adjustsFontSizeToFit=\"NO\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
									+ bean2.id + "\">\n";
							m += "                                <rect key=\"frame\" x=\""
									+ (bean2.x - viewX)
									+ "\" y=\""
									+ (bean2.y - viewY)
									+ "\" width=\""
									+ bean2.w
									+ "\" height=\""
									+ bean2.h
									+ "\"/>\n";
							m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
							m += "                                <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"17\"/>\n";
							m += "                                <nil key=\"highlightedColor\"/>\n";
							m += "                            </label>\n";
						} else if (bean2.controlType.equals("UITextView")) {
							m += "                            <textField opaque=\"NO\" clipsSubviews=\"YES\" contentMode=\"scaleToFill\" fixedFrame=\"YES\" contentHorizontalAlignment=\"left\" contentVerticalAlignment=\"center\" borderStyle=\"roundedRect\" minimumFontSize=\"17\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
									+ bean2.id + "\">\n";
							m += "                                <rect key=\"frame\" x=\""
									+ (bean2.x - viewX)
									+ "\" y=\""
									+ (bean2.y - viewY)
									+ "\" width=\""
									+ bean2.w
									+ "\" height=\""
									+ bean2.h
									+ "\"/>\n";
							m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
							m += "                                <fontDescription key=\"fontDescription\" type=\"system\" pointSize=\"14\"/>\n";
							m += "                                <textInputTraits key=\"textInputTraits\"/>\n";
							m += "                            </textField>\n";

						} else if (bean2.controlType.equals("UIImageView")) {
							m += "      <imageView userInteractionEnabled=\"NO\" contentMode=\"scaleToFill\" horizontalHuggingPriority=\"251\" verticalHuggingPriority=\"251\" fixedFrame=\"YES\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
									+ bean2.id + "\">\n";
							m += "                                <rect key=\"frame\" x=\""
									+ (bean2.x - viewX)
									+ "\" y=\""
									+ (bean2.y - viewY)
									+ "\" width=\""
									+ bean2.w
									+ "\" height=\""
									+ bean2.h
									+ "\"/>\n";
							m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
							m += "                            </imageView>\n";

						} else if (bean2.controlType.equals("UIButton")) {

							m += "              <button opaque=\"NO\" contentMode=\"scaleToFill\" fixedFrame=\"YES\" contentHorizontalAlignment=\"center\" contentVerticalAlignment=\"center\" buttonType=\"roundedRect\" lineBreakMode=\"middleTruncation\" translatesAutoresizingMaskIntoConstraints=\"NO\" id=\""
									+ bean2.id + "\">\n";
							m += "                                <rect key=\"frame\" x=\""
									+ (bean2.x - viewX)
									+ "\" y=\""
									+ (bean2.y - viewY)
									+ "\" width=\""
									+ bean2.w
									+ "\" height=\""
									+ bean2.h
									+ "\"/>\n";
							m += "                                <autoresizingMask key=\"autoresizingMask\" flexibleMaxX=\"YES\" flexibleMaxY=\"YES\"/>\n";
							m += "                                <state key=\"normal\" title=\""
									+ bean2.cnName + "\">\n";
							m += "                                    <color key=\"titleShadowColor\" white=\"0.5\" alpha=\"1\" colorSpace=\"calibratedWhite\"/>\n";
							m += "                                </state>\n";
							m += "                            </button>\n";
						}
					}

				}
				m += "                        </subviews>\n";
				m += "                        <color key=\"backgroundColor\" white=\"1\" alpha=\"1\" colorSpace=\"custom\" customColorSpace=\"calibratedWhite\"/>\n";
				m += "                    </view>\n";

			}
		}

		m += "                </subviews>\n";
		m += "            </tableViewCellContentView>\n";
		m += "        </tableViewCell>\n";
		m += "    </objects>\n";
		m += "</document>\n";
		System.out.println(m);

	}

	// table cell m
	public String tableViewCellM(List<ImgBean> controls,
			String tableViewCellName) {
		String m = "############################\n\n";

		m += "#import \"" + tableViewCellName + ".h\"\n";

		m += "@implementation " + tableViewCellName + "\n";
		for (ImgBean bean : controls) {
			m += "@synthesize " + firstCharLower(bean.enName) + ";\n";
		}

		m += "- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier withWidth:(float)width  withHeight:(float)height {\n";
		m += "    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];\n";
		m += "    if (self) {\n";
		m += "        // Initialization code\n";
		m += "        self.width = width;\n";
		m += "        self.height = height;\n";
		m += "        CGRect r = self.frame;\n";
		m += "        r.size.width = self.width;\n";
		m += "        self.frame = r;\n";

		for (ImgBean bean : controls)

		{
			ImgBean parent = bean.controlBelong;
			if (parent != null) {
				if (bean.controlType.equals("UITextView")) {
					m += "//" + bean.cnName + "\n";
					m += "        " + this.firstCharLower(bean.enName)
							+ " = [[" + bean.controlType
							+ " alloc] initWithFrame:CGRectMake("
							+ (bean.x - bean.controlBelong.x) + ", "
							+ (bean.y - bean.controlBelong.y)
							+ ", CGRectGetWidth(" + bean.controlBelong.enName
							+ ".bounds) - " + (bean.controlBelong.w - bean.w)
							+ ", " + bean.h + ")];\n";
					m += "        " + this.firstCharLower(bean.enName)
							+ ".font = [UIFont systemFontOfSize:18.f];\n";
					m += "        " + this.firstCharLower(bean.enName)
							+ ".backgroundColor = [UIColor clearColor];\n";
					m += "        "
							+ this.firstCharLower(bean.enName)
							+ ".autocapitalizationType = UITextAutocapitalizationTypeNone;\n";
					m += "        "
							+ this.firstCharLower(bean.enName)
							+ ".autocorrectionType = UITextAutocorrectionTypeNo;\n";
					m += "        "
							+ this.firstCharLower(bean.enName)
							+ ".contentInset = UIEdgeInsetsMake(-10, 0, -10, 0);\n";
					m += "        ["
							+ this.firstCharLower(bean.controlBelong.enName)
							+ " addSubview:" + this.firstCharLower(bean.enName)
							+ "];\n";

				} else if (bean.controlType.equals("UIImageView")) {
					m += "//" + bean.cnName + "\n";
					m += "        " + this.firstCharLower(bean.enName)
							+ " = [[" + bean.controlType
							+ " alloc] initWithFrame:CGRectMake("
							+ (bean.x - bean.controlBelong.x) + ", "
							+ (bean.y - bean.controlBelong.y)
							+ ", CGRectGetWidth(" + bean.controlBelong.enName
							+ ".bounds) - " + (bean.controlBelong.w - bean.w)
							+ ", " + bean.h + ")];\n";
					m += "        " + this.firstCharLower(bean.enName)
							+ ".backgroundColor = [UIColor colorWithRed:"
							+ bean.red10value + " green:" + bean.green10value
							+ " blue:" + bean.blue10Value + " alpha:1.000];\n";
					m += "        ["
							+ this.firstCharLower(bean.controlBelong.enName)
							+ " addSubview:" + this.firstCharLower(bean.enName)
							+ "];\n";

				} else if (bean.controlType.equals("UIButtom")) {
					m += "//" + bean.cnName + "\n";
					m += "        " + this.firstCharLower(bean.enName)
							+ " = [[" + bean.controlType
							+ " alloc] initWithFrame:CGRectMake("
							+ (bean.x - bean.controlBelong.x) + ", "
							+ (bean.y - bean.controlBelong.y)
							+ ", CGRectGetWidth(" + bean.controlBelong.enName
							+ ".bounds) - " + (bean.controlBelong.w - bean.w)
							+ ", " + bean.h + ")];\n";
					m += "        "
							+ this.firstCharLower(bean.enName)
							+ ".contentHorizontalAlignment = UIControlContentHorizontalAlignmentRight;\n";
					m += "        ["
							+ this.firstCharLower(bean.enName)
							+ " setTitle:@\"清空\" forState:UIControlStateNormal];\n";
					m += "        ["
							+ this.firstCharLower(bean.enName)
							+ " setTitleColor:[UIColor blackColor] forState:UIControlStateHighlighted];\n";
					m += "        ["
							+ this.firstCharLower(bean.enName)
							+ " setTitleColor:BXDCrmPersonContactCardFooterViewTitleColor forState:UIControlStateNormal];//#define BXDCrmPersonContactCardFooterViewTitleColor   [UIColor colorWithRed:0.180 green:0.569 blue:0.937 alpha:1.000]\n";
					m += "        ["
							+ this.firstCharLower(bean.controlBelong.enName)
							+ " addSubview:" + this.firstCharLower(bean.enName)
							+ "];\n";

				} else if (bean.controlType.equals("UILabel")) {
					m += "//" + bean.cnName + "\n";
					m += "    " + this.firstCharLower(bean.enName) + " = [["
							+ bean.controlType
							+ " alloc] initWithFrame:CGRectMake("
							+ (bean.x - bean.controlBelong.x) + ", "
							+ (bean.y - bean.controlBelong.y)
							+ ", CGRectGetWidth(" + bean.controlBelong.enName
							+ ".bounds) - " + (bean.controlBelong.w - bean.w)
							+ ", " + bean.h + ")];\n";
					m += "    " + this.firstCharLower(bean.enName)
							+ ".textColor = [UIColor blackColor];\n";
					m += "    " + this.firstCharLower(bean.enName) + ".text = "
							+ bean.cnName + ";\n";
					m += "    " + this.firstCharLower(bean.enName)
							+ ".textAlignment = NSTextAlignmentLeft;\n";
					m += "    ["
							+ this.firstCharLower(bean.controlBelong.enName)
							+ " addSubview:" + this.firstCharLower(bean.enName)
							+ "];\n";

				} else if (bean.controlType.equals("UIView")) {
					m += "//" + bean.cnName + "\n";
					m += "    " + this.firstCharLower(bean.enName) + " = [["
							+ bean.controlType
							+ " alloc] initWithFrame:CGRectMake("
							+ (bean.x - bean.controlBelong.x) + ", "
							+ (bean.y - bean.controlBelong.y)
							+ ", CGRectGetWidth(" + bean.controlBelong.enName
							+ ".bounds) - " + (bean.controlBelong.w - bean.w)
							+ ", " + bean.h + ")];\n";
					m += "    ["
							+ this.firstCharLower(bean.controlBelong.enName)
							+ " addSubview:" + this.firstCharLower(bean.enName)
							+ "];\n";

				}

			} else {
				m += "//" + bean.cnName + "\n";
				m += "    " + this.firstCharLower(bean.enName) + " = [["
						+ bean.controlType
						+ " alloc] initWithFrame:CGRectMake("
						+ (bean.x ) + ", "
						+ (bean.y ) + ", "+( bean.w) + ", " + bean.h
						+ ")];\n";
				m += "  [self addSubview:" + this.firstCharLower(bean.enName)
						+ "];\n";

			}
		}

		m += "- (void)setSelected:(BOOL)selected animated:(BOOL)animated {\n";
		m += "    [super setSelected:selected animated:animated];\n";

		m += "    // Configure the view for the selected state\n";
		m += "}\n";

		m += "@end\n";

		System.out.println(m);

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
	
	public void init()
	{
		FileReader fr=null;
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
				ImgBean imgBean=new ImgBean();
				myreadline = br.readLine();
				
				jsonString+=myreadline;
				
			}
			
			br.close();
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}