package cn.jiyun.service.impl;

import cn.jiyun.entity.Book;
import cn.jiyun.entity.Borrow;
import cn.jiyun.mapper.BookMapper;
import cn.jiyun.mapper.BorrowMapper;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private BookMapper bookMapper;

    @Override
    public PageResult getBorrowTableData(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Book> list = bookMapper.getBorrowTableData();
        PageInfo info = new PageInfo(list);
        return new PageResult(info.getTotal(), info.getList());
    }

    @Override
    public Result goBorrow(Integer id) {
        try {
            Borrow borrow = new Borrow();
            borrow.setBid(id);
            borrow.setBorrowTime(new Date());
            borrowMapper.insert(borrow);


            Book book = bookMapper.selectById(id);
            book.setBorrowStatus(1);
            bookMapper.updateById(book);
            return Result.success("借阅成功");
        } catch (Exception e) {
            return Result.error("借阅失败");

        }
    }

    @Override
    public Result getBookBorrowCount() {
        Integer count = null;
        try {
            count = borrowMapper.getBookBorrowCount();
            System.out.println(">>>>>>>>>>" + count);
            return Result.success(count);
        } catch (Exception e) {
            return Result.error("获取借阅数量失败");
        }
    }

    @Override
    public Result returnBook(Integer id) {
        try {
            Borrow borrow = borrowMapper.selectById(id);
            borrow.setReturnTime(new Date());
            borrowMapper.updateById(borrow);

            Book book = bookMapper.selectById(borrow.getBid());
            book.setBorrowStatus(0);
            book.setBorrowCount(book.getBorrowCount() + 1);
            bookMapper.updateById(book);
            return Result.success("还书成功");
        } catch (Exception e) {
            return Result.error("还书失败");
        }
    }
}
