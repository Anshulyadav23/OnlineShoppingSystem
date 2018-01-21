<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<title>Online Shopping - ${title}</title>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">


<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}'
</script>
</head>


<body>


	<!-- Navigation -->
	<nav class="navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="${contextRoot}/home">Shoppers Stop</a>
			</div>

		</div>
	</nav>

	<!--  page content -->

	<div class="content">
		<div class="container">
			<%--this will be displayed if credentials are wrong --%>
			<c:if test="${not empty message }">
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="alert alert-danger">${message}</div>
					</div>
				</div>
			</c:if>

			<%--this will be displayed if user is looged out --%>
			<c:if test="${not empty logout}">
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="alert alert-success">${logout}</div>
					</div>
				</div>
			</c:if>

			<div class="row">
				<div class="col-lg-offset-3 col-lg-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4>Login</h4>
						</div>
						<div class="panel-body">

							<form action="${contextRoot}/login" method="POST"
								class="form-horizontal" id="loginForm">
								<div class="form-group">
									<label for="username" class="col-md-4 control-label">Email:</label>
									<div class="col-md-8">
										<input type="text" name="username" id="username"
											class="form-control" placeholder="Mail Id">
									</div>
								</div>

								<div class="form-group">
									<label for="password" class="col-md-4 control-label">Password:</label>
									<div class="col-md-8">
										<input type="password" name="password" id="password"
											class="form-control" placeholder="Password">
									</div>
								</div>

								<div class="form-group">
									<div class="col-ms-offset-4 col-md-8">
										<input type="submit" value="Login" class="btn btn-primary">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</div>
								</div>
							</form>
						</div>

						<div class="panel-footer">
							<div class="text-left">
								New User - <a href="${contextRoot}/register">Register Here </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.js"></script>

		<!-- Jquery validator -->
		<script src="${js}/jquery.validate.js"></script>

		<script src="${js}/bootstrap.min.js"></script>


		<!-- self coded java script -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>

