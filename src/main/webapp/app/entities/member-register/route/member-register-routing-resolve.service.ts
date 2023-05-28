import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMemberRegister } from '../member-register.model';
import { MemberRegisterService } from '../service/member-register.service';

@Injectable({ providedIn: 'root' })
export class MemberRegisterRoutingResolveService implements Resolve<IMemberRegister | null> {
  constructor(protected service: MemberRegisterService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMemberRegister | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((memberRegister: HttpResponse<IMemberRegister>) => {
          if (memberRegister.body) {
            return of(memberRegister.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
