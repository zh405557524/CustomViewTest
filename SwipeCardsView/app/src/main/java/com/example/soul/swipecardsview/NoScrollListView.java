package com.example.soul.swipecardsview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * * @author soul
 *
 * @项目名:SwipeCardsView
 * @包名: com.example.soul.swipecardsview
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/9/1 22:24
 */

public class NoScrollListView extends ListView {
    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
