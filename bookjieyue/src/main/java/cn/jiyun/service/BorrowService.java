package cn.jiyun.service;

import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;

public interface BorrowService {
    PageResult getBorrowTableData(Integer page, Integer size);

    Result goBorrow(Integer id);

    Result getBookBorrowCount();

    Result returnBook(Integer id);
}
