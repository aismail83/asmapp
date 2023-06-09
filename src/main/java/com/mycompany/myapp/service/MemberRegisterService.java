package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.MemberRegister;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.MemberRegisterRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.dto.MemberRegisterDTO;
import com.mycompany.myapp.service.dto.AdminUserDTO;
import com.mycompany.myapp.service.mapper.MemberRegisterMapper;
import com.mycompany.myapp.service.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.jhipster.security.RandomUtil;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException; 
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Service Implementation for managing {@link MemberRegister}.
 */
@Service
@Transactional
public class MemberRegisterService {

    private final Logger log = LoggerFactory.getLogger(MemberRegisterService.class);

    private final MemberRegisterRepository memberRegisterRepository;
    private final UserMapper userMapper;
    private final MemberRegisterMapper memberRegisterMapper;
    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberRegisterService(MemberRegisterRepository memberRegisterRepository, 
    MemberRegisterMapper memberRegisterMapper,UserRepository userRepository, 
    UserMapper userMapper,PasswordEncoder passwordEncoder) {
        this.memberRegisterRepository = memberRegisterRepository;
        this.memberRegisterMapper = memberRegisterMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
    }

    /**
     * Save a memberRegister.
     *
     * @param memberRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    public MemberRegisterDTO save(MemberRegisterDTO memberRegisterDTO) {
        log.debug("Request to save MemberRegister : {}", memberRegisterDTO);
        Date  membreNumbreYear= new Date();
        DateFormat outyear = new SimpleDateFormat("yy");
        DateFormat outmonth = new SimpleDateFormat("MM");

        DateFormat inyear = new SimpleDateFormat("dd/MM/yyyy");
        try{
          membreNumbreYear = inyear.parse(memberRegisterDTO.getRegisterDate());
           }catch (Exception e) {
        System.out.println("Erreur format");
        }
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        int idto1 = 1+memberRegisterList.size();
        String idto = String.valueOf(idto1+1000).substring(1);
        String  membreNumbreYearstr = outyear.format(membreNumbreYear);
        String  membreNumbreMonthstr = outmonth.format(membreNumbreYear);
        memberRegisterDTO.setMemberNumber(membreNumbreYearstr+membreNumbreMonthstr+idto);
        MemberRegister memberRegister = memberRegisterMapper.toEntity(memberRegisterDTO);
        memberRegister = memberRegisterRepository.save(memberRegister);

        return memberRegisterMapper.toDto(memberRegister);

    }
        public AdminUserDTO saveAcces(MemberRegisterDTO memberRegisterDTO) {
            log.debug("Request to save MemberRegister : {}", memberRegisterDTO);
            String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        AdminUserDTO userDTO = new AdminUserDTO();
        userDTO.setLogin(memberRegisterDTO.getEmail().toLowerCase());
        userDTO.setFirstName(memberRegisterDTO.getFirstName());
        userDTO.setPassword(encryptedPassword);
        userDTO.setLastName(memberRegisterDTO.getLastName());
        userDTO.setEmail(memberRegisterDTO.getEmail().toLowerCase());
        User user = userMapper.userDTOToUser(userDTO);
         user =userRepository.save(user);
        return  userMapper.userToAdminUserDTO(user);
    }

    

    /**
     * Update a memberRegister.
     *
     * @param memberRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    public MemberRegisterDTO update(MemberRegisterDTO memberRegisterDTO) {
        log.debug("Request to update MemberRegister : {}", memberRegisterDTO);
        MemberRegister memberRegister = memberRegisterMapper.toEntity(memberRegisterDTO);
        memberRegister = memberRegisterRepository.save(memberRegister);
        return memberRegisterMapper.toDto(memberRegister);
    }

    /**
     * Partially update a memberRegister.
     *
     * @param memberRegisterDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MemberRegisterDTO> partialUpdate(MemberRegisterDTO memberRegisterDTO) {
        log.debug("Request to partially update MemberRegister : {}", memberRegisterDTO);

        return memberRegisterRepository
            .findById(memberRegisterDTO.getId())
            .map(existingMemberRegister -> {
                memberRegisterMapper.partialUpdate(existingMemberRegister, memberRegisterDTO);

                return existingMemberRegister;
            })
            .map(memberRegisterRepository::save)
            .map(memberRegisterMapper::toDto);
    }

    /**
     * Get all the memberRegisters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MemberRegisterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MemberRegisters");
        return memberRegisterRepository.findAll(pageable).map(memberRegisterMapper::toDto);
    }

    /**
     * Get one memberRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MemberRegisterDTO> findOne(Long id) {
        log.debug("Request to get MemberRegister : {}", id);
        return memberRegisterRepository.findById(id).map(memberRegisterMapper::toDto);
    }

    /**
     * Delete the memberRegister by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MemberRegister : {}", id);
        memberRegisterRepository.deleteById(id);
    }
}
