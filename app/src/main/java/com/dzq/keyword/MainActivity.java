package com.dzq.keyword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dzq.keyword.widget.KeyWordLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    int canUserScreenWidth;
    int rightMargin;
    int padding7dp2px;
    List<String> keyArray = new ArrayList<>();
    @Bind(R.id.lay_keywords2)
    KeyWordLayout layKeywords2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        canUserScreenWidth = CommonUtil.getScreenWidth(this) - CommonUtil.dip2px(this, 27);
        rightMargin = CommonUtil.dip2px(this, 13);// 间距
        padding7dp2px = CommonUtil.dip2px(this, 7); // textview 内部 padding
        keyArray.add("111111111111111");
        keyArray.add("2222");
        keyArray.add("3333");
        keyArray.add("4444");
        keyArray.add("5555");
        keyArray.add("6666");
        keyArray.add("7777");
        keyArray.add("8888");
        keyArray.add("9999");
        keyArray.add("0000");

        layKeywords2.setCanUserScreenWidth(canUserScreenWidth);
        layKeywords2.setItemMarginHorizontal(rightMargin);
        layKeywords2.setItemMarginVertical(CommonUtil.dip2px(this, 13));
        layKeywords2.setItemPadding(padding7dp2px, padding7dp2px, padding7dp2px, padding7dp2px);
        layKeywords2.setKeywordsData(keyArray);
        layKeywords2.setKeyWordLayoutItemOnClickListener(new KeyWordLayout.KeyWordLayoutItemOnClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
