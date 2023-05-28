import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberRegisreComponent } from './member-regisre.component';

describe('MemberRegisreComponent', () => {
  let component: MemberRegisreComponent;
  let fixture: ComponentFixture<MemberRegisreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MemberRegisreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MemberRegisreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
