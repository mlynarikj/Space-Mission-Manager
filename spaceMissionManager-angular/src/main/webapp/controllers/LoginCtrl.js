controllers.controller('LoginCtrl', function ($scope, $spaceHttp, AuthenticationService) {


    $scope.credentials = {};

    $scope.login = function () {
        AuthenticationService.Login($scope.credentials.name, $scope.credentials.password).then(
            function (response) {
                //TODO: toto je hack, musi se to opravit, aby se tam dal mail
                AuthenticationService.SetCredentials('ADMIN', 'ADMIN');
                var user = response.data;
                user.rawPassowrd = $scope.credentials.password;
                localStorage.setItem('user', JSON.stringify(response.data));
                $scope.badCredentials = false;
            },
            function (error) {
                $scope.badCredentials = true;
            }
        )
    };
});