var viewHeaderLevel = {
		account: 'View All Accounts',
		umbrellaProject: 'View All Umbrella Projects',
		project: 'View All Projects',
		subProject: 'View All Sub Projects'			
};

var status = {
		success: 'success',
		fail: 'fail'
};
var datatableConfig = {
	"name": "simple_datatable",
	"extraHeaders": {
		number: 0
	},
	"columns": [{
			"header": "Name",
			"property": "name",
			"order": true,
			"group": true,
			"type": "text",
			"showFilter": false,
			//"groupMethod":"collect",
			"edit": true,
			"hide": true,
			"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
		}, {
			"header": "Id",
			"property": "id",
			"order": true,
			"group": true,
			"type": "text",
			"showFilter": false,
			//"groupMethod":"collect",
			"edit": true,
			"hide": true,
			"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
		},

	],
	"edit": {
		"active": true,
		"columnMode": true
	},
	"filter": {
		"active": true,
		"highlight": true,
		"columnMode": true
	},
	"pagination": {
		"mode": 'remote',
		numberRecordsPerPageList: [{
			number: 10,
			clazz: ''
		}, {
			number: 25,
			clazz: ''
		}]
	},
	"order": {
		"mode": 'remote'
	},
	"remove": {
		"active": true,
		"mode": 'remote'
	},
	"save": {
		"active": true,
		"mode": 'remote'
	},
	"add": {
		"active": true,
		"showButton": true
	},
	"group": {
		"active": true,
	},
	"compact": true,
	"exportCSV": {
		active: true, //Active or not
		showButton: true, //Show the export button in the toolbar
		delimiter: "," //Set the delimiter
	},
	"hide": {
		"active": true,
		"byDefault": ["about", "name"]
	},
	"mouseevents": {
		"active": true,
		"overCallback": function(line, data) {
			//console.log("callback mouseover:", line, data);
		},
		"leaveCallback": function(line, data) {
			//console.log("callback mouseover:", line, data);
		},
		"clickCallback": function(line, data) {
			//console.log("callback select : "+data.name);
		}
	}
};

accountDataTablecolumns = [{
	"header": "Account Name",
	"property": "accountName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
}, {
	"header": "Account Id",
	"property": "accountId",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
},{
	"header": "Account Created By",
	"property": "accountCreatedBy",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
},{
	"header": "Account Created On",
	"property": "accountCreatedOn",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
},{
	"header": "Counts",
	"property": "accountId",
	"order": false,
	"group": false,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": false,
	"hide": true
}];

uProjectDataTablecolumns = [{
	"header": "Umbrella Project Name",
	"property": "umbrellaProjectId",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
}, {
	"header": "umbrellaProjectName",
	"property": "umbrellaProjectName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Account Name",
	"property": "accountName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>",
},{
	"header": "Umbrella Project CreatedBy",
	"property": "umbrellaProjectCreatedBy",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Umbrella Project Created On",
	"property": "umbrellaProjectCreatedOn",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Counts",
	"property": "accountId",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
}];

projectDataTablecolumns = [{
	"header": "Project Id",
	"property": "projectId",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Project Name",
	"property": "projectName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
}, {
	"header": "Account Name",
	"property": "accountName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Umbrella Project Name",
	"property": "umbrellaProjectName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Project Created By",
	"property": "projectCreatedBy",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Project Created On",
	"property": "projectCreatedOn",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Counts",
	"property": "accountId",
	"order": false,
	"group": false,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": false,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
}];

subProjectDataTablecolumns = [{
	"header": "Sub Project Id",
	"property": "subProjectId",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Sub Project Name",
	"property": "subProjectName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Account Name",
	"property": "accountName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Umbrella Project Name",
	"property": "umbrellaProjectName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Project Name",
	"property": "projectName",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Project Created By",
	"property": "projectCreatedBy",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Project Created On",
	"property": "projectCreatedOn",
	"order": true,
	"group": true,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": true,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
},{
	"header": "Counts",
	"property": "accountId",
	"order": false,
	"group": false,
	"type": "text",
	"showFilter": false,
	//"groupMethod":"collect",
	"edit": false,
	"hide": true,
	"render": "<a target='blank' ng-href='{{cellValue}}'>{{cellValue}}</a>"
}];