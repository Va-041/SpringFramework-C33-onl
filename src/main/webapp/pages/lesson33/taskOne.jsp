<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Задание 1 - CRUD операции для User</title>
</head>
<body>

<% String message = (String) request.getAttribute("message"); %>
<% String messageType = (String) request.getAttribute("messageType"); %>

<% if (message != null) { %>
<div style="color: <%= "success".equals(messageType) ? "green" : "red" %>;">
  <h3><%= message %></h3>
</div>
<% } %>

<h2>CREATE - создание пользователя</h2>
<form action="crud-create" method="post">
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

<h2>READ - чтение пользователя по ID</h2>
<form action="crud-get" method="get">
  <div>
    <label>ID пользователя:</label>
    <input type="text" name="id" required>
  </div>
  <button type="submit">Найти пользователя</button>
</form>

<h2>UPDATE - обновление данных пользователя</h2>
<form action="crud-update" method="post">
  <div>
    <label>ID пользователя:</label>
    <input type="text" name="id" required>
  </div>
  <div>
    <label>Новое имя:</label>
    <input type="text" name="name" required>
  </div>
  <div>
    <label>Новый логин:</label>
    <input type="text" name="username" required>
  </div>
  <button type="submit">Обновить данные</button>
</form>

<h2>DELETE - удаление пользователя</h2>
<form action="crud-delete" method="post">
  <div>
    <label>ID пользователя для удаления:</label>
    <input type="text" name="id" required>
  </div>
  <button type="submit">Удалить пользователя</button>
</form>

<h2>Все пользователи</h2>
<form action="crud-all" method="get">
  <button type="submit">Показать всех пользователей</button>
</form>

<!-- для отображения всех пользователей -->
<%
  java.util.List<lesson33.homework.User> allUsers =
          (java.util.List<lesson33.homework.User>) request.getAttribute("allUsers");
  if (allUsers != null) {
%>
<h3>Список всех пользователей:</h3>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Имя</th>
    <th>Логин</th>
    <th>Пароль</th>
  </tr>
  <% for (lesson33.homework.User user : allUsers) { %>
  <tr>
    <td><%= user.getId() %></td>
    <td><%= user.getName() %></td>
    <td><%= user.getUsername() %></td>
    <td><%= user.getPassword() %></td>
  </tr>
  <% } %>
</table>
<% } %>

<!-- для отображения найденного пользователя -->
<%
  lesson33.homework.User foundUser =
          (lesson33.homework.User) request.getAttribute("foundUser");
  if (foundUser != null) {
%>
<h3>Найденный пользователь:</h3>
<p>ID: <%= foundUser.getId() %></p>
<p>Имя: <%= foundUser.getName() %></p>
<p>Логин: <%= foundUser.getUsername() %></p>
<p>Пароль: <%= foundUser.getPassword() %></p>
<% } %>

<p><a href="home-33">Назад</a></p>
</body>
</html>