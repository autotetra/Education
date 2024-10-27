const obj = {
  value: 42,
  normalMethod: function () {
    console.log("normalMethod this: ", this); // 'this' refers to obj
    console.log("normalMethod this.value: ", this.value); // Logs 42

    function innerFunction() {
      console.log("innerFunction this: ", this); // this refers to the global object
    }

    const innerArrowFunction = () => {
      console.log("innerArrowFunction this: ", this); // 'this' refers to obj because it inherits from normalMethod
    };

    innerFunction();
    innerArrowFunction();
  },

  arrowMethod: () => {
    console.log("arrowMethod this: ", this); // 'this' refers to the global obj
    console.log("arrowMethod this.value: ", this.value); // Logs undefined
  },
};

obj.normalMethod();
obj.arrowMethod();
