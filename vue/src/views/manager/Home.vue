<script>
export default {
  name: "Home",
  data() {
    return {

      users: [],
      user: JSON.parse(localStorage.getItem('ncy-user' || '{}')),
      url:''
    }
  },

}
</script>

<template>
  <el-main>
    <el-table
        :data="users"
        style="width: 100%">
      <el-table-column
          prop="name"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column label="文件上传">
        <template v-slot="scope">
          <el-upload
              action="http://localhost:1000/file/upload"
              :headers="{token:user.token}"
              :show-file-list="false"
              :on-success="(row,file,fileList)=>handleTableFileUpload(scope.row,file,fileList)"
          >
            <el-button size="mini" type="primary">点击上传</el-button>
          </el-upload>
        </template>

      </el-table-column>
      <el-table-column

          label="头像">
        <template v-slot="scope">
          <el-image v-if="scope.row.avatar " :src="scope.row.avatar" style="width: 50px;height: 50px"></el-image>
        </template>
      </el-table-column>
      <el-table-column
          prop="role"
          label="角色">
      </el-table-column>
      <el-table-column
          prop="phone"
          label="手机号">
      </el-table-column>
    </el-table>

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