import { Component, OnInit } from '@angular/core';
import { Tarea } from './tarea';
import { TareaService } from './tarea.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-tareas',
  templateUrl: './tareas.component.html'
})
export class TareasComponent implements OnInit{

  tareas:Tarea[];

  constructor(private tareaService: TareaService){}

  ngOnInit(){
    this.tareaService.getTareas().subscribe(
      (tareas)=>{
        this.tareas=tareas
      }
    );
  }
  delete(tarea:Tarea):void{
    swal({
      title:'Esta seguro?',
      text:'¿Seguro que desea eliminar?',
      showCancelButton:true,
      confirmButtonColor:'#3085d6',
      cancelButtonColor:'#d33',
      confirmButtonText:'Si, eliminar!',
      cancelButtonText:'No, cancelar!',
      cancelButtonClass:'btn btn-danger',
      buttonsStyling:false,
      reverseButtons:true
    }).then((result) => {
         if (result.value) {
        this.tareaService.delete(tarea.id).subscribe(
          response=>{
            this.tareas=this.tareas.filter(cli=>cli !==tarea)
            swal('Tarea Eliminada!', 
                 'La tarea fue eliminada correctamente', 
                 'success')
          }
        )        
      } 
    })
  }
}
