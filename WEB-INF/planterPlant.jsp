<%@ page import="java.util.*,java.text.*,jardinServlet.*,JardinCollectif.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>IFT287 - Système de gestion de Jardin Collectif</title>
<meta name="author" content="Etienne Dagenais et Charles Belanger">
<meta name="description"
	content="Page  d'ajout de plant du système de gestion JardinCollectif.">

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
    <h1>Système de gestion de Jardin</h1><br>
    <h2>ajouter un plant</h2><br>
    UserId : <%= session.getAttribute("userID") %>
    <br>
    <form action="PlanterPlant" method="POST">
        Nom de Lot :<% int nomLotsSize = JardinHelper.getJardinInterro(session).getGestionLot().getAllLot().size();
            if (nomLotsSize> 0)
            {
            %>
         <select NAME="nomLot">
            <% List<TupleLot> nomLots = JardinHelper.getJardinInterro(session).getGestionLot().getAllLot();
            for(int i=0; i < nomLots.size(); i++)
            {
            %>
            <option value="<%=nomLots.get(i).getNomLot()%>"> <%=nomLots.get(i).getNomLot()%> </option>
            <%} 
            %>
        </select>
        <%}%> <br>
       Nom de Plante : <% int nomPlantesSize = JardinHelper.getJardinInterro(session).getGestionPlante().getAllPlante().size();
            if (nomPlantesSize> 0)
            {
            %>
         <select NAME="nomPlante">
            <% List<TuplePlante> nomPlantes = JardinHelper.getJardinInterro(session).getGestionPlante().getAllPlante();
            for(int i=0; i < nomPlantes.size(); i++)
            {
            %>
            <option value="<%=nomPlantes.get(i).getNomPlante()%>"> <%=nomPlantes.get(i).getNomPlante()%> </option>
            <%} 
            %>
            </select>
            <% }%> <br>
        Nombre d'exemplaire: <input type="TEXT" name="nbExemplaire"
         value="<%= (request.getAttribute("nbExemplaire") !=null) ? 
            request.getAttribute("nbExemplaire") 
            : "" %>"> <br> <br>
            <INPUT TYPE="SUBMIT" NAME="ajouterPlant"VALUE="Soumettre">
                <br> <br>
            <h2>Recolter un plant</h2><br>
            <table
    style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
    border="1" cellspacing="2" cellpadding="2">
      <tbody>
    <%-- titre des colonnes --%>
        <tr>
        <td style="vertical-align: top;">Sélection<br></td>
        <td style="vertical-align: top;">Lot<br></td>
        <td style="vertical-align: top;">Plante<br></td>
        <td style="vertical-align: top;">Date Plantaison<br></td>
        <td style="vertical-align: top;">Date recolte<br></td>
        </tr>
        <% List<String> affichagePlantsLots = JardinHelper.getJardinInterro(session).getGestionInterrogation().listerPlantsLot();
            for(int i=0; i < affichagePlantsLots.size(); i=i+4)
            {
                if(!affichagePlantsLots.get(i+1).equals("aucune")){
                
        %>
        <tr>
        <td style="vertical-align: top;"><INPUT TYPE="RADIO"
          NAME="plantSelectionne" VALUE="<%= affichagePlantsLots.get(i) %>,<%= affichagePlantsLots.get(i+2)%>"><br></td>
        <td style="vertical-align: top;"><%= affichagePlantsLots.get(i) %><br></td>
        <td style="vertical-align: top;"><%= affichagePlantsLots.get(i+1) %><br></td>
        <td style="vertical-align: top;"><%= affichagePlantsLots.get(i+2) %><br></td>
        <td style="vertical-align: top;"><%= affichagePlantsLots.get(i+3) %><br></td>
        </tr>
<%
    }
      }
%>
      </tbody>
    </table>
    <BR>
        <INPUT TYPE="SUBMIT" NAME="recolter" VALUE="Recolter">
        </form>
    </body>
    <jsp:include page="/WEB-INF/messageErreur.jsp" />
    <BR>
        <a href="Logout">Sortir</a>
    <BR>
    Date et heure : <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
    </html>