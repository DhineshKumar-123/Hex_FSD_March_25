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
import { useDispatch } from "react-redux"
import { useEffect } from "react"
import fetchAlbums from "./stores/actions/albumsActions"
import AlbumsList from "./components/playground/albumslist"
import ChartDashboard from "./components/playground/Dashboard"


function App()
{
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchAlbums())
  }, [])



  return(
     <Routes>
      <Route index path="" element={<Login />}></Route>
      <Route path="customer" element={<CustomerDashboard />}></Route>
      <Route path="product/:cid/:cname" element={<CustomerProducts />} />
      <Route path="/customer/signup" element={<CustomerSignUp />} />
      <Route path="vendor" element={<VendorDashboard />} />
      <Route path="albums" element={<AlbumsList />} />
      <Route path="chart-dashboard" element={<ChartDashboard />} />
     </Routes>
  )
}

export default App
