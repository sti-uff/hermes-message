angular.module('sendtasks', []).
        config(function($routeProvider) {
    $routeProvider.
            when('/', {controller: ListCtrl, templateUrl: 'list.html'}).
            when('/edit/:taskId', {controller: EditCtrl, templateUrl: 'detail.html'}).
            when('/new', {controller: CreateCtrl, templateUrl: 'detail.html'}).
            otherwise({redirectTo: '/'});
});

function ListCtrl($scope, $http) {
    $scope.urlBaseApi = '../../api';
    $scope.title = 'Send Tasks';

    $http.get($scope.urlBaseApi + '/sendtasks/').
            success(function(data) {
        $scope.sendtasks = data;
    });
}

function CreateCtrl($scope, $http) {
    $scope.urlBaseApi = '../../api';

    $scope.save = function() {
        $scope.answer = 'salvando...';

        $http.post($scope.urlBaseApi + '/sendtasks/', $scope.sendTask)
                .success(function(data) {
            $scope.answerClass = 'success';
            $scope.answer = 'Task created. Id = ' + data;

            window.location.replace("index.html");
        }).error(function(status) {
            $scope.answerClass = 'danger';
            $scope.answer = 'Error! HTML status code = ' + status;
        });
    };

    $scope.reset = function() {
        $scope.sendTask = null;
    };
}

function EditCtrl($scope, $location, $routeParams, $http) {
    $scope.urlBaseApi = '../../api';

    try {
        $http.get($scope.urlBaseApi + '/sendtasks/' + $routeParams.taskId).
                success(function(data) {
            $scope.sendTask = data;
        }).error(function(status) {
            $scope.answerClass = 'danger';
            $scope.answer = 'Error! HTML status code = ' + status;
        });
    } catch (error) {
        $scope.answer = "Error:" + error;
    }

    $scope.save = function() {
        $scope.answer = 'salvando...';

        $http.post($scope.urlBaseApi + '/sendtasks/', $scope.sendTask)
                .success(function(data) {
            $scope.answerClass = 'success';
            $scope.answer = 'Task created. Id = ' + data;

            window.location.replace("index.html");
        }).error(function(status) {
            $scope.answerClass = 'danger';
            $scope.answer = 'Error! HTML status code = ' + status;
        });
    };

    $scope.reset = function() {
        $scope.sendTask = null;
    };
}
