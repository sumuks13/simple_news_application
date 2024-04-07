import axios from "axios";

var http =  axios.create({
  baseURL: "http://localhost:8080/",
  headers: {"tenantID" : "in"},
  params: {"category" : "general"}
});

export default http;
