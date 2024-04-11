place1 = "Thessaloniki";
place2 = "Athens";
function get() {
  place = document.getElementById("place").value;
  f1 = document.getElementById("f1");
  f1.src = "https://google.com/maps?q=" + place + "&output=embed";
}
