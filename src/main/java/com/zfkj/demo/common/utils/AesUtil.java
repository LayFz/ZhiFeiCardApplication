package com.zfkj.demo.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 加解密
 */
public class AesUtil {

    private static final String KEY = "WM3i#o4jH5sRiWE8";

    /**
     * 加密
     *
     * @param sSrc
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc) {
        if (StringUtils.isBlank(sSrc)) {
            return null;
        }
        try {
            byte[] raw = KEY.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
            //此处使用BASE64做转码功能，同时能起到2次加密的作用。
            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            return sSrc;
        }
    }

    /**
     * 解密
     *
     * @param sSrc
     * @return
     * @throws Exception
     */
    public static String decrypt(String sSrc) {
        try {
            if (StringUtils.isBlank(sSrc)) {
                return null;
            }
            byte[] raw = KEY.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64解密
            byte[] encrypted1 = new Base64().decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, StandardCharsets.UTF_8);
                return originalString;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        String admin = encrypt("9/iTO1Yx63duuPUaYyJdUQ==");
        System.out.println("9/iTO1Yx63duuPUaYyJdUQ==");
        System.out.println(decrypt("9/iTO1Yx63duuPUaYyJdUQ=="));
    }
}
