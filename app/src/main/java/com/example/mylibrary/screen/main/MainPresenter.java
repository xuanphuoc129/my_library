package com.example.mylibrary.screen.main;

/**
* Presenter cho màn hình chính
* @created_by xuan phuoc on 2019-04-27
*/
public class MainPresenter implements MainContract.IPresenter {

    private static final String TAG = "MainPresenter";

    private MainContract.IView mView;

    public MainPresenter(MainContract.IView view) {
        mView = view;
    }


}
