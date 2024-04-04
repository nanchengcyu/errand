<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "Login",
  components: {
    ValidCode
  },
  data() {  //整个data返回得数据是一个整体对象
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.user.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {

      code: '',
      user: {
        username: '',
        password: '',
        checkPassword:''
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
        checkPassword: [
          { validator: validatePass2, trigger: 'blur' }
          // {min: 5, max: 8, message: '长度在5到8个字符', trigger: 'blur'}
        ],

        role: [
          { required: true, message: '请选择角色', trigger: 'blur'}
          // {min: 5, max: 8, message: '长度在5到8个字符', trigger: 'blur'}
        ],

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
    register() {
      this.$refs["loginRef"].validate((valid) => {
        if (valid) {
          this.$request.post('/register', this.user).then(res => {
            if (res.code ==='200'){
              this.$router.push('/login')
              this.$message.success("注册成功")
              localStorage.setItem("ncy",JSON.stringify(res.data)) //存储用户数据
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
            欢迎注册后台管理系统
          </div>
          <el-form-item prop="username">
            <el-input placeholder="请输入账号" v-model="user.username" style="width: 85%"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="请输入密码" type="password" v-model="user.password" style="width: 85%"></el-input>
          </el-form-item>
          <el-form-item prop="checkPassword">
            <el-input placeholder="请输入确认密码" type="password" v-model="user.checkPassword" style="width: 85%"></el-input>
          </el-form-item>
          <el-form-item prop="role">
            <el-radio-group v-model="user.role">
              <el-radio label="用户"></el-radio>
              <el-radio label="商家"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 85%" @click.native="$router.push('/login')">返回登录</el-button>
          </el-form-item>
          <el-form-item>
            <el-button plain type="info" style="width: 85% "@click="register" >注册</el-button>
          </el-form-item>
        </el-form>

      </div>
    </div>
  </div>
</template>

<style scoped>

</style>