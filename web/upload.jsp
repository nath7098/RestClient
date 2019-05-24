<jsp:useBean id="success" scope="request" type="java.lang.String"/>
<jsp:useBean id="message" scope="request" type="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  com.rest.userKilian
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
        <h3 class="card-title">Upload de fichier</h3>
    </div>
    <form class="animated fadeIn" action="upload" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <p>Select a file to upload</p>
                <div class="md-form">
                    <input id="file" type="file"  name="file" class="form-control w-100"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <button class="btn btn-dark w-100" type="submit">Upload</button>
            </div>
        </div>
    </form>
    <div class="row mb-3 mt-3">
        <div class="col-12 col-lg-8 offset-lg-2 text-center text-danger" id="error">
            ${message}
        </div>
        <div class="col-12 col-lg-8 offset-lg-2 text-center text-info" id="success">
            ${success}
        </div>
    </div>
</div>
</body>


<%@include file="footer.jsp" %>
</html>
