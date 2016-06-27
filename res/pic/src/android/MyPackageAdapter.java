import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
public class MyPackageAdapter extends BaseAdapter {
	private static final String TAG = "MyPackageAdapter";
	public List<MyPackageAdapterBean> list;
	private Context mContext;
	public MyPackageAdapter(Context context) {
		this.mContext = context;
	}
	public void setList(List<MyPackageAdapterBean> list) {
		this.list = list;
	}
	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.myPackage_item, null);
			viewHolder = new ViewHolder();
//未启用
			viewHolder.packageStatusTextView = (TextView) convertView.findViewById(R.id.packageStatusTextView);
//2013秋冬自驾游
			viewHolder.packageNameTextView = (TextView) convertView.findViewById(R.id.packageNameTextView);
//有效日期:
			viewHolder.dateTitleTextView = (TextView) convertView.findViewById(R.id.dateTitleTextView);
//2013111
			viewHolder.startDateTextView = (TextView) convertView.findViewById(R.id.startDateTextView);
//-
			viewHolder.lineTextView = (TextView) convertView.findViewById(R.id.lineTextView);
//2014
			viewHolder.endDateTextView = (TextView) convertView.findViewById(R.id.endDateTextView);
//
			viewHolder.rightImageView = (ImageView) convertView.findViewById(R.id.rightImageView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		MyPackageAdapterBean adapterbean = null;
	if(list!=null && list.size()>position)
 adapterbean = list.get(position);
		if (adapterbean != null) {
//未启用
viewHolder.packageStatusTextView.setText(adapterbean.packageStatusTextViewValue);
//2013秋冬自驾游
viewHolder.packageNameTextView.setText(adapterbean.packageNameTextViewValue);
//有效日期:
viewHolder.dateTitleTextView.setText(adapterbean.dateTitleTextViewValue);
//2013111
viewHolder.startDateTextView.setText(adapterbean.startDateTextViewValue);
//-
viewHolder.lineTextView.setText(adapterbean.lineTextViewValue);
//2014
viewHolder.endDateTextView.setText(adapterbean.endDateTextViewValue);
//
		}
		return convertView;
	}
	static class ViewHolder {
/**未启用*/
TextView packageStatusTextView;
/**2013秋冬自驾游*/
TextView packageNameTextView;
/**有效日期:*/
TextView dateTitleTextView;
/**2013111*/
TextView startDateTextView;
/**-*/
TextView lineTextView;
/**2014*/
TextView endDateTextView;
/***/
ImageView rightImageView;
	}
public	static class MyPackageAdapterBean {
public String id;
/**未启用*/
		public String packageStatusTextViewValue;
/**2013秋冬自驾游*/
		public String packageNameTextViewValue;
/**有效日期:*/
		public String dateTitleTextViewValue;
/**2013111*/
		public String startDateTextViewValue;
/**-*/
		public String lineTextViewValue;
/**2014*/
		public String endDateTextViewValue;
/***/
		public String rightImageViewUrl;
	}
	}

