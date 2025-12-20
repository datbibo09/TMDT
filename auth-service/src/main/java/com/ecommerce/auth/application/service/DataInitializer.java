package com.ecommerce.auth.application.service;

import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataInitializer {

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        if (User.count() == 0) {
            User admin = new User();
            admin.username = "admin";
            admin.password = BcryptUtil.bcryptHash("admin123");
            admin.fullName = "System Administrator";
            admin.gender = "NAM";
            admin.role = Role.ADMIN;
            admin.persist();
            System.out.println(">>> [INIT] Tạo tài khoản ADMIN thành công: admin / admin123");
        }
    }
}