import java.util.ArrayList;
import java.util.HashMap;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class XxxAdapter extends BaseAdapter {
	public ArrayList<HashMap<String, String>> dataSource;
	private LayoutInflater inflater;

	public XxxAdapter(Context context) {
		this.inflater = LayoutInflater.from(context);
		dataSource = new ArrayList<HashMap<String, String>>();
	}

	public int getCount() {
		return dataSource.size();
	}

	public Object getItem(int position) {
		return dataSource.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.text_first_char_hint = (TextView) convertView
					.findViewById(R.id.text_first_char_hint);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.content.setText(String.valueOf(dataSource.get(position).get(
				"content")));
		// 顶部隐藏条
		int idx = position - 1;
		// 判断前后Item是否匹配，如果不匹配则设置并显示，匹配则取消
		char previewChar = idx >= 0 ? String.valueOf(
				dataSource.get(idx).get("hint")).charAt(0) : ' ';
		char currentChar = String.valueOf(dataSource.get(position).get("hint"))
				.charAt(0);
		// 将小写字符转换为大写字符
		char newPreviewChar = Character.toUpperCase(previewChar);
		char newCurrentChar = Character.toUpperCase(currentChar);
		if (newCurrentChar != newPreviewChar) {
			holder.text_first_char_hint.setVisibility(View.VISIBLE);
			holder.text_first_char_hint.setText(String.valueOf(newCurrentChar));
		} else {
			// 此段代码不可缺：实例化一个CurrentView后，会被多次赋值并且只有最后一次赋值的position是正确
			holder.text_first_char_hint.setVisibility(View.GONE);
		}
		return convertView;
	}

	public final class ViewHolder {
		public TextView text_first_char_hint;
		public TextView content;
	}
}
