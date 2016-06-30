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
public class MyCanAddPackageAdapter extends BaseAdapter {
	private static final String TAG = "MyCanAddPackageAdapter";
	public List<MyCanAddPackageAdapterBean> list;
	private Context mContext;
	public MyCanAddPackageAdapter(Context context) {
		this.mContext = context;
	}
	public void setList(List<MyCanAddPackageAdapterBean> list) {
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
					R.layout.myCanAddPackage_item, null);
			viewHolder = new ViewHolder();
//60
			viewHolder.packageMoneyTextView = (TextView) convertView.findViewById(R.id.packageMoneyTextView);
//周期:
			viewHolder.packagePeriodTitleTextView = (TextView) convertView.findViewById(R.id.packagePeriodTitleTextView);
//6个月
			viewHolder.packagePeriodValueTextView = (TextView) convertView.findViewById(R.id.packagePeriodValueTextView);
//
			viewHolder.rightImageView = (ImageView) convertView.findViewById(R.id.rightImageView);
//业务包名称
			viewHolder.packageNameCheckBox = (CheckBox) convertView.findViewById(R.id.packageNameCheckBox);
 viewHolder.packageNameCheckBox.setOnClickListener(new View.OnClickListener() {
	                      @Override 
	                      public void onClick(View v) {

 int position=Integer.valueOf(v.getTag().toString());
MyCanAddPackageAdapterBean adapterbean = list.get(position);
	                          CheckBox cb = (CheckBox)v;
adapterbean.packageNameCheckBoxCheckBoxState=cb.isChecked();
}
});
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		MyCanAddPackageAdapterBean adapterbean = null;
	if(list!=null && list.size()>position)
 adapterbean = list.get(position);
		if (adapterbean != null) {
//60
viewHolder.packageMoneyTextView.setText(adapterbean.packageMoneyTextViewValue);
//周期:
viewHolder.packagePeriodTitleTextView.setText(adapterbean.packagePeriodTitleTextViewValue);
//6个月
viewHolder.packagePeriodValueTextView.setText(adapterbean.packagePeriodValueTextViewValue);
//
//业务包名称
viewHolder.packageNameCheckBox.setTag(position);
viewHolder.packageNameCheckBox.setChecked(adapterbean.packageNameCheckBoxCheckBoxState);
		}
		return convertView;
	}
	static class ViewHolder {
/**60*/
TextView packageMoneyTextView;
/**周期:*/
TextView packagePeriodTitleTextView;
/**6个月*/
TextView packagePeriodValueTextView;
/***/
ImageView rightImageView;
/**业务包名称*/
CheckBox packageNameCheckBox;
	}
public	static class MyCanAddPackageAdapterBean {
public String id;
/**60*/
		public String packageMoneyTextViewValue;
/**周期:*/
		public String packagePeriodTitleTextViewValue;
/**6个月*/
		public String packagePeriodValueTextViewValue;
/***/
		public String rightImageViewUrl;
/**业务包名称*/
		public boolean packageNameCheckBoxCheckBoxState;
	}
public void selectAllCheckBox() { // 全选checkBox
    for (int i = 0; i < list.size(); i++) {
MyCanAddPackageAdapterBean adapterbean = list.get(i);
     	adapterbean.packageNameCheckBoxCheckBoxState=true;
   }
     notifyDataSetChanged();
 }
 public void disSelectAllCheckBox() { // 全不选checkBox
     for (int i = 0; i < list.size(); i++) {
MyCanAddPackageAdapterBean adapterbean = list.get(i);
     	adapterbean.packageNameCheckBoxCheckBoxState=false;
    }
   notifyDataSetChanged();
}
 public void switchSelectCheckBox() { // 反选checkBox
  for (int i = 0; i < list.size(); i++) {
MyCanAddPackageAdapterBean adapterbean = list.get(i);
   boolean select =adapterbean.packageNameCheckBoxCheckBoxState;
  adapterbean.packageNameCheckBoxCheckBoxState=(!select);
   }
  notifyDataSetChanged();
 }
	}

