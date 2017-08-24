<%--
  Created by IntelliJ IDEA.
  User: dkondratyev
  Date: 24.08.2017
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
</head>
<body>
<h1>This is Tami</h1><p>
    <form action="/createEmployee" method="GET">
        <label>Фамилия</label><p>
    <input type="text" name="lastName"><p></p>
    <label>Имя</label><p>
        <input type="text" name="firstName"><p>
        <label>Пол</label><p>
        <input type="text" name="fatherName"><p>
        <label>Отчество</label><p>
        <input type="text" name="gender"><p>
        <label>Дата рождения</label><p>
        <input type="text" name="birthDate"><p>
        <label>Должность</label><p>
        <input type="text" name="jobTitle"><p>
        <label>Зарплата</label><p>
        <input type="text" name="salary"><p>
        <button>Создать пользователя</button>
        </form>
</body>
</html>
