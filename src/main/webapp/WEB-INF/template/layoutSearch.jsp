<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Top bar + Filter + Content</title>

	<style>
		#topbar {
			width: 100%;
			height: 110px;
		}
		
		#filter {
			float: left;
			width: 20%;
			background-color: gray;
		}
		
		#content {
			float: left;
			width: 80%;
			background-color: black;
		}
		
		#filter, #content {
			min-height: 600px;
		}
	</style>

</head>
<body>

	<div id="topbar">
		<tiles:insertAttribute name="topbar" />
	</div>

	<div class="container">
		<div id="filter">
			<tiles:insertAttribute name="filter" />
		</div>

		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>
	</div>

</body>
</html>