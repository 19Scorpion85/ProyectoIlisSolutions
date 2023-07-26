import { Component, OnInit } from '@angular/core';
import { Ciudadano } from './ciudadano';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
   
  public ciudadano:Ciudadano=new Ciudadano();
  public titulo:string = "Creación de ciudadano";

  constructor() { }

  ngOnInit(): void {
  }

  public create():void{
    console.log("Cliked!")
    console.log(this.ciudadano)
  }


}
