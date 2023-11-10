package cn.jiyun.service.impl;

import cn.jiyun.entity.Book;
import cn.jiyun.mapper.BookMapper;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;


    @Override
    public PageResult getBookTableDate(Integer page, Integer size, Book book) {
        PageHelper.startPage(page, size);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(book.getTitle())) {
            wrapper.like(Book::getTitle, book.getTitle());
        }
        if (StringUtils.isNotBlank(book.getAuthor())) {
            wrapper.like(Book::getAuthor, book.getAuthor());
        }
        if (book.getBorrowStatus() != null) {
            wrapper.eq(Book::getBorrowStatus, book.getBorrowStatus());
        }
        List<Book> bookList = bookMapper.selectList(wrapper);
        PageInfo info = new PageInfo(bookList);
        return new PageResult(info.getTotal(), info.getList());
    }

    @Override
    public Result getBookInfo(Integer id) {
        try {
            Book book = bookMapper.getByIdBookInfo(id);
            return Result.success(book);
        } catch (Exception e) {
            return Result.error("获取图书详情失败");
        }
    }

    @Override
    public Result luckInfoPage(Integer id,Integer status) {
        try {
            Book book = bookMapper.luckInfoPage(id,status);
            System.out.println("luckInfoPage:>>>>>>>>>>" + book.toString());
            return Result.success(book);
        } catch (Exception e) {
            return Result.error("获取借阅详情失败");
        }
    }
}
