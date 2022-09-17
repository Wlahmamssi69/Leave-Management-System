package com.pfa.LeaveManagementSystem.controller;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfa.LeaveManagementSystem.model.Role;
import com.pfa.LeaveManagementSystem.model.Utilisateur;
import com.pfa.LeaveManagementSystem.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.pfa.LeaveManagementSystem.utils.constant.USERS_ENDPOINT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController @RequiredArgsConstructor
@RequestMapping(USERS_ENDPOINT)
public class UtilisateurController {

    private UtilisateurService utilisateurService ;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }



    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> getUsers(){
        return ResponseEntity.ok().body(utilisateurService.getUsers());
    }

    @GetMapping("/getUser")
    public ResponseEntity<Utilisateur> getUser(@RequestBody String username){
        return ResponseEntity.ok().body(utilisateurService.getUser(username));
    }

    @PostMapping("/createUser")
    public ResponseEntity<Utilisateur> addUser(@RequestBody Utilisateur user) {
        Utilisateur newUser = utilisateurService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @PostMapping("/createRole")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(utilisateurService.addRole(role));
    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody String username,@RequestBody String roleName) {
        utilisateurService.addRoleToUser(username,roleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader=request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ){

            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Utilisateur user =utilisateurService.getUser(username);

                String access_token= JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens=new HashMap<>();
                tokens.put("refresh_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch(Exception exception){
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String,String> error=new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");

        }
    }

}

