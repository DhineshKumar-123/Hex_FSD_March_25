import { useState } from "react";
import users from "../../data/users";

function Login ()
{

    const[username,setUsername] = useState(null);
    const[password,setPassword] = useState(null);
    const[msgUsername,setMsgUsername] = useState(null);
    const[msgPassword,setMsgPassword] = useState(null);
    const[userData,setUserData] = useState(users);
    

    const login = ()=>
    {
        let isCorrect = false; 

        if(username === null || username === "" || username === undefined)
            {
            setMsgUsername("Please Enter Username")
            return;
            }
            else{
                setMsgUsername(null);
            }
        if(password === null || password === "" || password === undefined)
            {
            setMsgPassword("Please Enter Password")
            return 
            }
            else
            {
                setMsgPassword(null);
            }


            //Checking the username and Password given by enduser with the created dummy data users file 
            userData.forEach(u => {
                if(u.username === username && u.password === password )
                {
                    alert("U are legit and your role is " + u.role);
                    isCorrect(true)
                }
                if(isCorrect === false)
                {
                    setMsgUsername("Invalid Credentials")
                }
            });

        // console.log('i am on line 17')
        // alert('alright, u r ready to be tested!!!!')
    }


    return(
        <div>
            <div className="container-fluid">
                <div className="row-mb-4">
                    <div className="col-lg-12">
                        <nav class="navbar navbar-light bg-light">
                            <div class="container-fluid">
                                <span class="navbar-brand mb-0 h1">Navbar</span>
                            </div>
                        </nav>
                    </div>

                </div><br /><br /><br /><br />
                <div className="row">
                    <div className="col-sm-4">

                    </div>
                    <div className="col-sm-4">
                    <div class="card">
                            <div className="card-header">
                                Login
                            </div>
                            <div class="card-body">
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
                                    onChange={ ($event) => {setUsername($event.target.value); 
                                    setMsgUsername(null) }} />

                                </div>
                                <div className="mb-4">
                                    <label > Password </label>
                                    <input type="password" className="form-control" 
                                    onChange={ ($event) => {setPassword($event.target.value); 
                                    setMsgPassword(null) }} />
                                </div>

                                <div className="mb-4">
                                {/* mb-margin bottom */}
                                <button type="button" class="btn btn-primary" 
                                onClick={() => {login()}}> Login </button>
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