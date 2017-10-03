'use strict';

angular.module('staffEngine').controller('EmpPageController',
    ['$scope', '$location', 'DeptService', 'EmployeeService',
    function ($scope, $location, DeptService, EmployeeService) {

    var self = this;
    self.sId = $location.absUrl().split("/")[4];
    self.employee =
        {
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
    // self.parentDept = getDeptById(self.dept.parentDeptId);
    // self.deptHead = getEmployeeById(self.dept.deptHeadId);

    getEmployeeById(self.sId);

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

    function getEmployeeById(id) {
        EmployeeService.getEmployeeById(id)
            .then(
                function (data) {
                    self.employee = data;
                },
                function (errResponse) {
                    console.error('Error while fetching Dept with id = ' + id);
                }
            )
    }
}]);