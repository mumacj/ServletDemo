<%--
  Created by IntelliJ IDEA.
  User: Ncj
  Date: 2018/7/23
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MyFirstServletDemo</title>
  </head>
  <body>
  <button onclick="HelloWorld()">HelloWorld</button>
  <img src="/download/testDownload.jpg" alt="" style="height: 95%;width: 30%;">
  <button onclick="Download()">Download this picture!</button>
  <button onclick="Refresh()">Refresh Test!</button>
  <script>
    function HelloWorld() {
        window.location.href="/HelloWorld";
    }
    function Download() {
        window.location.href="/Download";
    }
    function Refresh() {
        window.location.href="/Refresh";
    }
  </script>
  </body>
</html>