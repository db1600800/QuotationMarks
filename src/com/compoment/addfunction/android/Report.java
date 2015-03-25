package com.compoment.addfunction.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.chinapost.palmpost.AbnormalMailQueryStatActivity;
import com.chinapost.palmpost.AccumulatedMailDynActivity;

public class Report {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	public class AbnormalMailQueryStatActivity extends Activity  {


		private ListView listView;// 展示列表
		
		private ArrayList<HashMap<String, String>> lists = new ArrayList<HashMap<String, String>>();// 当前页数据集合
		
		private AbnormalMailQueryStatAdapter abnormalMailQueryStatAdapter;// 适配器
		public HorizontalScrollView mTouchView;
		protected List<MyHorizontalScrollView> mHScrollViews = new ArrayList<MyHorizontalScrollView>();
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_abnormalmailquerystat);

			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				userPara = extras.getString("userPara");
			}
			listView = (ListView) findViewById(R.id.listview);
			
			
			listView.setVisibility(View.VISIBLE);
			
			
			
			abnormalMailQueryStatAdapter = new AbnormalMailQueryStatAdapter(
					getLayoutInflater(), lists, metrics);
			
			
			listView.setAdapter(abnormalMailQueryStatAdapter);
			
		}


		public void addHViews(final MyHorizontalScrollView hScrollView) {
			if (!mHScrollViews.isEmpty()) {
				int size = mHScrollViews.size();
				MyHorizontalScrollView scrollView = mHScrollViews.get(size - 1);
				final int scrollX = scrollView.getScrollX();

				if (scrollX != 0) {
					listView.post(new Runnable() {
						@Override
						public void run() {

							hScrollView.scrollTo(scrollX, 0);
						}
					});
				}
			}
			mHScrollViews.add(hScrollView);
		}

		public void onScrollChanged(int l, int t, int oldl, int oldt) {
			for (MyHorizontalScrollView scrollView : mHScrollViews) {
				if (mTouchView != scrollView)
					scrollView.smoothScrollTo(l, t);
			}
		}

		


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
					accumulatedMailDynActivity.onScrollChanged(l, t, oldl, oldt);
				}else{
					super.onScrollChanged(l, t, oldl, oldt);
				}
			}
			if(context instanceof AbnormalMailQueryStatActivity) {
				AbnormalMailQueryStatActivity accumulatedMailDynActivity = ((AbnormalMailQueryStatActivity) context);
				if(accumulatedMailDynActivity.mTouchView == this) {
					accumulatedMailDynActivity.onScrollChanged(l, t, oldl, oldt);
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
