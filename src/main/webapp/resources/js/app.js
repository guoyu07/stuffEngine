'use strict';

var staffEngine = angular.module('staffEngine', [], function($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});