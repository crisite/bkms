package com.puff.bkms.mapper;

import com.puff.bkms.model.dto.book.BookInsertRequest;
import com.puff.bkms.model.dto.book.BookQueryRequest;
import com.puff.bkms.model.dto.book.BookUpdateRequest;
import com.puff.bkms.model.entity.Book;
import com.puff.bkms.model.vo.BookVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: bkms
 * @package: com.puff.bkms.mapper
 * @className: BookMapper
 * @author: Puff
 * @description: TODO
 * @date: 2023/11/14 下午8:05
 * @version: 1.0
 */
@Mapper
public interface BookMapper {

    void insertBook(BookInsertRequest bookInsertRequest);

    int delBook(int id);

    void updateBook(BookUpdateRequest bookUpdateRequest);

    Book getBook(int id);

    BookVO getBookByISBN(String isbn);

    List<Book> queryBook(BookQueryRequest bookQueryRequest);
}
