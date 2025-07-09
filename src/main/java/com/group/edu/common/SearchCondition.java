package com.group.edu.common;

import lombok.Data;

@Data
public class SearchCondition<T> {
    private int currentPage = 1;
    private int pageSize = 10;
    private T example;
}
