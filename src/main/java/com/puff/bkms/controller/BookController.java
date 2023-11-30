package com.puff.bkms.controller;

import com.github.pagehelper.PageInfo;
import com.puff.bkms.annotation.OptLog;
import com.puff.bkms.common.BaseResponse;
import com.puff.bkms.common.ErrorCode;
import com.puff.bkms.common.ResultUtils;
import com.puff.bkms.exception.ThrowUtils;
import com.puff.bkms.model.dto.book.BookInsertRequest;
import com.puff.bkms.model.dto.book.BookUpdateRequest;
import com.puff.bkms.model.entity.Book;
import com.puff.bkms.model.dto.book.BookQueryRequest;
import com.puff.bkms.model.enums.OperationEnum;
import com.puff.bkms.model.vo.BookVO;
import com.puff.bkms.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书管理
 *
 * @author: Puff
 * @date: 2023/11/14 下午8:13
 */
@Api(tags = "书籍模块")
@RestController
@RequestMapping("/api")
public class BookController {
    private static final String ISBN = "ISBN";
    @Resource
    BookService bookService;

    @ApiOperation(value = "添加书籍")
    @PostMapping("/books/insert")
    @PreAuthorize("hasAuthority('sys:admin')")
    public BaseResponse<String> insertBook(@RequestBody BookInsertRequest bookInsertRequest) {
        bookService.insertBook(bookInsertRequest);
        return ResultUtils.success(null, "insert success");
    }

    @ApiOperation(value = "删除书籍")
    @DeleteMapping("/books/delete/{id}")
    @PreAuthorize("hasAuthority('sys:admin')")
    public BaseResponse<String> deleteBook(@PathVariable("id")int id) {
        bookService.delBook(id);
        return ResultUtils.success(null, "delete success");
    }

    @OptLog(value = OperationEnum.UPDATE_TYPE)
    @ApiOperation(value = "修改书籍信息")
    @PostMapping("/books/update")
    @PreAuthorize("hasAuthority('sys:admin')")
    public BaseResponse<String> updateBook(@RequestBody BookUpdateRequest bookUpdateRequest) {
        bookService.updateBook(bookUpdateRequest);
        return ResultUtils.success(null, "update success");
    }

    @ApiOperation(value = "根据id查询书籍")
    @GetMapping("/books/get/{id}")
    public BaseResponse<Book> getBook(@PathVariable("id") int id) {
        Book book = bookService.getBook(id);
        return ResultUtils.success(book);
    }

    /**
     * 分页查询图书列表
     *
     * @param bookQueryRequest:
     * @return BaseResponse<BookVO>
     */
    @ApiOperation(value = "分页查询书籍列表")
    @PostMapping("/books")
    public BaseResponse<List<BookVO>> listBookVOByPage(@RequestBody BookQueryRequest bookQueryRequest) {
        long size = bookQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        PageInfo<BookVO> pageInfo;
        /*
         * 根据ISBN精确查询
         * 按理来说应该用正则表达式来精确匹配
         * 但是模拟的ISBN是乱的
         */
        String searchText = bookQueryRequest.getSearchText();
        if(searchText != null && searchText.length() > 5 && ISBN.equals(searchText.substring(0,4))) {
            pageInfo = bookService.getBookByISBN(bookQueryRequest);
        } else {
            System.out.println(bookQueryRequest);
            pageInfo = bookService.listBookVOByPage(bookQueryRequest);
        }
        return ResultUtils.success(pageInfo.getList());
    }
}
