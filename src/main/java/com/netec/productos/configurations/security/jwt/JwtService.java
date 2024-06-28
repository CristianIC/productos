package com.netec.productos.configurations.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service(value = "jwtService")
public class JwtService {
	
	private String userGenerator = "APP-PRODUCTOS";
	
	private String tokenSecret = "870ab37afebfa098c19e45af4cbf3817ad2233648ca459330d0f34afa5861431";
	
	private Long expiredToken = (long) (60 * 1000);
	private Long expiredRefreshToken = (long) (120 * 1000);
	
	public String generateAccessToken(UserDetails userDetails) {
		return this.generateToken(userDetails, 15000L);
	}
	
	public String generateRefreshToken(UserDetails userDetails) {
		return this.generateToken(userDetails, this.expiredRefreshToken);
	}
	
	private String generateToken(UserDetails userDetails, Long expireTime) {
		Map<String, Object> claims = new HashMap<>();
		String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
		claims.put("roles", authorities);
		return Jwts.builder()
				.claims()
				.add(claims)
				.issuer(this.userGenerator)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.id(UUID.randomUUID().toString())
				.expiration(new Date(System.currentTimeMillis()  + expireTime))
				.and()
				.signWith(this.getKey(), Jwts.SIG.HS256)
				.compact();
	}
	
	public boolean validToken(String token, UserDetails userDetails) {
		final String username = this.extractUsernameFromToken(token);
		return ((username.equals(userDetails.getUsername())) && !this.isTokenExpired(token) );
	}
	
	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.tokenSecret));
	}
	
	public String extractUsernameFromToken(String token) {
		return this.extractClaim(token, Claims::getSubject);
	}
	
	public boolean isTokenExpired(String token) {
		return this.extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return this.extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = this.getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaims(String token) {
		return Jwts.parser().verifyWith(this.getKey()).build().parseSignedClaims(token).getPayload();
	}

}
