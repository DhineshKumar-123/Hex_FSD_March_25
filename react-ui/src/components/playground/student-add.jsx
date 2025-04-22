import axios from "axios"
import { useState } from "react"

function StudentAdd () {

    const[name,setName] = useState(null)
    const[email,setEmail] = useState(null)
    const[age,setAge] = useState(null)

    //create the function for adding the student into db where the user type into the forms are the inputs(requestBody)
    const addStudent = async($event) =>{
        $event.preventDefault();//this prevents the page reloading once the form get submitted 
        console.log(name);
        console.log(email);
        console.log(age);

        //creating obj to store them into a single object and then pass it to the api to store in db
        let obj = {
            "name":name,
            "email":email,
            "age":age
        }

        //Now the response and post them through our own springboot api
        //if we want to use await the function is must in async
        let response = await axios.post("http://localhost:8080/api/student/add",obj)//telling the react that this is url and passing the obj of data
        console.log(response);//showing whatever the response is return from the springboot app withn the console
    }

    return(
        <div>
            <h2>Student Adding Page Checking using own api</h2>
            <form onSubmit={($event) => addStudent($event)}>
                <div className="mb-4">
                    <label>Enter Student Name: </label>
                    <input type="text"
                        onChange={($event) => { setName($event.target.value) }} />
                </div>
                <div className="mb-4">
                    <label>Enter Student Email: </label>
                    <input type="text"
                        onChange={($event) => { setEmail($event.target.value) }} ></input>
                </div>
                <div className="mb-4">
                    <label>Enter Student Age: </label>
                    <input type="number"
                        onChange={($event) => { setAge($event.target.value) }} ></input>
                </div>
                <div className="mb-4">

                    <input type="submit"
                        value="Add Student Details"
                        className="btn btn-primary" />
                </div>

            </form>
        </div>
    )
}

export default StudentAdd;