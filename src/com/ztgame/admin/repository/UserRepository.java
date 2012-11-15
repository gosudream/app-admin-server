package com.ztgame.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ztgame.admin.domain.Role;

@Repository
public interface UserRepository extends JpaRepository<Role, Integer> {
	Page<Role> findByName(String name, Pageable pageable);
}
