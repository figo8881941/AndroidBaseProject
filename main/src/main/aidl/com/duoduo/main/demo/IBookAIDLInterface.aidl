// IBookAIDLInterface.aidl
package com.duoduo.main.demo;

// Declare any non-default types here with import statements
import com.duoduo.main.demo.Book;
import com.duoduo.main.demo.IBookListener;

interface IBookAIDLInterface {

    String addBook(in Book book, in IBookListener listener);
}
