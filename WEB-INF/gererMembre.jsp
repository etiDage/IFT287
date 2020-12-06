<%@ page import="java.util.*,java.text.*,jardinServlet.*,JardinCollectif.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>IFT287 - Système de gestion de Jardin Collectif</title>
<meta name="author" content="Etienne Dagenais et Charles Belanger">
<meta name="description"
	content="Page  de gestion membre du système de gestion JardinCollectif.">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<style>
	table, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>
    <h1>Système de gestion de Jardin</h1><br>
    <form action="GererMembre" method="post">
    <h3>Supprimer un membre</h3>
    <table>
    	<thead>
    	<tr>
    		<th>Selection</th>
    		<th>UserId</th>
    		<th>Prenom</th>
    		<th>Nom</th>
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
						<td style="border: 1px solid black;"><INPUT TYPE="RADIO"
          				NAME="membreSelectionne" VALUE="<%= m.getNoMembre() %>"></td>
						<td><%=m.getNoMembre()%></td>
						<td><%=m.getPrenom()%></td>
						<td><%=m.getNom()%></td>
					</tr>
                        <% } %>
    	
    	</tbody>
    </table>
    <br>
    	<input type="SUBMIT" name="promouvoir" value="Promouvoir Admin">
        <input type="SUBMIT" name="supprimer" value="Supprimer">
    <br>
    <br>
    <h3>Gestion des demandes</h3>
    <table>
    	<thead>
    		<tr>
    			<th>Selection</th>
    			<th>UserId</th>
    			<th>NomLot</th>
    		</tr>
    	</thead>
    	<tbody>
        	<%
					    List<TupleDemande> demandes = JardinHelper.getJardinInterro(session).getGestionDemandeAssignation().getAllDemandes();
					        for (TupleDemande d : demandes)
					        { %>
			<tr>
				<td style="border: 1px solid black;"><INPUT TYPE="RADIO"
          				NAME="demandeSelectionne" VALUE="<%=d.getNoMembre()%>,<%=d.getNomLot()%>"></td>
          		<td><%= d.getNoMembre()%></td>
          		<td><%= d.getNomLot()%></td>
			</tr>
			<%} %>
    	</tbody>
    </table>
    <br>
        <input type="SUBMIT" name="accepterDemande" value="Accepter">
        <input type="SUBMIT" name="refuserDemande" value="Refuser">
    
    </form>
</body>
    <jsp:include page="/WEB-INF/messageErreur.jsp" />
    <BR>
        <a href="Logout">Sortir</a>
    <BR>
    Date et heure : <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
    </html>