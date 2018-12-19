package vn.toancauxanh.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import vn.toancauxanh.model.KeyApi;

public interface KeyApiRepository extends JpaRepository<KeyApi, Long>, QueryDslPredicateExecutor<KeyApi> {
	@Query("select ka from KeyApi ka where ka.daXoa = false and ka.keyApi = ?1")
	KeyApi getByKey(String keyApi);
}
