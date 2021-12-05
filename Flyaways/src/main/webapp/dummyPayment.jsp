<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./commonstyles.jsp" %>
</head>
<body>
<h1>Flyaway - Your well deserved vacation a click away</h1>
<h2>Registration</h2>

	<div>
 		<form action="success" method ="post"> 
 		
 			<h4> Hello ${customer.firstName} ${customer.lastName}  </h4><br>
			
			<h4 style="color:Tomato;"> Your Flight Details <br>
 				From ${route.fromCity}
 			  	  To ${route.toCity}
 			  	  by ${route.airline}
 			      cost ${route.price} rupees<br><br>
			 </h4>
 	
 			<!--  pass key values for final confirmation screen -->
 			<input type="hidden" id="emailId"	name="emailId"  value="${customer.emailId}">		
 			<input type="hidden" id="routeId"	name="routeId" 	value="${route.routeId}">

 
 	
			<h4> Payment Details </h4>
 			<div class="input-container ic1">
 			<label for="Full Name" class="placeholder"Full Name"><b>Full Name</b></label></br>
        <input id="Full Name" class="input" type="text" placeholder=" " required/>
        <div class="cut"></div>
      </div>		
 		
 		<div class="input-container ic1">
 			<label for="Card Number" class="placeholder"><b>Card Number</b></label></br>
        <input id="Card Number" class="input" type="text" placeholder="1234-456-789" required/>
        <div class="cut"></div>
      </div>	
 		
 		<div class="input-container ic1">
 			<label for="expiryDate" class="placeholder"><b>Expiry Date</b></label></br>
        <input id="Card Number" class="input" type="text" placeholder="mm/yyyy" required/>
        <div class="cut"></div>
      </div>
      
      <div class="input-container ic1">
 			<label for="expiryDate" class="placeholder"><b>CVS number</b></label></br>
        <input id="cvsNo" class="input" type="text" placeholder="1234" required/>
        <div class="cut"></div>
      </div>			
 			
 			
 			<!-- Full Name 	<input type="text" id="fullName"name="fullName" placeholder="Fulle Name" required><br>
 			Card Number	<input type="text" id="cardNumber"name="cardNumber" placeholder="1234-456-789" required><br>
			Expiry Date <input type="text" id="expiryDate"name="expiryDate" placeholder="mm/yyyy" required><br>
			CVS number 	<input type="number" id="cvsNo"name="cvsNo" placeholder="1234" required><br> -->
			</br><button type="submit" class="submit"> Pay </button> &nbsp;
			<a href="${pageContext.request.contextPath}/"><b>Cancel</b></a> <br>
		</form>	
		
 	</div>
</body>
</html>