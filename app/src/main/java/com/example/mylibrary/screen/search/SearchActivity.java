package com.example.mylibrary.screen.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mylibrary.R;

/**
 * Màn hình Search
 */
public class SearchActivity extends AppCompatActivity implements ISearchContract.IView {
    private static final String TAG = SearchActivity.class.getName();
    private ISearchContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mPresenter = new SearchPresenter(this);
    }
}
