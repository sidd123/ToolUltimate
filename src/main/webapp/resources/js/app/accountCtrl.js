app.controller('accountCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data) {
    //initially set those objects to null to avoid undefined error
   $scope.viewAccount = function () {
	  
	   var searchAttribute = {
				quickSearchText: 'DR', 
				pageIndex: 1,
				sortByCol : 'accountName',
				sortByDir : 'asc',
				itemPerPage : 25
		};
        Data.post('viewAccount', searchAttribute).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$rootScope.accounts=results.details;
            }
            $('.filter').createFilter('init');
            $('.filter').createFilter({
            	buildFilter: true,
                filterArr: [{
					filterHeader: 'Created By',
					jsonSource: $.parseJSON('[{"key":4000004807,"id":"4000004807","desc":"Clark County School District 1","levelName":"District"}]'),
					isRadio: false,
					manadatory: true,
					showAllNone:true
				}]
			});
            
            $location.path('viewAccount');
        });
    };    
    
    $scope.saveAccount = function (account) {
        Data.post('saveAccount', account).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$rootScope.accounts=results.details;
            }
            $('#accounts').DataTable();
        	$location.path('viewAccount');
        });
    };  
    
    $scope.editAccount = function (account) {
        Data.post('editAccount', account).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$rootScope.account=results.details;
            }
            $('#AccountId').disable();
            $location.path('addAccount');
        });
    };
    
    $scope.viewSubProject = function(){
    	Data.post('viewSubProject').then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$rootScope.subProjectTable=results.details;
            }
            $('#subProjectTable').DataTable();
        	$location.path('viewSubProject');
        });
    };
});