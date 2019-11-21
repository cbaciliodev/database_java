import { Routes, RouterModule } from '@angular/router';
import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AccountSettingsComponent } from './account-settings/account-settings.component';
import { CargoComponent } from './cargo/cargo.component';
import { UpdateCargoComponent } from './cargo/update-cargo.component';
import { DocenteComponent } from './docente/docente.component';
import { UpdateDocenteComponent } from './docente/update-docente.component';
import { ActividadesComponent } from './actividades/actividades.component';
import { ActividadUpdateComponent } from './actividades/actividad-update.component';


const pagesRoutes: Routes = [

    {
        path: '', component: PagesComponent,
        children: [
            { path: 'dashboard', component: DashboardComponent },
            { path: 'account-settings', component: AccountSettingsComponent },
            { path: 'cargo', component: CargoComponent },
            { path: 'updateCargo/:id', component: UpdateCargoComponent },
            { path: 'docente', component: DocenteComponent },
            { path: 'updateDocente/:id', component: UpdateDocenteComponent },
            { path: 'actividad', component: ActividadesComponent },
            { path: 'updateActividad/:id', component: ActividadUpdateComponent },
            { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
        ]
    },

];

export const PagesRoutes = RouterModule.forChild(pagesRoutes);