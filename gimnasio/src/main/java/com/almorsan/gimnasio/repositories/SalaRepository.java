package com.almorsan.gimnasio.repositories;

import com.almorsan.gimnasio.models.Establecimiento;
import com.almorsan.gimnasio.models.Sala;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    public List<Sala> findByEstablecimiento(Establecimiento establecimiento);

}
