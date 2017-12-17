controllers.controller('LoginCtrl', function ($scope, $spaceHttp, AuthenticationService) {
    console.log('calling  /login');
    $scope.credentials = {};

    $scope.login = function () {
        AuthenticationService.Login($scope.credentials.name, $scope.credentials.password).then(
            function (response) {
                console.log(response.data);
            },
            function (error) {
                console.log(error.data);
            }
        )
    };
});