<%@ page import="java.util.*,java.text.*,jardinServlet.*,JardinCollectif.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<title>IFT287 - Système de gestion de Jardin Collectif</title>
<meta name="author" content="Etienne Dagenais et Charles Belanger">
<meta name="description"
	content="page de soumissions d'un lot">

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
    <h1>Systeme de gestion de Jardin Collectif</h1>
    <h3>Ajout d'un lot</h3>
    <form action="GererLot" method="POST">
        Nom Lot :<input type="Text" name="nomLot" 
        value="<%= (request.getAttribute("nomLot") !=null) ? request.getAttribute("nomLot") : "" %>"> <br>
        Nombre de membres maximum :<input type="number" name="nbMaxMembre" 
        value="<%= (request.getAttribute("nbMaxMembre") !=null) ? request.getAttribute("nbMaxMembre") : "" %>"> <br>
        <br><input type="SUBMIT" name="ajouterLot" value="Soumettre"><br>
    </form>
    <br/>
    <br/>
    <h3>Supprimer un lot</h3>
    <form action="GererLot" method="POST">
    <% List<TupleLot> affichageLots = JardinHelper.getJardinInterro(session).getGestionLot().getAllLot();
    	if(affichageLots.isEmpty())
    	{
    	%>
    	<p>Il n'y a aucun lot</p>
    	<%} 
    	else
    	{%>
    	
    	
    <table style="border: 1px solid black;">
    <thead>
    	<tr style="border: 1px solid black;">
    		<th>Selection</th>
    		<th>Nom Lot</th>
    		<th>Nb max membre</th>
    	</tr>
    </thead>
    	<tbody>
    	<% 
    		for(TupleLot lot : affichageLots)
    		{%>
    		<tr style="border: 1px solid black;">
    			<td style="border: 1px solid black;"><INPUT TYPE="RADIO"
          		NAME="lotSelectionne" VALUE="<%= lot.getNomLot() %>"></td>
          		<td style="border: 1px solid black;"><%= lot.getNomLot() %></td>
          		<td style="border: 1px solid black;"><%= lot.getNbMaxMembre() %></td>
    		</tr>
    		<%} %>
    	</tbody>
    </table>	
    <br/>	
    <input type="SUBMIT" name="supprimer" value="Supprimer">
    <%} %>
    </form>
    </body>
    
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<a href="Logout">Sortir</a>
<BR>
</html>