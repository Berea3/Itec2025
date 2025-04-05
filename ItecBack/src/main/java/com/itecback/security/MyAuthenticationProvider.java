package com.itecback.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itecback.security.entities.User;
import com.itecback.security.entities.UserRepository;
import com.itecback.security.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private UserService userService=new UserService();

    @Override
    public Authentication authenticate(Authentication authentication)
    {
        String email=authentication.getName();
        String password=authentication.getCredentials().toString();
        User user=userService.validateUser(email,password,userRepository);
        if (user!=null)       //return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
        {
            user.setPassword("");
            ObjectMapper mapper=new ObjectMapper();
            try {
                String json=mapper.writeValueAsString(user);
                return new UsernamePasswordAuthenticationToken(json,password,new ArrayList<>());  //new ArrayList<>() grantedAuthorities
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
