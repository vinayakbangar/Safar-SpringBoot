package com.lti.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lti.bean.Bus;
import com.lti.bean.BusTypeRate;
import com.lti.bean.Feedback;
import com.lti.bean.Route;
import com.lti.bean.SendEmail;
import com.lti.bean.Ticket;
import com.lti.bean.Timetable;
import com.lti.bean.User;
import com.lti.exception.HrException;

@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager userManager;
//==================================================================User
	@Override
	public User getUserById(String email) throws HrException {
		return  userManager.find(User.class, email);
	}

	@Override
	public boolean addUser(User user) throws HrException {
		userManager.persist(user);
		return true;
	}

	@Override
	public void updateUser(User user) throws HrException {
		userManager.merge(user);	
	}

	@Override
	public boolean deleteUser(String email) throws HrException {
		User user = userManager.find(User.class, email);
		userManager.remove(user);
		return true;
	}
//=======================================================================Route
	@Override
	public Route getRouteById(long routeId) throws HrException {
		return userManager.find(Route.class, routeId);
	}
	

	@Override
	public List<String> getSource() throws HrException {
		Query qry=userManager.createQuery("SELECT DISTINCT r.source from Route r");
		List<String> sourceList =  qry.getResultList();
		return sourceList;
	}

	
	@Override
	public List<String> getDestination() throws HrException {
		
		Query qry=userManager.createQuery("SELECT DISTINCT r.destination from Route r");
		List<String> destList =  qry.getResultList();
		return destList;
	}
//========================================================================Feedback
	@Override
	public boolean addFeedback(Feedback feed) throws HrException {
		userManager.persist(feed);
		return true;
	}
//========================================================================Ticket
	@Override
	public List<Ticket> getBookingHistory(String email) throws HrException {
		TypedQuery<Ticket> query = userManager.createQuery("SELECT t FROM Ticket t WHERE user.email =:email" , Ticket.class);
		List<Ticket> tickets = query.setParameter("email", email).getResultList();
		return tickets;
	}

	@Override
	public Ticket getTicketById(long tId) throws HrException {
		return userManager.find(Ticket.class, tId);
	}

	@Override
	public boolean updateTicket(Ticket ticket) throws HrException {
		userManager.merge(ticket);
		return true;
	}
	
	@Override
	public String[] getSeatNumbersList(String ttId) throws HrException {
		String strQry ="SELECT SEAT_NUMBERS FROM TICKET WHERE TIMETABLE_ID='"+ttId+"'";
		Query qry = userManager.createNativeQuery(strQry);
		String sarray="";
		List<String> lists= qry.getResultList();
		for(String list:lists) {
			sarray+=list;
			sarray+=",";
			
		}
		String[] seatNumbers = sarray.split(",");
		return seatNumbers;
	}

	@Override
	public Ticket bookTicket(String ttId, BigDecimal count, String email, String seatNumbers, String journeyType) throws HrException {
		BigDecimal finalFare=null;
		BigDecimal wallet=null;
		BigDecimal twice = new BigDecimal("2");
		Ticket t = new Ticket();
		Ticket ct = new Ticket();
		Timetable ttnew = userManager.find(Timetable.class, ttId);
		User usr1 = userManager.find(User.class, email);
		Route r1= ttnew.getRoute();
		Bus b1 = ttnew.getBus();
		BusTypeRate bt1= b1.getBusTypeRate();
		t.setUser(usr1);
		wallet=usr1.getWallet();
		t.setJourneyType(journeyType);
		t.setTimetable(ttnew);
		t.setSeatNumbers(seatNumbers);
		BigDecimal rfare = r1.getRouteFare();
		BigDecimal bfare = bt1.getRatePerSeat();
		BigDecimal totalFare = rfare.add(bfare);
		totalFare = totalFare.multiply(count);
		System.out.println(totalFare);
		
		if(journeyType.contentEquals("SINGLE")) {
			finalFare = totalFare;
			wallet=wallet.subtract(finalFare);
		}
		else {
			finalFare = totalFare.multiply(twice);
			wallet=wallet.subtract(finalFare);
		}
		
		t.setFare(finalFare);
		ct= addTicket(t);
		usr1.setWallet(wallet);
		userManager.merge(usr1);
		
		
		return ct;
	}

	
	@Override
	public Ticket addTicket(Ticket ticket) throws HrException {
		
		userManager.persist(ticket);
		long ticketid=ticket.getTicketId();
		return userManager.find(Ticket.class, ticketid);
	}
	
	@Override
	public boolean deleteTicket(Ticket ticket) throws HrException {
		BigDecimal fare=ticket.getFare();
		User user=ticket.getUser();
		BigDecimal wallet=user.getWallet();
		wallet=wallet.add(fare);
		user.setWallet(wallet);
		userManager.merge(user);
		userManager.remove(ticket);
		return true;
	}

//=================================================================================Timetable
	@Override
	public Timetable getTimetableById(String ttId) throws HrException {
		return userManager.find(Timetable.class, ttId);
	}
	
	@Override
	public List<Object[]> getCompleteTimetable() throws HrException {
		String strQry ="SELECT t.timetable_id,b.bus_no,b.bus_name,bt.type_name, t.departure_date_time,TO_CHAR(t.DEPARTURE_DATE_TIME,'hh24:mi') AS DEPT_TIME ,r.source,r.destination,d.driver_name " + 
				"FROM timetable t, routes r,driver d,bus b,bus_type_rate bt  " + 
				"WHERE t.route_id=r.route_id and t.driver_id=d.driver_id and b.bus_no=t.bus_no and b.bus_type_id=bt.bus_type_id ";
		Query qry = userManager.createNativeQuery(strQry);
		List<Object[]> list=qry.getResultList();
		return list;
	}

	@Override
	public List<Object[]> searchBus(String source, String destination, String date) throws HrException {
		String strQry ="SELECT t.timetable_id,b.bus_no,b.bus_name,bt.type_name, TO_CHAR(t.DEPARTURE_DATE_TIME,'dd-mm-yyyy') AS DEPARTURE_DATE_TIME,TO_CHAR(t.DEPARTURE_DATE_TIME,'hh24:mi') AS DEPT_TIME ,r.source,r.destination,d.driver_name " + 
				"FROM timetable t, routes r,driver d,bus b,bus_type_rate bt " + 
				"WHERE t.route_id=r.route_id and t.driver_id=d.driver_id and b.bus_no=t.bus_no and b.bus_type_id=bt.bus_type_id " + 
				"and r.source='"+source+"' and r.destination='"+destination+"' and t.departure_date_time>= TO_CHAR(TO_DATE('"+date+"','yyyy-mm-dd'),'dd-mm-yyyy') ";
		Query qry = userManager.createNativeQuery(strQry);
		List<Object[]> list=qry.getResultList();
		return list;
	}
//==================================================================================Email
	
	@Override
	public List<User> getLoginDetail(String email, String psw) throws HrException {
		Query query = userManager.createQuery("SELECT u FROM User u WHERE u.email =:email and u.password=:psw" , User.class);
		query.setParameter("email", email);
		query.setParameter("psw", psw);
		List<User> user =  query.getResultList();
		return user;
	}

	
//=======================================================================================

@Override
	public void resetpassword(String useremail) throws HrException {
		SendEmail.resetpwd(useremail);
		
	}
	


}
