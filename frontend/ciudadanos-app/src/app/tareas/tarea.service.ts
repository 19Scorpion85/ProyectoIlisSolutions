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
  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http:HttpClient,private router:Router) { }

  
  getTareas():Observable<Tarea[]>{
   // return  of(TAREAS);
   return this.http.get<Tarea[]>(this.urlEndPoint).pipe(
    map((response)=>response as Tarea[]));
  }

  create(tarea: Tarea): Observable<Tarea>{
    return this.http.post<Tarea>(this.urlEndPoint,tarea,{headers:this.httpHeaders})
  }

  getTarea(id):Observable<Tarea>{
    return this.http.get<Tarea>(`${this.urlEndPoint}${id}`)
   }
   
   update(tarea: Tarea): Observable<Tarea>{
    return this.http.put<Tarea>(`${this.urlEndPoint}${tarea.id}`,tarea,{headers: this.httpHeaders})
   }
  
  delete(id:number):Observable<Tarea>{
    return this.http.delete<Tarea>(`${this.urlEndPoint}${id}`,{headers:this.httpHeaders})
  }

}
