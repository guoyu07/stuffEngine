<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="staffEngine">
<head>
    <title>Аудит</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>"/>
    <script src="<c:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
    <script src="<c:url value="/resources/js/service/audit_service.js"/>"></script>
    <script src="<c:url value="/resources/js/controller/audit_emp_cntrl.js"/>"></script>
</head>
<body ng-controller="AuditController as bpCtrl">
<div class="panel panel-default">
    <div class="panel-heading"><span class="lead"> Аудит </span></div>
    <table class="table table-hover">
        <tr>
            <th>Id</th>
            <th>id отдела</th>
            <th>id сотрудника</th>
            <th>IP-адрес</th>
            <th>Тело запроса</th>
            <th>Действие</th>
            <th>Дата запроса</th>
            <th>Сообщение об ошибке</th>
        </tr>
        <tr ng-repeat="auditInfo in bpCtrl.audit | orderBy:'id':false">
            <td>{{auditInfo.id}}</td>
            <td>{{auditInfo.deptId}}</td>
            <td>{{auditInfo.empId}}</td>
            <td>{{auditInfo.ipAddr}}</td>
            <td>{{auditInfo.requestObject}}</td>
            <td>{{auditInfo.action.event}}</td>
            <td>{{auditInfo.requestDate}}</td>
            <td>{{auditInfo.exceptionMessage}}</td>
        </tr>
    </table>
</div>
</body>
</html>
