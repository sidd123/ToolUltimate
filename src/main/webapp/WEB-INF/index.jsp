<!DOCTYPE html>
<html lang="en" ng-app="myApp">
	<head>
		<meta charset="utf-8">
		
		<title>{{title}}</title>
		<!-- Bootstrap -->
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<link href="resources/css/custom.css" rel="stylesheet">
		<link href="resources/css/toaster.css" rel="stylesheet">
			<!-- Google Webfonts -->
		<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		
		<!-- Animate.css -->
		<link rel="stylesheet" href="resources/css/animate.css">
		<!-- Icomoon Icon Fonts-->
		<link rel="stylesheet" href="resources/css/icomoon.css">
		<!-- Simple Line Icons-->
		<link rel="stylesheet" href="resources/css/simple-line-icons.css">
		<!-- Magnific Popup -->
		<link rel="stylesheet" href="resources/css/magnific-popup.css">
		<!-- Owl Carousel -->
		<link rel="stylesheet" href="resources/css/owl.carousel.min.css">
		<link rel="stylesheet" href="resources/css/owl.theme.default.min.css">
		<!-- Salvattore -->
		<link rel="stylesheet" href="resources/css/salvattore.css">
		<!-- Theme Style -->
		<link rel="stylesheet" href="resources/css/style.css">
		<!-- Modernizr JS -->
		<script src="resources/js/modernizr-2.6.2.min.js"></script>
		<!-- FOR IE9 below -->
		<!--[if lt IE 9]>
		<script src="resources/js/respond.min.js"></script>
		<![endif]-->
			
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
		<script src="resources/js/app/app.js"></script>
		<script src="resources/js/app/data.js"></script>
		<script src="resources/js/app/directives.js"></script>
		<script src="resources/js/app/authCtrl.js"></script>
		<!-- jQuery -->
		<script src="resources/js/jquery.min.js"></script>
		<!-- jQuery Easing -->
		<script src="resources/js/jquery.easing.1.3.js"></script>
		<!-- Bootstrap -->
		<script src="resources/js/bootstrap.min.js"></script>
		<!-- Waypoints -->
		<script src="resources/js/jquery.waypoints.min.js"></script>
		<!-- Magnific Popup -->
		<script src="resources/js/jquery.magnific-popup.min.js"></script>
		<!-- Owl Carousel -->
		<script src="resources/js/owl.carousel.min.js"></script>
		<!-- toCount -->
		<script src="resources/js/jquery.countTo.js"></script>
		<!-- Main JS -->
		<script src="resources/js/main.js"></script>
		<script src="resources/js/jquery.dataTables.min.js"></script>
		<script src="resources/js/dataTables.bootstrap.min.js"></script>
    </head>

	<body class="scrolled">
		<div data-ng-view="" id="ng-view" class="slide-animation"></div>
	</body>	
	<script>
$(document).ready(function() {
    $('#accounts').DataTable();
} );
</script>
</html>

