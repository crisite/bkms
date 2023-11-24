package com.puff.bkms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.puff.bkms.mapper.BookMapper;
import com.puff.bkms.model.dto.book.BookInsertRequest;
import com.puff.bkms.model.dto.book.BookQueryRequest;
import com.puff.bkms.model.dto.book.BookUpdateRequest;
import com.puff.bkms.model.entity.Book;
import com.puff.bkms.model.vo.BookVO;
import com.puff.bkms.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
@Slf4j
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

    @Override
    public PageInfo<BookVO> getBookByISBN(BookQueryRequest bookQueryRequest) {
        String searchText = bookQueryRequest.getSearchText();
        int current = bookQueryRequest.getCurrent();
        int pageSize = bookQueryRequest.getPageSize();

        PageHelper.startPage(current, pageSize);
        BookVO bookVO = bookMapper.getBookByISBN(searchText);
        ArrayList<BookVO> bookVOS = new ArrayList<>();
        bookVOS.add(bookVO);
        return new PageInfo<>(bookVOS);
    }

    @Override
    public PageInfo<BookVO> listBookVOByPage(BookQueryRequest bookQueryRequest) {
        int current = bookQueryRequest.getCurrent();
        int pageSize = bookQueryRequest.getPageSize();

        List<BookVO> bookVOS = new ArrayList<>();
        // 先清除PageHelper缓存
        PageHelper.clearPage();
        // 设定分页参数
        PageHelper.startPage(current, pageSize);
        // 执行查询语句
        List<Book> books = bookMapper.queryBook(bookQueryRequest);
        books.stream().forEach( book -> {
            BookVO res = new BookVO();
            BeanUtils.copyProperties(book, res);
            bookVOS.add(res);
        });
        // PageInfo封装查询结果
        return new PageInfo<BookVO>(bookVOS);
    }
}
