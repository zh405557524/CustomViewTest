package com.example.soul.swipecardsview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

/**
 * * @author soul
 *
 * @项目名:SwipeCardsView
 * @包名: com.example.soul.swipecardsview
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2016/8/30 12:12
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setBackground(int resid) {
        getWindow().setBackgroundDrawableResource(resid);
    }

    public void setBackground(Drawable drawable){
        getWindow().setBackgroundDrawable(drawable);
    }


    public int getActionBarsize(){
        TypedValue typedValue = new TypedValue();
        int[] textsizeAttr = {R.attr.actionBarSize};

        return 0;

    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
