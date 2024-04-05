<script>
export default {
  name: "Password",
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.user.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      user: JSON.parse(localStorage.getItem('ncy-user') || '{}'),
      rules: {
        password: [
          {required: true, message: '请输入原始密码', trigger: 'blur'},
        ],
        newPassword: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ],
        confirmPassword: [
          {validator: validatePass2,required: true,trigger: 'blur'},
        ],


      },

    }
  },
  methods: {
    update() {
      //先校验再触发更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          //表单验证通过做一次更新
          this.user.password = this.user.confirmPassword
          //保存当前的用户信息到数据库
          this.$request.put('user/update', this.user).then(res => {
            if (res.code === '200') {
              this.$message.success('更新用户信息成功')
             this.$router.push('/login')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })

    },

  }
}
</script>

<template>
  <el-card style="width: 50%">
    <el-form :rules="rules" ref="formRef" :model="user" label-width="80px" style="padding-right: 20px">
      <el-form-item label="原始密码" prop="password">
        <el-input v-model="user.password" show-password plachholder="原始密码">
        </el-input>
      </el-form-item>
      <el-form-item label="修改密码" prop="newPassword">
        <el-input v-model="user.newPassword" show-password plachholder="请输入要修改的密码">
        </el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="user.confirmPassword " show-password plachholder="请再次输入要修改的密码">
        </el-input>
      </el-form-item>
      <div style="text-align: center;margin-top: 30px">
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
</style>