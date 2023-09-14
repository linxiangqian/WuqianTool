package fun.wuqian.simple.encode;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author 悟纤
 *AES加密方式采用AES-128-ECB加密模式，PKCS5Padding补码，key需要为16位/32位
 *
 *测试地址:http://www.seacha.com/tools/aes.html
 *
 *
 *javaAES加密解密32位密钥：
 */
public class SimpleAES_ECB_128 {
 
    /**
     * 加密
     * @param sKey：key
     * @param sSrc:要加密的原文.
     * @return 加密的内容
     */
    public static String encrypt(String sKey, String sSrc)  {
        checkKey(sKey);
        byte[] raw;
        String result;
        try {
            raw = sKey.getBytes("utf-8");
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            result = Base64.encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
 
    /**
     * 解密
     * @param sKey:key
     * @param sSrc:要解密的密文
     * @return 解密之后的原文
     */
    public static String decrypt(String sKey, String sSrc) {
        checkKey(sKey);
        byte[] raw ;
        String result ;
        try {
            raw = sKey.getBytes("utf-8");
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
            byte[] encrypted1 = Base64.decode(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            result = new String(original,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static void checkKey(String sKey){
        // 判断Key是否正确
        if (sKey == null) {
            throw new InvalidParameterException("SimpleAES_ECB_128.decrypt(),Key为空null");
        }
        // 判断Key是否为16位|32位
        if (sKey.length() != 16 && sKey.length() != 32) {
            throw new InvalidParameterException("Key长度不是16位或者是32位");
        }
    }
}