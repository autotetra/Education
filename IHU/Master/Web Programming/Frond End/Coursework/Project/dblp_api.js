var authorNameDefault;
var authorName;
var authorList;
var totalHits;
var fPointer;
var hPointer;
var url;
var previousButton;
var nextButton;

function startSearch() {
  fPointer = 0;
  hPointer = 10;
  clearContent();
  authorNameDefault = document.getElementById("authorName").value;
  authorName = authorNameDefault.split(" ").join("_");
  getAuthor(authorName);
}

function clearContent() {
  const publDataElement = document.getElementById("publData");
  const searchInfoElement = document.getElementById("searchInfo");
  const previousButtonStatus = document.getElementById("previousButton");
  const nextButtonStatus = document.getElementById("nextButton");

  if (publDataElement) {
    publDataElement.remove();
  }

  if (searchInfoElement) {
    searchInfoElement.innerHTML = "";
  }

  if (previousButtonStatus) {
    previousButtonStatus.remove();
  }
  if (nextButtonStatus) {
    nextButtonStatus.remove();
  }
}

function getAuthor(name) {
  url =
    "https://dblp.org/search/publ/api?q=author:" +
    name +
    ":&format=json&h=" +
    hPointer +
    "&f=" +
    fPointer;

  fetch(url)
    .then((response) => {
      return response.json();
    })
    .then((results) => {
      authorList = results;
      totalHits = authorList.result.hits["@total"];
      console.log("[getAuthor]API URL: ", url);
      console.log("[getAuthor]Response: ", authorList);
      if (totalHits <= 0) {
        if (authorNameDefault.length === 0) {
          authorBlankName();
        } else {
          authorNotFound();
        }
      } else {
        authorFound();
        setTable(authorList);
      }
    })
    .catch((error) => {
      console.error("Error: ", error);
    });
}

function authorNotFound() {
  document
    .getElementById("searchInfo")
    .setAttribute("class", "warningMessages");
  document.getElementById("searchInfo").textContent =
    "No author found with the name: " +
    authorNameDefault +
    ", please try again";
}

function authorBlankName() {
  document
    .getElementById("searchInfo")
    .setAttribute("class", "warningMessages");
  document.getElementById("searchInfo").textContent =
    "Please enter an author name in the search bar.";
}

function authorFound() {
  document
    .getElementById("searchInfo")
    .setAttribute("class", "successMessages");
  document.getElementById("searchInfo").textContent =
    authorName.split("_").join(" ") + " Publications";
}

function nextButtonClick() {
  clearContent();
  fPointer += hPointer;
  getAuthor(authorName);
}

function previousButtonClick() {
  clearContent();
  fPointer -= hPointer;
  getAuthor(authorName);
}

function updatePaginationButtons() {
  previousButton.disabled = fPointer === 0;
  if (totalHits < hPointer || hPointer >= totalHits - fPointer) {
    nextButton.disabled = true;
  }
}

function setTable(authorList) {
  //create table
  var table = document.createElement("table");

  //create header row
  var headerRow = document.createElement("tr");

  //create title cell and append to row
  var title = document.createElement("th");
  var titleContent = document.createTextNode("Title");
  title.append(titleContent);

  //create venue cell and append to row
  var venue = document.createElement("th");
  var venueContent = document.createTextNode("Venue");
  venue.append(venueContent);

  //create year cell and append to row
  var year = document.createElement("th");
  var yearContent = document.createTextNode("Year");
  year.append(yearContent);

  //create type cell and append to row
  var type = document.createElement("th");
  var typeContent = document.createTextNode("Type");
  type.append(typeContent);

  //append cells to the header row
  headerRow.append(title, venue, year, type);

  //append header row to the table
  table.append(headerRow);

  //add data to table
  for (var i = 0; i < hPointer; i++) {
    if (authorList.result.hits.hit[i]) {
      //create new row
      var newRow = document.createElement("tr");

      //add title value to the row
      // create a link value
      var newTitleLink = document.createElement("a");

      // set the href attribute of the link
      newTitleLink.href = authorList.result.hits.hit[i].info.url;
      newTitleLink.target = "_bblank";

      // create a table cell for the title
      var newRowTitle = document.createElement("td");

      // create a text node for the title content
      var newRowTitleContent = document.createTextNode(
        authorList.result.hits.hit[i].info.title
      );

      // append the text node to the link
      newTitleLink.appendChild(newRowTitleContent);

      // append the link to the table cell
      newRowTitle.appendChild(newTitleLink);

      // now, append the table cell to the row
      newRow.appendChild(newRowTitle);

      //add venue value to the row
      var newRowVenue = document.createElement("td");
      var newRowVenueContent = document.createTextNode(
        authorList.result.hits.hit[i].info.venue
      );
      newRowVenue.append(newRowVenueContent);

      //add year value to the row
      var newRowYear = document.createElement("td");
      var newRowYearContent = document.createTextNode(
        authorList.result.hits.hit[i].info.year
      );
      newRowYear.append(newRowYearContent);

      //add type value to the row
      var newRowType = document.createElement("td");
      var newRowTypeContent = document.createTextNode(
        authorList.result.hits.hit[i].info.type
      );
      newRowType.append(newRowTypeContent);

      //append values to the new row
      newRow.append(newRowTitle, newRowVenue, newRowYear, newRowType);

      //append new row to the table
      table.append(newRow);
    }
  }

  //create previous button
  previousButton = document.createElement("button");
  previousButton.setAttribute("id", "previousButton");
  previousButton.setAttribute("onclick", "previousButtonClick()");
  previousButton.innerHTML = "Previous";
  previousButton.style.marginTop = "10px";
  previousButton.style.marginBottom = "10px";

  //create next button
  nextButton = document.createElement("button");
  nextButton.setAttribute("id", "nextButton");
  nextButton.setAttribute("onclick", "nextButtonClick()");
  nextButton.innerHTML = "Next";
  nextButton.style.margin = "10px";

  //apend table to the body
  document.body.append(table);

  //set table attributes
  table.setAttribute("id", "publData");

  //append buttons to the bottom of the table
  document.body.append(previousButton, nextButton);

  updatePaginationButtons();

  //print values for check
  // console.log("fPointer: ", fPointer);
  // console.log("hPointer: ", hPointer);
  console.log("Total Hits: ", totalHits);
  // console.log("URL: ", url);
  // console.log("Response: ", authorList);
}
