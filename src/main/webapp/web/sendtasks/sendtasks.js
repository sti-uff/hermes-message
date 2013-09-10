function ListCtrl($scope, $http) {
    $scope.api = 'api/';
    $scope.title = 'Send Tasks';
    $http.get('http://localhost:8080/hermes/api/sendtasks/').
            success(function(data) {
        $scope.sendtasks = data;
    });
}
