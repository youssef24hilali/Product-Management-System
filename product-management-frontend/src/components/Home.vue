<template>
  <div class="container">
    <section class="mx-auto my-5" style="max-width: 23rem;">

      <div class="card testimonial-card mt-2 mb-3">
        <div class="card-up aqua-gradient"></div>
        <div class="avatar mx-auto white">
          <img class="rounded-circle img-fluid"
               alt="category avatar" src="https://medicaldialogues.in/h-upload/2022/01/21/168768-category.webp">
        </div>
        <div class="card-body text-center">
          <h4 class="card-title font-weight-bold">Categories</h4>
          <hr>
          <p><i class="fas fa-quote-left"></i> There are {{ countCategory }} categories</p>
        </div>
      </div>
    </section>
    <section class="mx-auto my-5" style="max-width: 23rem;">

      <div class="card testimonial-card mt-2 mb-3">
        <div class="card-up aqua-gradient"></div>
        <div class="avatar mx-auto white">
          <img src="https://airspeed.ph/wp-content/uploads/2020/06/The-Importance-of-Courier-Services-for-Online-Shopping-of-Essential-Items-1024x682.jpg" class="rounded-circle img-fluid"
               alt="product avatar">
        </div>
        <div class="card-body text-center">
          <h4 class="card-title font-weight-bold">Products</h4>
          <hr>
          <p><i class="fas fa-quote-left"></i> There are {{ countProduct }} products</p>
        </div>
      </div>
    </section>
  </div>

</template>

<script>
export default {
  name: "HomePage",
  data(){
    return {
      countCategory: 0,
      countProduct: 0,
    }
  },
  methods: {
    countingCategories(){
      fetch('http://localhost:8080/category/count')
          .then(response => response.json())
          .then(data => {
            this.countCategory = data;
          })
          .catch(error => {
            console.error(error);
          });
    },
    countingProducts(){
      fetch('http://localhost:8080/product/count')
          .then(response => response.json())
          .then(data => {
            this.countProduct = data;
          })
          .catch(error => {
            console.error(error);
          });
    }
  },
  mounted() {
    this.countingCategories();
    this.countingProducts();
  },
}
</script>

<style scoped>
body {
  background-color: #f5f7fa;
}

.container{
  margin-top: 60px;
  display: flex;
  column-gap: 24px;
}

.testimonial-card .card-up {
  height: 120px;
  overflow: hidden;
  border-top-left-radius: .25rem;
  border-top-right-radius: .25rem;
}

.aqua-gradient {
  background: linear-gradient(40deg, #2096ff, #05ffa3) !important;
}

.testimonial-card .avatar {
  width: 120px;
  margin-top: -60px;
  overflow: hidden;
  border: 5px solid #fff;
  border-radius: 50%;
}
</style>