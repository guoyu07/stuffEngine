'use strict';

angular.module('staffEngine').controller('AuditController', ['$scope', '$location', 'AuditService', function ($scope, $location, AuditService) {
    var self = this;
    self.sId = $location.absUrl().split("/")[4];
    self.audit = [];
    self.searchDate = {from:'', to:''};

    self.submit = submit;
    self.reset = reset;

    function fetchAuditInfo(id, searchDate) {
        AuditService.fetchAuditInfoByDeptId(id, searchDate)
            .then(
                function (data) {
                    self.audit = data;
                },
                function (errResponse) {
                    console.error('Error while fetching audit info of Dept with id = ' + id);
                }
            );
    }

    function submit() {
        fetchAuditInfo(self.sId, self.searchDate);
    }

    function reset() {
        self.searchDate={from:'', to:''};
        $scope.dateForm.$setPristine();
    }

}]);