package com.example.rssdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

public class ChannelActivity extends Activity {

	Button add_channel;
	Button save_channel;
	Button remove;
	LinearLayout ll;
	ScrollView sv;
	List<EditText> edit_text_list;
	int id;
	String[] urls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel);
		ll = (LinearLayout) findViewById(R.id.channel_list);
		id = 0;

		add_channel = (Button) findViewById(R.id.Add_channel);
		save_channel = (Button) findViewById(R.id.Save_channel);
		edit_text_list = new ArrayList<EditText>();

		EditText et = new EditText(ChannelActivity.this);

		et.setId(id);
		et.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
		et.setSingleLine();
		et.setHint("enter XML address with http:// ahead");
		edit_text_list.add(et);

		remove = new Button(ChannelActivity.this);
		remove.setTag(id);
		remove.setId(id);
		id++;
		remove.setText("remove");
		remove.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
		remove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int id = (Integer) v.getTag();
				//ll.removeView(remove);
				edit_text_list.remove(findViewById(id));
				ll.removeView(findViewById(id));
				ll.removeView(findViewById(id));
				
				id++;
			}
		});

		ll.addView(et);
		ll.addView(remove);
		add_channel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText et = new EditText(ChannelActivity.this);
				et.setId(id);
				et.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						1f));
				et.setSingleLine();
				et.setHint("enter XML address with http:// ahead");
				edit_text_list.add(et);

				remove = new Button(ChannelActivity.this);
				remove.setId(id);
				remove.setTag(id);
				remove.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						int id = (Integer) v.getTag();
						//ll.removeView(remove);
						edit_text_list.remove(findViewById(id));
						ll.removeView(findViewById(id));
						ll.removeView(findViewById(id));
						
						id++;
					}
				});
				
				id++;
				remove.setText("remove");
				remove.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						1f));

				ll.addView(et);
				ll.addView(remove);
			}
		});

		

		save_channel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				List<String> links = new ArrayList<String>();
				for (EditText link : edit_text_list) {
					if (link.getText().toString().length() == 0) {
						continue;
					}
					links.add(link.getText().toString());
				}

				Intent Tabintent = new Intent(ChannelActivity.this,
						TabDisplayActivity.class);
				Tabintent.putExtra("links", (ArrayList<String>) links);
				startActivity(Tabintent);
				// finish();

			}
		});

	}
}
