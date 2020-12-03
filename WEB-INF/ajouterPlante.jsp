<%@ page import="java.util.*,java.text.*,jardinServlet.*,JardinCollectif.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>IFT287 - Système de gestion de Jardin Collectif</title>
<meta name="author" content="Etienne Dagenais et Charles Belanger">
<meta name="description"
	content="page de soumissions d'une plante dans un lot">

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
    <h1>Syst�me de gestion de biblioth�que</h1>
    <form action="Plantation" method="POST">
        Nom Plante :<input type="Text" name="nomPlante" 
        value="<%= (request.getAttribute("nomPlante") !=null) ? request.getAttribute("nomPlante") : "" %> <br>
        Temps de culture :<input type="Text" name="tempsDeCulture" 
        value="<%= (request.getAttribute("tempsDeCulture") !=null) ? request.getAttribute("tempsDeCulture") : "" %> <br>
        <br><input type="SUBMIT" name="ajoutePlante" value="Soumettre"><br>
        <br><input type="SUBMIT" name="selectionMembre" value="sélection d'un membre"><br>
    </form>
    </body>
    
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<a href="Logout">Sortir</a>
<BR>
</html>