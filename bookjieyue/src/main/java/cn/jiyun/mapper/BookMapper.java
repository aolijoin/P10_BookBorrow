package cn.jiyun.mapper;

import cn.jiyun.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {
    @Select("select bk.* ,bw.borrow_time borrow_time,bw.return_time return_time , bw.id bid" +
            "     from book.borrow bw " +
            "          left join book.book bk on bw.bid = bk.id ")
    List<Book> getBorrowTableData();

    @Select("select bk.* ,bw.borrow_time borrow_time,bw.return_time return_time, bw.id bid" +
            "     from book.borrow bw " +
            "          left join book.book bk on bw.bid = bk.id where bk.id=#{id} and bw.id = #{status}")
    Book luckInfoPage(Integer id, Integer status);

    @Select("select bk.*, bw.borrow_time borrow_time, bw.return_time return_time, bw.id bid " +
            "     from book.borrow bw " +
            "          right outer join  book.book bk on bw.bid = bk.id " +
            " where bk.id = #{id} order by bw.id DESC limit 1")
    Book getByIdBookInfo(Integer id);
}
