package com.example.mylibrary.screen.search;

/**
 * Presenter cho màn hình Search
 */
public class SearchPresenter implements ISearchContract.IPresenter {
    private static final String TAG = SearchPresenter.class.getName();

    private ISearchContract.IView mView;

    public SearchPresenter(ISearchContract.IView view) {
        mView = view;
    }
}
