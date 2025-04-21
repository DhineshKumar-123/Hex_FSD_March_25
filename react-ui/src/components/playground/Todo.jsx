import { useEffect, useState } from "react";

function Todo ()
{
    
    const[todos,setTodo] = useState([]);
    useEffect(()=>{
        const getTodos = () => {
            fetch('https://jsonplaceholder.typicode.com/todos')
                .then(resp => resp.json())
                .then(data => setTodo(data))
                .catch(err => {
                    console.log(err)
                })
        }
        getTodos();
    },[])

    // This is function to delete the id from the web render
    //this needs the id for that data to be deleted from there 
    // here there are some tricks we have played

    const deleteTodo = (todoId) =>
    {

         //make api call to delete -- skip this time 

        //This is to cloning the original state array(todo) to this temparr 
        //this can be modify but the state cant be modify
        let tempArr = [...todos];

        //now we are filtering the temparray where the user clicked that corresponding id is passed here to delete that or remove it from the render
        //once they removed from the array then it will remains the balance data
        tempArr = tempArr.filter(t =>t.id != todoId);

        //Here the balance data are set it to the State array.......
        /** 
         * if this condition 't.id !== todoId' satisfies and gives true then that element stays. 
         * so in short, only element that the user the clicked will leave and others will stay. 
         */
        setTodo(tempArr);
    }



    return(
        <div>
            <h1> All the Todo Goes Here </h1>
            <div className="container">
                <div className="row">
                   {
                    todos.map((todo,index)=>(
                        <div className="col-sm-4 mb-4" key={index}>
                            <div className="card">
                                <div className="card-body">
                                        {todo.id} <br />
                                        {todo.userId} <br />
                                        {todo.title} <br /><br />
                                        <button className="btn btn-danger" onClick={() => deleteTodo(todo.id)}>Delete Todo</button>
                                </div>

                            </div>

                        </div>
                    ))
                   }

                </div>
            </div>
        </div>
    )
}
export default Todo;
