package com.puff.bkms.model.dto.book;

import com.puff.bkms.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author: Puff
 * @date: 2023/11/15 下午8:43
 */

@Data
public class BookQueryRequest extends PageRequest implements Serializable {

    private String searchText;

    private String category;

    private static final long serialVersionUID = 1L;
}
