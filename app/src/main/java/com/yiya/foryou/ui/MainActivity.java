package com.yiya.foryou.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yiya.foryou.viewmodel.MainViewModel;
import com.yiya.foryou.R;
import com.yiya.foryou.adapter.ViewPagerAdapter;
import com.yiya.foryou.base.BaseActivity;
import com.yiya.foryou.databinding.ActivityMainBinding;
import com.yiya.foryou.ui.home.HomeFragment;
import com.yiya.foryou.ui.mine.MineFragment;

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
        mBaseBinding.toolBar.setVisibility(View.GONE);
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
        bindingView.navigation.setOnNavigationItemSelectedListener(callOnClick);
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

    private BottomNavigationView.OnNavigationItemSelectedListener callOnClick = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    break;
                case R.id.navigation_knowledgesystem:
                    showFragment(1);
                    break;
            }
            return true;
        }
    };

    private void showFragment(int i) {
        switch (i) {
            case 0:
                bindingView.viewPage.setCurrentItem(0);
                break;
            case 1:
                bindingView.viewPage.setCurrentItem(3);
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
