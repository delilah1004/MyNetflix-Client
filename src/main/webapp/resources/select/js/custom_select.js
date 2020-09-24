$(document).ready(function () {
	$(".default_option").click(function () {
		$(this).parent().toggleClass("active");
	})

	$(".select_ul li").click(function () {
		var currentele = $(this).html();
		// var root = window.location.origin;
		// var condition = $(this).data("condition");

		$(".default_option li").html(currentele);
		$(this).parents(".select_wrap").removeClass("active");

		// alert(root + "/MyNetflix/movie/setView.mn?condition=" + condition);

		// $('#body').children().remove().load(root + "/MyNetflix/movie/setView.mn?condition=" + condition);
	})
});