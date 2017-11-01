package com.dzq.keyword.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dzq.keyword.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

/**
 * Created by dingzuoqiang on 2017/10/17.
 * Email: 530858106@qq.com
 */

public class KeyWordLayout extends LinearLayout {

    private float textSize = 14;// 字体大小
    private int textColorResId = R.color.colorPrimary;// 字体颜色
    private int itemBgResId = R.drawable.bg_keywords_selector;

    private int itemPaddingTop = 10;// item 的PaddingTop
    private int itemPaddingLeft = 20;//
    private int itemPaddingRight = 20;//
    private int itemPaddingBottom = 10;//
    private int itemMarginHorizontal = 20;// item 间的水平间距
    private int itemMarginVertical = 20;// item 间的垂直间距


    private int canUserScreenWidth;// VIEW 的宽度
    private KeyWordLayoutItemOnClickListener keyWordLayoutItemOnClickListener;
    private List<String> keyArray;

    public KeyWordLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public KeyWordLayout(Context context, AttributeSet attr) {
        super(context, attr);
        init(attr, 0);
    }

    public KeyWordLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        setOrientation(VERTICAL);
        if (attrs == null) return;

    }

    public void setKeywordsData(List<String> keyArray) {
        if (keyArray == null) return;
        this.keyArray = keyArray;
        removeAllViewsInLayout();
        LinearLayout layKeywordsContent1 = null;
        LinearLayout layKeywordsContent2 = null;
        boolean flag = true;
        int width = 0;

        for (int i = 0; i < keyArray.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setPadding(itemPaddingLeft, itemPaddingTop, itemPaddingRight, itemPaddingBottom);
            textView.setTag(i);
            textView.setTextSize(textSize);
            textView.setText(keyArray.get(i));
            try {
                XmlPullParser xrp = getResources().getXml(textColorResId);
                ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
                textView.setTextColor(csl);
            } catch (Exception e) {
                textView.setTextColor(getContext().getResources().getColor(textColorResId));
            }
            textView.setBackgroundResource(itemBgResId);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (keyWordLayoutItemOnClickListener != null)
                        keyWordLayoutItemOnClickListener.onItemClick((int) view.getTag());
                }
            });
            int textNeedsLength = getTextWidth(textView) + itemPaddingLeft + itemPaddingRight;
            width = width + textNeedsLength + itemMarginHorizontal;
            if (flag) {
                if (canUserScreenWidth >= width) {
                    layKeywordsContent1 = addLayChild(layKeywordsContent1, 0);
                    layKeywordsContent1.addView(textView);
                } else {
                    layKeywordsContent2 = addLayChild(layKeywordsContent2, itemMarginVertical);
                    width = textNeedsLength + itemMarginHorizontal;
                    layKeywordsContent2.addView(textView);
                    flag = false;
                }
                LayoutParams layoutParams = (LayoutParams) textView
                        .getLayoutParams();
                layoutParams.rightMargin = itemMarginHorizontal;
            } else {
                if (canUserScreenWidth >= width) {
                    layKeywordsContent2 = addLayChild(layKeywordsContent2, itemMarginVertical);
                    layKeywordsContent2.addView(textView);
                    LayoutParams layoutParams = (LayoutParams) textView
                            .getLayoutParams();
                    layoutParams.rightMargin = itemMarginHorizontal;
                } else return;
            }

        }

    }

    private LinearLayout addLayChild(LinearLayout layChild, int topMargin) {
        if (layChild == null) {
            layChild = new LinearLayout(getContext());
            addView(layChild);
        }
        LayoutParams layoutParams = (LayoutParams) layChild
                .getLayoutParams();
        layoutParams.topMargin = topMargin;
        return layChild;
    }

    private int getTextWidth(TextView textView) {
        Paint paint = textView.getPaint();
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }

    public void setItemPadding(int left, int top, int right, int bottom) {
        itemPaddingLeft = left;
        itemPaddingTop = top;
        itemPaddingRight = right;
        itemPaddingBottom = bottom;
    }

    public void setItemMarginVertical(int itemMarginVertical) {
        this.itemMarginVertical = itemMarginVertical;
    }

    public void setItemMarginHorizontal(int itemMarginHorizontal) {
        this.itemMarginHorizontal = itemMarginHorizontal;
    }

    public void setCanUserScreenWidth(int canUserScreenWidth) {
        this.canUserScreenWidth = canUserScreenWidth;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setTextColorResId(int textColorResId) {
        this.textColorResId = textColorResId;
    }

    public void setItemBgResId(int itemBgResId) {
        this.itemBgResId = itemBgResId;
    }

    public void setItemPaddingTop(int itemPaddingTop) {
        this.itemPaddingTop = itemPaddingTop;
    }

    public void setItemPaddingLeft(int itemPaddingLeft) {
        this.itemPaddingLeft = itemPaddingLeft;
    }

    public void setItemPaddingRight(int itemPaddingRight) {
        this.itemPaddingRight = itemPaddingRight;
    }

    public void setItemPaddingBottom(int itemPaddingBottom) {
        this.itemPaddingBottom = itemPaddingBottom;
    }

    public void setKeyWordLayoutItemOnClickListener(KeyWordLayoutItemOnClickListener keyWordLayoutItemOnClickListener) {
        this.keyWordLayoutItemOnClickListener = keyWordLayoutItemOnClickListener;
    }

    public interface KeyWordLayoutItemOnClickListener {
        public void onItemClick(int position);//
    }

}
