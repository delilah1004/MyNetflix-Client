<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

	<c:set var="root" value="${pageContext.request.contextPath}" />
	
	<title>Slick Custom</title>
	
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick.css">
	<link rel="stylesheet" type="text/css" href="${root}/resources/slick/slick-theme.css">
	
	<link rel="stylesheet" type="text/css" href="${root}/resources/css/custom_slider.css">

</head>
<body>

<div class="wrapper">

	<section class="regular slider">
	
		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/dzOxNbbz1liFzHU1IPvdgUR647b.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/eDGB58IxMZsQ6F7cpr8IbgUuqk6.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/1sBx2Ew4WFsa1YY32vlHt079O03.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/UBhjjSojL8z2ThtitnyWaKM8nq.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/u4yfu7i4G1nLA6Njl7tETI1RThC.jpg"
				alt="Movie" />
		</div>
		
		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/dzOxNbbz1liFzHU1IPvdgUR647b.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/eDGB58IxMZsQ6F7cpr8IbgUuqk6.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/1sBx2Ew4WFsa1YY32vlHt079O03.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/UBhjjSojL8z2ThtitnyWaKM8nq.jpg"
				alt="Movie" />
		</div>

		<div class="item">
			<img
				src="https://image.tmdb.org/t/p/w220_and_h330_face/u4yfu7i4G1nLA6Njl7tETI1RThC.jpg"
				alt="Movie" />
		</div>
		
	</section>
	
	</div>

	<script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
	
	<script src="${root}/resources/slick/slick_custom.js" type="text/javascript" charset="utf-8"></script>
	<script src="${root}/resources/js/carousel.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
