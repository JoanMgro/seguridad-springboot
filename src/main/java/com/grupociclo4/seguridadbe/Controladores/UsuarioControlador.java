package com.grupociclo4.seguridadbe.Controladores;

import com.grupociclo4.seguridadbe.Modelos.Usuario;
import com.grupociclo4.seguridadbe.Modelos.Rol;

import com.grupociclo4.seguridadbe.Repositorios.RolRepositorio;
import com.grupociclo4.seguridadbe.Repositorios.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    @Autowired
    private UsuarioRepositorio repositorio;
    @Autowired
    private RolRepositorio repoRol;
    @GetMapping("")
    public List<Usuario> index(){
        return this.repositorio.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody  Usuario dataUsuario){
        dataUsuario.setClave(convertirSHA256(dataUsuario.getClave()));
        return this.repositorio.save(dataUsuario);
    }
    @GetMapping("{id}")
    public Usuario show(@PathVariable String id){
        Usuario usuarioActual=this.repositorio
                .findById(id)
                .orElse(null);
        return usuarioActual;
    }
    @PutMapping("{id}")
    public Usuario update(@PathVariable String id,@RequestBody  Usuario infoUsuario){
        Usuario usuarioActual=this.repositorio
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setClave(convertirSHA256(infoUsuario.getClave()));
            return this.repositorio.save(usuarioActual);
        }else{
            return null;
        }
    }

    @PutMapping("{id}/rol/{id_rol}")
    public Usuario asignarRolAUsuario(@PathVariable String id, @PathVariable String id_rol){
        Usuario usr = this.repositorio.findById(id).orElse(null);
        Rol rol = this.repoRol.findById(id_rol).orElse(null);
        if(usr != null && rol != null) {
            usr.setRol(rol);
            return this.repositorio.save(usr);
        } else {
            System.out.println("buuu");
            return null;
        }
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Usuario usuarioActual=this.repositorio
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            this.repositorio.delete(usuarioActual);
        }
    }
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}
