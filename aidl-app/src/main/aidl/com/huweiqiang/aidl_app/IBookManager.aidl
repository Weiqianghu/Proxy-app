// IBookManager.aidl
package com.huweiqiang.aidl_app;

// Declare any non-default types here with import statements

import com.huweiqiang.aidl_app.Book;
import com.huweiqiang.aidl_app.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}
