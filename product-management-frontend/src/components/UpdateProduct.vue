<template>
  <div class="container">
    <div class="signup-content">
      <form @submit.prevent="updateProduct" method="post" class="signup-form">
        <h2 class="form-title" style="margin-bottom: 60px; margin-top: 20px">Update Product</h2>
        <div class="form-group">
          <label for="name">Product Name<span style="color: red;">*</span></label>
          <input autocomplete="off" ref="nameInput" :class="{ 'is-invalid': isNameInvalid && touched }" v-model="product.nom" type="text" class="form-control" name="name" id="name" placeholder="Product Name"/>
          <div class="invalid-feedback" v-if="formErrors[0] !== null">
            {{ formErrors[0] }}
          </div>
        </div>
        <div class="form-group">
          <label for="description">Product Description<span style="color: red;">*</span></label>
          <textarea autocomplete="off" ref="descriptionInput" :class="{ 'is-invalid': isDescriptionInvalid && touched }" v-model="product.description" type="text" class="form-control" name="description" id="description" placeholder="Product Description"/>
          <div class="invalid-feedback" v-if="formErrors[1] !== null">
            {{ formErrors[1] }}
          </div>
        </div>
        <div class="form-group">
          <label for="image">Product Image<span style="color: red;">*</span></label>
          <input autocomplete="off" ref="imageInput" :class="{ 'is-invalid': isImageInvalid && touched }" type="file" class="form-control" name="image" id="image" @change="onFileChange"/>
          <div class="invalid-feedback" v-if="formErrors[2] !== null">
            {{ formErrors[2] }}
          </div>
        </div>
        <div class="form-group">
          <label for="cat">Product Category Name<span style="color: red;">*</span></label>
          <select class="form-select" ref="catInput" :class="{ 'is-invalid': isCatInvalid && touched }" v-model="product.categoryId" name="cat" id="cat">
            <option v-for="category in categories" :key="category.id" :value="category.id">{{ category.nom }}</option>
          </select>
          <div class="invalid-feedback" v-if="formErrors[3] !== null">
            {{ formErrors[3] }}
          </div>
        </div>
        <div class="form-group">
          <input type="submit" name="submit" id="submit" class="btn btn-warning send-button" value="Update Product"/>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import ProductService from "@/services/ProductService";
import CategoryService from "@/services/CategoryService";

export default {
  name: "UpdateProduct",
  data() {
    return {
      formErrors: [],
      touched: false,
      categories: null,
      product: {
        nom: "",
        description: "",
        image: "",
        categoryId: null
      }
    }
  },
  methods: {
    getCategories(){
      CategoryService.listCategories().then((res)=> {
        this.categories = res.data;
      }).catch(function (error) {
        console.log(error)
      })
    },
    onFileChange(event) {
      this.product.image = event.target.files[0];
    },
    getProduct() {
      ProductService.getProduct(this.$route.params.id).then((res) => {
        console.log(res.data)
        this.product = res.data;
      }).catch(function (error) {
        console.log(error)
      })
    },
    updateProduct() {

      const nameInput = this.$refs.nameInput;
      const descriptionInput = this.$refs.descriptionInput;
      const imageInput = this.$refs.imageInput;
      const categoryInput = this.$refs.catInput;

      this.formErrors = [];

      if (!this.product.nom) {
        this.formErrors.push("Product name can't be empty");
        nameInput.classList.add('is-invalid');
      } else {
        this.formErrors.push("");
        nameInput.classList.remove('is-invalid');
      }

      if (!this.product.description) {
        this.formErrors.push("Product description can't be empty");
        descriptionInput.classList.add('is-invalid');
      } else {
        this.formErrors.push("");
        descriptionInput.classList.remove('is-invalid');
      }

      if (!this.product.image) {
        this.formErrors.push("Product image can't be empty");
        imageInput.classList.add('is-invalid');
      } else {
        this.formErrors.push("");
        imageInput.classList.remove('is-invalid');
      }

      if (!this.product.categoryId) {
        this.formErrors.push("Product category name can't be empty");
        categoryInput.classList.add('is-invalid');
      } else {
        this.formErrors.push("");
        categoryInput.classList.remove('is-invalid');
      }

      this.touched = true;

      if (this.formErrors[0] === "" && this.formErrors[1] === "" && this.formErrors[2] === "" && this.formErrors[3] === "") {
        let formData = new FormData();
        formData.append('nom', this.product.nom);
        formData.append('description', this.product.description);
        formData.append('image', this.product.image);
        formData.append('categoryId', this.product.categoryId);
        console.log("Id : " + this.product.categoryId)
        ProductService.updateProduct(this.$route.params.id, formData)
      } else {
        console.log("Fill in all fields")
      }
    }
  },
  mounted() {
    this.getProduct();
    this.getCategories();
  },
  computed: {
    isNameInvalid() {
      return !this.product.nom;
    },
    isDescriptionInvalid() {
      return !this.product.description;
    },
    isImageInvalid() {
      return !this.product.image;
    },
    isCatInvalid() {
      return !this.product.categoryId;
    }
  }
}
</script>

<style scoped>

.container {
  width: 660px;
  text-align: center;
  padding: 20px;
}

form {
  width: 500px;
  margin: 20px auto;
}

.form-group {
  margin-bottom: 20px;
}

input[type="text"]{
  padding: 10px 15px;
  background-color: #efefef;
  border: 1px solid #ccc;
  width: 93%;
  border-radius: 5px;
}

input[type="text"].is-invalid, textarea.is-invalid, input[type="file"].is-invalid, select.is-invalid{
  background-color: #fdeeee;
  border-color: #ff4343;
}

input[type="file"]{
  height: 45px;
}

.form-control {
  padding: 10px 15px;
  background-color: #efefef;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 100%;
}

label {
  display: block;
  margin-bottom: 15px;
  font-size: 18px;
}

textarea {
  padding: 10px 15px;
  height: 120px;
  resize: none;
}

.btn.send-button {
  display: block;
  height: 50px;
  border-color: transparent;
  padding: 5px 20px;
  width: 100%;
  font-size: 24px;
}

textarea:focus{
  outline: none;
}

</style>