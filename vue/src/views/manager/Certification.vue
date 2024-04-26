<template>
  <div>
    <div style="margin-bottom: 10px">
      <el-input style="width: 200px;margin-right:20px" placeholder="请输入骑手姓名查询" v-model="name">
      </el-input>
      <el-button type="primary" style="margin-left: 20px" @click="load(1)">查询</el-button>
      <el-button type="primary" style="margin-left: 20px" @click="reset">重置</el-button>
    </div>
    <div>
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
      <el-table-column prop="user id" label="账号" width="180"></el-table-column>
      <el-table-column prop="name" label="名称" width="180"></el-table-column>
      <el-table-column prop="phone" label="联系方式" width="180"></el-table-column>
      <el-table-column prop="card_no" label="身份证号码" width="180"></el-table-column>
      <el-table-column prop="avatar" label="本人照片" width="180">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" style="width: 50px; height: auto;">
        </template>
      </el-table-column>

      <el-table-column prop="card1" label="身份证正面" width="180">
        <template slot-scope="scope">
          <img :src="scope.row.card1" style="width: 50px; height: auto;">
        </template>
      </el-table-column>

      <el-table-column prop="card2" label="身份证反面" width="180">
        <template slot-scope="scope">
          <img :src="scope.row.card2" style="width: 50px; height: auto;">
        </template>
      </el-table-column>
      <el-table-column prop="address" label="常住地址" width="180"></el-table-column>
      <el-table-column prop="status" label="审核状态" width="180">
        <template v-slot="scope">
        <el-tag type="info" v-if="scope.row.status==='待审核'">待审核</el-tag>
        <el-tag type="success" v-if="scope.row.status==='通过'">通过</el-tag>
        <el-tag type="danger" v-if="scope.row.status==='拒绝'">拒绝</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="reason" label="审核理由" width="180"></el-table-column>

      <el-table-column label="操作" align="center" width="150" fixed="right">
        <template v-slot="scope">
          <el-button type="primary" plain @click="handleEdit(scope.row)">审核</el-button>
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

        <el-form-item label="骑手名称" prop="certification">
          <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="审核状态" prop="status" >
        <el-select v-model="form.status" >
          <el-option v-for="item in ['待审核','通过','拒绝']" :key="item" :value="item" :label="item"></el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="审核理由" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="审核理由"></el-input>
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
  name: "Certification",
  data() {
    return {
      name:null,
      userList:[],
      userName: null,
      tableData: [], //展示所有数据
      pageNum: 1,
      pageSize: 10,
      total: 0,
      form: {},
      fromVisible:false,
      ids: [],
      certification: JSON.parse(localStorage.getItem('ncy-certification') || '{}'),

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
        this.$request.delete('/certification/delete/batch', {
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
            url: this.form.id ? '/certification/update' : '/certification/add',
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
     this.name =null
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
      this.$request.get('/certification/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name:this.name
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    del(id) {
      this.$confirm('你确认删除吗', "确认删除", {type: "warning"}).then(response => {
        this.$request.delete('/certification/delete/' + id).then(res => {
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