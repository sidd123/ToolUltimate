var app = angular.module('myApp', [ 'ngRoute', 'ngAnimate', 'toaster' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/login.html',
		controller : 'authCtrl'
	}).when('/logout', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/login.html',
		controller : 'logoutCtrl'
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
	}).when('/viewAccount', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/viewAccount.html',
		controller : 'accountCtrl'
	}).when('/addAccount', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/addEditAccount.html',
		controller : 'accountCtrl'
	}).when('/employeeform', {
		title : 'Tool Ultimate',
		templateUrl : 'resources/views/employeeform.html',
		controller : 'authCtrl'
	});
} ]).run(
		[
				'$rootScope',
				'$location',
				'Data',
				function($rootScope, $location, Data) {
					$rootScope.$on("$routeChangeStart", function(event, next,
							current) {
						$rootScope.authenticated = false;
						/*
						 * Data.get('login').then(function (results) { if
						 * (results.name) { $rootScope.authenticated = true;
						 * $rootScope.uid = results.uid; $rootScope.name =
						 * results.name; $rootScope.email = results.email; }
						 * else { var nextUrl = next.$$route.originalPath; if
						 * (nextUrl == '/signup' || nextUrl == '/login') {
						 *  } else { $location.path("/login"); } } });
						 */
					});
				} ]).run([ '$rootScope', function($rootScope) {
	$rootScope.$on('$routeChangeSuccess', function(event, current, previous) {
		$rootScope.title = current.$$route.title;
	});
} ]);