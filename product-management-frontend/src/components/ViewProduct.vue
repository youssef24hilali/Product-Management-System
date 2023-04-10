<template>
  <div class="container">
    <div class="download">
      <button @click="exportToPdf" class="btn btn-success">Download</button>
    </div>
    <div class="element" ref="element" id="element">
      <div class="form-group row">
        <label for="staticEmail" class="col-lg-2 col-form-label">Product Name</label>
        <div class="col-lg-8">
          <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model=product.nom>
        </div>
      </div>
      <div class="form-group row">
        <label for="staticEmail" class="col-lg-2 col-form-label">Product Description</label>
        <div class="col-lg-8">
          <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model= product.description>
        </div>
      </div>
      <div class="form-group row">
        <label for="staticEmail" class="col-lg-2 col-form-label">Product Slug</label>
        <div class="col-lg-8">
          <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model= product.slug>
        </div>
      </div>
      <div class="form-group row">
        <label for="staticEmail" class="col-lg-2 col-form-label">Product Image</label>
        <div class="col-lg-8">
          <img :src="'/assets/images/' + product.imagePath" class="img-responsive img-rounded"
               style="max-height: 200px; max-width: 300px; border-radius: 10px;" alt="image">
        </div>
      </div><div class="form-group row">
        <label for="staticEmail" class="col-lg-2 col-form-label">Category Name</label>
        <div class="col-lg-8">
          <input type="text" readonly class="form-control-plaintext" v-if="product.category" id="staticEmail" v-model= product.category.nom>
        </div>
      </div>
      <div class="form-group row">
        <label for="staticEmail" class="col-lg-2 col-form-label">Product Creation Date</label>
        <div class="col-lg-8">
          <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model=product.dateCreation>
        </div>
      </div>
      <div class="form-group row">
        <label for="staticEmail" class="col-lg-2 col-form-label">Product Modification Date</label>
        <div class="col-lg-8">
          <input type="text" readonly class="form-control-plaintext" id="staticEmail" v-model=product.dateModification>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import ProductService from "@/services/ProductService";
import router from "@/router";
import html2pdf from "html2pdf.js";

export default {
  name: "ViewProduct",
  data(){
    return{
      product: {
        nom: "",
        description: "",
        slug: "",
        dateCreation: null,
        dateModification: null,
        category: null,
        imagePath: ""
      }
    }
  },
  methods: {
    getProduct(){
      ProductService.getProduct(this.$route.params.id).then((res) => {
        console.log(res.data)
        this.product = res.data;
        this.product.dateCreation = this.formatDate(this.product.dateCreation)
        this.product.dateModification = this.formatDate(this.product.dateModification)
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
    exportToPdf() {
      html2pdf(document.getElementById("element"), {
        margin: 1,
        filename: "product.pdf",
      });
      router.push({path: '/products'})
    }
  },
  mounted() {
    this.getProduct()
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