package com.abt.edittextux;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

/**
 * @描述： @自定义ScrollView，想实现EditText内容的平滑移动，功能暂时未实现，TODO
 * @作者： @黄卫旗
 * @创建时间： @2018/4/26
 */
public class CustomScrollView extends ScrollView {

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        if (child instanceof EditText) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isSmoothScrollingEnabled() {
        return super.isSmoothScrollingEnabled();
    }

    @Override
    public void setSmoothScrollingEnabled(boolean smoothScrollingEnabled) {
        super.setSmoothScrollingEnabled(smoothScrollingEnabled);
    }

}
