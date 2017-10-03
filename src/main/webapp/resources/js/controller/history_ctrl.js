'use strict';

angular.module('staffEngine').controller('HistoryController',
    ['$scope', '$location', 'EmployeeService',
        function ($scope, $location, EmployeeService) {

            var self = this;
            self.sId = $location.absUrl().split("/")[4];
            self.history = [];
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


            getHistory(self.sId);
            getEmployeeById(self.sId);

            function getHistory(id) {
                EmployeeService.getEmployeeHistory(id)
                    .then(
                        function (data) {
                            self.history = data;
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