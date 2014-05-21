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
import android.view.View.OnClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CheckBox;
public class OrderPaywayCoupon implements OnScrollListener {
	Context context;
	public View containView;
String searchText;
public ViewGroup parentViewContain;
public ... parentThis;
LoadingProgressDialog loading ;
/**使用优惠券*/
CheckBox use_Coupon_Checkbox;
/***/
/***/
LinearLayout payway_Coupon_List_Linearlayout;
OrderPaywayCouponAdapter adapter;
public OrderPaywayCoupon(Context context,ViewGroup parentViewContain,... parentThis)
{
this.parentViewContain=parentViewContain;
this.context=context;
this.parentThis=parentThis;
init();
}
	public void clean() {
InputMethodManager m = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
m.hideSoftInputFromWindow(containView.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		containView = null;
//使用优惠券
use_Coupon_Checkbox=null;
//
//
payway_Coupon_List_Linearlayout=null;
adapter.list.clear();
adapter=null;
}
	public View init() {
		if (containView == null) {
			containView = inflateView(R.layout.order_payway_coupon);
//使用优惠券
use_Coupon_Checkbox= (CheckBox) containView.findViewById(R.id.use_coupon_checkbox);
use_Coupon_Checkbox.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
if (use_Coupon_Checkbox.isChecked()) {
 adapter.selectAllCheckBox();
 }else{
 adapter.disSelectAllCheckBox();
}      }
    });
//
//
payway_Coupon_List_Linearlayout= (LinearLayout) containView.findViewById(R.id.payway_coupon_list_linearlayout);
adapter= new OrderPaywayCouponAdapter(context);
		}
parentViewContain.addView(containView);
runAsyncTask();
return containView;
}
public void runAsyncTask()
{
	GetNetInfoTask getNetInfoTask=new GetNetInfoTask();
	getNetInfoTask.execute("");
}
	private class GetNetInfoTask extends
			AsyncTask<String, Integer, List<OrderPaywayCouponAdapterBean>> {
List<OrderPaywayCouponAdapterBean> list =new ArrayList();
		@Override
		protected void onPreExecute() {
if (loading == null) {
	loading = new LoadingProgressDialog();
}
loading.showProgressDailg("提示", "加载中。。。", context);
//使用优惠券
//
//
adapter.setList(list);
		}
		@Override
		protected List<OrderPaywayCouponAdapterBean> doInBackground(String... params) {
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			// 更新进度
		}
		@Override
	      protected void onPostExecute(List<OrderPaywayCouponAdapterBean> list) {
	loading.cancleProgressDialog();
if(list==null || list.size()<1) return;
//使用优惠券
//
adapter.getView(payway_Coupon_List_Linearlayout);
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
