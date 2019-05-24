<jsp:useBean id="message" scope="request" type="java.lang.String" class="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  com.rest.userKilian
  Date: 26/03/2019
  Time: 11:16
  To change this template use file | Settings | file Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Inscription</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<%@ include file="header.jsp" %>
<body>
<div class="container border-light border rounded mt-5 pt-5 mb-5 pb-5">
    <div class="heading text-center pt-4">
        <h3 class="card-title">Enregistrement dans l'application</h3>
    </div>
    <form method="post" id="registerForm" action="register" class="animated fadeIn">
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="nom" type="text" name="nom" class="form-control">
                    <label for="nom">Nom *</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="prenom" type="text" name="prenom" class="form-control">
                    <label for="prenom">Prénom *</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="username" type="text" name="username" class="form-control">
                    <label for="username">Identifiant utilisateur *</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="password" type="password" name="password" class="form-control">
                    <label for="password">Mot de passe *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2 text-danger text-center" id="success">
                ${message}
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <button class="btn btn-dark w-100" type="submit" id="btnSubmit">S'inscrire</button>
            </div>
        </div>
    </form>
    <div class="text-center mt-2 pb-3 animated fadeIn">
        <p>Déjà inscrit ?
            <a href="login">Se connecter</a>
        </p>
    </div>
</div>
</body>
<%@ include file="footer.jsp" %>
</html>
