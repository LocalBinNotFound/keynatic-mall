<template> 
  <coupon-detail :isEdit="false"></coupon-detail>
</template>
<script>
  import CouponDetail from './components/CouponDetail.vue'
  export default {
    name: 'addCoupon',
    components: { CouponDetail }
  }
</script>
<style scoped>
</style>


