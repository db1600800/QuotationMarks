package com.compoment.addfunction.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.chinapost.palmpost.AbnormalMailQueryStatActivity;
import com.chinapost.palmpost.AccumulatedMailDynActivity;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;

public class Report {
	String sourceAddress = KeyValue.readCache("compomentProjectAddress");//"C:\\Documents and Settings\\Administrator\\My Documents\\下载\\mobile-android";
	String destinationAddress = KeyValue.readCache("projectPath");
	String waitByModifyFileName;


	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Report("");
	}

	public Report(String waitByModifyFileName) {
		this.waitByModifyFileName = waitByModifyFileName;
		copyFile();
		add();
	}

	public void copyFile() {
		
		List<FileBean> fileBeans = new ArrayList();

		
		fileBeans.add(new FileBean("/res/layout", null,
				"listfooter", "xml"));



		for (FileBean bean : fileBeans) {

			File wantFile = FileUtil.findFile(new File(sourceAddress
					+ bean.sourcePath), bean.name + "." + bean.type);

			if (wantFile == null)
				System.out.println("找不到此文件" + bean.name + "." + bean.type);

			// 图片文件
			if (bean.type.equals("png") || bean.type.equals("jpg")) {
				try {
					FileUtil.copyFile(wantFile, new File(destinationAddress
							+ bean.destinationPath + bean.name + "."
							+ bean.type));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {// 其它文件

				List lines = FileUtil.fileContentToArrayList(wantFile
						.getAbsolutePath());

				String content = "";
				for (int i = 0; i < lines.size(); i++) {
					String line = "";
					if (lines.get(i) == null) {
						line = "";
					} else {
						line = lines.get(i).toString();
					}

					content += line + "\n";
				}

				String filename = FileUtil.makeFile(destinationAddress
						+ bean.destinationPath, null, bean.name, bean.type,
						content);
				System.out.println(filename);
			}
		}

	}

	

	public void add() {
	
		List addedLines =new ArrayList();
	
		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(waitByModifyFileName);

		
		String className="";
		
		//是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if(line.contains("//注入报表列表"))
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

		
	       if ( regex.constructFunctionRegex(line)!=null) {
	    	   className=regex.constructFunctionRegex(line);
		    	String m="";
		    	m+="\n//注入报表列表\n";
		    	m+="public HorizontalScrollView mTouchView;\n";
				m+="public List<MyHorizontalScrollView> mHScrollViews = new ArrayList<MyHorizontalScrollView>();\n";
		    	
				content += m;
			}
	       else  if (regex.functionRegex(line)!=null && regex.functionRegex(line).equals("onScroll")) {
				
				String m="//报表列表\n";
				m+="	public void addHViews(final MyHorizontalScrollView hScrollView) {\n";
				m+="			if (!mHScrollViews.isEmpty()) {\n";
				m+="				int size = mHScrollViews.size();\n";
				m+="				MyHorizontalScrollView scrollView = mHScrollViews.get(size - 1);\n";
				m+="				final int scrollX = scrollView.getScrollX();\n";

				m+="				if (scrollX != 0) {\n";
				m+="					listView.post(new Runnable() {\n";
				m+="						@Override\n";
				m+="						public void run() {\n";
				m+="							hScrollView.scrollTo(scrollX, 0);\n";
				m+="						}\n";
				m+="					});\n";
				m+="				}\n";
				m+="			}\n";
				m+="			mHScrollViews.add(hScrollView);\n";
				m+="		}\n\n";
				
				m+="//报表列表\n";
				m+="		public void onHorizontalScrollChanged (int l, int t, int oldl, int oldt) {\n";
				m+="			for (MyHorizontalScrollView scrollView : mHScrollViews) {\n";
				m+="				if (mTouchView != scrollView)\n";
				m+="					scrollView.smoothScrollTo(l, t);\n";
				m+="			}\n";
				m+="		}\n";

		    	content += m;
		    	
			}else if(line.contains(".setOnScrollListener"))
				
			{
				String m="//分页\n";
				m+="(listData.add()之前加入  int oldSize = listData.size();)\n";
		    	m+="countPage(maxCount);\n";
		    	m+="listListView.setSelection(oldSize);\n";
		    	content += m;
			}else if(line.contains("//分页使用"))
			{
				int p=line.indexOf("//");
				if(p!=-1)
				{
					line=line.substring(p+2);
				}
			}
			
			content += line + "\n";
			
			 if (regex.functionRegex(line)!=null && regex.functionRegex(line).equals("onScrollStateChanged")) {
				 
					String m="//分页\n";
				    m+="		if (scrollState == SCROLL_STATE_IDLE) {\n";
			    	m+="			if (view.getLastVisiblePosition() == (view.getCount() - 1)) {\n";
			    	m+="				if ((page) < totalPage) {\n";
			    	m+="					page++;\n";
			    	m+="					recordCount += pageSize;\n";
			    	m+="					request...();\n";
			    	m+="					Toast.makeText("+className+".this, \"数据已加载...\" + page + \" 页\",\n";
			    	m+="							Toast.LENGTH_SHORT).show();\n";
			    	m+="					\n";
			    	m+="					more.setVisibility(view.GONE);\n";
			    	m+="					progress.setVisibility(View.VISIBLE);\n";
			    	m+="					load.setVisibility(View.VISIBLE);\n";
			    	m+="					footerView.setVisibility(View.VISIBLE);\n";
			    	m+="				} else {\n";

			    	m+="					Toast.makeText("+className+".this, \"数据已加载完...\" + page + \" 页\",\n";
			    	m+="							Toast.LENGTH_SHORT).show();\n";
			    	m+="					\n";
			    	m+="					footerView.setVisibility(View.GONE);\n";

			    	m+="				}\n";
			    	m+="			}\n";
			    	m+="		}\n";
			    	content += m;
			 }

		}

	 String filename=FileUtil.makeFile(waitByModifyFileName, content);
	}
	

	public class AbnormalMailQueryStatActivity extends Activity  {






	
		


		public class AbnormalMailQueryStatAdapter extends BaseAdapter {

			private LayoutInflater inflater;// 填充对象
			private ArrayList<HashMap<String, String>> lists;// 数据集
			
		

			public AbnormalMailQueryStatAdapter(LayoutInflater layoutInflater,
					ArrayList<HashMap<String, String>> lists) {
				super();
				this.lists = lists;
			
				inflater = layoutInflater;
			}

			@Override
			public int getCount() {
				return lists.size();
			}

			@Override
			public Object getItem(int arg0) {
				return lists.get(arg0);
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public View getView(int index, View converView, ViewGroup parent) {

				
				if (converView == null) {
				
					converView = inflater.inflate(
							R.layout.abnormalmailquerystat_list_item, null);
					addHViews((MyHorizontalScrollView) converView
							.findViewById(R.id.item_scroll));
				
				

				} else {
					
				}

						holder.textView2.getPaint().setUnderlineText(true);
									
					if (index == 0) {
					
					}
					if (index == lists.size() - 1) {
						
					}
				}
				return converView;
			}

			class ViewHolder {
		
				TextView textView2;
				TextView textView3;
				TextView textView4;
				TextView textView5;
				TextView textView6;
				TextView textView7;
				TextView textView8;
			}

		}
	}

	

public class MyHorizontalScrollView extends HorizontalScrollView {
	
	Context context;
	
	public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (context != null) {
			this.context = context;
		}
	}

	
	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (context != null) {
			this.context = context;
		}
	}

	public MyHorizontalScrollView(Context context) {
		super(context);
		if (context != null) {
			this.context = context;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (context != null) {
			if(context instanceof AccumulatedMailDynActivity) {
				((AccumulatedMailDynActivity) context).mTouchView = this;
			}
			if(context instanceof AbnormalMailQueryStatActivity) {
				((AbnormalMailQueryStatActivity) context).mTouchView = this;
			}
		}
		return super.onTouchEvent(ev);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if (context != null) {
			if(context instanceof AccumulatedMailDynActivity) {
				AccumulatedMailDynActivity accumulatedMailDynActivity = ((AccumulatedMailDynActivity) context);
				if(accumulatedMailDynActivity.mTouchView == this) {
					accumulatedMailDynActivity.onHorizontalScrollChanged (l, t, oldl, oldt);
				}else{
					super.onScrollChanged(l, t, oldl, oldt);
				}
			}
			if(context instanceof AbnormalMailQueryStatActivity) {
				AbnormalMailQueryStatActivity accumulatedMailDynActivity = ((AbnormalMailQueryStatActivity) context);
				if(accumulatedMailDynActivity.mTouchView == this) {
					accumulatedMailDynActivity.onHorizontalScrollChanged (l, t, oldl, oldt);
				}else{
					super.onScrollChanged(l, t, oldl, oldt);
				}
			}
		}
		
	}
}



