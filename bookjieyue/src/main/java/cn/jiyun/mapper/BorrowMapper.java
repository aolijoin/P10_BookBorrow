package cn.jiyun.mapper;

import cn.jiyun.entity.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BorrowMapper extends BaseMapper<Borrow> {

    @Select("select count(*) from book.borrow bw where bw.return_time is null")
    Integer getBookBorrowCount();
}
