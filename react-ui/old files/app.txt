import { use, useEffect, useState } from 'react'
import './App.css'

function App()
{//App is a component and this must have a return 

//   let [name] = useState('John Doe');
//   // if we want to set the differnet values for the declared variables within a fuction we need to use usestate and setname or set keyword
//   let[msg,setMsg] = useState('');
//   const age = 50
// //let can be updated when we need to modify
// //const cant be altered or modified once it declared with a value
// let percentage = 100

// //create a function for clicking button
// const btnclick = ()=>
// {
//    setMsg("I have been Clicked by you and you generated me !!!!")
// }
  // let [name,setName] = useState(null)
  // let [email,setEmail] = useState(null)
  // let [contact,setContact] = useState(null)

  // const populate = ()=>
  // {
  //   setName("Johnathon")
  //   setEmail("johnaa@gmail.com")
  //   setContact("9632587410")
  // }
  // const manager = ()=>
  //   {
  //     setName("Emma")
  //     setEmail("emma@gmail.com")
  //     setContact("6359635696")
  //   }

  //Creation of object and store the hardcoded values to it
  const [employeeObj,setEmployeeObj] = useState({});
  const [managerObj,setManagerObj] = useState({});
  const [showEmp,setShowEmp] = useState(false); 
   const [showManager,setShowManager] = useState(false);
  
    useEffect (() =>
    {
      // this contains the empObject and ManagerObject with harcoded values
      const populate = ()=>
        {
          let empObj = {
            "name":"Laura Williams",
            "email" : "emp@gmail.com",
            "contact" : "8569321472",
            "projectInfo" : "Web Design with Some of the internal projects"
          }

          let managerObj = {
            "name":"Prenash",
            "email" : "manager@gmail.com",
            "contact" : "91234567898",
            "departmentInfo" : "IT Department",
            "employeeInfo": "Manages 45 employees"
          }
          /** Assign this local objects to states, so that we can display 
       * states in other functions */
            setEmployeeObj(empObj)
            setManagerObj(managerObj)
    
        }
        populate()
    
    },[]);

    const populate = (val)=>{
      switch(val){
        case 'employee':
          setShowEmp(true)  
          setShowManager(false)
          break; 
        case 'manager':
          setShowEmp(false)  
          setShowManager(true)
          break; 
        default:
          setShowEmp(false)  
          setShowManager(false)
          break; 
      }
    }
  return(
    <div>
    {/* <p>
      {
        percentage>75?`You have cleared the Exam with ${percentage}%`
        :percentage>50?`You have an Re-Exam because you have scored ${percentage}%`
        :`You are not cleared the exam because your percentage is ${percentage}%`
      }
    </p>
      <hr />
      <p>
        {
          age>60?`According to your age you are a Senior Citizen`
          :age>20?`According to your age you are a Middle Age Citizen`
          :`According to your age you are a Teenager`
        }
      </p>
      <hr /> */}

      {/* <button onClick={()=> {populate()} }>Click here to see the Details of the Employee </button>
      <button onClick={()=> {manager()} }>Click here to see the Details of the Manager </button> */}
      {/* <p>
        Name : {name} <br />
        E-Mail : {email} <br />
        Contact : {contact} <br />
      </p> */}

          <button onClick={()=>{ populate('employee') }}>Populate Employee</button> 
          <button onClick={()=>{ populate('manager') }}>Populate Manager</button>
        {
          showEmp === false? "" : 
          <div id='employeeContent'>
          <h3>Employee Block</h3>
            <div>
              Name: {employeeObj.name} <br />
              Email: {employeeObj.email} <br />
              Contact: {employeeObj.contact} <br />
              <br />
              <h4>Project Info</h4>
              {employeeObj.projectInfo}
            </div>
         </div>
        }
       
        {
          showManager === false? "": 
          <div id='managerContent'>
          <h3>Manager Block</h3>
           <div>
             Name: {managerObj.name} <br />
             Email: {managerObj.email} <br />
             Contact: {managerObj.contact} <br />
             <br />
             <h4>Department Info</h4>
             <span>{managerObj.departmentInfo} </span> 
             <h4>All Employee Info</h4>
             <span> {managerObj.employeeInfo}</span>
           </div>
          </div>
        }
    </div>
  )
}

export default App
