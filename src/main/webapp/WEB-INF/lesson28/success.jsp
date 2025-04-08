<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Заявка успешно создана</title>
    <link rel="stylesheet" href="css/lesson28/styles.css">
</head>
<body>
<h1>Поздравляем! Ваша заявка была успешно отправлена</h1>

<div class="info-container">
    <div class="info-item">
        <span class="info-label">Имя:</span> ${name}
    </div>
    <div class="info-item">
        <span class="info-label">Email:</span> ${email}
    </div>
    <div class="info-item">
        <span class="info-label">Сообщение:</span> ${message}
    </div>
</div>

<div class="actions">
    <a href="/SpringFrame_war/taskOnePage" class="btn">Назад</a>
    <a href="/SpringFrame_war/home-28" class="btn">На главную</a>
</div>
</body>
</html>