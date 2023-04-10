<template>
  <h1 style="margin-bottom: 30px; margin-top: 10px" class="text-center">Categories</h1>
  <table class="table table-striped table-bordered display" id="example">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Slug</th>
      <th>Description</th>
      <th>Date de creation</th>
      <th>Date de modification</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(category, index) in categories" :key="index">
      <td>{{index + 1}}</td>
      <td>{{category.nom}}</td>
      <td>{{category.slug}}</td>
      <td>{{category.description}}</td>
      <td>{{formatDate(category.dateCreation)}}</td>
      <td>{{formatDate(category.dateModification)}}</td>
      <td>
        <div class="btn-container">
          <router-link :to="'/updateCategory/' + category.id" ><button class="btn-warning"><i class="fa-regular fa-pen-to-square"></i></button></router-link>
          <button @click="deleteCat(category.id, index)" class="btn-danger"><i class="fa-regular fa-trash-can"></i></button>
          <router-link :to="'/viewCategory/' + category.id"><button  class="btn-primary"><i class="fa-solid fa-eye"></i></button></router-link>
        </div>
      </td>
    </tr>

    </tbody>
  </table>


</template>
<script>

import 'bootstrap/dist/css/bootstrap.min.css';
import 'jquery/dist/jquery.min.js';
import "datatables.net-dt/js/dataTables.dataTables"
import "datatables.net-dt/css/jquery.dataTables.min.css"
import "datatables.net-buttons/js/dataTables.buttons.js"
import "datatables.net-buttons/js/buttons.colVis.js"
import "datatables.net-buttons/js/buttons.flash.js"
import "datatables.net-buttons/js/buttons.html5.js"
import "datatables.net-buttons/js/buttons.print.js"
import CategoryService from "@/services/CategoryService";
import $ from 'jquery';

