package com.fonyou.prueba.repository;

import com.fonyou.prueba.entity.ExamenEstudiante;
import com.fonyou.prueba.entity.ExamenEstudiantePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenEstudianteRepository extends JpaRepository<ExamenEstudiante, ExamenEstudiantePK> {
}
