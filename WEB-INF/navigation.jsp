<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="navbar-collapse collapse">
    	<ul class="nav navbar-nav">
	<%
	    if (session.getAttribute("admin") != null)
	    {
	%>
			<li><a class="nav-item nav-link" href="GererMembre">Gérer les membres</a></li>
			<li><a class="nav-item nav-link" href="AjouterLot">Gérer les lots</a></li>
			
	<%
	    }
	%>
	<%
	    if (session.getAttribute("admin") != null)
	    {
	%>
			<li><a class="nav-item nav-link" href="AjoutPlante">Gérer les plantes</a></li>
	<%
	    }
	%>
			<li><a class="nav-item nav-link" href="PlanterPlant">Gérer plants</a></li>
			<li><a class="nav-item nav-link" href="Demande">Faire demande</a></li>
		
		</ul>
		</div>
		<div class="navbar-collapse collapse justify-content-end">
		<ul class="nav navbar-nav navbar-right">
			<li><a class="nav-item nav-link" href="Logout">Déconnexion</a></li>
		</ul>
	</div>
</nav>
