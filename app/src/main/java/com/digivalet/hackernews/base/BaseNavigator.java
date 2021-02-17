package com.digivalet.hackernews.base;
/**
 * Created by Rupesh Saxena
 */

public interface BaseNavigator {

    default void setStatusBarColor(int color){}

    default void hideStatusBar(){}

    default void showProgressDialog(){}

    default void hideProgressDialog(){}
}
