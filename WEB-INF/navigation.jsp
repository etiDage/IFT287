<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="navbar-collapse collapse">
    	<ul class="nav navbar-nav">
	<%
	    if (session.getAttribute("admin") != null)
	    {
	%>
			<li><a class="nav-item nav-link" href="GererMembre">G�rer les membres</a></li>
			<li><a class="nav-item nav-link" href="AjouterLot">G�rer les lots</a></li>
			
	<%
	    }
	%>
	<%
	    if (session.getAttribute("admin") != null)
	    {
	%>
			<li><a class="nav-item nav-link" href="AjoutPlante">G�rer les plantes</a></li>
	<%
	    }
	%>
			<li><a class="nav-item nav-link" href="PlanterPlant">G�rer plants</a></li>
			<li><a class="nav-item nav-link" href="Demande">Faire demande</a></li>
		
		</ul>
		</div>
		<div class="navbar-collapse collapse justify-content-end">
		<ul class="nav navbar-nav navbar-right">
			<li><a class="nav-item nav-link" href="Logout">D�connexion</a></li>
		</ul>
	</div>
</nav>
