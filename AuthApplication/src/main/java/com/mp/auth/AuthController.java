package com.mp.auth;

import com.mp.auth.AuthServiceImpl;
import com.mp.auth.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    private static final String SECRET_KEY = "t6w9z$C&F)J@NcRfUjWnZr4u7x!A%D*G-KaPdSgVkYp2s5v8y/B?E(H+MbQeThWm";

    @GetMapping(value = "/")
    @ResponseBody
    public String register() {
        return "Authentication Page";
    }

    @GetMapping(value = "/register")
    @ResponseBody
    public ModelAndView register(@RequestParam String username, @RequestParam String password){
        User newUser = new User();
        newUser.setUsername(username);
        Integer hash = password.hashCode();
        newUser.setHash(hash);
        authService.save(newUser);
        return new ModelAndView("redirect:" + "http://localhost:8080/");
    }

    @GetMapping(value = "/auth")
    @ResponseBody
    public ModelAndView auth(@RequestParam String username, @RequestParam String password, RedirectAttributes rattrs){
        Optional<User> user = authService.getByUsername(username);
        if (user.isPresent()){
            Integer hash = user.get().getHash();
            Integer currentHash = password.hashCode();
            if (hash.equals(currentHash)) {
                String token = getToken(username, System.currentTimeMillis());
                rattrs.addAttribute("token", token);
                rattrs.addAttribute("user", username);
                rattrs.addAttribute("secret", SECRET_KEY);
                return new ModelAndView("redirect:" + "http://localhost:8080/order/token");
            }
            else return new ModelAndView("Incorrect Data");
        }
        else return new ModelAndView("Incorrect Data");
    }

    @GetMapping(value = "/getToken/{user}")
    @ResponseBody
    public String getToken(@PathVariable("user") String username, @RequestParam Long created) {
        return createJWT(username, "KerelPizza", "auth", (long) 120000, created);
    }

    public static String createJWT(String username, String issuer, String subject, Long ttlMillis, Long created){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date now = new Date(created);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("user", username)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis > 0){
            long expMillis = created + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

}
