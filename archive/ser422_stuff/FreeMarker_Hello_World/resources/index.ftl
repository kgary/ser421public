<html>
<head><title>ViralPatel.net - FreeMarker Hello World</title>

<style>
body, input {
	font-family: Calibri, Arial;
	margin: 0px;
	padding: 0px;
}
#header h2 {
	color: white;
	background-color: #3275A8;
	height: 40px;
	padding: 5px 0 0 5px;
}
	
.datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:400px;max-width:100%;font-family:Calibri}
.datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
.datatable tr {border:1px solid #888}
.datatable tr.odd {background-color:#eee}
.datatable td {padding:2px;border:1px solid #888}
#content { padding 5px; margin: 5px; text-align: center}

fieldset { width: 300px; padding: 5px; margin-bottom: 0px; }
legend { font-weight: bold; }
</style>

<body>
<div id="header">
<H2>
	<a href="http://viralpatel.net"><img height="37" width="236" border="0px" src="http://viralpatel.net/blogs/wp-content/themes/vp/images/logo.png" align="left"/></a>
	FreeMarker Hello World
</H2>
</div>
<div id="content">
  
  
  <fieldset>
  	<legend>Add User</legend>
  <form name="user" action="hello" method="post">
  	Firstname: <input type="text" name="firstname" />	<br/>
  	Lastname: <input type="text" name="lastname" />	<br/>
  	<input type="submit" value="   Save   " />
  </form>
  </fieldset>
  <br/>
  <table class="datatable">
  	<tr>
  		<th>Firstname</th>  <th>Lastname</th>
  	</tr>
    <#list users as user>
  	<tr>
  		<td>${user.firstname}</td> <td>${user.lastname}</td>
  	</tr>
    </#list>
  </table>

</div>  
</body>
</html>  