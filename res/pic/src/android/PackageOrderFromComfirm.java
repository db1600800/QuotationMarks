import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
public class PackageOrderFromComfirm extends Activity implements OnScrollListener {
Context context;
public View containView;
String searchText;
/**back*/
Button backButton;
/**2013年*/
Button beginDateButton;
/**支付*/
Button paynowButton;
/**订单确认*/
TextView titleTextView;
/**业务包名*/
TextView packageNameTextView;
/**6个月*/
TextView periodTextView;
/**北京广州*/
TextView arearTextView;
/**先付款*/
TextView feeModeTextView;
/**按次数累加*/
TextView countModeTextView;
/**245*/
TextView feeTextView;
/**套餐生效日期*/
TextView dateTitleTextView;
/**总计:*/
TextView totalfeeTextView;
/**微支付*/
CheckBox weiPayCheckBox;
/**银联在线*/
CheckBox onlinePayCheckBox;
public PackageOrderFromComfirm()
{
}
  @Override
    public void onDestroy() {
        super.onDestroy();
 }
@Override
public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	Bundle extras = getIntent().getExtras();
	if (extras != null) {
		//userPara = extras.getString("userPara");
	}
context = this;
View view = init();
this.setContentView(view);
 }
public View init() {
		if (containView == null) {
			containView = inflateView(R.layout.packageOrderFromComfirm);
//back
backButton = (Button) containView
					.findViewById(R.id.backButton);
backButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//2013年
beginDateButton = (Button) containView
					.findViewById(R.id.beginDateButton);
beginDateButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//支付
paynowButton = (Button) containView
					.findViewById(R.id.paynowButton);
paynowButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//订单确认
titleTextView= (TextView) containView.findViewById(R.id.titleTextView);
//业务包名
packageNameTextView= (TextView) containView.findViewById(R.id.packageNameTextView);
//6个月
periodTextView= (TextView) containView.findViewById(R.id.periodTextView);
//北京广州
arearTextView= (TextView) containView.findViewById(R.id.arearTextView);
//先付款
feeModeTextView= (TextView) containView.findViewById(R.id.feeModeTextView);
//按次数累加
countModeTextView= (TextView) containView.findViewById(R.id.countModeTextView);
//245
feeTextView= (TextView) containView.findViewById(R.id.feeTextView);
//套餐生效日期
dateTitleTextView= (TextView) containView.findViewById(R.id.dateTitleTextView);
//总计:
totalfeeTextView= (TextView) containView.findViewById(R.id.totalfeeTextView);
//微支付
weiPayCheckBox= (CheckBox) containView.findViewById(R.id.weiPayCheckBox);
weiPayCheckBox.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
if (weiPayCheckBox.isChecked()) {
 adapter.selectAllCheckBox();
 }else{
 adapter.disSelectAllCheckBox();
}      }
    });
//银联在线
onlinePayCheckBox= (CheckBox) containView.findViewById(R.id.onlinePayCheckBox);
onlinePayCheckBox.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
if (onlinePayCheckBox.isChecked()) {
 adapter.selectAllCheckBox();
 }else{
 adapter.disSelectAllCheckBox();
}      }
    });
		}
request...();
return containView;
}
public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
}
public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
}
private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
}
{
		Thread thread = new Thread(new Runnable() {
			public void run() {
			}
		});
		thread.start();

Handler handler = new Handler() {
	public void handleMessage(Message msg) {
		switch (msg.what) {
			// case SHOW_LOCKER:
			// break;
			default:
				super.handleMessage(msg);
			}
		}
	};

 new  AsyncTask<String, Integer, Boolean>() {
	@Override
	protected void onPreExecute() {
	}
	@Override
	protected Boolean doInBackground(String... params) {
return true;
	}
	@Override
	protected void onPostExecute(Boolean isOk) {
		//Intent intent = new Intent();
		//intent.setClass(PackageOrderFromComfirm.this,OperatorModify.class);
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		//intent.putExtras(bundle);
		//startActivityForResult(intent,n0000);
	}
}.execute("");}
public void setView(){
//back
//2013年
//支付
//订单确认
titleTextView.setText("");
//业务包名
packageNameTextView.setText("");
//6个月
periodTextView.setText("");
//北京广州
arearTextView.setText("");
//先付款
feeModeTextView.setText("");
//按次数累加
countModeTextView.setText("");
//245
feeTextView.setText("");
//套餐生效日期
dateTitleTextView.setText("");
//总计:
totalfeeTextView.setText("");
//微支付
//银联在线
}
/**返回本页刷新数据*/
int n0000=0;
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == n0000) {
				// 页面刷新  重新查询一次
				if (listData != null)
					listData.clear();
				if (adapter != null)
					adapter.notifyDataSetChanged();
				//page = 1;//分页使用
				//recordCount = 1;//分页使用
				request...();
			return;
		}
		if (data == null)
			return;
	
		Bundle bundle = data.getExtras();
		String body = bundle.getString("jsonString");
	
		if (body!=null) {
			//有数据 且头没错
				try {
//back
//2013年
//支付
//订单确认
//业务包名
//6个月
//北京广州
//先付款
//按次数累加
//245
//套餐生效日期
//总计:
//微支付
//银联在线
//注入RequestRespond
//End注入RequestRespond
} catch (Exception e) {
e.printStackTrace();
}

} else  {
//page--;//分页使用
} 
}
}

