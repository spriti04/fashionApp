package com.spriti.serviceImpl;

import com.spriti.Model.Person;
import com.spriti.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> opt = personRepo.findByEmail(username);

        if(opt.isPresent()){
            Person person = opt.get();
//            System.out.println("Person login with role " + person.getRole());

            List<GrantedAuthority> authorities = new ArrayList<>();

            SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_" + person.getRole());
            authorities.add(sga);

            return new User(person.getEmail(), person.getPassword(), authorities);
        }else{
            throw new BadCredentialsException("Person doesn't exists with this username...........");
        }
    }
}
