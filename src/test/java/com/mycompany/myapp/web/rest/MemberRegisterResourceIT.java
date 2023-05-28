package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.MemberRegister;
import com.mycompany.myapp.repository.MemberRegisterRepository;
import com.mycompany.myapp.service.dto.MemberRegisterDTO;
import com.mycompany.myapp.service.mapper.MemberRegisterMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MemberRegisterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MemberRegisterResourceIT {

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTER_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT = "AAAAAAAAAA";
    private static final String UPDATED_ETAT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/member-registers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MemberRegisterRepository memberRegisterRepository;

    @Autowired
    private MemberRegisterMapper memberRegisterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMemberRegisterMockMvc;

    private MemberRegister memberRegister;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberRegister createEntity(EntityManager em) {
        MemberRegister memberRegister = new MemberRegister()
            .lastName(DEFAULT_LAST_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .surName(DEFAULT_SUR_NAME)
            .registerDate(DEFAULT_REGISTER_DATE)
            .memberNumber(DEFAULT_MEMBER_NUMBER)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .adresse(DEFAULT_ADRESSE)
            .email(DEFAULT_EMAIL)
            .etat(DEFAULT_ETAT);
        return memberRegister;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberRegister createUpdatedEntity(EntityManager em) {
        MemberRegister memberRegister = new MemberRegister()
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .surName(UPDATED_SUR_NAME)
            .registerDate(UPDATED_REGISTER_DATE)
            .memberNumber(UPDATED_MEMBER_NUMBER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .adresse(UPDATED_ADRESSE)
            .email(UPDATED_EMAIL)
            .etat(UPDATED_ETAT);
        return memberRegister;
    }

    @BeforeEach
    public void initTest() {
        memberRegister = createEntity(em);
    }

    @Test
    @Transactional
    void createMemberRegister() throws Exception {
        int databaseSizeBeforeCreate = memberRegisterRepository.findAll().size();
        // Create the MemberRegister
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);
        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        MemberRegister testMemberRegister = memberRegisterList.get(memberRegisterList.size() - 1);
        assertThat(testMemberRegister.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testMemberRegister.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testMemberRegister.getSurName()).isEqualTo(DEFAULT_SUR_NAME);
        assertThat(testMemberRegister.getRegisterDate()).isEqualTo(DEFAULT_REGISTER_DATE);
        assertThat(testMemberRegister.getMemberNumber()).isEqualTo(DEFAULT_MEMBER_NUMBER);
        assertThat(testMemberRegister.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testMemberRegister.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMemberRegister.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMemberRegister.getEtat()).isEqualTo(DEFAULT_ETAT);
    }

    @Test
    @Transactional
    void createMemberRegisterWithExistingId() throws Exception {
        // Create the MemberRegister with an existing ID
        memberRegister.setId(1L);
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        int databaseSizeBeforeCreate = memberRegisterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRegisterRepository.findAll().size();
        // set the field null
        memberRegister.setLastName(null);

        // Create the MemberRegister, which fails.
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRegisterRepository.findAll().size();
        // set the field null
        memberRegister.setFirstName(null);

        // Create the MemberRegister, which fails.
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSurNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRegisterRepository.findAll().size();
        // set the field null
        memberRegister.setSurName(null);

        // Create the MemberRegister, which fails.
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRegisterDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRegisterRepository.findAll().size();
        // set the field null
        memberRegister.setRegisterDate(null);

        // Create the MemberRegister, which fails.
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRegisterRepository.findAll().size();
        // set the field null
        memberRegister.setPhoneNumber(null);

        // Create the MemberRegister, which fails.
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberRegisterRepository.findAll().size();
        // set the field null
        memberRegister.setEmail(null);

        // Create the MemberRegister, which fails.
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        restMemberRegisterMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMemberRegisters() throws Exception {
        // Initialize the database
        memberRegisterRepository.saveAndFlush(memberRegister);

        // Get all the memberRegisterList
        restMemberRegisterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(memberRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].surName").value(hasItem(DEFAULT_SUR_NAME)))
            .andExpect(jsonPath("$.[*].registerDate").value(hasItem(DEFAULT_REGISTER_DATE)))
            .andExpect(jsonPath("$.[*].memberNumber").value(hasItem(DEFAULT_MEMBER_NUMBER)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT)));
    }

    @Test
    @Transactional
    void getMemberRegister() throws Exception {
        // Initialize the database
        memberRegisterRepository.saveAndFlush(memberRegister);

        // Get the memberRegister
        restMemberRegisterMockMvc
            .perform(get(ENTITY_API_URL_ID, memberRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(memberRegister.getId().intValue()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.surName").value(DEFAULT_SUR_NAME))
            .andExpect(jsonPath("$.registerDate").value(DEFAULT_REGISTER_DATE))
            .andExpect(jsonPath("$.memberNumber").value(DEFAULT_MEMBER_NUMBER))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT));
    }

    @Test
    @Transactional
    void getNonExistingMemberRegister() throws Exception {
        // Get the memberRegister
        restMemberRegisterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMemberRegister() throws Exception {
        // Initialize the database
        memberRegisterRepository.saveAndFlush(memberRegister);

        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();

        // Update the memberRegister
        MemberRegister updatedMemberRegister = memberRegisterRepository.findById(memberRegister.getId()).get();
        // Disconnect from session so that the updates on updatedMemberRegister are not directly saved in db
        em.detach(updatedMemberRegister);
        updatedMemberRegister
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .surName(UPDATED_SUR_NAME)
            .registerDate(UPDATED_REGISTER_DATE)
            .memberNumber(UPDATED_MEMBER_NUMBER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .adresse(UPDATED_ADRESSE)
            .email(UPDATED_EMAIL)
            .etat(UPDATED_ETAT);
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(updatedMemberRegister);

        restMemberRegisterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, memberRegisterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isOk());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
        MemberRegister testMemberRegister = memberRegisterList.get(memberRegisterList.size() - 1);
        assertThat(testMemberRegister.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testMemberRegister.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testMemberRegister.getSurName()).isEqualTo(UPDATED_SUR_NAME);
        assertThat(testMemberRegister.getRegisterDate()).isEqualTo(UPDATED_REGISTER_DATE);
        assertThat(testMemberRegister.getMemberNumber()).isEqualTo(UPDATED_MEMBER_NUMBER);
        assertThat(testMemberRegister.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testMemberRegister.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMemberRegister.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMemberRegister.getEtat()).isEqualTo(UPDATED_ETAT);
    }

    @Test
    @Transactional
    void putNonExistingMemberRegister() throws Exception {
        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();
        memberRegister.setId(count.incrementAndGet());

        // Create the MemberRegister
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemberRegisterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, memberRegisterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMemberRegister() throws Exception {
        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();
        memberRegister.setId(count.incrementAndGet());

        // Create the MemberRegister
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberRegisterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMemberRegister() throws Exception {
        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();
        memberRegister.setId(count.incrementAndGet());

        // Create the MemberRegister
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberRegisterMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMemberRegisterWithPatch() throws Exception {
        // Initialize the database
        memberRegisterRepository.saveAndFlush(memberRegister);

        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();

        // Update the memberRegister using partial update
        MemberRegister partialUpdatedMemberRegister = new MemberRegister();
        partialUpdatedMemberRegister.setId(memberRegister.getId());

        partialUpdatedMemberRegister
            .lastName(UPDATED_LAST_NAME)
            .surName(UPDATED_SUR_NAME)
            .memberNumber(UPDATED_MEMBER_NUMBER)
            .adresse(UPDATED_ADRESSE)
            .email(UPDATED_EMAIL);

        restMemberRegisterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMemberRegister.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberRegister))
            )
            .andExpect(status().isOk());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
        MemberRegister testMemberRegister = memberRegisterList.get(memberRegisterList.size() - 1);
        assertThat(testMemberRegister.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testMemberRegister.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testMemberRegister.getSurName()).isEqualTo(UPDATED_SUR_NAME);
        assertThat(testMemberRegister.getRegisterDate()).isEqualTo(DEFAULT_REGISTER_DATE);
        assertThat(testMemberRegister.getMemberNumber()).isEqualTo(UPDATED_MEMBER_NUMBER);
        assertThat(testMemberRegister.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testMemberRegister.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMemberRegister.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMemberRegister.getEtat()).isEqualTo(DEFAULT_ETAT);
    }

    @Test
    @Transactional
    void fullUpdateMemberRegisterWithPatch() throws Exception {
        // Initialize the database
        memberRegisterRepository.saveAndFlush(memberRegister);

        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();

        // Update the memberRegister using partial update
        MemberRegister partialUpdatedMemberRegister = new MemberRegister();
        partialUpdatedMemberRegister.setId(memberRegister.getId());

        partialUpdatedMemberRegister
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .surName(UPDATED_SUR_NAME)
            .registerDate(UPDATED_REGISTER_DATE)
            .memberNumber(UPDATED_MEMBER_NUMBER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .adresse(UPDATED_ADRESSE)
            .email(UPDATED_EMAIL)
            .etat(UPDATED_ETAT);

        restMemberRegisterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMemberRegister.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberRegister))
            )
            .andExpect(status().isOk());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
        MemberRegister testMemberRegister = memberRegisterList.get(memberRegisterList.size() - 1);
        assertThat(testMemberRegister.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testMemberRegister.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testMemberRegister.getSurName()).isEqualTo(UPDATED_SUR_NAME);
        assertThat(testMemberRegister.getRegisterDate()).isEqualTo(UPDATED_REGISTER_DATE);
        assertThat(testMemberRegister.getMemberNumber()).isEqualTo(UPDATED_MEMBER_NUMBER);
        assertThat(testMemberRegister.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testMemberRegister.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMemberRegister.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMemberRegister.getEtat()).isEqualTo(UPDATED_ETAT);
    }

    @Test
    @Transactional
    void patchNonExistingMemberRegister() throws Exception {
        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();
        memberRegister.setId(count.incrementAndGet());

        // Create the MemberRegister
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemberRegisterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, memberRegisterDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMemberRegister() throws Exception {
        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();
        memberRegister.setId(count.incrementAndGet());

        // Create the MemberRegister
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberRegisterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMemberRegister() throws Exception {
        int databaseSizeBeforeUpdate = memberRegisterRepository.findAll().size();
        memberRegister.setId(count.incrementAndGet());

        // Create the MemberRegister
        MemberRegisterDTO memberRegisterDTO = memberRegisterMapper.toDto(memberRegister);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberRegisterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(memberRegisterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MemberRegister in the database
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMemberRegister() throws Exception {
        // Initialize the database
        memberRegisterRepository.saveAndFlush(memberRegister);

        int databaseSizeBeforeDelete = memberRegisterRepository.findAll().size();

        // Delete the memberRegister
        restMemberRegisterMockMvc
            .perform(delete(ENTITY_API_URL_ID, memberRegister.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MemberRegister> memberRegisterList = memberRegisterRepository.findAll();
        assertThat(memberRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
