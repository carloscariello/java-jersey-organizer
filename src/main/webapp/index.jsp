<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Organizer</title>

<link rel="shortcut icon" href="img/favicon.ico">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/starter.css">
</head>

<body ng-app="OrganizerApp">

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Organizer</a>
		         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>				
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class=""><a href="#">Start</a></li>
					<li class=""><a href="#/tasks">Tasks</a></li>
					<li class=""><a href="#/login">Login</a></li>
					<li class=""><a href="#/logout">Logout</a></li>
					<li class=""><a href="#/register">Register</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="starter-template">
			<div class="starter-template">
				<section ng-view></section>
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/angular.min.js"></script>
	<script src="js/angular-route.min.js"></script>
	<script src="js/organizer.js"></script>

</body>
</html>
