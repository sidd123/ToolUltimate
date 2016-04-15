app.controller('accountCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data, datatable) {
    //initially set those objects to null to avoid undefined error
	$scope.viewAccount = function () {  
		var searchAttribute = {
				quickSearchText: 'DR', 
				pageIndex: 1,
				sortByCol : 'accountName',
				sortByDir : 'asc',
				itemPerPage : 25,
				requestPath: 'viewAccount',
				dataTableColumn: accountDataTablecolumns,
				dataTableHeaderLevel: 'View All Accounts'
		};
	    
		$scope.viewDataFromServer(searchAttribute);
    };
    
    $scope.viewDataFromServer = function (searchAttribute) {    	
 	   Data.post(searchAttribute.requestPath, searchAttribute).then(function (results) {
             Data.toast(results);
             var datatableData = [];
  			 if (results.status == "success") {
             	datatableData = results.details;     			
             }
  			 datatableConfig.columns = searchAttribute.dataTableColumn;
 			 $rootScope.datatable = datatable(datatableConfig);
 			 $rootScope.datatable.setData(datatableData);
 			 $rootScope.dataTableHeaderLevel = searchAttribute.dataTableHeaderLevel;
             $location.path('viewData');
         });
     }; 
    
    $scope.saveAccount = function (account) {
        Data.post('saveAccount', account).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	$rootScope.accounts=results.details;
            }
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
    	
    	var searchAttribute = {
				quickSearchText: 'DR', 
				pageIndex: 1,
				sortByCol : 'accountName',
				sortByDir : 'asc',
				itemPerPage : 25,
				requestPath: 'viewSubProject',
				dataTableColumn: subProjectDataTablecolumns
		};
	    
		$scope.viewDataFromServer(searchAttribute);
		
    };
    
    $scope.viewUProject = function () {  
		var searchAttribute = {
				quickSearchText: 'DR', 
				pageIndex: 1,
				sortByCol : 'accountName',
				sortByDir : 'asc',
				itemPerPage : 25,
				requestPath: 'viewUmbrellaProject',
				dataTableColumn: uProjectDataTablecolumns
		};
	    
		$scope.viewDataFromServer(searchAttribute);
    };
    
    $scope.viewProject = function () {  
		var searchAttribute = {
				quickSearchText: 'DR', 
				pageIndex: 1,
				sortByCol : 'accountName',
				sortByDir : 'asc',
				itemPerPage : 25,
				requestPath: 'viewProject',
				dataTableColumn: projectDataTablecolumns
		};
	    
		$scope.viewDataFromServer(searchAttribute);
    };
});