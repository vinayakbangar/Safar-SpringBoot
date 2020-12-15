package com.lti.service;

import java.util.List;

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

public interface AdminService {
	
	//User
	public abstract List<User> getUserList() throws HrException;
	
	//Admin
	public abstract Admin getAdminById(String email) throws HrException;
	public abstract List<Admin> getAdminLoginDetail(String email, String psw) throws HrException;
	
	//Feedback
	public abstract List<Feedback> getFeedbackList() throws HrException;
	
	//Bus
	public abstract List<Bus> getBusList() throws HrException;
	public abstract Bus getBusById(String bid) throws HrException;
	public abstract boolean addBus(Bus bus) throws HrException;
	public abstract boolean deleteBus(String busId) throws HrException;
	
	//Ticket
	public abstract List<Ticket> getTicketList() throws HrException;
	//Timetable
	public abstract List<Timetable> getTimetable() throws HrException;
	public abstract boolean addTimetable(String timetableId,String dateTime,Bus bus,Route route,Driver driver) throws HrException;
	public abstract boolean updateTimetable(String timetableId,String dateTime,Bus bus,Route route,Driver driver) throws HrException;
	public Timetable findTimeTable(String id) throws HrException;
	//Routes
		public abstract List<Route> getRouteList() throws HrException;
		public abstract Route getRouteById(long rid) throws HrException;
		public abstract boolean addRoute(Route route) throws HrException;
		public abstract void updateRoutes(Route route) throws HrException;
		public abstract boolean deleteRoute(Long routeId) throws HrException;
		
	//BusTypeRate
		public abstract List<BusTypeRate> getBusTypeList() throws HrException;
		public abstract BusTypeRate getBusTypeById(long btId) throws HrException;
		public abstract void updateBusType(BusTypeRate busType) throws HrException;
		
	//Driver
		public abstract List<Driver> getDriverList() throws HrException;
		public abstract Driver getDriverById(String did) throws HrException;
		public abstract boolean addDriver(Driver driver) throws HrException;
		public abstract void updateDriver(Driver driver) throws HrException;
		public abstract boolean deleteDriver(String driverId) throws HrException;

	//Reports
	public abstract List<Object[]> freqTravelledRoutes() throws HrException;
	public abstract List<Object[]> profitOfEachMonth() throws HrException;
	public abstract List<Object[]> prefBus() throws HrException;
	public abstract List<Object[]> userWithNoReserv() throws HrException;
		
}
