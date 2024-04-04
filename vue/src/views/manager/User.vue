<script>
export default {
  name: "user",
  data() {
    return {
      tableData: [],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get('/user/selectAll').then(res => {
        this.tableData = res.data
      })
    },
    deleteUser() {
      this.$request.delete(`/user/delete/${this.tableData.id}`).then(res => {

      })
    }
  }
}

</script>

<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="id"
          label="序号"
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
          <el-button type="primary" plain @click="edit">编辑</el-button>
          <el-button type="danger" plain @click="deleteUser">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<style scoped>

</style>