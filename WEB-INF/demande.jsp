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
    <h2>faire une demande</h2><br>
    UserId : <%= session.getAttribute("userID") %>
    <br>
    <form action="demande" method="POST">
        Nom de Lot : <select NAME="nomLot">
            <% List<TupleLot> nomLots = JardinHelper.getJardinInterro(session).getGestionLot().getAllLot();
            for(int i=0; i < nomLots.size(); i++)
            {
            %>
            <option value="<%=nomLots.get(i).getNomLot()%>"> <%=nomLots.get(i).getNomLot()%> </option>
            <%} %>
        </select> <br>
        <INPUT TYPE="SUBMIT" NAME="demander" VALUE="Demander">
    </form>
    </body>
    <jsp:include page="/WEB-INF/messageErreur.jsp" />
    <BR>
        <a href="Logout">Sortir</a>
    </html>