<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<style>
input {
	border-radius: 31px;
	height: 38px
}

h1 {
	text-align: center;
}

h2 {
	text-align: center;
}

h3 {
	text-align: right;
	STYLE ="background-color: red;
	"
}

h4 {
	text-align: center;
	STYLE ="color: Tomato;
	"
}

p {
	text-align: center;
}

div {
	text-align: center;
}

form {
	text-align: center;
}

button {
	background: #B9DFFF;
	color: #fff;
	border: 1pxsolid #eee;
	border-radius: 25px;
	box-shadow: 5px5px5px #eee;
	text-shadow: none;
	border-radius: 31px;
	height: 34;
	width: 90;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px; //
	background-color: yellow;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

.title {
	color: #eee;
	font-family: sans-serif;
	font-size: 36px;
	font-weight: 600;
	margin-top: 30px;
}

.subtitle {
	color: #eee;
	font-family: sans-serif;
	font-size: 16px;
	font-weight: 600;
	margin-top: 10px;
}

.input-container {
	height: 50px;
	position: relative;
	width: 100%;
}

.ic1 {
	margin-top: 40px;
}

.ic2 {
	margin-top: 30px;
}

.input {
	border-radius: 12px;
	border: 0;
	box-sizing: border-box;
	font-size: 18px;
	height: 100%;
	outline: 0;
	padding: 4px 20px 0;
}

.cut {
	border-radius: 10px;
	height: 20px;
	left: 20px;
	position: absolute;
	top: -20px;
	transform: translateY(0);
	transition: transform 200ms;
	width: 76px;
}

.cut-short {
	width: 50px;
}

placeholder {
	color: #65657b;
	font-family: sans-serif;
	left: 20px;
	line-height: 14px;
	pointer-events: none;
	position: absolute;
	transform-origin: 0 50%;
	transition: transform 200ms, color 200ms;
	top: 20px;
}

.input:focus ~ .placeholder, .input:not(:placeholder-shown) ~
	.placeholder {
	transform: translateY(-30px) translateX(10px) scale(0.75);
}

.input:not(:placeholder-shown) ~ .placeholder {
	color: #808097;
}

.input:focus ~ .placeholder {
	color: #dc2f55;
}

.submit {
	background-color: #08d;
	border-radius: 12px;
	border: 0;
	box-sizing: border-box;
	color: #eee;
	cursor: pointer;
	font-size: 18px;
	height: 50px; //
	outline: 0;
	text-align: center;
}

.submit:active {
	background-color: #06b;
}
</style>

<title><c:out value="${title}">Flayaway Home</c:out></title>
<h3 STYLE="background-color: red;">
	<c:out value="${error}"></c:out>
</h3>
<h3 STYLE="background-color: lightblue;">
	<c:out value="${info}"></c:out>
</h3>
<h3 STYLE="background-color: yellow;">
	<c:out value="${warn}"></c:out>
</h3>

<body>
<body
	style="background-color: lightblue; background-image: url(https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F28%2F2020%2F04%2F15%2Funited-airlines-zoom-sunset-UAZOOM0420.jpg);">
</body>
</html>