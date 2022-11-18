package com.cas;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 自定义cas加密方式  wanghai 2021-06-23
 */
public class MD5PasswordEncoder  implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    /**
     *
     * @param charSequence 前台输入的密码
     * @param s   数据库存的
     * @return 是否通过验证
     */
    @SneakyThrows
    @Override
    public boolean matches(CharSequence charSequence, String s)  {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(charSequence.toString().getBytes());
        String s1 = new BigInteger(1, md.digest()).toString(16);
        return s1.equals(s);
    }
}
