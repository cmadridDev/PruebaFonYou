package com.fonyou.prueba.repository;

import com.fonyou.prueba.entity.RespuestaExamenEstudiante;
import com.fonyou.prueba.entity.RespuestaExamenEstudiantePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaExamenEstudianteRepository extends JpaRepository<RespuestaExamenEstudiante, RespuestaExamenEstudiantePK> {
    public boolean existsByExamenIdAndEstudianteIdAndPreguntaId(Long examenId, Long estudianteId, Long preguntaId);
}
