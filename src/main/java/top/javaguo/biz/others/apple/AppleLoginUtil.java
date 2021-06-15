//package top.javaguo.biz.others.apple;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.auth0.jwk.InvalidPublicKeyException;
//import com.auth0.jwk.Jwk;
//import io.jsonwebtoken.*;
//import org.apache.tomcat.util.codec.binary.Base64;
//import top.javaguo.utils.GuoHttpRequestUtil;
//
//import java.security.PublicKey;
//
///**苹果授权登陆相关工具类
// *
// */
//public class AppleLoginUtil {
//
//
//
//    public String appleAuth(String jwt) {
//        String sendGet = GuoHttpRequestUtil.sendGet("https://appleid.apple.com/auth/keys", null);
//        System.out.println(sendGet);
//        String keys = JSON.parseObject(sendGet).getString("keys");
//        JSONArray arr = JSONObject.parseArray(keys);
//        JSONObject jsonObject1 = JSONObject.parseObject(arr.getString(0));
//        Jwk jwa = Jwk.fromValues(jsonObject1);
//        try {
//            // 生成苹果公钥
//            PublicKey publicKey = jwa.getPublicKey();
//
////            String hearder = new String(Base64.decodeBase64(jwt.split("\\.")[0]));
//            if (jwt.split("\\.").length > 1) {
//                String claim = new String(Base64.decodeBase64(jwt.split("\\.")[1]));
//                String aud = JSONObject.parseObject(claim).get("aud").toString();
//                String sub = JSONObject.parseObject(claim).get("sub").toString();
//                String verify = verify(publicKey, jwt, aud, sub);
//                if(verify.equals("FAIL")){
//                    JSONObject jsonObject2 = JSONObject.parseObject(arr.getString(1));
//                    jwa = Jwk.fromValues(jsonObject2);
//                    publicKey = jwa.getPublicKey();
//                    verify = verify(publicKey, jwt, aud, sub);
//                }
//                return verify;
//            }
//            return "FAIL";
//        } catch (InvalidPublicKeyException e) {
//            System.out.println("转换公钥失败");
//        }
//        return null;
//    }
//
//    public static String verify(PublicKey key, String jwt, String audience, String subject) {
//        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
//        jwtParser.requireIssuer("https://appleid.apple.com");
//        jwtParser.requireAudience(audience);
//        jwtParser.requireSubject(subject);
//        try {
//            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
//            if (claim != null && claim.getBody().containsKey("auth_time")) {
//                return "SUCCESS";
//            }
//            return "FAIL";
//        } catch (ExpiredJwtException e) {
//            System.out.println("token过期");
//        } catch (Exception e) {
//            System.out.println("token非法");
//        }
//        return "FAIL";
//    }
//
//
//
//    public static void main(String[] args) {
//
//    }
//
//}
