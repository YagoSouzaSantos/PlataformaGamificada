package com.ifba.plataformaGamificada.repository;

import com.ifba.plataformaGamificada.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findByPhase(Long phase, Pageable pageable);
}
