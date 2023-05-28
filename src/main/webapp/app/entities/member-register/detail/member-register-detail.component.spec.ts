import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MemberRegisterDetailComponent } from './member-register-detail.component';

describe('MemberRegister Management Detail Component', () => {
  let comp: MemberRegisterDetailComponent;
  let fixture: ComponentFixture<MemberRegisterDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MemberRegisterDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ memberRegister: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(MemberRegisterDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MemberRegisterDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load memberRegister on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.memberRegister).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
