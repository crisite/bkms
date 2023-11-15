package com.puff.bkms.model.dto.book;

import lombok.Data;

import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/14 下午10:52
 */
@Data
public class BookUpdateRequest {
    private Integer id;

    private String title;

    private String author;

    private String ISBN;

    private Date publishedDate;

    private String category;

    private Integer status;

    private String description;

    private static final long serialVersionUID = 1L;
}
