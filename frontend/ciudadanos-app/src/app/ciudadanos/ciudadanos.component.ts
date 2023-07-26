import { Component, OnInit } from '@angular/core';
import { CiudadanoService } from './ciudadano.service';
import {ActivatedRoute} from '@angular/router';
import { Ciudadano } from './ciudadano';
import { CIUDADANOS } from './ciudadano.json';
import swal from 'sweetalert2';

@Component({
  selector: 'app-ciudadanos',
  templateUrl: './ciudadanos.component.html'
})
export class CiudadanosComponent implements OnInit {

  ciudadanos:Ciudadano[];

  constructor(private ciudadanoService: CiudadanoService){}

  ngOnInit(){
    this.ciudadanoService.getCiudadanos().subscribe(
      (ciudadanos)=>{
        this.ciudadanos=ciudadanos
      }
    );
  }

  delete(ciudadano:Ciudadano):void{
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
      /* Read more about isConfirmed, isDenied below */
      if (result.value) {
        this.ciudadanoService.delete(ciudadano.id).subscribe(
          response=>{
            swal('Eliminado!', 'ciudadano fue eliminado correctamente', 'success')
          }
        )        
      } 
    })
  }

}
