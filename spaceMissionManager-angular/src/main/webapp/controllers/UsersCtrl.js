controllers.controller('UsersCtrl', function ($scope, $spaceHttp) {
    console.log('calling  /users');
    $spaceHttp.getAllUsers().then(function (response) {
      $scope.users = response.data;
    },function () {
        
    })
  });