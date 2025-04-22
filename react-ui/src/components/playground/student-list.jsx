import axios from "axios"
import { useEffect, useState } from "react"


/**
 * Steps to create this (For simple render the students)
 * -- create normal function studentList
 * -- then return for render the table use bootstrap
 * -- First create the Usestate for the model 
 * -- then create useeffect and create method nside that to get response from the backend
 * -- then create map function to render the data with its appropriate columns and rows within the table
 */

/**
 * Now here the work around is to create the list of studetns and show them as the table and enabling the buttons of previous and Next using 
 * some logics that are travelled from the Backend Springboot APplication
 */
function StudentList() {

    const [students, setStudents] = useState([])
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [totalPages, setTotalPages] = useState(0);
    const [pageArry, setPageArry] = useState([]);

    let count = 0;

    useEffect(() => {

        setPageArry([])

        const getAllStudents = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/student/all?page=${page}&size=${size}`)
                setStudents(response.data.list);
                setTotalPages(response.data.totalPages)
                let temp = totalPages;
                while (temp > 0) {
                    pageArry.push(count + 1);
                    temp = temp - 1;
                }
            }
            catch (err) {
                console.log(err);
            }
        }

        getAllStudents()
    }, [page]) 
    /** [] : indicates that useEffect will be called only once.
    * But in this case whenever the value of page changes, we want react to call useEffect. 
    */




    return (
        <div>
            <p>List of Students with Pagination</p>
            <div className="container">
                <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">#ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Age</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            students.map((s, index) => (
                                <tr key={index}>
                                    <th scope="row"> {s.id}</th>
                                    <td>{s.name}</td>
                                    <td>{s.email}</td>
                                    <td>{s.age}</td>
                                    <td>
                                        <button className="btn btn-danger btn-sm">Delete</button>
                                        &nbsp;&nbsp;&nbsp;
                                        <button className="btn btn-info btn-sm">Update</button>
                                    </td>
                                </tr>
                            ))
                        }


                    </tbody>
                </table>
                <div className="row">
                    <div className="col-md-4">

                    </div>
                    <div className="col-md-6">
                        <nav aria-label="Page navigation example">
                            <ul className="pagination">
                               
                            <li className="page-item"><a className="page-link" href="#"
                                    onClick={() => { page === 0 ? setPage(0) : setPage(page - 1) }}>Previous</a></li>
                                {
                                    pageArry.map((e, index) => (
                                        <li key={index} className="page-item"><a className="page-link" href="#"
                                            onClick={() => { setPage(e + 1) }} >{index + 1}</a></li>
                                    ))
                                }

                                <li className="page-item"><a className="page-link" href="#"
                                    onClick={() => { page === totalPages - 1 ? setPage(page) : setPage(page + 1) }} >Next</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>

            </div>
        </div>
    )
}
export default StudentList