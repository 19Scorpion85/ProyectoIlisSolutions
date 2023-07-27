import { Component, OnInit } from '@angular/core';
import { Tarea } from './tarea';
import { TareaService } from './tarea.service';
import {Router,ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form-tarea',
  templateUrl: './form-tarea.component.html'
})
export class FormTareaComponent implements OnInit {

  public tarea:Tarea=new Tarea();
  public titulo:string = "Agregar tarea";

  constructor(public tareaService:TareaService,
              public router:Router,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarTarea();
  }

  daysOfWeek: string[] = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];

  cargarTarea():void{
    this.activatedRoute.params.subscribe(params=>{
      let id = params['id']
      if(id){
        this.tareaService.getTarea(id).subscribe((tarea)=>this.tarea=tarea)
      }
    })
  } 

  create():void{
    this.tareaService.create(this.tarea)
    .subscribe(tarea=> {
     this.router.navigate(['/tareas'])
     swal('Nueva tarea',`creada con existo`,`success`)
   });
 }
 
 update():void{
  this.tareaService.update(this.tarea)
  .subscribe( tarea=>{
    this.router.navigate(['/tareas'])
    swal('Tarea Actualizada',`La tarea fue actualizada correctamente`,`success`)
  })
}


}
