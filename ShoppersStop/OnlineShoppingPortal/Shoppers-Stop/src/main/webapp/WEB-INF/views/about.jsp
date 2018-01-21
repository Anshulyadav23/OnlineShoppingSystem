 <!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
	<h1 class="red-text text-center">Welcome to Shoppers Stop</h1>
   <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
           <div class="carousel-inner">
							
							<div class="item active">
							 <img class="slide-image" src="${images}/banner1.jpg"
									alt="">
							</div>
							 <div class="item">
								<img class="slide-image" src="${images}/banner2.jpg"
									alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${images}/banner3.jpg"
									alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${images}/banner4.jpg"
									alt="">
							</div>
						</div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
          
     <hr/>	
	<h1 class="red-text text-center">Discover More Stories Register With Us Now</h1>
	<hr/> 
          
</div>

</body>
</html>

