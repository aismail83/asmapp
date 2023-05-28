package com.mycompany.myapp.service;

import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.Membre;
import com.mycompany.myapp.repository.AuthorityRepository;
import com.mycompany.myapp.repository.MembreRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.dto.AdminUserDTO;
import com.mycompany.myapp.service.dto.MembreDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

/**
 * Service class for managing users.
 */
@Service
public class MembreService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final MembreRepository membreRepository;

    public MembreService(
        MembreRepository membreRepository
    ) {
        this.membreRepository = membreRepository;
    }

    public Membre registerMembre(MembreDTO membreDTO) {

        SimpleDateFormat year = new SimpleDateFormat("yy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        String  membreNumbreYear = year.format(membreDTO.getRegistreDate());
        String  membreNumbreMonth = month.format(membreDTO.getRegistreDate());

        Membre newMembre = new Membre();
        newMembre.setMembreNumbre(membreNumbreYear+membreNumbreMonth+newMembre.getId());
        newMembre.setFirstName(membreDTO.getFirstName());
        newMembre.setLastName(membreDTO.getLastName());
        newMembre.setNameArabe(membreDTO.getNameArabe());
        newMembre.setRegistreDate(membreDTO.getRegistreDate());
        newMembre.setPhoneNumbre(membreDTO.getPhoneNumbre());
        newMembre.setEmail(membreDTO.getEmail());
        newMembre.setAdresse(membreDTO.getAdresse());
        newMembre.setEtat(membreDTO.getetat());

        membreRepository.save(newMembre);
        log.debug("Created Information for User: {}", newMembre);
        return newMembre;
    }

    
}
