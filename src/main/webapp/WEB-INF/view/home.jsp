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
</head>
<body ng-controller="DeptController as bpCtrl">
<div class="panel panel-default">
    <div class="panel-heading"><span class="lead">Список отделов</span></div>
    <table class="table table-hover">
        <tr>
            <th>Название отдела</th>
        </tr>
        <tr ng-repeat="dept in bpCtrl.depts | orderBy:'parentDeptId':false">
            <td><a href="/dept/{{dept.id}}" target="_blank">{{dept.deptName}}</a></td>
        </tr>
    </table>
</div>

</body>
</html>
