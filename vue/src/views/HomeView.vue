<template>

  <div>
    <el-container>

      <el-aside :width="asideWidth" style="min-height: 100vh;background-color:#001529 ">
        <div
            style="height: 60px;color: white;display: flex;align-items: center;justify-content: center">
          logo
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
          <i class="el-icon-menu" style="width: 40px;margin-top: 20px" @click="handleCollapse"></i>
          Header
        </el-header>
        <el-main>Main</el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </div>
</template>


<script>
export default {
  name: 'HomeView',
  data() {
    return {
      isCollapse: false,
      asideWidth: '200px'
    }
  },
  methods: {
    handleCollapse() {
      this.isCollapse = !this.isCollapse
      this.asideWidth = this.isCollapse?'64px':'200px'
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
</style>