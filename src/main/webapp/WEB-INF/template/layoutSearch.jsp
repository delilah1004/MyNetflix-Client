<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<style>
	
		#topbar {
			height: 74px;
		}
		
		#filter {
			height: 200px;
			background-color: gray;
		}
		
	</style>

</head>
<body>

	<div id="topbar">
		<tiles:insertAttribute name="topbar" />
	</div>

	<div id="filter">
		<tiles:insertAttribute name="filter" />
	</div>

	<div id="content">
		<tiles:insertAttribute name="content" />
	</div>
	
</body>
</html>