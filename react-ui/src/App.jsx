import { use, useState } from 'react'
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


  let [name,setName] = useState(null)
  let [email,setEmail] = useState(null)
  let [contact,setContact] = useState(null)

  const populate = ()=>
  {
    setName("Johnathon")
    setEmail("johnaa@gmail.com")
    setContact("9632587410")
  }
  const manager = ()=>
    {
      setName("Emma")
      setEmail("emma@gmail.com")
      setContact("6359635696")
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

      <button onClick={()=> {populate()} }>Click here to see the Details of the Employee </button>
      <button onClick={()=> {manager()} }>Click here to see the Details of the Manager </button>
      <p>
        Name : {name} <br />
        E-Mail : {email} <br />
        Contact : {contact} <br />
      </p>

    </div>
  )
}

export default App
