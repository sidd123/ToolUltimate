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
    
    $scope.saveEmployee = function (employee,role,level,origin) {

    	employee.userRoles = {};
    	
    	if(role != null){
    		var roleObj = role.roleId;
    		employee.userRoles.role = roleObj.role;
    		employee.userRoles.roleId = roleObj.roleId;
    	}
    	
    	if(level != null){
    		var levelObj = level.levelId;
    		employee.userRoles.level = levelObj.level;
    	}
    	
    	if(origin != null){
    		var originObj = origin.originId;
    		employee.userRoles.originIds = [];
        	
        	for(var count in originObj){
        		employee.userRoles.originIds.push(originObj[count].originId);
        	}
    	}
    	
    	Data.post('saveemployee',employee).then(function (results) {
            Data.toast(results);
            if (results.status == "success") {
            	 alert('successfully created');
            	$location.path('dashboard');
            }
        });
    	
    };
    
    
    
});
