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
		
	</style>

</head>

<body>

	<!-- Page Content -->
	<div class="container">

		<%-- <h1 class="pt-4">${check}</h1> --%>

		<!-- Custom content -->
		<header class="jumbotron my-5">

			<div class="row no-gutters align-items-center">

				<!-- 영화 포스터 -->
				<img class="rounded" src="${movie.posterPath}" alt="">

				<div class="py-3">

					<!-- 영화 제목 -->
					<h2 class="card-title align-self-start list-inline-item">${movie.title}</h2>
					
					<!-- 개봉 여부 -->
					<span class="status py-2">${movie.status}</span>

					<div class="align-self-content pb-2">

						<!-- 영화 개봉일 -->
						<h6 class="list-inline-item">${movie.releaseDate}</h6>

						<!-- 영화 상영시간 -->
						<span>( ${movie.runtime}분 )</span>

					</div>

					<!-- 영화 전체 줄거리 -->
					<div class="card-text align-self-end">${movie.overview}</div>

				</div>
				<!-- End of contents -->

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