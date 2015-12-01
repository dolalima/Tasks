angular.module(appName).config(function ($stateProvider, $routeProvider, $locationProvider, USER_ROLES) {
    $stateProvider.state('dashboard', {
        url: '/dashboard',
        templateUrl: 'dashboard/index.html',
        data: {
            authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
        }
    });
    $routeProvider.when('/tasks', {
        templateUrl: 'templates/atividades/index.html'
    });
    $routeProvider.when('/task/:id', {
        templateUrl: 'templates/atividades/form.html'
    });
    $routeProvider.when('/', {
        templateUrl: 'templates/atividades/index.html'
    });
});

angular.module(appName).run(function ($rootScope, AUTH_EVENTS, AuthService) {
  $rootScope.$on('$stateChangeStart', function (event, next) {
    var authorizedRoles = next.data.authorizedRoles;
    if (!AuthService.isAuthorized(authorizedRoles)) {
      event.preventDefault();
      if (AuthService.isAuthenticated()) {
        // user is not allowed
        $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
      } else {
        // user is not logged in
        $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
      }
    }
  });
});

angular.module(appName).config(function ($httpProvider) {
  $httpProvider.interceptors.push([
    '$injector',
    function ($injector) {
      return $injector.get('AuthInterceptor');
    }
  ]);
});

angular.module(appName).factory('AuthInterceptor', function ($rootScope, $q,
                                      AUTH_EVENTS) {
  return {
    responseError: function (response) { 
      $rootScope.$broadcast({
        401: AUTH_EVENTS.notAuthenticated,
        403: AUTH_EVENTS.notAuthorized,
        419: AUTH_EVENTS.sessionTimeout,
        440: AUTH_EVENTS.sessionTimeout
      }[response.status], response);
      return $q.reject(response);
    }
  };
});



