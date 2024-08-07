function showV() {
  p = document.createElement("p");
  //txt = document.createTextNode("The Taxi Driver");
  txt = document.getElementById("inpt1");
  p.append(txt.value);
  document.body.append(p);
}
