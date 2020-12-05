<%@ page import="java.util.*,java.text.*,jardinServlet.*,JardinCollectif.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>IFT287 - Système de gestion de Jardin Collectif</title>
<meta name="author" content="Etienne Dagenais et Charles Belanger">
<meta name="description"
	content="Page d'accueil du système de gestion JardinCollectif.">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/navigation.jsp" />
		<h1 class="text-center">Système de gestion de Jardin Collectif</h1>

<%
		    if (session.getAttribute("admin") != null)
		    {
%>
		<h3 class="text-center">Liste des membres</h3>
		<div class="col-8 offset-2">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Utilisateur</th>
                        <th scope="col">Prenom</th>
                        <th scope="col">Nom</th>
					</tr>
				</thead>
				<tbody>
<%
					    List<TupleMembre> membres = JardinHelper.getJardinInterro(session).getGestionMembre()
					                .getListMembres();
					        for (TupleMembre m : membres)
					        {
%>
					<tr>
						<td><%=m.getNoMembre()%></td>
						<td><%=m.getPrenom()%></td>
						<td><%=m.getNom()%></td>
					<tr>
                        <% } %>
				</tbody>
			</table>

		<h3 class="text-center">Liste des plantes</h3>			
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Nom plante</th>
						<th scope="col">Temps de culture</th>
						<th scope="col">Nombre de plants en culture</th>						
					</tr>
				</thead>
				<tbody>
					<% List<String> affichagePlantes = JardinHelper.getJardinInterro(session).getGestionInterrogation().listerPlantes();
						for(int i=0; i < affichagePlantes.size(); i=i+3)
						{
					%>
					<tr>
						<td><%=affichagePlantes.get(i)%></td>
						<td><%=affichagePlantes.get(i+1)%></td>
						<td><%=affichagePlantes.get(i+2)%></td>
					</tr>
					<%} %>
				</tbody>
			</table>
			
					<h3 class="text-center">Liste des lots</h3>			
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Nom lot</th>
						<th scope="col">UserId</th>
					</tr>
				</thead>
				<tbody>
					<% List<String> affichageLots = JardinHelper.getJardinInterro(session).getGestionInterrogation().listerLots();
						for(int i=0; i < affichageLots.size(); i=i+2)
						{
					%>
					<tr>
						<td><%=affichageLots.get(i)%></td>
						<td><%=affichageLots.get(i+1)%></td>
					</tr>
					<%} %>
				</tbody>
			</table>
			
			<h3 class="text-center">Liste des plantes par lots</h3>	
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Nom lot</th>
						<th scope="col">Nom plante</th>
						<th scope="col">Date Plantaison</th>
						<th scope="col">Date recolte</th>
					</tr>
				</thead>
				<tbody>
					<% List<String> affichagePlantsLots = JardinHelper.getJardinInterro(session).getGestionInterrogation().listerPlantsLot();
						for(int i=0; i < affichagePlantsLots.size(); i=i+4)
						{
							
					%>
					<tr>
						<td><%=affichagePlantsLots.get(i)%></td>
						<td><%=affichagePlantsLots.get(i+1)%></td>
						<td><%=affichagePlantsLots.get(i+2)%></td>
						<td><%=affichagePlantsLots.get(i+3)%></td>
					</tr>
					<%} %>
				</tbody>
			</table>
			
			
		</div>
<%	        
		    }
%>

		<br>
		<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
		<jsp:include page="/WEB-INF/messageErreur.jsp" />
		<br>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>