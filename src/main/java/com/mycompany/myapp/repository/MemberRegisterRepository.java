package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MemberRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MemberRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberRegisterRepository extends JpaRepository<MemberRegister, Long> {}
