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
public class DialogFuhaoListAdapter extends BaseAdapter {
	private static final String TAG = "DialogFuhaoListAdapter";
	/** 列表是否滑动中，如果是滑动状态则仅从内存中获取图片，否则开启线程去外存或网络获取图片。 */
	private boolean isScrolling = false;
	public List<DialogFuhaoListAdapterBean> list;
	public ImageLoader mImageLoader;
	private Context mContext;
	int defaultImg = R.drawable.ic_launcher;
	public void setFlagIsScrolling(boolean isScrolling) {
		this.isScrolling = isScrolling;
	}
	public DialogFuhaoListAdapter(Context context) {
		this.mContext = context;
		mImageLoader= ((..Activity)context).mImageLoader;//建议在Acitivty中new 公用  new ImageLoader(context);
	}
	public ImageLoader getImageLoader() {
		return mImageLoader;
	}
	public void setList(List<DialogFuhaoListAdapterBean> list) {
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
					R.layout.dialog_fuhao_list_item, null);
			viewHolder = new ViewHolder();
//副号1
			viewHolder.fuhao_Name_Txt = (TextView) convertView.findViewById(R.id.fuhao_name_txt);
//15818876767
			viewHolder.fuhao_Number_Txt = (TextView) convertView.findViewById(R.id.fuhao_number_txt);
//
			viewHolder.is_Check_Checkbox = (CheckBox) convertView.findViewById(R.id.is_check_checkbox);
 viewHolder.is_Check_Checkbox.setOnClickListener(new View.OnClickListener() {
	                      @Override 
	                      public void onClick(View v) {

 int position=Integer.valueOf(v.getTag().toString());
DialogFuhaoListAdapterBean adapterbean = list.get(position);
	                          CheckBox cb = (CheckBox)v;
adapterbean.is_Check_CheckboxCheckBoxState=cb.isChecked();
}
});
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		DialogFuhaoListAdapterBean adapterbean = null;
	if(list!=null && list.size()>position)
 adapterbean = list.get(position);
		if (adapterbean != null) {
//副号1
viewHolder.fuhao_Name_Txt.setText(adapterbean.fuhao_Name_TxtValue);
//15818876767
viewHolder.fuhao_Number_Txt.setText(adapterbean.fuhao_Number_TxtValue);
//
viewHolder.is_Check_Checkbox.setTag(position);
viewHolder.is_Check_Checkbox.setChecked(adapterbean.is_Check_CheckboxCheckBoxState);
		}
		return convertView;
	}
	static class ViewHolder {
/**副号1*/
TextView fuhao_Name_Txt;
/**15818876767*/
TextView fuhao_Number_Txt;
/***/
CheckBox is_Check_Checkbox;
	}
public	static class DialogFuhaoListAdapterBean {
public String id;
/**副号1*/
		public String fuhao_Name_TxtValue;
/**15818876767*/
		public String fuhao_Number_TxtValue;
/***/
		public boolean is_Check_CheckboxCheckBoxState;
	}
	public  void beanChange(List<DialogFuhaoListAdapterBean> adapterBeans,List<Bean>  dbBeans)
{
	for(Bean dbBean:dbBeans)
{
DialogFuhaoListAdapterBean adapterBean=new DialogFuhaoListAdapterBean();
adapterBean.fuhao_Name_TxtValue=dbBean.getname();
adapterBean.fuhao_Number_TxtValue=dbBean.getnumber();
		adapterBeans.add(adapterBean);
}
}
public void selectAllCheckBox() { // 全选checkBox
    for (int i = 0; i < list.size(); i++) {
DialogFuhaoListAdapterBean adapterbean = list.get(i);
     	adapterbean.is_Check_CheckboxCheckBoxState=true;
   }
     notifyDataSetChanged();
 }
 public void disSelectAllCheckBox() { // 全不选checkBox
     for (int i = 0; i < list.size(); i++) {
DialogFuhaoListAdapterBean adapterbean = list.get(i);
     	adapterbean.is_Check_CheckboxCheckBoxState=false;
    }
   notifyDataSetChanged();
}
 public void switchSelectCheckBox() { // 反选checkBox
  for (int i = 0; i < list.size(); i++) {
DialogFuhaoListAdapterBean adapterbean = list.get(i);
   boolean select =adapterbean.is_Check_CheckboxCheckBoxState;
  adapterbean.is_Check_CheckboxCheckBoxState=(!select);
   }
  notifyDataSetChanged();
 }
	}
