import { useEffect } from "react";
import { useState } from "react"

function Post()
{

    const[posts,SetPosts] = useState([]);

    useEffect(()=>
    {
        const getPosts = () =>//this is the method or function within the Use Effect
        {
            fetch("https://jsonplaceholder.typicode.com/posts")
            .then(response => response.json())
            .then(data => SetPosts(data))
        }

        getPosts();//Calling the Created function to activate that and it is more Important

    },[])

    return(
        <div>
            <h2>Hello this is Post Playground page </h2>

            {/* Create an JSX for projecting the posts that are stored in the PostArray */}
            
            {/* this p indicates that every posts p */}
            {/* we need to create the unique key for the div which is inside the map function  */}
            {
                posts.map((p , index )=>( 
                    <div key={index}>
                        {p.id} <br />
                        {p.userId} <br />
                        {p.title} <br />
                        {p.body} <br />
                        <hr />
                        <hr />
                    </div>
                ))
            }

        </div>
    )
}
export default Post