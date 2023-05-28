import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MEMBRE_ROUTE } from './membre.route';
import { MembreListComponent } from './membre-list/membre-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [MembreListComponent],
  imports: [
    CommonModule,RouterModule.forChild(MEMBRE_ROUTE),ReactiveFormsModule

  ]
})
export class MembreModule { }
