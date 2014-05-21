import android.app.Activity;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
public class LayoutFuhaoList extends Activity implements OnScrollListener {
	Context context;
	public View containView;
String searchText;
LoadingProgressDialog loading ;
/**默认联系号码*/
TextView title;
/***/
ListView fuhao_List;
LayoutFuhaoListAdapter adapter;
public LayoutFuhaoList()
{
}
  @Override
    public void onDestroy() {
        super.onDestroy();
        clean();
 }
	public void clean() {
InputMethodManager m = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
m.hideSoftInputFromWindow(containView.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		containView = null;
//默认联系号码
title=null;
//
fuhao_List=null;
adapter.list.clear();
adapter=null;
}
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
 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
context = this;
View view = init();
this.setContentView(view);
 }
	public View init() {
		if (containView == null) {
			containView = inflateView(R.layout.layout_fuhao_list);
//默认联系号码
title= (TextView) containView.findViewById(R.id.title);
//
fuhao_List= (ListView) containView.findViewById(R.id.fuhao_list);
adapter= new LayoutFuhaoListAdapter(context);
		}
runAsyncTask();
return containView;
}
public void runAsyncTask()
{
	GetNetInfoTask getNetInfoTask=new GetNetInfoTask();
	getNetInfoTask.execute("");
}
	private class GetNetInfoTask extends
			AsyncTask<String, Integer, List<LayoutFuhaoListAdapterBean>> {
List<LayoutFuhaoListAdapterBean> list =new ArrayList();
		@Override
		protected void onPreExecute() {
if (loading == null) {
	loading = new LoadingProgressDialog();
}
loading.showProgressDailg("提示", "加载中。。。", context);
//<
//默认联系号码
//
adapter.setList(list);
fuhao_List.setAdapter(adapter);// 将数据适配器与Activity进行绑定
fuhao_List.setOnScrollListener(LayoutFuhaoList.this);
fuhao_List.setOnItemClickListener(new OnItemClickListener(){
		@Override
		public void onItemClick(AdapterView<?> arg0, View view,int position, long id) {
	}});
		}
		@Override
		protected List<LayoutFuhaoListAdapterBean> doInBackground(String... params) {
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			// 更新进度
		}
		@Override
	      protected void onPostExecute(List<LayoutFuhaoListAdapterBean> list) {
	loading.cancleProgressDialog();
if(list==null || list.size()<1) return;
//默认联系号码
title.setText("");
//
adapter.notifyDataSetChanged();
	      }
		@Override
		protected void onCancelled() {
			super.onCancelled();
           list.clear();
		}
}
	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
}
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
}
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
}
	public void tread() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
			}
		});
		thread.start();
}
}
