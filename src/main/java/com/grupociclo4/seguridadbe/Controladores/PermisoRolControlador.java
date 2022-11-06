package com.grupociclo4.seguridadbe.Controladores;

import com.grupociclo4.seguridadbe.Modelos.Permiso;
import com.grupociclo4.seguridadbe.Modelos.PermisosRol;
import com.grupociclo4.seguridadbe.Modelos.Rol;

import com.grupociclo4.seguridadbe.Repositorios.PermisoRolRepositorio;
import com.grupociclo4.seguridadbe.Repositorios.RolRepositorio;
import com.grupociclo4.seguridadbe.Repositorios.PermisoRepositorio;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")
public class PermisoRolControlador {

    @Autowired
    private PermisoRolRepositorio repoPermisoRol;
    @Autowired
    private RolRepositorio repoRol;

    @Autowired
    private PermisoRepositorio repoPermiso;

    @GetMapping("")
    public List<PermisosRol> index(){
        return this.repoPermisoRol.findAll();
    }


    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRol create(@PathVariable String id_rol, @PathVariable String id_permiso) {
        PermisosRol pr = new PermisosRol();
        Rol rol = this.repoRol.findById(id_rol).orElse(null);
        Permiso per = this.repoPermiso.findById(id_permiso).orElse(null);

        if(rol != null && per != null) {
            pr.setRol(rol);
            pr.setPermiso(per);
            return  this.repoPermisoRol.save(pr);
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public PermisosRol show(@PathVariable String id){
        PermisosRol permisosRolesActual=this.repoPermisoRol
                .findById(id)
                .orElse(null);
        return permisosRolesActual;
    }

    /**
     * Modificación Rol y Permiso
     * @param id
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRol update(@PathVariable String id,@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRol permisosRolesActual=this.repoPermisoRol
                .findById(id)
                .orElse(null);
        Rol elRol=this.repoRol.findById(id_rol).get();
        Permiso elPermiso=this.repoPermiso.findById(id_permiso).get();
        if(permisosRolesActual!=null && elPermiso!=null && elRol!=null){
            permisosRolesActual.setPermiso(elPermiso);
            permisosRolesActual.setRol(elRol);
            return this.repoPermisoRol.save(permisosRolesActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisosRol permisosRolesActual=this.repoPermisoRol
                .findById(id)
                .orElse(null);
        if (permisosRolesActual!=null){
            this.repoPermisoRol.delete(permisosRolesActual);
        }
    }
}
