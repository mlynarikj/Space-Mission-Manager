spaceMissionApp.factory('$spaceHttp', ['$http', function ($http) {
    var service = {};

    const API_URL = "http://localhost:8080/pa165/rest/";
    const LOGIN_PATH = "login";
    const USERS_PATH = "users";
    const MISSIONS_PATH = "missions";
    const ASTRONAUTS_PATH = "astronauts";
    const SPACECRAFTS_PATH = "spacecrafts";


    service.login = function (name, password) {
        return $http.post(API_URL+LOGIN_PATH, {name: name, password: password});
    };

    //Users
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

    service.loadAstronauts = function () {
        return $http.get(API_URL+ASTRONAUTS_PATH);
    };

    service.loadAvailableAstronauts = function () {
        return $http.get(API_URL+ASTRONAUTS_PATH+"/available");
    };

    service.acceptMission = function (id) {
        return $http.get(API_URL+USERS_PATH+'/'+id+'/acceptMission');
    };

    service.declineMission = function (id, message) {
        return $http.post(API_URL+USERS_PATH+'/'+id+'/rejectMission', message);
    };

    //Missions
    service.createMission = function (data){
        return $http.post(API_URL+MISSIONS_PATH, data);
    };

    service.loadMissions = function () {
        return $http.get(API_URL+MISSIONS_PATH);
    };

    service.loadActiveMissions = function () {
        return $http.get(API_URL+MISSIONS_PATH+"?active=true");
    };
    service.loadInactiveMissions = function () {
        return $http.get(API_URL+MISSIONS_PATH+"?active=false");
    };

    service.deleteMission = function (id) {
        return $http.delete(API_URL+MISSIONS_PATH+'/'+id);
    };

    service.getMission = function (id) {
        return $http.get(API_URL+MISSIONS_PATH+'/'+id);
    };

    service.updateMission = function (data){
        return $http.put(API_URL+MISSIONS_PATH, data);
    };

    //Spacecrafts
    service.loadSpacecrafts = function () {
        return $http.get(API_URL+SPACECRAFTS_PATH);
    };

    service.loadAvailableSpacecrafts = function() {
        return $http.get(API_URL+SPACECRAFTS_PATH+"/available");
    };

    return service;
}]);