package com.grupociclo4.seguridadbe.Repositorios;


import com.grupociclo4.seguridadbe.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolRepositorio extends MongoRepository<Rol, String> {
}
