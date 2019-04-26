package com.example.mylibrary.screen.main;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mylibrary.data.bookrepository.BookRepository;
import com.example.mylibrary.data.model.Book;

import java.util.List;

public class MainPresenter implements MainContract.IPresenter {

    private static final String TAG = "MainPresenter";

    private MainContract.IView mView;

    public MainPresenter(MainContract.IView view) {
        mView = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onLoadBooks() {
        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                return BookRepository.getInstance().getAllBook();
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                super.onPostExecute(books);
                if (books != null && books.size() > 0) {
                    Log.d(TAG, "onPostExecute: " + books.size());
                    for (Book book : books) {
                        Log.d(TAG, "onLoadBooks: " + book.getBookName());
                    }
                }
            }
        }.execute();
    }
}
