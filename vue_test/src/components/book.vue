<template>
  <div id="book">
    <h1>图书详情页面</h1>
    <div class="book-details">
      <div class="book-details-title">{{ book.title }}</div>
      <div class="book-details-author">作者：{{ book.author }}</div>
      <div class="book-details-publisher">出版社：{{ book.publisher }}</div>
      <div class="book-details-status">
        当前借阅状态：{{ book.borrowStatus === 1 ? '借阅中' : '未借阅' }}
        <span v-if="book.borrowStatus === 1">{{ book.borrowTime }}</span>
      </div>

      <div style="color: red">注意: 处于 "借阅中"
        的书籍最多只能有3本，您目前已经借阅{{ count }}本，还可以借阅{{ 3 - count }}本，一本书不可以被重复借阅
      </div>

      <div class="book-details-button">
        <el-button @click="goBorrow" :disabled="count >=3 || book.borrowStatus === 1">借阅</el-button>
        <el-button @click="goBookTable" :disabled="count===3 && book.borrowStatus === 0">返回图书列表</el-button>
        <el-button @click="goBorrowTable" :disabled="count===3 && book.borrowStatus === 0">借阅列表</el-button>
      </div>
    </div>
  </div>
</template>
<script>
// 在Vue.js实例中定义一个包含商品信息的数据对象和一个buyNow方法
import axios from "axios";

export default {
  name: '#book',
  data() {
    return {
      book: {},
      count: 0
    }
  },
  methods: {
    getBookInfo() {
      // let id = getQueryString('id')
      let id = this.$route.query.id
      axios.post(`http://localhost:8080/book/getBookInfo?id=${id}`).then(res => {
        if (res.data.code === 200) {
          this.book = res.data.data
        } else {
          this.$message.info(res.data.message)
        }
      })
    },
    goBorrow() {
      axios.post(`http://localhost:8080/borrow/goBorrow?id=${this.book.id}`).then(res => {
        if (res.data.code === 200) {
          this.$message.success(res.data.data)
          // location.href = 'borrowedList.html'
          this.$router.push('/borrowedList')
        } else {
          this.$message.info(res.data.message)
        }
      })
    },
    getBookBorrowCount() {
      axios.post(`http://localhost:8080/borrow/getBookBorrowCount`).then(res => {
        if (res.data.code === 200) {
          this.count = res.data.data
          console.log(res.data)
        } else {
          this.$message.info(res.data.message)
        }
      })
    },
    goBookTable() {
      // location.href = 'bookList.html'
      this.$router.push('/')
    },
    goBorrowTable(){
      this.$router.push('/borrowedList')
    }
  },
  created() {
    this.getBookInfo()
    this.getBookBorrowCount()
  }

}
</script>
<style>
.book-details {
  display: flex;
  flex-direction: column;
  /*align-items: left;*/
  width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.book-details-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}

.book-details-author,
.book-details-publisher,
.book-details-status {
  font-size: 16px;
  margin-bottom: 5px;
}

#book {
  background-color: #f5f5f5;
  color: #333;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

.el-form {
  width: 1000px;
  margin: 0 auto;
}

h1 {
  text-align: center;
}
</style>