import { Component, OnInit } from '@angular/core';
import { Tarea } from './tarea';

@Component({
  selector: 'app-form-tarea',
  templateUrl: './form-tarea.component.html'
})
export class FormTareaComponent implements OnInit {

  public tarea:Tarea=new Tarea();
  public titulo:string = "Agregar tarea";

  constructor() { }

  ngOnInit(): void {
  }

  public create():void{
    console.log("Cliked!")
    console.log(this.tarea)
  }


}
