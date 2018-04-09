package org.springframework.util;

import org.springframework.lang.UsesJava8;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public abstract class Base64Utils {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private static final Base64Delegate delegate;

    static {
        Base64Delegate delegateToUse = null;
        if (ClassUtils.isPresent("java.util.Base64", Base64Utils.class.getClassLoader())) {
            delegateToUse = new JdkBase64Delegate();
        } else if (ClassUtils.isPresent("org.apache.commons.codec.binary.Base64", Base64Utils.class.getClassLoader())) {
            delegateToUse = new CommonsCodecBase64Delegate();
        }

        delegate = delegateToUse;
    }

    private static void assertDelegateAvailable() {
        Assert.state(delegate != null, "Neither Java 8 nor Apache Commons Codec found - Base64 encoding between byte arrays not supported");
    }

    public static byte[] encode(byte[] src) {
        assertDelegateAvailable();
        return delegate.encode(src);
    }

    public static byte[] decode(byte[] src) {
        assertDelegateAvailable();
        return delegate.decode(src);
    }

    public static byte[] encodeUrlSafe(byte[] src) {
        assertDelegateAvailable();
        return delegate.encodeUrlSafe(src);
    }

    public static byte[] decodeUrlSafe(byte[] src) {
        assertDelegateAvailable();
        return delegate.decodeUrlSafe(src);
    }




    interface Base64Delegate {

        byte[] encode(byte[] src);

        byte[] decode(byte[] src);

        byte[] encodeUrlSafe(byte[] src);

        byte[] decodeUrlSafe(byte[] src);
    }


    @UsesJava8
    static class JdkBase64Delegate implements Base64Delegate {
        @Override
        public byte[] encode(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return Base64.getEncoder().encode(src);
        }

        @Override
        public byte[] decode(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return Base64.getDecoder().decode(src);
        }

        @Override
        public byte[] encodeUrlSafe(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return Base64.getUrlEncoder().encode(src);
        }

        @Override
        public byte[] decodeUrlSafe(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return Base64.getUrlDecoder().decode(src);
        }
    }

    static class CommonsCodecBase64Delegate implements Base64Delegate {

        private final org.apache.commons.codec.binary.Base64 base64 =
                new org.apache.commons.codec.binary.Base64();

        private final org.apache.commons.codec.binary.Base64 base64UrlSafe =
                new org.apache.commons.codec.binary.Base64(0, null, true);

        @Override
        public byte[] encode(byte[] src) {
            return this.base64.encode(src);
        }

        @Override
        public byte[] decode(byte[] src) {
            return this.base64.decode(src);
        }

        @Override
        public byte[] encodeUrlSafe(byte[] src) {
            return this.base64UrlSafe.encode(src);
        }

        @Override
        public byte[] decodeUrlSafe(byte[] src) {
            return this.base64UrlSafe.decode(src);
        }
    }

}


