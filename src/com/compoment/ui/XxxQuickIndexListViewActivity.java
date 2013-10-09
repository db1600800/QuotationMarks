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
	private DisapearThread disapearThread;

	private ListView list;

	private final static int SHOW_LOCKER = 0;
	private Timer lockTimer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quickindex_listview);

		handler = new Handler() {
			public void handleMessage(Message msg) {

				switch (msg.what) {

				case SHOW_LOCKER:

					if (txtOverlay.isShown()) {

						txtOverlay.setVisibility(View.INVISIBLE);

					}

					break;

				default:

					super.handleMessage(msg);

				}

			}
		};
		disapearThread = new DisapearThread();
		list = (ListView) this.findViewById(R.id.listview); // 联系人ListView

		adapter = new XxxAdapter(this);

		list.setAdapter(adapter);// 将数据适配器与Activity进行绑定
		list.setOnScrollListener(this);

	}

	private class DisapearThread implements Runnable {
		public void run() {

		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		txtOverlay.setText(String
				.valueOf(
						adapter.dataSource.get(
								firstVisibleItem + (visibleItemCount >> 1))
								.get("hint")).toUpperCase());
		a_zQuickIndexBar.setFocusItem(txtOverlay.getText().toString());
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.scrollState = scrollState;
		if (scrollState == ListView.OnScrollListener.SCROLL_STATE_IDLE) {
			handler.removeCallbacks(disapearThread);
			// 提示延迟1.5s再消失
			handler.postDelayed(disapearThread, 1500);
		}

	}

	public void onDestroy() {
		super.onDestroy();

	}

	private class LockTimerTask extends TimerTask {

		@Override
		public void run() {

			handler.sendMessage(handler.obtainMessage(SHOW_LOCKER));

		}

	}

}
