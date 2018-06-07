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
public class QuestionSuggestDetail extends Activity implements OnScrollListener {
Context context;
public View containView;
String searchText;
/**guangldong*/
Button fileurlButton;
/**提交*/
Button sumbitButton;
/**需求豆腐饭d*/
TextView questionMsgTextView;
/**需求管理模块*/
TextView modelTextView;
/**企业管理平台*/
TextView applicationTextView;
/**|*/
TextView lineTextView;
/**故障及问题*/
TextView questionTypeTextView;
/**反馈人*/
TextView feedBackTitleTextView;
/**张三*/
TextView feedBackNameTextView;
/**|*/
TextView line1TextView;
/**13343*/
TextView feedBackPhoneTextView;
/**|*/
TextView line3TextView;
/**广州*/
TextView feedBackOrgTextView;
/**反馈时间:*/
TextView feedBackTimeTitleTextView;
/**2018*/
TextView feedBackTimeTextView;
/***/
EditText dealMsgEditText;
/***/
EditText dealNameEditText;
/***/
EditText dealPhoneEditText;
/***/
ImageView fileimgImageView;
public QuestionSuggestDetail()
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
			containView = inflateView(R.layout.questionSuggestDetail);
//guangldong
fileurlButton = (Button) containView
					.findViewById(R.id.fileurlButton);
fileurlButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//提交
sumbitButton = (Button) containView
					.findViewById(R.id.sumbitButton);
sumbitButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//需求豆腐饭d
questionMsgTextView= (TextView) containView.findViewById(R.id.questionMsgTextView);
//需求管理模块
modelTextView= (TextView) containView.findViewById(R.id.modelTextView);
//企业管理平台
applicationTextView= (TextView) containView.findViewById(R.id.applicationTextView);
//|
lineTextView= (TextView) containView.findViewById(R.id.lineTextView);
//故障及问题
questionTypeTextView= (TextView) containView.findViewById(R.id.questionTypeTextView);
//反馈人
feedBackTitleTextView= (TextView) containView.findViewById(R.id.feedBackTitleTextView);
//张三
feedBackNameTextView= (TextView) containView.findViewById(R.id.feedBackNameTextView);
//|
line1TextView= (TextView) containView.findViewById(R.id.line1TextView);
//13343
feedBackPhoneTextView= (TextView) containView.findViewById(R.id.FeedBackPhoneTextView);
//|
line3TextView= (TextView) containView.findViewById(R.id.line3TextView);
//广州
feedBackOrgTextView= (TextView) containView.findViewById(R.id.FeedBackOrgTextView);
//反馈时间:
feedBackTimeTitleTextView= (TextView) containView.findViewById(R.id.FeedBackTimeTitleTextView);
//2018
feedBackTimeTextView= (TextView) containView.findViewById(R.id.FeedBackTimeTextView);
//
dealMsgEditText= (EditText) containView.findViewById(R.id.dealMsgEditText);
dealMsgEditText.addTextChangedListener(
new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
				
		}
}
);
//
dealNameEditText= (EditText) containView.findViewById(R.id.dealNameEditText);
dealNameEditText.addTextChangedListener(
new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
				
		}
}
);
//
dealPhoneEditText= (EditText) containView.findViewById(R.id.dealPhoneEditText);
dealPhoneEditText.addTextChangedListener(
new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
				
		}
}
);
//
fileimgImageView= (ImageView) containView.findViewById(R.id.fileimgImageView);
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
		//intent.setClass(QuestionSuggestDetail.this,OperatorModify.class);
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		//intent.putExtras(bundle);
		//startActivityForResult(intent,n0000);
	}
}.execute("");}
public void setView(){
//guangldong
//提交
//需求豆腐饭d
questionMsgTextView.setText("");
//需求管理模块
modelTextView.setText("");
//企业管理平台
applicationTextView.setText("");
//|
lineTextView.setText("");
//故障及问题
questionTypeTextView.setText("");
//反馈人
feedBackTitleTextView.setText("");
//张三
feedBackNameTextView.setText("");
//|
line1TextView.setText("");
//13343
feedBackPhoneTextView.setText("");
//|
line3TextView.setText("");
//广州
feedBackOrgTextView.setText("");
//反馈时间:
feedBackTimeTitleTextView.setText("");
//2018
feedBackTimeTextView.setText("");
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
//guangldong
//提交
//需求豆腐饭d
//需求管理模块
//企业管理平台
//|
//故障及问题
//反馈人
//张三
//|
//13343
//|
//广州
//反馈时间:
//2018
//
//
//
//
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

