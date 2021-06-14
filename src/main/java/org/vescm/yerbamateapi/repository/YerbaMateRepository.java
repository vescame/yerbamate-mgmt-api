package org.vescm.yerbamateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vescm.yerbamateapi.model.YerbaMate;

@Repository
public interface YerbaMateRepository extends JpaRepository<YerbaMate, Long> {
    boolean existsByName(String name);
}
