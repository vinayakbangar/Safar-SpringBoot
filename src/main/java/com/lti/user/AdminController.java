package com.lti.user;

import java.text.SimpleDateFormat;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Admin;
import com.lti.bean.Bus;
import com.lti.bean.BusTypeRate;
import com.lti.bean.Driver;
import com.lti.bean.Feedback;
import com.lti.bean.Route;
import com.lti.bean.Ticket;
import com.lti.bean.Timetable;
import com.lti.bean.User;
import com.lti.exception.HrException;
import com.lti.service.AdminService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin/v1")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;
//==========================================================================Users
	//Display all Users List
	// http://localhost:8080/admin/v1/usersList	
	@GetMapping(value = "/usersList",produces = "application/json")
	public List<User> getUserList(){
		List<User> userList=null;
		try {
			userList=adminService.getUserList();
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return userList;
	}
//==========================================================================Admin
	//Get Login Details
	//http://localhost:8080/admin/v1/adminLogin/{email}/{psw}
	@GetMapping(value = "/adminLogin/{email}/{psw}")
	public List<Admin> getAdminLoginDetail(@PathVariable(value = "email") String email,@PathVariable(value = "psw") String psw) {
	List<Admin> admin = null;
		try {
			admin = adminService.getAdminLoginDetail(email, psw);
			} catch (HrException e) {
			System.out.println("Error in User");
		e.printStackTrace();
		}
	return admin;
	}
//=====================================================================Feedback	
	//Display all Feedbacks List
	// http://localhost:8080/admin/v1/feedbacksList	
	@GetMapping(value = "/feedbacksList",produces = "application/json")
	public List<Feedback> getFeedbackList(){
		List<Feedback> feedbackList=null;
		try {
			feedbackList=adminService.getFeedbackList();
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return feedbackList;
	}
//===========================================================================Bus
	//Display all Buses List
	// http://localhost:8080/admin/v1/busesList
	@GetMapping(value = "/busesList",produces = "application/json")
	public List<Bus> getBusList(){
		List<Bus> busesList=null;
		try {
			busesList=adminService.getBusList();
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return busesList;
	}
	
	//Add Bus
	// http://localhost:8080/admin/v1/addBus/{btId}
	@PostMapping(value="/addBus/{btId}", consumes="application/json")
	public boolean addBus(@RequestBody Bus bus ,@PathVariable(value = "btId") long btId) {	
	boolean newRecord = false;
	BusTypeRate busType=new BusTypeRate();
	try {
		busType = adminService.getBusTypeById(btId);
		bus.setBusTypeRate(busType);
		newRecord=adminService.addBus(bus);
	} catch (HrException e) {
		System.out.println("Error in adding Bus");
		e.printStackTrace();
	}	
	return newRecord;
	}
	
	//Delete Bus
	// http://localhost:8080/admin/v1/deleteBus/{bId}
	@DeleteMapping("/deleteBus/{bId}")
	public boolean deleteBus(@PathVariable String bId) {
		boolean Record = false;
		try {
			Record=adminService.deleteBus(bId);
		} 
		catch (HrException e) {
			System.out.println("Error in deleting routes");
			e.printStackTrace();
		}
		return Record;
	}

//===========================================================================Tickets
	//Display all Tickets List
	// http://localhost:8080/admin/v1/ticketsList
	@GetMapping(value = "/ticketsList",produces = "application/json")
	public List<Ticket> getTicketList(){
		List<Ticket> ticketList=null;
		try {
			ticketList=adminService.getTicketList();
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return ticketList;
	}
//=========================================================================TimeTable	
	//Display Timetable
	// http://localhost:8080/admin/v1/timetable
	@GetMapping(value = "/timetable",produces = "application/json")
	public List<Timetable> getTimetable(){
		List<Timetable> timetable=null;
		Timetable t1= new Timetable();
		try {
			timetable=adminService.getTimetable();
			t1=timetable.get(0);
			String newString = new SimpleDateFormat("HH:mm").format(t1.getDepartureDateTime()); 
			System.out.println(newString);
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return timetable;
	}
	
	//Add Timetable
	//addTimetable(String timetableId,String dateTime,Bus bus,Route route,Driver driver)
	//http://localhost:8080/admin/v1/addTimetable/{tid}/{dateTime}/{busid}/{routeid}/{driverid}
	@GetMapping(value = "/addTimetable/{tid}/{dateTime}/{busid}/{routeid}/{driverid}")
	public boolean addTimetable(@PathVariable(value = "tid") String tid,
			@PathVariable(value = "dateTime") String dateTime,
			@PathVariable(value = "busid") String busid,
			@PathVariable(value = "routeid") long routeid,
			@PathVariable(value = "driverid") String driverid ) {
			boolean record=false;
			try {
				Bus bus=adminService.getBusById(busid);
				Route route=adminService.getRouteById(routeid);
				Driver driver=adminService.getDriverById(driverid);
				record=adminService.addTimetable(tid, dateTime, bus, route, driver);
			} catch (HrException e) {
				System.out.println("Error at controller");
				e.printStackTrace();
			}
			
			return record;
	}
	
	@GetMapping(value = "/updateTimetable/{tid}/{dateTime}/{busid}/{routeid}/{driverid}")
	public boolean updateTimetable(@PathVariable(value = "tid") String tid,
			@PathVariable(value = "dateTime") String dateTime,
			@PathVariable(value = "busid") String busid,
			@PathVariable(value = "routeid") long routeid,
			@PathVariable(value = "driverid") String driverid ) {
			boolean record=false;
			try {
				Bus bus=adminService.getBusById(busid);
				Route route=adminService.getRouteById(routeid);
				Driver driver=adminService.getDriverById(driverid);
				record=adminService.updateTimetable(tid, dateTime, bus, route, driver);
			} catch (HrException e) {
				System.out.println("Error at controller");
				e.printStackTrace();
			}
			
			return record;
	}
	
	//http://localhost:8080/admin/v1/findTimeTable/TT6
			@GetMapping(value="/findTimeTable/{id}")
			public Timetable findTimeTable(@PathVariable String id)
			{
				Timetable timeTable = null;
				try {
					timeTable = adminService.findTimeTable(id);
				} catch (HrException e) {
					System.out.println("error while finding route id");
					e.printStackTrace();
				}
				return timeTable;
			}
	
//-------------------------------------------------------------Route
	
	//Get Routes List
	// http://localhost:8080/admin/v1/routesList
	@GetMapping(value = "/routesList",produces = "application/json")
	public List<Route> getRouteList(){
		List<Route> routeList=null;
		try {
			routeList=adminService.getRouteList();
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return routeList;
	}
	
	//Add Route
	// http://localhost:8080/admin/v1/addRoute
	@PostMapping(value="/addRoute", consumes="application/json")
	public boolean addRoute(@RequestBody Route route) {	
	boolean newRecord = false;
	try {
		newRecord = adminService.addRoute(route);
		
	} catch (HrException e) {
		System.out.println("Error occured at adding route Controller");
		e.printStackTrace();
	}
		return newRecord;
	}
	
	//Update route
	//http://localhost:8080/admin/v1/updateRoute/227
		@PutMapping(value="/updateRoute/{id}",consumes="application/json")
		public void updateRoute(@PathVariable long id, @RequestBody Route route)
		{
			route.setRouteId(id);
			try {
				adminService.updateRoutes(route);
			} catch (HrException e) {
				System.out.println("error while updating");
				e.printStackTrace();
			}

		}
	
	//Delete Route BY ID
	// http://localhost:8080/admin/v1/deleteRoute/{routeId}
	@DeleteMapping("/deleteRoute/{routeId}")
	public boolean deleteRoutes(@PathVariable Long routeId) {
		boolean Record = false;
		try {
			Record=adminService.deleteRoute(routeId);
		} 
		catch (HrException e) {
			System.out.println("Error in deleting routes");
			e.printStackTrace();
		}
		return Record;
	}
	
	//http://localhost:8080/admin/v1/findRoute/111
		@GetMapping(value="/findRoute/{rid}")
		public Route findRouteById(@PathVariable long rid)
		{
			Route route = null;
			try {
				route = adminService.getRouteById(rid);
			} catch (HrException e) {
				System.out.println("error while finding route id");
				e.printStackTrace();
			}
			return route;
		}

//-------------------------------------------------------------BusTypeRate
	//Get BusType List
	// http://localhost:8080/admin/v1/busTypeList
	@GetMapping(value = "/busTypeList",produces = "application/json")
	public List<BusTypeRate> getBusTypeList(){
			List<BusTypeRate> busTypeList=null;
			try {
				busTypeList=adminService.getBusTypeList();
			} catch (HrException e) {
				System.out.println("Error occured at Controller");
				e.printStackTrace();
			}
			return busTypeList;
		}
		
	
	//Update route
	// http://localhost:8080/admin/v1/updateBusType
	@PutMapping(value = "/updateBusType", consumes = "application/json")
	public void updateBusType(@RequestBody BusTypeRate busType) {
		try {
			adminService.updateBusType(busType);
			} catch (HrException e) {
				System.out.println("Error in updating BusType");
				e.printStackTrace();
			}
		}
		
//-------------------------------------------------------------Driver
	//Get Driver List
	// http://localhost:8080/admin/v1/driverList
	@GetMapping(value = "/driverList",produces = "application/json")
		public List<Driver> getDriverList(){
				List<Driver> driverList=null;
				try {
					driverList=adminService.getDriverList();
				} catch (HrException e) {
					System.out.println("Error occured at Controller");
					e.printStackTrace();
				}
				return driverList;
			}
	//Add Driver
	// http://localhost:8080/admin/v1/addDriver
	@PostMapping(value="/addDriver", consumes="application/json")
	public boolean addDriver(@RequestBody Driver driver) {	
		boolean newRecord = false;
		try {
			newRecord = adminService.addDriver(driver);
			
		} catch (HrException e) {
			System.out.println("Error occured at adding Driver Controller");
			e.printStackTrace();
		}
			return newRecord;
		}
	//Update Driver
	// http://localhost:8080/admin/v1/updateDriver
	@PutMapping(value = "/updateDriver", consumes = "application/json")
	public void updateDriver(@RequestBody Driver driver) {
			try {
				adminService.updateDriver(driver);
				} catch (HrException e) {
					System.out.println("Error in updating Driver");
					e.printStackTrace();
				}
			}
		
	//Delete Driver BY ID
	// http://localhost:8080/admin/v1/deleteDriver/{driverId}
	@DeleteMapping("/deleteDriver/{driverId}")
	public boolean deleteDriver(@PathVariable String driverId) {
			boolean Record = false;
			try {
				Record=adminService.deleteDriver(driverId);
			} 
			catch (HrException e) {
				System.out.println("Error in deleting Driver");
				e.printStackTrace();
			}
			return Record;
		}	

//=========================================================================Reports
	
	//Frequently Travelled Routes
	// http://localhost:8080/admin/v1/freqTravRoutes
	@GetMapping(value = "/freqTravRoutes",produces = "application/json")
	public List<Object[]> getFreqTravRoutes(){
			List<Object[]> list=null;
			try {
				list=adminService.freqTravelledRoutes();
			} catch (HrException e) {
				System.out.println("Error occured at Controller");
				e.printStackTrace();
			}
			return list;
		}
	
	//Profit Of Each Month
	// http://localhost:8080/admin/v1/profit
	@GetMapping(value = "/profit",produces = "application/json")
	public List<Object[]> getProfitMonth(){
				List<Object[]> list=null;
				try {
					list=adminService.profitOfEachMonth();
				} catch (HrException e) {
					System.out.println("Error occured at Controller");
					e.printStackTrace();
				}
				return list;
			}

	//Preferred Bus Type
	// http://localhost:8080/admin/v1/prefBus
	@GetMapping(value = "/prefBus",produces = "application/json")
	public List<Object[]> getPrefBus(){
				List<Object[]> list=null;
				try {
					list=adminService.prefBus();
				} catch (HrException e) {
					System.out.println("Error occured at Controller");
					e.printStackTrace();
				}
				return list;
			}

	//Preferred Bus Type
	// http://localhost:8080/admin/v1/userNoReserv
	@GetMapping(value = "/userNoReserv",produces = "application/json")
	public List<Object[]> getUserWithoutReserv(){
				List<Object[]> list=null;
				try {
					list=adminService.userWithNoReserv();
				} catch (HrException e) {
					System.out.println("Error occured at Controller");
					e.printStackTrace();
				}
				return list;
			}
	
}
