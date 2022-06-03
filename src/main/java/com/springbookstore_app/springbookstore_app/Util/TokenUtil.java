//package com.springbookstore_app.springbookstore_app.Util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TokenUtil {
//    private static final String TOKEN_SECRET="sameer";
//    // This method create a token with the id parameter as a claim. It then returns the same token
//    public String createToken(long id) {
//
//        // We create a token using the HMAC256 algorithm and store the id as claim.
//        Algorithm algo = Algorithm.HMAC256(TOKEN_SECRET);
//        String token = JWT.create().withClaim("User_Key", id).sign(algo);
//
//        return token;
//    }
//
//    // This method decodes the passed token and returns the id claim. If the verification fails it will throw an exception
//    public Long decodeToken(String token) throws SignatureVerificationException {
//
//        // We specify the algorithm for the verifier here and then build the verifier in the next step
//       // 1Saml2RelyingPartyProperties.AssertingParty.Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
//        JWTVerifier jwtVerifier = verification.build();
//
//        // We verify and decode the token using the verifier. If the token is incorrect it will throw an exception.
//        DecodedJWT decodedJWT = jwtVerifier.verify(token);
//
//        // We extract the claim from the decoded token and the convert the claim to long type. We then return this id.
//        Claim idClaim = decodedJWT.getClaim("id_key");
//        Long id = idClaim.asLong();
//
//        return id;
//    }
//}
