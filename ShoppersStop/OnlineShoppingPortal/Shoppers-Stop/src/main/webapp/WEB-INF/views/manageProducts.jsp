
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
			<c:if test="${not empty message}">
				<div class="row">
					<div class="col-xs-12 col-md-offset-2 col-md-8">
						<div class="alert alert-info fade in">${message}</div>
					</div>
				</div>
			</c:if>
			<div class="col-md-offset-2 col-md-8">
				<div class="panel panel-primary">
					<div class="panel-heading">

						<h4>Product Management</h4>

					</div>

					<div class="panel-body">
						<!-- FORM ELEMENTS -->
                   <!-- model attribute same as the name of object specified in controller -->
                              <sf:form class="form-horizontal" modelAttribute="product"
							action="${contextRoot}/manage/products" method="POST"
							enctype="multipart/form-data">

							<div class="form-group">
								<label class="control-label col-md-4" for="name">Enter
									Product Name:</label>
								<div class="col-md-8">
									<sf:input type="text" path="name" id="name"
										placeholder="Product Name" class="form-control" />
									<sf:errors path="name" style="color:red" element="em" />
								</div>
							</div>


							<div class="form-group">
								<label class="control-label col-md-4" for="brand">Enter
									Brand Name:</label>
								<div class="col-md-8">
									<sf:input type="text" path="brand" id="brand"
										placeholder="Brand Name" class="form-control" />
									<sf:errors path="brand" style="color:red" element="em" />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4" for="description">Enter
									Product Description:</label>
								<div class="col-md-8">
									<sf:textarea path="description" id="description" rows="4"
										placeholder="Write a description for the product"
										class="form-control" />
									<sf:errors path="description" style="color:red" element="em" />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4" for="unitPrice">Enter
									Unit Price:</label>
								<div class="col-md-8">
									<sf:input type="number" path="unitPrice" id="unitPrice"
										placeholder="Unit Price in &#8377" class="form-control" />
									<sf:errors path="unitPrice" style="color:red" element="em" />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4" for="quantity">Quantity
									Available:</label>
								<div class="col-md-8">
									<sf:input type="number" path="quantity" id="quantity"
										placeholder="Quantity Available" class="form-control" />
								</div>
							</div>


							<!--  file element for uploading image -->


							<div class="form-group">
								<label class="control-label col-md-4" for="file">Select
									an image:</label>
								<div class="col-md-8">
									<sf:input type="file" path="file" id="file"
										class="form-control" />
									<sf:errors path="file" style="color:red" element="em" />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4" for="categoryId">Select
									Category:</label>
								<div class="col-md-8">
									<sf:select class="form-control" id="categoryId"
										path="categoryId" items="${categories}" itemLabel="name"
										itemValue="id" />
									<c:if test="${product.id == 0 }">
										<div class="text-right">
											<br />
											<button type="button" data-toggle="modal"
												data-target="#myCategoryModal"
												class="btn btn-warning btn-xs">Add Category</button>
										</div>
									</c:if>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<input type="submit" name="submit" id="submit" value="Submit"
										class="btn btn-primary" />

									<!-- hidden fields -->
									<sf:hidden path="id" />
									<sf:hidden path="code" />
									<sf:hidden path="supplierId" />
									<sf:hidden path="active" />
									<sf:hidden path="purchases" />
									<sf:hidden path="views" />

								</div>
							</div>
						</sf:form>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" id="myCategoryModal" role="dialog"
		tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!-- modal header -->
				<div class="modal-header">
					<h2 class="modal-title">Add New Category</h2>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- category form -->
					<sf:form id="categoryForm" modelAttribute="category"
						action="${contextRoot}/manage/category" method="POST"
						class="form-horizontal">

						<div class="form-group">
							<label for="category_name" class="control-label col-md-4">Category
								Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name"
									class="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="category_description" class="control-label col-md-4">Category
								Description</label>
							<div class="col-md-8">
								<sf:textarea cols="" rows="5" path="description"
									id="category_description" class="form-control" />
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category"
									class="btn btn-primary" />
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>


	<hr />
	<h1 class="red-text text-center">Available Products</h1>
	<hr />
		<div class="container">
		<div class="row">
			 
		  <div class="row">
          <div class="col-xs-12">

						<div class="container-fluid">
							<div class="table-responsive">

					<!-- product table for admin -->
					<table id="adminProductTable"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Quantity</th>
								<th>Unit Price</th>
								<th>Active</th>
								<th>Edit</th>
							</tr>
						</thead>

						<tfoot>
							<tr>
								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Quantity</th>
								<th>Unit Price</th>
								<th>Active</th>
								<th>Edit</th>
							</tr>
						</tfoot>
					</table>
				</div>

			</div>

		</div>

</div>
</div>
	</div>


</body>