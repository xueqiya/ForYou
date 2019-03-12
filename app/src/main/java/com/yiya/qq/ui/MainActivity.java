package com.yiya.qq.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.yiya.qq.viewmodel.MainViewModel;
import com.yiya.qq.R;
import com.yiya.qq.adapter.ViewPagerAdapter;
import com.yiya.qq.base.BaseActivity;
import com.yiya.qq.databinding.ActivityMainBinding;
import com.yiya.qq.ui.home.HomeFragment;
import com.yiya.qq.ui.mine.MineFragment;
import com.yiya.qq.utils.PerfectClickListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private ArrayList<Fragment> fragments;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showContentView();
    }

    @Override
    public void initView() {
        mBaseBinding.back.setVisibility(View.INVISIBLE);
        initFragment();
        initListener();
        bindingView.viewPage.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        bindingView.viewPage.setOffscreenPageLimit(4);
        showFragment(0);
    }

    private void initFragment() {
        fragments = new ArrayList();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        fragments.add(homeFragment);
        fragments.add(mineFragment);
    }

    private void initListener() {
        bindingView.viewPage.addOnPageChangeListener(viewPagerListener);
        bindingView.home.setOnClickListener(clickListener);
        bindingView.mine.setOnClickListener(clickListener);
    }

    private ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            showFragment(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private PerfectClickListener clickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.home:
                    showFragment(0);
                    break;
                case R.id.mine:
                    showFragment(2);
                    break;
            }
        }
    };

    private void showFragment(int i) {
        switch (i) {
            case 0:
                bindingView.viewPage.setCurrentItem(0);
                bindingView.home.callOnClick();
                setTitle("首页");
                break;
            case 1:
                bindingView.viewPage.setCurrentItem(3);
                bindingView.mine.callOnClick();
                setTitle("我的");
                break;
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyUp(keyCode, event);
    }
}
