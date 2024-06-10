package com.zmxstudy.rbac;

import com.zmxstudy.rbac.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class RbacApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("123456"));
    }


    @Test
    public void test() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
    }
}
