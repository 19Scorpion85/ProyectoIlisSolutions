import { Component, OnInit } from '@angular/core';
import { Ciudadano } from './ciudadano';
import { CiudadanoService } from './ciudadano.service';
import {Router,ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
   
  public ciudadano:Ciudadano=new Ciudadano();
  public titulo:string = "Agregar ciudadano";

  constructor(private ciudadanoService: CiudadanoService,
              private router:Router,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCiudadano();
  }

  cargarCiudadano():void{
    this.activatedRoute.params.subscribe(params=>{
      let id = params['id']
      if(id){
        this.ciudadanoService.getCiudadano(id).subscribe((ciudadano)=>this.ciudadano=ciudadano)
      }
    })
  }

  create():void{
     this.ciudadanoService.create(this.ciudadano)
     .subscribe(ciudadano=> {
      this.router.navigate(['ciudadano/'])
      swal('Nuevo ciudadano',`creado con existo`,`success`)
    });
  }
  
  update():void{
    this.ciudadanoService.update(this.ciudadano)
    .subscribe( ciudadano=>{
      this.router.navigate(['ciudadano/'])
      swal('Ciudadano Actualizado',`Ciudadano actualizado correctamente`,`success`)
    })
  }
  
}
