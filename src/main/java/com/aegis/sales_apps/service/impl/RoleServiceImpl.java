package com.aegis.sales_apps.service.impl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 15:12
@Last Modified 10/2/2023 15:12
Version 1.0
*/

import com.aegis.sales_apps.entity.Role;
import com.aegis.sales_apps.entity.constant.ERole;
import com.aegis.sales_apps.repository.RoleRepository;
import com.aegis.sales_apps.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role).orElseGet(()-> roleRepository.save(Role.builder()
                .role(role)
                .build()));
    }
}
