package com.puff.bkms.model.dto.book;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/14 下午8:53
 */
@Data
public class BookInsertRequest implements Serializable {
    private String title;

    private String author;

    private String ISBN;

    private Date publishedDate;

    private String category;

    private String description;

    private static final long serialVersionUID = 1L;
}
