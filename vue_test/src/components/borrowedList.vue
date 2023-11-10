<template>
  <div id="borrowedList">
    <h1 class="page-title">借阅图书列表页</h1>
    <h3>处于 "借阅中" 的书籍最多只能有3本 </h3>
    <el-table :data="bookList">
      <el-table-column prop="title" label="书名"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="borrowStatus" label="状态">
        <template slot-scope="scope">
          {{ scope.row.borrowStatus === 1 ? '借阅中' : '可借阅' }}
        </template>
      </el-table-column>
      <el-table-column prop="borrowTime" label="借阅时间"></el-table-column>
      <el-table-column prop="returnTime" label="归还时间"></el-table-column>
      <el-table-column prop="" label="查看详情">
        <template slot-scope="scope">
          <el-button @click="luckInfoPage(scope.row.id,scope.row.bid)">查看详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="page.pageSize"
          :current-page="page.currentPage"
          :total="page.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'borrowedList',
  data() {
    return {
      bookList: [],
      page: {
        currentPage: 1,
        pageSize: 5,
        total: 30
      }
    }
  },
  methods: {
    getBorrowTableData() {
      axios.post(`http://localhost:8080/borrow/getBorrowTableData?page=${this.page.currentPage}&size=${this.page.pageSize}`,).then(res => {
        this.bookList = res.data.list
        this.page.total = res.data.total
        console.log(res.data)
      })
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getBorrowTableData()
    },
    handleCurrentChange(val) {
      this.page.currentPage = val
      this.getBorrowTableData()
    },
    luckInfoPage(id, bid) {
      // location.href = 'borrowedInfo.html?id=' + id + '&bid='+bid
      this.$router.push({path: 'borrowedInfo', query: {id: id, bid: bid}})
    }
  }, created() {
    this.getBorrowTableData()
  }
}
</script>
<style>
.el-table,
.el-pagination {
  width: 1000px;
  margin: 0 auto;
}

h1, h3 {
  text-align: center;
}

span {
  display: block;
  margin: 0 auto;
}
</style>