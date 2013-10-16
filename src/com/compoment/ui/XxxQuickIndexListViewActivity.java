import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class XxxQuickIndexListViewActivity extends Activity implements
		OnScrollListener {

	private XxxAdapter adapter;
	private Handler handler;
	private ListView list;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quickindex_listview);

		list = (ListView) this.findViewById(R.id.listview); // 联系人ListView

		adapter = new XxxAdapter(this);

		list.setAdapter(adapter);// 将数据适配器与Activity进行绑定
		list.setOnScrollListener(this);

	}


	handler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case SHOW_LOCKER:

				break;
			default:
				super.handleMessage(msg);

			}

		}
	};

	private class DisapearThread implements Runnable {
		public void run() {

		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	public void onDestroy() {
		super.onDestroy();

	}

}
