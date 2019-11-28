import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert'
import { Actividad } from 'src/app/models/actividad.model';
import { Tema } from 'src/app/models/tema.model';
import { CargoService } from 'src/app/services/service.index';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Docente } from 'src/app/models/docente.model';
import { Cargo } from 'src/app/models/cargo.model';
import { Asignado } from 'src/app/models/asignado.model';

@Component({
  selector: 'app-reunion',
  templateUrl: './reunion.component.html',
  styleUrls: ['./reunion.component.css']
})
export class ReunionComponent implements OnInit {


  public _actividad = new Actividad(null, '', null);
  public _tema = new Tema(null, '', '', this._actividad);

  public _cargo = new Cargo(null, '');
  public _docente = new Docente(null, '', '', '', this._cargo,'');

  public _asignado = new Asignado(null, null, null, this._tema, this._docente);

  public _listaActividad: Actividad[];
  public _listaTemas: Tema[];
  public _listaDocente: Docente[];
  public cantidad: number;
  public p: number = 1;
  public paginador: any;

  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router) {
    this._listaTemas = [];
    this._listaActividad = [];
    this._listaDocente = [];
    
  }

  ngOnInit() {
    this.findAllActividad();
    this.findAllDocentes();
  }

  findAllActividad() {

    this._serviceCargo.findAllActividades()
      .subscribe(res => {

        if (res == null) {
          this._listaActividad = [];
          return;
        }
        this._listaActividad = res;
      })
  }

  valor(e: any) {

    if (e.target.value == '') {
      this._listaTemas = [];
      this._tema.vdesc_TEMA = '';
      this._docente.vnombre_DOCENTE = '';
      return;
    }
    this.findAllTemasById(e.target.value);
  }

  findAllTemasById(id: number) {
    this._serviceCargo.findOneTemasById(id)
      .subscribe(res => {

        if (res == null) {
          this._listaTemas = [];
          this._tema.vdesc_TEMA = '';

          return;
        }
        this._listaTemas = res;
      })
  }

  limpiarDocente(e: any) {


    if (e.target.value == '') {
      this._docente.vnombre_DOCENTE = '';
    }

    this._docente.vnombre_DOCENTE = '';
  }


  findAllDocentes() {

    //his._listaDocente = [];
    this._serviceCargo.findAllDocentes()
      .subscribe(res => {
        this._listaDocente = res;
      })
  }


  guardarDocente(f: NgForm, s: string) {

    if (f.invalid) {
      return;
    }

    if (s == 'create') {

      this._asignado.fk_ICOD_TEMA = f.value.icod_TEMA;
      this._asignado.fk_ICOD_DOCENTE = f.value.icod_DOCENTE;
      this._asignado.dfec_ASIGNADO = f.value.dfec_ACTIVIDAD;
      
      this._serviceCargo.createAsignacion(this._asignado)
        .subscribe(data => {
          swal(`Asignacion Creado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/listaAsignado'])
        });
    }



    /* if (s == 'update') {
      this._serviceCargo.updateTemas(this._tema, this._tema.icod_TEMA)
        .subscribe(data => {
          swal(`Docente Actualizado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/tema'])
        });
    } */
  }

  deleteTema(id: number) {

    swal(`Una vez eliminado, no podrás recuperar este registro!`, {
      title: `Estas seguro?`,
      icon: `warning`,
      buttons: [`Cancel`, `Ok`],
      dangerMode: true
    })
      .then((willDelete) => {
        if (willDelete) {
          this._serviceCargo.deleteTemaById(id)
            .subscribe(data => {

              if (data.mensaje == `The DELETE statement conflicted with the REFERENCE constraint "FK__TBL_REUNI__FK_IC__3E52440B". The conflict occurred in database "REUNION", table "dbo.TBL_REUNIONES", column 'FK_ICOD_DOCENTE'.`) {
                swal(`Lo siento, no de puede borrar`, `El registro esta relacionado con la Tabla Reuniones`, {
                  icon: `error`,
                });
              } else if (data.mensaje == `El registro ha sido eliminado con éxito.`) {
                swal(`Buen Trabajo`, ` ${data.mensaje}`, {
                  icon: `success`,
                });
              }
              this.findAllTemasById(data.tema.actividad.icod_ACTIVIDAD);
            });

        } else {
          swal(`Informacion`, `El registro no fue eliminado!`, `info`);
        }
      });
  }

}
