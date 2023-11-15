package com.puff.bkms.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Puff
 * @date: 2023/11/15 上午1:22
 */
@Data
public class BookVO implements Serializable {

    private int id;

    private String title;

    private String author;

    private String ISBN;

    private Date publishedDate;
    private static final long serialVersionUID = 1L;
}

