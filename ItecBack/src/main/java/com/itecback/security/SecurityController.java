package com.itecback.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itecback.entities.generator.Generator;
import com.itecback.security.entities.User;
import com.itecback.security.entities.UserRepository;
import com.itecback.security.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecurityController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/security")
    public String security() throws JsonProcessingException
    {
        Map<String, Object> responseBody=new HashMap<>();
        responseBody.put("loggedin","yes");

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(responseBody);
        return json;
    }

    @GetMapping("/security/loggedin")
    public HashMap<String,Boolean> loggedin()
    {
        HashMap<String,Boolean> map=new HashMap<>();
        map.put("loggedin",true);
        return map;
    }

    @GetMapping("/security/getUser")
    public User getUser() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        User user=objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),User.class);
        user=userRepository.findById(user.getId()).get();
        return user;
    }

    @PostMapping("/security/sign-up")
    public void securitySignUp(@RequestBody User user)
    {
        user.setId(Generator.generateId());
        String salt=BCrypt.gensalt();
        user.setPassword(BCrypt.hashpw(user.getPassword(),salt));
        userRepository.save(user);

//        if (!UserService.userExists(user.getEmail(),userRepository))
//        {
//            user.setId(Generator.generateId());
//            String salt=BCrypt.gensalt();
//            user.setPassword(BCrypt.hashpw(user.getPassword(),salt));
//            userRepository.save(user);
//            return "user added";
//        }
//        else
//        {
//            return "user already exists";
//        }
    }
}
