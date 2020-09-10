<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-black fixed-top">
	<div class="container">
		<a class="red title-text" href="#">MyNetflix</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link top-menu-text" data-toggle="tab" href="${root}/main.mn">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link top-menu-text" data-toggle="tab" href="${root}/main.mn">영화</a></li>
				<li class="nav-item"><a class="nav-link top-menu-text" data-toggle="tab" href="#">TV 프로그램</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
