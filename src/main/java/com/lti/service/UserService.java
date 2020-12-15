package com.lti.service;

import java.math.BigDecimal;
import java.util.List;

import com.lti.bean.Feedback;
import com.lti.bean.Route;
import com.lti.bean.Ticket;
import com.lti.bean.Timetable;
import com.lti.bean.User;
import com.lti.exception.HrException;

public interface UserService {
	public abstract User getUserById(String email) throws HrException;
	public abstract boolean addUser(User user) throws HrException;
	public abstract void updateUser(User user) throws HrException;
	public abstract boolean deleteUser(String email) throws HrException;
	
	//Get Route By Id
	public abstract Route getRouteById(long routeId) throws HrException;
	public abstract List<String> getSource() throws HrException;
	public abstract List<String> getDestination() throws HrException;
	
	//Add Feedback
	public abstract boolean addFeedback(Feedback feed) throws HrException;
	
	//Ticket
	public abstract List<Ticket> getBookingHistory(String email) throws HrException;
	public abstract Ticket getTicketById(long tId) throws HrException;
	public abstract boolean updateTicket(Ticket ticket) throws HrException;
	public String[] getSeatNumbersList(String ttId) throws HrException;
	public abstract Ticket bookTicket(String ttId, BigDecimal count, String email, String seatNumbers, String journeyType) throws HrException;
	public abstract boolean deleteTicket(Ticket ticket) throws HrException;
	
	//Timetable
	public abstract Timetable getTimetableById(String ttId) throws HrException;
	public abstract List<Object[]> getCompleteTimetable() throws HrException;
	public abstract List<Object[]> searchBus(String source,String destination, String date) throws HrException;
	
	//Login
	public abstract List<User> getLoginDetail(String email, String psw) throws HrException;
	
	//sendMail
	public abstract void resetpassword(String useremail) throws HrException;
}