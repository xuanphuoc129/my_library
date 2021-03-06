package com.example.mylibrary.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Bảng thông tin sách
 *
 * @created_by xuan phuoc on 2019-04-26
 */
@Entity(tableName = "books")
public class Book {

    @PrimaryKey
    @ColumnInfo(name = "BookID")
    @NonNull
    private String BookID;     // mã sách

    @ColumnInfo(name = "BookName")
    private String BookName;     // tên sách

    @ColumnInfo(name = "BookThumbnail")
    private String BookThumbnail; // ảnh sách

    @ColumnInfo(name = "AuthorID")
    @ForeignKey(parentColumns = "AuthorID", childColumns = "AuthorID", entity = Author.class)
    private String AuthorID;     // mã tác giả

    @ColumnInfo(name = "TypeID")
    @ForeignKey(parentColumns = "TypeID", childColumns = "TypeID", entity = BookType.class)
    private String TypeID;     // mã loại sách

    @ColumnInfo(name = "Description")
    private String Description;

    @ColumnInfo(name = "RateNumber")
    private float RateNumber;

    @Embedded(prefix = "_")
    private Author author;

    public Book() {

    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getRateNumber() {
        return RateNumber;
    }

    public void setRateNumber(float rateNumber) {
        RateNumber = rateNumber;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String authorID) {
        AuthorID = authorID;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getBookThumbnail() {
        return BookThumbnail;
    }

    public void setBookThumbnail(String bookThumbnail) {
        BookThumbnail = bookThumbnail;
    }
}
