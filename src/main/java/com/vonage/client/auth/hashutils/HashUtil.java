package com.vonage.client.auth.hashutils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility methods for hashing strings.
 */
public class HashUtil {

    private static Map<HashType, AbstractHasher> hashTypes = new HashMap<HashType, AbstractHasher>() {{
        put(HashType.MD5, new Md5Hasher());
        put(HashType.SHA_1, new Sha1Hasher());
        put(HashType.HMAC_SHA256, new HmacSha256Hasher());
    }};

    /**
     * Calculates hash for string. assume string is UTF-8 encoded.
     * @param input string which is going to be encoded into requested format
     * @param hashType The type of hash to be applied to the input string
     * @return representation of the input string with given hash type
     * @throws NoSuchAlgorithmException if the algorithm is not available.
     */
    public static String calculate(String input, HashType hashType) throws NoSuchAlgorithmException {
        return hashTypes.get(hashType).calculate(input);
    }

    /**
     * Calculates hash for string.
     * @param input string which is going to be encoded into requested format
     * @param secretKey the key to be used for encoding
     * @param encoding character encoding of the string which is going to be encoded into requested format
     * @param hashType The type of hash to be applied to the input string
     * @return representation of the input string with given hash type
     * @throws NoSuchAlgorithmException if the algorithm is not available.
     * @throws UnsupportedEncodingException if the specified encoding is unavailable.
     */
    public static String calculate(String input, String secretKey, String encoding, HashType hashType) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        return hashTypes.get(hashType).calculate(input, secretKey, encoding);
    }

    public enum HashType {
        MD5,
        SHA_1,
        HMAC_SHA256
    }
}
