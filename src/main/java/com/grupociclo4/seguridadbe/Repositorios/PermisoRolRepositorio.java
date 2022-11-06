package com.grupociclo4.seguridadbe.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.grupociclo4.seguridadbe.Modelos.PermisosRol;

public interface PermisoRolRepositorio extends MongoRepository<PermisosRol, String> {
}
