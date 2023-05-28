package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.domain.Membre;
import com.mycompany.myapp.repository.MembreRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.MembreService;
import com.mycompany.myapp.service.dto.MembreDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.errors.EmailAlreadyUsedException;
import com.mycompany.myapp.web.rest.errors.LoginAlreadyUsedException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Collections;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;


@RestController
@RequestMapping("/")
public class MembreResource {


    private final MembreRepository membreRepository;
    private final MembreService membreService;

    public MembreResource(MembreRepository membreRepository,MembreService membreService) {
        
        this.membreRepository = membreRepository;
        this.membreService = membreService;
        
    }

    @GetMapping("/membres")
    public List<Membre> afficheMembre() {
        return membreRepository.findAll();
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registreMembre(@Valid @RequestBody MembreDTO membreDTO) {
        Membre newmMembre = membreService.registerMembre(membreDTO);
       }
}
