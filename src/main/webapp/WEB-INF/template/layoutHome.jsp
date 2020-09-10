<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Top bar + Content</title>
	
	<style>
		
		#topbar {
			width: 100%;
			height: 110px;
		}
		
		#content {
			width: 100%;
			background-color: black;
			min-height: 600px;
		}
		
	</style>
	
</head>
<body>

	<div id="topbar">
		<tiles:insertAttribute name="topbar" />
	</div>

	<div id="content">
		<tiles:insertAttribute name="content" />
	</div>

</body>
</html>