export default {
  name: "CategoryTable",
  mounted(){
    this.getCategories();
  },
  data: function() {
    return {
      categories:[]
    }
  },
  methods: {
    getCategories(){
      CategoryService.listCategories().then((res)=>
      {
        this.categories = res.data;
        setTimeout(function(){
              $('#example').DataTable(
                  {
                    bDestroy: true,
                    responsive: true,
                    pagingType: 'full_numbers',
                    processing: true,
                    dom:  'B<"clear">lfrtip',
                    buttons: ['csv', 'print'
                    ]
                  }
              );
            },
            1000
        );
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
    deleteCat(id, index) {
        CategoryService.deleteCategory(id).then(res => {
          const table = $('#example').DataTable();
          table.row(index).remove().draw();
          res.data;
          this.getCategories()
        }).catch(function (error){
          alert(error.response.data.message);
        })
    }
  }
}
</script>
<style>
@import url("https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css");
@import url("https://cdn.datatables.net/1.11.4/css/dataTables.bootstrap5.min.css");


 /* CSS */

button.dt-button, div.dt-button, a.dt-button, input.dt-button {
 align-items: center;
 appearance: none;
 background-image: radial-gradient(100% 100% at 100% 0, #5adaff 0, #5468ff 100%);
 border: 0;
 border-radius: 6px;
 box-shadow: rgba(45, 35, 66, .4) 0 2px 4px,rgba(45, 35, 66, .3) 0 7px 13px -3px,rgba(58, 65, 111, .5) 0 -3px 0 inset;
 box-sizing: border-box;
 color: #fff;
 cursor: pointer;
 display: inline-flex;
 font-family: "JetBrains Mono",monospace;
 height: 48px;
 justify-content: center;
 line-height: 1;
 list-style: none;
 overflow: hidden;
 padding-left: 16px;
 padding-right: 16px;
 position: relative;
 text-align: left;
 text-decoration: none;
 transition: box-shadow .15s,transform .15s;
 user-select: none;
 -webkit-user-select: none;
 touch-action: manipulation;
 white-space: nowrap;
 will-change: box-shadow,transform;
 font-size: 18px;
}

button:focus {
  box-shadow: #3c4fe0 0 0 0 1.5px inset, rgba(45, 35, 66, .4) 0 2px 4px, rgba(45, 35, 66, .3) 0 7px 13px -3px, #3c4fe0 0 -3px 0 inset;
}

td{
  text-align: center;
  vertical-align: middle;
}

button:hover {
  box-shadow: rgba(45, 35, 66, .4) 0 4px 8px, rgba(45, 35, 66, .3) 0 7px 13px -3px, #3c4fe0 0 -3px 0 inset;
  transform: translateY(-2px);
}
.btn-warning .btn-primary .btn-danger{
  color: white;
  border-radius: 3px;
}
.btn-warning:hover{
  box-shadow: rgba(45, 35, 66, .4) 0 4px 8px, rgba(45, 35, 66, .3) 0 7px 13px -3px, #ffce00 0 -3px 0 inset;
  transform: translateY(-2px);
}
.btn-danger:hover{
  box-shadow: rgba(45, 35, 66, .4) 0 4px 8px, rgba(45, 35, 66, .3) 0 7px 13px -3px, #ac3c3c 0 -3px 0 inset;
  transform: translateY(-2px);
  color: black;
}
.btn-primary:hover{
  box-shadow: rgba(45, 35, 66, .4) 0 4px 8px, rgba(45, 35, 66, .3) 0 7px 13px -3px, #135dd0 0 -3px 0 inset;
  transform: translateY(-2px);
  color: black;
}

button:active {
  box-shadow: #3c4fe0 0 3px 7px inset;
  transform: translateY(2px);
}

.btn-container{
  display: flex;
  gap: 10px;
  align-items: center;
}

.dt-button.buttons-csv.buttons-html5{
  margin-right: 100px;
}

</style>

<!--
<template>
  <div class="row">
    <div class="col-lg-12 offset-lg-0">
      <div class="table-responsive">
        <DataTable :data="categories" :columns="columns" class="table table-striped table-bordered display"
                   :options="{responsive:true, autoWidth: false, dom: 'Blfrtip', buttons:[], language:{
          search: 'Search', zeroRecords: 'Aucun categorie',
          paginate:{first:'First', previous:'Previous', next: 'Next', last:'Last'}
        }}">
          <thead>
          <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Slug</th>
            <th>Date de creation</th>
            <th>Date de modification</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>View</th>
          </tr>
          </thead>
        </DataTable>
      </div>
    </div>
  </div>

</template>

<script>
import moment from 'moment';
import CategoryService from "@/services/CategoryService";
import DataTable from 'datatables.net-vue3';
import DataTableLib from 'datatables.net-bs5';
import Buttons from 'datatables.net-buttons-bs5';
import ButtonsHtml5 from 'datatables.net-buttons/js/buttons.html5';
import print from 'datatables.net-buttons/js/buttons.print';
import pdfmake from 'pdfmake';
import 'datatables.net-responsive-bs5'
import JSZip from 'jszip';
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
window.JsZip = JSZip;
DataTable.use(DataTableLib);
DataTable.use(pdfmake);
DataTable.use(ButtonsHtml5);

export default {
  name: 'HelloWorld',
  components:{
    DataTable,
    // eslint-disable-next-line vue/no-unused-components
    Buttons,
    // eslint-disable-next-line vue/no-unused-components
    print,
  },
  methods: {
    getCategories(){
      CategoryService.listCategories().then((res) => {
        this.categories = res.data;
      })
    },
    deleteRow(id){
      CategoryService.deleteCategory(id);
    }
  },
  data(){
    return{
      categories: null,
      columns:[
        {data: null,
          render: function (data, type, row, meta)
          {return `${meta.row + 1}`}
        },
        {data: 'nom'},
        {data: 'description'},
        {data: 'slug'},
        {data: null, render: function dateTime(dateCreation) {
            return moment(dateCreation).format('LLLL');
          }},
        {data: null, render: function dateTime(dateModification) {
      return moment(dateModification).format('LLLL');
    }},
        {
          data: "id",
          className: "dt-center editor-editor",
          render: function (data){
            return  `<button data-id="${data}" style="color: #1e7e34" @click="${deleteRow(data)}"><i class="fa-solid fa-trash-can"></i></button> `
          },
          orderable: false
        },
        {
          data: "id",
          className: "dt-center editor-editor",
          render: function (data){
            return  `<button data-id="${data}" style="color: #1e7e34" @click="${deleteRow(data)}"><i class="fa-solid fa-trash-can"></i></button> `
          },
          orderable: false
        },
        {
          data: "id",
          className: "dt-center editor-editor",
          render: function (data){
            return  `<button data-id="${data}" style="color: #1e7e34" @click="${deleteRow(data)}"><i class="fa-solid fa-trash-can"></i></button> `
          },
          orderable: false
        },
      ]
    }
  },
  mounted() {
    this.getCategories();
  }
}
</script>

&lt;!&ndash; Add "scoped" attribute to limit CSS to this component only &ndash;&gt;
<style scoped>
@import "datatables.net-bs5";

h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>-->
