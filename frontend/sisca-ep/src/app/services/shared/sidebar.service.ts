import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {

  menu: any = [
    {
      titulo: 'Principal',
      icono: 'mdi mdi-gauge',
      submenu: [
        {titulo: 'Dashboard', url: '/dashboard'},
        {titulo:'Cargos', url:'/cargo'},
        {titulo:'Docentes', url:'/docente'},
        {titulo:'Actividades',url:'/actividad'}
      ]
    }
  ];


  constructor() { }
}
