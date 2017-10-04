'use strict';

angular.module('staffEngine').factory('EmployeeService', ['$http', '$q', function ($http, $q) {
    var SERVICE_URI = '/employee/';

    var factory = {
        getEmployeeById: getEmployeeById,
        getEmployeeHistory: getEmployeeHistory,
        fetchDeptEmployees: fetchDeptEmployees
    };

    return factory;

    function getEmployeeById(id) {
        var deffered = $q.defer();
        $http.get(SERVICE_URI+id)
            .then(
                function (response) {
                    deffered.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while getting employee by id = ' + id);
                    deffered.reject(errResponse);
                }
            );
        return deffered.promise;
    }

    function getEmployeeHistory(id) {
        var deffered = $q.defer();
        $http.get(SERVICE_URI+id+"/history")
            .then(
                function (response) {
                    deffered.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while getting employee history by id = ' + id);
                    deffered.reject(errResponse);
                }
            );
        return deffered.promise;
    }

    function fetchDeptEmployees(id) {
        var deffered = $q.defer();
        $http.get(SERVICE_URI+"all/"+id)
            .then(
                function (response) {
                    deffered.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching all Depts');
                    deffered.reject(errResponse);
                }
            );
        return deffered.promise;
    }

}]);