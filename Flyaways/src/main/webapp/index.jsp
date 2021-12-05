<html>
<%@include file="./commonstyles.jsp" %>

<body>
	<h1 style="color: #999999; font-family: arial, sans-serif;font-weight: bold;">Fly with Flyaway </h1>
	<div>
		<a href="${pageContext.request.contextPath}/admin" style="hover: #00ff00;color:black; border-bottom: 1px solid;">Admin</a> 
	</div>
	<br> 
	<br>

	<div>
 		<form action="search" method ="post"> 
 		
			<input type="text" id="fromCity"name="fromCity" style="border-radius:30" 	placeholder="From City">
			<input type="text" id="toCity" 	name="toCity" 		placeholder="To City">
			<input type="text" id="airline" name="airline" 		placeholder="Airline">
			<input type="date" id="travelDate"name="travelDate" placeholder="Travel Date">
			
			<button type="submit">Search</button> &nbsp;
		</form>
		
	</div>
	<div>
		<form action="" method="post" > 
		<table>
		  <tr style="background-color:green;">

		  	<th>Route Id</th>
		    <th>From City</th>
		    <th>To City</th>
		    <th>Airline</th>
		      <th>Price</th>
		    <th>Action</th>
		  </tr>
		  <c:forEach items="${routes}" var="route">
		    <tr>
		      <td>${route.routeId}</td>
		      <td>${route.fromCity}</td>
		      <td>${route.toCity}</td>
		      <td>${route.airline}</td>
		      <td>&#36;${route.price}</td>
		      <td><a href="book?routeId=<c:out   value='${route.routeId}'/>">Book</a></td>    	       
		    </tr>
		  </c:forEach>
		</table>
		</form>
	</div>
	
<body>
</html>