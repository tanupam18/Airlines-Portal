package com.simplilearn.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.dao.AdminDetailsDao;
import com.simplilearn.dao.AirlineDetailsDao;
import com.simplilearn.dao.CustomerDetailsDao;
import com.simplilearn.dao.PlaceDetailsDao;
import com.simplilearn.dao.RouteDetailsDao;
import com.simplilearn.model.Admin;
import com.simplilearn.model.Airline;
import com.simplilearn.model.Customer;
import com.simplilearn.model.Place;
import com.simplilearn.model.Route;

@WebServlet("/")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RouteDetailsDao 	RouteDetailsDao;
	private CustomerDetailsDao CustomerDetailsDao;
	private AdminDetailsDao 	AdminDetailsDao;
	private AirlineDetailsDao  AirlineDetailsDao;
	private PlaceDetailsDao	PlaceDetailsDao;
	
	
	//Lets initialize our database object
	public void init(ServletConfig config) throws ServletException {
		RouteDetailsDao 	= new RouteDetailsDao();
		CustomerDetailsDao = new CustomerDetailsDao();
		AdminDetailsDao	= new AdminDetailsDao();
		PlaceDetailsDao	= new PlaceDetailsDao();
		AirlineDetailsDao	= new AirlineDetailsDao();
		
		if (RouteDetailsDao == null) {
			System.out.println ("Error : Database issues - make sure you run the SQL setup");

		}
	}
	
	//
	// Main Handler routine for handling all events
	// 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		System.out.println("RoteManagement=>doGet action:" + action);
		try {
			switch (action) {
	
			case "/admin":
				formtoCall(request, response,"validate-admin-form.jsp");
				break;
			// validate admin password.
			case "/admin-process":
				adminRoute(request, response);
				break;
			case "/changePwd":
				formtoCall(request, response,"change-password-form.jsp");
				break;
			// change admin password.
			case "/password-process":
				passwordProcess(request, response);
				break;
				
			// Register Customer before dummy payment
			case "/payment-process":
				paymentProcess(request, response);
				break;
				
			// Insert airline Route 
			case "/insert":
				insertRoute(request, response);
				break;
			// Delete  airline Route 
			case "/delete":
				deleteRoute(request, response);
				break;
			case "/edit":
				editForm(request,response);
				break;
			// update  airline Route 
			case "/update":
				updateRoute(request, response);
				break;
			// search  airline Routes 	
			case "/search":
				listRoutesBy(request, response,"index.jsp");
				break;
			// Final message to customer after payment
			case "/success":
				successProcess(request, response);
				break;
			// When customer decided to book a ticket
			case "/book":	
				int routeId = Integer.parseInt(request.getParameter("routeId"));
				Route aRoute = RouteDetailsDao.getByKey(routeId);
				System.out.println(aRoute.toString());
				request.setAttribute("route", aRoute);
				formtoCall(request, response,"register-user-form.jsp");
				break;
			case "/admin-back":
				adminRefesh(request,response);
				break;
				
		    //Catch all, route back to home page
			default:
				listRoutes(request, response,"index.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//
	// general method to call any jsp page
	//
	private void formtoCall(HttpServletRequest request, HttpServletResponse response,String jspName)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspName);
		dispatcher.forward(request, response);
		//response.sendRedirect(jspName); 
	}
	
	//
	// method to validate admin credential and display error
	//

	private void adminRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		// not a normal path
	    if(request.getParameter("userName")==null || 
	    			request.getParameter("userName").equals("")) {
	    		System.out.println ("Shouldnt acess direcly coming to admin-prrocec page - redirec to home");
	    		List<Route> listRoutes = RouteDetailsDao.listOfAll();
	    		request.setAttribute("routes", listRoutes);
	    		formtoCall(request,response,"index.jsp");
	    }
	    
	    String userName	=request.getParameter("userName");  
	    String password	=request.getParameter("password");
		Admin admin = AdminDetailsDao.getByKey(userName);
		

	    if(password.equals(admin.getPassword())){ 	
	    	request.setAttribute("title", "Admin - Add Route");
	    	
	    	adminRefesh(request,response);
	    	
			

	    }  
	   
	    //should we invalidate the session ?
        request.setAttribute("error", "Please Check - Invalid user or password");      
	    formtoCall(request, response,"validate-admin-form.jsp");
	}
	

	private void adminRefesh(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		List<Airline> listAirlines  = AirlineDetailsDao.listOfAll();
		List<Place> listPlaces 		= PlaceDetailsDao.listOfAll();
		List<Route> listRoutes 		= RouteDetailsDao.listOfAll();
		
		request.setAttribute("routes", listRoutes);
		request.setAttribute("airlines", listAirlines);
		request.setAttribute("places", listPlaces);
		
		formtoCall(request, response,"add-route-form.jsp");
	}
	
	//
	// Validate and reset admin password 
	//
	
	private void passwordProcess(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		String userName		=request.getParameter("userName");  
		String oPassword	=request.getParameter("oldPassword");
		String nPassword	=request.getParameter("newPassword");
		
		Admin admin = AdminDetailsDao.getByKey(userName);
		if (admin !=null && oPassword.equals(admin.getPassword())){ 	
			admin.setPassword(nPassword);
			AdminDetailsDao.update(admin); 
			request.setAttribute("info", "Password changed successfully - relogin");      
			formtoCall(request, response,"validate-admin-form.jsp");
			System.out.println(admin.toString());    
	    } 
		
	    //should we invalidate the session ?
        request.setAttribute("error", "Please Check - Invalid user or password");      
	    formtoCall(request, response,"validate-admin-form.jsp");
	}
	

	//
	// Customer Registration and process to dummy payment
	//
	private void paymentProcess(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		Customer customer = null ;
		String firstName=request.getParameter("firstName");  
		String lastName	=request.getParameter("lastName");
		String emailId	=request.getParameter("emailId");
		String phoneNo	=request.getParameter("phoneNo");
		String password	=request.getParameter("password");
		
		// avoid duplicate creation of customer if e-mail is already registered.
		customer = CustomerDetailsDao.getByKey(emailId);
		if (customer == null) {
			customer = new Customer();
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmailId(emailId);
			customer.setPhoneNo(phoneNo);
			customer.setPassword(password);
			CustomerDetailsDao.insert(customer);
		}
		
		System.out.println(customer.toString());

		Route route = new Route();
		
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		
		String fromCity = request.getParameter("fromCity");
		String toCity 	= request.getParameter("toCity");	
		String airline 	= request.getParameter("airline");
		Long	price 	= Long.parseLong(request.getParameter("price"));
		
		route.setRouteId(routeId);
		route.setFromCity(fromCity);
		route.setToCity(toCity);
		route.setAirline(airline);
		route.setPrice(price);
		System.out.println(route.toString());
	
		request.setAttribute("route", route);
		request.setAttribute("customer", customer);
  
		formtoCall(request, response,"dummyPayment.jsp");
	
	}
	
	//
	// success-process(request, response,"success.jsp");
	//
	
	private void successProcess(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		Customer customer = null ;
		Route 		route = null;

		String emailId	=request.getParameter("emailId");
		
		// avoid duplicate creation of customer if e-mail is already registered.
		customer = CustomerDetailsDao.getByKey(emailId);
		if (customer == null) {
			System.out.println( "Email id is empty - probelm: " + emailId);
		}	
		System.out.println(customer.toString());
		
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		
		route = RouteDetailsDao.getByKey(routeId);
		if (route == null) {
			System.out.println( "Route id is empty - probelm: " + routeId);
		}
		System.out.println(route.toString());
	
		request.setAttribute("route", route);
		request.setAttribute("customer", customer);
  
		formtoCall(request, response,"success.jsp");
	}
	
	//
	// List all the routes
	//
	private void listRoutes(HttpServletRequest request, HttpServletResponse response,String jspToList)
			throws SQLException, IOException, ServletException {
		System.out.println ("in List Route");
		if (RouteDetailsDao == null ){
			System.out.println ("routeDao is null");
			RouteDetailsDao = new RouteDetailsDao();
		}
		List<Route> listRoutes = RouteDetailsDao.listOfAll();
		request.setAttribute("routes", listRoutes);
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspToList);
		dispatcher.forward(request, response);
		//response.sendRedirect("user-list.jsp"); 
	}
	
	private void listRoutesBy(HttpServletRequest request, HttpServletResponse response,String jspToList)
			throws SQLException, IOException, ServletException, ParseException {
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String fromCity = request.getParameter("fromCity");
		String toCity 	= request.getParameter("toCity");	
		String airline 	= request.getParameter("airline");
		Date fromDate = null;
		
		System.out.println (request.getParameter("travelDate"));
		if (!request.getParameter("travelDate").isEmpty() ) {
			   fromDate = dateFormat.parse(request.getParameter("travelDate"));
		}
	
		if (RouteDetailsDao == null ){
			System.out.println ("routeDao is null");
			RouteDetailsDao = new RouteDetailsDao();
		}

		List<Route> listRoutes = RouteDetailsDao.listOfAllBy(fromCity,toCity,airline,fromDate);
		request.setAttribute("routes", listRoutes);
		
		if (listRoutes.size() == 0) {
			request.setAttribute("warn", "No data found , admin to create or just press search"); 
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspToList);
		dispatcher.forward(request, response);
		//response.sendRedirect("user-list.jsp"); 
	}

	//
	// Admin main edit route form
	//
	private void editForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		if (RouteDetailsDao == null ){
			System.out.println ("routeDao is null");
			RouteDetailsDao = new RouteDetailsDao();
		}
		Route aRoute = RouteDetailsDao.getByKey(routeId);
		System.out.println(aRoute.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-route-form.jsp");
		request.setAttribute("route", aRoute);
		dispatcher.forward(request, response);

	}

	//
	// insertRoute
	//
	private void insertRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		// not a normal path - lets take one parm to check
	    if(request.getParameter("price")==null || 
	    			request.getParameter("price").equals("")) {
	    		System.out.println ("Shouldnt acess direcly coming to admin-prrocec page - redirec to home");
	    		List<Route> listRoutes = RouteDetailsDao.listOfAll();
	    		request.setAttribute("routes", listRoutes);
	    		formtoCall(request,response,"index.jsp");
	    }

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
//		String fromCity = request.getParameter("fromCity");
//		String toCity 	= request.getParameter("toCity");	
		
		Date   fromDate = dateFormat.parse(request.getParameter("fromDate"));
		Date   toDate 	= dateFormat.parse(request.getParameter("toDate"));
		Long	price 	= Long.parseLong(request.getParameter("price"));
		
		
		Airline airline	= AirlineDetailsDao.getByKey(Integer.parseInt(request.getParameter("airlineId")));
		String code 	= airline.getCode();
		
		Place place		= PlaceDetailsDao.getByKey(Integer.parseInt(request.getParameter("placeId")));
		String sCity = place.getSourceCity();
		String tCity = place.getDestinationCity();
				
		Route art = new Route();
	//	art.setFromCity(fromCity);
	//	art.setToCity(toCity);
		art.setFromCity(sCity);
		art.setToCity(tCity);

		art.setAirline(code);
		art.setFromDate(fromDate);
		art.setToDate(toDate);
		art.setPrice(price);
		
		System.out.println(art.toString());
		System.out.println(place.toString());
		System.out.println(airline.toString());
		

		if (RouteDetailsDao == null ){
			System.out.println ("routeDao is null");
			RouteDetailsDao = new RouteDetailsDao();
		}
		RouteDetailsDao.inser(art);
    	List<Route> listRoutes = RouteDetailsDao.listOfAll();
    	request.setAttribute("routes", listRoutes);
    	
    	List<Airline> listAirlines = AirlineDetailsDao.listOfAll();
    	request.setAttribute("airlines", listAirlines);
    	
    	List<Place> listPlaces = PlaceDetailsDao.listOfAll();
    	request.setAttribute("places", listPlaces);
    	
		formtoCall(request, response,"add-route-form.jsp");
		//response.sendRedirect("list");
	}
	
	//
	// Update Route
	//

	private void updateRoute(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, Exception {
		
		
		// not a normal path - lets take one parm to check
	    if(request.getParameter("price")==null || 
	    			request.getParameter("price").equals("")) {
	    		System.out.println ("Shouldnt acess direcly coming to admin-prrocec page - redirec to home");
	    		List<Route> listRoutes = RouteDetailsDao.listOfAll();
	    		request.setAttribute("routes", listRoutes);
	    		formtoCall(request,response,"index.jsp");
	    }
		
		System.out.println (request.getParameter("routeId"));
		
		int routeid = Integer.parseInt(request.getParameter("routeId"));
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String fromCity = request.getParameter("fromCity");
		String toCity 	= request.getParameter("toCity");
		String airline 	= request.getParameter("airline");
		Date   fromDate = dateFormat.parse(request.getParameter("fromDate"));
		Date   toDate 	= dateFormat.parse(request.getParameter("toDate"));
		Long	price 	= Long.parseLong(request.getParameter("price"));
		
		Route art = new Route();
		art.setRouteId(routeid);
		art.setFromCity(fromCity);
		art.setToCity(toCity);
		art.setAirline(airline);
		art.setFromDate(fromDate);
		art.setToDate(toDate);
		art.setPrice(price);
		
		System.out.println(art.toString());
		
		//Route aroute = new Route(routeid,fromCity,toCity,airline,capacity,fromDate, toDate,price);
	
		RouteDetailsDao.update(art);
    	List<Route> listRoutes = RouteDetailsDao.listOfAll();
		request.setAttribute("routes", listRoutes);
		formtoCall(request, response,"add-route-form.jsp");
	}

	//
	// Delete route
	//
	private void deleteRoute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		if (RouteDetailsDao == null ){
			System.out.println ("routeDao is null");
			RouteDetailsDao = new RouteDetailsDao();
		}
		RouteDetailsDao.delete(routeId);
		adminRefesh(request,response);

	}	
	
}
