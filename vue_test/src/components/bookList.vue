<script>
import {defineComponent} from 'vue'
import axios from "axios";

export default defineComponent({
  name: "bookList",
  data() {
    return {
      dialogFormVisible: false,
      searchForm: {
        title: '',
        author: '',
        publisher: '',
        borrowStatus: ''
      },
      tableData: [
        /*{
          id: 7,
          title: 'JavaScript高级程序设计',
          author: 'Nicholas C.Zakas',
          publisher: '人民邮电出版社',
          borrowCount: 109,
          borrowStatus: false
        },
        {
          id: 13,
          title: 'JavaScript权威指南',
          author: 'David Flanagan',
          publisher: '人民邮电出版社',
          borrowCount: 89,
          borrowStatus: true
        },
        {
          id: 34,
          title: 'CSS揭秘',
          author: 'Lea Verou',
          publisher: '人民邮电出版社',
          borrowCount: 85,
          borrowStatus: false
        },
        {
          id: 19,
          title: 'HTML5与CSS3基础教程',
          author: '何新',
          publisher: '清华大学出版社',
          borrowCount: 30,
          borrowStatus: true
        },
        {
          id: 8,
          title: 'Vue.js实战',
          author: '梁灏',
          publisher: '人民邮电出版社',
          borrowCount: 25,
          borrowStatus: false
        },
        {
          id: 15,
          title: 'Node.js实战',
          author: '汤姆大叔',
          publisher: '人民邮电出版社',
          borrowCount: 10,
          borrowStatus: false
        }*/
      ],
      page: {
        currentPage: 1,
        pageSize: 5,
        total: 50
      }
    }
  },
  methods: {
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getBookTableDate()
    }, handleCurrentChange(val) {
      this.page.currentPage = val
      this.getBookTableDate()
    },
    getBookTableDate() {
      axios.post(`http://localhost:8080/book/getBookTableDate?page=${this.page.currentPage}&size=${this.page.pageSize}`, this.searchForm).then(res => {
        this.page.total = res.data.total
        this.tableData = res.data.list
        console.log(res.data)
      })
    },
    infoPage(row) {
      // location.href = 'book.html?id=' + row.id
      this.$router.push({path: '/book', query: {id: row.id}})

    }
  },
  created() {
    this.getBookTableDate()
  }
})
</script>

<template>
  <div id="bookList">
    <h1 class="page-title">图书列表</h1>
    <div class="search-bar">
      <el-form :model="searchForm" label-width="100px" inline>
        <el-form-item label="书名">
          <el-input v-model="searchForm.title" placeholder="请输入书名"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="searchForm.author" placeholder="请输入作者"></el-input>
        </el-form-item>
        <el-form-item label="借阅状态">
          <el-select v-model="searchForm.borrowStatus" placeholder="">
            <el-option label="借阅中" :value="1" key="借阅中"></el-option>
            <el-option label="可借阅" :value="0" key="可借阅"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getBookTableDate">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="tableData" border stripe>
      <el-table-column prop="title" label="书名"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="publisher" label="出版社"></el-table-column>
      <el-table-column prop="borrowCount" label="借阅次数"></el-table-column>
      <el-table-column prop="borrowStatus" label="当前借阅状态">
        <template slot-scope="scope">
          {{ scope.row.borrowStatus === 1 ? '借阅中' : '可借阅' }}
        </template>
      </el-table-column>
      <el-table-column prop="mgr" label="操作">
        <template slot-scope="scope">
          <!--                :disabled="scope.row.borrowStatus===1"-->
          <el-button @click="infoPage(scope.row)">详情信息</el-button>
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

<style scoped>
.el-table,
.el-pagination,
.el-form {
  width: 1000px;
  margin: 0 auto;
}

h1 {
  text-align: center;
}
</style>