'use strict';

angular.module('staffEngine').factory('AuditService', ['$http', '$q', function ($http, $q) {
    var SERVICE_URI = '/audit/';

    var factory = {
        fetchAuditInfoByDeptId: fetchAuditInfoByDeptId,
        fetchAuditInfoByEmployeeId: fetchAuditInfoByEmployeeId
    };

    return factory;

    function fetchAuditInfoByDeptId(id) {
        var deffered = $q.defer();
        $http.get(SERVICE_URI+"getInfoDep/"+id)
            .then(
                function (response) {
                    deffered.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching audit info of Dept with id = ' + id);
                    deffered.reject(errResponse);
                }
            );
        return deffered.promise;
    }

    function fetchAuditInfoByEmployeeId(id) {
        var deffered = $q.defer();
        $http.get(SERVICE_URI+"getInfoEmp/"+id)
            .then(
                function (response) {
                    deffered.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while getting audit info of Employee with id = ' + id);
                    deffered.reject(errResponse);
                }
            );
        return deffered.promise;
    }

}]);