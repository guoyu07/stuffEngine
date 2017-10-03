'use strict';

angular.module('staffEngine').controller('DeptController', ['$scope', 'DeptService', function ($scope, DeptService) {
    var self = this;
    self.dept = {id:null, parentDeptId:null, deptName:'', deptHeadId:null};
    self.depts = [];
    self.deptEmployees = [];

    // self.submit = submit;
    // self.edit = edit;
    // self.remove = remove;
    // self.reset = reset;

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

    function getDeptById(id) {
        DeptService.getDeptById(id)
            .then(
                function (data) {
                    self.dept = data;
                },
                function (errResponse) {
                    console.error('Error while fetching Dept with id = ' + id);
                }
            )
    }

    function getDeptEmployees(id) {
        DeptService.fetchDeptEmployees(id)
            .then(
                function (data) {
                    self.deptEmployees = data;
                },
                function (errResponse) {
                    console.error('Error while fetching Dept with id = ' + id);
                }
            )
    }

}]);