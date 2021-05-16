'use strict';

App
		.controller(
				'CommonController',
				[
						'$http',
						'restUrl',
						'$scope',
						'$state',
						'$stateParams',
						'CommonService',
						'$sce',
						'$q',
						'$window',
						function($http, restUrl, $scope, $state, $stateParams,
								CommonService, $sce, $q, $window) {
							var self = this;
							self.entity = {};
							self.search = {};
							self.loader = [];
							self.fileLoader = '';
							self.entityList = [];
							self.counter = -1;
							self.counter1 = -1;
							self.counter2 = 0;
							self.temp = {};
							self.lastSearch = {};
							self.temp1 = [];
							self.temp2 = {};
							self.company = {};
							self.currentPage = 1;
							self.count = 0;
							self.fileName = new FormData();
							self.contentImage = [];
							self.content = [];
							self.searchTerm = "";
							self.publicKey = "6Ld8XugUAAAAALsjUNeDDt0KHeG4HrMKPfL72sFt";
							self.counter = -1;
							self.count = 0;
							self.reload = function() {
								$state.reload();
							}

						} ]);
App.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);
