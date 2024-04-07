function NavBar({onChange, onClick}){
    return (
        <nav className="navbar navbar-expand-lg sticky-top fs-5" data-bs-theme="dark">
        <div className="container-fluid">
          <a className="navbar-brand padleft" href="/general">Simple News Application</a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNavDropdown">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <a className="nav-link" href="/general" onClick={onClick}>General</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/business" onClick={onClick}>Business</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/science" onClick={onClick}>Science</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/technology" onClick={onClick}>Technology</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/sports" onClick={onClick}>Sports</a>
              </li>
              <li className="nav-item padleft">
                <select className="form-select fs-5" id="options" onChange={onChange}>
                    <option value="in">India</option>
                    <option value="br">Brazil</option>
                    <option value="jp">Japan</option>
                    <option value="gb">UK</option>
                    <option value="us">USA</option>
                    
                </select>                   
              </li>
            </ul>
          </div>
        </div>
      </nav>
    );
}

export default NavBar;