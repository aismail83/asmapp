package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.MemberRegister;
import com.mycompany.myapp.service.dto.MemberRegisterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MemberRegister} and its DTO {@link MemberRegisterDTO}.
 */
@Mapper(componentModel = "spring")
public interface MemberRegisterMapper extends EntityMapper<MemberRegisterDTO, MemberRegister> {}
