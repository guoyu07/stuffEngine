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
    <script src="<c:url value="/resources/js/controller/audit_dept_cntrl.js"/>"></script>
</head>
<body ng-controller="AuditController as bpCtrl">
<div class="formcontainer">
    <form ng-submit="bpCtrl.submit()" name="dateForm" class="form-horizontal">
        <div class="row">
            <div class="form-group col-md-12">
                <label for="fromDate" class="col-md-2 control-label">From</label>
                <div class="col-md-7">
                    <input type="text" class="form-control input-sm" ng-model="bpCtrl.searchDate.from" id="fromDate" placeholder="Enter the start date"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label for="toDate" class="col-md-2 control-label">To</label>
                <div class="col-md-7">
                    <input type="text" class="form-control input-sm" ng-model="bpCtrl.searchDate.to" id="toDate" placeholder="Enter the end date"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div align="center">
                <input type="submit"  value="Get audit info" class="btn">
                <button type="button" ng-click="bpCtrl.reset()" class="btn">Reset Form</button>
            </div>
        </div>
    </form>
</div>
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
