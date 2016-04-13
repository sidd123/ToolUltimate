
<!DOCTYPE html>
<html lang="en" data-ng-app="myApp">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>{{title}} user logged in: {{loggedInUser}}</title>
        
        <!-- /Bootstrap & CSS libs -->
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/extra/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="resources/css/custom1.css" rel="stylesheet">
        <link href="resources/css/toaster1.css" rel="stylesheet">
        <link href="resources/css/style.css" rel="stylesheet">
        <link href="resources/css/ultimate-datatable-3.2.1.css" rel="stylesheet" >
        
		<style>
			a{ color: orange; }
		</style>
		
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
			<link href= "resources/css/bootstrap-theme.css" rel="stylesheet" >
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
    </head>

	<body class="scrolled" style="">
		<div data-ng-view="" id="ng-view" class="slide-animation"></div>
	</body>
    
	<!-- Libs -->
	<script src="resources/js/angular.min.js"></script>
	<script src="resources/js/angular-route.min.js"></script>
	<script src="resources/js/angular-animate.min.js" ></script>
	<script src="resources/js/toaster.js"></script>
	<script src="resources/js/nz-toggle.min.js"></script>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/momentjs.js"></script>
 	<script src="resources/js/fileSaver.min.js"></script>
 	<script src="resources/js/ultimate-datatable-3.2.2-SNAPSHOT.js"></script>
	
	
	<!-- /custom project js files -->
	<script src="resources/js/app/app.js"></script>
	<script src="resources/js/app/data.js"></script>
	<script src="resources/js/app/directives.js"></script>
	<script src="resources/js/app/authCtrl.js"></script>
	<script src="resources/js/app/accountCtrl.js"></script>
	<script src="resources/js/app/employeeCtrl.js"></script>
</html>