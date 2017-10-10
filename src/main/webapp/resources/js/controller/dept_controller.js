'use strict';

angular.module('staffEngine').controller('DeptController', ['$scope', 'DeptService', function ($scope, DeptService) {
    var self = this;
    self.depts = [];
    self.tree = [];

    fetchAllDepts();
    fetchHierarchy();

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

    function fetchHierarchy() {
        DeptService.getHierarchy()
            .then(
                function (data) {
                    self.tree = data;
                },
                function (errResponse) {
                    console.error('Error while fetching hierarchy');
                }
            );
    }

}]);