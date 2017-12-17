'use strict';

/* Defines application and its dependencies */

var spaceMissionApp = angular.module('spaceMissionApp', ['ngRoute', 'controllers', 'ngCookies']);
var controllers = angular.module('controllers', []);

/* Configures URL fragment routing, e.g. #/product/1  */
spaceMissionApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/login', {templateUrl: 'views/login.html', controller: 'LoginCtrl'}).
        when('/users', {templateUrl: 'views/users.html', controller: 'UsersCtrl'}).
        when('/components', {templateUrl: 'views/components.html', controller: 'ComponentsCtrl'}).
        when('/missions', {templateUrl: 'views/missions.html', controller: 'MissionsCtrl'}).
        when('/spacecrafts', {templateUrl: 'views/spacecrafts.html', controller: 'SpacecraftsCtrl'}).

/*        when('/shopping', {templateUrl: 'partials/shopping.html', controller: 'ShoppingCtrl'}).
        when('/product/:productId', {templateUrl: 'partials/product_detail.html', controller: 'ProductDetailCtrl'}).
        when('/category/:categoryId', {templateUrl: 'partials/category_detail.html', controller: 'CategoryDetailCtrl'}).
        when('/admin/products', {templateUrl: 'partials/admin_products.html', controller: 'AdminProductsCtrl'}).
        when('/admin/newproduct', {templateUrl: 'partials/admin_new_product.html', controller: 'AdminNewProductCtrl'}).
        when('/admin/categories', {templateUrl: 'partials/admin_categories.html', controller: 'AdminCategoriesCtrl'}).
        when('/admin/newcategory', {
            templateUrl: 'partials/admin_new_category.html',
            controller: 'AdminNewCategoryCtrl'
        }).*/
        otherwise({redirectTo: '/login'});
    }]);

/*
 * alert closing functions defined in root scope to be available in every template
 */
spaceMissionApp.run(function ($rootScope) {
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };
});

