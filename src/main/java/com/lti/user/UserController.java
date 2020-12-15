package com.lti.user;

import java.math.BigDecimal;
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

import com.lti.bean.Feedback;
import com.lti.bean.Route;
import com.lti.bean.Ticket;
import com.lti.bean.Timetable;
import com.lti.bean.User;
import com.lti.exception.HrException;
import com.lti.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user/v1")
public class UserController {

	@Autowired
	private UserService userService;

	// Get User By Email(Id)
	// http://localhost:8080/user/v1/userId/{email}/
	@GetMapping(value = "/userId/{email}")
	public User getUserbyId(@PathVariable(value = "email") String email) {
		User user = null;
		try {
			user = userService.getUserById(email);
			System.out.println(user);
		} catch (HrException e) {
			System.out.println("Error in User");
			e.printStackTrace();
		}
		return user;
	}

	// Add User
	// http://localhost:8080/user/v1/addUser
	@PostMapping(value = "/addUser", consumes = "application/json")
	public boolean addUser(@RequestBody User user) {
		boolean newRecord = false;
		try {
			newRecord = userService.addUser(user);

		} catch (HrException e) {
			System.out.println("Error occured at adding User Controller");
			e.printStackTrace();
		}
		return newRecord;
	}

	// Update User
	// http://localhost:8080/user/v1/updateUser
	@PutMapping(value = "/updateUser/{email}", consumes = "application/json")
	public void updateUser(@PathVariable String email, @RequestBody User user) {
		user.setEmail(email);
		try {
			userService.updateUser(user);
		} catch (HrException e) {
			System.out.println("Error occured during updating user");
			e.printStackTrace();
		}
	}
	

	//Update User Password
	// http://localhost:8080/user/v1/updatePwd
	@PutMapping(value = "/updatePwd", consumes = "application/json")
		public void updateUserPwd(@RequestBody User user) {
			try {
			userService.updateUser(user);
			} catch (HrException e) {
				System.out.println("Error occured during updating Password");
				e.printStackTrace();
			}
		}

	// Delete User BY Email(ID)
	// http://localhost:8080/user/v1/deleteUser/{email}/
	@DeleteMapping("/deleteUser/{email}")
	public boolean deleteUser(@PathVariable String email) {
		boolean Record = false;
		try {
			Record = userService.deleteUser(email);
		} catch (HrException e) {
			System.out.println("Error in deleting user");
			e.printStackTrace();
		}
		return Record;
	}

//=======================================================================Route
	// Get Route By (Id)
	// http://localhost:8080/user/v1/route/{id}
	@GetMapping(value = "/route/{id}")
	public Route getRoutebyId(@PathVariable(value = "id") long id) {
		Route route = null;
		try {
			route = userService.getRouteById(id);
		} catch (HrException e) {
			System.out.println("Error in Route");
			e.printStackTrace();
		}
		return route;
	}

	// http://localhost:8080/user/v1/sourceList
	@GetMapping(value = "/sourceList", produces = "application/json")
	public List<String> getSource() {
		List<String> sourceList = null;
		try {
			sourceList = userService.getSource();
		} catch (HrException e) {
			System.out.println("Error in Route");
			e.printStackTrace();
		}

		return sourceList;

	}

	// http://localhost:8080/user/v1/destList
	@GetMapping(value = "/destList", produces = "application/json")
	public List<String> getDestination() {
		List<String> destList = null;
		try {
			destList = userService.getDestination();
		} catch (HrException e) {
			System.out.println("Error in Route");
			e.printStackTrace();
		}

		return destList;

	}

//========================================================================Feedback
	// Add Feedback
	// http://localhost:8080/user/v1/addFeedback/{email}
	@PostMapping(value = "/addFeedback/{email}", consumes = "application/json")
	public boolean addFeedback(@RequestBody Feedback feed, @PathVariable(value = "email") String email) {
		boolean newRecord = false;
		try {
			User user = userService.getUserById(email);
			feed.setUser(user);
			newRecord = userService.addFeedback(feed);

		} catch (HrException e) {
			System.out.println("Error occured at adding Feedback Controller");
			e.printStackTrace();
		}
		return newRecord;
	}

//============================================================================Ticket
	// Get Booking History By email
	// http://localhost:8080/user/v1/bookHistory/{email}
	@GetMapping(value = "/bookHistory/{email}")
	public List<Ticket> getBookingHistory(@PathVariable(value = "email") String email) {
		List<Ticket> tickets = null;
		try {
			tickets = userService.getBookingHistory(email);
		} catch (HrException e) {
			System.out.println("Error in Ticket");
			e.printStackTrace();
		}
		return tickets;
	}

