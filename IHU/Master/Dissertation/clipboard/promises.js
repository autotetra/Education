import axios from "https://cdn.skypack.dev/axios";

async function getTitle() {
  try {
    let response = await axios.get(
      "https://jsonplaceholder.typicode.com/todos/1"
    );
    console.log(`You could ${response.data.title}`);
  } catch (error) {
    console.log(`Error! : ${error}`);
  }
}

getTitle();

console.log("Why am I here?");
