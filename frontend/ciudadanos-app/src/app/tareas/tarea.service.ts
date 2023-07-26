import { Injectable } from '@angular/core';
import {of,Observable,throwError} from 'rxjs';
import {Router} from '@angular/router';
import { TAREAS } from './tarea.json';
import { Tarea } from './tarea';
import {HttpClient,HttpHeaders,HttpRequest,HttpEvent} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TareaService {

  constructor(private http:HttpClient,private router:Router) { }

  getTareas():Observable<Tarea[]>{
    return  of(TAREAS);
  }

}
