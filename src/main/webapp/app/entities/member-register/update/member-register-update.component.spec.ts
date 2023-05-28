import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MemberRegisterFormService } from './member-register-form.service';
import { MemberRegisterService } from '../service/member-register.service';
import { IMemberRegister } from '../member-register.model';

import { MemberRegisterUpdateComponent } from './member-register-update.component';

describe('MemberRegister Management Update Component', () => {
  let comp: MemberRegisterUpdateComponent;
  let fixture: ComponentFixture<MemberRegisterUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let memberRegisterFormService: MemberRegisterFormService;
  let memberRegisterService: MemberRegisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MemberRegisterUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(MemberRegisterUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MemberRegisterUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    memberRegisterFormService = TestBed.inject(MemberRegisterFormService);
    memberRegisterService = TestBed.inject(MemberRegisterService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const memberRegister: IMemberRegister = { id: 456 };

      activatedRoute.data = of({ memberRegister });
      comp.ngOnInit();

      expect(comp.memberRegister).toEqual(memberRegister);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMemberRegister>>();
      const memberRegister = { id: 123 };
      jest.spyOn(memberRegisterFormService, 'getMemberRegister').mockReturnValue(memberRegister);
      jest.spyOn(memberRegisterService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ memberRegister });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: memberRegister }));
      saveSubject.complete();

      // THEN
      expect(memberRegisterFormService.getMemberRegister).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(memberRegisterService.update).toHaveBeenCalledWith(expect.objectContaining(memberRegister));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMemberRegister>>();
      const memberRegister = { id: 123 };
      jest.spyOn(memberRegisterFormService, 'getMemberRegister').mockReturnValue({ id: null });
      jest.spyOn(memberRegisterService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ memberRegister: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: memberRegister }));
      saveSubject.complete();

      // THEN
      expect(memberRegisterFormService.getMemberRegister).toHaveBeenCalled();
      expect(memberRegisterService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMemberRegister>>();
      const memberRegister = { id: 123 };
      jest.spyOn(memberRegisterService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ memberRegister });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(memberRegisterService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
