<template>
  <div>
    <div style="margin-bottom: 10px">
      <el-input style="width: 200px;margin-right:20px" placeholder="请输入联系人姓名查询" v-model="userName">
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
          prop="address"
          label="地址"
          width="180">
      </el-table-column>
      <el-table-column
          prop="doorNo"
          label="门牌号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="userName"
          label="联系人"
          width="180">
      </el-table-column>
      <el-table-column
          prop="phone"
          label="联系电话"
          width="180">
      </el-table-column>
      <el-table-column
          prop="userId"
          label="关联用户ID"
          width="180">
      </el-table-column>

      <el-table-column
          prop="user"
          label="关联用户"
          width="180">
      </el-table-column>

      <el-table-column label="操作" align="center" width="150" fixed="right">
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
    <el-dialog title="信息修改" :visible.sync="fromVisible" width="30%">
      <el-form :model="form" label-width="80px" style="padding-right: 20px"  ref="formRef">

        <!-- 地址 -->
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <!-- 门牌号 -->
        <el-form-item label="门牌号" prop="doorNo">
          <el-input v-model="form.doorNo"></el-input>
        </el-form-item>
        <!-- 联系人 -->
        <el-form-item label="联系人" prop="userName">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <!-- 联系电话 -->
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <!-- 关联用户ID -->
        <el-form-item label="关联用户" prop="userId">
          <el-select style="width: 100%" v-model="form.userId">
            <el-option v-for="item in userList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
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
  name: "Address",
  data() {
    return {
      userList:[],
      userName: null,
      tableData: [], //展示所有数据
      pageNum: 1,
      pageSize: 10,
      total: 0,
      form: {},
      fromVisible:false,
      ids: [],
      address: JSON.parse(localStorage.getItem('ncy-address') || '{}'),

    }
  },
  created() {
    this.load()
  },
  methods: {
  
    delBatch() {
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('你确认删除吗', "确认删除", {type: "warning"}).then(response => {
        this.$request.delete('/address/delete/batch', {
          data: this.ids
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
      this.$request.get('/user/selectAll').then(res=>{
        this.userList = res.data || []
        this.fromVisible = true //打开弹窗
      })
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/address/update' : '/address/add',
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
     this.userName =null
      this.load(1)
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
      this.$request.get('/address/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userName:this.userName
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    del(id) {
      this.$confirm('你确认删除吗', "确认删除", {type: "warning"}).then(response => {
        this.$request.delete('/address/delete/' + id).then(res => {
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