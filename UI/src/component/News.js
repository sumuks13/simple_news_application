import React from "react";

function News({headlines, url, imageUrl, shortDescription, createdOn, websiteName, category}){
    return (
            <div className="col-sm-12 col-md-6 col-lg-4 mb-4" id="news-card">
                <div className="card text-light card-has-bg click-col container-fluid no-padding" style={{backgroundImage:`url(${imageUrl})`, backgroundSize: "cover"}}>
                    <div className="card-img-overlay d-flex flex-column">
                        <div className="card-body">
                            <small className="card-meta mb-2 font-color">{category}</small>
                            <h4 className="card-title mt-0"><a className = "font-color" style={{ textDecoration: 'none' }} target="_blank" rel="noreferrer" href={url}>{headlines}</a></h4>
                            {/* <small><i className="far fa-clock"></i>{}</small> */}
                        </div>
                        <div className="card-footer">
                            <div className="media">
                                <div className="media-body">
                                    <h6 className="my-0 d-block font-color">{websiteName}</h6>
                                    <small className = "font-color">{createdOn}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    );
}

export default News;
