<template>
  <div>
    <div style="margin-bottom: 10px">
      <el-input style="width: 200px;margin-right:20px" placeholder="查询用户名" v-model="username">
      </el-input>
      <el-input style="width: 200px " placeholder="查询姓名" v-model="name">
      </el-input>
      <el-button type="primary" style="margin-left: 20px" @click="load(1)">查询</el-button>
      <el-button type="primary" style="margin-left: 20px" @click="reset">重置</el-button>
    </div>
    <div>
      <el-button type="primary" plain @click="handleAdd"> 新增</el-button>
      <el-button type="danger" plain @click="delBatch"> 批量删除</el-button>
    </div>
    <el-table
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column align="center"
                       type="selection"
                       width="55">
      </el-table-column>
      <el-table-column
          prop="id"
          label="序号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="username"
          label="用户名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column
          label="头像">
        <template v-slot="scope">
          <el-image style="height: 50px;width: 50px;border-radius: 50%" v-if="scope.row.avatar" :src="scope.row.avatar "
                    :preview-src-list="[scope.row.avatar]"></el-image>
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
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="danger" plain @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="用户信息修改" :visible.sync="fromVisible" width="30%">
      <el-form :model="form" label-width="80px" style="padding-right: 20px" :rules="rules" ref="formRef">
        <div style="margin: 15px;text-align: center">
          <el-upload
              class="avatar-uploader"
              action="http:localhost:1000/file/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :headers="{token:user.token}"
          >
            <img v-if="form.avatar" :src="user.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" plachholder="用户名">
          </el-input>

        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input v-model="form.name" plachholder="用户名">
          </el-input>

        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-input v-model="form.sex" plachholder="性别">
          </el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="username">
          <el-input v-model="form.phone" plachholder="手机号">
          </el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "user",
  data() {
    return {
      fromVisible: false,
      tableData: [],
      pageNum: 1,
      pageSize: 5,
      username: '',
      name: '',
      total: 0,
      form: {},
      ids: [],
      user: JSON.parse(localStorage.getItem('ncy-user') || '{}'),
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入昵称', trigger: 'blur'},
        ],
        sex: [
          {required: true, message: '请输入性别', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    delBatch() {
      if (!this.ids.length){
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('你确认删除吗', "确认删除", {type: "warning"}).then(response => {
        this.$request.delete('/user/delete/batch' ,{
          data:this.ids
        }).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load(1)  //刷新表格
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })

    },
    handleSelectionChange(rows) {
      this.ids = rows.map(v => v.id)
    },
    handleEdit(row) {
      //编辑数据
      this.form = JSON.parse(JSON.stringify(row))  //给form对象复制
      this.fromVisible = true //打开弹窗
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/user/update' : '/user/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form,

          }).then(res => {
            if (res.code === '200') {
              //表示保存成功
              this.$message.success('保存成功')
              this.load(1)
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)
            }
          })

        }
      })
    },
    handleAdd() {
      this.form = {} //新增用户时，数据为空
      this.fromVisible = true
    },
    handleAvatarSuccess(res, file) {
      this.form.avatar = res.data //res的data是一个图片的链接
    },
    reset() {
      this.name = ''
      this.username = ''
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()

    },
    load(pageNum) {
      if (pageNum) {
        this.pageNum = pageNum
      }
      //分页查询
      this.$request.get('/user/selectByPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          name: this.name
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    del(id) {
      this.$confirm('你确认删除吗', "确认删除", {type: "warning"}).then(response => {
        this.$request.delete('/user/delete/' + id).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load(1)  //刷新表格
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    }
  }
}

</script>
<style scoped>

</style>