<%@include file="./shared/sidebar.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- column to display personal details -->
<div class="row">
	<div class="col-sm-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Invoice</h4>
			</div>
			<div class="panel-body">
				<!-- code to display the personal details -->
				<div class="text-center">
					<h2>Delivery Address : ${address.getAddressLineOne()}
						${registerModel.getAddressLineTwo()}</h2>
					<h2>City : ${address.getCity()}</h2>
					<h2>State:${address.getState()}</h2>
					<h2>Country : ${address.getCountry()}</h2>
					<h2>Postal Code : ${address.getPostalCode()}</h2>
				</div>
			</div>
			<div class="panel-footer">
				<!-- anchor to move to the edit of personal details -->
			</div>
		</div>
	</div>

	<!-- column to display the address -->
	<div class="col-sm-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Items Purchased</h4>
			</div>
			<div class="panel-body">

				<div class="text-center">

					<c:forEach var="value" items="${products }" varStatus="loop">
						<h2>${value.getName()}quantity :
							${cartline[loop.index].getProductCount()}</h2>
					</c:forEach>
					<h2>Total Amount: &#36; ${totalamount}</h2>
				</div>

			</div>
			<div class="panel-footer">
				<!-- anchor to display the edit of address -->

			</div>
		</div>
	</div>
</div>

<!-- to provide the confirm button after displaying the details -->
<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<div class="text-center">
			<!--  anchor to move to the success page -->
			<a href="orderconfirm" class="btn btn-primary">Confirm</a>
		</div>

	</div>

</div>

<%@include file="./shared/footer.jsp"%>



