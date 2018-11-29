package vn.toancauxanh.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import vn.toancauxanh.model.DoanVao;

public interface DoanVaoRepository extends JpaRepository<DoanVao, Long>, QueryDslPredicateExecutor<DoanVao> {
	Optional<DoanVao> getById(Long id);
}
