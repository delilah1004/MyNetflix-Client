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

	<title>TV 프로그램 상세페이지</title>

	<!-- Bootstrap core CSS -->
	<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${root}/resources/css/small-business.css" rel="stylesheet">

	<!-- Custom CSS for topbar.jsp -->
	<link href="${root}/resources/css/topbar.css" rel="stylesheet">

	<style type="text/css">

		.box-image {
			position: relative;
			left: 0;
			right: 0;
			clear: both;
			margin-right: 30px;
		}

		.box-image img {
			width: 185px;
			height: 278px;
		}

		.box-contents {
			position: relative;
			left: 0;
			right: 0;
			clear: both;
		}

		.box-contents .title strong {
			color: #1a1919;
			font-size: 25px;
			font-weight: 500;
			vertical-align: middle;
		}

		.box-contents .title .runtime {
			color: gray;
			vertical-align: bottom;
		}

		.box-contents .title .status {
			color: blue;
			vertical-align: middle;
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

</head>

<body>

	<!-- Page Content -->
	<div class="container">

		<h1 class="pt-4">${check}</h1>

		<div class="sect-base-movie overflow-hidden p-3">

			<div class="box-image float-left">
				<span class="thumb-image">
					<img src="${tv.posterPath}" alt="">
				</span>
			</div>

			<div class="box-contents float-left">

				<div class="title pb-3">
					<!-- TV Program 제목 -->
					<strong>${tv.name}</strong>

					<!-- TV Program 상영시간 -->
					<span>( ${tv.episodeRunTime}분 )</span>

					<!-- 종영 여부 -->
					<span class="status py-2">시즌 연재중</span>

					<!-- TV Program 전체 줄거리 -->
					<div>${tv.overview}</div>
				</div>

				<div class="spec pt-3">
					<dl>
						<!-- TV Program 감독 -->
						<dt>감독 :&nbsp;</dt>
						<dd>
							<a href="/movies/persons/?pidx=11015">크리스토퍼 놀란</a>
						</dd>

						<!-- TV Program 출연 배우 -->
						<dt>&nbsp;/ 배우 :&nbsp;</dt>
						<dd class="on">
							<a href="/movies/persons/?pidx=118931">존 데이비드 워싱턴</a> ,&nbsp;
							<a href="/movies/persons/?pidx=38481">로버트 패틴슨</a> ,&nbsp;
							<a href="/movies/persons/?pidx=115882">엘리자베스 데비키</a> ,&nbsp;
							<a href="/movies/persons/?pidx=26886">애런 존슨</a>
						</dd>

						<!-- TV Program 장르 -->
						<dt>장르 :&nbsp;</dt>
						<dd>액션,&nbsp;SF</dd>

						<!-- TV Program 첫 방영일 -->
						<dt>&nbsp;/ 첫 방영일 :&nbsp;</dt>
						<dd class="on">${tv.firstAirDate}</dd>
					</dl>
				</div>

				<div class="like">
					<a class="btn btn-dark text-white" href="${tv.homepage}">영상 보러가기</a>
				</div>

			</div>

		</div>

		<!-- Call to Action Well -->
		<div class="card text-white bg-secondary my-5 py-4 text-center" hidden>
			<div class="card-body">
				<p class="text-white m-0">This call to action card is a great
					place to showcase some important information or display a clever
					tagline!</p>
			</div>
		</div>

		<!-- Content Row -->
		<div class="row" hidden>
			<div class="col-md-4 mb-5">
				<div class="card h-100">
					<div class="card-body">
						<h2 class="card-title">Card One</h2>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Rem magni quas ex numquam, maxime minus quam
							molestias corporis quod, ea minima accusamus.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary btn-sm">More Info</a>
					</div>
				</div>
			</div>
			<!-- /.col-md-4 -->
			<div class="col-md-4 mb-5">
				<div class="card h-100">
					<div class="card-body">
						<h2 class="card-title">Card Two</h2>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Quod tenetur ex natus at dolorem enim! Nesciunt
							pariatur voluptatem sunt quam eaque, vel, non in id dolore
							voluptates quos eligendi labore.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary btn-sm">More Info</a>
					</div>
				</div>
			</div>
			<!-- /.col-md-4 -->
			<div class="col-md-4 mb-5">
				<div class="card h-100">
					<div class="card-body">
						<h2 class="card-title">Card Three</h2>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Rem magni quas ex numquam, maxime minus quam
							molestias corporis quod, ea minima accusamus.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary btn-sm">More Info</a>
					</div>
				</div>
			</div>
			<!-- /.col-md-4 -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript -->
	<script src="${root}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${root}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>