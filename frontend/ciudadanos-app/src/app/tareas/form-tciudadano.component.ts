import { Component, OnInit } from '@angular/core';
import { Tarea } from './tarea';
import { TareaService } from './tarea.service';
import {Router,ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2';
import { Ciudadano } from '../ciudadanos/ciudadano';

@Component({
  selector: 'app-form-tciudadano',
  templateUrl: './form-tciudadano.component.html'
})
export class FormTCiudadanoComponent implements OnInit {
   
  tareas:Tarea[];
 
  public tarea:Tarea=new Tarea();
  public titulo:string = "Agregar tarea";


              constructor(private tareaService: TareaService){}

              ngOnInit(){
                this.tareaService.getTareas().subscribe(
                  (tareas)=>{
                    this.tareas=tareas
                  }
                );
              }

}
