package com.example.mylibrary.data;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mylibrary.data.dao.AuthorDao;
import com.example.mylibrary.data.dao.BookDao;
import com.example.mylibrary.data.dao.BookTypeDao;
import com.example.mylibrary.data.model.Author;
import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookType;
import com.example.mylibrary.data.model.CreateDataEvent;
import com.example.mylibrary.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Class quản lý dữ liệu
 * Gọi đến các interface Dao thông qua class này
 *
 * @created_by xuan phuoc on 2019-04-26
 */
public class DatabaseService {
    private static final String TAG = "DatabaseService";
    // tên cơ sở dữ liệu
    private static final String DATABASE_NAME = "my_library";

    private static DatabaseService ourInstance = null;

    private AppDatabase mAppDatabase;

    public static DatabaseService getInstance() {
        if (ourInstance == null) {
            ourInstance = new DatabaseService();
        }
        return ourInstance;
    }

    /**
     * Chức năng: Khỏi tạo và mở kết nối đến database
     *
     * @param context - context
     * @created_by xuan phuoc on 2019-04-26
     */
    public void createDatabase(final Context context) {
        mAppDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        createDefaultValue(context);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }
                })
                .build();
    }

    /**
     * Chức năng: Khỏi tạo giá trị mặc định cho bảng
     *
     * @param context - context
     * @created_by xuan phuoc on 2019-04-26
     */
    @SuppressLint("StaticFieldLeak")
    private void createDefaultValue(final Context context) {
        final Gson gson = new Gson();
        try {

            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {
                    // thêm thể loại sách
                    Log.d(TAG, "doInBackground: ");

                    String typeBookString = CommonUtils.loadJSONFromAsset(context, "book_type.json");
                    List<BookType> bookTypeList = new ArrayList<>();

                    if (!CommonUtils.checkNullEmpty(typeBookString)) {
                        Log.d(TAG, "doInBackground: " + typeBookString);

                        bookTypeList = gson.fromJson(typeBookString, new TypeToken<List<BookType>>() {
                        }.getType());

                        getBookTypeDao().addAllBookType(bookTypeList);
                    }

                    // thêm tác giả
                    String authorString = CommonUtils.loadJSONFromAsset(context, "authors.json");
                    List<Author> authorList = new ArrayList<>();
                    if (!CommonUtils.checkNullEmpty(authorString)) {
                        Log.d(TAG, "doInBackground: " + authorString);
                        authorList = gson.fromJson(authorString, new TypeToken<List<Author>>() {
                        }.getType());
                        getAuthorDao().addAllAuthor(authorList);
                    }

                    // thêm sách
                    String bookString = CommonUtils.loadJSONFromAsset(context, "books.json");
                    if (!CommonUtils.checkNullEmpty(bookString)) {
                        List<Book> bookList = gson.fromJson(bookString, new TypeToken<List<Book>>() {
                        }.getType());

                        if (bookList != null) {
                            Log.d(TAG, "doInBackground: book size:" + bookList.size());
                            for (Book book : bookList) {
                                int typeIndex = 0;
                                int authorIndex = 0;
                                if (bookTypeList != null) {
                                    Log.d(TAG, "doInBackground: book type size:" + bookTypeList.size());

                                    typeIndex = CommonUtils.randomInt(0, bookTypeList.size());
                                    book.setTypeID(bookTypeList.get(typeIndex).getTypeID());
                                }
                                if (authorList != null) {
                                    Log.d(TAG, "doInBackground: author size:" + authorList.size());

                                    authorIndex = CommonUtils.randomInt(0, authorList.size());
                                    book.setAuthorID(authorList.get(authorIndex).getAuthorID());
                                }
                            }
                            getBookDao().addAllBook(bookList);
                        }
                    }

                    return null;

                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    EventBus.getDefault().post(new CreateDataEvent());
                }
            }.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DatabaseService() {

    }

    public BookDao getBookDao() {
        return mAppDatabase.getBookDao();
    }

    public AuthorDao getAuthorDao() {
        return mAppDatabase.getAuthorDao();
    }

    public BookTypeDao getBookTypeDao() {
        return mAppDatabase.getBookTypeDao();
    }
}
