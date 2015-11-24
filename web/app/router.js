angular.module(appName).config(function($routeProvider, $locationProvider) {
    $routeProvider.when('/tasks',{
        templateUrl:'templates/atividades/index.html'
    });
    $routeProvider.when('/task/:id',{
        templateUrl:'templates/atividades/form.html'
    });
    $routeProvider.when('/',{
        templateUrl:'templates/atividades/index.html'
    });
});


