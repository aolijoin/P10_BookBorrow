<template>

  <div id="borrowedInfo">
    <h1>借阅详情</h1>
    <div class="book-details">
      <div class="book-details-title">{{ book.title }}</div>
      <div class="book-details-author">作者:{{ book.author }}</div>
      <div class="book-details-publisher">出版社:{{ book.publisher }}</div>
      <div class="book-details-borrowTime">借阅时间:{{ book.borrowTime }}</div>
      <div class="book-details-returnTime">归还时间:{{ book.returnTime }}</div>
      <div class="book-details-status">
        当前图书借阅状态：{{ book.borrowStatus === 0 && book.returnTime === '' ? '已归还' : '借阅中' }}
      </div>
      <div class="book-details-button">
        <el-button type="default" @click="returnBook" v-if="!book.borrowStatus == 0? true:false"> 归还图书
        </el-button>
        <el-button type="default" @click="returnInfoPage" v-if="book.borrowStatus == 0? true:false">
          返回借阅列表
        </el-button>
      </div>
    </div>
  </div>
</template>
<script>
// 在Vue.js实例中定义一个包含商品信息的数据对象和一个buyNow方法
import axios from "axios";

export default {
  el: '#borrowedInfo',
  data() {
    return {
      bookAndInfo: '',
      book: {
        /*title: 'JavaScript高级程序设计',
        author: 'Nicholas C.Zakas',
        publisher: '人民邮电出版社',
        borrowTime: "2023-01-01 12:34:12",
        returnTime: ""*/
      }
    }
  },
  methods: {
    returnBook() {
      let id = this.book.bid
      axios.post(`http://localhost:8080/borrow/returnBook?id=${id}`).then(res => {
        if (res.data.code === 200) {
          this.$message.success(res.data.data)
          // location.href = 'borrowedList.html'
          this.$router.push('/borrowedList')
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    returnInfoPage() {
      // location.href = 'borrowedList.html'
      this.$router.push('/borrowedList')
    },
    luckInfoPage() {
      // let id = getQueryString('id')
      let id = this.$route.query.id
      // let status = getQueryString('bid')
      let status = this.$route.query.bid
      axios.post(`http://localhost:8080/book/luckInfoPage?id=${id}&bid=${status}`).then(res => {
        if (res.data.code === 200) {
          this.book = res.data.data
          console.log(res.data)
        } else {
          this.$message.error(res.data.message)
        }
      })
      this.bookAndInfo = this.book.borrowStatus === 0 && this.book.returnTime != ''
      console.log("bookAndInfo:" + this.bookAndInfo)
    }
  },
  created() {
    this.luckInfoPage()
  }
}
</script>
<style>
#borrowedInfo {
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
