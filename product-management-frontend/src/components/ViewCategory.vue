<template>
<div class="container">
  <div class="download">
    <button @click="exportToPdf" class="btn btn-success">Download</button>
  </div>
  <div class="element" id="element">
    <div class="form-group row">
      <label for="staticEmail" class="col-lg-2 col-form-label">Category Name</label>
      <div class="col-lg-8">
        <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model=category.nom>
      </div>
    </div>
    <div class="form-group row">
      <label for="staticEmail" class="col-lg-2 col-form-label">Category Description</label>
      <div class="col-lg-8">
        <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model= category.description>
      </div>
    </div>
    <div class="form-group row">
      <label for="staticEmail" class="col-lg-2 col-form-label">Category Slug</label>
      <div class="col-lg-8">
        <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model= category.slug>
      </div>
    </div>
    <div class="form-group row">
      <label for="staticEmail" class="col-lg-2 col-form-label">Category Creation Date</label>
      <div class="col-lg-8">
        <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model=category.dateCreation>
      </div>
    </div>
    <div class="form-group row">
      <label for="staticEmail" class="col-lg-2 col-form-label">Category Modification Date</label>
      <div class="col-lg-8">
        <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model=category.dateModification>
      </div>
    </div>
  </div>
</div>
</template>

<script>

import CategoryService from "@/services/CategoryService";
import html2pdf from 'html2pdf.js';
import router from "@/router";

export default {
  name: "ViewCategory",
  data(){
    return{
      category: {
        nom: "",
        description: "",
        slug: "",
        dateCreation: null,
        dateModification: null
      }
    }
  },
  methods: {
    getCategory(){
      CategoryService.getCategory(this.$route.params.id).then((res) => {
        console.log(res.data)
        this.category = res.data;
        this.category.dateCreation = this.formatDate(this.category.dateCreation)
        this.category.dateModification = this.formatDate(this.category.dateModification)
      }).catch(function (error){
        console.log(error)
      })
    },
    formatDate(date){
      let day = new Date(date).getDate();
      let mounth = new Date(date).getMonth()+1;
      let year = new Date(date).getFullYear();
      let hour = new Date(date).getHours();
      let second = new Date(date).getSeconds();
      let minute = new Date(date).getMinutes();
      return day + "-" + mounth + "-" + year + "  " + hour + ":" + minute + ":" + second;
    },
    exportToPdf(){
      html2pdf(document.getElementById("element"), {
        margin: 1,
        filename: "category.pdf",
      });
      router.push({path: '/categories'})
    }
  },
  mounted() {
    this.getCategory()
  }
}
</script>

<style scoped>

input[type=text]:not(.browser-default){
  border-bottom: none;
  margin-left: 150px;
  padding-top: 15px;
  font-weight: bolder;
}

.col-form-label{
  padding-top: 20px;
  font-size: 17px;
  font-weight: bold;
  margin-left: 100px;
  color: #383838;
}

.container{
  margin-top: 10px;
  align-content: center;
  background-color: #ffffff;
  height: 100%;
}

.element{
  padding-left: 150px;
  background-color: #ececec;
  margin-bottom: 10px;
  border: 1px solid #3f3f3f;
  border-radius: 15px;
}

.download{
  background-color: #ececec;
  margin-bottom: 10px;
  border: 1px solid #3f3f3f;
  border-radius: 5px;
  margin-top: 30px;
  padding-top: 10px;
  padding-bottom: 10px;
  align-items: center;
}


</style>