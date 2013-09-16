function ListCtrl($scope, $http) {
    $scope.urlBaseApi = '../../api';
    $scope.title = 'Send Tasks';

    $http.get($scope.urlBaseApi + '/sendtasks/').
            success(function(data) {
        $scope.sendtasks = data;
    });
}

function NewCtrl($scope, $http) {
    $scope.urlBaseApi = '../../api';

    $scope.save = function() {
        $scope.answer = 'salvando...';

        $http.post($scope.urlBaseApi + '/sendtasks/', $scope.newSendTask)
                .success(function(data) {
            $scope.answerClass = 'success';
            $scope.answer = 'Task created. Id = ' + data;

        }).error(function(status) {
            $scope.answerClass = 'danger';
            $scope.answer = 'Error! HTML status code = ' + status;
        });
    };

    $scope.reset = function() {
        $scope.newSendTask = null;
    };
}
