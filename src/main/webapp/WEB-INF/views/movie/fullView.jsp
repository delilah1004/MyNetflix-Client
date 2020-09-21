<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>

	<c:set var="root" value="${pageContext.request.contextPath}" />

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>영화 상세 페이지</title>

	<!-- Bootstrap core CSS -->
	<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${root}/resources/css/small-business.css" rel="stylesheet">

	<!-- Custom CSS for topbar.jsp -->
	<link href="${root}/resources/css/topbar.css" rel="stylesheet">

	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
		}

		.spec {
			position: relative;
			left: 0;
			right: 0;
			clear: both;
			border-top: 1px solid #d9d6c8;
			width: 100%;
		}

		.spec dt,
		dd {
			float: left;
		}

		.spec .on {
			float: none;
		}

		.sect-base-movie a {
			color: black;
		}

		.sect-base-movie a:hover {
			text-decoration: none;
		}
	</style>

	<style>
		div.header.large.first {
			border-bottom: 1px solid rgba(19.80%, 21.37%, 17.84%, 1.00);
			background-position: right -200px top;
			background-size: cover;
			background-repeat: no-repeat;
			background-image: url('//image.tmdb.org/t/p/w1920_and_h800_multi_faces/vdTZHig4F9aIT9M5C6r2YqRXUws.jpg');
		}
	</style>

</head>

<body>

	<!-- Page Content -->
	<div class="container">

		<h1 class="pt-4">${check}</h1>

		<!-- 커스텀 중 -->
		<header class="jumbotron">

			<div class="row no-gutters align-items-center">

				<!-- 영화 포스터 -->
				<div class="col-3">
					<div class="box-image text-center">
						<img class="rounded" src="${movie.posterPath}" alt="">
					</div>
				</div>

				<!-- 영화 정보 -->
				<div class="col-9 px-4">

					<div class="box-contents">
						<div class="title">

							<!-- 영화 제목 -->
							<h2 class="card-title movie-title align-self-start">${movie.title}</h2>

							<div class="align-self-content">

								<!-- 개봉 여부 -->
								<span class="status py-2">${movie.status}</span><br />

								<!-- 영화 개봉일 -->
								<span>${movie.releaseDate}</span>

								<!-- 영화 상영시간 -->
								<span>( ${movie.runtime}분 )</span>

							</div>

							<!-- 영화 전체 줄거리 -->
							<div class="card-text align-self-end">${movie.overview}</div>

						</div>
					</div>
					<!-- End of contents -->

				</div>
			</div>
			<!-- End of card -->

			<div class="spec pt-3">

				<dl>
				
					<!-- 영화 장르 -->
					<dt>장르 :&nbsp;</dt>
					<dd class="on">
						<c:forEach var="genre" items="${movie.genres}" varStatus="index">
							${genre.name}
							<c:if test="${!index.last}">,&nbsp;</c:if>
						</c:forEach>
					</dd>

					<!-- 영화 출연 배우 -->
					<dt>배우 :&nbsp;</dt>
					<dd class="on">
						<c:forEach var="cast" items="${movie.casts}" varStatus="index">
							<a href="/movies/persons/?pidx=${cast.id}">${cast.name}</a>
							<c:if test="${!index.last}">,&nbsp;</c:if>
						</c:forEach>
					</dd>

				</dl>

			</div>

			<div class="like">
				<a class="btn btn-dark text-white" href="${movie.homepage}">영상 보러가기</a>
			</div>

		</header>

	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript -->
	<script src="${root}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${root}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>