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