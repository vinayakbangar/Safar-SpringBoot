package com.lti.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.bean.Feedback;
import com.lti.bean.Route;
import com.lti.bean.Ticket;
import com.lti.bean.Timetable;
import com.lti.bean.User;
import com.lti.dao.UserDao;
import com.lti.exception.HrException;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(String email) throws HrException {
		return userDao.getUserById(email);
	}

	@Override
	public boolean addUser(User user) throws HrException {
		return userDao.addUser(user);
	}

	@Override
	public void updateUser(User user) throws HrException {
		userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(String email) throws HrException {
		return userDao.deleteUser(email);
	}
//========================================================================Route
	@Override
	public Route getRouteById(long routeId) throws HrException {
		return userDao.getRouteById(routeId);
	}
	@Override
	public List<String> getSource() throws HrException {
		
		return userDao.getSource();
	}
	
	@Override
	public List<String> getDestination() throws HrException {
		
		return userDao.getDestination();
	}
//=======================================================================Feedback
	@Override
	public boolean addFeedback(Feedback feed) throws HrException {
		return userDao.addFeedback(feed);
	}
//==============================================================================Ticket
	@Override
	public List<Ticket> getBookingHistory(String email) throws HrException {
		return userDao.getBookingHistory(email);
	}

	@Override
	public Ticket getTicketById(long tId) throws HrException {
		return userDao.getTicketById(tId);
	}

	@Override
	public boolean updateTicket(Ticket ticket) throws HrException {
		return userDao.updateTicket(ticket);
	}
	
	@Override
	public String[] getSeatNumbersList(String ttId) throws HrException {
	
		return userDao.getSeatNumbersList(ttId);
	}

	
	@Override
	public Ticket bookTicket(String ttId, BigDecimal count, String email, String seatNumbers, String journeyType)
			throws HrException {
		
		return userDao.bookTicket(ttId, count, email, seatNumbers, journeyType);
	}
	
	@Override
	public boolean deleteTicket(Ticket ticket) throws HrException {
		return userDao.deleteTicket(ticket);
	}


//============================================================================Timetable
	@Override
	public Timetable getTimetableById(String ttId) throws HrException {
		return userDao.getTimetableById(ttId);
	}
	
	@Override
	public List<Object[]> getCompleteTimetable() throws HrException {
		
		return userDao.getCompleteTimetable();
	}

	@Override
	public List<Object[]> searchBus(String source, String destination, String date) throws HrException {
		
		return userDao.searchBus(source, destination, date);
	}
//================================================================================Login
	@Override
	public List<User> getLoginDetail(String email, String psw) throws HrException {
		return userDao.getLoginDetail(email, psw);
	}
	
	@Override
	public void resetpassword(String useremail) throws HrException {
		userDao.resetpassword(useremail);
		
	}
	
}
