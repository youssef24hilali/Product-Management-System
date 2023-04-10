import axios from 'axios';
import router from "@/router";

const base_url = "http://localhost:8080/product/";

class ProductService{

    listProducts(){
        return axios.get(base_url + "newList");
    }

    addProduct(formData){
        axios.post(base_url + "add", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then((res) => {
                console.log(res.data);
                router.push({path: '/products'})
            }).catch(function (error){
            console.log(error);
        })
    }

    updateProduct(id, formData){
        axios.put(base_url + "update/" + id, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then((res) => {
                console.log(res.data);
                router.push({path: '/products'})
            }).catch(function (error){
            console.log(error)
        })
    }

    getProduct(id){
        return axios.get(base_url + "getProduct/" + id)
    }

    deleteProduct(id){
        return axios.put(base_url + "update-delete/" + id)
    }

    counting(){
        return axios.get(base_url + "count");
    }

}

export default new ProductService();