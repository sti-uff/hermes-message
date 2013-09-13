function ListCtrl($scope, $http) {
    $scope.api = 'api/';
    $scope.title = 'Send Tasks';
    $http.get('http://localhost:8080/hermes/api/sendtasks/').
            success(function(data) {
        $scope.sendtasks = data;
    });
}

function NewCtrl($scope) {
    $scope.save = function(sendtask) {
        $http.post('http://localhost:8080/hermes/api/sendtasks/', sendtask).
                success(function(data) {
            $scope.sendtasks = data;
        });
    };

    $scope.reset = function() {
        $scope.user = angular.copy($scope.master);
    };
}
