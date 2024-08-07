//normal function
function sum1(a, b) {
  return a + b;
}

//arrow function
let sum2 = (a, b) => a + b;

//normal function
function isPositive1(number) {
  return number >= 0;
}

//arrow function
let isPositive2 = (number) => number >= 0;

//normal function
function randomNumber1() {
  return Math.random();
}

//arrow function
let randomNumber2 = () => Math.random();

//normal function
document.addEventListener("click", function () {
  console.log("Click");
});

//arrow function
document.addEventListener("click", () => console.log("Click"));
