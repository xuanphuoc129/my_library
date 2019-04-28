package com.example.mylibrary.screen.main.book;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.mylibrary.data.bookrepository.BookRepository;
import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookInType;

import java.util.List;

/**
* Presenter cho màn hình sách
* @created_by xuan phuoc on 2019-04-27
*/
public class BookPresenter implements IBookContract.IPresenter {

    private static final String TAG = "BookPresenter";

    private IBookContract.IView mView;

    public BookPresenter(IBookContract.IView mView) {
        this.mView = mView;
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-27
    */
    @SuppressLint("StaticFieldLeak")
    @Override
    public void onLoadBooks() {
        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                return BookRepository.getInstance().getBookPopular();
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                super.onPostExecute(books);
                if (books != null && books.size() > 0) {
                    mView.setBookDatas(books);
                }else {
                    mView.showError();
                }
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onLoadTypeBooks() {
        new AsyncTask<Void, Void, List<BookInType>>() {

            @Override
            protected List<BookInType> doInBackground(Void... voids) {
                return BookRepository.getInstance().getBookInTypePreview();
            }

            @Override
            protected void onPostExecute(List<BookInType> books) {
                super.onPostExecute(books);
                if (books != null && books.size() > 0) {
                    mView.setTypeBooks(books);
                }else {
                    mView.showError();
                }
            }
        }.execute();
    }
}
