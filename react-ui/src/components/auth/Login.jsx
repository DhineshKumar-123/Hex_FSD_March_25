import { useState } from "react";
import users from "../../data/users";
import { useNavigate } from "react-router";
import axios from "axios";

function Login() {

    const [username, setUsername] = useState(null);
    const [password, setPassword] = useState(null);
    const [msgUsername, setMsgUsername] = useState(null);
    const [msgPassword, setMsgPassword] = useState(null);
    const [userData, setUserData] = useState(users);
    const navigate = useNavigate();


    const login = () => {
        let isCorrect = false;

        if (username === null || username === "" || username === undefined) {
            setMsgUsername("Please Enter Username")
            return;
        }
        else {
            setMsgUsername(null);
        }
        if (password === null || password === "" || password === undefined) {
            setMsgPassword("Please Enter Password")
            return
        }
        else {
            setMsgPassword(null);
        }

        //Now We are Implementing the real checking of user credentials instead of user.js
        //check username password by calling API else use file data now token generation

        //---- First create the object for colecting the data of username and password that needs to be sent to the api request
        let body = {
            "username": username,
            "password": password
        }
        axios.post("http://localhost:8080/api/auth/token/generate", body)
            .then(response => {
                // console.log(response) //Response Works fine
                //Now store this token and username in the localstorage of the webbrowser
                let token = response.data.token;
                localStorage.setItem('token', token);
                localStorage.setItem('username', username)

                // console.log(token);
                axios.get("http://localhost:8080/api/auth/user/details",
                    {
                        headers: {
                            "Authorization": `Bearer ${token}`  //this will automatically fills the authorization header int he request of jwtfilter
                        }
                    }
                )
                    .then(resp => {
                        // console.log(res);
                        console.log(resp)

                        switch (resp.data.role) {
                            case 'CUSTOMER':
                                navigate("/customer")
                                break;
                            case 'VENDOR':
                                //navigate to vendor dashboard
                                break;
                            case 'ADMIN':
                                //navigate to executive dashboard
                                break;
                            default:
                                break;
                        }
                    })
                    .catch(err => {
                        setMsgUsername("Invalid Credentials")
                        console.log(err)
                    })
            })
            .catch(err => {
                setMsgUsername("Invalid Credentials")
                console.log(err)


            })
    }


    return (
        <div>
            <div className="container-fluid">
                <div className="row-mb-4">
                    <div className="col-lg-12">
                        <nav className="navbar navbar-light bg-light">
                            <div className="container-fluid">
                                <span className="navbar-brand mb-0 h1">Navbar</span>
                            </div>
                        </nav>
                    </div>

                </div><br /><br /><br /><br />
                <div className="row">
                    <div className="col-sm-4">

                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-header">
                                Login
                            </div>
                            <div className="card-body">
                                {
                                    msgUsername === null ? "" : <div className="mb-4">
                                        {msgUsername}
                                    </div>

                                }
                                {
                                    msgPassword === null ? "" : <div className="mb-4">
                                        {msgPassword}
                                    </div>
                                }
                                <div className="mb-4">
                                    <label > Username </label> {/*This e is called as event*/}
                                    <input type="text" className="form-control"
                                        onChange={($event) => {
                                            setUsername($event.target.value);
                                            setMsgUsername(null)
                                        }} />

                                </div>
                                <div className="mb-4">
                                    <label > Password </label>
                                    <input type="password" className="form-control"
                                        onChange={($event) => {
                                            setPassword($event.target.value);
                                            setMsgPassword(null)
                                        }} />
                                </div>

                                <div className="mb-4">
                                    {/* mb-margin bottom */}
                                    <button type="button" className="btn btn-primary"
                                        onClick={() => { login() }}> Login </button>
                                </div>
                            </div>
                            <div className="card-footer">
                                Don't have an Account? Sign Up <br />
                                Forgot Password
                            </div>
                        </div>
                    </div>

                    <div className="col-sm-4">

                    </div>
                </div>

            </div>
        </div>
    )
}

export default Login;