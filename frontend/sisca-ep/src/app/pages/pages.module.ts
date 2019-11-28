import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PagesComponent } from './pages.component';
import { SharedModule } from '../shared/shared.module';
import {NgxPaginationModule} from 'ngx-pagination'; // <-- import the module

//formModule:
import { FormsModule } from '@angular/forms';

//Routes
import { PagesRoutes } from './pages.routes';

//graficas
import { ChartsModule } from 'ng2-charts';
import { AccountSettingsComponent } from './account-settings/account-settings.component';
import { CargoComponent } from './cargo/cargo.component';
import { UpdateCargoComponent } from './cargo/update-cargo.component';
import { DocenteComponent } from './docente/docente.component';
import { UpdateDocenteComponent } from './docente/update-docente.component';
import { ActividadesComponent } from './actividades/actividades.component';
import { ActividadUpdateComponent } from './actividades/actividad-update.component';
import { TemaComponent } from './tema/tema.component';
import { UpdateTemaComponent } from './tema/update-tema.component';
import { ReunionComponent } from './reunion/reunion.component';
import { AsignacionesTemasComponent } from './reunion/asignaciones-temas.component';
import { AsistenciaComponent } from './asistencia/asistencia.component';
import { CreateReunionComponent } from './reunion/create-reunion.component';
import { FormularioReunionComponent } from './reunion/formulario-reunion.component';


@NgModule({
  declarations: [
    PagesComponent,
    DashboardComponent,
    AccountSettingsComponent,
    CargoComponent,
    UpdateCargoComponent,
    DocenteComponent,
    UpdateDocenteComponent,
    ActividadesComponent,
    ActividadUpdateComponent,
    TemaComponent,
    UpdateTemaComponent,
    ReunionComponent,
    AsignacionesTemasComponent,
    AsistenciaComponent,
    CreateReunionComponent,
    FormularioReunionComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    PagesRoutes,
    FormsModule,
    ChartsModule,
    NgxPaginationModule
  ],
  exports: [
    PagesComponent,
    DashboardComponent
  ]
})
export class PagesModule { }
