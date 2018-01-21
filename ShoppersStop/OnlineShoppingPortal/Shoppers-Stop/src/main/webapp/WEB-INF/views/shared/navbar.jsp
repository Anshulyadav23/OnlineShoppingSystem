<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="/scripts/plugins/jquery/jquery-1.7.2.min.js">
	
</script>
<script src="/scripts/plugins/bootstrap/js/bootstrap.js"></script>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="${contextRoot}/home">Shoppers-Stop</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="${contextRoot}/home">Home</a> <span class="sr-only">(current)</span>
					</li>
					<li class="nav-item">
					<li id="about"><a class="nav-link" href="${contextRoot}/about">About</a>
					</li>
					<li class="nav-item">
					<li id=contact><a class="nav-link"
						href="${contextRoot}/contact">Contact</a></li>
					<li class="nav-item">
					<li id="listProducts"><a class="nav-link"
						href="${contextRoot}/listproducts">View Products</a></li>
					<security:authorize access="hasAuthority('ADMIN')">
						<li id="manageProducts"><a class="nav-link"
							href="${contextRoot}/manage/products">Manage Products</a></li>
						<li id="backOrder"><a class="nav-link"
							href="${contextRoot}/manage/backOrder">Back Orders</a></li>
					</security:authorize>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<security:authorize access="isAnonymous()">
						<li id="register"><a href="${contextRoot}/register">Sign
								Up</a></li>

						<li id="login"><a href="${contextRoot}/login">Login</a></li>
					</security:authorize>

 						 <!-- Drop down menu -->

					<security:authorize access="isAuthenticated()">
						<li class="dropdown" id="userCartS"><a
							href="javascript:void(0)" class="btn btn-default dropdown-toggle"
							id="dropdownMenu1" data-toggle="dropdown">
								${userModel.fullName} </a>
							<ul class="dropdown-menu">

								<security:authorize access="hasAuthority('USER')">

									<li><a href="${contextRoot}/cart/show"> <span
											class="glyphicon glyphicon-shopping-cart"></span> <span
											class="badge">${userModel.cart.cartLines}</span> - &#036;
											${userModel.cart.grandTotal}
									</a></li>
                                 <li class="divider" role="separator"></li>
								</security:authorize>
								<li><a href="${contextRoot}/perform-logout">Logout</a></li>
							</ul></li>
					</security:authorize>
				</ul>
			</div>
		</div>
	</nav>
	<script>
		window.userRole = '${userModel.role}';
	</script>





</body>
</html>