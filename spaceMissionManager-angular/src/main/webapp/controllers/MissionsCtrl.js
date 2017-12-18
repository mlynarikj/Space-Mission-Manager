controllers.controller('MissionsCtrl', function ($scope, $spaceHttp, $rootScope, $location) {

    if (typeof $rootScope.globals === 'undefined' || typeof $rootScope.globals.currentUser === 'undefined') {
        $location.path('login');
        return;
    }

    console.log('calling  /missions');
    $spaceHttp.loadMissions().then(function (response) {
        console.log(response);
        $scope.missions = response.data;
    });
    $spaceHttp.loadAstronauts().then(function (response) {
        console.log(response);
        $scope.astronauts = response.data;
    });
    $spaceHttp.loadSpacecrafts().then(function (response) {
        console.log(response);
        $scope.spacecrafts = response.data;
    });

    $scope.createNewMission = function () {
        $scope.editedMission =  {
            'active': true
        };
        $scope.create = true;
    };

    $scope.submitCreate = function () {
        var data = angular.copy($scope.editedMission);
        $spaceHttp.createMission(data).then(function (res) {
            $spaceHttp.loadMissions().then(function (response) {
                $scope.missions = response.data;
                $scope.create = false;
            }, function (error) {
                console.error(error);
            });
        }, function (error) {
            console.error(error);
        })
    };

    $scope.deleteMission = function (id) {
        $spaceHttp.deleteMission(id).then(function (response) {
            $scope.missions = response.data;
        }, function (error) {
            console.error(error);
        });
    };

    $scope.editMission = function (id) {
        $spaceHttp.getMission(id).then(function (response) {
            $scope.editedMission = response.data;
            $scope.editedMission.eta = new Date(response.data.eta);
            $scope.edit = true;
        }, function (error) {
            console.error(error);
        })
    };

    $scope.cancelEdit = function () {
        $scope.create = false;
        $scope.edit = false;
    }

    $scope.submitEdit = function () {
        var data = angular.copy($scope.editedMission);
        data.eta = data.eta.toISOString().substring(0, 10);
        $spaceHttp.updateMission(data).then(function (res) {
            $spaceHttp.getAllMissions().then(function (response) {
                $scope.missions = response.data;
                $scope.edit = false;
            }, function (error) {
                console.error(error);
            });
        }, function (error) {
            console.error(error);
        })
    };
});