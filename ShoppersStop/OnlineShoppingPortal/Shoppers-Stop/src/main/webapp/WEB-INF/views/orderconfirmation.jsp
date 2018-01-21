
<%@include file="./shared/sidebar.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">

	<div class="col-xs-12">

		<ol class="breadcrumb">
			<li><a href="${contextRoot}/home">Home</a></li>
			<li><a href="${contextRoot}/listproducts">Products</a></li>
			<li class="active">${product.name}</li>
		</ol>
	</div>
</div>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h1 class="red-text text-center">Thank you for Shopping with us</h1>
	</div>
	<div class="panel-body">
		<!-- code to display the personal details -->
		<div class="text-center">

			<p class="lead">
				<a class="btn btn-primary btn-sm"
					href="http://localhost:8080/Shoppers-Stop/" role="button">Continue
					to homepage</a>
			</p>

		</div>
	</div>

</div>