app.controller('employeeCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data) {
    //initially set those objects to null to avoid undefined error
   $scope.addEmployee = function () {
        Data.get('addEmployee').then(function (results) {
          
        	 Data.toast(results);
             if (results.status == "success") {
             	$rootScope.rolesdropdown = results.roles;
             }
            $location.path('addEmployee');
        });
    };    
    
});