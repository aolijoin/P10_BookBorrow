package cn.jiyun.service;

import cn.jiyun.entity.Book;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;

public interface BookService {
    PageResult getBookTableDate(Integer page, Integer size, Book book);

    Result getBookInfo(Integer id);

    Result luckInfoPage(Integer id,Integer status);
}
