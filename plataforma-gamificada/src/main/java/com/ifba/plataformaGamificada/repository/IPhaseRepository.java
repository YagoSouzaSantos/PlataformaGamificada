package com.ifba.plataformaGamificada.repository;

import com.ifba.plataformaGamificada.model.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhaseRepository extends JpaRepository<Phase, Long> {
}
