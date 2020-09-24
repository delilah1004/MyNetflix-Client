<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<!-- Custom Select Box 플러그인 -->
<link type="text/css" href="${root}/resources/select/css/style.css" rel="stylesheet">
<script type="text/javascript" src="${root}/resources/select/js/custom_select.js"></script>

<script type="text/javascript">

	$(document).ready(function () {

		$(".genreSearch").click(function () {
			$("#genreSearch").show();
			$("#yearSearch").hide();
		});

		$(".yearSearch").click(function () {
			$("#genreSearch").hide();
			$("#yearSearch").show();
		});

		var genreId = '<c:out value="${genreId}" />';
		var year = '<c:out value="${year}" />';

		if (genreId != "") {

			$("#genreSearch").show();

			if (document.getElementById(genreId).value == genreId) {
				document.getElementById(genreId).checked = true;
			}
		}

		if (year != "") {

			$("#yearSearch").show();
		}

	});

</script>

<!-- Select Box -->
<div class="container pb-4">

	<div class="row p-3">

		<!-- Drop Down -->
		<div class="select_wrap">
			<ul class="default_option">
				<li>
					<div class="option">
						<p>검색분류</p>
					</div>
				</li>
			</ul>

			<ul class="select_ul">

				<!-- 인기도 내림차순 -->
				<!-- <li data-condition="1">
					<div class="option">
						<p>인기도&nbsp;오름차순</p>
					</div>
				</li> -->

				<!-- 인기도 내림차순 -->
				<a href="${root}/movie/setView.mn?condition=0">
					<li>
						<div class="option">
							<p>인기도&nbsp;내림차순</p>
						</div>
					</li>
				</a>

				<!-- 인기도 오름차순 -->
				<a href="${root}/movie/setView.mn?condition=1">
					<li>
						<div class="option">
							<p>인기도&nbsp;오름차순</p>
						</div>
					</li>
				</a>

				<!-- 최신순 -->
				<a href="${root}/movie/setView.mn?condition=2">
					<li>
						<div class="option">
							<p>최신순</p>
						</div>
					</li>
				</a>

				<!-- 오래된순 -->
				<a href="${root}/movie/setView.mn?condition=3">
					<li>
						<div class="option">
							<p>오래된순</p>
						</div>
					</li>
				</a>

				<!-- 장르별 검색 -->
				<a class="genreSearch" href="#">
					<li>
						<div class="option">
							<p>장르별&nbsp;검색</p>
						</div>
					</li>
				</a>

				<!-- 연도별 검색 -->
				<a class="yearSearch" href="#">
					<li>
						<div class="option">
							<p>연도별&nbsp;검색</p>
						</div>
					</li>
				</a>

			</ul>
		</div>

		<!-- Year Search -->
		<div id="yearSearch" class="select_wrap" style="display: none">
			<ul class="default_option">
				<li>
					<div class="option">
						<p>연도 검색</p>
					</div>
				</li>
			</ul>

			<ul class="select_ul">

				<c:forEach var="year" begin="2010" end="2020">
					<a href="${root}/movie/setView.mn?condition=5&year=${year}">
						<li>
							<div class="option">
								<p>${year}</p>
							</div>
						</li>
					</a>
				</c:forEach>
				
			</ul>
		</div>

	</div>

</div>

<!-- Genre Search -->
<div id="genreSearch" class="row px-3 pb-3" style="display: none">

	<form id="genreSelector" class="mx-3" action="${root}/movie/setView.mn">

		<c:forEach var="genre" items="${genres}">

			<div class="custom-control custom-radio float-left mr-3">

				<!-- 라디오 박스 -->
				<input type="radio" class="custom-control-input" id="${genre.id}" name="genreId" value="${genre.id}">

				<!-- 장르명 -->
				<label class="custom-control-label" for="${genre.id}">${genre.name}</label>

			</div>

		</c:forEach>

		<input type="hidden" name="condition" value="4"> <input type="submit" value="검색" />

	</form>

</div>
</div>