import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min.js';
import './App.css';
import NavBar from './component/NavBar';
import News from './component/News';
import React, { useState, useEffect } from 'react';
import http from './http';


function App() {

  const [news, setNews] = useState([""]);
  const [data, setData] = useState({
    category : "general",
    tenantID : "in"
  });

  useEffect(()=> {
      http.defaults.params.category = data.category;
      http.defaults.headers.tenantID = data.tenantID;
      http.get('/newsapplication')
        .then(response => {
          setNews(response.data);

        })
        .catch(error => {
          console.error(error);
        });
    }, [data]);

    const newsList = news.map((news, index) => {
      return(
        <News
          headlines = {news.headlines}
          url = {news.url}
          imageUrl = {news.imageUrl}
          shortDescription = {news.shortDescription}
          createdOn = {news.createdOn}
          websiteName = {news.websiteName}
          category = {news.category}
        />
      )
    })

    
  function handleChange(event){
    setData({...data, tenantID : event.target.value});

  }
  function handleClick(event){
    event.preventDefault();
    setData({...data, category : event.target.innerHTML});

  }

  return (
    <>
      <NavBar onChange={handleChange} onClick={handleClick} />
      <section class="wrapper">
        <div class="container">
          {/* <div class="row">
            <div class="col text-center mb-5">
              <h1 class="display-6 font-weight-bolder">{newsList[0].props.headlines}</h1>
            </div>
          </div> */}
          {/* {news.map((item, index) => {
            <News key={index} value={item}/>
          })} */}
          <div class="row">
            {newsList}
          </div>
        </div>
      </section>
      </>
  );
}

export default App;
