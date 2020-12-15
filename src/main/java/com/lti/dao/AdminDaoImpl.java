package com.lti.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

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

@Repository
public class AdminDaoImpl implements AdminDao{
	
	@PersistenceContext
	private EntityManager adminManager;
//---------------------------------------------------------------------User
	@Override
	public List<User> getUserList() throws HrException {
		String strQry ="from User";
		Query qry = adminManager.createQuery(strQry);
		List<User> list=qry.getResultList();
		return list;
	}
	
//=======================================================================Login
	@Override
	public Admin getAdminById(String email) throws HrException {
		return adminManager.find(Admin.class, email);
	}

	@Override
	public List<Admin> getAdminLoginDetail(String email, String psw) throws HrException {
		Query query = adminManager.createQuery("SELECT a FROM Admin a WHERE a.adminEmail =:email and a.adminPassword=:psw" , Admin.class);
		query.setParameter("email", email);
		query.setParameter("psw", psw);
		List<Admin> admin =  query.getResultList();
		return admin;
	}
//---------------------------------------------------------------------Feedback
	@Override
	public List<Feedback> getFeedbackList() throws HrException {
		String strQry ="from Feedback";
		Query qry = adminManager.createQuery(strQry);
		List<Feedback> list=qry.getResultList();
		return list;
	}
//--------------------------------------------------------------------Bus
	@Override
	public List<Bus> getBusList() throws HrException {
		String strQry ="from Bus";
		Query qry = adminManager.createQuery(strQry);
		List<Bus> list=qry.getResultList();
		return list;
	}
	@Override
	public Bus getBusById(String bid) throws HrException {
		return adminManager.find(Bus.class, bid);
	}
	
	@Override
	public boolean addBus(Bus bus) throws HrException {
		adminManager.persist(bus);
		return true;
	}

	@Override
	public boolean deleteBus(String busId) throws HrException {
		Bus bus = adminManager.find(Bus.class, busId);
		adminManager.remove(bus);
		return true;
	}
//----------------------------------------------------------------------Ticket
	@Override
	public List<Ticket> getTicketList() throws HrException {
		String strQry ="from Ticket";
		Query qry = adminManager.createQuery(strQry);
		List<Ticket> list=qry.getResultList();
		return list;
	}
	
	
//---------------------------------------------------------------------Timetable
	@Override
	public List<Timetable> getTimetable() throws HrException {
		String strQry ="from Timetable";
		Query qry = adminManager.createQuery(strQry);
		List<Timetable> list=qry.getResultList();
		return list;
	}
	
	@Override
	public boolean addTimetable(String timetableId, String dateTime, Bus bus, Route route, Driver driver)
			throws HrException{
		Timetable t= new Timetable();
		t.setTimetableId(timetableId);
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
			t.setDepartureDateTime(date);
		} catch (ParseException e) {
			System.out.println("Error in date");
			e.printStackTrace();
		}
		t.setBus(bus);
		t.setDriver(driver);
		t.setRoute(route);
		adminManager.persist(t);
		return true;
	}
	
	@Override
	public boolean updateTimetable(String timetableId, String dateTime, Bus bus, Route route, Driver driver)
			throws HrException {
		Timetable t= new Timetable();
		t.setTimetableId(timetableId);
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
			t.setDepartureDateTime(date);
		} catch (ParseException e) {
			System.out.println("Error in date");
			e.printStackTrace();
		}
		t.setBus(bus);
		t.setDriver(driver);
		t.setRoute(route);
		adminManager.merge(t);
		return true;
	}
	
	@Override
	public Timetable findTimeTable(String id) throws HrException {
		Timetable timeTable=adminManager.find(Timetable.class, id);
		return timeTable ;
	}
	
	//-------------------------------------------------------------Route
	@Override
	public List<Route> getRouteList() throws HrException {
		String strQry ="from Route";
		Query qry = adminManager.createQuery(strQry);
		List<Route> list=qry.getResultList();
		return list;
	}
	@Override
	public Route getRouteById(long rid) throws HrException {
		return adminManager.find(Route.class, rid);
	}

	@Override
	public boolean addRoute(Route route) throws HrException {
		adminManager.persist(route);
		return true;
	}

	@Override
	public void updateRoutes(Route route) throws HrException {
		adminManager.merge(route);		
	}

	@Override
	public boolean deleteRoute(Long routeId) throws HrException {
		Route route = adminManager.find(Route.class, routeId);
		adminManager.remove(route);
		return true;
	}

