$(document).ready(function () {
    $(".genreSearch").click(function () {
        $("#genreSearch").show();
        $("#yearSearch").hide();
    });

    // $("#genreSelector").onsubmit(function () {
    //     var post_data = "post_msg="+$("#post_msg").val();

    //     $.ajax ({
    //         type:"POST",
    //         url:"./recv.php",
    //         data:post_data,
    //         success:function(data) {
    //             alert ("질문이 입력되었습니다.");
    //         }
    //     });
    // });

    $(".yearSearch").click(function () {
        $("#genreSearch").hide();
        $("#yearSearch").show();
    });


});