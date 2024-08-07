function searchAuthor() {
  var authorName = document.getElementById("authorName").value;
  //console.log(authorName);
}

function getAuthors() {
  fetch("http://89.47.163.191/ages_cors.php")
    .then((response) => {
      return response.json();
    })
    .then((authorList) => {
      const objects = authorList;
      for (var i = 0; i < objects.length; i++) {
        console.log(objects[i].name + " " + objects[i].age);
      }
    })
    .catch((error) => {
      console.error("Error: ", error);
    });
}
