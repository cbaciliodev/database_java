import { Routes, RouterModule } from "@angular/router";
import { PagesComponent } from "./pages.component";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { AccountSettingsComponent } from "./account-settings/account-settings.component";
import { CargoComponent } from "./cargo/cargo.component";
import { UpdateCargoComponent } from "./cargo/update-cargo.component";
import { DocenteComponent } from "./docente/docente.component";
import { UpdateDocenteComponent } from "./docente/update-docente.component";
import { ActividadesComponent } from "./actividades/actividades.component";
import { ActividadUpdateComponent } from "./actividades/actividad-update.component";
import { TemaComponent } from "./tema/tema.component";
import { UpdateTemaComponent } from './tema/update-tema.component';

const pagesRoutes: Routes = [
    {
        path: "",
        component: PagesComponent,
        children: [
            {
                path: "dashboard",
                component: DashboardComponent,
                data: { titulo: "Agenda", link: "dashboard" }
            },
            {
                path: "account-settings",
                component: AccountSettingsComponent,
                data: { titulo: "Configuración del Tema", link: "Configuración" }
            },
            {
                path: "cargo",
                component: CargoComponent,
                data: { titulo: "Cargo", link: "Cargo" }
            },
            {
                path: "updateCargo/:id",
                component: UpdateCargoComponent,
                data: { titulo: "Actualizar Cargo", link: "Cargo" }
            },
            {
                path: "docente",
                component: DocenteComponent,
                data: { titulo: "Docente", link: "Docente" }
            },
            {
                path: "updateDocente/:id",
                component: UpdateDocenteComponent,
                data: { titulo: "Actualizar Docente", link: "Docente" }
            },
            {
                path: "actividad",
                component: ActividadesComponent,
                data: { titulo: "Actividad", link: "Actividad" }
            },
            {
                path: "updateActividad/:id",
                component: ActividadUpdateComponent,
                data: { titulo: "Actualizar Actividad", link: "Actividad" }
            },
            {
                path: "tema",
                component: TemaComponent,
                data: { titulo: "Tema", link: "Tema" }
            },
            {
                path: "updateTema/:id",
                component: UpdateTemaComponent,
                data: { titulo: "Tema", link: "Tema" }
            },

            { path: "", redirectTo: "/dashboard", pathMatch: "full" }
        ]
    }
];

export const PagesRoutes = RouterModule.forChild(pagesRoutes);
