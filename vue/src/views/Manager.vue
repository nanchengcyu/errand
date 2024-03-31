<template>


  <el-container>

    <el-aside :width="asideWidth" style="min-height: 100vh;background-color:#001529 ">
      <div
          style="height: 60px;color: white;display: flex;align-items: center;justify-content: center">
        <img src="@/assets/logo.png" alt="logo" style="width: 40px;height: 40px">
        <span class="logo-title" v-show="!isCollapse">Errand</span>
      </div>

      <el-menu :collapse="isCollapse"
               :collapse-transition="false"
               default-active="2"
               class="el-menu-vertical-demo"
               background-color="#001529"
               active-text-color="#fff"
               text-color="rgba(255,255,255,0.65)"
      >
        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span slot="title">导航一</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span slot="title">导航二</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <i class="el-icon-document"></i>
          <span slot="title">导航三</span>
        </el-menu-item>
        <el-submenu>
          <template slot="title">
            <i class="el-icon-document"></i>
            <span slot="title">信息管理</span>
          </template>
          <el-menu-item>用户信息</el-menu-item>
        </el-submenu>
      </el-menu>

    </el-aside>


    <el-container>

      <el-header>
        <i :class="collapseIcon" style="font-size: 26px" @click="handleCollapse"></i>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>活动管理</el-breadcrumb-item>
          <el-breadcrumb-item>活动列表</el-breadcrumb-item>
          <el-breadcrumb-item>活动详情</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="flex: 1;width: 0;display: flex;align-items: center;justify-content: flex-end">
          <i class="el-icon-full-screen" style="font-size: 26px" @click="handleFull"></i>
          <el-dropdown>
            <div style="display: flex;align-items: center " cursor="default">
              <img src="@/assets/logo.png" alt="logo" style="width: 40px;height: 40px;margin: 0 5px">
              <span>管理员</span>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-main>
      <router-view></router-view>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </el-container>

</template>


<script>
import request from "@/utils/request";
import router from "@/router";

export default {

  name: 'HomeView',
  data() {
    return {
      fileList: [],
      isCollapse: false,
      asideWidth: '200px',
      collapseIcon: 'el-icon-s-fold',
      users: [],
      user: JSON.parse(localStorage.getItem('ncy-user' || '{}')),
      url:''
    }
  },
  mounted() {


    // axios.get('http://localhost:1000/user/selectAll').then(res=>{
    //   console.log(res)
    //   this.users=res.data.data()
    // })
    request.get('user/selectAll').then(res => {
      this.users = res.data   //此处res可以直接data是因为封装的request.js 原来应该为res.data.data

    })
  },
  methods: {
    handleMultipleFileUpload(response, file, fileList){

    },
    handleTableFileUpload(row,file,fileList) {
    row.avatar = file.response.data


      request.put('user/update',row).then(res => {
        if (res.code ==='200'){
          this.$message.success('上传成功')
        }else{
          this.$message.error(res.msg)
        }

      })
    },

    handleFileUpload(fileList) {
      this.fileList = fileList
    },
    logout() {
      localStorage.removeItem('ncy-user')
      this.$router.push('/login')
    },
    router() {
      return router
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


<style>
.el-menu-inline .el-menu-item {
  background-color: #000c17 !important;
}

.el-menu-item:hover, .el-icon-document:hover, .el-submenu__title:hover {
  color: #fff !important;
}

.el-menu-item.is-active {
  background-color: #40a9ff !important;
  border-radius: 5px !important;
  margin: 4px !important;
}

.el-menu-item.is-active i, .el-menu-item.is-active .el-tooltip {
  margin-left: -4px;
}

.el-menu-inline .el-menu-item.is-active {
  padding-left: 45px !important;
}

.el-side {
  transition: width .3s;
  box-shadow: 2px 0 6px rgba(0, 21, 41, 35);
}

.logo-title {
  margin-left: 5px;
  font-size: 20px;
  transition: all .3s;
}

.el-header {
  box-shadow: 2px 0 6px rgba(0, 21, 41, 35);
  display: flex;
  align-items: center;
}
</style>