'use strict';

angular.module('staffEngine').controller('DeptPageController',
    ['$scope', '$location', 'DeptService', 'EmployeeService',
    function ($scope, $location, DeptService, EmployeeService) {

    var self = this;
    self.sId = $location.absUrl().split("/")[4];
    self.dept = {id:null, parentDeptId:null, deptName:'', deptHeadId:null};
    self.deptEmployees = getDeptEmployees(self.sId);
    self.parentDept = {id:null, parentDeptId:null, deptName:'', deptHeadId:null};
    self.deptHead = {
        empID:null,
        position:null,
        grade:null,
        department:null,
        lastName:'',
        firstName:'',
        patrName:'',
        gender:'',
        birthday:'',
        salary:null
    };

    getDeptById(self.sId);
    getDeptHead();
    getParentDept();

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

    function getDeptHead() {
        DeptService.getDeptById(self.sId)
            .then(
                function (data) {
                    EmployeeService.getEmployeeById(data.deptHeadId)
                        .then(
                            function (data) {
                                self.deptHead = data
                            },
                            function (errResponse) {
                                console.error('Error while fetching employee with id = ' + id);
                            }
                        )
                },
                function (errResponse) {
                    console.error('Error while fetching Dept with id = ' + id);
                }
            )
    }

    function getParentDept() {
        DeptService.getDeptById(self.sId)
            .then(
                function (data) {
                    DeptService.getDeptById(data.parentDeptId)
                        .then(
                            function (data) {
                                self.parentDept = data
                            },
                            function (errResponse) {
                                console.error('Error while fetching Dept with id = ' + id);
                            }
                        )
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