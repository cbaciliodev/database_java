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
    UpdateTemaComponent
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
