package com.ayantsoft.payroll.security.oauth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.pojo.UserMst;
import com.ayantsoft.payroll.service.EmployeeMstService;
import com.ayantsoft.payroll.service.UserMstService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	private Logger log = Logger.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserMstService userMstService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			log.info("loadUserByUsername");

			UserMst userMst = userMstService.getUserMstByUsername(username);

			if(userMst == null){
				throw new UsernameNotFoundException("User Not Found");
			}else{
				if(userMst.getUserName().equals(username)){
					GrantedAuthority authority = new GrantedAuthorityImpl("ROLE_ADMIN");
					Set<GrantedAuthority> authorities = new HashSet<>();
					authorities.add(authority);
					AuthUser authUser = new AuthUser(userMst.getUserName(), userMst.getPassword(), authorities);
					authUser.setUserMst(userMst);
					return authUser;
				}else{
					throw new UsernameNotFoundException("User Not Found");
				}
			}
		}catch(Exception e){
			log.error("UserDetailsServiceImpl loadUserByUsername : ", e);
			return null;
		}
	}
}