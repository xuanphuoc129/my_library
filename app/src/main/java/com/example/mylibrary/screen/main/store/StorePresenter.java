package com.example.mylibrary.screen.main.store;

/**
 * Presenter cho màn hình Store
 */
public class StorePresenter implements IStoreContract.IPresenter {
    private static final String TAG = StorePresenter.class.getName();

    private IStoreContract.IView mView;

    public StorePresenter(IStoreContract.IView view) {
        mView = view;
    }
}
