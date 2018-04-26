package com.abt.edittextux;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.OverScroller;
import android.widget.Scroller;

/**
 * @描述： @自定义EditText，想实现EditText内容的平滑移动，功能暂时未实现，TODO
 * @作者： @黄卫旗
 * @创建时间： @2018/4/26
 */
@SuppressLint("AppCompatCustomView")
public class CustomEditText extends EditText {

    private static final String TAG = CustomEditText.class.getSimpleName();
    private int mScrollX;
    private int mScrollY;
    private long mLastScroll;
    //protected OverScroller mScroller;
    protected  Scroller mScroller;
    /**
     * orientation of the scrollview
     */
    protected boolean mHorizontal = false;
    private static final int ANIMATED_SCROLL_GAP = 250;
    /*private StrictMode.Span mScrollStrictSpan = null;  // aka "drag"
    private StrictMode.Span mFlingStrictSpan = null;*/

    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingBottom = 0;
    private int mPaddingTop = 0;
    /**
     * 控件的宽高
     */
    private int tempX, tempY;
    private boolean maybeClickEvent;
    private int mStartX, mStartY;
    /**
     * 按下的坐标
     */
    private int mScrollOffsetX;
    private int mScrollOffsetY;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScrollView();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScrollView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initScrollView();
    }

    private void initScrollView() {
        //mScroller = new OverScroller(getContext());
        mScroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventX = (int) event.getX();
        int eventY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = tempX = (int) event.getX();
                mStartY = (int) event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                mScrollOffsetX = eventX - tempX;
                mScrollOffsetY = eventY - tempY;
                tempX = eventX;
                mScroller.startScroll (mStartX, mStartY, mScrollOffsetX, mScrollOffsetY);
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                mScrollOffsetX = eventX - mStartX;
                return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * {@inheritDoc}
     *
     * <p>This version also clamps the scrolling to the bounds of our child.
     */
    @Override
    public void scrollTo(int x, int y) {
        // we rely on the fact the View.scrollBy calls scrollTo.
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            x = clamp(x, getWidth() - mPaddingRight - mPaddingLeft, child.getWidth());
            y = clamp(y, getHeight() - mPaddingBottom - mPaddingTop, child.getHeight());
            if (x != mScrollX || y != mScrollY) {
                super.scrollTo(x, y);
            }
        }
    }

    private int clamp(int n, int my, int child) {
        if (my >= child || n < 0) {
            /* my >= child is this case:
             *                    |--------------- me ---------------|
             *     |------ child ------|
             * or
             *     |--------------- me ---------------|
             *            |------ child ------|
             * or
             *     |--------------- me ---------------|
             *                                  |------ child ------|
             *
             * n < 0 is this case:
             *     |------ me ------|
             *                    |-------- child --------|
             *     |-- mScrollX --|
             */
            return 0;
        }
        if ((my+n) > child) {
            /* this case:
             *                    |------ me ------|
             *     |------ child ------|
             *     |-- mScrollX --|
             */
            return child-my;
        }
        return n;
    }

    /**
     * Like {@link #scrollTo}, but scroll smoothly instead of immediately.
     *
     * @param x the position where to scroll on the X axis
     * @param y the position where to scroll on the Y axis
     */
    public final void smoothScrollTo(int x, int y) {
        smoothScrollBy(x - mScrollX, y - mScrollY);
    }

    private int getChildCount() {
        return 1;
    }

    /**
     * Like {@link View#scrollBy}, but scroll smoothly instead of immediately.
     *
     * @param dx the number of pixels to scroll by on the X axis
     * @param dy the number of pixels to scroll by on the Y axis
     */
    public final void smoothScrollBy(int dx, int dy) {
        if (getChildCount() == 0) {
            // Nothing to do.
            return;
        }
        long duration = AnimationUtils.currentAnimationTimeMillis() - mLastScroll;
        if (duration > ANIMATED_SCROLL_GAP) {
            if (mHorizontal) {
                final int width = getWidth() - mPaddingRight - mPaddingLeft;
                final int right = getChildAt(0).getWidth();
                final int maxX = Math.max(0, right - width);
                final int scrollX = mScrollX;
                dx = Math.max(0, Math.min(scrollX + dx, maxX)) - scrollX;
                mScroller.startScroll(scrollX, mScrollY, dx, 0);
            } else {
                final int height = getHeight() - mPaddingBottom - mPaddingTop;
                final int bottom = getChildAt(0).getHeight();
                final int maxY = Math.max(0, bottom - height);
                final int scrollY = mScrollY;
                dy = Math.max(0, Math.min(scrollY + dy, maxY)) - scrollY;
                mScroller.startScroll(mScrollX, scrollY, 0, dy);
            }
            invalidate();
        } else {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
                /*if (mFlingStrictSpan != null) {
                    mFlingStrictSpan.finish();
                    mFlingStrictSpan = null;
                }*/
            }
            scrollBy(dx, dy);
        }
        mLastScroll = AnimationUtils.currentAnimationTimeMillis();
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY,
                                  boolean clampedX, boolean clampedY) {
        // Treat animating scrolls differently; see #computeScroll() for why.
        if (!mScroller.isFinished()) {
            mScrollX = scrollX;
            mScrollY = scrollY;
            //invalidateParentIfNeeded();
            if (mHorizontal && clampedX) {
                //mScroller.springBack(mScrollX, mScrollY, 0, getScrollRange(), 0, 0);
            } else if (!mHorizontal && clampedY) {
                //mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange());
            }
        } else {
            super.scrollTo(scrollX, scrollY);
        }
        awakenScrollBars();
    }

    private int getScrollRange() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            if (mHorizontal) {
                scrollRange = Math.max(0,
                        child.getWidth() - (getWidth() - mPaddingRight - mPaddingLeft));
            } else {
                scrollRange = Math.max(0,
                        child.getHeight() - (getHeight() - mPaddingBottom - mPaddingTop));
            }
        }
        return scrollRange;
    }

    private View getChildAt(int i) {
        return this;
    }

}
