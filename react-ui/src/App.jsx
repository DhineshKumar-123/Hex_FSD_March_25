import { useState } from 'react'
import './App.css'

function App()
{//App is a component and this must have a return 

  let [name] = useState('John Doe');
  // if we want to set the differnet values for the declared variables within a fuction we need to use usestate and setname or set keyword

  let[msg,setMsg] = useState('');

  const age = 50

//let can be updated when we need to modify
//const cant be altered or modified once it declared with a value

let x = 20
let y = 50

let percentage = 100

//create a function for clicking button
const btnclick = ()=>
{
  // alert("You Clicked Me I am Here to assist you!!!!")
  // console.log("You clicked me I am Here in the Console")

  setMsg("I have been Clicked by you and you generated me !!!!")
}


  return(
    <div>
      <h1>React App Vite</h1>
      <p>Name is {name}</p>
      <p>Age is {age}</p>
      <p>Multiplication is {x*y}</p>

<hr />

    <p>
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
      <hr />

      <button onClick={()=>btnclick()}>Click here to see the Different </button>
      <p>{msg}</p>

    </div>
  )
}

export default App
