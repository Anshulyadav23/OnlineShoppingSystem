
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
	

	<h1 class="red-text text-center">Buffer-Orders Tracking</h1>
	<hr />
	<div class="container">
		<div class="row">
		  <div class="row">
          <div class="col-xs-12">

						<div class="container-fluid">
							<div class="table-responsive">

					<!-- product table for admin -->
					<table id="backOrderTable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>UserId</th>
								<th>UserName</th>
								<th>ProductName</th>
								<th>QuantityAvailable</th>
								<th>QuantityNeeded</th>
								<th>E-mail</th>
							</tr>
						</thead>

						<tfoot>
							<tr>
								<th>UserId</th>
								<th>UserName</th>
								<th>ProductName</th>
								<th>QuantityAvailable</th>
								<th>QuantityNeeded</th>
								<th>E-mail</th>
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