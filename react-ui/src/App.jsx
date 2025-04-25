import { Route, Routes } from "react-router"
import Login from "./components/auth/Login"
import Post from "./components/playground/Posts"
import StudentAdd from "./components/playground/student-add"
import StudentList from "./components/playground/student-list"
import Todo from "./components/playground/todo"
import CustomerDashboard from "./components/customer/CustomerDashboard"
import CustomerProducts from "./components/customer/product"
import CustomerSignUp from "./components/customer/signup"
import VendorDashboard from "./components/vendor/VendorDashboard"


function App()
{
  return(
    <div>
     <Routes>
      <Route index path="" element={<Login />}></Route>
      <Route path="customer" element={<CustomerDashboard />}></Route>
      <Route path="product/:cid/:cname" element={<CustomerProducts />} />
      <Route path="/customer/signup" element={<CustomerSignUp />} />
      <Route path="vendor" element={<VendorDashboard />} />
     </Routes>
    </div>
  )
}

export default App
