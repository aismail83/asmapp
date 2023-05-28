import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../member-register.test-samples';

import { MemberRegisterFormService } from './member-register-form.service';

describe('MemberRegister Form Service', () => {
  let service: MemberRegisterFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MemberRegisterFormService);
  });

  describe('Service methods', () => {
    describe('createMemberRegisterFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMemberRegisterFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            lastName: expect.any(Object),
            firstName: expect.any(Object),
            surName: expect.any(Object),
            registerDate: expect.any(Object),
            memberNumber: expect.any(Object),
            phoneNumber: expect.any(Object),
            adresse: expect.any(Object),
            email: expect.any(Object),
            etat: expect.any(Object),
          })
        );
      });

      it('passing IMemberRegister should create a new form with FormGroup', () => {
        const formGroup = service.createMemberRegisterFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            lastName: expect.any(Object),
            firstName: expect.any(Object),
            surName: expect.any(Object),
            registerDate: expect.any(Object),
            memberNumber: expect.any(Object),
            phoneNumber: expect.any(Object),
            adresse: expect.any(Object),
            email: expect.any(Object),
            etat: expect.any(Object),
          })
        );
      });
    });

    describe('getMemberRegister', () => {
      it('should return NewMemberRegister for default MemberRegister initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMemberRegisterFormGroup(sampleWithNewData);

        const memberRegister = service.getMemberRegister(formGroup) as any;

        expect(memberRegister).toMatchObject(sampleWithNewData);
      });

      it('should return NewMemberRegister for empty MemberRegister initial value', () => {
        const formGroup = service.createMemberRegisterFormGroup();

        const memberRegister = service.getMemberRegister(formGroup) as any;

        expect(memberRegister).toMatchObject({});
      });

      it('should return IMemberRegister', () => {
        const formGroup = service.createMemberRegisterFormGroup(sampleWithRequiredData);

        const memberRegister = service.getMemberRegister(formGroup) as any;

        expect(memberRegister).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMemberRegister should not enable id FormControl', () => {
        const formGroup = service.createMemberRegisterFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMemberRegister should disable id FormControl', () => {
        const formGroup = service.createMemberRegisterFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
