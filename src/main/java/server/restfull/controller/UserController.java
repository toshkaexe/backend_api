
package server.restfull.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import server.restfull.entity.User;

import server.restfull.service.UserService;

@RestController
public class UserController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    @ResponseBody
    public String welcome() {

        return "Hello backend!";
    }


    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllUsers() {
        log.info("all user list");
        return ResponseEntity.ok(userService.getAllUsers());

    }


    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getById(@PathVariable String id) {
        log.info("get data by Id");
        User user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>("no user with this id", HttpStatus.BAD_REQUEST);


    }

    @RequestMapping(value = "email/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getByEmail(@PathVariable String name) {
        log.info("get user with email");
        if (userService.getByEmail(name) == null) {
            return new ResponseEntity<>(
                    "no user with this email",
                    HttpStatus.BAD_REQUEST);

        } else {
            return ResponseEntity.ok(userService.getByEmail(name));
        }
    }


    @RequestMapping(value = "phone/{number}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getByPhone(@PathVariable String number) {
        log.info("get user with phone by number");
        if (userService.getByPhone(number) == null) {
            return new ResponseEntity<>(
                    "no user with this phone",
                    HttpStatus.BAD_REQUEST);

        } else {
            return ResponseEntity.ok(userService.getByPhone(number));

        }
    }


    @RequestMapping(value = "source/{sourceName}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBySource(@PathVariable String sourceName) {
        if (userService.getBySource(sourceName) == null) {
            return new ResponseEntity<>(
                    "no user with this source",
                    HttpStatus.BAD_REQUEST);

        } else {
            return ResponseEntity.ok(userService.getBySource(sourceName));
        }
    }

    @RequestMapping(value = "/phone", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPhones() {
        return new ResponseEntity<>(
                "sorry, but not set yet",
                HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEmails() {
        return new ResponseEntity<>(
                "sorry, but not set yet",
                HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/sources", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getSources() {
        return ResponseEntity.ok(userService.getAllSource());
    }

    @RequestMapping(value = "/source", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getSource() {
        return new ResponseEntity<>(
                "sorry, but not set yet",
                HttpStatus.BAD_REQUEST);
    }


    /*
    Number users by sourses
     */
    @RequestMapping(value = "/numbs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity numberUsersBySource() {
        return ResponseEntity.ok(userService.getNumberUsersBySource());
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST, produces = "application/json")
    public void addUser(@RequestBody User newUser) {

        userService.addUser(newUser);
    }


    @RequestMapping(value = "/data/{id}", method = RequestMethod.PUT, produces = "application/json")
    public void updatedUserById(@PathVariable String id, @RequestBody User user) {

        userService.updateUser(id, user);
    }

}
