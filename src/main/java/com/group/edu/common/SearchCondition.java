package com.group.edu.common;

import lombok.Data;

import java.util.List;

@Data
public class SearchCondition<T> {
    private int currentPage = 1;
    private int pageSize = 10;
    private T example;
    private List<String> list;
    private List<String> range;
    private String orderBy;
}
