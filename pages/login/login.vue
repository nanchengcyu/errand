<template>
	<view style="margin: 20rpx;">
		<view class="box" style="padding: 60rpx 30rpx;">
			<view style="font-size: 40rpx;">login</view>
			<view style="margin: 60rpx 0rpx;">
				<uni-forms :modelValue="form" :rules="rules" ref="formRef" validateTrigger="blur">
					<uni-forms-item name="username">
						<uni-easyinput prefixIcon="person" type="text" v-model="form.username" placeholder="请输入账号" />
					</uni-forms-item>
					<uni-forms-item name="password">
						<uni-easyinput prefixIcon="locked" type="password" v-model="form.password"
							placeholder="请输入密码" />
					</uni-forms-item>

					<uni-forms-item>
						<button type="primary" size="mini" @click="login"
							style="width: 100%; height: 70rpx; line-height: 70rpx;">登 录</button>
					</uni-forms-item>
				</uni-forms>
			</view>
			<view style="text-align: right;">
				还没账号？请 <navigator style="display: inline; margin-left: 5rpx; color: blue;">注册</navigator>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {},
				rules: {
					// 对name字段进行必填验证
					username: {
						// name 字段的校验规则
						rules: [
							// 校验 name 不能为空
							{
								required: true,
								errorMessage: '请填写用户名',
							},
						],
					},
					password: {
						rules: [{
							required: true,
							errorMessage: '请输入密码',
						}]
					},
				}
			}
		},
		methods: {
			login() {
				this.$refs.formRef.validate().then(res => {
					this.$request.post('/login', this.form).then(res => {
						if (res.code === '200') {
							uni.setStorageSync('xm-user', res.data) //数据保存到本地
							uni.showToast({
									icon: 'success',
									title: '登录成功'
								}),
								setTimeout(() => { //登录成功后延迟500毫秒跳转到首页
									uni.switchTab({
										url: '/pages/index/index'
									})
								}, 500)
						} else {
							uni.showToast({
								icon: 'none',
								title: res.msg
							})
						}
					})
				}).catch(err => {
					console.log('err', err);
				})
			}
		}
	}
</script>

<style>

</style>