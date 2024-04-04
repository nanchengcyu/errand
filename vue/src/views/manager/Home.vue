<script>
import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    return {
      isCollapse: false,  // 不收缩
      asideWidth: '200px',
      collapseIcon: 'el-icon-s-fold',
      users: [],
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
      url: '',
      urls: []
    }
  },
  mounted() {   // 页面加载完成之后触发
    request.get('/user/selectAll').then(res => {
      this.users = res.data
    })
  },
  methods: {
    preview(url) {
      window.open(url)  // 默认图片是预览
    },
    showUrls() {
      console.log(this.urls)
    },
    handleMultipleFileUpload(response, file, fileList) {
      this.urls = fileList.map(v => v.response?.data)
    },
    handleTableFileUpload(row, file, fileList) {
      console.log(row, file, fileList)
      row.avatar = file.response.data
      // this.$set(row, 'avatar', file.response.data)
      console.log(row)
      // 触发更新就可以了
      request.put('/user/update', row).then(res => {
        if (res.code === '200') {
          this.$message.success('上传成功')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleFileUpload(response, file, fileList) {
      console.log(response, file, fileList)
    },
    logout() {
      localStorage.removeItem('honey-user')  // 清除当前的token和用户数据
      this.$router.push('/login')
    },
    handleFull() {
      document.documentElement.requestFullscreen()
    },
    handleCollapse() {
      this.isCollapse = !this.isCollapse
      this.asideWidth = this.isCollapse ? '64px' : '200px'
      this.collapseIcon = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
    }
  }
}
</script>

<template>
  <el-main>

    <div style="display: flex;margin: 10px 0">
      <el-card style="width: 50%;margin-right: 10px">
        <div slot="header" class="clearfix">
          <span>文件上传下载</span>
        </div>
        <el-upload
            class="upload-demo"
            action="http://localhost:1000/file/upload"
            :headers="{token:user.token}"
            list-type="picture"
            :file-list="fileList"
            :on-success="handleFileUpload"
        >
          <el-button size="small" type="primary">单文件上传</el-button>
        </el-upload>
      </el-card>
    </div>

    <div style="display: flex;margin: 10px 0">
      <el-card style="width: 50%;margin-right: 10px">
        <div slot="header" class="clearfix">
          <span>文件上传下载</span>
        </div>
        <el-upload
            class="upload-demo"
            action="http://localhost:1000/file/upload"
            :headers="{token:user.token}"
            list-type="picture"
            :file-list="fileList"
            multiple
            :on-success="handleMultipleFileUpload"
        >
          <el-button size="small" type="primary">多文件上传</el-button>
        </el-upload>
      </el-card>
    </div>
  </el-main>
</template>

<style scoped>

</style>