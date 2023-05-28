package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MemberRegister;
import com.mycompany.myapp.repository.MemberRegisterRepository;
import com.mycompany.myapp.service.MemberRegisterService;
import com.mycompany.myapp.service.dto.MemberRegisterDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.MemberRegister}.
 */
@RestController
@RequestMapping("/api")
public class MemberRegisterResource {

    private final Logger log = LoggerFactory.getLogger(MemberRegisterResource.class);

    private static final String ENTITY_NAME = "memberRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberRegisterService memberRegisterService;

    private final MemberRegisterRepository memberRegisterRepository;

    public MemberRegisterResource(MemberRegisterService memberRegisterService, MemberRegisterRepository memberRegisterRepository) {
        this.memberRegisterService = memberRegisterService;
        this.memberRegisterRepository = memberRegisterRepository;
    }

    /**
     * {@code POST  /member-registers} : Create a new memberRegister.
     *
     * @param memberRegisterDTO the memberRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberRegisterDTO, or with status {@code 400 (Bad Request)} if the memberRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/member-registers")
    public ResponseEntity<MemberRegisterDTO> createMemberRegister(@Valid @RequestBody MemberRegisterDTO memberRegisterDTO)
        throws URISyntaxException {
        log.debug("REST request to save MemberRegister : {}", memberRegisterDTO);
        if (memberRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new memberRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MemberRegisterDTO result = memberRegisterService.save(memberRegisterDTO);
        return ResponseEntity
            .created(new URI("/api/member-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /member-registers/:id} : Updates an existing memberRegister.
     *
     * @param id the id of the memberRegisterDTO to save.
     * @param memberRegisterDTO the memberRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the memberRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/member-registers/{id}")
    public ResponseEntity<MemberRegisterDTO> updateMemberRegister(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MemberRegisterDTO memberRegisterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MemberRegister : {}, {}", id, memberRegisterDTO);
        if (memberRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberRegisterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberRegisterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MemberRegisterDTO result = memberRegisterService.update(memberRegisterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /member-registers/:id} : Partial updates given fields of an existing memberRegister, field will ignore if it is null
     *
     * @param id the id of the memberRegisterDTO to save.
     * @param memberRegisterDTO the memberRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the memberRegisterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the memberRegisterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the memberRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/member-registers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MemberRegisterDTO> partialUpdateMemberRegister(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MemberRegisterDTO memberRegisterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MemberRegister partially : {}, {}", id, memberRegisterDTO);
        if (memberRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberRegisterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberRegisterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MemberRegisterDTO> result = memberRegisterService.partialUpdate(memberRegisterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberRegisterDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /member-registers} : get all the memberRegisters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memberRegisters in body.
     */
    @GetMapping("/member-registers")
    public ResponseEntity<List<MemberRegisterDTO>> getAllMemberRegisters(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of MemberRegisters");
        Page<MemberRegisterDTO> page = memberRegisterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /member-registers/:id} : get the "id" memberRegister.
     *
     * @param id the id of the memberRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/member-registers/{id}")
    public ResponseEntity<MemberRegisterDTO> getMemberRegister(@PathVariable Long id) {
        log.debug("REST request to get MemberRegister : {}", id);
        Optional<MemberRegisterDTO> memberRegisterDTO = memberRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(memberRegisterDTO);
    }

    /**
     * {@code DELETE  /member-registers/:id} : delete the "id" memberRegister.
     *
     * @param id the id of the memberRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/member-registers/{id}")
    public ResponseEntity<Void> deleteMemberRegister(@PathVariable Long id) {
        log.debug("REST request to delete MemberRegister : {}", id);
        memberRegisterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
