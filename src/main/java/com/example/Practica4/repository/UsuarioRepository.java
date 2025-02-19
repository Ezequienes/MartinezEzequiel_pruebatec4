package com.example.Practica4.repository;

import com.example.Practica4.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username); // Método para buscar usuarios por nombre de usuario
}
