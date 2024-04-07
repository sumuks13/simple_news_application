package dev.sumuks.Newspaper.repository;

import dev.sumuks.Newspaper.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantsRepository extends JpaRepository<Tenants, String> {
    List<Tenants> findAll();
}
