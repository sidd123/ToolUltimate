<!DOCTYPE html>
<html lang="en" ng-app="myApp">
	<head>
		<meta charset="utf-8">
		
		<title>{{title}}</title>
		<!-- Bootstrap -->
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<link href="resources/css/custom.css" rel="stylesheet">
		<link href="resources/css/toaster.css" rel="stylesheet">
		<style>
			a {
				color: orange;
			}
		</style>
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]><link href= "css/bootstrap-theme.css"rel= "stylesheet" >

		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- Libs -->
		<script src="resources/js/angular.min.js"></script>
		<script src="resources/js/angular-route.min.js"></script>
		<script src="resources/js/angular-animate.min.js" ></script>
		<script src="resources/js/toaster.js"></script>
		<script src="resources/js/jquery-2.2.2.js"></script>
		<script src="resources/js/jquery.dataTables.min.js"></script>
		<script src="resources/js/dataTables.bootstrap.min.js"></script>
		<script src="resources/js/app/app.js"></script>
		<script src="resources/js/app/data.js"></script>
		<script src="resources/js/app/directives.js"></script>
		<script src="resources/js/app/authCtrl.js"></script>
    </head>

	<body>
		<div>
			<div class="container" style="margin-top:20px;">
				<div data-ng-view="" id="ng-view" class="slide-animation"></div>
			</div>
		</div>
    </body>	
	
</html>