	// Get Ticket By Id
	// http://localhost:8080/user/v1/getTicket/{id}
	@GetMapping(value = "/getTicket/{id}")
	public Ticket getTicketById(@PathVariable(value = "id") long id) {
		Ticket ticket = null;
		try {
			ticket = userService.getTicketById(id);
		} catch (HrException e) {
			System.out.println("Error in Ticket");
			e.printStackTrace();
		}
		return ticket;
	}

	// Update Ticket
	// http://localhost:8080/user/v1/reschedule/{ttId}/{email}
	@PutMapping(value = "/reschedule/{ttId}/{email}", consumes = "application/json")
	public boolean updateTicket(@RequestBody Ticket ticket, @PathVariable(value = "ttId") String ttId,
			@PathVariable(value = "email") String email) {
		boolean record = false;
		try {
			Timetable tt = userService.getTimetableById(ttId);
			User user = userService.getUserById(email);
			ticket.setTimetable(tt);
			ticket.setUser(user);
			record = userService.updateTicket(ticket);
		} catch (HrException e) {
			System.out.println("Error occured during updating Ticket");
			e.printStackTrace();
		}
		return record;
	}

	// http://localhost:8080/user/v1/getSeatNumbers/{ttId}
	@GetMapping(value = "/getSeatNumbers/{ttId}")
	public String[] getSeatNumbers(@PathVariable(value = "ttId") String ttId) {
		String[] seatNumbers = null;
		try {
			seatNumbers = userService.getSeatNumbersList(ttId);
		} catch (HrException e) {
			System.out.println("Error in Seat Numbers");
			e.printStackTrace();
		}
		return seatNumbers;
	}

	// bookTicket
	// http://localhost:8080/user/v1/bookTicket/{ttid}/{count}/{email}/{seatNumbers}/{journeyType}

	@GetMapping(value = "/bookTicket/{ttid}/{count}/{email}/{seatNumbers}/{journeyType}")
	public Ticket bookTicket(@PathVariable(value = "ttid") String ttid, @PathVariable(value = "count") BigDecimal count,
			@PathVariable(value = "email") String email, @PathVariable(value = "seatNumbers") String seatNumbers,
			@PathVariable(value = "journeyType") String journeyType

	) {
		BigDecimal wallet = new BigDecimal("10000");
		User user = null;
		Ticket ticket = null;
		try {
			user = userService.getUserById(email);
			if (user == null) {
				User user1 = new User();
				user1.setEmail(email);
				user1.setRegistered('N');
				user1.setWallet(wallet);
				userService.addUser(user1);
			}

			ticket = userService.bookTicket(ttid, count, email, seatNumbers, journeyType);
		} catch (HrException e) {
			System.out.println("Error in Ticket");
			e.printStackTrace();
		}
		return ticket;
	}

	// delete ticket
	@DeleteMapping("/deleteTicket/{tid}")
	public boolean deleteTicket(@PathVariable long tid) {
		boolean Record = false;

		try {
			Ticket ticket = userService.getTicketById(tid);
			Record = userService.deleteTicket(ticket);
		} catch (HrException e) {
			System.out.println("Error in deleting user");
			e.printStackTrace();
		}
		return Record;
	}

//=================================================================================================Login
	// Get Login Details
	// http://localhost:8080/user/v1/userLogin/{email}/{psw}
	@GetMapping(value = "/userLogin/{email}/{psw}")
	public List<User> getLoginDetail(@PathVariable(value = "email") String email,
			@PathVariable(value = "psw") String psw) {
		List<User> user = null;
		try {
			user = userService.getLoginDetail(email, psw);
		} catch (HrException e) {
			System.out.println("Error in User");
			e.printStackTrace();
		}
		return user;
	}

//=================================================================TimeTable		
	// Get Complete Timetable
	// http://localhost:8080/user/v1/getCompTt
	@GetMapping(value = "/getCompTt", produces = "application/json")
	public List<Object[]> getCompleteTimetable() {
		List<Object[]> list = null;
		try {
			list = userService.getCompleteTimetable();
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return list;
	}

	// Bus Availability Enquiry
	// http://localhost:8080/user/v1/searchBus/{source}/{destination}/{date}
	@GetMapping(value = "/searchBus/{source}/{destination}/{date}", produces = "application/json")
	public List<Object[]> searchBusAvail(@PathVariable(value = "source") String source,
			@PathVariable(value = "destination") String destination, @PathVariable(value = "date") String date) {
		List<Object[]> list = null;

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println(date);

		try {
			list = userService.searchBus(source, destination, date);
		} catch (HrException e) {
			System.out.println("Error occured at Controller");
			e.printStackTrace();
		}
		return list;
	}
//================================================================================================Email Send

	// Send email by id
	// http://localhost:8080/user/v1/sendResetMail/{email}/
	@GetMapping(value = "/sendResetMail/{email}")
	public void sendEmail(@PathVariable(value = "email") String email) {
		try {
			userService.resetpassword(email);
		} catch (HrException e) {
			System.out.println("Error in function");
			e.printStackTrace();
		}
	}
}
