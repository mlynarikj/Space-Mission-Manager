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

    $scope.createMission = function () {
        $scope.newMission =  {
            'name': '',
            'destination': '',
            'active': true,
            'astronauts': [],
            'spacecrafts': [],
            'eta': '',
            'missionDescription': '',
            'endDate': ''
        };
        $scope.create = true;
    };

    $scope.submitCreate = function () {
        var data = angular.copy($scope.newMission);
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
});