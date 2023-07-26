import { Injectable } from '@angular/core';
import {of,Observable,throwError} from 'rxjs';
import {HttpClient,HttpHeaders,HttpRequest,HttpEvent} from '@angular/common/http';
import {map} from 'rxjs/operators';
import { Ciudadano } from './ciudadano';
import {Router} from '@angular/router';
import { CIUDADANOS } from './ciudadano.json';

@Injectable()
export class CiudadanoService {

  private urlEndPoint:string='http://localhost:8001/ciudadano/';
  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http:HttpClient,private router:Router) { }

  getCiudadanos():Observable<Ciudadano[]>{
   // return  of(CIUDADANOS);
      return this.http.get<Ciudadano[]>(this.urlEndPoint).pipe(
        map((response)=>response as Ciudadano[])
      );
  }

  create(ciudadano: Ciudadano): Observable<Ciudadano>{
    return this.http.post<Ciudadano>(this.urlEndPoint,ciudadano,{headers:this.httpHeaders})
  }

   getCiudadano(id):Observable<Ciudadano>{
    return this.http.get<Ciudadano>(`${this.urlEndPoint}/${id}`)
   }
   
   update(ciudadano: Ciudadano): Observable<Ciudadano>{
    return this.http.put<Ciudadano>(`${this.urlEndPoint}/${ciudadano.id}`,ciudadano,{headers: this.httpHeaders})
   }
  
  delete(id:number):Observable<Ciudadano>{
    return this.http.delete<Ciudadano>(`${this.urlEndPoint}/${id}`,{headers:this.httpHeaders})
  }

}
