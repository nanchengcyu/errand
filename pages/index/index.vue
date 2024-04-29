<template>
	<view style="padding: 20rpx; ">
		<view style="margin-bottom: 20rpx;">
			<swiper circular autoplay :interval="3000" :duration="500" indicator-dots style="height: 320rpx;"
							indicator-color="rgba(255, 255, 255, 0.6)" indicator-active-color="#006eff">
							<swiper-item v-for="item in imgs" :key="item">
								<image :src="item" alt="" style="width: 100%; height: 350rpx;" />
							</swiper-item>
			</swiper>
		</view>
		<view style="margin-bottom: 20rpx;">
			<uni-notice-bar single scrollable showIcon  :text="content"></uni-notice-bar>
		</view>
		
	<view style="display: flex; margin-bottom: 20rpx;" class="box">
				<view class="category-item" @click="goProOrder('代拿快递')">
					<image src="../../static/imgs/快递.png" mode="widthFix" style="width: 50%"></image>
					<view style="flex: 1">代拿快递</view>
				</view>
				<view class="category-item" @click="goProOrder('代取餐品')">
					<image src="../../static/imgs/餐品.png" mode="widthFix" style="width: 50%"></image>
					<view style="flex: 1">代取餐品</view>
				</view>
				<view class="category-item" @click="goProOrder('代买零食')">
					<image src="../../static/imgs/零食.png" mode="widthFix" style="width: 50%"></image>
					<view style="flex: 1">代买零食</view>
				</view>
				<view class="category-item" @click="goProOrder('代送鲜花')">
					<image src="../../static/imgs/花.png" mode="widthFix" style="width: 50%"></image>
					<view style="flex: 1">代送鲜花</view>
				</view>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imgs: [
						require('../../static/imgs/1.jpg'),
						require('../../static/imgs/2.jpg'),
						require('../../static/imgs/3.png'),
					],
					content:'',
					noticeList:[],
					inter : null
					
				
			}
		},
		onLoad() {
		this.load()
		},
		onHide(){
			//清空定时器
			clearInterval(this.inter)
			this.inter = null
		},
		methods: {
			load(){
				this.$request.get('/notice/selectAll').then(res=>{
					this.noticeList = res.data || []
					
					let i = 0
					this.content = this.noticeList.length ? this.noticeList[i].content : ''
					//切换展示公告内容
					if(this.noticeList.length >1){
						this.inter = setInterval(()=>{
							i++
							if(i === this.noticeList.length){
								i =0
							}
							this.content = this.noticeList[i].content
						},5000)
					}
				})
			}

		}
	}
</script>

<style>
	.category-item {
			flex: 1;
			display: flex;
			justify-content: space-between;
			align-items: center;
			flex-direction: column;
			grid-gap: 20rpx;
		}
</style>
