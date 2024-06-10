package com.zmxstudy.rbac;

import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.service.AuthService;
import com.zmxstudy.rbac.service.RoleService;
import com.zmxstudy.rbac.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class RbacApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    RoleService roleService;

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

    @Test
    public void testAddRole() {
        List<Auth> auths = authService.getAuths();
        List<Long> collect = auths.stream().map(Auth::getId).collect(Collectors.toList());
        Role role = new Role();
        role.setId(4L);
        role.setRolename("test");
        role.setIsDeleted(false);
        role.setAuthIds(collect);
        roleService.addRole(role, role.getAuthIds());
    }

    @Test
    public void testDelRole() {
        roleService.deleteRole(4L);
    }

    @Test
    public void testAuth() {
        Auth auth = new Auth();
        auth.setIsDeleted(false);
        auth.setName("test");
        auth.setPath("/test");
        auth.setPid(1L);
        auth.setUri("/test");
        auth.setId(100L);
        authService.addAuth(auth);

        List<Auth> auths = authService.getAuths();
        System.out.println(auths);
        auth.setUri("/testEdit");
        authService.editAuth(auth);
        auths = authService.getAuths();
        System.out.println(auths);
        authService.deleteAuth(auth.getId());
        auths = authService.getAuths();
        System.out.println(auths);

    }
}
