package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


  @Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    //Crea la sentencia en base al nombre en inglés del método
    //Tmb se puede hacer mediante Query pero en este caso no es necesario
      Optional<Usuario> findUserEntityByUsername(String username);
      


}
