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
    <form action="AjouterLot" method="POST">
        Nom Lot :<input type="Text" name="nomLot" 
        value="<%= (request.getAttribute("nomLot") !=null) ? request.getAttribute("nomLot") : "" %>"> <br>
        Nombre de membres maximum :<input type="number" name="nbMaxMembre" 
        value="<%= (request.getAttribute("nbMaxMembre") !=null) ? request.getAttribute("nbMaxMembre") : "" %>"> <br>
        <br><input type="SUBMIT" name="AjouterLot" value="Soumettre"><br>
    </form>
    </body>
    
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<a href="Logout">Sortir</a>
<BR>
</html>