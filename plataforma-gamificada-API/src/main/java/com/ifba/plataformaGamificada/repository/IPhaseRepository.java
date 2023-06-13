package com.ifba.plataformaGamificada.repository;

import com.ifba.plataformaGamificada.model.Phase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhaseRepository extends JpaRepository<Phase, Long> {

    public Page<Phase> findByWorld(long id, Pageable var1);
}
