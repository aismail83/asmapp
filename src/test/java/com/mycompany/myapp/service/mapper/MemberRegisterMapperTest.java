package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberRegisterMapperTest {

    private MemberRegisterMapper memberRegisterMapper;

    @BeforeEach
    public void setUp() {
        memberRegisterMapper = new MemberRegisterMapperImpl();
    }
}
