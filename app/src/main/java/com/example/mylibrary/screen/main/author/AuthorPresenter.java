package com.example.mylibrary.screen.main.author;

/**
 * Presenter cho màn hình Author
 */
public class AuthorPresenter implements IAuthorContract.IPresenter {
    private static final String TAG = AuthorPresenter.class.getName();

    private IAuthorContract.IView mView;

    public AuthorPresenter(IAuthorContract.IView view) {
        mView = view;
    }
}
