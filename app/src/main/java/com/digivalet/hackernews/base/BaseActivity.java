package com.digivalet.hackernews.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.digivalet.hackernews.utils.ActivityNavigator;


/**
 * Created by Rupesh Saxena
 */

public abstract class BaseActivity<T extends ViewDataBinding, V extends ViewModel> extends AppCompatActivity implements BaseNavigator {

    public T mViewDataBinding;
    public V mViewModel;
    public ActivityNavigator activityNavigator;

    public abstract int getBindingVariable();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract Class<V> getVM();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        performDataBinding();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void init() {
        activityNavigator = ActivityNavigator.getInstance(this);
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(getVM());
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

}

