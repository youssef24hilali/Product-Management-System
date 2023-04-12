<template>
  <div class="container">
    <div class="signup-content">
      <form @submit.prevent="updateCategory" method='post' class="signup-form">
        <h2 class="form-title" style="margin-bottom: 60px; margin-top: 20px">Update Category</h2>
        <div class="form-group position-relative">
          <label for="nom">Category Name<span style="color: red;">*</span></label>
          <input autocomplete="off" ref="nameInput" :class="{ 'is-invalid': isNameInvalid && touched }" v-model="category.nom" type="text" class="form-control" name="nom" id="name" placeholder="Category Name"/>
          <div class="invalid-feedback" v-if="formErrors[0] !== null">
            {{ formErrors[0] }}
          </div>
        </div>
        <div class="form-group position-relative">
          <label for="description">Category Description<span style="color: red;">*</span></label>
          <textarea autocomplete="off" ref="descriptionInput" :class="{ 'is-invalid': isDescriptionInvalid && touched }" v-model="category.description" type="text" class="form-control" name="description" id="description" placeholder="Category Description"/>
          <div class="invalid-feedback" v-if="formErrors[1] !== null">
            {{ formErrors[1] }}
          </div>
        </div>
        <div class="form-group">
          <input type="submit" name="submit" id="submit" class="btn btn-warning send-button" value="Update Category"/>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import CategoryService from "@/services/CategoryService";

export default {
  name: "UpdateCategory",
  data(){
    return{
      formErrors: [],
      touched: false,
      category: {
        nom: "",
        description: ""
      }
    }
  },
  methods: {
    getCategory(){
      CategoryService.getCategory(this.$route.params.id).then((res) => {
        console.log(res.data)
        this.category = res.data;
      }).catch(function (error){
        console.log(error)
      })
    },
    updateCategory(){
      const nameInput = this.$refs.nameInput;
      const descriptionInput = this.$refs.descriptionInput;
      this.formErrors = [];

      if (!this.category.nom) {
        this.formErrors.push("Category name can't be empty");
        nameInput.classList.add('is-invalid');
      } else {
        this.formErrors.push("");
        nameInput.classList.remove('is-invalid');
      }

      if (!this.category.description) {
        this.formErrors.push("Category description can't be empty");
        descriptionInput.classList.add('is-invalid');
      } else {
        this.formErrors.push("");
        descriptionInput.classList.remove('is-invalid');
      }

      this.touched = true;

      if (this.formErrors[0] === "" && this.formErrors[1] === "") {
        CategoryService.updateCategory(this.$route.params.id,{
          nom: this.category.nom,
          description: this.category.description
        })
      } else {
        console.log("Fill in all fields")
      }
    }
  },
  computed: {
    isNameInvalid() {
      return !this.category.nom;
    },
    isDescriptionInvalid() {
      return !this.category.description;
    }
  },
  mounted() {
    this.getCategory()
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

input[type="text"].is-invalid, textarea.is-invalid{
  background-color: #fdeeee;
  border-color: #ff4343;
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
</style>