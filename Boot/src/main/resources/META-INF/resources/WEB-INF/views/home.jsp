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

<style type="text/css">
.jumbotron {
	background-image:
		url("https://robert-parker-michelin-hk-prod.s3.amazonaws.com/media/image/2017/06/24/9998e7d4f2094dc5b181d8d1f2e08959_types+of+eggs+banner.jpg");
	background-size: cover;
	display: flex;
	align-items: center;
}
</style>

<title>Insert title here</title>
</head>
<body>

	<div class="jumbotron jumbotron-fluid p-0 m-0">
		<div class="container">
			<h1 class="display-4">AJC Kitchen</h1>
			<p class="lead">Va te faire cuire un oeuf</p>
		</div>
	</div>

	<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
		<a class="navbar-brand" href="#">AJC Kitchen</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="#">Recettes
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Choisi pour moi</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Mes favoris</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Mon compte</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="input-group">
			<input class="form-control" type="text" placeholder="Search"
				aria-label="Search">
			<div class="input-group-append" id="button-addon4">
				<button class="btn btn-outline-secondary" type="button">Recherche</button>
				<button class="btn btn-outline-secondary" type="button">Recherche
					avancée</button>
			</div>
		</div>
	</div>


	<div class="container">
		<c:forEach var="i" begin="0" end="9" step="3">
			<div class="row">
				<c:forEach var="j" begin="${i}" end="${i+2}" step="1"
					items="${recettes}">
					<div class="card m-3 col-md" style="width: 18rem;">
						<img
							src="https://www.ledahu.net/dahu/wp-content/uploads/cache/images/A-3/A-3-202976579.jpg"
							class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">${j.nom}</h5>
							<p class="card-text">${j.getClass().simpleName}</p>
							<p class="card-text">
								Tags :
								<c:forEach var="t" begin="0" end="2" step="1" items="${j.tags}">
									<button type="button" class="btn btn-outline-primary btn-sm">${t.id.tag.tag}</button>
								</c:forEach>
							</p>
							<a href="#" class="btn btn-primary">Recette complète</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
	</div>



</body>
</html>