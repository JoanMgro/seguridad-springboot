package com.grupociclo4.seguridadbe.Repositorios;

import com.grupociclo4.seguridadbe.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.data.mongodb.repository.Query;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {

    @Query("{'correo': ?0}")
    public Usuario getUsuarioByEmail(String correo);
}
