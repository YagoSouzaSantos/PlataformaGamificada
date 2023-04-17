package com.ifba.plataformaGamificada.repository;

import com.ifba.plataformaGamificada.model.Nivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INivelRepository extends JpaRepository<Nivel, Long> {


}
