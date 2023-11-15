package com.puff.bkms.common;

import com.puff.bkms.constant.SortConstant;
import lombok.Data;

/**
 * @author: Puff
 * @date: 2023/11/15 下午9:07
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = SortConstant.SORT_ORDER_ASC;
}