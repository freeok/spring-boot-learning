<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh">
<head>
    <title>Title</title>
    <style>
      .show {
        color: red;
      }
    </style>
</head>
<body>
<h2>
    <span class="show">${show}</span>
</h2>
<%=request.getRequestURI()%>
</body>
</html>
