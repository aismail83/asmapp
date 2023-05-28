import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMemberRegister } from '../member-register.model';
import { MemberRegisterService } from '../service/member-register.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './member-register-delete-dialog.component.html',
})
export class MemberRegisterDeleteDialogComponent {
  memberRegister?: IMemberRegister;

  constructor(protected memberRegisterService: MemberRegisterService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.memberRegisterService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
