
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<%@include file="./shared/sidebar.jsp"%>
			</div>
			<!-- To display the actual products -->
			<div class="col-md-9">
				<!-- Added Breadcrumb product -->
				<div class="row">
					<div class="col-lg-12">
						<c:if test="${userClickAllProducts==true}">

							<script>
								window.categoryId = '';
							</script>
							<ol class="breadcrumb">
								<li><a href="home">Home</a></li>
								<li class="active">All Products</li>
							</ol>
						</c:if>
						<c:if test="${userClickCategoryProducts==true}">

							<script>
								window.categoryId = '${category.id}';
							</script>
							<ol class="breadcrumb">
								<li><a href="${contextRoot}/home">Home</a></li>
								<li class="active">Category</li>
								<li class="active">${name}</li>

							</ol>
						</c:if>
					</div>
				</div>

				<div class="row">

					<div class="col-xs-12">

						<div class="container-fluid">
							<div class="table-responsive">

								<table id="productListTable"
									class="table table-striped table-bordered">

									<thead>

										<tr>
										    <th>Name</th>
											<th>Brand</th>
											<th>Price</th>
											<th>Qty. Available</th>
											<th></th>
										</tr>

									</thead>

									<tfoot>

										<tr>
											<th>Name</th>
											<th>Brand</th>
											<th>Price</th>
											<th>Qty. Available</th>
											<th></th>
										</tr>

									</tfoot>

								</table>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>