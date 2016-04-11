app.controller('authCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data) {
    //initially set those objects to null to avoid undefined error
    $scope.login = {};
    $scope.signup = {};
    $scope.doLogin = function (user) {
        Data.post('login',user).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$rootScope.loggedInUser=results.details;
            	$location.path('dashboard');
            }
        });
    };
    
    $scope.saveEmployee = function (employee) {
        Data.post('saveemployee',employee).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	 alert('successfully created');
            	$location.path('dashboard');
            }
        });
    };
    
    $scope.signup = {email:'',password:'',name:'',phone:'',address:''};
    $scope.signUp = function (customer) {
        Data.post('signUp', {
            customer: customer
        }).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
                $location.path('dashboard');
            }
        });
    };
    $scope.logout = function () {
        Data.get('logout').then(function (results) {
            $location.path('login');
        });
    };
});
