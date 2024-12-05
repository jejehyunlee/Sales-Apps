package com.aegis.sales_apps.service.impl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/3/2023 09:19
@Last Modified 10/3/2023 09:19
Version 1.0
*/

import com.aegis.sales_apps.entity.UserCredential;
import com.aegis.sales_apps.entity.UserDetailImpl;
import com.aegis.sales_apps.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        // Ambil authorities dari UserCredential dan konversi ke GrantedAuthority
        List<GrantedAuthority> authorities = userCredential.getRoles().stream()  // Asumsi bahwa getRoles() mengembalikan daftar role
                .map(Role -> new SimpleGrantedAuthority(Role.getRole().name()))  // Ambil nama role
                .collect(Collectors.toList());

        // Kembalikan UserDetailImpl dengan authorities
        return UserDetailImpl.builder()
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .authorities(authorities)  // Pastikan otoritas diatur
                .build();
    }
}
