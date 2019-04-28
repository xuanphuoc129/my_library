package com.example.mylibrary.data.bookrepository;

import com.example.mylibrary.data.DatabaseService;
import com.example.mylibrary.data.dao.BookDao;
import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookInType;
import com.example.mylibrary.data.model.BookOfAuthor;
import com.example.mylibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Kho lưu trữ sách và xử lý nghiệp vụ cho đối tượng sách
 *
 * @created_by xuan phuoc on 2019-04-26
 */
public class BookRepository implements IBookDataSource {

    private static BookRepository ourInstance = null;

    private HashMap<String, Book> mBookDataCache;

    private boolean isFullBookInDatabase = false;

    private BookDao mBookDao;

    public static BookRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new BookRepository();
        }
        return ourInstance;
    }

    private BookRepository() {
        mBookDataCache = new LinkedHashMap<>();
        mBookDao = DatabaseService.getInstance().getBookDao();
    }

    /**
     * Thêm dữ liệu vào cache
     *
     * @created_by xuan phuoc on 2019-04-26
     */
    private void addCache(Book book) {
        try {
            mBookDataCache.put(book.getBookID(), book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lấy dữ liệu trong cache
     *
     * @created_by xuan phuoc on 2019-04-26
     */
    private Book getDataInCache(String id) {
        try {
            if (mBookDataCache.size() > 0) {
                return mBookDataCache.get(id);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Xoá dữ liệu cache
     *
     * @created_by xuan phuoc on 2019-04-26
     */
    private void clearCache() {
        mBookDataCache.clear();
    }

    /**
     * Chức năng: thêm mới 1 cuốn sách
     *
     * @param book - thông tin sách
     * @created_by xuan phuoc on 2019-04-27
     */
    @Override
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }

        try {
            book.setBookID(UUID.randomUUID().toString());
            long newId = mBookDao.addBook(book);
            if (newId > 0) {
                addCache(book);
            }
            return newId > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Chức năng: thêm mới nhiều sách
     *
     * @param books - danh sách thông tin sách
     * @created_by xuan phuoc on 2019-04-27
     */
    @Override
    public boolean addBook(List<Book> books) {
        if (books == null) {
            return false;
        }
        try {
            long[] ids = mBookDao.addAllBook(books);
            if (ids != null && ids.length > 0) {
                for (Book book : books) {
                    addCache(book);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Chức năng: Lấy toàn bộ sách
     *
     * @return - danh sách toàn bộ sách trong database
     * @created_by xuanphuoc
     */
    @Override
    public List<Book> getAllBook() {
        try {
            if (isFullBookInDatabase) {
                return new ArrayList<>(mBookDataCache.values());
            } else {
                isFullBookInDatabase = true;
                List<Book> books = mBookDao.getAllBook();
                if (books != null && books.size() > 0) {
                    for (Book book : books) {
                        addCache(book);
                    }
                    return books;
                } else {
                    return new ArrayList<>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Chức năng: Lấy thông tin sách theo mã sách
     *
     * @param bookID - mã sách
     * @return - thông tin sách
     * @created_by xuanphuoc
     */
    @Override
    public Book getBookByBookID(String bookID) {
        if (bookID == null || bookID.trim().isEmpty()) {
            return null;
        }
        try {
            Book book = mBookDataCache.get(bookID);
            if (book != null) {
                return book;
            } else {
                book = mBookDao.getBookByBookID(bookID);
                if (book != null) {
                    addCache(book);
                }
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Chức năng: lấy toàn bộ sách của 1 tác giả
     *
     * @param authorID - mã tác giả
     * @return - danh sách sách của 1 tác giả trong database
     * @created_by xuanphuoc
     */
    @Override
    public BookOfAuthor getAllBookOfAuthor(String authorID) {
        if (CommonUtils.checkNullEmpty(authorID)) {
            return null;
        }
        try {
            return mBookDao.getAllBookOfAuthor(authorID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Chức năng: lấy toàn bộ sách cùng thể loại
     *
     * @param typeID - mã thể loại
     * @return - danh sách sách cùng thể loại trong database
     * @created_by xuanphuoc
     */
    @Override
    public BookInType getAllBookOfType(String typeID) {
        if (CommonUtils.checkNullEmpty(typeID)) {
            return null;
        }
        try {
            return mBookDao.getAllBookInType(typeID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Chức năng: tìm kiếm sách theo tên sách
     *
     * @param name - từ khoá
     * @return - danh sách sách theo từ khoá  trong database
     * @created_by xuanphuoc
     */
    @Override
    public List<Book> searchBookByName(String name) {
        if (CommonUtils.checkNullEmpty(name)) {
            return null;
        }
        try {
            return mBookDao.searchBookByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Chức năng: tìm kiếm sách theo tên tác giả
     *
     * @param authorName - từ khoá
     * @return - danh sách sách theo từ khoá  trong database
     * @created_by xuanphuoc
     */
    @Override
    public List<BookOfAuthor> searchBookByAuthorName(String authorName) {
        if (CommonUtils.checkNullEmpty(authorName)) {
            return null;
        }
        try {
            return mBookDao.searchBookByAuthorName(authorName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Chức năng: tìm kiếm sách theo tên thể loại sách
     *
     * @param typeName - từ khoá
     * @return - danh sách sách theo từ khoá  trong database
     * @created_by xuanphuoc
     */
    @Override
    public List<BookInType> searchBookByTypeName(String typeName) {
        if (CommonUtils.checkNullEmpty(typeName)) {
            return null;
        }
        try {
            return mBookDao.searchBookByTypeName(typeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-28
    */
    @Override
    public List<BookInType> getBookInTypePreview() {
        try {
            return mBookDao.getBookInTypePreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-28
    */
    @Override
    public List<Book> getBookPopular() {
        try {
            return mBookDao.getBookPopular();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
