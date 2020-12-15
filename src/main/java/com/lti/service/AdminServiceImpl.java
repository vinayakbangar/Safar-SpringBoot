package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.bean.Admin;
import com.lti.bean.Bus;
import com.lti.bean.BusTypeRate;
import com.lti.bean.Driver;
import com.lti.bean.Feedback;
import com.lti.bean.Route;
import com.lti.bean.Ticket;
import com.lti.bean.Timetable;
import com.lti.bean.User;
import com.lti.dao.AdminDao;
import com.lti.exception.HrException;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
//=================================================================User	
	@Override
	public List<User> getUserList() throws HrException {
		return adminDao.getUserList();
	}
//==================================================================Admin 
	@Override
	public Admin getAdminById(String email) throws HrException {
		return adminDao.getAdminById(email);
	}
	@Override
	public List<Admin> getAdminLoginDetail(String email, String psw) throws HrException {
		return adminDao.getAdminLoginDetail(email, psw);
	}

//=================================================================Feedback

	@Override
	public List<Feedback> getFeedbackList() throws HrException {
		return adminDao.getFeedbackList();
	}

//==============================================================Bus
	@Override
	public List<Bus> getBusList() throws HrException {
		return adminDao.getBusList();
	}
	@Override
	public Bus getBusById(String bid) throws HrException {
		return adminDao.getBusById(bid);
	}
	@Override
	public boolean addBus(Bus bus) throws HrException {
		return adminDao.addBus(bus);
	}

	@Override
	public boolean deleteBus(String busId) throws HrException {
		return adminDao.deleteBus(busId);
	}


	//=====================================================================Ticket
	@Override
	public List<Ticket> getTicketList() throws HrException {
		return adminDao.getTicketList();
	}
	

//=====================================================================Timetable	
	@Override
	public List<Timetable> getTimetable() throws HrException {
		return adminDao.getTimetable();
	}
	
	@Override
	public boolean addTimetable(String timetableId, String dateTime, Bus bus, Route route, Driver driver)
			throws HrException {
		return adminDao.addTimetable(timetableId, dateTime, bus, route, driver);
	}
	
	@Override
	public boolean updateTimetable(String timetableId, String dateTime, Bus bus, Route route, Driver driver)
			throws HrException {
		return adminDao.updateTimetable(timetableId, dateTime, bus, route, driver);
	}
	
	@Override
	public Timetable findTimeTable(String id) throws HrException {
		return adminDao.findTimeTable(id);
	}


	//-------------------------------------------------------------Route
	@Override
	public List<Route> getRouteList() throws HrException {
		return adminDao.getRouteList();
	}

	@Override
	public Route getRouteById(long rid) throws HrException {
		return adminDao.getRouteById(rid);
	}


	@Override
	public boolean addRoute(Route route) throws HrException {
		return adminDao.addRoute(route);
	}

	@Override
	public void updateRoutes(Route route) throws HrException {
		adminDao.updateRoutes(route);		
	}

	@Override
	public boolean deleteRoute(Long routeId) throws HrException {
		return adminDao.deleteRoute(routeId);
	}

//-------------------------------------------------------------BusTypeRate
	@Override
	public List<BusTypeRate> getBusTypeList() throws HrException {
		return adminDao.getBusTypeList();
	}
	
	@Override
	public BusTypeRate getBusTypeById(long btId) throws HrException {
		return adminDao.getBusTypeById(btId);
	}
	

	@Override
	public void updateBusType(BusTypeRate busType) throws HrException {
		adminDao.updateBusType(busType);	
	}

//-------------------------------------------------------------Driver
	@Override
	public List<Driver> getDriverList() throws HrException {
		return adminDao.getDriverList();
	}
	
	@Override
	public Driver getDriverById(String did) throws HrException {
		return adminDao.getDriverById(did);
	}

	@Override
	public boolean addDriver(Driver driver) throws HrException {
		return adminDao.addDriver(driver);
	}

	@Override
	public void updateDriver(Driver driver) throws HrException {
		adminDao.updateDriver(driver);	
	}

	@Override
	public boolean deleteDriver(String driverId) throws HrException {
		return adminDao.deleteDriver(driverId);
	}
//======================================================================Reports
	@Override
	public List<Object[]> freqTravelledRoutes() throws HrException {
		return adminDao.freqTravelledRoutes();
	}

	@Override
	public List<Object[]> profitOfEachMonth() throws HrException {
		return adminDao.profitOfEachMonth();
	}

	@Override
	public List<Object[]> prefBus() throws HrException {
		return adminDao.prefBus();
	}

	@Override
	public List<Object[]> userWithNoReserv() throws HrException {
		return adminDao.userWithNoReserv();
	}
	
	

}
