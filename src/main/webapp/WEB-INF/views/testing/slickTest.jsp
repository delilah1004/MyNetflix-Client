<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<c:set var="root" value="${pageContext.request.contextPath}" />
	
	<title>Slick 예제</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick.css">
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick-theme.css">
	
	<link rel="stylesheet" type="text/css" href="${root}/resources/css/slick_slider.css">
</head>
<body>
	<h1>${check}</h1>

	<section class="regular slider">
		<c:forEach var="tv" items="${array}">
			<div>
				<img src="${tv.posterPath}">
			</div>
		</c:forEach>
	</section>
	
	<!-- Carousel Slider - Slick -->
	<script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
	<script src="${root}/resources/slick/slick.js" type="text/javascript" charset="utf-8"></script>
	
	<!-- Carousel Slider - Slick(Regular) -->
	<script src="${root}/resources/js/slick_slider.js" type="text/javascript"></script>
</body>
</html>
