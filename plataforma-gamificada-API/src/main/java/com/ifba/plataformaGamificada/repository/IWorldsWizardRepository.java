package com.ifba.plataformaGamificada.repository;

import com.ifba.plataformaGamificada.model.WorldsWizard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWorldsWizardRepository extends JpaRepository<WorldsWizard, Long> {

    Optional<WorldsWizard> findByTitle(String title);
}
