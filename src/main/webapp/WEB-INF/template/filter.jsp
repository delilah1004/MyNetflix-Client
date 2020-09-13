<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!-- Bootstrap core CSS -->
<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Sidebar Widgets Column -->
<div class="overflow-hidden container py-4">

	<!-- Drop Down -->
	<div class="dropdown mr-4 float-left">
		<button type="button" class="btn btn-outline-dark dropdown-toggle"
			data-toggle="dropdown">검색 분류 </button>
		<div class="dropdown-menu">
			<a class="dropdown-item" href="#">장르별 검색</a> <a class="dropdown-item"
				href="#">연도별 검색</a> <a class="dropdown-item" href="#">기타 검색</a>
		</div>
	</div>

	<!-- Search Widget -->
	<div class="card mr-4 float-left" hidden>
		<h5 class="card-header">Search</h5>
		<div class="card-body">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-append">
					<button class="btn btn-secondary" type="button">Go!</button>
				</span>
			</div>
		</div>
	</div>

	<!-- Categories Widget -->
	<div class="card mx-4 float-left" hidden>
		<h5 class="card-header">Categories</h5>
		<div class="card-body">
			<div class="row">
				<div class="col-lg-6">
					<ul class="list-unstyled mb-0">
						<li><a href="#">Web Design</a></li>
						<li><a href="#">HTML</a></li>
						<li><a href="#">Freebies</a></li>
					</ul>
				</div>
				<div class="col-lg-6">
					<ul class="list-unstyled mb-0">
						<li><a href="#">JavaScript</a></li>
						<li><a href="#">CSS</a></li>
						<li><a href="#">Tutorials</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</div>

<!-- Bootstrap core jquery -->
<script type="text/javascript"
	src="${root}/resources/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap core JavaScript -->
<script type="text/javascript"
	src="${root}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

