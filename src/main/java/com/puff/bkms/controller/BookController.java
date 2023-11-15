package com.puff.bkms.controller;

import com.puff.bkms.common.BaseResponse;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.model.dto.book.BookInsertRequest;
import com.puff.bkms.model.dto.book.BookUpdateRequest;
import com.puff.bkms.model.entity.Book;
import com.puff.bkms.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @projectName: bkms
 * @package: com.puff.bkms.controller
 * @className: BookController
 * @author: Puff
 * @description: TODO
 * @date: 2023/11/14 下午8:13
 * @version: 1.0
 */
@RestController
@RequestMapping("/api")
public class BookController {
    @Resource
    BookService bookService;

    @PostMapping("/book/insert")
    public BaseResponse<String> insertBook(@RequestBody BookInsertRequest bookInsertRequest) {
        bookService.insertBook(bookInsertRequest);
        return ResultUtils.success("");
    }

    @DeleteMapping("/book/delete/{id}")
    public BaseResponse<String> deleteBook(@PathVariable("id")int id) {
        bookService.delBook(id);
        return ResultUtils.success("");
    }

    @PostMapping("/book/update")
    public BaseResponse<String> updateBook(@RequestBody BookUpdateRequest bookUpdateRequest) {
        bookService.updateBook(bookUpdateRequest);
        return ResultUtils.success("");
    }

    @GetMapping("/book/get/{id}")
    public BaseResponse<Book> getBook(@PathVariable("id") int id) {
        Book book = bookService.getBook(id);
        return ResultUtils.success(book);
    }
}
