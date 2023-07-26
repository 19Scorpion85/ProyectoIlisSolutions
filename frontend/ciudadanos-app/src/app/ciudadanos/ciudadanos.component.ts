import { Component, OnInit } from '@angular/core';
import { CiudadanoService } from './ciudadano.service';
import {ActivatedRoute} from '@angular/router';
import { Ciudadano } from './ciudadano';
import { CIUDADANOS } from './ciudadano.json';

@Component({
  selector: 'app-ciudadanos',
  templateUrl: './ciudadanos.component.html'
})
export class CiudadanosComponent implements OnInit {

  ciudadanos:Ciudadano[];

  constructor(private ciudadanoService: CiudadanoService){}

  ngOnInit(){
    this.ciudadanoService.getCiudadanos().subscribe(
      (ciudadanos)=>{
        this.ciudadanos=ciudadanos
      }
    );
  }

}
