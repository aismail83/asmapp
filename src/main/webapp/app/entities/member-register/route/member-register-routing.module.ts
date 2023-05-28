import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MemberRegisterComponent } from '../list/member-register.component';
import { MemberRegisterDetailComponent } from '../detail/member-register-detail.component';
import { MemberRegisterUpdateComponent } from '../update/member-register-update.component';
import { MemberRegisterRoutingResolveService } from './member-register-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const memberRegisterRoute: Routes = [
  {
    path: '',
    component: MemberRegisterComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MemberRegisterDetailComponent,
    resolve: {
      memberRegister: MemberRegisterRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MemberRegisterUpdateComponent,
    resolve: {
      memberRegister: MemberRegisterRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MemberRegisterUpdateComponent,
    resolve: {
      memberRegister: MemberRegisterRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(memberRegisterRoute)],
  exports: [RouterModule],
})
export class MemberRegisterRoutingModule {}
