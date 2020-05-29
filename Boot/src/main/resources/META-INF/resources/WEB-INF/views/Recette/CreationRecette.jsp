<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/script_recette.js"></script>
	<title>Création d'une nouvelle recette</title>
</head>

<body onload="ajoutEtapeRecette()">
	<div class="container">
		<h1>Ajout d'une nouvelle Recette, étape par étape</h1>
		<form:form id="form-recette-create" action="${ctx}/creation-recette/ajout/entree" method="POST"
			modelAttribute="recetteEtapeContainer">
			<form:hidden id="hidden-version" path="entree.version" />
			<div class="row">
				<div class="form-group">
					<form:label id="label-nom" path="entree.nom">Nom de la recette : </form:label>
					<form:input id="input-nom" path="entree.nom" cssClass="form-control" />
					<form:errors path="entree.nom"></form:errors>
				</div>
				<div class="form-group">
					<label for="type-recette">Type : </label>
					<select class="form-control" id="type-recette" onchange="modifierTypeRecette('${ctx}')">
						<option value="entree">Entrée</option>
						<option value="plat">Plat</option>
						<option value="dessert">Dessert</option>
						<option value="boisson">Boisson</option>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="form-inline">
					<form:label id="label-cout" path="entree.cout">Coût : </form:label>
					<form:select id="select-cout" path="entree.cout" items="${couts}" itemLabel="label"
						cssClass="form-control" />
				</div>
				<div class="form-inline">
					<form:label id="label-difficulte" path="entree.difficulte">Difficulté : </form:label>
					<form:select id="select-difficulte" path="entree.difficulte" items="${difficultes}"
						itemLabel="label" cssClass="form-control" />
				</div>
				<div class="form-inline">
					<form:label id="label-parts" path="entree.nbPersonne">Parts : </form:label>
					<form:input id="select-parts" path="entree.nbPersonne" cssClass="form-control" maxlength="3" />
				</div>
				<div class="form-inline">
					<form:label id="label-duration" path="entree.temps">Durée estimée (min) : </form:label>
					<form:input id="input-duration" path="entree.temps" cssClass="form-control" maxlength="3" />
				</div>
			</div>

			<div class="row">
				<label>Ajouter une nouvelle étape a la recette : </label>
				<button id="btn-ajout-etape-recette" type="button" class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-plus"></span>+
				</button>
				<script>
					document.getElementById("btn-ajout-etape-recette").addEventListener("click", function (event) {
						ajoutEtapeRecette();
					});
				</script>
			</div>

			<div id="etape-recette" class="container">

			</div>

			<div>
				<button type="submit" class="btn btn-success">Enregistrer</button>
				<a href="${ctx}/creation-recette" class="btn btn-warning">Annuler</a>
			</div>
		</form:form>
	</div>

</body>

</html>