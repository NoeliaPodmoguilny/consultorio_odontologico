package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.dto.auth.AuthLoginRequestDTO;
import com.consultorio.tooth.dto.auth.AuthResponseDTO;
import com.consultorio.tooth.model.Rol;
import com.consultorio.tooth.model.Usuario;
import com.consultorio.tooth.repository.interfaces.IUsuarioRepository;
import com.consultorio.tooth.utils.JwtUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUsuarioRepository userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // TENEMOS EL USUARIO Y NECESITAMOS DEVOLVER UN USERDETAILS
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Traemos el usuario de la base de datos usando el username
        Usuario usuario = userRepo.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario  " + username + " no fue encontrado"));

        // Creamos una lista de GrantedAuthority para el rol del usuario
        // CON ESTA CLASE SPRING SECURITY MANEJA LOS PERMISOS
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // Obtenemos el rol único del usuario
        Rol rol = usuario.getRol();

        // TOMAMOS EL ROL Y LO CONVERTIRMOS A  SimpleGrantedAuthority (con el prefijo "ROLE_" que usa Spring Security) Y LO AGREGAMOS A LA LISTA
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(rol.getRol())));

        // PROGRAMCION FUNCIONAL
        // También agregamos los permisos asociados al rol A LA MISMA LISTA
        rol.getPermissionList().forEach(permission
                -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName()))
        );

        // Retornamos el USER en formato Spring Security con los datos del usuario
        return new User(usuario.getUsername(),
                usuario.getContrasenia(),
                usuario.isEnabled(),
                usuario.isAcountNotExpired(),
                usuario.isCredentialNotExpired(),
                usuario.isAcountNotLocked(),
                authorityList);
    }

    //Metodo loginUser
    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequest) {

        //recuperamos nombre de usuario y contraseña
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);

        //si todo sale bien
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "login ok", accessToken, true);

        return authResponseDTO;

    }

    //Metodo authenticate()
    public Authentication authenticate(String username, String password) {
        //buscamos el usuario
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Contraseña y/o usuario incorrectos");
        }
        // manejo de error, si el pass no es igual
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña y/o usuario incorrectos");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
    
    

}
