<%@ page import="java.util.*,java.text.*,jardinServlet.*,JardinCollectif.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>IFT287 - Système de gestion de Jardin Collectif</title>
<meta name="author" content="Etienne Dagenais et Charles Belanger">
<meta name="description"
	content="Page de sélection de membre du système de gestion JardinCollectif.">

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
    <h1>Système de gestion de Jardin</h1>
    <form action="SelectionMembre" method="GET">
        Id de membre : <input type="text" name="userId"
        <%
        String userId = (String) session.getAttribute("userId");
        if (userId == null)
        {
            userId = (String) request.getAttribute("userId");
            if(userId== null) userId= "";
        }
        %>
        VALUE="<%= userId %>"> <br>
        <br>
        <input type="SUBMIT" value="Soumettre">
        </form>
    
    <jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
    <a href="Logout">Sortir</a>
<BR>
</body>
    </html>