class House {
  constructor(color) {
    this.color = color;
  }

  getFurniture() {
    return "sofa";
  }
}

const houseObject1 = new House("red");
const houseObject2 = new House("blue");

console.log(houseObject1.color);
console.log(houseObject1.getFurniture());
console.log(houseObject2.color);
console.log(houseObject2.getFurniture());
