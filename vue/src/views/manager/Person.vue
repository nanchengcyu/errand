<script>

export default {
  name: "Person",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('ncy-user') || '{}')
    }
  },
  methods: {
    update(){
    //保存当前的用户信息到数据库
      this.$request.put('user/update',this.user).then(res=>{
        if (res.code === '200'){
          this.$message.success('更新用户信息成功')
          //更新成功后，更新localstorage里面的信息
          localStorage.setItem('ncy-user',JSON.parse(this.user))

          //触发父级组件更新
          this.$emit('update:user',this.user)
        }
        else{
          this.$message.error(res.msg)
        }
      })
    },
    handleAvatarSuccess(res, file) {
      this.user.avatar=res.data //res的data是一个图片的链接
    },
  }


}
</script>

<template>
  <el-card style="width: 50%">
    <el-form :model="user" label-width="80px" style="padding-right: 20px">
      <div style="margin: 15px;text-align: center" >
        <el-upload
            class="avatar-uploader"
            action="http:localhost:1000/file/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :headers="{token:user.token}"
        >
          <img v-if="user.avatar" :src="user.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="user.username" plachholder="用户名" disabled>
        </el-input>

      </el-form-item>

      <el-form-item label="性别" prop="sex">
        <el-input v-model="user.sex" plachholder="性别">
        </el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="username">
        <el-input v-model="user.phone" plachholder="手机号">
        </el-input>
      </el-form-item>
      <div style="text-align: center">
        <el-button @click="update" type="primary">保存</el-button>
      </div>
    </el-form>
  </el-card>

</template>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>