<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

	<c:set var="root" value="${pageContext.request.contextPath}" />

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>HOME</title>

	<!-- Bootstrap core CSS -->
	<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${root}/resources/css/modern-business.css" rel="stylesheet">

	<!-- Carousel Script -->
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick.css">
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick-theme.css">

	<!-- Carousel CSS -->
	<link href="${root}/resources/css/carousel.css" rel="stylesheet">

</head>

<body>

	<!-- Carousel Slider -->
	<header>
		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
			
			<ol class="carousel-indicators">
				<c:forEach var="mainContent" items="${mainContents}" varStatus="status">
					<c:if test="${status.first}">
						<li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="active"></li>
					</c:if>
					<c:if test="${!status.first}">
						<li data-target="#carouselExampleIndicators" data-slide-to="${status.index}"></li>
					</c:if>
				</c:forEach>
			</ol>

			<div class="carousel-inner" role="listbox">

				<c:forEach var="mainContent" items="${mainContents}" varStatus="status">
					<c:if test="${status.first}">
						<a class="carousel-item active"
							style="background-image: url('${mainContent.backdropPath}')" href="${mainContent.homepage}">
						</a>
					</c:if>
					<c:if test="${!status.first}">
						<a class="carousel-item"
							style="background-image: url('${mainContent.backdropPath}')" href="${mainContent.homepage}">
						</a>
					</c:if>
				</c:forEach>

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
		<section class="regular slider">
			<c:forEach var="movie" items="${nowPlayingMovies}">
				<div>
					<a href="${root}/movie/fullView.mn?movieId=${movie.id}"><img src="${movie.posterPath}"></a>
				</div>
			</c:forEach>
		</section>

		<h1>인기 영화</h1>
		<section class="regular slider">
			<c:forEach var="movie" items="${popularMovies}">
				<div>
					<a href="${root}/movie/fullView.mn?movieId=${movie.id}"><img src="${movie.posterPath}"></a>
				</div>
			</c:forEach>
		</section>

		<h1>최신 TV 프로그램</h1>
		<section class="regular slider">
			<c:forEach var="tv" items="${onTheAirTVs}">
				<div>
					<a href="${root}/tv/fullView.mn?tvId=${tv.id}"><img src="${tv.posterPath}"></a>
				</div>
			</c:forEach>
		</section>

		<h1>인기 TV 프로그램</h1>
		<section class="regular slider">
			<c:forEach var="tv" items="${popularTVs}">
				<div>
					<a href="${root}/tv/fullView.mn?tvId=${tv.id}"><img src="${tv.posterPath}"></a>
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
	<script src="${root}/resources/js/carousel.js" type="text/javascript"></script>
</body>

</html>