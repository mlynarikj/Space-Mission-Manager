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
            'active': true,
            'astronauts': [],
            'spacecrafts': []
        };
        $scope.create = true;
        $spaceHttp.loadAstronauts().then(function (value) {
            $scope.selectedAstronauts = value.data
        });
        $spaceHttp.loadSpacecrafts().then(function (value) {
            $scope.selectedSpacecrafts = value.data
        })
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
            $rootScope.successAlert = 'A new mission "' + data.name +'" was created'
        }, function (error) {
            console.error(error);
            $rootScope.errorAlert = 'Cannot create mission!';
        })
    };

    $scope.deleteMission = function (id) {
        $spaceHttp.deleteMission(id).then(function (response) {
            $rootScope.successAlert = 'Mission ' + id + ' was deleted';
            $rootScope.errorAlert = '';
            $scope.missions = response.data;
        }, function error(response) {
            //display error
            console.log(response);
            $rootScope.errorAlert = 'Cannot delete mission!';
            $rootScope.successAlert = '';
            $route.reload();
            })
    };

    $scope.editMission = function (id) {
        $spaceHttp.getMission(id).then(function (response) {
            $scope.editedMission = response.data;
            $scope.editedMission.eta = $scope.editedMission.eta === null || $scope.editedMission.eta === undefined
                ? null : new Date($scope.editedMission.eta.substring(0, 16));
            $scope.edit = true;
        }, function (error) {
            console.error(error);
        })
    };

    $scope.cancelEdit = function () {
        $scope.create = false;
        $scope.edit = false;
    };

    $scope.submitEdit = function () {
        var data = angular.copy($scope.editedMission);
        var selectedAstronauts = $('#astronauts').val();
        var selectedSpacecrafts = $('#spacecrafts').val();
        $scope.astronauts.forEach(function(astronaut){
            selectedAstronauts.forEach(function(index){
                if(index === astronaut.id){
                    console.log(astronaut);
                    data.astronauts.push(astronaut);
                }
            });
        });
        $scope.spacecrafts.forEach(function(spacecraft){
            selectedSpacecrafts.forEach(function(index){
                if(index === spacecraft.id){
                    console.log(spacecraft);
                    data.spacecrafts.push(spacecraft);
                }
            });
        });
        if (!(data.eta === null || data.eta === undefined)){
            data.eta.setHours(data.eta.getHours()+1);
            data.eta = data.eta.toISOString();
        }
        $spaceHttp.updateMission(data).then(function (res) {
            $spaceHttp.loadMissions().then(function (response) {
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