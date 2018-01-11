controllers.controller('UsersCtrl', function ($scope, $spaceHttp, $rootScope, $location) {

    if (typeof $rootScope.globals === 'undefined' || typeof $rootScope.globals.currentUser === 'undefined' || !$rootScope.user.manager) {
        $location.path('login');
        return;
    }

	$rootScope.errorAlert = '';
	$rootScope.successAlert = '';
	$rootScope.warningAlert ='';
    var myDate = new Date();
    var date = new Date(myDate);
    date.setYear(myDate.getFullYear()-18);
    $scope.date = date.toISOString().substring(0,10);

    console.log('calling  /users');
    $spaceHttp.getAllUsers().then(function (response) {
        $scope.users = response.data;
    }, function (error) {
        console.error(error);
    })

    $scope.deleteUser = function (id) {
        $spaceHttp.deleteUser(id).then(function (response) {
            $scope.users = response.data;
	        $rootScope.errorAlert = '';
	        $rootScope.successAlert = 'User deleted!';
        }, function (error) {
            console.error(error);
	        $rootScope.errorAlert = 'User cannot be deleted!';
	        $rootScope.successAlert = '';
        });
    }

    $scope.editUser = function (id) {
        $spaceHttp.getUser(id).then(function (response) {
            $scope.editedUser = response.data;
            $scope.editedUser.birthDate = new Date(response.data.birthDate);
            $scope.edit = true;
        }, function (error) {
            console.error(error);
        })
    }


    $scope.submitEdit = function () {
        var data = angular.copy($scope.editedUser);
        data.birthDate = data.birthDate.toISOString().substring(0, 10);
        $spaceHttp.updateUser(data).then(function (res) {

            $spaceHttp.getAllUsers().then(function (response) {
                $scope.users = response.data;
                $scope.edit = false;
            }, function (error) {
                console.error(error);
            });
	        $rootScope.errorAlert = '';
	        $rootScope.successAlert = 'User edited!';
        }, function (error) {
            console.error(error);
	        $rootScope.errorAlert = 'User cannot be deleted!';
	        $rootScope.successAlert = '';
        })
    };


    $scope.createUser = function () {
        $scope.editedUser = {};
        $scope.create = true;
    }

    $scope.submitCreate = function () {
        var data = angular.copy($scope.editedUser);
        data.birthDate = data.birthDate.toISOString().substring(0, 10);
        $spaceHttp.createUser(data).then(function (res) {
            $spaceHttp.getAllUsers().then(function (response) {
                $scope.users = response.data;
                $scope.create = false;
            }, function (error) {
                console.error(error);
            });
	        $rootScope.errorAlert = '';
	        $rootScope.successAlert = 'User created';
        }, function (error) {
            console.error(error);
	        $rootScope.errorAlert = 'User cannot be created!';
	        $rootScope.successAlert = '';
        })
    };

    $scope.cancelEdit = function () {
        $scope.create = false;
        $scope.edit = false;
    }
});