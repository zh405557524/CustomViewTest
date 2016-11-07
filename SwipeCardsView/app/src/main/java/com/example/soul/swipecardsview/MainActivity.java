package com.example.soul.swipecardsview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btTalkMe = (Button) findViewById(R.id.bt_talkme);

        ListView listview = (ListView) findViewById(R.id.lv_listview);
        ScrollView scrollView = (ScrollView) findViewById(R.id.sv_scrollView);
        mData = new ArrayList<>();

        for (int x = 0; x < 100; x++) {

            TextView textView = new TextView(MainActivity.this);
            textView.setText("fdasdasgasdfdshafdsafsdafsda");
            final int finalX1 = x;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Tag", finalX1 +"被点击了");
                }
            });
            if (x==10){
                View view = View.inflate(MainActivity.this, R.layout.bitton_item, null);
                mData.add(view);
            }else {
                mData.add(textView);
            }
        }

        MainAdpater mainAdpater = new MainAdpater();
        listview.setAdapter(mainAdpater);
        setListViewHeightBasedOnChildren(listview);
        scrollView.smoothScrollTo(   0,0);


        btTalkMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReactNatiiveActivity.class));
            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        listView.setLayoutParams(params);
    }

    private class MainAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = mData.get(position);
            return view;
        }
    }
}
