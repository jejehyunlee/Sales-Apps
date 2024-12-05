package com.aegis.sales_apps.service;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 15:10
@Last Modified 10/2/2023 15:10
Version 1.0
*/


import com.aegis.sales_apps.entity.Role;
import com.aegis.sales_apps.entity.constant.ERole;

public interface RoleService {

    Role getOrSave(ERole eRole);


}
