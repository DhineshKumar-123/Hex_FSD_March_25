import { useEffect } from "react";
import { useState } from "react"

function Post()
{

    const[posts,SetPosts] = useState([]);

    useEffect(()=>
    {
        const getPosts = () =>
        {
            let post1 = {
                "userId": 1,
                "id": 1,
                "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            }
            let post2 = {
                "userId": 1,
                "id": 2,
                "title": "qui est esse",
                "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
            }
            //we need to push the post object above to the Array named as Temp
            let temp = [];
            temp.push(post1);
            temp.push(post2);

            //Assigining the Temp array into the already defined Post Array
            SetPosts(temp);
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