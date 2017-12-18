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

    service.deleteUser = function (id) {
        return $http.delete(API_URL+USERS_PATH+'/'+id);
    };


    service.getUser = function (id) {
        return $http.get(API_URL+USERS_PATH+'/'+id);
    };

    service.updateUser = function (data){
        return $http.put(API_URL+USERS_PATH, data);
    };


    service.createUser = function (data){
        return $http.post(API_URL+USERS_PATH, data);
    };

    service.acceptMission = function (id) {
        return $http.get(API_URL+USERS_PATH+'/'+id+'/acceptMission');
    };

    service.declineMission = function (id, message) {
        return $http.post(API_URL+USERS_PATH+'/'+id+'/rejectMission', message);
    };

    return service;
}]);