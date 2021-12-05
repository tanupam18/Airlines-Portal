<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./commonstyles.jsp" %>
</head>
<body>
<h1>fly with flyaway</h1>
<h2>Registration form</h2>

	<div>
 		<form class="form" action="${pageContext.request.contextPath }/payment-process" method ="post"> 
 			<input type="hidden" id="routeId"name="routeId" value="${route.routeId}" STYLE="color:lightblue;" >
			<input type="hidden" id="fromCity"name="fromCity" value="${route.fromCity}" STYLE="color:lightblue;">
			<input type="hidden" id="toCity" 	name="toCity" value="${route.toCity}" STYLE="color:lightblue;" >
			<input type="hidden" id="airline" name="airline" value="${route.airline}" STYLE="color:lightblue;" >
			<input type="hidden" id="price" name="price" value="${route.price}" STYLE="color:lightblue;"><br><br>
 		
 			<h3 style="color:Tomato;text-align: center;"> Your Flight Details <br>
 				From ${route.fromCity}
 			  	  To ${route.toCity}
 			  	  by ${route.airline}
 			      cost ${route.price} rupees<br><br>
			 </h3>
			<h4> To Book Your Flight - Please register below </h4>
 	
 			<div class="input-container ic1">
 			<label for="firstname" class="placeholder"><b>First name</b></label></br>
        <input id="firstname" class="input" type="text" placeholder=" " />
        <div class="cut"></div>
        
      </div>		
 		
 		<div class="input-container ic2">
 		<label for="lastname" class="placeholder"><b>Last name</b></label></br>
        <input id="lastname" class="input" type="text" placeholder=" " />
        <div class="cut"></div>
        
      </div>
      <div class="input-container ic2">
      	<label for="email" class="placeholder"><b>Email</b></label><br>
        <input id="email" class="input" type="text" placeholder=" " />
        <div class="cut"></div>
        
      </div>
      <div class="input-container ic2">
      	<label for="Phone No" class="placeholder"><b>Phone No</b></label></br>
        <input id="Phone No" class="input" type="text" placeholder=" " />
        <div class="cut"></div>
        &nbsp;&nbsp;&nbsp;
      </div>
      	<div class="input-container ic2">
      	<label for="password" class="placeholder"><b>Password</b></label></br>
        <input id="Password" class="input" type="password" placeholder=" "/>&nbsp;&nbsp;&nbsp;
        <div class="cut"></div>
      </div>
 		<div>
      		<br/><button type="submit" class="submit">Register & Pay</button>
    	</div>
    	<div><a  href="${pageContext.request.contextPath}/"><b>Cancel</b></a> <br></div>
 			<!-- First Name<input type="text" id="firstName"name="firstName" placeholder="First Name" required><br>
 			Last Name <input type="text" id="lastName"name="lastName" placeholder="Last Name" required><br>
			e-mail Id <input type="email" id="emailId"name="emailId" placeholder="example@gmail.com" required><br>
			Phone No  <input type="text" id="phoneNo"name="phoneNo" placeholder="Phone No" required><br>
			Password  <input type="password" id="password" name="password" placeholder="password" required><br><br> -->
			<%-- <button type="submit" class="submit">Register & Pay </button> &nbsp;
			<a href="${pageContext.request.contextPath}/">Cancel</a> <br> --%>
		</form>	
		
 	</div>
</body>
</html>