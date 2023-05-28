import { Component, OnInit } from '@angular/core';
import { MembreModel } from '../membre-model';
import { MembreserviceService } from '../service/membreservice.service';

@Component({
  selector: 'jhi-membre-list',
  templateUrl: './membre-list.component.html',
  styleUrls: ['./membre-list.component.scss']
})
export class MembreListComponent implements OnInit {

  membres: MembreModel[] = [];

  constructor(private membreService: MembreserviceService) {
  }

  ngOnInit():void {
    this.membreService.findAll().subscribe(data => {
      this.membres = data;
    });
  }

}
