package io.torres.ddunddun.repository;

import io.torres.ddunddun.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserName(String userName);
    Optional<Member> findByEmailId(String emailId);

}
