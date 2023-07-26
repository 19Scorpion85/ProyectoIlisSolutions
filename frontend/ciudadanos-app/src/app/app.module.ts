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
import { FormComponent } from './ciudadanos/form.component';
import { FormTareaComponent } from './tareas/form-tarea.component';
import { FormsModule } from '@angular/forms';
import { FormTCiudadanoComponent } from './tareas/form-tciudadano.component';

const routes:Routes=[
  {path:'',redirectTo:'/ciudadanos',pathMatch:'full'},
  {path:'ciudadanos',component:CiudadanosComponent},
  {path:'tareas',component:TareasComponent},
  {path:'ciudadanos/form',component:FormComponent},
  {path:'ciudadanos/form/:id',component:FormComponent},
  {path:'tareas/form-tarea',component:FormTareaComponent},
  {path:'tareas/form-tarea/:id',component:FormTareaComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    CiudadanosComponent,
    TareasComponent,
    FormComponent,
    FormTareaComponent,
    FormTCiudadanoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [CiudadanoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
