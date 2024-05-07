import request from '@/utils/request'
export function policy() {
  return request({
    url:'/amazon/s3/policy',
    method:'get',
  })
}
