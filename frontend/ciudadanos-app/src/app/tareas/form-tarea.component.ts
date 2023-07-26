import { Component, OnInit } from '@angular/core';
import { Tarea } from './tarea';
import { TareaService } from './tarea.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-form-tarea',
  templateUrl: './form-tarea.component.html'
})
export class FormTareaComponent implements OnInit {

  public tarea:Tarea=new Tarea();
  public titulo:string = "Agregar tarea";

  constructor(public tareaService:TareaService,public router:Router) { }

  ngOnInit(): void {
  }

  public create():void{
    this.tareaService.create(this.tarea).subscribe(
      response=> this.router.navigate(['/tareas'])
    )
  }


}
