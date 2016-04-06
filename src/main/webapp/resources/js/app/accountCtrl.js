app.controller('accountCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data) {
    //initially set those objects to null to avoid undefined error
   $scope.viewAccount = function () {
        Data.post('viewAccount').then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	 if (results.name) {
                     $rootScope.authenticated = true;
                     $rootScope.uid = results.uid;
                     $rootScope.name = results.name;
                     $rootScope.email = results.email;
                 } 
            	$location.path('viewAccount');
            }
        });
    };    
});