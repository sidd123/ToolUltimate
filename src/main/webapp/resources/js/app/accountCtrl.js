app.controller('accountCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data) {
    //initially set those objects to null to avoid undefined error
   $scope.viewAccount = function () {
        Data.post('viewAccount').then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$scope.accounts=results.details;
            }
            $('#accounts').DataTable();
        	$location.path('viewAccount');
        });
    };    
    
    $scope.addAccount = function () {
        Data.post('addAccount').then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$scope.accounts=results.details;
            }
            $('#accounts').DataTable();
        	$location.path('viewAccount');
        });
    };    
});