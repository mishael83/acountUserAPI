package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Acount;
import model.User;


@RestController
@RequestMapping("/api")
public class AcountUserController {
	
	
	private static Map<String, Acount> acountMap;
    private static Map<String, User> userMap;
    private static Map<String, User> tempUserMap;
    
    private static Acount saveA(Acount acount) {
        if (acountMap == null) {
        	acountMap = new HashMap<String, Acount>();
        }
     
        acountMap.put(acount.getAcountId(), acount);
        return acount;
    }

    private static User saveU(User user) {
        if (userMap == null) {
        	userMap = new HashMap<String, User>();
        }
        userMap.put(user.getUserId(), user);
        return user;
    }

    private static boolean deleteA(String acountId) {
        Acount deletedAcount = acountMap.remove(acountId);
        if (deletedAcount == null) {
            return false;
        }
        return true;
    }
    
    private static boolean deleteU(String userId) {
        User deletedUser = userMap.remove(userId);
        if (deletedUser == null) {
            return false;
        }
        return true;
    }
    //for test
    static {
        Acount a1 = new Acount("1","aaa");
        saveA(a1);
        Acount a2 = new Acount("2","bbb");
        saveA(a2);
        Acount a3 = new Acount("2","hhhh");
        saveA(a3);
        User u1 = new User ("11","AAA","1");
        saveU(u1);
        User u2 = new User ("22","BBB","2");
        saveU(u2);
        User u3 = new User ("33","CCC","1");
        saveU(u3);
    }
    
    // GET:
    
    @RequestMapping(
            value = "/acounts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Acount>> getAcount() {

        Collection<Acount> acounts = acountMap.values();

        return new ResponseEntity<Collection<Acount>>(acounts,
                HttpStatus.OK);
    }
    @RequestMapping(
            value = "/acounts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Acount> getGreeting(
            @PathVariable("id") String acountID) {

        Acount acount = acountMap.get(acountID);
        if (acount == null) {
            return new ResponseEntity<Acount>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Acount>(acount, HttpStatus.OK);
    }
    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getUser() {

        Collection<User> users = userMap.values();

        return new ResponseEntity<Collection<User>>(users,
                HttpStatus.OK);
    }
    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(
            @PathVariable("id") String userID) {

        User user = userMap.get(userID);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(
            value = "/usersByAcount/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getUsers(
            @PathVariable("id") String acountId) {
    	User user=null;
    	if (tempUserMap == null) {
    		tempUserMap = new HashMap<String, User>();
        }
    	tempUserMap.clear();
    	for (Map.Entry entry : userMap.entrySet()){
    		user = (User)entry.getValue();
    		if (user.getUserAcounId().equals(acountId))
    				tempUserMap.put(user.getUserId(), user);
    	}
    	        Collection<User> users = tempUserMap.values();   
    	        	
    	        return new ResponseEntity<Collection<User>>(users,
    	                HttpStatus.OK);
    }
    
    //POST:
    
    @RequestMapping(
            value = "/acounts",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Acount> createAcount(
            @RequestBody Acount acount) {

        Acount savedAcount = saveA(acount);

        return new ResponseEntity<Acount>(savedAcount, HttpStatus.CREATED);
    }
    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(
            @RequestBody User user) {

        User savedUser = saveU(user);

        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }
    //put:
    @RequestMapping(
            value = "/acounts/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Acount> updateAcount(
            @RequestBody Acount acount) {

    	Acount updatedAcount = saveA(acount);
        if (updatedAcount == null) {
            return new ResponseEntity<Acount>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Acount>(updatedAcount, HttpStatus.OK);
    }
    @RequestMapping(
            value = "/users/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(
            @RequestBody User user) {

    	User updatedUser = saveU(user);
        if (updatedUser == null) {
            return new ResponseEntity<User>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }
    
   // delete:
    @RequestMapping(
            value = "/acountsDelte/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Acount> deleteAcount(
            @PathVariable("id") String acountId, @RequestBody Acount acount) {

    	boolean deletedUsers=false;
    	User user;
    	for (Map.Entry entry : userMap.entrySet()){
    		user=(User)entry.getValue();
    		if (user.getUserAcounId().equals(acountId))
    			deletedUsers=deleteU(user.getUserId());
    	}
    	boolean deleted = deleteA(acountId);
        if (!deleted||!deletedUsers) {
            return new ResponseEntity<Acount>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Acount>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(
            value = "/userDelete/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(
            @PathVariable("id") String userId, @RequestBody User user) {

        boolean deleted = deleteU(userId);
        if (!deleted) {
            return new ResponseEntity<User>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
