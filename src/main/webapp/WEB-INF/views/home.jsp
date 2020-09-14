<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

	<c:set var="root" value="${pageContext.request.contextPath}" />
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<title>Modern Business - Start Bootstrap Template</title>
	
	<!-- Bootstrap core CSS -->
	<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
	<link href="${root}/resources/css/modern-business.css" rel="stylesheet">
	
	<!-- Custom styles for carousel -->
	<%-- <link href="${root}/resources/css/carousel.css" rel="stylesheet"> --%>
	
	<!-- Carousel Script -->
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick.css">
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick-theme.css">
	
	<!-- Slick Slider -->
	<link rel="stylesheet" type="text/css" href="${root}/resources/css/slick_slider.css">
	
	<!-- Netflix Slider -->
	<link rel="stylesheet" type="text/css" href="${root}/resources/css/netflix_slider.css">

</head>

<body>

	<!-- Carousel Slider -->
	<header>
		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<!-- Slide One - Set the background image for this slide in the line below -->
				<div class="carousel-item active" style="background-image: url('//image.tmdb.org/t/p/w1920_and_h800_multi_faces/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg')">
					<div class="carousel-caption d-none d-md-block">
						<h3>루시퍼</h3>
					</div>
				</div>
				<!-- Slide Two - Set the background image for this slide in the line below -->
				<div class="carousel-item" style="background-image: url('//image.tmdb.org/t/p/w1920_and_h800_multi_faces/xGexTKCJDkl12dTW4YCBDXWb1AD.jpg')">
					<div class="carousel-caption d-none d-md-block">
						<h3>종이의 집</h3>
					</div>
				</div>
				<!-- Slide Three - Set the background image for this slide in the line below -->
				<div class="carousel-item" style="background-image: url('//image.tmdb.org/t/p/w1920_and_h800_multi_faces/hTExot1sfn7dHZjGrk0Aiwpntxt.jpg')">
					<div class="carousel-caption d-none d-md-block">
						<h3>원헌드레드</h3>
					</div>
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span> 
				<span class="sr-only">Previous</span>
			</a> 
			<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</header>

	<!-- Page Content -->
	<div class="container">
	
		<h1 class="my-4">${check}</h1>

		<h1>최신 영화</h1>

<%-- 		<section class="regular slider">
			<c:forEach var="movie" items="${latestMovies}">
				<div>
					<img src="${movie.posterPath}">
				</div>
			</c:forEach>
		</section> --%>

		<h1>인기 영화</h1>

		<section class="regular slider">
			<c:forEach var="movie" items="${popularMovies}">
				<div>
					<img src="${movie.posterPath}">
				</div>
			</c:forEach>
		</section>
		
		<h1>최신 TV 프로그램</h1>

<%-- 		<section class="regular slider">
			<c:forEach var="tv" items="${latestTVs}">
				<div>
					<img src="${tv.posterPath}">
				</div>
			</c:forEach>
		</section> --%>
		
		<h1>인기 TV 프로그램</h1>

		<section class="regular slider">
			<c:forEach var="tv" items="${popularTVs}">
				<div>
					<img src="${tv.posterPath}">
				</div>
			</c:forEach>
		</section>

	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript -->
	<script src="${root}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${root}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Carousel Slider - Slick -->
	<script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
	<script src="${root}/resources/slick/slick.js" type="text/javascript" charset="utf-8"></script>
	
	<!-- Carousel Slider - Slick(Regular) -->
	<script src="${root}/resources/js/slick_slider.js" type="text/javascript"></script>

	<!-- netflix slider -->
	<script src="${root}/resources/js/netflix_slider.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
