package com.puff.bkms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.puff.bkms.model.dto.book.BookInsertRequest;
import com.puff.bkms.model.dto.book.BookQueryRequest;
import com.puff.bkms.model.dto.book.BookUpdateRequest;
import com.puff.bkms.model.entity.Book;
import com.puff.bkms.model.vo.BookVO;

public interface BookService {

    void insertBook(BookInsertRequest bookInsertRequest);

    void delBook(int id);

    void updateBook(BookUpdateRequest bookUpdateRequest);

    Book getBook(int id);

    PageInfo<BookVO> getBookByISBN(BookQueryRequest bookQueryRequest);

    PageInfo<BookVO> listBookVOByPage(BookQueryRequest bookQueryRequest);
}
