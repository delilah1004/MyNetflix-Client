<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<c:set var="root" value="${pageContext.request.contextPath}" />

<title>Slick Playground</title>
<meta charset="UTF-8">

<!-- Bootstrap core CSS -->
<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${root}/resources/css/modern-business.css" rel="stylesheet">

</head>
<body>

	<div class="container py-4">
		<div class="row">
		
		<!-- TV 프로그램 정보가 담겨있는 Card -->
		<c:forEach var="tv" items="${array}">
		
			<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">
			
				<div class="card h-100">
					<!-- Card image -->
					<a href="${root}/tv/fullView.mn?tvId=${tv.id}">
						<!-- 포스터 -->
						<img class="card-img-top" src="${tv.posterPath}" alt="">
					</a>
					<!-- Card body -->
					<div class="card-body">
						<h4 class="card-title tv-name">
							<a href="#">${tv.name}</a>
						</h4>
						<span class="card-text first-air-date">${tv.firstAirDate}</span>
						<span class="card-text runtime">(${tv.episodeRunTime}분)</span>
						<p class="card-text genre">코미디, 범죄, 스릴러</p>
					</div>
				
				</div>
			
			</div>
			
		</c:forEach>
			
		</div>

		<!-- Pagination -->
		<ul class="pagination justify-content-center">
		
			<!-- pageCount : 총 페이지 수 / pageBlock : 페이지 묶음 단위 -->
			<fmt:parseNumber var="temp" value="${tvListCount/listSize}" integerOnly="true"/>
			<c:set var="pageCount" value="${temp + (tvListCount % listSize == 0 ? 0 : 1)}"/>
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
				<a class="page-link" href="${root}/tv/main.mn?pageNumber=${startPage-pageBlock}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span> 
					<span class="sr-only">Previous</span>
				</a>
			</li>
			</c:if>
			
			<!-- 페이지 번호 생성 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li class="page-item">
					<a class="page-link" href="${root}/tv/main.mn?pageNumber=${i}">${i}</a>
				</li>
			</c:forEach>
			
			<!-- 다음 버튼 -->
			<c:if test="${endPage < pageCount}">
			<li class="page-item">
				<a class="page-link" href="${root}/tv/main.mn?pageNumber=${endPage+1}" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span> 
					<span class="sr-only">Next</span>
				</a>
			</li>
			</c:if>
		</ul>

	</div>
</body>
</html>
