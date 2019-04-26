package com.example.mylibrary.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookInType;
import com.example.mylibrary.data.model.BookOfAuthor;

import java.util.List;

/**
 * Dao xử lý dữ liệu với bảng sách
 *
 * @created_by xuan phuoc on 2019-04-26
 */

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addBook(Book book);   // thêm 1 cuốn sách

    @Insert
    @Transaction
    long[] addAllBook(List<Book> book);     // thêm nhiều sách

    @Query("SELECT * FROM books")
    List<Book> getAllBook();     // lấy toàn bộ sách

    @Query("SELECT * FROM books WHERE BookName LIKE :name")
    List<Book> searchBookByName(String name);     // tìm kiếm sách theo tên sách

    @Query(value = "SELECT * FROM authors WHERE AuthorID =:authorId")
    @Transaction
    BookOfAuthor getAllBookOfAuthor(String authorId);     // lấy toàn bộ sách của tác giả

    @Query("SELECT * FROM authors WHERE AuthorName LIKE :authorName")
    @Transaction
    List<BookOfAuthor> searchBookByAuthorName(String authorName);     // tìm kiếm sách theo tên tác giả

    @Query("SELECT * FROM book_type WHERE TypeID =:typeId")
    @Transaction
    BookInType getAllBookInType(String typeId);     // lấy toàn bộ sách cùng thể loại

    @Query("SELECT * FROM book_type WHERE TypeName LIKE :typeName")
    @Transaction
    List<BookInType> searchBookByTypeName(String typeName);     // tìm kiếm sách theo tên thể loại sách

    @Query("SELECT * FROM books WHERE BookID =:bookID LIMIT 1")
    Book getBookByBookID(String bookID);  //   Lấy thông tin sách theo mã sách

}
