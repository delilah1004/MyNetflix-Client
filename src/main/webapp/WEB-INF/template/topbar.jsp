<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!-- Bootstrap core CSS -->
<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS for topbar.jsp -->
<link href="${root}/resources/css/topbar.css" rel="stylesheet">

<!-- Navigation -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-black">
	<div class="container">
		<a class="navbar-brand" href="${root}/">MyNetflix</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="${root}/">HOME</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root}/movie/main.mn">영화</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root}/tv/main.mn">TV 프로그램</a></li>
			</ul>
		</div>
	</div>
</nav>