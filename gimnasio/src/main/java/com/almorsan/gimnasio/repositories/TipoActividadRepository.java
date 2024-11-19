package com.almorsan.gimnasio.repositories;

import com.almorsan.gimnasio.models.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad, Long> {

}
