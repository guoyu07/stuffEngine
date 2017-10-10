<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="staffEngine">
<head>
    <title>Информация по сотруднику</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>"/>
    <script src="<c:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
    <script src="<c:url value="/resources/js/service/employee_service.js"/>"></script>
    <script src="<c:url value="/resources/js/controller/history_ctrl.js"/>"></script>
</head>
<body ng-controller="HistoryController as empCtrl">
<div class="panel panel-default">
    <div class="panel-heading">
        <span class="lead">
            Сотрудник: <pre>{{empCtrl.employee.lastName}} {{empCtrl.employee.firstName}} {{empCtrl.employee.patrName}}</pre>
        </span>
    </div>
    <table class="table table-hover">
        <tr>
            <th>id</th>
            <th>empId</th>
            <th>startDate</th>
            <th>endDate</th>
            <th>isActive</th>
            <th>isDeleted</th>
            <th>ФИО</th>
            <th>Пол</th>
            <th>Дата рождения</th>
            <th>Должность</th>
            <th>Грейд</th>
            <th>Отдел</th>
            <th>Зарплата</th>
        </tr>
        <tr ng-repeat="empl in empCtrl.history">
            <td>{{empl.id}} </td>
            <td>{{empl.empID}} </td>
            <td>{{empl.startDate}} </td>
            <td>{{empl.endDate}} </td>
            <td>{{empl.isActive}} </td>
            <td>{{empl.isDeleted}} </td>
            <td><pre>{{empl.lastName}} {{empl.firstName}} {{empl.patrName}}</pre></td>
            <td>{{empl.gender}}</td>
            <td>{{empl.birthday}}</td>
            <td>{{empl.position.title}}</td>
            <td>{{empl.grade.description}}</td>
            <td>{{empl.parentDept.deptName}}</td>
            <td>{{empl.salary}}&nbsp;руб.</td>
        </tr>
    </table>
</div>
</body>
</html>
