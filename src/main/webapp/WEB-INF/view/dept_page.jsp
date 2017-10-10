<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="staffEngine">
<head>
    <title>Информация по отделу</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>"/>
    <script src="<c:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
    <script src="<c:url value="/resources/js/service/dept_service.js"/>"></script>
    <script src="<c:url value="/resources/js/controller/dept_page_cntr.js"/>"></script>
    <script src="<c:url value="/resources/js/service/employee_service.js"/>"></script>
</head>
<body ng-controller="DeptPageController as bpCtrl">
<div class="panel panel-default">
    <div class="panel-heading"><span class="lead"> Отдел: {{bpCtrl.dept.deptName}} </span></div>
    <table class="table table-hover">
        <tr>
            <th>Id</th>
            <th>Родительский отдел</th>
            <th>Название отдела</th>
            <th>Руководитель отдела</th>
        </tr>
        <tr>
            <td>{{bpCtrl.dept.id}}</td>
            <td>{{bpCtrl.parentDept.deptName}}</td>
            <td>{{bpCtrl.dept.deptName}}</td>
            <td><pre>{{bpCtrl.deptHead.lastName}} {{bpCtrl.deptHead.firstName}} {{bpCtrl.deptHead.patrName}}</pre></td>
        </tr>
    </table>
</div>
<a href="/dept/{{bpCtrl.dept.id}}/audit" target="_blank">Показать данные аудита по данному отделу</a>
<br/>
<div class="panel panel-default">
    <div class="panel-heading"><span class="lead">Список сотрудников </span></div>
    <table class="table table-hover">
        <tr>
            <th>ФИО</th>
            <th>Пол</th>
            <th>Дата рождения</th>
            <th>Должность</th>
            <th>Грейд</th>
            <th>Отдел</th>
            <th>Зарплата</th>
        </tr>
        <tr ng-repeat="employee in bpCtrl.deptEmployees | orderBy:['lastName','firstName','patrName']:false">
            <td>
                <a href="/employees/{{employee.empID}}" target="_blank">
                    <pre>{{employee.lastName}} {{employee.firstName}} {{employee.patrName}}</pre>
                </a>
            </td>
            <td>{{employee.gender}}</td>
            <td>{{employee.birthday}}</td>
            <td>{{employee.position.title}}</td>
            <td>{{employee.grade.description}}</td>
            <td>{{employee.parentDept.deptName}}</td>
            <td>{{employee.salary}}&nbsp;руб.</td>
        </tr>
    </table>
</div>
</body>
</html>
