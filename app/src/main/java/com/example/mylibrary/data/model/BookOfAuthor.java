package com.example.mylibrary.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Relation;

import java.util.List;

@Entity(tableName = "book_of_author")
public class BookOfAuthor {

    @ColumnInfo(name = "AuthorID")
    private String AuthorID; // mã tác giả

    @ColumnInfo(name = "AuthorName")
    private String AuthorName; // tên tác giả

    @Relation(parentColumn = "AuthorID", entityColumn = "AuthorID", entity = Book.class)
    private List<Book> books; // sách cùng tác giả

    public BookOfAuthor() {
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String authorID) {
        AuthorID = authorID;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }
}
