import { Component, OnInit } from '@angular/core';
import { Tarea } from './tarea';
import { TareaService } from './tarea.service';

@Component({
  selector: 'app-tareas',
  templateUrl: './tareas.component.html',
  styleUrls: ['./tareas.component.css']
})
export class TareasComponent  {
  tareas:Tarea[];

  constructor(private tareaService: TareaService){}

  ngOnInit(){
    this.tareaService.getTareas().subscribe(
      (tareas)=>{
        this.tareas=tareas
      }
    );
  }

}
