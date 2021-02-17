package com.digivalet.hackernews.ui.details;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.digivalet.data.dto.ArticleResponse;
import com.digivalet.hackernews.BR;
import com.digivalet.hackernews.R;
import com.digivalet.hackernews.base.BaseActivity;
import com.digivalet.hackernews.databinding.ActivityDetailsBinding;
import com.digivalet.hackernews.utils.AppConstants;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsVM> implements DetailsNavigator {

    @Override
    public int getBindingVariable() {
        return BR.detailsVM;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public Class<DetailsVM> getVM() {
        return DetailsVM.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init();
    }

    @Override
    public void init() {
        mViewModel.setWebView(((ArticleResponse)getIntent().getSerializableExtra(AppConstants.INTENT_DETAILS)).getUrl());
    }

    @Override
    public void setWebView(String url) {

        /*settings and load url*/
        WebSettings webSettings = mViewDataBinding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mViewDataBinding.webView.loadUrl(url);

        /*set webView chrome client*/
        mViewDataBinding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        /*set key event for back page*/
        mViewDataBinding.webView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getAction() == MotionEvent.ACTION_UP
                    && mViewDataBinding.webView.canGoBack()) {
                mViewDataBinding.webView.goBack();
                return true;
            }
            return false;
        });

        /*set webView custom client */
        mViewDataBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mViewModel.setIsLoading(true);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                mViewDataBinding.webView.loadUrl(url);
                mViewModel.setIsLoading(false);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mViewModel.setIsLoading(false);
            }
        });
    }
}