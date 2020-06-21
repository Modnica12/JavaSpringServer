package com.mp.order;

import com.mp.cart.Cart;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    private String username;

    private static String SECRET_KEY;

    private HashMap<String, Long> authTime = new HashMap<>();

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getToken(@ModelAttribute("token") String token,
                                               @ModelAttribute("user") String user,
                                               @ModelAttribute("secret") String secret){
        username = user;
        SECRET_KEY = secret;
        Claims claims = decodeJWT(token);
        if (claims != null)
            authTime.put(user, claims.getIssuedAt().getTime());
        return new ModelAndView("redirect:" + "http://localhost:8080");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Order> getOrders(@RequestParam String token) {
        Claims decoded = decodeJWT(token);
        if(decoded != null)
            if (!token.isEmpty() && token.equals(get("http://localhost:8081/getToken/" + username + "?created=" + authTime.get(username))))
                return orderService.findAll();
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Order> getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PutMapping(value = "/make")
    @ResponseBody
    public void makeOrder(@RequestParam String token){
        Claims decoded = decodeJWT(token);
        if(decoded != null)
            order(token);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deleteOrder(@PathVariable Long id, @RequestBody String token){
        Claims decoded = decodeJWT(token);
        if(decoded != null)
            if (!token.isEmpty() && token.equals(get("http://localhost:8081/getToken/" + username + "?created=" + authTime.get(username)))) {
                Optional<Order> order = orderService.getById(id);
                if (order.isPresent()) {
                    if (order.get().getUser().equals(username))
                        orderService.deleteById(id);
                }
            }
    }

    private void order(String token){
        if (!token.isEmpty() && token.equals(get("http://localhost:8081/getToken/" + username + "?created=" + authTime.get(username)))) {
            Order newOrder = new Order();
            newOrder.setPizzaIds(new Cart().getAllFromCart());
            newOrder.setUser(username);
            orderService.save(newOrder);
        }
    }

    private String get(String uri){
        HttpEntity<String> requestEntity = new HttpEntity<String>("", new HttpHeaders());
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public @ResponseBody
    List<Order> getOrdersOfUser(@RequestParam String token) {
        Claims decoded = decodeJWT(token);
        if (decoded != null) {
            String createdToken = get("http://localhost:8081/getToken/" + username + "?created=" + authTime.get(username));
            System.out.println("\n\n\n" + token);
            System.out.println("\n\n\n" + createdToken);
            if (!token.isEmpty() && token.equals(createdToken))
                return orderService.getAllWithUsername(username);
            else return null;
        }
        else return null;
    }

    private static Claims decodeJWT(String jwt){
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt).getBody();
        }
        catch (io.jsonwebtoken.ExpiredJwtException e){
            return null;
        }
    }

}
