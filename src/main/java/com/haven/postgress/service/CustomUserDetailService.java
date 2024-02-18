package com.haven.postgress.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.haven.postgress.model.Customers;


@Service  
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	CustomerService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		 Optional<Customers> customerOptional = customerService.getCustomerByEmail(email);
		    
		    if (customerOptional.isPresent()) {
		        Customers customers =customerOptional.get();
		        return new org.springframework.security.core.userdetails.User(customers.getName(), customers.getPassword(),new ArrayList<>());
		        		
		    } else {
		    	throw new UsernameNotFoundException("User not found with username: " + email);
		    }
       
		
	}
}
