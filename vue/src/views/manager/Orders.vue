<template>
  <div>
    <div style="margin-bottom: 10px">
      <el-input style="width: 200px;margin-right:20px" placeholder="请输入订单编号" v-model="orderNo">
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
      <el-table-column
          prop="id"
          label="ID"
          width="180">
      </el-table-column>
      <el-table-column
          prop="orderNo"
          label="订单编号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="物品名称"
          width="180">
      </el-table-column>
      <el-table-column
          label="物品图片">
        <template v-slot="scope">
          <el-image style="height: 50px;width: 50px;border-radius: 50%" v-if="scope.row.img" :src="scope.row.img"
                    :preview-src-list="[scope.row.img]"></el-image>
        </template>
      </el-table-column>
      <el-table-column
          prop="type"
          label="物品类型">
      </el-table-column>
      <el-table-column
          prop="userName"
          label="发起人">
      </el-table-column>
      <el-table-column
          prop="acceptName"
          label="接单人">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="创建时间"
          width="180">
      </el-table-column>
      <el-table-column
          prop="acceptTime"
          label="接单时间"
          width="180">
      </el-table-column>
      <el-table-column
          prop="status"
          label="订单状态">
      </el-table-column>
      <el-table-column
          prop="addressId"
          label="取货地址ID">
      </el-table-column>
      <el-table-column
          prop="targetId"
          label="送货地址ID">
      </el-table-column>
      <el-table-column
          prop="comment"
          label="订单备注">
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

        <el-form-item label="订单状态" prop="status" >
          <el-select v-model="form.status">
            <el-option value="已取消"> </el-option>
            <el-option value="待接单"> </el-option>
            <el-option value="待送达"> </el-option>
            <el-option value="待收货"> </el-option>
            <el-option value="待评价"> </el-option>
            <el-option value="已完成"> </el-option>
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
  name: "Orders",
  data() {
    return {
      orderNo: null,
      tableData: [], //展示所有数据
      pageNum: 1,
      pageSize: 10,
      total: 0,
      form: {},
      fromVisible:false,
      ids: [],
      orders: JSON.parse(localStorage.getItem('ncy-orders') || '{}'),

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
        this.$request.delete('/orders/delete/batch', {
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
      this.fromVisible = true //打开弹窗
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/orders/update' : '/orders/add',
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
     this.orderNo =null
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
      this.$request.get('/orders/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          orderNo:this.orderNo
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    del(id) {
      this.$confirm('你确认删除吗', "确认删除", {type: "warning"}).then(response => {
        this.$request.delete('/orders/delete/' + id).then(res => {
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