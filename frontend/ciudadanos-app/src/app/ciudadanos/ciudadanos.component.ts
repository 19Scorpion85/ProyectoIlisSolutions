import { Component, OnInit } from '@angular/core';
import { CiudadanoService } from './ciudadano.service';
import {ActivatedRoute} from '@angular/router';
import { Ciudadano } from './ciudadano';

@Component({
  selector: 'app-ciudadanos',
  templateUrl: './ciudadanos.component.html'
})
export class CiudadanosComponent implements OnInit {

  ciudadanos:Ciudadano[];

  constructor(private ciudadanoService: CiudadanoService,
    private activatedRoute: ActivatedRoute ){}

  ngOnInit(): void {
  }

}
