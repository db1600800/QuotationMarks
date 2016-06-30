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
public class MyCanAddPackage extends Activity implements OnScrollListener {
Context context;
public View containView;
String searchText;
/**我的套餐*/
Button myPackageButton;
/**价格从高到低*/
Button priceUpButton;
/**3个月*/
Button timeButton;
/**全国*/
Button cityButton;
/**办理*/
Button buyButton;
/**可加办套餐*/
TextView titleTextView;
/**总计:*/
TextView totalTitleTextView;
/**123*/
TextView totalValueTextView;
/***/
ImageView downImageView;
/***/
ImageView downImageView;
/***/
ImageView downImageView;
/***/
ListView listListView;
MyCanAddPackageAdapter adapter;
public MyCanAddPackage()
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
			containView = inflateView(R.layout.myCanAddPackage);
//我的套餐
myPackageButton = (Button) containView
					.findViewById(R.id.myPackageButton);
myPackageButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//价格从高到低
priceUpButton = (Button) containView
					.findViewById(R.id.priceUpButton);
priceUpButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//3个月
timeButton = (Button) containView
					.findViewById(R.id.timeButton);
timeButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//全国
cityButton = (Button) containView
					.findViewById(R.id.cityButton);
cityButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//办理
buyButton = (Button) containView
					.findViewById(R.id.buyButton);
buyButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//可加办套餐
titleTextView= (TextView) containView.findViewById(R.id.titleTextView);
//总计:
totalTitleTextView= (TextView) containView.findViewById(R.id.totalTitleTextView);
//123
totalValueTextView= (TextView) containView.findViewById(R.id.totalValueTextView);
//
downImageView= (ImageView) containView.findViewById(R.id.downImageView);
//
downImageView= (ImageView) containView.findViewById(R.id.downImageView);
//
downImageView= (ImageView) containView.findViewById(R.id.downImageView);
//
listListView= (ListView) containView.findViewById(R.id.listListView);
adapter= new MyCanAddPackageAdapter(context);
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
		//intent.setClass(MyCanAddPackage.this,OperatorModify.class);
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		//intent.putExtras(bundle);
		//startActivityForResult(intent,n0000);
	}
}.execute("");}
public void setView(){
//我的套餐
//价格从高到低
//3个月
//全国
//办理
//可加办套餐
titleTextView.setText("");
//总计:
totalTitleTextView.setText("");
//123
totalValueTextView.setText("");
//
//
//
//
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
//我的套餐
//价格从高到低
//3个月
//全国
//办理
//可加办套餐
//总计:
//123
//
//
//
//
adapter.setList(listData);
listListView.setAdapter(adapter);
listListView.setOnScrollListener(MyCanAddPackage.this);
listListView.setOnItemClickListener(new OnItemClickListener() {
	@Override
	public void onItemClick(
	AdapterView<?> arg0,
	View view, int position,
	long id) {
		Intent intent = new Intent();
		intent.setClass(MyCanAddPackage.this,OperatorModify.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		intent.putExtras(bundle);
		startActivityForResult(intent,n0000);
	}
});
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

