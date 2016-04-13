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
    
    $scope.viewLevels = function (role) {
        Data.post('viewLevels',role).then(function (results) {
          
        	 Data.toast(results);
             if (results.status == "success") {
             	$rootScope.levelsdropdown = results.levels;
             }
            $location.path('addEmployee');
        });
    }; 
    
    
    $scope.viewOrigins = function (level) {
        Data.post('viewOrigins',level).then(function (results) {          
        	 Data.toast(results);
             if (results.status == "success") {

             	$rootScope.origins = results.origins;
             	$rootScope.originlabel = results.originlabelname;
             	$rootScope.showorigin = true;
             }
            $location.path('addEmployee');
        });
    };
    
    
    
});
