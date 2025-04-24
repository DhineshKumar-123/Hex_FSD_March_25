import { useEffect, useState } from "react";
import CustomerNavBar from "./Navbar";
import axios from "axios";
import { Link } from "react-router";

function CustomerDashboard() {

    const [categories, setCategories] = useState([])

    useEffect(() => {
        const getAllCategories = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/category/getall")
                setCategories(response.data)
            }
            catch (err) {
                console.log(err);
            }
        }
        getAllCategories();
    }, [])

    return (

        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-12">
                    <CustomerNavBar />
                </div>
            </div>
            <div className="row">
                <div className="col-lg-12">
                    <div className="row">
                        {
                            categories.map((c, index) => (
                                <div className="col-sm-3" key={index}>
                                    <div className="card">
                                        <Link to={`/product/${c.id}/${c.name}`}>
                                            <div className="card-body">
                                                {c.name}
                                            </div>
                                        </Link>

                                    </div>

                                </div>
                            ))
                        }

                    </div>
                </div>
            </div>

        </div>
    )
}

export default CustomerDashboard; 