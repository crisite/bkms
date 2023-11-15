package com.puff.bkms.service.impl;

import com.puff.bkms.mapper.BookMapper;
import com.puff.bkms.model.dto.book.BookInsertRequest;
import com.puff.bkms.model.dto.book.BookUpdateRequest;
import com.puff.bkms.model.entity.Book;
import com.puff.bkms.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @projectName: bkms
 * @package: com.puff.bkms.service.impl
 * @className: BookServiceImpl
 * @author: Puff
 * @description: TODO
 * @date: 2023/11/14 下午8:03
 * @version: 1.0
 */
@Service
public class BookServiceImpl implements BookService{

    @Resource
    BookMapper bookMapper;

    @Override
    public void insertBook(BookInsertRequest bookInsertRequest) {
        bookMapper.insertBook(bookInsertRequest);
    }

    @Override
    public void delBook(int id) {
        bookMapper.delBook(id);
    }

    @Override
    public void updateBook(BookUpdateRequest bookUpdateRequest) {
        System.out.println(bookUpdateRequest.getTitle());
        bookMapper.updateBook(bookUpdateRequest);
    }

    @Override
    public Book getBook(int id) {
        return bookMapper.getBook(id);
    }
}
