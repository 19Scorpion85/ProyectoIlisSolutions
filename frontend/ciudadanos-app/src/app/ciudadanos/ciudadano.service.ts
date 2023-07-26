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

  //private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})


  constructor(private http:HttpClient,private router:Router) { }

  getCiudadanos():Observable<Ciudadano[]>{
   // return  of(CIUDADANOS);
      return this.http.get<Ciudadano[]>(this.urlEndPoint).pipe(
        map((response)=>response as Ciudadano[])
      );

  }

}
