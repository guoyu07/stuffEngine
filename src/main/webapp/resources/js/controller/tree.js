'use strict';

angular.module('staffEngine').directive('tree', function($compile) {
    return {
        restrict: "E",
        transclude: true,
        scope: {family: '='},
        template:
            '<ul style="margin-left: 0; padding-left: 0">' +
            '<li ng-transclude style="list-style-type: none;"></li>' +
            '<a href="/dept/{{family.id}}" target="_blank">{{ family.deptName }}</a>' +
            '<li ng-repeat="child in family.children" style="list-style-type: none; margin-left: 10px">' +
            '<tree family="child"></tree>' +
            '</li>' +
            '</ul>',
        compile: function (tElement, tAttr, transclude) {
            var contents = tElement.contents().remove();
            var compiledContents;
            return function (scope, iElement, iAttr) {
                if (!compiledContents) {
                    compiledContents = $compile(contents, transclude);
                }
                compiledContents(scope, function (clone, scope) {
                    iElement.append(clone);
                });
            };
        }
    };
});


