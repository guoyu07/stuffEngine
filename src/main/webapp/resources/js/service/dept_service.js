'use strict';

angular.module('staffEngine').factory('DeptService', ['$http', '$q', function ($http, $q) {
    var SERVICE_URI = '/department/';

    var factory = {
        fetchAllDepts: fetchAllDepts,
        getDeptById: getDeptById
    };

    return factory;

    function fetchAllDepts() {
        var deffered = $q.defer();
        $http.get(SERVICE_URI+"all")
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

    function getDeptById(id) {
        var deffered = $q.defer();
        $http.get(SERVICE_URI + id)
            .then(
                function (response) {
                    deffered.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while getting Dept by id = ' + id);
                    deffered.reject(errResponse);
                }
            );
        return deffered.promise;
    }

}]);