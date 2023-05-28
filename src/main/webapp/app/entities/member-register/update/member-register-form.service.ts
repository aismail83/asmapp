import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMemberRegister, NewMemberRegister } from '../member-register.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMemberRegister for edit and NewMemberRegisterFormGroupInput for create.
 */
type MemberRegisterFormGroupInput = IMemberRegister | PartialWithRequiredKeyOf<NewMemberRegister>;

type MemberRegisterFormDefaults = Pick<NewMemberRegister, 'id'>;

type MemberRegisterFormGroupContent = {
  id: FormControl<IMemberRegister['id'] | NewMemberRegister['id']>;
  lastName: FormControl<IMemberRegister['lastName']>;
  firstName: FormControl<IMemberRegister['firstName']>;
  surName: FormControl<IMemberRegister['surName']>;
  registerDate: FormControl<IMemberRegister['registerDate']>;
  phoneNumber: FormControl<IMemberRegister['phoneNumber']>;
  adresse: FormControl<IMemberRegister['adresse']>;
  email: FormControl<IMemberRegister['email']>;
  etat: FormControl<IMemberRegister['etat']>;
};

export type MemberRegisterFormGroup = FormGroup<MemberRegisterFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MemberRegisterFormService {
  createMemberRegisterFormGroup(memberRegister: MemberRegisterFormGroupInput = { id: null }): MemberRegisterFormGroup {
    const memberRegisterRawValue = {
      ...this.getFormDefaults(),
      ...memberRegister,
    };
    return new FormGroup<MemberRegisterFormGroupContent>({
      id: new FormControl(
        { value: memberRegisterRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      lastName: new FormControl(memberRegisterRawValue.lastName, {
        validators: [Validators.required, Validators.maxLength(50)],
      }),
      firstName: new FormControl(memberRegisterRawValue.firstName, {
        validators: [Validators.required, Validators.maxLength(50)],
      }),
      surName: new FormControl(memberRegisterRawValue.surName, {
        validators: [Validators.required, Validators.maxLength(150)],
      }),
      registerDate: new FormControl(memberRegisterRawValue.registerDate, {
        validators: [Validators.required, Validators.maxLength(10)],
      }),
      
      phoneNumber: new FormControl(memberRegisterRawValue.phoneNumber, {
        validators: [Validators.required, Validators.maxLength(10)],
      }),
      adresse: new FormControl(memberRegisterRawValue.adresse),
      email: new FormControl(memberRegisterRawValue.email, {
        validators: [Validators.required],
      }),
      etat: new FormControl(memberRegisterRawValue.etat),
    });
  }

  getMemberRegister(form: MemberRegisterFormGroup): IMemberRegister | NewMemberRegister {
    return form.getRawValue() as IMemberRegister | NewMemberRegister;
  }

  resetForm(form: MemberRegisterFormGroup, memberRegister: MemberRegisterFormGroupInput): void {
    const memberRegisterRawValue = { ...this.getFormDefaults(), ...memberRegister };
    form.reset(
      {
        ...memberRegisterRawValue,
        id: { value: memberRegisterRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MemberRegisterFormDefaults {
    return {
      id: null,
    };
  }
}
