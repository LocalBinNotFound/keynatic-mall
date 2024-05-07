<template> 
  <div>
    <el-upload
      :action="useOss?dataObj.host:minioUploadUrl"
      :http-request="customUploadHandler"
      :data="useOss?dataObj:null"
      list-type="picture"
      :multiple="false"
      :show-file-list="showFileList"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      accept=".jpg, .jpeg, .png">
      <el-button size="small" type="primary">Click to Upload</el-button>
      <div slot="tip" class="el-upload__tip">Upload jpg/png file that is smaller than 10MB only</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="fileList[0].url" alt="">
    </el-dialog>
  </div>
</template>
<script>
  import {policy} from '@/api/oss'

  export default {
    name: 'singleUpload',
    props: {
      value: String
    },
    computed: {
      imageUrl() {
        return this.value;
      },
      imageName() {
        if (this.value != null && this.value !== '') {
          return this.value.substr(this.value.lastIndexOf("/") + 1);
        } else {
          return null;
        }
      },
      fileList() {
        return [{
          name: this.imageName,
          url: this.imageUrl
        }]
      },
      showFileList: {
        get: function () {
          return this.value !== null && this.value !== ''&& this.value!==undefined;
        },
        set: function (newValue) {
        }
      }
    },
    data() {
      return {
        dataObj: {
          publicDir:'',
          uuid:'',
          dir: '',
          host: '',
        },
        dialogVisible: false,
        useOss:true,
        minioUploadUrl:'http://localhost:8080/minio/upload',
      };
    },
    methods: {
      emitInput(val) {
        this.$emit('input', val)
      },
      handleRemove(file, fileList) {
        this.emitInput('');
      },
      handlePreview(file) {
        this.dialogVisible = true;
      },
      beforeUpload(file) {
        const isJPGorPNG = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJPGorPNG) {
          return false;
        }
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
            console.log(_self.dataObj);
          }).catch(err => {
            console.log(err)
            reject(false)
          })
        })
      },
      handleUploadSuccess(res, file) {
        this.showFileList = true;
        this.fileList.pop();
        let url = `${this.dataObj.publicDir}/${this.dataObj.dir}/${this.dataObj.uuid}.jpg`;
        console.log(url)
        if(!this.useOss){
          url = res.data.url;
        }
        this.fileList.push({name: file.name, url: url});
        this.emitInput(this.fileList[0].url);
      },
      customUploadHandler(uploadEvent) {
        const file = uploadEvent.file;
        const config = {
          method: 'PUT',
          headers: {
            'Content-Type': file.type  // Ensure this matches the type expected by the presigned URL
          },
          body: file
        };

        fetch(uploadEvent.action, config)
          .then(response => {
            if (response.ok) {
              uploadEvent.onSuccess();
            } else {
              uploadEvent.onError(new Error('Upload failed with status: ' + response.status));
            }
          })
          .catch(error => {
            uploadEvent.onError(error);
          });
      }
    }
  }
</script>
<style>

</style>


