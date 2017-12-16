controllers.controller('LoginCtrl', function ($scope, $spaceHttp) {
    console.log('calling  /login');
    $scope.credentials = {};
    $scope.login = function () {
        $spaceHttp.login($scope.credentials.name, $scope.credentials.password);
    };
});