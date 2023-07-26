import { Injectable } from '@angular/core';
import {of,Observable,throwError} from 'rxjs';
import {Router} from '@angular/router';
import { TAREAS } from './tarea.json';
import { Tarea } from './tarea';
import {HttpClient,HttpHeaders,HttpRequest,HttpEvent} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TareaService {

  private urlEndPoint:string='http://localhost:8002/tarea/';

  constructor(private http:HttpClient,private router:Router) { }
  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  getTareas():Observable<Tarea[]>{
   // return  of(TAREAS);
   return this.http.get<Tarea[]>(this.urlEndPoint).pipe(
    map((response)=>response as Tarea[])
  );
  }

  create(tarea: Tarea): Observable<Tarea>{
    return this.http.post<Tarea>(this.urlEndPoint,tarea,{headers:this.httpHeaders})
  }



}
