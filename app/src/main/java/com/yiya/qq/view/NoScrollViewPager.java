package com.yiya.qq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class NoScrollViewPager extends ViewPager {

    private boolean isCanScroll = false;

    private OnViewPagerTouchEvent listener;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置其是否能滑动换页
     *
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.onTouchDown();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.onTouchUp();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (listener != null) {
                    listener.onTouchMove();
                }
                break;
            default:
                break;
        }
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    public void setOnViewPagerTouchEventListener(OnViewPagerTouchEvent l) {
        listener = l;
    }

    public interface OnViewPagerTouchEvent {
        void onTouchDown();

        void onTouchUp();

        void onTouchMove();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);
    }
}
