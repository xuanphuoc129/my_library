package com.example.mylibrary.data.bookrepository;

import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookInType;
import com.example.mylibrary.data.model.BookOfAuthor;

import java.util.List;

public interface IBookDataSource {

    // thêm 1 cuốn sách
    boolean addBook(Book book);

    // thêm nhiều sách
    boolean addBook(List<Book> books);

    // lấy toàn bộ sách
    List<Book> getAllBook();

    // lấy thông tin sách theo mã sách
    Book getBookByBookID(String bookID);

    // lấy toàn bộ sách của 1 tác giả
    BookOfAuthor getAllBookOfAuthor(String authorID);

    // lấy toàn bộ sách cùng thể loại
    BookInType getAllBookOfType(String typeID);

    // tìm kiếm sách theo tên sách
    List<Book> searchBookByName(String name);

    // tìm kiếm sách theo tên tác giả
    List<BookOfAuthor> searchBookByAuthorName(String authorName);

    // tìm kiếm sách theo tên thể loại sách
    List<BookInType> searchBookByTypeName(String typeName);
}
