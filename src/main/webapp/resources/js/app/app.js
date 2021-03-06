var app = angular.module('myApp', [ 'ngRoute', 'ngAnimate', 'toaster', 'nzToggle', 'ultimateDataTableServices' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/login.html',
		controller : 'authCtrl'
	}).when('/logout', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/login.html',
		controller : 'authCtrl'
	}).when('/signup', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/signup.html',
		controller : 'authCtrl'
	}).when('/dashboard', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/dashboard.html',
		controller : 'authCtrl'
	}).when('/', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/login.html',
		controller : 'authCtrl',
		role : '0'
	}).when('/viewData', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/viewData.html',
		controller : 'accountCtrl'
	}).when('/addAccount', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/addEditAccount.html',
		controller : 'accountCtrl'
	}).when('/addEmployee', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/employeeform.html',
		controller : 'employeeCtrl'
	});
}]).run(['$rootScope', '$location', 'Data',
				function($rootScope, $location, Data) {
					$rootScope.$on("$routeChangeStart", 
							function(event, next, current) {
								Data.get('sessionexists').then(function (results) {
					                if (results == "true") {
					                	$rootScope.loggedInUser = true;
					                	 var nextUrl = next.$$route.originalPath;
					                	 if(nextUrl == '/addEmployee') {
					                		 Data.get('addEmployee').then(function (results) {
					                	          
					                        	 Data.toast(results);
					                             if (results.status == "success") {
					                             	$rootScope.rolesdropdown = results.roles;
					                             }
					                            $location.path('addEmployee');
					                        });
					                	 }
					                	 
					                } else {
					                	$rootScope.loggedInUser = false;
					                	$location.path("/login");
					                }
					            });
								
							});
}]).run(['$rootScope', 
  		function($rootScope) {
				$rootScope.$on('$routeChangeSuccess', 
						function(event, current, previous) {
							//$rootScope.title = current.$$route.title;
						});
}]);
