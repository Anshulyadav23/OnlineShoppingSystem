
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">



<title>Shoppers-Stop - ${title}</title>
<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}'
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Datatables CSS -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap Datatables CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom Css -->
<link href="${css}/shop-homepage.css" rel="stylesheet">



</head>
<body>
	<div class="wrapper">

		<!-- Navigation -->

		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<!-- load the home content -->
		<div class="content">
			<c:if test="${userClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- load only if user clicks about -->
			<c:if test="${userClickAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- load only when user clicks contact-->
			<c:if test="${userClickContact == true }">
				<%@include file="contact.jsp"%>
			</c:if>

			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true }">
				<%@include file="listproducts.jsp"%>
			</c:if>
			
			<!-- Load this when user clicks manage products -->
			
			<c:if test="${userClickManageProducts == true }">
				<%@include file="manageProducts.jsp"%>
			</c:if>

			<!-- load only when user click show product -->
			<c:if test="${userClickShowProduct == true }">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			
			<c:if test="${userClickBackOrder == true }">
				<%@include file="backOrder.jsp"%>
			</c:if>

			<c:if test="${userClickShowCart == true }">
				<%@include file="cart.jsp"%>
			</c:if>
		</div>
		<!-- footer comes here -->


		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.js"></script>

		<!-- Jquery validator -->
		<script src="${js}/jquery.validate.js"></script>

		<script src="${js}/bootstrap.min.js"></script>


		<!-- DataTable Bootstrap Script -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- DataTable Plugin -->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!-- BootBox -->
		<script src="${js}/bootbox.min.js"></script>

		<!-- self coded java script -->
		<script src="${js}/myapp.js"></script>


	</div>
</body>

</html>

