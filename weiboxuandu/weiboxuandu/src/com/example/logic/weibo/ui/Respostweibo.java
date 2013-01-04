package com.example.logic.weibo.ui;


import java.io.IOException;

import com.example.R;
import com.example.ui.Login;
import com.example.ui.logic.main.TaskRepostWeibo;
import com.example.util.WeiboUtil;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.RequestListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Respostweibo extends Activity {

	EditText etrespostcon;
	Button btsend;
	ImageView btback;
	String sid = null;

	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("---------------------RepostWeibo-----------------------");
		super.onCreate(savedInstanceState);
		requestWindowFeature(getWindow().FEATURE_NO_TITLE);
		setContentView(R.layout.respostweibo);
		//头标题布局
		View view = this.findViewById(R.id.title_res);
		TextView titleview = (TextView) view.findViewById(R.id.tvinfo);
		titleview.setText(R.string.resopnse_page);
		btsend = (Button) view.findViewById(R.id.title_bt_right);
		btback = (ImageView) view.findViewById(R.id.title_bt_left);
		btback.setImageResource(R.drawable.title_back);
		etrespostcon = (EditText) this.findViewById(R.id.etResReason);

		Intent intent = getIntent();
		String sttext = intent.getExtras().getString("status");
		sid = intent.getExtras().getString("sid");
		etrespostcon.setText(sttext);

		btsend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String status = etrespostcon.getText().toString();
				//调用转发微博的方法
				//boolean isok = WeiboUtil.Repostweibo(Respostweibo.this, status,sid);
				TaskRepostWeibo repo = new TaskRepostWeibo(Login.accessToken);
				//StatusesAPI statusApi = new StatusesAPI(Login.accessToken);
				//statusApi.repost(sid, status,  , Respostweibo.this);
				repo.Repostweibo(status, Long.parseLong(sid));
				
				
				//if (isok) {//如果转发成功
				Toast.makeText(Respostweibo.this, R.string.new_response_succeed, 3000).show();
				Respostweibo.this.finish();
				//} else {
					//失败
				//	Toast.makeText(Respostweibo.this,
				//			R.string.new_response_fail, 3000).show();
				//}
				//*/

			}
		});
		btback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Respostweibo.this.finish();
			}
		});
	}
}
