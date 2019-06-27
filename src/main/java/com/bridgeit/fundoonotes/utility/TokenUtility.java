package com.bridgeit.fundoonotes.utility;

import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtility {

	private String secretPin = "##9738672744";

	public  String generateToken(String id){
//		Algorithm algorithm = Algorithm.HMAC256(secretPin);
//		String token = JWT.create().withClaim("ID", id).sign(algorithm);
		String token = Jwts.builder()
                .setSubject("fundooNotes")
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .setId(id)
                .signWith(SignatureAlgorithm.HS256, secretPin)
                .compact();
      return token;
	}
	
	public String verifyToken(String token) 
	{
//		String id;
//		Verification verification=JWT.require(Algorithm.HMAC256(secretPin));
//		JWTVerifier jwtVerifier=verification.build();
//		DecodedJWT decodedJWT=jwtVerifier.verify(token);
//		Claim claim=decodedJWT.getClaim("ID");
//		id=claim.asString();
		 Jws<Claims> claims = Jwts.parser()
		          .setSigningKey(secretPin)
		          .parseClaimsJws(token);
		        String userId = claims.getBody().getId();
		        return userId;
				
	}

}
