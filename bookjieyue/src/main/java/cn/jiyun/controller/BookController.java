package cn.jiyun.controller;

import cn.jiyun.entity.Book;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BookService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("book")
public class BookController {
    @Resource
    private BookService bookService;

    @CrossOrigin
    @RequestMapping("getBookTableDate")
    public PageResult getBookTableDate(@RequestBody Book book, Integer page, Integer size) {
        System.out.println(">>>>: getBookTableDate =" + book.toString());
        return bookService.getBookTableDate(page, size, book);
    }

    @CrossOrigin
    @RequestMapping("getBookInfo")
    public Result getBookInfo(Integer id) {
        return bookService.getBookInfo(id);
    }

    @CrossOrigin
    @RequestMapping("luckInfoPage")
    public Result luckInfoPage(Integer id, Integer bid) {
        System.out.println("/*luckInfoPage(Integer*/ id)>>>>:" + id);
        return bookService.luckInfoPage(id, bid);
    }
}
