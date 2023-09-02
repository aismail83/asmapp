import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMemberRegister, NewMemberRegister } from '../member-register.model';

export type PartialUpdateMemberRegister = Partial<IMemberRegister> & Pick<IMemberRegister, 'id'>;

export type EntityResponseType = HttpResponse<IMemberRegister>;
export type EntityArrayResponseType = HttpResponse<IMemberRegister[]>;

@Injectable({ providedIn: 'root' })
export class MemberRegisterService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/member-registers');
  
  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(memberRegister: NewMemberRegister): Observable<EntityResponseType> {
    return this.http.post<IMemberRegister>(this.resourceUrl, memberRegister, { observe: 'response' });
   
  }

  update(memberRegister: IMemberRegister): Observable<EntityResponseType> {
    return this.http.put<IMemberRegister>(`${this.resourceUrl}/${this.getMemberRegisterIdentifier(memberRegister)}`, memberRegister, {
      observe: 'response',
    });
  }

  partialUpdate(memberRegister: PartialUpdateMemberRegister): Observable<EntityResponseType> {
    return this.http.patch<IMemberRegister>(`${this.resourceUrl}/${this.getMemberRegisterIdentifier(memberRegister)}`, memberRegister, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMemberRegister>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMemberRegister[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMemberRegisterIdentifier(memberRegister: Pick<IMemberRegister, 'id'>): number {
    return memberRegister.id;
  }

  compareMemberRegister(o1: Pick<IMemberRegister, 'id'> | null, o2: Pick<IMemberRegister, 'id'> | null): boolean {
    return o1 && o2 ? this.getMemberRegisterIdentifier(o1) === this.getMemberRegisterIdentifier(o2) : o1 === o2;
  }

  addMemberRegisterToCollectionIfMissing<Type extends Pick<IMemberRegister, 'id'>>(
    memberRegisterCollection: Type[],
    ...memberRegistersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const memberRegisters: Type[] = memberRegistersToCheck.filter(isPresent);
    if (memberRegisters.length > 0) {
      const memberRegisterCollectionIdentifiers = memberRegisterCollection.map(
        memberRegisterItem => this.getMemberRegisterIdentifier(memberRegisterItem)!
      );
      const memberRegistersToAdd = memberRegisters.filter(memberRegisterItem => {
        const memberRegisterIdentifier = this.getMemberRegisterIdentifier(memberRegisterItem);
        if (memberRegisterCollectionIdentifiers.includes(memberRegisterIdentifier)) {
          return false;
        }
        memberRegisterCollectionIdentifiers.push(memberRegisterIdentifier);
        return true;
      });
      return [...memberRegistersToAdd, ...memberRegisterCollection];
    }
    return memberRegisterCollection;
  }
}
