package com.yiya.qq.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yiya.qq.R;
import com.yiya.qq.base.BaseActivity;
import com.yiya.qq.databinding.ActivityWebBinding;
import com.yiya.qq.utils.L;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/26	10:57
 * description:
 */
public class WebActitvity extends BaseActivity<ActivityWebBinding, WebViewModel> {
    private String title;
    private String url;
    private WebSettings settings;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }

    @Override
    public void initView() {
        webView = bindingView.webView;
        settings = webView.getSettings();
        //支持javascript
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");

        setTitle(title);
        L.d("web地址：" + url);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showContentView();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
