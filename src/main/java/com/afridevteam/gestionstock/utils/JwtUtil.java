package com.afridevteam.gestionstock.utils;

import com.afridevteam.gestionstock.domain.auth.ExtendedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResoler) {
        final Claims claims = extractAllClaims(token);
        return claimsResoler.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(ExtendedUser extendedUser) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, extendedUser);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, ExtendedUser extendedUser) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(extendedUser.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .claim("idEntreprise", extendedUser.getIdEntreprise().toString())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public String extractIdEntreprise(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("idEntreprise", String.class);
    }
}
