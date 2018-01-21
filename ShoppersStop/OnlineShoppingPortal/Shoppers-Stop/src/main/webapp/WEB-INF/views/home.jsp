
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
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

			<div class="col-lg-3">

				<%@include file="./shared/sidebar.jsp"%>

			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
		             	<div class="carousel-inner">
  						<div class="item active">
						<img class="slide-image" src="${images}/banner1.jpg" alt="">
						</div>
						<div class="item">
							<img class="slide-image" src="${images}/banner2.jpg" alt="">
						</div>
						<div class="item">
							<img class="slide-image" src="${images}/banner3.jpg" alt="">
						</div>
						<div class="item">
							<img class="slide-image" src="${images}/banner4.jpg" alt="">
						</div>
					</div>
					<a  class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
				   </a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
				<div class="row is-table-row">
					<c:forEach items="${products}" var="product">
						<div class="col-lg-4 col-md-6 mb-4">
							<div class="card h-100">
								<div class="card-body">
									<a href="${contextRoot}/listproducts"><img
										class="card-img-top"
										src="${images}/${product.value.getCode()}.jpg" alt=""></a>
								</div>
								<div class="card-footer">
									<h4 class="card-title">
										<a href="${contextRoot}/listproducts">${product.key}</a>
									</h4>
									<h4 class="card-text">${product.value.getName()}</h4>
									<h4 class="card-text">Cost: &#36;
										${product.value.getUnitPrice()}</h4>
									<h6 class="card-text">Total product views:
										${product.value.getViews()}</h6>
									<h6 class="card-text">Quantity Available:
										${product.value.getQuantity()}</h6>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="col-sm-4 col-lg-4 col-md-4">
					<h3>Checkout more products!</h3>
					<hr />
					<a class="btn btn-primary" href="${contextRoot}/listproducts">More
						Products</a>
				</div>
			</div>
			<!-- /.row -->

		</div>
		<!-- /.col-lg-9 -->

	</div>
	<!-- /.row -->

	</div>
</body>
</html>