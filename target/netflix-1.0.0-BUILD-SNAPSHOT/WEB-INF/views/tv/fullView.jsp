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
			
				<!-- TV Program 포스터 -->
				<img class="rounded" src="${tv.posterPath}" alt="">
				
				<div class="py-3">
				
					<!-- TV Program 제목 -->
					<h2 class="card-title align-self-start list-inline-item">${tv.name}</h2>
	
					<!-- 종영 여부 -->
					<span class="status py-2">${tv.status}</span>
					
					<div class="align-self-content pb-2">
					
						<!-- TV Program 상영 기간 -->
						<h6 class="list-inline-item">${tv.firstAirDate} ~ ${tv.lastAirDate}</h6>
					
					</div>
	
					<!-- TV Program 전체 줄거리 -->
					<div class="card-text align-self-end pt-2">${tv.overview}</div>
						
				</div>
				<!-- End of contents -->
				
			</div>
			<!-- End of card -->

			<div class="spec pt-3">
			
				<dl>
				
					<!-- TV Program 장르 -->
					<dt>장르 :&nbsp;</dt>
					<dd class="on">
						<c:forEach var="genre" items="${tv.genres}" varStatus="index">
							${genre.name}
							<c:if test="${!index.last}">,&nbsp;</c:if>
						</c:forEach>
					</dd>
					
				</dl>
			</div>

			<div class="like">
				<a class="btn btn-dark text-white" href="${tv.homepage}">영상 보러가기</a>
			</div>

		</header>

	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript -->
	<script src="${root}/resources/vendor/jquery/jquery.min.js"></script>
	<script src="${root}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>