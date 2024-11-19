package com.almorsan.gimnasio.repositories;

import com.almorsan.gimnasio.models.Actividad;
import com.almorsan.gimnasio.models.Sala;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    public List<Actividad> findBySala(Sala sala);

}
