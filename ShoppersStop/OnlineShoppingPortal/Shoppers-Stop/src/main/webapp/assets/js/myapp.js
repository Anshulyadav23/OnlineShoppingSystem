//J query data
//active menu problem
$(function() {
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Back Orders':
		$('#backOrder').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	 case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		$('#listproducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// to tackle csrf token

	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if (token.length > 0 && header.length > 0) {
		// set the token header for ajax request
		$(document).ajaxSend(function(e, xhr, options) {

			xhr.setRequestHeader(header, token);
		});

	}

	// code for jquery dataTable
	var $table = $('#productListTable');

	// execute the below code where we have this table

	if ($table.length) {

		// console.log('Inside the table');
		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/listproducts';
		} else {
			jsonUrl = window.contextRoot + '/json/data/showproducts/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'All' ] ],
					pageLength : 5,

					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},

					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#036; ' + data

								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span';
									}
									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									if (userRole == 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product"class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
									} else {

										if (row.quantity < 1) {
											str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										} else {

											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product"class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

										}
									}
									return str;
								}
							} ]
				});
	}

	// dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if ($alert.length) {

		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)

	}

	// -------
});

// Data table for admin
// ------------


var $adminProductsTable = $('#adminProductTable');

// execute the below code where we have this table

if ($adminProductsTable.length) {

	// console.log('Inside the table');

	var jsonUrl = window.contextRoot + '/json/data/admin/listproducts';

	$adminProductsTable
			.DataTable({

				lengthMenu : [ [ 3, 5, 10, -1 ],
					[ '3 Records', '5 Records', '10 Records', 'All' ] ],
			pageLength : 5,

				ajax : {
					url : jsonUrl,
					dataSrc : ''
				},

				columns : [
						{
							data : 'id'
						},
						{
							data : 'code',
							mRender : function(data, type, row) {
								return '<img src="' + window.contextRoot
										+ '/resources/images/' + data
										+ '.jpg" class="adminDataTableImg"/>';
							}
						},
						{
							data : 'name'
						},
						{
							data : 'brand'
						},

						{
							data : 'quantity',
							mRender : function(data, type, row) {

								if (data < 1) {
									return '<span style="color:red">Out of Stock!</span';
								}
								return data;
							}
						},
						{
							data : 'unitPrice',
							mRender : function(data, type, row) {
								return '&#036; ' + data

							}
						},
						{
							data : 'active',
							bSortable : false,
							mRender : function(data, type, row) {
								var str = '';
								str += '<label class="switch">';
								if (data) {
									str += '<input type="checkbox" checked="checked" value="'
											+ row.id + '"/>';
								} else {
									str += '<input type="checkbox" value="'
											+ row.id + '"/>';
								}

								str += '<div class="slider round"></div></label>';

								return str;
							}

						},
						{
							data : 'id',
							bSortable : false,
							mRender : function(data, type, row) {
								var str = '';
								str += '<a href = "' + window.contextRoot
										+ '/manage/' + data
										+ '/product" class="btn btn-warning">';
								str += '<span class="glyphicon glyphicon-pencil"></span></a>';
								return str;
							}
						}

				],

				initComplete : function() {
					var api = this.api();
					api.$('.switch input[type="checkbox"]')
							.on(
									'change',
									function() {
										var checkbox = $(this);
										var checked = checkbox.prop('checked');
										var dMsg = (checked) ? 'you want to activate the product?'
												: 'you want to deactivate the product?';
										var value = checkbox.prop('value');
										bootbox.confirm({
													message : dMsg,
													buttons : {
														confirm : {
															label : 'Yes',
															className : 'btn-success'
														},
														cancel : {
															label : 'No',
															className : 'btn-danger'
														}
													},
													callback : function(
															confirmed) {
														if (confirmed) {
															console.log(value);
															var avtivationUrl = window.contextRoot
																	+ '/manage/product/'
																	+ value
																	+ '/activation';
															$
																	.post(
																			avtivationUrl,
																			function(
																					data) {
																				bootbox
																						.alert({
																							size : 'medium',
																							title : 'information',
																							message : data
																						});
																			});
														} else {
															checkbox.prop(
																	'checked',
																	!checked)
														}

													}
												});

									});

				}
			});

}
	
	var $backOrderTable = $('#backOrderTable');

	// execute the below code where we have this table

	if ($backOrderTable.length) {

		// console.log('Inside the table');

		var jsonUrl = window.contextRoot + '/json/data/admin/backorder';

		$backOrderTable
				.DataTable({

					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'All' ] ],
					pageLength : 30,

					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},

					columns : [
							{
								data : 'userid'
							},
							{
								data : 'username'
							},
							{
								data : 'productname'
							},
							{
								data : 'quantityavailable'
							},

							{
								data : 'quantityneeded'
							},{
								data : 'userid',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href = "' + window.contextRoot
											+ '/manage/' + data
											+ '/backOrderEmail" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-envelope"></span></a>';
									
									
									return str;
									 
									
								}
							
							
							
							}

					],

					
				});
	
	
	// validation code for category

	var $categoryForm = $('#categoryForm');

	if ($categoryForm.length) {

		$categoryForm
				.validate({

					rules : {

						name : {
							required : true,
							minlength : 2
						},

						description : {
							required : true
						}

					},

					messages : {

						name : {
							required : 'please add the category name',
							minlength : 'The category name should not be less than 2 characters'

						},

						description : {
							required : 'please add a description to this category'

						}

					},

					errorElement : 'em',
					errorPlacement : function(error, element) {
						// adding class of help block
						error.addClass('help-block');
						// add error element after input element
						error.insertAfter(element);
					}
				});
	

	}

	/* validating the loginform */

	var $loginForm = $('#loginForm');

	if ($loginForm.length) {

		$loginForm.validate({
			rules : {
				username : {
					required : true,
					email : true

				},
				password : {
					required : true
				}
			},
			messages : {
				username : {
					required : 'Please enter your email!',
					email : 'Please enter a valid email address!'
				},
				password : {
					required : 'Please enter your password!'
				}
			},
			errorElement : "em",
			errorPlacement : function(error, element) {
				// Add the 'help-block' class to the error element
				error.addClass("help-block");

				// add the error label after the input element
				error.insertAfter(element);
			}
		});
	}

	// handling the click event of refresh cart button
}

$('button[name="refreshCart"]')
		.click(
				function() {

					// fetching the cart line id

					var cartLineId = $(this).attr('value');
					var countElement = $('#count_' + cartLineId);

					var originalCount = countElement.attr('value');
					var currentCount = countElement.val();
					
					
					// work only when the count has changed

					if (currentCount !== originalCount) {
						
						
						if (currentCount < 1 || currentCount > 3) {
							// reverting back to the original count
							// user has given value below 1 and above 3

							countElement.val(originalCount);
							bootbox
									.alert({
										size : 'medium',
										title : 'Error',
										message : 'Product count should be minimum 1 and maximum 3!'

									});

						} else {
							var updateUrl = window.contextRoot + '/cart/'
									+ cartLineId + '/update?count='
									+ currentCount;
							// foreward it to the controller
							window.location.href = updateUrl;

						}
					}

				});