//-------------------------------------------------------------BusTypeRate
	
	@Override
	public List<BusTypeRate> getBusTypeList() throws HrException {
		String strQry ="from BusTypeRate";
		Query qry = adminManager.createQuery(strQry);
		List<BusTypeRate> list=qry.getResultList();
		return list;
	}
	@Override
	public BusTypeRate getBusTypeById(long btId) throws HrException {
		BusTypeRate bt=adminManager.find(BusTypeRate.class, btId);
		return bt;
	}

	@Override
	public void updateBusType(BusTypeRate busType) throws HrException {
		adminManager.merge(busType);	
	}
//------------------------------------------------------------Driver
	@Override
	public List<Driver> getDriverList() throws HrException {
		String strQry ="from Driver";
		Query qry = adminManager.createQuery(strQry);
		List<Driver> list=qry.getResultList();
		return list;
	}
	
	@Override
	public Driver getDriverById(String did) throws HrException {
		return adminManager.find(Driver.class, did);
	}


	@Override
	public boolean addDriver(Driver driver) throws HrException {
		adminManager.persist(driver);
		return true;
	}

	@Override
	public void updateDriver(Driver driver) throws HrException {
		adminManager.merge(driver);
	}

	@Override
	public boolean deleteDriver(String driverId) throws HrException {
		Driver driver = adminManager.find(Driver.class, driverId);
		adminManager.remove(driver);
		return true;	
	}
	
	
//=============================================================================Reports
	
	@Override
	public List<Object[]> freqTravelledRoutes() throws HrException {
		String strQry ="SELECT r.route_id,r.source,r.destination,COUNT(*) AS TicketsBooked " + 
				"FROM ticket t, timetable tt , routes r  " + 
				"WHERE t.timetable_id=tt.timetable_id and tt.route_id=r.route_id  " + 
				"GROUP BY r.route_id,r.source,r.destination " + 
				"ORDER BY COUNT(*) Desc ";
		Query qry = adminManager.createNativeQuery(strQry);
		List<Object[]> list=qry.getResultList();
		return list;
	}
	@Override
	public List<Object[]> profitOfEachMonth() throws HrException {
		String strQry ="SELECT  TO_CHAR(tt.departure_date_time,'MONTH-YYYY') AS MONTH,SUM(t.fare) AS PROFIT " + 
				"FROM ticket t,timetable tt " + 
				"WHERE t.timetable_id=tt.timetable_id " + 
				"GROUP BY TO_CHAR(tt.departure_date_time,'MONTH-YYYY') ";
		Query qry = adminManager.createNativeQuery(strQry);
		List<Object[]> list=qry.getResultList();
		return list;
	}
	@Override
	public List<Object[]> prefBus() throws HrException {
		String strQry ="SELECT bt.type_name,COUNT(*) AS TicketsBooked " + 
				"FROM ticket t, timetable tt , bus b,bus_type_rate bt " + 
				"WHERE t.timetable_id=tt.timetable_id and tt.bus_no=b.bus_no and b.bus_type_id=bt.bus_type_id  " + 
				"GROUP BY bt.bus_type_id,bt.type_name " + 
				"ORDER BY COUNT(*) Desc";
		Query qry = adminManager.createNativeQuery(strQry);
		List<Object[]> list=qry.getResultList();
		return list;
	}
	@Override
	public List<Object[]> userWithNoReserv() throws HrException {
		String strQry ="SELECT (u.first_name||' '|| u.last_name) AS Name, u.email " + 
				"FROM users u   " + 
				"WHERE NOT EXISTS(SELECT NULL FROM ticket t WHERE t.email=u.email) AND u.registered='Y' " + 
				"ORDER BY Name";
		Query qry = adminManager.createNativeQuery(strQry);
		List<Object[]> list=qry.getResultList();
		return list;
	}

	

	






}
	


