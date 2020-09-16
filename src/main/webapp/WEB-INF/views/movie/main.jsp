<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<c:set var="root" value="${pageContext.request.contextPath}" />

<title>영화 목록</title>
<meta charset="UTF-8">

<!-- Bootstrap core CSS -->
<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${root}/resources/css/modern-business.css" rel="stylesheet">

<!-- DropBox -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

</head>
<body>

	<div class="container py-4">
	
		<div class="row px-2">			
			
			<!-- Drop Down -->
			<div class="dropdown mx-3 mb-3 float-left">
				<button type="button" class="btn btn-outline-dark dropdown-toggle" data-toggle="dropdown">검색 분류 </button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="${root}/movie/setView.mn?condition=0">인기도 내림차순</a>
					<a class="dropdown-item" href="${root}/movie/setView.mn?condition=1">인기도 오름차순</a>
					<a class="dropdown-item" href="${root}/movie/setView.mn?condition=0">최신순</a>
					<a class="dropdown-item" href="${root}/movie/setView.mn?condition=3">오래된순</a>
					<a class="dropdown-item" href="#">장르별 검색</a>
					<a class="dropdown-item" href="#">연도별 검색</a>
				</div>
			</div>
			
			<!-- Search Widget -->
			<div id="genreSearch" class="col-lg-3 col-md-4 col-sm-6 input-group mr-4 float-left">
				<input type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-append">
					<button class="btn btn-secondary" type="button">Go!</button>
				</span>
			</div>

			<div class="input-group col-lg-3">
				<input type="text" class="form-control" placeholder="장르명을 검색하세요">
				<div class="input-group-append">
					<button class="btn btn-secondary" type="button">검색</button>
				</div>
			</div>

		</div>
	
	
		<div class="row">
		
		<!-- 영화 정보가 담겨있는 Card -->
		<c:forEach var="movie" items="${array}">
			<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">
			
				<div class="card h-100">
					<!-- Card image -->
					<a href="${root}/movie/fullView.mn?movieId=${movie.id}">
						<!-- 포스터 -->
						<img class="card-img-top" src="${movie.posterPath}" alt="">
					</a>
					<!-- Card body -->
					<div class="card-body">
						<h4 class="card-title movie-title">
							<a href="#">${movie.title}</a>
						</h4>
						<span class="card-text release-date">${movie.releaseDate}</span>
						<span class="card-text runtime">(${movie.runtime}분)</span>
						<p class="card-text genre">코미디, 범죄, 스릴러</p>
					</div>
				
				</div>
			
			</div>
			
		</c:forEach>
			
		</div>

		<!-- Pagination -->
		<ul class="pagination justify-content-center">
		
			<!-- pageCount : 총 페이지 수 / pageBlock : 페이지 묶음 단위 -->
			<fmt:parseNumber var="temp" value="${movieListCount/listSize}" integerOnly="true"/>
			<c:set var="pageCount" value="${temp + (movieListCount % listSize == 0 ? 0 : 1)}"/>
			<c:set var="pageBlock" value="${10}"/>
	
			<!-- pageNumber : 현재 페이지 번호 -->
			<fmt:parseNumber var="result" value="${(pageNumber-1)/pageBlock}" integerOnly="true"/>
			<c:set var="startPage" value="${result * pageBlock + 1}"/>
			<c:set var="endPage" value="${startPage + pageBlock -1}"/>
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}"/>
			</c:if>
		
			<!-- 이전 버튼 -->
			<c:if test="${startPage > pageBlock}">
			<li class="page-item">
				<a class="page-link" href="${root}/movie/setView.mn?pageNumber=${startPage-pageBlock}&condition=${condition}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span> 
					<span class="sr-only">Previous</span>
				</a>
			</li>
			</c:if>
			
			<!-- 페이지 번호 생성 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li class="page-item">
					<a class="page-link" href="${root}/movie/setView.mn?pageNumber=${i}&condition=${condition}">${i}</a>
				</li>
			</c:forEach>
			
			<!-- 다음 버튼 -->
			<c:if test="${endPage < pageCount}">
			<li class="page-item">
				<a class="page-link" href="${root}/movie/setView.mn?pageNumber=${endPage+1}&condition=${condition}" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span> 
					<span class="sr-only">Next</span>
				</a>
			</li>
			</c:if>
		</ul>

	</div>
	
	<!-- Bootstrap core jquery -->
	<script type="text/javascript" src="${root}/resources/vendor/jquery/jquery.min.js"></script>
	
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="${root}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
