import { Route, Routes } from "react-router"
import Login from "./components/auth/Login"
import Post from "./components/playground/Posts"
import StudentAdd from "./components/playground/student-add"
import StudentList from "./components/playground/student-list"
import Todo from "./components/playground/todo"
import CustomerDashboard from "./components/customer/CustomerDashboard"


function App()
{
  return(
    <div>
     <Routes>
      <Route index path="" element={<Login />}></Route>
      <Route path="customer" element={<CustomerDashboard />}></Route>
     </Routes>
    </div>
  )
}

export default App
