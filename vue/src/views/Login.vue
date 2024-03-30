<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "Login",
  components: {
    ValidCode
  },
  data() {  //整个data返回得数据是一个整体对象
    const validateCode = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('验证码不能为空'));
      } else if (value !== this.code) {
        callback(new Error('验证码错误'))
      } else {
        callback()
      }

    };
    return {

      code: '',
      user: {
        username: '',
        password: '',
        code: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          // {min: 5, max: 8, message: '长度在5到8个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          // {min: 5, max: 8, message: '长度在5到8个字符', trigger: 'blur'}
        ],
        code: [
          {validator: validateCode, trigger: 'blur'}
        ]


      },

    }
  },

  mounted() {

  },
  methods: {
    getCode(code) {
      console.log(code)
      this.code = code.toLowerCase();
    },
    login() {
      this.$refs["loginRef"].validate((valid) => {
        if (valid) {
          this.$requset.post('/login', this.user).then(res => {
            if (res.code ==='200'){
              this.$router.push('/')
              this.$message.success("登录成功")
              localStorage.setItem("ncy-user",JSON.stringify(res.data)) //存储用户数据
             }else{
              this.$message.error(res.msg)
            }
          })

        }
      })
    }
  }


}
</script>

<template>
  <div style="height: 100vh;display: flex;align-items: center;justify-content: center;background-color: cadetblue">
    <div style="display: flex;background-color: white;width: 80%;border-radius: 5px;overflow: hidden">
      <div style="flex: 1">
        <img src="@/assets/login.png" style="width: 95%;height: 100%" alt="login.png">
      </div>
      <div style="flex: 1;justify-content: center">
        <el-form :model="user" :rules="rules" ref="loginRef">
          <div style="font-size: 20px;font-weight: bold;text-align: center;margin-bottom: 20px;margin-right: 150px">
            欢迎登录后台管理系统
          </div>
          <el-form-item prop="username">
            <el-input placeholder="请输入账号" v-model="user.username" style="width: 85%"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="请输入密码" type="password" v-model="user.password" style="width: 85%"></el-input>
          </el-form-item>
          <el-form-item prop="code">
            <div style="display: flex">
              <el-input placeholder="请输入验证码" style="flex: 1" size="medium" v-model="user.code"></el-input>
              <div style="flex: 1;height: 36px">
                <ValidCode @update:value="getCode"></ValidCode>
              </div>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 85%" @click="login">登录</el-button>
          </el-form-item>
          <el-form-item>
            <el-button plain type="info" style="width: 85% "   @click.native="$router.push('/register')">注册</el-button>
          </el-form-item>
        </el-form>

      </div>
    </div>
  </div>
</template>

<style scoped>

</style>