<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:background="#ffffff" >

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal" >

        <View
            android:id="@+id/link0"
            style="@style/vertical_layout" />

        <TextView
            android:id="@+id/text2"
            android:layout_width="100dip"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical|center_horizontal"
            android:gravity="center"
            android:padding="8dip"
            android:text="广东邮政公司"
            android:textColor="#000000"
            android:textSize="12dp" />

        <!-- 第一日 -->

        <View
            android:id="@+id/link1"
            style="@style/vertical_layout" />

        <com.chinapost.view.MyHorizontalScrollView
            android:id="@+id/item_scroll"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:layout_weight="1"
            android:scrollbars="none" >

            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="horizontal" >

                <TextView
                    android:id="@+id/text3"
                    android:layout_width="75dip"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical|center_horizontal"
                    android:gravity="center"
                    android:padding="8dip"
                    android:text="邮件量"
                    android:textColor="#000000"
                    android:textSize="12dp" />

                <View
                    android:id="@+id/link2"
                    style="@style/vertical_layout" />

                <TextView
                    android:id="@+id/text4"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical|center_horizontal"
                    android:gravity="center"
                    android:minWidth="55dip"
                    android:padding="8dip"
                    android:text="2242"
                    android:textColor="#000000"
                    android:textSize="12dp" />

                <View
                    android:id="@+id/link3"
                    style="@style/vertical_layout" />

                <TextView
                    android:id="@+id/text5"
                    android:layout_width="65dip"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical|center_horizontal"
                    android:gravity="center"
                    android:padding="8dip"
                    android:text="二次异常"
                    android:textColor="#000000"
                    android:textSize="12dp" />

                <View
                    android:id="@+id/link4"
                    style="@style/vertical_layout" />

                <!-- 第二日 -->

                <TextView
                    android:id="@+id/text6"
                    android:layout_width="78dip"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical|center_horizontal"
                    android:gravity="center"
                    android:padding="8dip"
                    android:text="二次未异常"
                    android:textColor="#000000"
                    android:textSize="12dp" />

                <View
                    android:id="@+id/link5"
                    style="@style/vertical_layout" />

                <TextView
                    android:id="@+id/text7"
                    android:layout_width="65dip"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical|center_horizontal"
                    android:gravity="center"
                    android:minWidth="55dip"
                    android:padding="8dip"
                    android:text="三次异常"
                    android:textColor="#000000"
                    android:textSize="12dp" />

                <View
                    android:id="@+id/link6"
                    style="@style/vertical_layout" />

                <TextView
                    android:id="@+id/text8"
                    android:layout_width="78dip"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center_vertical|center_horizontal"
                    android:gravity="center"
                    android:padding="8dip"
                    android:text="三次未异常"
                    android:textColor="#000000"
                    android:textSize="12dp" />

                <View
                    android:id="@+id/link7"
                    style="@style/vertical_layout" />
            </LinearLayout>
        </com.chinapost.view.MyHorizontalScrollView>
    </LinearLayout>

</LinearLayout>



}
