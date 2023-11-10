package cn.jiyun.controller;

import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BorrowService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



@RestController
@RequestMapping("borrow")
public class BorrowController {
    @Resource
    private BorrowService borrowService;
    @CrossOrigin
    @RequestMapping("getBorrowTableData")
    public PageResult getBorrowTableData(Integer page, Integer size) {
        return borrowService.getBorrowTableData(page, size);
    }
    @CrossOrigin
    @RequestMapping("goBorrow")
    public Result goBorrow(Integer id) {
        return borrowService.goBorrow(id);
    }
    @CrossOrigin
    @RequestMapping("getBookBorrowCount")
    public Result getBookBorrowCount() {
        return borrowService.getBookBorrowCount();
    }
    @CrossOrigin
    @RequestMapping("returnBook")
    public Result returnBook(Integer id) {
        return borrowService.returnBook(id);
    }
}
