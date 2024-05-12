package team08.issuetracker.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team08.issuetracker.member.model.Member;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationTime}")
    private long expirationTime;

    public String createJwtToken(Member member) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS512")
                .setSubject("access-token")
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("member_id", member.getMemberId())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public void parseJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        System.out.println("JWT Subject : " + claims.getSubject());

        System.out.println("JWT Token MemberId : " + claims.get("member_id"));
    }


}
