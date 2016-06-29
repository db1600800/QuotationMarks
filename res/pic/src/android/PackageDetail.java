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
public class PackageDetail extends Activity implements OnScrollListener {
Context context;
public View containView;
String searchText;
/**back*/
Button backButton;
/**新绑定*/
Button newBindButton;
/**删除*/
Button deleteButton;
/**可加办套餐详情*/
TextView titleTextView;
/**套餐信息*/
TextView part1TitleTextView;
/**套餐名称*/
TextView packageNameTitleTextView;
/**2013*/
TextView packageNameTitleTextViewValueTextView;
/**周期*/
TextView feePeriodTextView;
/**6个月*/
TextView feePeriodValueTextView;
/**价格*/
TextView feeTitleTextView;
/**333*/
TextView feeValueTextView;
/**服务区域*/
TextView areaTitleTextView;
/**广州北京*/
TextView areaValueTextView;
/**收费模式*/
TextView feeModeTitleTextView;
/**先付款*/
TextView feeModeValueTextView;
/**累加模式*/
TextView countModeTitleTextView;
/**按次*/
TextView countModeTitleTextViewValueTextView;
/**绑定信息*/
TextView part3TitleTextView;
/**最大绑定数量*/
TextView maxBindNumTitleTextView;
/**10*/
TextView maxBindNumValueTextView;
/**已绑*/
TextView bindedNumTitleTextView;
/**2*/
TextView bindedNumValueTextView;
/**小型汽车*/
TextView cartypeTextView;
/**粤c132*/
TextView carNumTextView;
/**小树枝*/
TextView carMsgTextView;
/**暂未绑定车辆*/
TextView emptyBindTextView;
/**简介*/
TextView singleTitleTextView;
/**safdsf*/
TextView singleMsgValueTextView;
public PackageDetail()
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
			containView = inflateView(R.layout.packageDetail);
//back
backButton = (Button) containView
					.findViewById(R.id.backButton);
backButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//新绑定
newBindButton = (Button) containView
					.findViewById(R.id.newBindButton);
newBindButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//删除
deleteButton = (Button) containView
					.findViewById(R.id.deleteButton);
deleteButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//可加办套餐详情
titleTextView= (TextView) containView.findViewById(R.id.titleTextView);
//套餐信息
part1TitleTextView= (TextView) containView.findViewById(R.id.part1TitleTextView);
//套餐名称
packageNameTitleTextView= (TextView) containView.findViewById(R.id.packageNameTitleTextView);
//2013
packageNameTitleTextViewValueTextView= (TextView) containView.findViewById(R.id.packageNameTitleTextViewValueTextView);
//周期
feePeriodTextView= (TextView) containView.findViewById(R.id.feePeriodTextView);
//6个月
feePeriodValueTextView= (TextView) containView.findViewById(R.id.feePeriodValueTextView);
//价格
feeTitleTextView= (TextView) containView.findViewById(R.id.feeTitleTextView);
//333
feeValueTextView= (TextView) containView.findViewById(R.id.feeValueTextView);
//服务区域
areaTitleTextView= (TextView) containView.findViewById(R.id.areaTitleTextView);
//广州北京
areaValueTextView= (TextView) containView.findViewById(R.id.areaValueTextView);
//收费模式
feeModeTitleTextView= (TextView) containView.findViewById(R.id.feeModeTitleTextView);
//先付款
feeModeValueTextView= (TextView) containView.findViewById(R.id.feeModeValueTextView);
//累加模式
countModeTitleTextView= (TextView) containView.findViewById(R.id.countModeTitleTextView);
//按次
countModeTitleTextViewValueTextView= (TextView) containView.findViewById(R.id.countModeTitleTextViewValueTextView);
//绑定信息
part3TitleTextView= (TextView) containView.findViewById(R.id.part3TitleTextView);
//最大绑定数量
maxBindNumTitleTextView= (TextView) containView.findViewById(R.id.maxBindNumTitleTextView);
//10
maxBindNumValueTextView= (TextView) containView.findViewById(R.id.maxBindNumValueTextView);
//已绑
bindedNumTitleTextView= (TextView) containView.findViewById(R.id.bindedNumTitleTextView);
//2
bindedNumValueTextView= (TextView) containView.findViewById(R.id.bindedNumValueTextView);
//小型汽车
cartypeTextView= (TextView) containView.findViewById(R.id.cartypeTextView);
//粤c132
carNumTextView= (TextView) containView.findViewById(R.id.carNumTextView);
//小树枝
carMsgTextView= (TextView) containView.findViewById(R.id.carMsgTextView);
//暂未绑定车辆
emptyBindTextView= (TextView) containView.findViewById(R.id.emptyBindTextView);
//简介
singleTitleTextView= (TextView) containView.findViewById(R.id.singleTitleTextView);
//safdsf
singleMsgValueTextView= (TextView) containView.findViewById(R.id.singleMsgValueTextView);
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
		//intent.setClass(PackageDetail.this,OperatorModify.class);
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		//intent.putExtras(bundle);
		//startActivityForResult(intent,n0000);
	}
}.execute("");}
public void setView(){
//back
//新绑定
//删除
//可加办套餐详情
titleTextView.setText("");
//套餐信息
part1TitleTextView.setText("");
//套餐名称
packageNameTitleTextView.setText("");
//2013
packageNameTitleTextViewValueTextView.setText("");
//周期
feePeriodTextView.setText("");
//6个月
feePeriodValueTextView.setText("");
//价格
feeTitleTextView.setText("");
//333
feeValueTextView.setText("");
//服务区域
areaTitleTextView.setText("");
//广州北京
areaValueTextView.setText("");
//收费模式
feeModeTitleTextView.setText("");
//先付款
feeModeValueTextView.setText("");
//累加模式
countModeTitleTextView.setText("");
//按次
countModeTitleTextViewValueTextView.setText("");
//绑定信息
part3TitleTextView.setText("");
//最大绑定数量
maxBindNumTitleTextView.setText("");
//10
maxBindNumValueTextView.setText("");
//已绑
bindedNumTitleTextView.setText("");
//2
bindedNumValueTextView.setText("");
//小型汽车
cartypeTextView.setText("");
//粤c132
carNumTextView.setText("");
//小树枝
carMsgTextView.setText("");
//暂未绑定车辆
emptyBindTextView.setText("");
//简介
singleTitleTextView.setText("");
//safdsf
singleMsgValueTextView.setText("");
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
//新绑定
//删除
//可加办套餐详情
//套餐信息
//套餐名称
//2013
//周期
//6个月
//价格
//333
//服务区域
//广州北京
//收费模式
//先付款
//累加模式
//按次
//绑定信息
//最大绑定数量
//10
//已绑
//2
//小型汽车
//粤c132
//小树枝
//暂未绑定车辆
//简介
//safdsf
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

