$(document).ready(function () {

    $(".genreSearch").click(function () {
        $("#genreSearch").show();
        $("#yearSearch").hide();
    });

    $(".yearSearch").click(function () {
        $("#genreSearch").hide();
        $("#yearSearch").show();
    });
});

function genreSearch() {
    var x = document.getElementsById("genreSelector");
    document.getElementById("demo").innerHTML = x.length;
  }