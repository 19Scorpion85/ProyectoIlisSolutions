import { Component, OnInit } from '@angular/core';
import { Ciudadano } from './ciudadano';
import { CiudadanoService } from './ciudadano.service';
import {Router} from '@angular/router';
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

  public create():void{
     this.ciudadanoService.create(this.ciudadano).subscribe(
       response=> this.router.navigate(['ciudadano/'])
     )
  }


}
