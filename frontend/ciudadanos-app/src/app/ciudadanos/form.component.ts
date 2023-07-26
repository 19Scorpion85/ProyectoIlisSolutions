import { Component, OnInit } from '@angular/core';
import { Ciudadano } from './ciudadano';
import { CiudadanoService } from './ciudadano.service';
import {Router} from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
   
  public ciudadano:Ciudadano=new Ciudadano();
  public titulo:string = "Agregar ciudadano";

  constructor(private ciudadanoService: CiudadanoService,private router:Router) { }

  ngOnInit(): void {
  }

  create():void{
     this.ciudadanoService.create(this.ciudadano)
     .subscribe(ciudadano=> {
      this.router.navigate(['ciudadano/'])
      swal('Nuevo ciudadano',`creado con existo`)
    });
  }
  
  

}
