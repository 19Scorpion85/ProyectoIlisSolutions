import { Component, OnInit } from '@angular/core';
import { Tarea } from './tarea';
import { TareaService } from './tarea.service';
import {Router} from '@angular/router';
import swal from 'sweetalert2';

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

  daysOfWeek: string[] = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];

  createTarea():void{
    this.tareaService.create(this.tarea).subscribe(
      tarea=> {
        this.router.navigate(['tarea/'])
        swal('Nueva tarea',`creada con existo`)
      });
  }


}
