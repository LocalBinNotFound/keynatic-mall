<template> 
  <div>
    <el-upload
      :action="useOss?dataObj.host:minioUploadUrl"
      :data="useOss?dataObj:null"
      list-type="picture-card"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :limit="maxCount"
      :on-exceed="handleExceed"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>
<script>
  import {policy} from '@/api/oss'

  export default {
    name: 'multiUpload',
    props: {
      value: Array,
      maxCount:{
        type:Number,
        default:5
      }
    },
    data() {
      return {
        dataObj: {
          publicDir:'',
          uuid:'',
          dir: '',
          host: ''
        },
        dialogVisible: false,
        dialogImageUrl:null,
        useOss:true, //oss->true;MinIO->false
        minioUploadUrl:'http://localhost:8080/minio/upload',
      };
    },
    computed: {
      fileList() {
        let fileList=[];
        for(let i=0;i<this.value.length;i++){
          fileList.push({url:this.value[i]});
        }
        return fileList;
      }
    },
    methods: {
      emitInput(fileList) {
        let value=[];
        for(let i=0;i<fileList.length;i++){
          value.push(fileList[i].url);
        }
        this.$emit('input', value)
      },
      handleRemove(file, fileList) {
        this.emitInput(fileList);
      },
      handlePreview(file) {
        this.dialogVisible = true;
        this.dialogImageUrl=file.url;
      },
      beforeUpload(file) {
        let _self = this;
        if(!this.useOss){
          return true;
        }
        return new Promise((resolve, reject) => {
          policy().then(response => {
            _self.dataObj.publicDir = response.data.publicDir;
            _self.dataObj.uuid = response.data.uuid;
            _self.dataObj.dir = response.data.dir;
            _self.dataObj.host = response.data.host;
            resolve(true)
          }).catch(err => {
            console.log(err)
            reject(false)
          })
        })
      },
      handleUploadSuccess(res, file) {
        let url = `${this.dataObj.publicDir}/${this.dataObj.dir}/${this.dataObj.uuid}.jpg`;
        if(!this.useOss){
          url = res.data.url;
        }
        this.fileList.push({name: file.name,url:url});
        this.emitInput(this.fileList);
      },
      handleExceed(files, fileList) {
        this.$message({
          message: 'You can only upload '+this.maxCount+' images',
          type: 'warning',
          duration:1000
        });
      },
    }
  }
</script>
<style>

</style>


