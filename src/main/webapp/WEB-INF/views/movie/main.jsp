<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>

	<c:set var="root" value="${pageContext.request.contextPath}" />

	<title>영화 목록</title>
	<meta charset="UTF-8">

	<!-- Custom styles for this template -->
	<link type="text/css" href="${root}/resources/css/modern-business.css" rel="stylesheet">

	<!-- Movie Card CSS -->
	<link type="text/css" href="${root}/resources/css/movie-card.css" rel="stylesheet">

	<!-- DropBox -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

</head>

<body>

	<div class="container pb-4">

		<div class="row">

			<c:if test="${array eq null}">
				<h1>데이터가 없습니다.</h1>
			</c:if>

			<c:if test="${array ne null}">

				<!-- 영화 정보가 담겨있는 Card -->
				<c:forEach var="movie" items="${array}">
					<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item d-flex flex-column">

						<div class="card h-100">

							<!-- Card image -->
							<a class="poster" href="${root}/movie/fullView.mn?movieId=${movie.id}">
								<!-- 포스터 -->
								<img class="card-img-top" src="${movie.posterPath}" alt="">
							</a>

							<!-- Card body -->
							<div class="card-body">

								<!-- 제목 -->
								<h4 class="card-title movie-title align-self-start">
									<a href="#">${movie.title}</a>
								</h4>

								<div class="align-self-content">

									<!-- 방영일 -->
									<span class="card-text release-date">${movie.releaseDate}</span>

									<!-- 상영시간 -->
									<span class="card-text runtime">(${movie.runtime}분)</span>

								</div>

								<!-- 장르 -->
								<p class="card-text genre align-self-end">
									<c:forEach var="genre" items="${movie.genres}" varStatus="index">
										${genre.name}
										<c:if test="${!index.last}">,&nbsp;</c:if>
									</c:forEach>
								</p>

							</div>

						</div>

					</div>
				</c:forEach>

			</c:if>

		</div>

		<!-- Pagination -->
		<ul class="pagination justify-content-center">

			<!-- movieTotalCount : 총 객체 수 / movieBlockCount : 한 페이지에 표시되는 객체 수 -->
			<!-- pageCount : 총 페이지 수 / pageBlock : 페이지 묶음 단위 -->
			<fmt:parseNumber var="temp" value="${movieTotalCount/movieBlockCount}" integerOnly="true" />
			<c:set var="pageCount" value="${temp + (movieTotalCount % movieBlockCount == 0 ? 0 : 1)}" />
			<c:set var="pageBlock" value="${10}" />

			<!-- pageNumber : 현재 페이지 번호 -->
			<fmt:parseNumber var="result" value="${(pageNumber-1)/pageBlock}" integerOnly="true" />
			<c:set var="startPage" value="${result * pageBlock + 1}" />
			<c:set var="endPage" value="${startPage + pageBlock -1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>

			<!-- 이전 버튼 -->
			<c:if test="${startPage > pageBlock}">
				<li class="page-item">
					<!-- condition 0~3 -->
					<c:if test="${condition != 4 && condition !=5}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${startPage-pageBlock}&condition=${condition}"
							aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if>

					<!-- condition 4 -->
					<c:if test="${condition == 4}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${startPage-pageBlock}&condition=${condition}&genreId=${genreId}"
							aria-label="Previous" data-genreIds="${genreIds}">
							<span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if>

					<!-- condition 5 -->
					<c:if test="${condition == 5}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${startPage-pageBlock}&condition=${condition}&year=${year}"
							aria-label="Previous" data-year="${year}">
							<span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if>
				</li>
			</c:if>

			<!-- 페이지 번호 생성 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li class="page-item">

					<!-- condition 0~3 -->
					<c:if test="${condition != 4 && condition !=5}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${i}&condition=${condition}">${i}</a>
					</c:if>

					<!-- condition 4 -->
					<c:if test="${condition == 4}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${i}&condition=${condition}&genreId=${genreId}">${i}</a>
					</c:if>

					<!-- condition 5 -->
					<c:if test="${condition == 5}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${i}&condition=${condition}&year=${year}">${i}</a>
					</c:if>
				</li>
			</c:forEach>

			<!-- 다음 버튼 -->
			<c:if test="${endPage < pageCount}">
				<li class="page-item">

					<!-- condition 0~3 -->
					<c:if test="${condition != 4 && condition !=5}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${endPage+1}&condition=${condition}"
							aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
							<span class="sr-only">Next</span>
						</a>
					</c:if>

					<!-- condition 4 -->
					<c:if test="${condition == 4}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${endPage+1}&condition=${condition}&genreId=${genreId}"
							aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
							<span class="sr-only">Next</span>
						</a>
					</c:if>

					<!-- condition 5 -->
					<c:if test="${condition == 5}">
						<a class="page-link"
							href="${root}/movie/setView.mn?pageNumber=${endPage+1}&condition=${condition}&year=${year}"
							aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
							<span class="sr-only">Next</span>
						</a>
					</c:if>




				</li>
			</c:if>
		</ul>

	</div>

	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="${root}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>