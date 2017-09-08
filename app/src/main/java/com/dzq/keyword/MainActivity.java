package com.dzq.keyword;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.lay_keywords)
    LinearLayout layKeywords;

    int canUserScreenWidth;
    int rightMargin;
    int padding7dp2px;
    List<String> keyArray = new ArrayList<>();


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
        SetKeywordsData();
    }

    private void SetKeywordsData() {
        if (keyArray == null) return;
        layKeywords.removeAllViews();
        LinearLayout layKeywordsContent1 = null;
        LinearLayout layKeywordsContent2 = null;
        boolean flag = true;
        int width = 0;

        for (int i = 0; i < keyArray.size(); i++) {
            TextView textView = new TextView(this);
            textView.setPadding(padding7dp2px, padding7dp2px, padding7dp2px, padding7dp2px);
            textView.setTag(i);
            textView.setText(keyArray.get(i));
            textView.setBackgroundResource(R.drawable.bg_keywords_selector);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, view.getTag().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            int textNeedsLength = getTextWidth(textView) + padding7dp2px * 2;
            width = width + textNeedsLength + rightMargin;
            if (flag) {
                if (canUserScreenWidth >= width) {
                    layKeywordsContent1 = addLayChild(layKeywordsContent1, 0);
                    layKeywordsContent1.addView(textView);
                } else {
                    layKeywordsContent2 = addLayChild(layKeywordsContent2, 15);
                    width = textNeedsLength + rightMargin;
                    layKeywordsContent2.addView(textView);
                    flag = false;
                }
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView
                        .getLayoutParams();
                layoutParams.rightMargin = rightMargin;
            } else {

                if (canUserScreenWidth >= width) {
                    layKeywordsContent2 = addLayChild(layKeywordsContent2, 15);
                    layKeywordsContent2.addView(textView);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView
                            .getLayoutParams();
                    layoutParams.rightMargin = rightMargin;
                } else return;
            }

        }


    }

    private LinearLayout addLayChild(LinearLayout layChild, int topMarginDp) {
        if (layChild == null) {
            layChild = new LinearLayout(this);
            layKeywords.addView(layChild);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layChild
                .getLayoutParams();
        layoutParams.topMargin = CommonUtil.dip2px(this, topMarginDp);
        return layChild;
    }

    public int getTextWidth(TextView textView) {
        Paint paint = textView.getPaint();
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }

}
