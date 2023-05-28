import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { MemberRegister } from '../member-register.model';

@Component({
  selector: 'jhi-member-register-detail',
  templateUrl: './member-register-detail.component.html',
})
export class MemberRegisterDetailComponent implements OnInit {
  memberRegister: MemberRegister | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ memberRegister }) => {
      this.memberRegister = memberRegister;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
