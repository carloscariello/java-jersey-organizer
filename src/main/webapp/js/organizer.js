/**
 * Organizer Library
 */

var OrganizerApp = angular.module('OrganizerApp', [ 'ngRoute' ]);

/* Configure routes - redirect everything else to '/' */
function organizerAppConfig($routeProvider) {
	$routeProvider.when('/', {
		controller : 'StartController',
		templateUrl : 'views/start.html'
	}).when('/tasks', {
		controller : 'TaskController',
		templateUrl : 'views/tasks.html'
	}).when('/login', {
		controller : 'LoginController',
		templateUrl : 'views/login.html'
	}).when('/logout', {
		controller: 'LogoutController',
		templateUrl: 'views/logout.html'
	}).when('/register', {
		controller : 'LoginController',
		templateUrl : 'views/register.html'
	}).otherwise({
		redirectTo : '/'
	});
};
OrganizerApp.config(organizerAppConfig);


/* A very simple entity manager */
OrganizerApp.factory('UserAuth', ['$http', function ($http) {

	if (typeof (localStorage) == "undefined") {
		alert("no storage!");
	}
	
  UserAuth = new function(){
		this.setAuthHeaderHash = function(value) {
			localStorage.setItem('authHeader', value);
			this.setBasicAuthAsHttpHeader();
		};
		
		this.getAuthHeaderHash = function() {
			return localStorage.getItem('authHeader');
		};
		
		this.isUserLoggedIn = function() {
			var auth = this.getAuthHeaderHash();
			return auth !== undefined && auth != null && auth != '';
		};
		
		this.getBasicAuthHeader = function() {
			return "Basic " + (this.isUserLoggedIn() ? this.getAuthHeaderHash() : '');
		};
		
		this.setBasicAuthAsHttpHeader = function() {
			$http.defaults.headers.common.Authorization = this.getBasicAuthHeader();
		};
  };
  
  return UserAuth;
}]);

/**
 * Set the Basic Authentification Header for all requests
 */
OrganizerApp.run(function(UserAuth){
	UserAuth.setBasicAuthAsHttpHeader();
});

/**
 * Controller for the start page
 */
OrganizerApp.controller('StartController', function($scope) {
});

/**
 * Controller for the task page
 */
OrganizerApp.controller('TaskController', function($scope, $http, UserAuth) {
	$scope.name = '';
	$scope.description = '';
	$scope.tasks = [];
	
	$scope.getTasks = function() {
		$http.get('/organizer/api/tasks').success(function(data, status, headers, config){
			$scope.tasks = data;
		}).error(function(data, status, headers, config){
			console.log(status);
		});
	};
	
	if(UserAuth.isUserLoggedIn()) {
		$scope.getTasks();
	}
	
	$scope.addTask = function() {
		var parameters = {
			name : $scope.name,
			description : $scope.description,
			content: $scope.content
		};

		$http.post('/organizer/api/tasks/create', parameters).success(function(data, status, headers, config){
			console.log(data);
			$scope.tasks.push(data);
		}).error(function(data, status, headers, config) {
			console.log(data);
		});	
	};
	
	$scope.deleteTask= function(taskId) {
		var parameters = {
			id: taskId	
		};
		$http.post('/organizer/api/tasks/delete', parameters).success(function(data, status, headers, config){
			console.log(data);
		}).error(function(data, status, headers, config){
			console.log(data);
		});
	};
	
});

/**
 * Controller for the login page
 */
OrganizerApp.controller('LoginController', function($scope, $http, UserAuth) {
	$scope.username = '';
	$scope.email = '';
	$scope.password = '';
	
	$scope.register = function() {
		var parameters = {
			username: $scope.username,
			email: $scope.email,
			password: $scope.password
		};

		$http.post("/organizer/api/auth/signup", parameters).
		success(function(data, status, headers, config) {
			console.log(data);
			if(data.status == "SUCCESSFUL") {
				UserAuth.setAuthHeaderHash(data.messages.authentification);
			}
		}).error(function(data, status, headers, config){
			console.log(data);
		});
	};
	
	$scope.login = function() {
		var parameters = {
			username : $scope.username,
			password : $scope.password
		};

		$http.post('/organizer/api/auth/signin', parameters).
	    success(function(data, status, headers, config) {
			console.log(data);
			if (data.status == "SUCCESSFUL") {
				UserAuth.setAuthHeaderHash(data.messages.authentification);
			}
	    }).
	    error(function(data, status, headers, config) {
	    	console.log('error: ', data, headers);
	    });		
	};
});

/**
 * Controller for the logout page
 */
OrganizerApp.controller('LogoutController', function($scope, UserAuth){
	UserAuth.setAuthHeaderHash('');
});
