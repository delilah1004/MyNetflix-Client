<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<style type="text/css">
	
		#topbar {
			position: absolute;
			height: 74px;
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

	<div id="body">
		<tiles:insertAttribute name="body" />
	</div>

</body>
</html>