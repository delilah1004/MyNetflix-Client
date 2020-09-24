<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!-- Bootstrap core CSS -->
<link type="text/css" href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS for topbar.jsp -->
<link type="text/css" href="${root}/resources/css/topbar.css" rel="stylesheet">

<!-- Navigation -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-black">
	<div class="container">
		<!-- 로고 -->
		<a class="navbar-brand" href="${root}/">MyNetflix</a>
		
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<!-- HOME -->
				<li class="nav-item"><a class="nav-link" href="${root}/">HOME</a></li>
				<!-- 영화 -->
				<li class="nav-item"><a class="nav-link" href="${root}/movie/setView.mn">영화</a></li>
				<!-- TV 프로그램 -->
				<li class="nav-item"><a class="nav-link" href="${root}/tv/setView.mn">TV 프로그램</a></li>
			</ul>
		</div>
	</div>
</nav>