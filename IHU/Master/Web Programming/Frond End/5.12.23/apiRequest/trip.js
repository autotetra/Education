place1 = document.getElementById("place1").value;
place2 = document.getElementById("place2").value;
function get() {
  //40.62716431389977, 22.94839704237129
  //window.location.href = "https://google.com/maps?q=" + 40.62716431389977 + ","+ 22.94839704237129;
  pWidth = 700;
  pHeight = 350;
  left1 = (window.screen.width - pWidth) / 2;
  top1 = (window.screen.height - pHeight) / 4;
  ///  window.open("https://google.com/maps?q=" + 40.62893078447501 + ","+ 22.95038421678329, "_blank", "resizable=yes,width="+pWidth+",height="+pHeight+",top="+top1+",left="+left1);
  //window.location.href = "https://google.com/maps?q=" + encodeURI("AUTH");
  window.location.href =
    "https://www.google.com/maps/dir/?api=1&origin=" +
    encodeURI(place1) +
    "&destination=" +
    encodeURI(place2) +
    "&travelmode=air"; //air driving walking bicycling

  // f1 = document.getElementById("f1");
  // f1.src = "https://google.com/maps?q=" + "Athens" + "&output=embed";
}
