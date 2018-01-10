controllers.controller('ComponentsCtrl', function ($scope, $spaceHttp, $rootScope, $location, $route) {
	console.log('calling  /components');
	console.log($rootScope);
	console.log(localStorage.getItem('user'));
	console.log($rootScope.user);
	if (typeof $rootScope.globals === 'undefined' || typeof $rootScope.globals.currentUser === 'undefined') {
		$location.path('login');
		return;
	}
	var storedUserInfo = localStorage.getItem('user');
	if(storedUserInfo) {
		var user = JSON.parse(storedUserInfo);
		$scope.manager = user.manager;
	}

	$scope.date = new Date().toISOString().substring(0,16);
	console.log($scope.date);

	$spaceHttp.loadComponents().then(function (response) {
		console.log(response);
		$scope.craftComponents = response.data;
	});

	$scope.delete = function (id) {
		$spaceHttp.deleteComponent(id).then(function success(response) {
			//display confirmation alert
			$rootScope.successAlert = 'Component ' + id + ' was deleted';
			$rootScope.errorAlert = '';
			//$location.path("/components");
			$route.reload();
		}, function error(response) {
			//display error
			console.log(response);
			$rootScope.errorAlert = 'Cannot delete component!';
			$rootScope.successAlert = '';
			$route.reload();

		})
	};

	$scope.editCC = function (id) {
		$spaceHttp.getComponent(id).then(function success(resp) {
			var tmp = resp.data;
			$scope.cc = {
				'id': id,
				'readyToUse': tmp.readyToUse,
				'name': tmp.name,
				'readyDate': tmp.readyDate === null || tmp.readyDate === undefined ? null : new Date(tmp.readyDate.substring(0, 16))
			};
			$scope.edit = true;
		}, function error(resp) {
			console.log(resp);
		});
	};

	$scope.createCC = function () {
		$scope.create = true;
		$scope.cc = {};
	};

	$scope.save = function () {
		var data = angular.copy($scope.cc);
		if (data.readyToUse){
			data.readyDate = null;
		}
		if (!(data.readyDate === null || data.readyDate === undefined)){
			data.readyDate.setHours(data.readyDate.getHours()+1);
			data.readyDate = data.readyDate.toISOString();
		}
		if ($scope.create) {
			$spaceHttp.createComponent(data).then(function (res) {

				$spaceHttp.loadComponents().then(function (response) {
					$scope.craftComponents = response.data;
					$scope.create = false;
				}, function (error) {
					console.error(error);
				});
			}, function (error) {
				console.error(error);
				$rootScope.errorAlert = 'Cannot create component!';

			})
		} else {
			$spaceHttp.updateComponent(data).then(function (res) {

				$spaceHttp.loadComponents().then(function (response) {
					$scope.craftComponents = response.data;
					$scope.edit = false;
				}, function (error) {
					console.error(error);
				});
			}, function (error) {
				console.error(error);
				$rootScope.errorAlert = 'Cannot update component!';

			})

		}
	};

	$scope.cancel = function () {
		$scope.create = false;
		$scope.edit = false;
	}


});
/*

controllers.controller('NewComponentCtrl', function ($scope, $spaceHttp, $rootScope, $location) {
	console.log('calling  /newComponent');
	if (typeof $rootScope.globals === 'undefined' || typeof $rootScope.globals.currentUser === 'undefined') {
		$location.path('login');
		return;
	}

	$scope.cc = {
		'name': '',
		'readyToUse': false,
		'readyDate': null
	};

	$scope.create = function (cc) {
		console.log("TEST");
		$spaceHttp.createComponent(cc).then(function success(response) {
			console.log('created component');
			var createdCC = response.data;
			//display confirmation alert
			$rootScope.successAlert = 'A new component "' + createdCC.name + '" was created';
			$location.path("/components");
		}, function error(response) {
			//display error
			$scope.errorAlert = 'Cannot create component!';
		});
	}
});*/
