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
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
<title>Création d'une nouvelle recette</title>
</head>
<body>
	
	<div class="container">
		<h1>Ajout d'une nouvelle Recette, étape par étape</h1>
		<form:form id="form-recette-create" action="" method="POST" modelAttribute="recette">
			<form:hidden path="version"/>
			<div class="row">
				<div class="form-group">
					<form:label path="nom">Nom de la recette : </form:label>
					<form:input path="nom" cssClass="form-control"/>
					<form:errors path="nom"></form:errors>
				</div>
				<div class="form-group">
					<label for="typeRecette">Type : </label> 
					<select class="form-control" id="typeRecette">
						<option value="entree">Entrée</option>
						<option value="plat">Plat</option>
						<option value="dessert">Dessert</option>
						<option value="boisson">Boisson</option>
					</select>
					<script>
						document.getElementById("typeRecette").addEventListener("change", function(){
							let get_form = document.getElementById("form-recette-create");
							get_form.action= '${ctx}/creation-recette/ajout/'+this.value;
						});
					</script>
				</div>
			</div>
			
			<div class="row">
				<div class="form-inline ">
					<form:label path="cout">Coût : </form:label>
					<form:select path="cout" items="${couts}" itemLabel="label" cssClass="form-control"/>
				</div>
				<div class="form-inline">
					<form:label path="difficulte">Difficulté : </form:label>
					<form:select path="difficulte" items="${difficultes}" itemLabel="label" cssClass="form-control"/>
				</div>
				<div class="form-inline">
					<form:label path="nbPersonne">Parts : </form:label>
					<form:input path="nbPersonne" cssClass="form-control"/>
				</div>
			</div>
			
			<div>
				<button type="submit" class="btn btn-success">Enregistrer</button>
				<a href="${ctx}/creation-recette" class="btn btn-warning">Annuler</a>
			</div>
		</form:form>
	</div>
		
</body>
</html>