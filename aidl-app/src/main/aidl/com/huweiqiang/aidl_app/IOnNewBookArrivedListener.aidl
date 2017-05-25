// IOnNewBookArrivedListener.aidl
package com.huweiqiang.aidl_app;

// Declare any non-default types here with import statements
import com.huweiqiang.aidl_app.Book;

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book newBook);
}
