<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отправка заявки</title>
    <link rel="stylesheet" href="css/lesson28/styles.css">
</head>
<body>
<h1>Форма для заявки</h1>
<form action="/SpringFrame_war/check-save-request" method="post" novalidate>
    <div class="form-group">
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name"
               value="${not empty nameValue ? nameValue : param.name}">
        <span class="error">${nameError}</span>
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"
               value="${not empty emailValue ? emailValue : param.email}">
        <span class="error">${emailError}</span>
    </div>

    <div class="form-group">
        <label for="message">Сообщение:</label>
        <textarea id="message" name="message">${not empty messageValue ? messageValue : param.message}</textarea>
        <span class="error">${messageError}</span>
    </div>

    <button type="submit">Отправить</button>
</form>
</body>
</html>