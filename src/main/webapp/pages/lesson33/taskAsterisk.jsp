<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Задание * - операции с помощью JDBC</title>
</head>
<body>
<h1>Задание *: Web приложение на сервлетах</h1>

<!-- для отображения результатов -->
<% String message = (String) request.getAttribute("message"); %>
<% String messageType = (String) request.getAttribute("messageType"); %>

<% if (message != null) { %>
<div style="color: <%= "success".equals(messageType) ? "green" : "red" %>;">
  <h3><%= message %></h3>
</div>
<% } %>

<!-- для отображения найденного пользователя -->
<%
  lesson33.homework.User foundUser =
          (lesson33.homework.User) request.getAttribute("foundUser");
  if (foundUser != null) {
%>
<h3>Результат:</h3>
<p>ID: <%= foundUser.getId() %></p>
<p>Имя: <%= foundUser.getName() %></p>
<p>Логин: <%= foundUser.getUsername() %></p>
<p>Пароль: <%= foundUser.getPassword() %></p>
<% } %>

<h3>1. GET /get?id= - получить пользователя по ID</h3>
<form action="get-user" method="get">
  <div>
    <label>ID пользователя:</label>
    <input type="text" name="id" required>
  </div>
  <button type="submit">Получить пользователя</button>
</form>

<h3>2. POST /create - создать пользователя</h3>
<form action="create-user" method="post">
  <div>
    <label>Имя:</label>
    <input type="text" name="name" required>
  </div>
  <div>
    <label>Логин:</label>
    <input type="text" name="username" required>
  </div>
  <div>
    <label>Пароль:</label>
    <input type="password" name="password" required>
  </div>
  <button type="submit">Создать пользователя</button>
</form>

<h3>3. GET /change-login?id=&login= - изменить логин</h3>
<form action="change-login" method="get">
  <div>
    <label>ID пользователя:</label>
    <input type="text" name="id" required>
  </div>
  <div>
    <label>Новый логин:</label>
    <input type="text" name="login" required>
  </div>
  <button type="submit">Изменить логин</button>
</form>

<h3>4. GET /delete?id= - удалить пользователя</h3>
<form action="delete-user" method="get">
  <div>
    <label>ID пользователя:</label>
    <input type="text" name="id" required>
  </div>
  <button type="submit">Удалить пользователя</button>
</form>


<p><a href="home-33">Назад</a></p>
</body>
</html>