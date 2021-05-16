'use strict';

App.factory('CommonService', [
		'$http',
		'$q',
		'restUrl',
		'$stateParams',
		function($http, $q, restUrl, $stateParams) {
			var REST_SERVICE_URI = restUrl+'api';
			var count = {};
			var resultPerPage = {};
			var entityList = [];
			var homeDetails = {};
			var entityData = {};
			var loginRequired = false;
			var factory = {
			};
			return factory;
			
			

		} ]);
