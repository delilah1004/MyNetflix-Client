<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap core CSS -->
<link href="${root}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS for topbar.jsp -->
<link href="${root}/resources/css/topbar.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${root}/resources/css/sb-admin-2.min.css" rel="stylesheet">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.navbar-height {
	height: 110px;
}
</style>

</head>

<body>
	<!-- Navigation -->
	<nav
		class="navbar navbar-expand-lg navbar-dark bg-black fixed-top navbar-height">
		<div class="container">

			<div>
				<a class="red title-text" href="${root}/main.mn">MyNetflix</a>
			</div>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item ml-2 active"><a
						class="nav-link top-menu-text" href="${root}/main.mn">Home </a></li>
					<li class="nav-item ml-2"><a class="nav-link top-menu-text"
						href="${root}/movie/fullView.mn">영화</a></li>
					<li class="nav-item ml-2"><a class="nav-link top-menu-text"
						href="${root}/tv/fullView.mn">TV 프로그램</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>