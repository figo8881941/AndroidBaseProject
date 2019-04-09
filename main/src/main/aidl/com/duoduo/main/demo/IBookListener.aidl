// IBookListener.aidl
package com.duoduo.main.demo;

// Declare any non-default types here with import statements
import com.duoduo.main.demo.Book;

interface IBookListener {
    void addBookFinish(in Book book, in IBookListener listener);
}
