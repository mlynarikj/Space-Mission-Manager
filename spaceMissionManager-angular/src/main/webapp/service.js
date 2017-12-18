spaceMissionApp.factory('$spaceHttp', ['$http', function ($http) {
    var service = {};

    const API_URL = "http://localhost:8080/pa165/rest/";
    const LOGIN_PATH = "login";
    const USERS_PATH = "users";


    service.login = function (name, password) {
        return $http.post(API_URL+LOGIN_PATH, {name: name, password: password});
    };

    service.getAllUsers = function () {
        return $http.get(API_URL+USERS_PATH);
    };

    return service;
}]);