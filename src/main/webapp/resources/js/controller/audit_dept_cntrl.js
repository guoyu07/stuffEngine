'use strict';

angular.module('staffEngine').controller('AuditController', ['$scope', '$location', 'AuditService', function ($scope, $location, AuditService) {
    var self = this;
    self.sId = $location.absUrl().split("/")[4];
    self.audit = [];

    fetchAuditInfo(self.sId);

    function fetchAuditInfo(id) {
        AuditService.fetchAuditInfoByDeptId(id)
            .then(
                function (data) {
                    self.audit = data;
                },
                function (errResponse) {
                    console.error('Error while fetching audit info of Dept with id = ' + id);
                }
            );
    }

}]);