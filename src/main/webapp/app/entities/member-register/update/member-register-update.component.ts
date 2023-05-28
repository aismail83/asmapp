import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { MemberRegisterFormService, MemberRegisterFormGroup } from './member-register-form.service';
import { IMemberRegister } from '../member-register.model';
import { MemberRegisterService } from '../service/member-register.service';

@Component({
  selector: 'jhi-member-register-update',
  templateUrl: './member-register-update.component.html',
})
export class MemberRegisterUpdateComponent implements OnInit {
  isSaving = false;
  memberRegister: IMemberRegister | null = null;

  editForm: MemberRegisterFormGroup = this.memberRegisterFormService.createMemberRegisterFormGroup();

  constructor(
    protected memberRegisterService: MemberRegisterService,
    protected memberRegisterFormService: MemberRegisterFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ memberRegister }) => {
      this.memberRegister = memberRegister;
      if (memberRegister) {
        this.updateForm(memberRegister);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const memberRegister = this.memberRegisterFormService.getMemberRegister(this.editForm);
    if (memberRegister.id !== null) {
      this.subscribeToSaveResponse(this.memberRegisterService.update(memberRegister));
    } else {
      this.subscribeToSaveResponse(this.memberRegisterService.create(memberRegister));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMemberRegister>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(memberRegister: IMemberRegister): void {
    this.memberRegister = memberRegister;
    this.memberRegisterFormService.resetForm(this.editForm, memberRegister);
  }
}
