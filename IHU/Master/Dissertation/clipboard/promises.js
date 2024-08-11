function fetchData(url) {
  return new Promise((resolve, reject) => {
    fetch(url)
      .then((response) => {
        if (!response.ok) {
          reject(new Error(`HTTP error! status: ${response.status}`));
        }
        return response.json();
      })
      .then((data) => resolve(data))
      .catch((error) => reject(error));
  });
}

const url = "https://jsonplaceholder.typicode.com/todos/1";
fetchData(url)
  .then((data) => {
    console.log("Success:", data); // This runs if resolve() was called
  })
  .catch((error) => {
    console.error("Error:", error); // This runs if reject() was called
  })
  .finally(() => {
    console.log("Operation complete."); // This runs regardless of the outcome
  });
