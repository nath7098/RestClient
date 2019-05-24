<jsp:useBean id="user" scope="session" type="com.rest.user.User"/>

<%--com.rest.userKilian
Date: 26/03/2019
Time: 11:16
To change this template use file | Settings | file Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Connexion</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>

<%@ include file="header.jsp" %>
<body>
<div class="container border border-light rounded pt-5 mt-5 mb-5 pb-5">
    <div class="heading text-center pt-4">
        <h3 class="card-title">Welcome ${user.firstName} ${user.lastName}</h3>
        <br>
        <p>--- You can now download files ! ---</p>
        <br>
    </div>
</div>
</body>

<%@include file="footer.jsp" %>
</html>
