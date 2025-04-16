import './App.css'

function App()
{//App is a component and this must have a return 

  let name = "John Doe"
  const age = 50

//let can be updated when we need to modify
//const cant be altered or modified once it declared with a value

let x = 20
let y = 50

let percentage = 100
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

    </div>
  )
}

export default App
