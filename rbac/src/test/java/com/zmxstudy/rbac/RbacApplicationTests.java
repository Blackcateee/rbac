package com.zmxstudy.rbac;

import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class RbacApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

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

    @Test
    public void testRegister(){
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123456");
        userService.register(user);
    }

    @Test
    public void testEditUser() {
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("1234567");
        userService.editUser(user);
    }
}
