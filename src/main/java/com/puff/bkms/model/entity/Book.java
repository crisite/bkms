package com.puff.bkms.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: bkms
 * @package: com.puff.bkms.model.entity
 * @className: Books
 * @author: Puff
 * @description: TODO
 * @date: 2023/11/14 下午5:55
 * @version: 1.0
 */
@Data
public class Book implements Serializable {

    private int id;

    private String title;

    private String author;

    private String ISBN;

    private Date publishedDate;

    private String category;

    private Integer status;

    private String description;

    private static final long serialVersionUID = 1L;
}
