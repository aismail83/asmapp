import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MemberRegisterComponent } from './list/member-register.component';
import { MemberRegisterDetailComponent } from './detail/member-register-detail.component';
import { MemberRegisterUpdateComponent } from './update/member-register-update.component';
import { MemberRegisterDeleteDialogComponent } from './delete/member-register-delete-dialog.component';
import { MemberRegisterRoutingModule } from './route/member-register-routing.module';

@NgModule({
  imports: [SharedModule, MemberRegisterRoutingModule],
  declarations: [
    MemberRegisterComponent,
    MemberRegisterDetailComponent,
    MemberRegisterUpdateComponent,
    MemberRegisterDeleteDialogComponent,
  ],
})
export class MemberRegisterModule {}
