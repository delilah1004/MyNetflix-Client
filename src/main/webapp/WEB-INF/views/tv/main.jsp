<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>

	<c:set var="root" value="${pageContext.request.contextPath}" />

	<title>TV 프로그램 목록</title>
	<meta charset="UTF-8">

	<!-- Bootstrap core CSS -->
	<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${root}/resources/css/modern-business.css" rel="stylesheet">

	<!-- TV Program Card CSS -->
	<link href="${root}/resources/css/tv-card.css" rel="stylesheet">

	<!-- Drop down -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<!-- Bootstrap core jquery -->
	<script type="text/javascript" src="${root}/resources/vendor/jquery/jquery.min.js"></script>

	<!-- Search jquery -->
	<script type="text/javascript" src="${root}/resources/jquery/search.js"></script>

	<!-- GenreSearch Script -->
	<script type="text/javascript" src="${root}/resources/js/genreSearch.js"></script>

</head>

<body>

	<div class="container pb-4">

		<div class="row p-3">

			<!-- Drop Down -->
			<div class="dropdown mx-3 float-left">
				<button type="button" class="btn btn-outline-dark dropdown-toggle" data-toggle="dropdown">검색 분류</button>
				<div class="dropdown-menu">
					<%-- <!-- 인기도 내림차순 -->
					<a class="dropdown-item" href="#"
						onclick="javascript:page_move('${root}/tv/setView.mn/post', '0', null);">인기도 내림차순</a>
					<!-- 인기도 오름차순 -->
					<a class="dropdown-item" href="#"
						onclick="javascript:page_move('${root}/tv/setView.mn/post', '1', null);">인기도 오름차순</a> --%>
					<!-- 인기도 내림차순 -->
					<a class="dropdown-item" href="${root}/tv/setView.mn?condition=0">인기도
						내림차순</a>
					<!-- 인기도 오름차순 -->
					<a class="dropdown-item" href="${root}/tv/setView.mn?condition=1">인기도
						오름차순</a>
					<!-- 최신순 -->
					<a class="dropdown-item" href="${root}/tv/setView.mn?condition=2">최신순</a>
					<!-- 오래된순 -->
					<a class="dropdown-item" href="${root}/tv/setView.mn?condition=3">오래된순</a>
					<!-- 연도별 검색 -->
					<a class="dropdown-item yearSearch" href="#">연도별 검색</a>
					<!-- 장르별 검색 -->
					<a class="dropdown-item genreSearch" href="#">장르별 검색</a>
				</div>
			</div>


			<!-- Year Search -->
			<div id="yearSearch" class="dropdown mx-3 float-left" style="display: none">
				<button type="button" class="btn btn-outline-dark dropdown-toggle" data-toggle="dropdown">연도 검색</button>
				<div class="dropdown-menu">
					<c:forEach var="year" begin="2010" end="2020">
						<a class="dropdown-item" href="${root}/tv/setView.mn?condition=5&year=${year}">${year}</a>
					</c:forEach>
				</div>
			</div>
		</div>

		<!-- Genre Search -->
		<div id="genreSearch" class="row px-3 pb-3" style="display: none">

			<form id="genreSelector" class="mx-3" action="${root}/tv/setView.mn" onsubmit="genreCheck(this);">

				<c:forEach var="genre" items="${genres}">

					<div class="custom-control custom-checkbox float-left mr-3">
						<!-- 체크박스 -->
						<input type="checkbox" class="custom-control-input" id="${genre}" name="genre" value="${genre}">
						<!-- 장르명 -->
						<label class="custom-control-label" for="${genre}">${genre}</label>
					</div>

				</c:forEach>

				<!-- 기존 장르 선택 정보 갱신 -->
				<c:if test="${genreIds eq null}">
				<c:forTokens var="genreId" items="${genreIds}" delims=" ">
					<script type="text/javascript">
						for (var i = 0; i < genreSelector.genreIds.length; i++) {
							if (genreSelector.genre[i].value == "${genreId}") {
								genreSelector.genre[i].checked = true;
							}
						}
					</script>
				</c:forTokens>
				</c:if>

				<input type="hidden" name="selectedGenres">
				<input type="hidden" name="condition" value="4">
				<input type="submit" value="검색" />
				
			</form>

		</div>

		<div class="row">

			<c:if test="${array eq null}">
				<h1>데이터가 없습니다.</h1>
			</c:if>

			<c:if test="${array ne null}">
				<!-- TV 프로그램 정보가 담겨있는 Card -->
				<c:forEach var="tv" items="${array}">

					<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item d-flex flex-column">

						<div class="card h-100">
						
							<!-- Card image -->
							<a class="poster" href="${root}/tv/fullView.mn?tvId=${tv.id}">
								<!-- 포스터 --> <img class="card-img-top" src="${tv.posterPath}" alt="">
							</a>
							
							<!-- Card body -->
							<div class="card-body">
							
								<!-- 제목 -->
								<h4 class="card-title tv-name align-self-start">
									<a href="#">${tv.name}</a>
								</h4>
								
								<!-- 방영일 -->
								<div class="align-self-content">
									<span class="card-text">${tv.firstAirDate}</span>
									<c:if test="${tv.lastAirDate ne null}">
										<span class="card-text"> ~ ${tv.lastAirDate}</span>
									</c:if>
								</div>
								
								<!-- 장르 -->
								<p class="card-text genre align-self-end">
									<c:forEach var="genre" items="${tv.genres}" varStatus="index">
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

			<!-- tvTotalCount : 총 객체 수 / tvBlockCount : 한 페이지에 표시되는 객체 수 -->
			<!-- pageCount : 총 페이지 수 / pageBlock : 페이지 묶음 단위 -->
			<fmt:parseNumber var="temp" value="${tvTotalCount/tvBlockCount}" integerOnly="true" />
			<c:set var="pageCount" value="${temp + (tvTotalCount % tvBlockCount == 0 ? 0 : 1)}" />
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
							href="${root}/tv/setView.mn?pageNumber=${startPage-pageBlock}&condition=${condition}"
							aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if>

					<!-- condition 4 -->
					<c:if test="${condition == 4}">
						<a class="page-link"
							href="${root}/tv/setView.mn?pageNumber=${startPage-pageBlock}&condition=${condition}"
							aria-label="Previous" data-genreIds="${genreIds}">
							<span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if>

					<!-- condition 5 -->
					<c:if test="${condition == 5}">
						<a class="page-link"
							href="${root}/tv/setView.mn?pageNumber=${startPage-pageBlock}&condition=${condition}"
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
							href="${root}/tv/setView.mn?pageNumber=${i}&condition=${condition}">${i}</a>
					</c:if>

					<!-- condition 4 -->
					<c:if test="${condition == 4}">
						<a class="page-link" href="${root}/tv/setView.mn?pageNumber=${i}&condition=${condition}&genreIds=${genreIds}">${i}</a>
					</c:if>

					<!-- condition 5 -->
					<c:if test="${condition == 5}">
						<a class="page-link" href="${root}/tv/setView.mn?pageNumber=${i}&condition=${condition}"
							data-year="${year}">${i}</a>
					</c:if>

				</li>
			</c:forEach>

			<!-- 다음 버튼 -->
			<c:if test="${endPage < pageCount}">
				<li class="page-item">

					<!-- condition 0~3 -->
					<c:if test="${condition != 4 && condition !=5}">
						<a class="page-link" href="${root}/tv/setView.mn?pageNumber=${endPage+1}&condition=${condition}"
							aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
							<span class="sr-only">Next</span>
						</a>
					</c:if>

					<!-- condition 4 -->
					<c:if test="${condition == 4}">
						<a class="page-link" href="${root}/tv/setView.mn?pageNumber=${endPage+1}&condition=${condition}"
							aria-label="Next" data-genreIds="${genreIds}">
							<span aria-hidden="true">&raquo;</span>
							<span class="sr-only">Next</span>
						</a>
					</c:if>

					<!-- condition 5 -->
					<c:if test="${condition == 5}">
						<a class="page-link" href="${root}/tv/setView.mn?pageNumber=${endPage+1}&condition=${condition}"
							aria-label="Next" data-year="${year}">
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