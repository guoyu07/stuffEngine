<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="staffEngine">
<head>
    <title>Информация по сотруднику</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>"/>
    <script src="<c:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
    <script src="<c:url value="/resources/js/service/dept_service.js"/>"></script>
    <script src="<c:url value="/resources/js/service/employee_service.js"/>"></script>
    <script src="<c:url value="/resources/js/controller/empl_page_ctrl.js"/>"></script>
</head>
<body ng-controller="EmpPageController as empCtrl">
<div class="panel panel-default">
    <div class="panel-heading">
        <span class="lead">
            Сотрудник: <pre>{{empCtrl.employee.lastName}} {{empCtrl.employee.firstName}} {{empCtrl.employee.patrName}}</pre>
        </span>
    </div>
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
        <tr>
            <td><pre>{{empCtrl.employee.lastName}} {{empCtrl.employee.firstName}} {{empCtrl.employee.patrName}}</pre></td>
            <td>{{empCtrl.employee.gender}}</td>
            <td>{{empCtrl.employee.birthday}}</td>
            <td>{{empCtrl.employee.position.title}}</td>
            <td>{{empCtrl.employee.grade.description}}</td>
            <td>{{empCtrl.employee.parentDept.deptName}}</td>
            <td>{{empCtrl.employee.salary}}&nbsp;руб.</td>
        </tr>
    </table>
</div>
<br/>
<a href="/employees/{{empCtrl.employee.empID}}/history" target="_blank">Перейти к истории изменений</a>
<br/>
<a href="/employees/{{empCtrl.employee.empID}}/audit" target="_blank">Показать данные аудита по данному сотруднику</a>
</body>
</html>
