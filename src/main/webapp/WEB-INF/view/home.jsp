<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="staffEngine">
<head>
    <title>Отделы</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>"/>
    <script src="<c:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
    <script src="<c:url value="/resources/js/service/dept_service.js"/>"></script>
    <script src="<c:url value="/resources/js/controller/dept_controller.js"/>"></script>
    <script src="<c:url value="/resources/js/controller/tree.js"/>"></script>
</head>
<body ng-controller="DeptController as bpCtrl">
<div class="panel-heading"><span class="lead"> Структура отделов </span></div>
    <div class="tree-cell" ng-repeat="node in bpCtrl.tree">
        <tree family="node">

        </tree>
    </div>
</body>
</html>
