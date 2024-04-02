package com.favcode.favschool.repository;

import com.favcode.favschool.model.FavClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavClassRepository extends JpaRepository<FavClass, Integer> {
}
