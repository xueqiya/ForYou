package com.yiya.qq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

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

        bindingView.viewPage.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        showFragment(0);
    }

    @Override
    public void initView() {

        initFragment();
        initListener();
    }

    private void initFragment() {
        fragments = new ArrayList();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        fragments.add(homeFragment);
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(mineFragment);
    }

    private void initListener() {
        bindingView.viewPage.addOnPageChangeListener(viewPagerListener);
        bindingView.home.setOnClickListener(clickListener);
        bindingView.market.setOnClickListener(clickListener);
        bindingView.transation.setOnClickListener(clickListener);
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
                case R.id.market:
                    showFragment(1);
                    break;
                case R.id.transation:
                    showFragment(2);
                    break;
                case R.id.mine:
                    showFragment(3);
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
                bindingView.viewPage.setCurrentItem(1);
                bindingView.market.callOnClick();
                setTitle("");
                break;
            case 2:
                bindingView.viewPage.setCurrentItem(2);
                bindingView.transation.callOnClick();
                setTitle("");
                break;
            case 3:
                bindingView.viewPage.setCurrentItem(3);
                bindingView.mine.callOnClick();
                setTitle("我的");
                break;
        }
    }
}
