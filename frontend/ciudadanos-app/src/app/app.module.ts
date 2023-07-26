import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { CiudadanosComponent } from './ciudadanos/ciudadanos.component';
import {CiudadanoService} from './ciudadanos/ciudadano.service';
import { RouterModule, Routes } from '@angular/router';
import{HttpClientModule} from '@angular/common/http';
import { TareasComponent } from './tareas/tareas.component';

const routes:Routes=[
  {path:'',redirectTo:'/ciudadanos',pathMatch:'full'},
  {path:'ciudadanos',component:CiudadanosComponent},
  {path:'tareas',component:TareasComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    CiudadanosComponent,
    TareasComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [CiudadanoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
