app.controller('accountCtrl', function ($scope, $rootScope, $routeParams, $location, $http, Data) {
    //initially set those objects to null to avoid undefined error
	var searchAttribute = {
			quickSearchText: 'DR', 
			pageIndex: 1,
			sortByCol : 'accountName',
			sortByDir : 'asc',
			itemPerPage : 25
	};
    $scope.viewAccount = function () {
	  
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
            		var datatableConfig = {
        				"name":"simple_datatable",
        				"extraHeaders":{number:0},
        				"columns":[
        					{
        						"header":"Name",
        						"property":"accountName",
        						"order":true,
        						"group":true,
        						"type":"text",
        						"showFilter":false,
        						//"groupMethod":"collect",
        						"edit":true,
        						"hide":true,
        						"render":"<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
        					},
        					{
        						"header":"Account Id",
        						"property":"accountId",
        						"order":true,
        						"group":true,
        						"type":"text",
        						"showFilter":false,
        						//"groupMethod":"collect",
        						"edit":true,
        						"hide":true,
        						"render":"<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
        					},
        					
        				],
        				"edit":{
        					"active":true,
        					"columnMode":true
        				},
        				"filter":{
        					"active":true,
        					"highlight":true,
        					"columnMode":true
        				},
        				"pagination":{
        					"mode":'local',
        					numberRecordsPerPageList:[{
                                number: 10,
                                clazz: ''
                            }, {
                                number: 25,
                                clazz: ''
                            }]
        				},
        				"order":{
        					"mode":'local'
        				},
        				"remove":{
        					"active":true,
        					"mode":'local'
        				},
        				"save":{
        					"active":true,
        					"mode":'local'
        				},
        				"add":{
        					"active":true,
        					"showButton":true
        				},
        				"group":{
        					"active":true,
        				},
        				"compact":true,
        				"exportCSV":{
        					active:true,//Active or not
        					showButton:true,//Show the export button in the toolbar
        					delimiter:";"//Set the delimiter
        				},
        				"hide":{
        					"active":true,
        					"byDefault":["about","name"]
        				},
                                        "mouseevents":{
                                            "active": true,
                                            "overCallback": function(line, data){
                                                //console.log("callback mouseover:", line, data);
                                            },
                                            "leaveCallback": function(line, data){
                                                //console.log("callback mouseover:", line, data);
                                            },
                                            "clickCallback": function(line,data){
                                                //console.log("callback select : "+data.name);
                                            }
                                        }
        			};
        			
        			var datatableData = [];
        			datatableData = results.details;
        			
        			
        			$rootScope.datatable = datatable(datatableConfig);
        			$rootScope.datatable.setData(datatableData);
            }
            $('#subProjectTable').DataTable();
        	$location.path('viewSubProject');
        });
    };
});