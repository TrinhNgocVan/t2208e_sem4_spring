package org.example.utils.encryption;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JWTUtils {
    private static final String secretKey = "3123525325252523525325325252523525322552532523523";
    public static void main(String[] args) {
        String token  = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJjbGFzcyI6InQyMjA0ZSJ9.8fg5y2mfNgCVI-IJumKZ1NiUnLoa2eMRxUG-I2ga3EE";
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0].getBytes())) ;
        System.err.println(header);
        String payload  = new String(decoder.decode(chunks[1].getBytes())) ;
        System.err.println(payload);
        boolean isValidToken  = verifyToken(token);
        System.err.println("Token is" + (isValidToken ?" valid" : " not valid"));
    }
    public static boolean verifyToken(String token){
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
        JwtParser jwtParser = Jwts.parser()
                .verifyWith(secretKeySpec)
                .build();
        boolean isResult  = false;
        try {
            jwtParser.parse(token);
            return true;
        }catch (Exception ex){
            System.err.println(ex);
        }
        return isResult;
    }

}
