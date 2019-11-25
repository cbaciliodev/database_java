import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment as env } from 'src/environments/environment';
import { Cargo } from 'src/app/models/cargo.model';
import swal from 'sweetalert';
import { Docente } from 'src/app/models/docente.model';
import { Actividad } from 'src/app/models/actividad.model';
import { Tema } from 'src/app/models/tema.model';
import { Asignado } from 'src/app/models/asignado.model';

@Injectable({
  providedIn: 'root'
})
export class CargoService {

  constructor(private _httpClient: HttpClient) { }

  /**
   * DOCENTES
   */

  findAllCargos(): Observable<any> {

    let url = env.API_URI.concat('cargo');
    return this._httpClient.get<any>(url)
      .pipe(catchError(e => throwError(e)));
  }

  createCargo(cargo: Cargo): Observable<any> {

    let url = env.API_URI.concat('cargo');

    return this._httpClient.post<any>(url, cargo)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }


  updateCargo(cargo: Cargo, id: number): Observable<any> {

    let url = env.API_URI.concat('cargo/' + `${id}`);

    return this._httpClient.put<any>(url, cargo)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }

  findOneById(id: number): Observable<any> {

    let url = env.API_URI.concat('cargo/' + `${id}`);

    return this._httpClient.get<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  deleteCargoById(id: number): Observable<any> {
    let url = env.API_URI.concat('cargo/' + `${id}`);

    return this._httpClient.delete<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  /**
   * DOCENTES
   */

  findAllDocentes(): Observable<any> {

    let url = env.API_URI.concat('docente');
    return this._httpClient.get<any>(url)
      .pipe(catchError(e => throwError(e)));
  }

  createDocente(docente: Docente): Observable<any> {

    let url = env.API_URI.concat('docente');

    return this._httpClient.post<any>(url, docente)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }


  updateDocente(docente: Docente, id: number): Observable<any> {

    let url = env.API_URI.concat('docente/' + `${id}`);

    return this._httpClient.put<any>(url, docente)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }

  findOneDocenteById(id: number): Observable<any> {

    let url = env.API_URI.concat('docente/' + `${id}`);

    return this._httpClient.get<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  deleteDocenteById(id: number): Observable<any> {
    let url = env.API_URI.concat('docente/' + `${id}`);

    return this._httpClient.delete<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  /**
    * ACTIVIDADES
    */
  findAllActividades(): Observable<any> {

    let url = env.API_URI.concat('actividad');
    return this._httpClient.get<any>(url)
      .pipe(catchError(e => throwError(e)));
  }

  createActividad(actividad: Actividad): Observable<any> {

    let url = env.API_URI.concat('actividad');

    return this._httpClient.post<any>(url, actividad)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }


  updateActividad(actividad: Actividad, id: number): Observable<any> {

    let url = env.API_URI.concat('actividad/' + `${id}`);

    return this._httpClient.put<any>(url, actividad)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }

  findOneActividadById(id: number): Observable<any> {

    let url = env.API_URI.concat('actividad/' + `${id}`);

    return this._httpClient.get<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  deleteActividadById(id: number): Observable<any> {
    let url = env.API_URI.concat('actividad/' + `${id}`);

    return this._httpClient.delete<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  /**
    * TEMAS
    */
  findAllTemas(): Observable<any> {

    let url = env.API_URI.concat('tema');
    return this._httpClient.get<any>(url)
      .pipe(catchError(e => throwError(e)));
  }

  createTemas(tema: Tema): Observable<any> {

    let url = env.API_URI.concat('tema');

    return this._httpClient.post<any>(url, tema)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }


  updateTemas(tema: Tema, id: number): Observable<any> {

    let url = env.API_URI.concat('tema/' + `${id}`);

    return this._httpClient.put<any>(url, tema)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }

  findOneTemasById(id: number): Observable<any> {

    let url = env.API_URI.concat('temas/' + `${id}`);

    return this._httpClient.get<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  findOneTemaById(id: number): Observable<any> {

    let url = env.API_URI.concat('tema/' + `${id}`);

    return this._httpClient.get<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }

  deleteTemaById(id: number): Observable<any> {
    let url = env.API_URI.concat('tema/' + `${id}`);

    return this._httpClient.delete<any>(url)
      .pipe(catchError(e => {
        return throwError(e)
      }));
  }


  /*
  ASIGNACION DE TEMA A PROFESORES
   */

  createAsignacion(asignado: Asignado): Observable<any> {

    let url = env.API_URI.concat('asignado');

    return this._httpClient.post<any>(url, asignado)
      .pipe(catchError(e => {
        //swal(`El Correo ya existe`, `${e.error.mensaje.errors.correo.message}`, `error`)
        return throwError(e)
      }));
  }

}
