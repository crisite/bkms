package com.puff.bkms.service;

import com.github.pagehelper.Page;
import com.puff.bkms.model.dto.book.BookInsertRequest;
import com.puff.bkms.model.dto.book.BookUpdateRequest;
import com.puff.bkms.model.entity.Book;

public interface BookService {

    void insertBook(BookInsertRequest bookInsertRequest);

    void delBook(int id);

    void updateBook(BookUpdateRequest bookUpdateRequest);

    Book getBook(int id);

}
