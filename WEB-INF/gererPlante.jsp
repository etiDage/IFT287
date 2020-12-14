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
    <h1>Systeme de gestion de Jardin Collectif</h1>
    <form action="GererPlante" method="POST">
        Nom Plante :<input type="Text" name="nomPlante" 
        value="<%= (request.getAttribute("nomPlante") !=null) ? request.getAttribute("nomPlante") : "" %>"> <br>
        Temps de culture :<input type="Text" name="tempsDeCulture" 
        value="<%= (request.getAttribute("tempsDeCulture") !=null) ? request.getAttribute("tempsDeCulture") : "" %>"> <br>
        <br><input type="SUBMIT" name="ajoutePlante" value="Soumettre"><br>
        <br>
        <h2>Tableau des plantes</h2><br>
        <table
    style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
    border="1" cellspacing="2" cellpadding="2">
    <tbody>
        
        <%-- titre des colonnes --%>
        <tr>
        <td style="vertical-align: top;">Sélection<br></td>
        <td style="vertical-align: top;">nomPlante<br></td>
        <td style="vertical-align: top;">temp de Culture(en jour)<br></td>
        </tr>
        <% List<TuplePlante> nomPlantes = JardinHelper.getJardinInterro(session).getGestionPlante().getAllPlante();
            for(int i=0; i < nomPlantes.size(); i++)
            {  
            %>
            <tr>
                <td style="vertical-align: top;"><INPUT TYPE="RADIO"
                  NAME="planteSelectionne" VALUE="<%=nomPlantes.get(i).getNomPlante() %>"><br></td>
                <td style="vertical-align: top;"><%= nomPlantes.get(i).getNomPlante() %><br></td>
                <td style="vertical-align: top;"><%= nomPlantes.get(i).getTmpCulture() %><br></td>
                </tr>
            <%
      }
%>
</tbody>
</table>
    <INPUT TYPE="SUBMIT" NAME="retirer" VALUE="Retirer">
    </form>
    </body>
    
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<a href="Logout">Sortir</a>
<BR>
</html>