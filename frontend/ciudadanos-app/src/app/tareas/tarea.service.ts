import { Injectable } from '@angular/core';
import {of,Observable,throwError} from 'rxjs';
import {HttpClient,HttpHeaders,HttpRequest,HttpEvent} from '@angular/common/http';
import {map} from 'rxjs/operators';
import { Tarea } from './tarea';
import {Router} from '@angular/router';
import { TAREAS } from './tarea.json';

@Injectable()
export class TareaService {

  //private urlEndPoint:string='http://localhost:8001/ciudadano/';

 // private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})


  constructor(private http:HttpClient,private router:Router) { }

  getTareas():Observable<Tarea[]>{
    return  of(TAREAS);
  }

}