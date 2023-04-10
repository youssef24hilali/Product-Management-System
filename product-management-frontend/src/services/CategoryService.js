import axios from 'axios';
import router from "@/router";

const base_url = "http://localhost:8080/category/";

class CategoryService{
    listCategories(){
        return axios.get(base_url + "newList");
    }

    addCategory(category){
        axios.post(base_url + "add", category)
            .then((res) => {
                console.log(res.data);
                router.push({path: '/categories'})
            }).catch(function (error){
            console.log(error);
        })
    }

    updateCategory(id, category){
        axios.put(base_url + "update/" + id, category)
            .then((res) => {
                console.log(res.data);
                router.push({path: '/categories'})
            }).catch(function (error){
            console.log(error)
        })
    }

    getCategory(id){
        return axios.get(base_url + "getCategory/" + id)
    }

    deleteCategory(id){
        return axios.put(base_url + "up-de/" + id)
    }

    counting(){
        return axios.get(base_url + "count");
    }
}

export default new CategoryService();