import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'member-register',
        data: { pageTitle: 'asmappApp.memberRegister.home.title' },
        loadChildren: () => import('./member-register/member-register.module').then(m => m.MemberRegisterModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
