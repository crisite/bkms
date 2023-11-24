package com.puff.bkms;

import com.puff.bkms.mapper.BookMapper;
import com.puff.bkms.model.dto.book.BookQueryRequest;
import java.util.List;

import com.puff.bkms.model.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BkmsApplicationTests {

    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {
        System.out.println("yes");
    }

    @Test
    void bookTest() {
        BookQueryRequest bookQueryRequest = new BookQueryRequest();
        bookQueryRequest.setSearchText("泡芙");
        bookQueryRequest.setCategory("");
        bookQueryRequest.setCurrent(1);
        bookQueryRequest.setPageSize(10);
        List<Book> books = bookMapper.queryBook(bookQueryRequest);
        System.out.println(books);
    }

}
