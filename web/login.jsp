<jsp:useBean id="registerSuccess" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="message" scope="request" type="java.lang.String" class="java.lang.String"/>
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
        <h3 class="card-title">Connexion à l'application</h3>
    </div>
    <form method="post" id="loginForm" action="login" class="animated fadeIn">
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
            <div class="col-6 offset-lg-2 col-lg-4 text-left">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
                    <label class="custom-control-label" for="defaultLoginFormRemember">Se souvenir de moi</label>
                </div>
            </div>
            <div class="col-6 col-lg-4 text-right">
                <a data-toggle="modal" data-target="#exampleModalPreview" href="">Mot de passe oublié ?</a>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2 text-center text-danger" id="error">
                ${message}
            </div>
            <div class="col-12 col-lg-8 offset-lg-2 text-center text-info" id="success">
                ${registerSuccess}
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <button class="btn btn-dark w-100" type="submit">Connexion</button>
            </div>
        </div>
        <div class="text-center mt-2 pb-3 animated fadeIn">
            <p>Pas encore inscrit ?
                <a href="register">S'enregistrer</a>
            </p>
        </div>
    </form>
</div>
</body>

<!-- Modal -->
<div class="modal fade top" id="exampleModalPreview" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalPreviewLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalPreviewLabel">Récupération de mot de passe</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row mb-3 mt-3">
                    <div class="col-12 col-lg-8 offset-lg-2">
                        <div class="md-form">
                            <label for="password_forgotten">Identifiant utilisateur</label>
                            <input id="password_forgotten" type="text" name="password_forgotten" class="form-control">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-dark" formaction="PasswordForgotten" formmethod="post">Récupérer
                    votre mot de passe
                </button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
</html>
