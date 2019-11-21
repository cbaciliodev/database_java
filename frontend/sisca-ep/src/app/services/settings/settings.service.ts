import { Injectable, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  ajustes: Ajustes = {
    temaUrl: 'assets/css/colors/default.css',
    tema: 'default'
  }


  constructor(@Inject(DOCUMENT) private _document) {

    this.cargarAjustes()
  }

  guardarAjustes() {

    //console.log('Guardando los ajustes en el localstorage');
    localStorage.setItem('ajustes', JSON.stringify(this.ajustes));

  }

  cargarAjustes() {

    if (localStorage.getItem('ajustes')) {
      this.ajustes = JSON.parse(localStorage.getItem('ajustes'));
      //console.log('Cargando los ajustes del localstorage');

      this.aplicarTema(this.ajustes.tema);
    } else {
      //console.log('Usando valores por defecto');
      this.aplicarTema(this.ajustes.tema);
    }

  }


  aplicarTema(tema: string) {
    let url = `assets/css/colors/${tema}.css`;

    this._document.getElementById('tema').setAttribute('href', url, tema);

    this.ajustes.tema = tema;
    this.ajustes.temaUrl= url;

    this.guardarAjustes();
  }
}


interface Ajustes {

  temaUrl: string;
  tema: string;

}