angular.module(appName).constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
});

angular.module(appName).constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    editor: 'editor',
    guest: 'guest'
});

angular.module(appName).factory('AuthService', function ($http, Session) {
    var authService = {};



    authService.login = function (credentials) {
        return $http
                .post(appApi + '/login', credentials)
                .then(function (res) {
                    Session.create(res.data.entidade.id, res.data.entidade.email, 'guest');
                    return res.data.entidade;
                });
    };

    authService.isAuthenticated = function () {
        return !!Session.userId;
    };

    authService.isAuthorized = function (authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
            authorizedRoles = [authorizedRoles];
        }
        return (authService.isAuthenticated() &&
                authorizedRoles.indexOf(Session.userRole) !== -1);
    };

    return authService;
});

angular.module(appName).service('Session', function () {
    this.create = function (sessionId, userId, userRole) {
        this.id = sessionId;
        this.userId = userId;
        this.userRole = userRole;
    };
    this.destroy = function () {
        this.id = null;
        this.userId = null;
        this.userRole = null;
    };
});

angular.module(appName).controller('ApplicationController', function ($scope,
        USER_ROLES,
        AuthService) {
    $scope.currentUser = null;
    $scope.userRoles = USER_ROLES;
    $scope.isAuthorized = AuthService.isAuthorized;

    $scope.setCurrentUser = function (user) {
        console.debug(user);
        $scope.currentUser = user;
    };
});

angular.module(appName).controller('LoginController', function ($scope, $rootScope, AUTH_EVENTS, AuthService) {
    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (user) {
            $scope.setCurrentUser(user);
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        });
    };
});



angular.module(appName).controller("TaskCadastroController", function ($scope) {
    c = this;

    c.salvar = function () {
        $.post(appApi + "/atividades", c.atividade, function (response) {
            console.debug(response);
        });
    };

});

angular.module(appName).controller("TaskListController", function ($scope) {
    c = this;



    this.updateList = function () {
        $.post(appApi + "/atividades", {option: "findByUser", cpf: $scope.currentUser.cpf}, function (response) {
            if (response.erro === false) {
                c.lista = response.entidades;
            } else {
                c.lista = [];
            }
        }, "json");
    };

    c.excluir = function (id) {

    };




});


