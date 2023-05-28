package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MemberRegisterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberRegister.class);
        MemberRegister memberRegister1 = new MemberRegister();
        memberRegister1.setId(1L);
        MemberRegister memberRegister2 = new MemberRegister();
        memberRegister2.setId(memberRegister1.getId());
        assertThat(memberRegister1).isEqualTo(memberRegister2);
        memberRegister2.setId(2L);
        assertThat(memberRegister1).isNotEqualTo(memberRegister2);
        memberRegister1.setId(null);
        assertThat(memberRegister1).isNotEqualTo(memberRegister2);
    }
}
