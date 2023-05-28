import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormGroup, FormControl,Validators } from '@angular/forms';
import { MembreregistreService } from './membreregistre.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'jhi-member-regisre',
  templateUrl: './member-regisre.component.html',
  styleUrls: ['./member-regisre.component.scss']
})
export class MemberRegisreComponent {
  
  error = false;
  success = false;
  title='تسجيل عضو جديد';

  constructor(private membreregistreService:MembreregistreService,  private route: ActivatedRoute, 
    private router: Router ) { }
  membreregisterForm = new FormGroup({
    lastName: new FormControl('', {
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50)

      ],
    }),
  firstName: new FormControl('', {
  nonNullable: true,
  validators: [
    Validators.required,
    Validators.minLength(1),
    Validators.maxLength(50)
  ],
}),
   surnom : new FormControl('', {
    nonNullable: true,
    validators: [
      Validators.required,
      Validators.minLength(1),
      Validators.maxLength(150)
    ],
  }),
 dateregistre: new FormControl('', {
  nonNullable: true,
  validators: [
    Validators.required,
    Validators.minLength(1),
    Validators.maxLength(50)

  ],
}),
  phonenumber: new FormControl('', {
    nonNullable: true,
    validators: [
      Validators.required,
      Validators.minLength(10),
      Validators.maxLength(10)

    ],
  }),
  email : new FormControl('', {
    nonNullable: true,
    validators: [
      Validators.required,
      Validators.minLength(1),
      Validators.maxLength(50),
    ],
  }),
  adresse : new FormControl('', {
    nonNullable: true,
    validators: [
      Validators.required,
      Validators.minLength(1),
      Validators.maxLength(250)

    ],
  }),
  
  etat : new FormControl('', {
    nonNullable: true,
    validators: [
      Validators.required,
      Validators.minLength(1),
      Validators.maxLength(50)

    ],
  }),
});

registre():void{
  const { lastName,
    firstName,
 surnom,
 dateregistre,
 phonenumber,
 email,
  adresse,
 etat } = this.membreregisterForm.getRawValue();
 this.membreregistreService
        .save({lastName,
          firstName,
         surnom,
         dateregistre,
         phonenumber,
         email,
          adresse,
         etat })
        .subscribe({ next: () => (this.success = true), error: response => this.processError(response) });

    }
    private processError(response: HttpErrorResponse): void {
      if (response.status === 400 ) {
        this.error = true;
      }
    }

  

}
