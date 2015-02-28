package com.compoment.addfunction.android;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;


/**
 * 
 * 
 * 
 * 
 */
public class WaitActivity extends Activity {

	private String TAG = WaitActivity.class.getName();
	private Thread mThread;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// -=-=-=开启对话框-=-=-=-
		showWaitProgressDialog("请稍候...");
	
		Bundle extras = this.getIntent().getExtras();

		final String url = extras.getString("url");
		
	
		// 打印log
		
		if (true) {
			Log.i("hobby",url);
		}
		
		final Intent intent = new Intent();
		final Bundle bundle = new Bundle();
		
		Runnable requestRunnable = new Runnable() {

			@Override
			public void run() {

				//发起请求
				HttpClientManager httpClientManager = new HttpClientManager();
				com.example.test.HttpClientManager.NetErrBean netErrBean = httpClientManager.httpGet(url);
				if (netErrBean != null && netErrBean.errorCode.equals("000000")) {//有数据返回
					
				
				
						String jsonStr =netErrBean.returnData;
						
						if(jsonStr==null || jsonStr.equals(""))
						{//返回空字符串
						   return;
						}
							
						try {
							JSONObject jsonObject = new JSONObject(jsonStr);
							
							boolean isSuccess = jsonObject.optBoolean("returnCode");
							String dataJson = jsonObject.getString("returnData");
							if (isSuccess) {// 调用成功

								JSONObject returnData = new JSONObject(dataJson);
								
								
								if (returnData != null
										&& returnData.has("HEAD")) {

									JSONObject HEAD = returnData
											.optJSONObject("HEAD");
									final String HOST_RET_ERR =HEAD
											.optString("HOST_RET_ERR");
									final String HOST_RET_MSG = HEAD
											.optString("HOST_RET_MSG");
									if (!HOST_RET_ERR.equals("000000")) {

										if (HOST_RET_ERR.contains("2001")) {
											//token失效不做提示处理
										} else if (HOST_RET_ERR.contains("050008")) {
										    //  交易代号为 4460140 并且 HOST_RET_ERR（错误响应吗）为“050008”(超时），屏蔽提示，交给具体调用的地方处理。
										} else {
											//1、根据后台返回的错误描述查询数据库对应的转义描述
											//2、如果查询结果为null,则根据"*"去查询通用的错误描述文字信息
									
											//在此提示数据错误描述转义信息
										
										}
									}else
									{//报文头 0000000 表示成功获取内容 
									
										bundle.putString("result", jsonStr);
										
									
									}
								
									//	mHandler.sendEmptyMessage(100);
									
								} else {//没有报文头
									
									//"交易报文出错
								}

							} else {//网关调用失败
							
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										
									}
								});
							}
						} catch (JSONException e) {//json格式出错
							e.printStackTrace();
						}
					
					
				}else if (netErrBean != null && !netErrBean.errorCode.equals("000000")) {//没数据返回
					
					
				}
				
	
				
			
				intent.putExtras(bundle);
				setResult(0, intent);
				finish();// 此处一定要调用finish()方法
			}
		};
		mThread = new Thread(requestRunnable);
		mThread.start();

	}


	

	
	
	
	
	//等待框
	protected WaitProgressDialog progressDialog;
	
	/**
	 * 显示等待进度对话框

	 * 
	 * 
	 * 
	 * @param title
	 */
	public void showWaitProgressDialog(String title) {
		try {
			if (progressDialog == null) {
				progressDialog = WaitProgressDialog.createDialog(this);
			}
			progressDialog.setMessage(title);
			progressDialog.setCancelable(false);
			progressDialog.show();

			progressDialog
					.setOnCancelListener(new DialogInterface.OnCancelListener() {
						@Override
						public void onCancel(DialogInterface arg0) {
							if (progressDialog != null) {
								progressDialog.dismiss();
								progressDialog = null;
							}
						}
					});
			progressDialog.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					if (progressDialog != null) {
						progressDialog.dismiss();
						progressDialog = null;
					}
				}
			});
		} catch (Exception e) {
			Log.d("showProgressDialog", e.toString());
			progressDialog = null;
		}
	}

	/**
	 * 移除等待进度对话框

	 */
	public void removeWaitProgressDialog() {
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
				progressDialog = null;
			} else {

			}
		} catch (Exception e) {
			Log.d("removeProgressDialog", e.toString());
			progressDialog = null;
		}
	}
	
	
}
