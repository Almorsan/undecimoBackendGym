package com.almorsan.gimnasio.repositories;

import com.almorsan.gimnasio.models.Entrenador;
import com.almorsan.gimnasio.models.Establecimiento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {
    
       boolean existsByEntrenadorSupervisor(Entrenador entrenadorSupervisor);
       
       public Optional<Establecimiento> findByEntrenadorSupervisor(Entrenador entrenadorSupervisor);

}
