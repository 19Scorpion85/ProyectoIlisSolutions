import { Injectable } from '@angular/core';
import {Observable,throwError} from 'rxjs';
import {HttpClient,HttpHeaders,HttpRequest,HttpEvent} from '@angular/common/http'
import {map} from 'rxjs/operators';
import { Ciudadano } from './ciudadano';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CiudadanoService {

  private urlEndPoint:string='http://localhost:8001/ciudadano';

  constructor(private http:HttpClient,private router:Router) { }

  getCiudadanos():Observable<Ciudadano[]>{
    return this.http.get(this.urlEndPoint).pipe(
      map((response) =>response as Ciudadano[])
    );
  }

}
