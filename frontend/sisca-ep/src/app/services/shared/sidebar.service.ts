import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {

  menu: any = [
    {
      titulo: 'Principal',
      icono: 'mdi mdi-tag-text-outline',
      submenu: [
        {titulo: 'Dashboard', url: '/dashboard'},
        {titulo:'Asistencia', url:'/listaReunion'}
      ]
    },
    {
      titulo: 'Mantenimiento',
      icono: 'mdi mdi-gauge',
      submenu: [
        {titulo:'Cargos', url:'/cargo'},
        {titulo:'Docentes', url:'/docente'},
        {titulo:'Actividades',url:'/actividad'},
        {titulo:'Temas',url:'/tema'},
        {titulo:'Reuniones', url:'/reunion'}
      ]
    }
  ];


  constructor() { }
}
