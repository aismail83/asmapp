package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.repository.MemberRegisterRepository;
import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MemberRegisterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberRegisterDTO.class);
        MemberRegisterDTO memberRegisterDTO1 = new MemberRegisterDTO();
        memberRegisterDTO1.setId(1L);
        MemberRegisterDTO memberRegisterDTO2 = new MemberRegisterDTO();
        assertThat(memberRegisterDTO1).isNotEqualTo(memberRegisterDTO2);
        memberRegisterDTO2.setId(memberRegisterDTO1.getId());
        assertThat(memberRegisterDTO1).isEqualTo(memberRegisterDTO2);
        memberRegisterDTO2.setId(2L);
        assertThat(memberRegisterDTO1).isNotEqualTo(memberRegisterDTO2);
        memberRegisterDTO1.setId(null);
        assertThat(memberRegisterDTO1).isNotEqualTo(memberRegisterDTO2);
    }
}
