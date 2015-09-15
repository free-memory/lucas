package iken.controller;

import iken.domain.object.User;
import iken.domain.service.UserService;
import iken.domain.service.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ken on 15/8/25.
 */

@Controller
@RequestMapping(value = "/users")
public class UserController extends RestfulController {

    /**
     * Get user by ID. GET /users/id
     *
     * @param model
     * @param id
     * @return userinfo page
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<String> getUser(ModelMap model, @PathVariable("id") int id) {
        JSONObject jsonResponse = new JSONObject();
        try {
            UserService userService = new UserServiceImpl();
            User user = userService.getUserByID(id);
            JSONObject jsonObject = new JSONObject(user);

            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_SUCCESS);
            jsonResponse.put(RESPONSE_DATA, jsonObject);

            System.out.println("User =" + jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_FAILURE);
            jsonResponse.put(RESPONSE_EXCEPTION, e.getMessage());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<String>(jsonResponse.toString(), responseHeaders, HttpStatus.ACCEPTED);

//        User user = new User();
//        user.setId(id);
//        user.setName("ken");
//        user.setAge(30);
//
//        model.addAttribute("user", user);
//        return "userinfo";
    }


    /**
     * List users.  GET /users
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> listUser(ModelMap model) {

        JSONObject jsonResponse = new JSONObject();
        UserService userService = new UserServiceImpl();
        try {
            List<User> userList = userService.listUser();

            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_SUCCESS);
            jsonResponse.put(RESPONSE_DATA, new JSONArray(userList.toArray()));
            System.out.println("User List=" + userList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_FAILURE);
            jsonResponse.put(RESPONSE_EXCEPTION, e.getMessage());
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<String>(jsonResponse.toString(), responseHeaders, HttpStatus.ACCEPTED);
    }

    /**
     * Delete a user
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        boolean flag;
        JSONObject jsonResponse = new JSONObject();

        try {
            UserService userService = new UserServiceImpl();

            flag = userService.deleteUser(id);

            JSONObject jsonObject = new JSONObject();
            jsonResponse.put(RESPONSE_STATUS, flag ? RESPONSE_STATUS_SUCCESS : RESPONSE_STATUS_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_FAILURE);
            jsonResponse.put(RESPONSE_EXCEPTION, e.getMessage());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<String>(jsonResponse.toString(), responseHeaders, HttpStatus.ACCEPTED);

    }


    /**
     * Update a user. PUT /users/{id}
     *
     * @param model
     * @param id
     * @param strUser
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<String> putUser(ModelMap model, @PathVariable("id") int id, @RequestBody String strUser) {

        JSONObject jsonResponse = new JSONObject();
        try {
            System.out.println("Received user =" + strUser);
            JSONObject jsonObj = new JSONObject(strUser);

            User user = new User(jsonObj);
            user.setId(id);
            System.out.println("Converted user =" + user);

            UserService userService = new UserServiceImpl();
            boolean flag = userService.updateUser(user);

            jsonResponse.put(RESPONSE_STATUS, flag ? RESPONSE_STATUS_SUCCESS : RESPONSE_STATUS_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_FAILURE);
            jsonResponse.put(RESPONSE_EXCEPTION, e.getMessage());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<String>(jsonResponse.toString(), responseHeaders, HttpStatus.NO_CONTENT);
    }


    /**
     * Add a user via json.  POST /users
     *
     * @param model
     * @param strUser
     * @return
     */
    //    @RequestMapping(value = "", headers = {"content-type=application/json", "content-type=application/xml"}, method = RequestMethod.POST)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(ModelMap model, @RequestBody String strUser) {
        JSONObject jsonResponse = new JSONObject();
        int id;
        try {
            System.out.println("Received user =" + strUser);
            JSONObject jsonObj = new JSONObject(strUser);

            jsonObj.remove("id");
            User user = new User(jsonObj);
            System.out.println("Converted user =" + user);

            UserService userService = new UserServiceImpl();
            id = userService.addUser(user);

            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_SUCCESS);
            jsonResponse.put(RESPONSE_DATA, id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put(RESPONSE_STATUS, RESPONSE_STATUS_FAILURE);
            jsonResponse.put(RESPONSE_EXCEPTION, e.getMessage());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<String>(jsonResponse.toString(), responseHeaders, HttpStatus.CREATED);
    }
}
