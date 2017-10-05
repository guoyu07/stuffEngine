'use strict';

angular.module('staffEngine').controller('DeptController', ['$scope', 'DeptService', function ($scope, DeptService) {
    var self = this;
    self.depts = [];

    fetchAllDepts();

    function fetchAllDepts() {
        DeptService.fetchAllDepts()
            .then(
                function (data) {
                    self.depts = data;
                },
                function (errResponse) {
                    console.error('Error while fetching Depts');
                }
            );
    }

}]);